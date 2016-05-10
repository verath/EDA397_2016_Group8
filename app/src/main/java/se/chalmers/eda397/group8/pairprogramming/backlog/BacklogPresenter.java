package se.chalmers.eda397.group8.pairprogramming.backlog;

import java.util.List;

import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogItem;
import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogItemDataSource;
import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogStatus;
import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogStatusDataSource;

/**
 * Created by m_cal on 2016-04-14.
 */
public class BacklogPresenter implements BacklogContract.Presenter {

    private final BacklogContract.View mBacklogView;
    private final BacklogItemDataSource mItemDataSource;
    private final BacklogStatusDataSource mStatusDataSource;

    public BacklogPresenter(BacklogContract.View backlogView, BacklogItemDataSource itemDataSource,
                            BacklogStatusDataSource statusDataSource) {
        this.mBacklogView = backlogView;
        mBacklogView.setPresenter(this);
        mItemDataSource = itemDataSource;
        mStatusDataSource = statusDataSource;
    }

    @Override
    public void start() {

    }

    @Override
    public void onAddClicked() {
        mBacklogView.showAddBacklogItemView();
    }

    @Override
    public void onSwipeFragmentResume(String statusId) {
        BacklogStatus status = mStatusDataSource.get(statusId);
        List<BacklogItem> items = mItemDataSource.getAllByStatus(statusId);
        mBacklogView.showBacklogForStatus(status, items);
    }

    @Override
    public void onBacklogItemClicked(BacklogItem backlogItem) {
        mBacklogView.showBacklogItemDetails(backlogItem.getId());
    }

}
