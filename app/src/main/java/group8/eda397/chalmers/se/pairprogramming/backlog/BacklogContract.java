package group8.eda397.chalmers.se.pairprogramming.backlog;

import java.util.List;

import group8.eda397.chalmers.se.pairprogramming.BasePresenter;
import group8.eda397.chalmers.se.pairprogramming.BaseView;
import group8.eda397.chalmers.se.pairprogramming.backlog.model.BacklogItem;

public interface BacklogContract {

    interface View extends BaseView<Presenter> {

        void showBacklogForStatus(BacklogItem.Status status, List<BacklogItem> items);

        void showAddBacklogItemView();

        void showBacklogItemDetails(String backlogItemId);
    }

    interface Presenter extends BasePresenter {

        void onAddClicked();

        void onSwipeFragmentResume(BacklogItem.Status status);

        void onBacklogItemClicked(BacklogItem backlogItem);
    }
}
