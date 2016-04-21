package group8.eda397.chalmers.se.pairprogramming.timer;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

/**
 * A service for running a CountDownTimer that may live for longer than the
 * TimerActivity. It is started with a countdown, and broadcasts progress
 * via the LocalBroadcastManager.
 * <p/>
 * Note: To change the time being counted down to, start the Service again
 * with the new time.
 */
public class TimerService extends Service {

    private static final String TIMER_BROADCAST =
            "group8.eda397.chalmers.se.pairprogramming.timer.TIMER_BROADCAST";

    private static final String TIMER_BROADCAST_EXTRA_MILLIS_UNTIL_FINISHED =
            "group8.eda397.chalmers.se.pairprogramming.timer.TIMER_BROADCAST_EXTRA_MILLIS_UNTIL_FINISHED";

    private static final String TIMER_BROADCAST_EXTRA_IS_FINISHED =
            "group8.eda397.chalmers.se.pairprogramming.timer.TIMER_BROADCAST_EXTRA_IS_FINISHED";

    private static final String INTENT_EXTRA_PARAM_COUNTDOWN_TIME =
            "group8.eda397.chalmers.se.pairprogramming.timer.INTENT_PARAM_COUNTDOWN_TIME";

    private CountDownTimer mCountDownTimer;
    private LocalBroadcastManager mLocalBroadcastManager;

    private long mMillisUntilFinished;
    private boolean mIsFinished;

    /**
     * Gets an intent for starting the TimerService, and sets its timer for millisInFuture
     * milliseconds in the future.
     *
     * @param context        The context.
     * @param millisInFuture The number of millis in the future from that the timer is started
     *                       to that it is finished.
     * @return The intent to use for starting the service.
     */
    public static Intent getStartingIntent(Context context, long millisInFuture) {
        if (millisInFuture <= 0) {
            throw new IllegalArgumentException("millisInFuture must be > 0");
        }

        Intent callingIntent = new Intent(context, TimerService.class);
        callingIntent.putExtra(INTENT_EXTRA_PARAM_COUNTDOWN_TIME, millisInFuture);
        return callingIntent;
    }

    /**
     * Gets an intent for starting the TimerService and making it report
     * progress of the currently running timer, if any, as a broadcast.
     *
     * @param context The context.
     * @return The intent to use for starting the service.
     */
    public static Intent getStartingIntent(Context context) {
        return new Intent(context, TimerService.class);
    }

    public static IntentFilter getBroadcastFilter() {
        return new IntentFilter(TIMER_BROADCAST);
    }

    public static boolean parseBroadcastIsFinished(Intent intent) {
        return intent.getBooleanExtra(TIMER_BROADCAST_EXTRA_IS_FINISHED, false);
    }

    public static long parseBroadcastMillisUntilFinished(Intent intent) {
        return intent.getLongExtra(TIMER_BROADCAST_EXTRA_MILLIS_UNTIL_FINISHED, 0);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
        mIsFinished = true;
        mMillisUntilFinished = 0;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // We do not support binding
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("TimerService", "onStartCommand");

        long millisInFuture = -1;
        if (intent != null) {
            millisInFuture = intent.getLongExtra(INTENT_EXTRA_PARAM_COUNTDOWN_TIME, -1);
        }

        if (millisInFuture > 0) {
            startTimer(millisInFuture);
        }

        if (!mIsFinished) {
            sendTimerBroadcast();
        } else {
            // We were started, but without a millisInFuture, so stop directly.
            stopSelf();
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("TimerService", "onDestroy");
        cancelTimer();
    }

    private void cancelTimer() {
        mIsFinished = true;
        mMillisUntilFinished = 0;
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
            mCountDownTimer = null;
        }
    }

    private void startTimer(long millisInFuture) {
        if (millisInFuture <= 0) {
            throw new IllegalArgumentException("millisInFuture must be > 0");
        }

        cancelTimer();

        mIsFinished = false;
        mMillisUntilFinished = millisInFuture;

        mCountDownTimer = new CountDownTimerImpl(millisInFuture, 500);
        mCountDownTimer.start();
    }

    private void onTimerTick(long millisUntilFinished) {
        mMillisUntilFinished = millisUntilFinished;
        sendTimerBroadcast();
    }

    private void onTimerFinish() {
        mIsFinished = true;
        sendTimerBroadcast();
        stopSelf();
    }

    private void sendTimerBroadcast() {
        Intent intent = new Intent(TIMER_BROADCAST);
        intent.putExtra(TIMER_BROADCAST_EXTRA_IS_FINISHED, mIsFinished);
        intent.putExtra(TIMER_BROADCAST_EXTRA_MILLIS_UNTIL_FINISHED, mMillisUntilFinished);
        mLocalBroadcastManager.sendBroadcast(intent);
    }

    private class CountDownTimerImpl extends CountDownTimer {

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
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
