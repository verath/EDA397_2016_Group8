package se.chalmers.eda397.group8.pairprogramming.backlog;

import java.util.List;

import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogItem;
import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogItemDataSource;


public class BacklogStatusPresenter implements BacklogStatusContract.Presenter {

    private BacklogStatusContract.View mView;
    private final BacklogItemDataSource mItemDataSource;
    private final String mStatusId;

    public BacklogStatusPresenter(BacklogStatusContract.View view, BacklogItemDataSource itemDataSource, String statusId) {
        mView = view;
        mItemDataSource = itemDataSource;
        mStatusId = statusId;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        List<BacklogItem> items = mItemDataSource.getAllByStatus(mStatusId);
        mView.showItems(items);
    }


    @Override
    public void onBacklogItemClicked(BacklogItem backlogItem) {
        mView.showBacklogItemDetails(backlogItem.getId());
    }
}
