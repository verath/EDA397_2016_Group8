package se.chalmers.eda397.group8.pairprogramming.backlog.addedit;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogItem;
import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogItemDataSource;
import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogStatus;
import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogStatusDataSource;
import se.chalmers.eda397.group8.pairprogramming.reqspec.RequirementSpecification;

public class AddEditBacklogPresenter implements AddEditBacklogContract.Presenter {

    private final AddEditBacklogContract.View mBacklogView;
    private final BacklogItemDataSource mItemDataSource;
    private final BacklogStatusDataSource mStatusDataSource;
    private final String mItemId;
    private final String mDefaultStatusId;

    public AddEditBacklogPresenter(AddEditBacklogContract.View backlogView, String itemId,
                                   String defaultStatusId, BacklogItemDataSource itemDataSource,
                                   BacklogStatusDataSource statusDataSource) {
        this.mBacklogView = backlogView;
        this.mItemDataSource = itemDataSource;
        this.mStatusDataSource = statusDataSource;
        this.mItemId = itemId;
        this.mDefaultStatusId = defaultStatusId;
        mBacklogView.setPresenter(this);
    }

    @Override
    public void start() {
        List<RequirementSpecification> requirements = new ArrayList<>();
        String[] fileNames = mBacklogView.getFileNames();
        for (String fileName : fileNames) {
            if (fileName.endsWith(".pdf")) {
                requirements.add(new RequirementSpecification(fileName));
            }
        }
        mBacklogView.showRequirements(requirements);
        List<BacklogStatus> statuses = mStatusDataSource.getAll();
        mBacklogView.showStatuses(statuses);
        if (mItemId != null) {
            BacklogItem item = mItemDataSource.get(mItemId);
            if (item == null) {
                mBacklogView.showMissingBacklogItem();
            } else {
                populateFields(item);
            }
        } else if (mDefaultStatusId != null) {
            BacklogStatus status = mStatusDataSource.get(mDefaultStatusId);
            if (status != null) {
                mBacklogView.showSelectedStatus(status);
            }
        }
    }

    private void populateFields(BacklogItem item) {
        mBacklogView.showTitle(item.getTitle());
        mBacklogView.showContent(item.getContent());
        mBacklogView.showPage(item.getPage());


        BacklogStatus status = mStatusDataSource.get(item.getStatusId());
        if (status != null) {
            mBacklogView.showSelectedStatus(status);
        }
    }

    @Override
    public void onSaveItem(@NonNull String title, @NonNull String content,
                           @NonNull String statusId, @NonNull String page, @NonNull String PDFName) {
        if (title.isEmpty()) {
            mBacklogView.showTitleEmptyError();
            return;
        }
        BacklogItem item;
        if (mItemId != null) {
            item = new BacklogItem(mItemId, title, content, statusId, page, PDFName);
        } else {
            item = new BacklogItem(title, content, statusId, page, PDFName);
        }
        mItemDataSource.save(item);
        mBacklogView.showBacklog();
    }
}
