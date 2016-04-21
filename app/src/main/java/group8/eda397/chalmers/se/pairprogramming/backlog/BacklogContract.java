package group8.eda397.chalmers.se.pairprogramming.backlog;

import android.support.design.widget.FloatingActionButton;

import java.util.List;

import group8.eda397.chalmers.se.pairprogramming.BasePresenter;
import group8.eda397.chalmers.se.pairprogramming.BaseView;
import group8.eda397.chalmers.se.pairprogramming.backlog.model.BacklogItem;

/**
 * Created by m_cal on 2016-04-14.
 */
public interface BacklogContract {

    interface View extends BaseView<Presenter> {

        void showBacklog(List<BacklogItem> items);

        void showAddBacklog();

        FloatingActionButton getFab();
        void showAddBacklogItemView();
    }

    interface Presenter extends BasePresenter {

        void loadBacklog();

    }
}
