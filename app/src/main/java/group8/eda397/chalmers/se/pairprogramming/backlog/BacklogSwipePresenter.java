package group8.eda397.chalmers.se.pairprogramming.backlog;

import java.util.ArrayList;
import java.util.List;

import group8.eda397.chalmers.se.pairprogramming.backlog.model.BacklogItem;

/**
 * Created by m_cal on 2016-04-21.
 */
public class BacklogSwipePresenter implements BacklogSwipeContract.Presenter {

    private final BacklogSwipeContract.View mBacklogView;
    private final BacklogItem.Status mStatus;
    private final List<BacklogItem> mBacklog = new ArrayList<BacklogItem>();

    public BacklogSwipePresenter(BacklogSwipeContract.View backlogView, BacklogItem.Status status) {
        this.mBacklogView = backlogView;
        mBacklogView.setPresenter(this);
        this.mStatus = status;
    }

    @Override
    public void start() {
        mBacklog.clear();
        for (int i = 0; i < 20; i++) {
            mBacklog.add(new BacklogItem("Item " + (i + 1), "Backlog content " + (i + 1),
                    mStatus));
        }
        mBacklogView.showItems(mBacklog);
    }
}
