package se.chalmers.eda397.group8.pairprogramming.backlog.addedit;

import android.support.annotation.NonNull;

import se.chalmers.eda397.group8.pairprogramming.BasePresenter;
import se.chalmers.eda397.group8.pairprogramming.BaseView;
import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogItem;

public interface AddEditBacklogContract {

    interface View extends BaseView<Presenter> {

        void showBacklog();

        void showTitleEmptyError();

        void showTitle(String title);

        void showContent(String content);

        void showStatus(BacklogItem.Status status);

        void showMissingBacklogItem();
    }

    interface Presenter extends BasePresenter {

        void onSaveItem(@NonNull String title, @NonNull String content,
                        @NonNull BacklogItem.Status status);
    }
}