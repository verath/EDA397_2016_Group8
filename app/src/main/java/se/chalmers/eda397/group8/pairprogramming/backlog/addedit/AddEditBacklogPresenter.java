package se.chalmers.eda397.group8.pairprogramming.backlog.addedit;

import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogItem;
import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogItemDataSource;

public class AddEditBacklogPresenter implements AddEditBacklogContract.Presenter {

    private final AddEditBacklogContract.View mBacklogView;
    private final BacklogItemDataSource mDataSource;
    private final String mItemId;

    public AddEditBacklogPresenter(AddEditBacklogContract.View backlogView, String itemId, BacklogItemDataSource dataSource) {
        this.mBacklogView = backlogView;
        this.mDataSource = dataSource;
        this.mItemId = itemId;
        mBacklogView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void onAddBacklogItem(BacklogItem item) {
        mDataSource.save(item);

    }
}
