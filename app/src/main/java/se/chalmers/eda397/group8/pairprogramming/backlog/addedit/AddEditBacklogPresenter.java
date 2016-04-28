package se.chalmers.eda397.group8.pairprogramming.backlog.addedit;

import se.chalmers.eda397.group8.pairprogramming.R;
import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogItem;
import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogItemDataSource;

public class AddEditBacklogPresenter implements AddEditBacklogContract.Presenter {

    private final AddEditBacklogContract.View mBacklogView;
    private final BacklogItemDataSource mDataSource;
    private final String mItemId;
    private final BacklogItem.Status mDefaultStatus;

    public AddEditBacklogPresenter(AddEditBacklogContract.View backlogView, String itemId,
                                   BacklogItem.Status defaultStatus, BacklogItemDataSource dataSource) {
        this.mBacklogView = backlogView;
        this.mDataSource = dataSource;
        this.mItemId = itemId;
        this.mDefaultStatus = defaultStatus;
        mBacklogView.setPresenter(this);
    }

    @Override
    public void start() {
        if (mItemId != null) {
            populateFields();
        } else if (mDefaultStatus != null) {
            mBacklogView.showStatus(mDefaultStatus);
        }
    }

    private void populateFields() {
        BacklogItem item = mDataSource.get(mItemId);
        mBacklogView.showTitle(item.getTitle());
        mBacklogView.showContent(item.getContent());
        mBacklogView.showStatus(item.getStatus());
    }

    @Override
    public void onSaveItem(String title, String content, BacklogItem.Status status) {
        if (title == null || title.length() == 0) {
            mBacklogView.showTitleEmptyError();
            return;
        }
        if (content == null || content.length() == 0) {
            mBacklogView.showContentEmptyError();
            return;
        }

        BacklogItem item;
        if (mItemId != null) {
            item = new BacklogItem(mItemId, title, content, status);
        } else {
            item = new BacklogItem(title, content, status);
        }
        mDataSource.save(item);
        mBacklogView.showBacklog();
    }
}
