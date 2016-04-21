package group8.eda397.chalmers.se.pairprogramming.backlog;

import android.support.design.widget.FloatingActionButton;

import java.util.List;

import group8.eda397.chalmers.se.pairprogramming.BasePresenter;
import group8.eda397.chalmers.se.pairprogramming.BaseView;
import group8.eda397.chalmers.se.pairprogramming.backlog.model.BacklogItem;

/**
 * Created by m_cal on 2016-04-21.
 */
public interface BacklogSwipeContract {

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {

    }
}
