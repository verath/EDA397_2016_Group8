package group8.eda397.chalmers.se.pairprogramming.backlog.add;

import group8.eda397.chalmers.se.pairprogramming.BasePresenter;
import group8.eda397.chalmers.se.pairprogramming.BaseView;
import group8.eda397.chalmers.se.pairprogramming.backlog.model.BacklogItem;

/**
 * Created by m_cal on 2016-04-14.
 */
public interface AddBacklogContract {

    interface View extends BaseView<Presenter> {

        void goBack();
    }

    interface Presenter extends BasePresenter {

        void addBacklogItem(BacklogItem item);
    }
}
