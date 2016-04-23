package group8.eda397.chalmers.se.pairprogramming.timer;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import group8.eda397.chalmers.se.pairprogramming.R;

/**
 * A service for running a CountDownTimer that may live for longer than the
 * TimerActivity. It is started with a countdown, and broadcasts progress
 * via the LocalBroadcastManager.
 * <p/>
 * Note: To change the time being counted down to, start the Service again
 * with the new time.
 */
public class TimerService extends Service {

    public static final int TIMER_NOTIFICATION_ID = 1;

    public interface Listener {

        void onTimerTick(long millisUntilFinished);

        void onTimerFinish();

    }

    public static Intent getStartingIntent(Context context) {
        return new Intent(context, TimerService.class);
    }

    private final IBinder mBinder = new TimerServiceBinder();

    private NotificationManager mNotificationManager;
    private CountDownTimer mCountDownTimer;
    private Listener mListener;

    private boolean mNotificationShown;
    private long mMillisUntilFinished = 0;
    private boolean mTimerFinished = true;

    @Override
    public void onCreate() {
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        if (mTimerFinished) {
            // No timer running, so stop ourselves
            stopSelf();
            return false;
        } else {
            // Timer is running, start ourselves as a foreground service
            moveToForeground();
            return true;
        }
    }

    @Override
    public void onRebind(Intent intent) {
        moveToBackground();
    }

    public void setListener(@Nullable Listener listener) {
        mListener = listener;
    }

    public void startTimer(long millisInFuture) {
        if (millisInFuture <= 0) {
            throw new IllegalArgumentException("millisInFuture must be > 0");
        }
        cancelTimer();

        mTimerFinished = false;
        mMillisUntilFinished = millisInFuture;

        mCountDownTimer = new CountDownTimerImpl(millisInFuture, 500);
        mCountDownTimer.start();
    }

    public void cancelTimer() {
        mTimerFinished = true;
        mMillisUntilFinished = 0;
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
            mCountDownTimer = null;
        }
    }

    public long getMillisUntilFinished() {
        return mMillisUntilFinished;
    }

    public boolean isFinished() {
        return mTimerFinished;
    }

    private void onTimerTick(long millisUntilFinished) {
        mMillisUntilFinished = millisUntilFinished;
        if (mListener != null) {
            mListener.onTimerTick(millisUntilFinished);
        }
        updateNotification();
    }

    private void onTimerFinish() {
        mTimerFinished = true;
        if (mListener != null) {
            mListener.onTimerFinish();
        }
        updateNotification();
    }

    private void updateNotification() {
        if (mNotificationShown) {
            Notification newNotification = createTimerNotification();
            mNotificationManager.notify(TIMER_NOTIFICATION_ID, newNotification);
        }
    }

    private void moveToForeground() {
        mNotificationShown = true;
        Notification notification = createTimerNotification();
        startForeground(TIMER_NOTIFICATION_ID, notification);
    }

    private Notification createTimerNotification() {
        PendingIntent contentIntent = createNotificationContentIntent();
        return new NotificationCompat.Builder(this)
                .setContentTitle("Timer")
                .setContentText(String.format("%d", mMillisUntilFinished))
                .setSmallIcon(R.drawable.ic_add_white_24dp)
                .setContentIntent(contentIntent)
                .build();
    }

    private PendingIntent createNotificationContentIntent() {
        // See http://developer.android.com/guide/topics/ui/notifiers/notifications.html#DirectEntry
        Intent timerIntent = TimerActivity.getCallingIntent(this);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(TimerActivity.class);
        stackBuilder.addNextIntent(timerIntent);
        return stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private void moveToBackground() {
        mNotificationShown = false;
        stopForeground(true);
    }

    /**
     * The Binder used when binding to the TimerService, simply
     * exposing a method for getting the TimerService instance.
     */
    public class TimerServiceBinder extends Binder {
        /**
         * Gets the TimerService instance.
         *
         * @return The TimerService instance.
         */
        TimerService getTimerService() {
            return TimerService.this;
        }
    }

    /**
     * Simple implementation of CountDownTimer that forwards
     * any callbacks to the TimerService.
     */
    private class CountDownTimerImpl extends CountDownTimer {
        public CountDownTimerImpl(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            onTimerTick(millisUntilFinished);
        }

        @Override
        public void onFinish() {
            onTimerFinish();
        }
    }
}
