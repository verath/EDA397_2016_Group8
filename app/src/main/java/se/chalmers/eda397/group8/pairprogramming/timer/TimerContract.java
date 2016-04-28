package se.chalmers.eda397.group8.pairprogramming.timer;

import se.chalmers.eda397.group8.pairprogramming.BasePresenter;
import se.chalmers.eda397.group8.pairprogramming.BaseView;

/**
 * Created by Vidar on 14/04/16.
 */
public interface TimerContract {

    interface View extends BaseView<Presenter> {

        void displayRemainingTime(long millisUntilFinished);

        void displayFinished();

        void disableTimerInput();

        void enableTimerInput();

        void startTimer(long millisInFuture);

        void stopTimer();

        void showStartButton();

        void showStopButton();
    }

    interface Presenter extends BasePresenter {

        void onTimerServiceConnected(int timerState, long millisUntilFinished);

        void onTimerTick(long millisUntilFinished);

        void onTimerFinish();

        void onStartStopButtonClick(long millisInFuture);
    }
}
