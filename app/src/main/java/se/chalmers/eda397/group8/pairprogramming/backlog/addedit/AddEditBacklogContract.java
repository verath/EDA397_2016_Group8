package se.chalmers.eda397.group8.pairprogramming.backlog.addedit;

import se.chalmers.eda397.group8.pairprogramming.BasePresenter;
import se.chalmers.eda397.group8.pairprogramming.BaseView;
import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogItem;

public interface AddEditBacklogContract {

    interface View extends BaseView<Presenter> {

        void showBacklog();

        void showTitleEmptyError();

        void showContentEmptyError();

        void showTitle(String title);

        void showContent(String content);

        void showStatus(BacklogItem.Status status);
    }

    interface Presenter extends BasePresenter {

        void onSaveItem(String title, String content, BacklogItem.Status status);
    }
}
