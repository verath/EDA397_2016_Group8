package group8.eda397.chalmers.se.pairprogramming.backlog.details;

import android.support.design.widget.FloatingActionButton;

import group8.eda397.chalmers.se.pairprogramming.BasePresenter;
import group8.eda397.chalmers.se.pairprogramming.BaseView;

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
    }

    interface Presenter extends BasePresenter {

        /**
         * When delete is clicked.
         */
        void onDeleteItemClicked();

        /**
         * When edit is clidked.
         */
        void onEditItemClicked();
    }
}
