package se.chalmers.eda397.group8.pairprogramming.backlog;

import java.util.List;

import se.chalmers.eda397.group8.pairprogramming.BasePresenter;
import se.chalmers.eda397.group8.pairprogramming.BaseView;
import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogStatus;

public interface BacklogContract {

    interface View extends BaseView<Presenter> {

        void showBacklogStatuses(List<BacklogStatus> backlogStatuses);

        void showAddBacklogItemView(String backlogStatusId);
    }

    interface Presenter extends BasePresenter {
        void onAddClicked(BacklogStatus selectedStatus);
    }
}
