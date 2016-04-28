package se.chalmers.eda397.group8.pairprogramming.backlog.add;

import se.chalmers.eda397.group8.pairprogramming.BasePresenter;
import se.chalmers.eda397.group8.pairprogramming.BaseView;
import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogItem;

public interface AddBacklogContract {

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {

        void onAddBacklogItem(BacklogItem item);
    }
}
