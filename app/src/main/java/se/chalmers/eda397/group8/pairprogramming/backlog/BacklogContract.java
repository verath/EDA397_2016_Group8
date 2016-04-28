package se.chalmers.eda397.group8.pairprogramming.backlog;

import java.util.List;

import se.chalmers.eda397.group8.pairprogramming.BasePresenter;
import se.chalmers.eda397.group8.pairprogramming.BaseView;
import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogItem;

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
