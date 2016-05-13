package se.chalmers.eda397.group8.pairprogramming.backlog;

import java.util.List;

import se.chalmers.eda397.group8.pairprogramming.BasePresenter;
import se.chalmers.eda397.group8.pairprogramming.BaseView;
import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogItem;

public interface BacklogStatusContract {
    interface View extends BaseView<Presenter> {
        void showItems(List<BacklogItem> items);

        void showBacklogItemDetails(String backlogItemId);
    }

    interface Presenter extends BasePresenter {
        void onBacklogItemClicked(BacklogItem backlogItem);
    }
}
