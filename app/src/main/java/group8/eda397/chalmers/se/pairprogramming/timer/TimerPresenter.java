package group8.eda397.chalmers.se.pairprogramming.timer;

import android.os.CountDownTimer;

import group8.eda397.chalmers.se.pairprogramming.timer.TimerContract.Presenter;

/**
 * Created by Vidar on 14/04/16.
 */
public class TimerPresenter implements Presenter {


    private final TimerContract.View mTimerView;
    private CountDownTimer countDownTimer;

    public TimerPresenter(TimerContract.View timerView) {
        mTimerView = timerView;
        mTimerView.setPresenter(this);
    }

    @Override
    public void startTimer(long startTime) {
        countDownTimer = new CountDownTimer(startTime * 60000, 1000) {

            public void onTick(long millisUntilFinished) {
                mTimerView.displayRemainingTime(millisUntilFinished);
            }

            public void onFinish() {
                mTimerView.displayFinished();
            }
        }.start();

    }

    @Override
    public void stopTimer() {
        countDownTimer.cancel();
    }

    @Override
    public void start() {

    }
}
