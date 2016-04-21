package group8.eda397.chalmers.se.pairprogramming.timer;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;

/**
 * A service for running a CountDownTimer that may live for longer than the
 * TimerActivity. It is started with a countdown, and broadcasts progress
 * via the LocalBroadcastManager.
 * <p/>
 * Note: To change the time being counted down to, start the Service again
 * with the new time.
 */
public class TimerService extends Service {

    /**
     * The intent the TimerService will broadcast to for timer ticks and timer finish.
     */
    public static final String TIMER_BROADCAST =
            "group8.eda397.chalmers.se.pairprogramming.timer.TIMER_BROADCAST";

    /**
     * Used as a long value for TIMER_BROADCAST to represent the time remaining
     * (in milliseconds).
     */
    public static final String TIMER_BROADCAST_EXTRA_TIME_REMAINING =
            "group8.eda397.chalmers.se.pairprogramming.timer.TIMER_BROADCAST_EXTRA_TIME_REMAINING";

    /**
     * Used as a boolean value for TIMER_BROADCAST to represent if the timer is
     * finished.
     */
    public static final String TIMER_BROADCAST_EXTRA_IS_FINISHED =
            "group8.eda397.chalmers.se.pairprogramming.timer.TIMER_BROADCAST_EXTRA_IS_FINISHED";


    private static final String INTENT_EXTRA_PARAM_COUNTDOWN_TIME =
            "group8.eda397.chalmers.se.pairprogramming.timer.INTENT_PARAM_COUNTDOWN_TIME";

    private CountDownTimer mCountDownTimer;
    private LocalBroadcastManager mLocalBroadcastManager;

    /**
     * Gets an intent for starting the TimerService, which starts a timer for millisInFuture
     * milliseconds in the future.
     *
     * @param context        The context.
     * @param millisInFuture The number of millis in the future from that the timer is started
     *                       to that it is finished.
     * @return The intent to use for starting the service.
     */
    public static Intent getStartingIntent(Context context, long millisInFuture) {
        Intent callingIntent = new Intent(context, TimerService.class);
        callingIntent.putExtra(INTENT_EXTRA_PARAM_COUNTDOWN_TIME, millisInFuture);
        return callingIntent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // We do not support binding
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        long millisInFuture = intent.getLongExtra(INTENT_EXTRA_PARAM_COUNTDOWN_TIME, -1);
        if (millisInFuture <= 0) {
            throw new IllegalArgumentException("millisInFuture must be > 0");
        }
        startTimer(millisInFuture);

        return super.onStartCommand(intent, flags, startId);
    }

    private void startTimer(long millisInFuture) {
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
        mCountDownTimer = new CountDownTimerImpl(millisInFuture, 500);
        mCountDownTimer.start();
    }

    private void onTimerTick(long millisUntilFinished) {
        sendTimerBroadcast(false, millisUntilFinished);
    }

    private void onTimerFinish() {
        sendTimerBroadcast(true, 0);
        stopSelf();
    }

    private void sendTimerBroadcast(boolean isFinished, long millisUntilFinished) {
        Intent intent = new Intent(TIMER_BROADCAST);
        intent.putExtra(TIMER_BROADCAST_EXTRA_IS_FINISHED, isFinished);
        intent.putExtra(TIMER_BROADCAST_EXTRA_TIME_REMAINING, millisUntilFinished);
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
