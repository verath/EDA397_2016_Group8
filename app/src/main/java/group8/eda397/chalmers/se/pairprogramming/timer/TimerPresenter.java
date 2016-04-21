package group8.eda397.chalmers.se.pairprogramming.timer;

import group8.eda397.chalmers.se.pairprogramming.timer.TimerContract.Presenter;

/**
 * Created by Vidar on 14/04/16.
 */
public class TimerPresenter implements Presenter {

    private final TimerContract.View mTimerView;
    private boolean mFinished = true;

    public TimerPresenter(TimerContract.View timerView) {
        mTimerView = timerView;
        mTimerView.setPresenter(this);
    }

    @Override
    public void start() {
        // Disable until TimerService is connected
        mTimerView.disableTimerInput();
    }

    @Override
    public void onTimerServiceConnected(boolean finished, long millisUntilFinished) {
        mFinished = finished;
        if (!finished) {
            mTimerView.displayRemainingTime(millisUntilFinished);
            mTimerView.showStopButton();
        } else {
            mTimerView.showStartButton();
        }
        mTimerView.enableTimerInput();
    }

    @Override
    public void onTimerTick(long millisUntilFinished) {
        mTimerView.displayRemainingTime(millisUntilFinished);
    }

    @Override
    public void onTimerFinish() {
        mFinished = true;
        mTimerView.displayFinished();
        mTimerView.showStartButton();
    }

    @Override
    public void onStartStopButtonClick(long millisInFuture) {
        if (mFinished) {
            startTimer(millisInFuture);
        } else {
            stopTimer();
        }
    }

    private void startTimer(long millisInFuture) {
        mFinished = false;
        mTimerView.startTimer(millisInFuture);
        mTimerView.showStopButton();
    }

    private void stopTimer() {
        mFinished = true;
        mTimerView.stopTimer();
        mTimerView.showStartButton();
    }
}
