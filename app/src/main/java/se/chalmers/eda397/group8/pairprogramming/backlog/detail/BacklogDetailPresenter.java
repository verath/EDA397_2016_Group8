package se.chalmers.eda397.group8.pairprogramming.backlog.detail;

import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogItem;
import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogItemDataSource;
import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogStatus;
import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogStatusDataSource;


public class BacklogDetailPresenter implements BacklogDetailContract.Presenter {

    private final BacklogDetailContract.View mDetailView;
    private final BacklogItemDataSource mItemDataSource;
    private final BacklogStatusDataSource mStatusDataSource;
    private final String mBacklogItemId;
    private BacklogItem mBacklogItem;

    public BacklogDetailPresenter(BacklogDetailContract.View mDetailView, String backlogItemId,
                                  BacklogItemDataSource itemDataSource,
                                  BacklogStatusDataSource statusDataSource) {
        this.mDetailView = mDetailView;
        this.mItemDataSource = itemDataSource;
        this.mStatusDataSource = statusDataSource;
        this.mBacklogItemId = backlogItemId;
        mDetailView.setPresenter(this);
    }

    @Override
    public void start() {
        mBacklogItem = mItemDataSource.get(mBacklogItemId);
        populateFields();
    }

    private void populateFields() {
        if (mBacklogItem != null) {
            mDetailView.showTitle(mBacklogItem.getTitle());
            mDetailView.showContent(mBacklogItem.getContent());
            mDetailView.showPage(mBacklogItem.getPage());
            mDetailView.showPDFName(mBacklogItem.getmPDFName());
            BacklogStatus status = mStatusDataSource.get(mBacklogItem.getStatusId());
            if (status != null) {
                mDetailView.showStatus(status);
            }
        } else {
            mDetailView.showMissingItemView();
        }
    }

    @Override
    public void onDeleteItemClicked() {
        mItemDataSource.delete(mBacklogItemId);
        mDetailView.showBacklog();
    }

    @Override
    public void onEditItemClicked() {
        mDetailView.showEditView(mBacklogItemId);
    }

    @Override
    public void onGoToPdfClicked() {
        mDetailView.showPdfPage(mBacklogItem.getmPDFName(), mBacklogItem.getPage());
    }


}
