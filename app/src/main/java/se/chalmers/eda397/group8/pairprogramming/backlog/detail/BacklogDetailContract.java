package se.chalmers.eda397.group8.pairprogramming.backlog.detail;

import se.chalmers.eda397.group8.pairprogramming.BasePresenter;
import se.chalmers.eda397.group8.pairprogramming.BaseView;
import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogStatus;

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

        void showPdfPage(String page);

        void showTitle(String title);

        void showContent(String content);

        void showStatus(BacklogStatus status);

        void showPage(String page);

        void showMissingItemView();
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

        void onGoToPdfClicked();
    }
}
