package group8.eda397.chalmers.se.pairprogramming.backlog.add;

import group8.eda397.chalmers.se.pairprogramming.BasePresenter;
import group8.eda397.chalmers.se.pairprogramming.BaseView;
import group8.eda397.chalmers.se.pairprogramming.backlog.model.BacklogItem;

public interface AddBacklogContract {

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {

        void onAddBacklogItem(BacklogItem item);
    }
}
