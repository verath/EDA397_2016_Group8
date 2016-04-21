package group8.eda397.chalmers.se.pairprogramming.timer;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * A service for running a CountDownTimer that may live for longer than the
 * TimerActivity. It is started with a countdown, and broadcasts progress
 * via the LocalBroadcastManager.
 * <p/>
 * Note: To change the time being counted down to, start the Service again
 * with the new time.
 */
public class TimerService extends Service {

    public interface Listener {
        void onTimerTick(long millisUntilFinished);

        void onTimerFinish();
    }

    public static Intent getStartingIntent(Context context) {
        return new Intent(context, TimerService.class);
    }

    private final IBinder mBinder = new TimerServiceBinder();
    private CountDownTimer mCountDownTimer;
    private Listener mListener;
    private long mMillisUntilFinished = 0;
    private boolean mFinished = true;

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public void setListener(@Nullable Listener listener) {
        mListener = listener;
    }

    public void startTimer(long millisInFuture) {
        if (millisInFuture <= 0) {
            throw new IllegalArgumentException("millisInFuture must be > 0");
        }
        cancelTimer();

        mFinished = false;
        mMillisUntilFinished = millisInFuture;

        mCountDownTimer = new CountDownTimerImpl(millisInFuture, 500);
        mCountDownTimer.start();
    }

    public void cancelTimer() {
        mFinished = true;
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
        return mFinished;
    }

    public class TimerServiceBinder extends Binder {
        TimerService getTimerService() {
            return TimerService.this;
        }
    }

    private class CountDownTimerImpl extends CountDownTimer {
        public CountDownTimerImpl(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            mMillisUntilFinished = millisUntilFinished;
            if (mListener != null) {
                mListener.onTimerTick(millisUntilFinished);
            }
        }

        @Override
        public void onFinish() {
            mFinished = true;
            if (mListener != null) {
                mListener.onTimerFinish();
            }
        }
    }
}
