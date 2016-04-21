package group8.eda397.chalmers.se.pairprogramming.timer;

import group8.eda397.chalmers.se.pairprogramming.BasePresenter;
import group8.eda397.chalmers.se.pairprogramming.BaseView;

/**
 * Created by Vidar on 14/04/16.
 */
public interface TimerContract {

    interface View extends BaseView<Presenter> {

        void showTimer();

        void setTitle(String title);

        void setDescription(String description);

        boolean isActive();

        void displayRemainingTime(long millisUntilFinished);

        void displayFinished();
    }

    interface Presenter extends BasePresenter {

        void startTimer(long startTime);

        void stopTimer();
    }
}
