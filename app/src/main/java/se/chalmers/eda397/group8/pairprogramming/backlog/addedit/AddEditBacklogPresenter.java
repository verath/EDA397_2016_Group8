package se.chalmers.eda397.group8.pairprogramming.backlog.addedit;

import android.support.annotation.NonNull;

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
            BacklogItem item = mDataSource.get(mItemId);
            if (item == null) {
                mBacklogView.showMissingBacklogItem();
            } else {
                populateFields(item);
            }
        } else if (mDefaultStatus != null) {
            mBacklogView.showStatus(mDefaultStatus);
        }
    }

    private void populateFields(BacklogItem item) {
        mBacklogView.showTitle(item.getTitle());
        mBacklogView.showContent(item.getContent());
        mBacklogView.showStatus(item.getStatus());
        mBacklogView.showPage(item.getPage());
    }

    @Override
    public void onSaveItem(@NonNull String title, @NonNull String content,
                           @NonNull BacklogItem.Status status, @NonNull String page) {
        if (title.isEmpty()) {
            mBacklogView.showTitleEmptyError();
            return;
        }

        BacklogItem item;
        if (mItemId != null) {
            item = new BacklogItem(mItemId, title, content, status, page);
        } else {
            item = new BacklogItem(title, content, status, page);
        }
        if (page.isEmpty()) {
            item = new BacklogItem(title, content, status, "1");

        }
        mDataSource.save(item);
        mBacklogView.showBacklog();
    }
}
