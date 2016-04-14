package group8.eda397.chalmers.se.pairprogramming.timer;

import group8.eda397.chalmers.se.pairprogramming.BaseView;
import group8.eda397.chalmers.se.pairprogramming.BasePresenter;

/**
 * Created by Vidar on 14/04/16.
 */
public interface TimerContract {

    interface View extends BaseView<Presenter> {

        void showTimer();

        void setTitle(String title);

        void setDescription(String description);

        boolean isActive();
    }

    interface Presenter extends BasePresenter {

        void startTimer();

        void stopTimer();
    }
}