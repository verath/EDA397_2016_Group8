package se.chalmers.eda397.group8.pairprogramming.backlog.details;

import se.chalmers.eda397.group8.pairprogramming.BasePresenter;
import se.chalmers.eda397.group8.pairprogramming.BaseView;
import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogItem;

public interface BacklogDetailContract {

    interface View extends BaseView<Presenter> {

        /**
         * Shows the edit view for the backlog item with the specified ID.
         *
         * @param backlogItemId the ID of the backlog item
         */
        void showEditView(String backlogItemId);

        /**
         * Navigates back to the backlog.
         */
        void showBacklog();

        /**
         * Shows the specified item
         *
         * @param backlogItem the backlog item to be displayed
         */
        void showBacklogItem(BacklogItem backlogItem);
    }

    interface Presenter extends BasePresenter {

        /**
         * When delete is clicked.
         */
        void onDeleteItemClicked();

        /**
         * When edit is clicked.
         */
        void onEditItemClicked();
    }
}
