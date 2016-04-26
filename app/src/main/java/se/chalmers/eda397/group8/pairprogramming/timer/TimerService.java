package se.chalmers.eda397.group8.pairprogramming.timer;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Binder;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import group8.eda397.chalmers.se.pairprogramming.R;

/**
 * A service for running a CountDownTimer that may live for longer than the
 * TimerActivity. To communicate with the TimerService, bind it and use the
 * {@link TimerServiceBinder}.
 */
public class TimerService extends Service {


    /**
     * State indicating that the timer is not yet started.
     */
    public static final int STATE_TIMER_NOT_STARTED = 0x1;
    /**
     * State for when the timer is started, but is not yet finished.
     */
    public static final int STATE_TIMER_STARTED = 0x2;
    /**
     * State for when the timer was previously started has also finished.
     */
    public static final int STATE_TIMER_FINISHED = 0x3;

    public interface Listener {

        void onTimerTick(long millisUntilFinished);

        void onTimerFinish();

    }

    public static Intent getStartingIntent(Context context) {
        return new Intent(context, TimerService.class);
    }

    private static final int COUNT_DOWN_INTERVAL = 500;
    private static final int TIMER_NOTIFICATION_ID = 1;

    private final IBinder mBinder = new TimerServiceBinder();

    private NotificationManager mNotificationManager;
    private CountDownTimer mCountDownTimer;
    private Listener mListener;

    private boolean mNotificationShown;
    private long mMillisUntilFinished = 0;
    private int mTimerState = STATE_TIMER_NOT_STARTED;

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
        if (mTimerState == STATE_TIMER_STARTED) {
            // Timer is running, start ourselves as a foreground service
            moveToForeground();
            return true;
        } else {
            // No timer running, so stop ourselves
            stopSelf();
            return false;
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
        cancelTimer();

        mTimerState = STATE_TIMER_STARTED;
        mMillisUntilFinished = millisInFuture;

        mCountDownTimer = new CountDownTimerImpl(millisInFuture, COUNT_DOWN_INTERVAL);
        mCountDownTimer.start();
    }

    public void cancelTimer() {
        mTimerState = STATE_TIMER_NOT_STARTED;
        mMillisUntilFinished = 0;

        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
            mCountDownTimer = null;
        }
    }

    public long getMillisUntilFinished() {
        return mMillisUntilFinished;
    }

    public int getTimerState() {
        return mTimerState;
    }

    private void onTimerTick(long millisUntilFinished) {
        mMillisUntilFinished = millisUntilFinished;
        if (mListener != null) {
            mListener.onTimerTick(millisUntilFinished);
        }
        updateNotification();
    }

    private void onTimerFinish() {
        mTimerState = STATE_TIMER_FINISHED;

        if (mListener != null) {
            mListener.onTimerFinish();
        }

        updateNotification();
        playTimerFinishedSound();
    }

    private void updateNotification() {
        if (mNotificationShown) {
            Notification newNotification = createTimerNotification();
            mNotificationManager.notify(TIMER_NOTIFICATION_ID, newNotification);
        }
    }

    private void playTimerFinishedSound() {
        Uri ringtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Ringtone ringtone = RingtoneManager.getRingtone(this, ringtoneUri);
        if (ringtone != null) {
            ringtone.play();
        }
    }

    private void moveToForeground() {
        mNotificationShown = true;
        Notification notification = createTimerNotification();
        startForeground(TIMER_NOTIFICATION_ID, notification);
    }

    private void moveToBackground() {
        mNotificationShown = false;
        stopForeground(true);
    }

    private Notification createTimerNotification() {
        PendingIntent contentIntent = createNotificationContentIntent();
        long minutes = mMillisUntilFinished / (60 * 1000);
        long seconds = (mMillisUntilFinished / 1000) % 60;
        String parsedTime = String.format("%02d:%02d", minutes, seconds);
        return new NotificationCompat.Builder(this)
                .setContentTitle("Pair Programming Timer")
                .setContentText(parsedTime)
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
