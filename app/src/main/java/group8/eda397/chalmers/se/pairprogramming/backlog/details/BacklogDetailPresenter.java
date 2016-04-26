package group8.eda397.chalmers.se.pairprogramming.backlog.details;

import group8.eda397.chalmers.se.pairprogramming.backlog.BacklogContract;
import group8.eda397.chalmers.se.pairprogramming.backlog.add.AddBacklogContract;
import group8.eda397.chalmers.se.pairprogramming.backlog.model.BacklogItem;
import group8.eda397.chalmers.se.pairprogramming.backlog.model.BacklogItemDataSource;

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

    }

    @Override
    public void onDeleteItemClicked() {

    }

    @Override
    public void onEditItemClicked() {

    }
}
