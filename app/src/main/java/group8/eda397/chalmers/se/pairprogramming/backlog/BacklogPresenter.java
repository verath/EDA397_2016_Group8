package group8.eda397.chalmers.se.pairprogramming.backlog;

import group8.eda397.chalmers.se.pairprogramming.backlog.model.BacklogItem;
import group8.eda397.chalmers.se.pairprogramming.backlog.model.BacklogItemDataSource;

/**
 * Created by m_cal on 2016-04-14.
 */
public class BacklogPresenter implements BacklogContract.Presenter {

    private final BacklogContract.View mBacklogView;
    private final BacklogItemDataSource mDataSource;

    public BacklogPresenter(BacklogContract.View backlogView, BacklogItemDataSource datasource) {
        this.mBacklogView = backlogView;
        mBacklogView.setPresenter(this);
        mDataSource = datasource;
    }

    @Override
    public void start() {

    }

    @Override
    public void onAddClicked() {
        mBacklogView.showAddBacklogItemView();
    }

    @Override
    public void onSwipeFragmentResume(BacklogItem.Status status) {
        mBacklogView.showBacklogForStatus(status, mDataSource.getAll(status));
    }

    @Override
    public void onBacklogItemClicked(BacklogItem backlogItem) {
        mBacklogView.showBacklogItemDetails(backlogItem.getId());
    }

}
