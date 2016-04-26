package group8.eda397.chalmers.se.pairprogramming.backlog.details;

import android.support.design.widget.FloatingActionButton;

import group8.eda397.chalmers.se.pairprogramming.BasePresenter;
import group8.eda397.chalmers.se.pairprogramming.BaseView;

public interface BacklogDetailContract {

    interface View extends BaseView<Presenter> {

        void showEditView(String backlogItemId);

        void showBacklog();
    }

    interface Presenter extends BasePresenter {

        void onDeleteItemClicked();

        void onEditItemClicked();

        void onEditItemResult(int result);
    }
}
