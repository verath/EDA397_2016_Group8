package se.chalmers.eda397.group8.pairprogramming.backlog;

import java.util.List;

import se.chalmers.eda397.group8.pairprogramming.BasePresenter;
import se.chalmers.eda397.group8.pairprogramming.BaseView;
import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogItem;
import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogStatus;

public interface BacklogContract {

    interface View extends BaseView<Presenter> {

        void showBacklogForStatus(BacklogStatus status, List<BacklogItem> items);

        void showAddBacklogItemView();

        void showBacklogItemDetails(String backlogItemId);
    }

    interface Presenter extends BasePresenter {

        void onAddClicked();

        void onSwipeFragmentResume(String statusId);

        void onBacklogItemClicked(BacklogItem backlogItem);
    }
}
