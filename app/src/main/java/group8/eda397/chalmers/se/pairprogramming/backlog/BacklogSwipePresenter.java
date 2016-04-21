package group8.eda397.chalmers.se.pairprogramming.backlog;

import group8.eda397.chalmers.se.pairprogramming.backlog.model.BacklogItem;

/**
 * Created by m_cal on 2016-04-21.
 */
public class BacklogSwipePresenter implements BacklogSwipeContract.Presenter {

    private final BacklogSwipeContract.View mBacklogView;
    private final BacklogItem.Status mStatus;

    public BacklogSwipePresenter(BacklogSwipeContract.View backlogView, BacklogItem.Status status) {
        this.mBacklogView = backlogView;
        mBacklogView.setPresenter(this);
        this.mStatus = status;
    }

    @Override
    public void start() {

    }
}
