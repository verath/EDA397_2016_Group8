package se.chalmers.eda397.group8.pairprogramming.backlog;

import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogItem;
import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogItemDataSource;

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
