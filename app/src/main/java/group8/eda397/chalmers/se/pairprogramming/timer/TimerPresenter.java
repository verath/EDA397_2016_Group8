package group8.eda397.chalmers.se.pairprogramming.timer;

import android.os.CountDownTimer;

import group8.eda397.chalmers.se.pairprogramming.timer.TimerContract.Presenter;

/**
 * Created by Vidar on 14/04/16.
 */
public class TimerPresenter implements Presenter {

    private final TimerContract.View mTimerView;

    public TimerPresenter(TimerContract.View timerView) {
        mTimerView = timerView;
        mTimerView.setPresenter(this);
    }

    @Override
    public void start() {
    }

    @Override
    public void startTimer(long startTime) {

    }

    @Override
    public void stopTimer() {

    }

    @Override
    public void onTimerUpdate(boolean isFinished, long millisUntilFinished) {
        if(isFinished) {
            mTimerView.displayFinished();
        } else {
            mTimerView.displayRemainingTime(millisUntilFinished);
        }
    }
}
