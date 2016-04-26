package se.chalmers.eda397.group8.pairprogramming.backlog.details;

import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogItemDataSource;

public class BacklogDetailPresenter implements BacklogDetailContract.Presenter {

    private final BacklogDetailContract.View mDetailView;
    private final BacklogItemDataSource mDataSource;
    private final String mBacklogItemId;

    public BacklogDetailPresenter(BacklogDetailContract.View mDetailView, String backlogItemId,
                                  BacklogItemDataSource dataSource) {
        this.mDetailView = mDetailView;
        this.mDataSource = dataSource;
        this.mBacklogItemId = backlogItemId;
        mDetailView.setPresenter(this);
    }

    @Override
    public void start() {
        mDetailView.showBacklogItem(mDataSource.get(mBacklogItemId));


    }

    @Override
    public void onDeleteItemClicked() {
        mDataSource.delete(mBacklogItemId);
        mDetailView.showBacklog();
    }

    @Override
    public void onEditItemClicked() {

    }
}
