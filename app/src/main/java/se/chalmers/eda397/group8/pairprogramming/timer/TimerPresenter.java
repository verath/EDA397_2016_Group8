package se.chalmers.eda397.group8.pairprogramming.timer;

import se.chalmers.eda397.group8.pairprogramming.timer.TimerContract.Presenter;

/**
 * Created by Vidar on 14/04/16.
 */
public class TimerPresenter implements Presenter {

    private static final int STATE_TIMER_NOT_STARTED = 0x1;
    private static final int STATE_TIMER_STARTED = 0x2;
    private static final int STATE_TIMER_FINISHED = 0x3;

    private final TimerContract.View mTimerView;
    private int mState;

    public TimerPresenter(TimerContract.View timerView) {
        mTimerView = timerView;
        mTimerView.setPresenter(this);
    }

    @Override
    public void start() {
        mState = STATE_TIMER_NOT_STARTED;
        // Disable until TimerService is connected
        mTimerView.disableTimerInput();
    }

    @Override
    public void onTimerServiceConnected(int timerState, long millisUntilFinished) {
        // Sync state with the TimerService.
        switch (timerState) {
            case TimerService.STATE_TIMER_STARTED:
                mState = STATE_TIMER_STARTED;
                showTimerStarted(millisUntilFinished);
                break;
            case TimerService.STATE_TIMER_FINISHED:
                mState = STATE_TIMER_FINISHED;
                showTimerFinished();
                break;
            default:
                mState = STATE_TIMER_NOT_STARTED;
        }

        mTimerView.enableTimerInput();
    }

    @Override
    public void onTimerTick(long millisUntilFinished) {
        mTimerView.displayRemainingTime(millisUntilFinished);
    }

    @Override
    public void onTimerFinish() {
        mState = STATE_TIMER_FINISHED;
        showTimerFinished();
    }

    @Override
    public void onStartStopButtonClick(long millisInFuture) {
        if (mState != STATE_TIMER_STARTED) {
            startTimer(millisInFuture);
        } else {
            stopTimer();
        }
    }

    private void startTimer(long millisInFuture) {
        mState = STATE_TIMER_STARTED;
        mTimerView.startTimer(millisInFuture);
        showTimerStarted(millisInFuture);
    }

    private void stopTimer() {
        mState = STATE_TIMER_NOT_STARTED;
        mTimerView.stopTimer();
        mTimerView.showStartButton();
    }

    private void showTimerStarted(long millisInFuture) {
        mTimerView.showStopButton();
        mTimerView.displayRemainingTime(millisInFuture);
    }

    private void showTimerFinished() {
        mTimerView.displayFinished();
        mTimerView.showStartButton();
    }
}
