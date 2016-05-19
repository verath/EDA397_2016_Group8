package se.chalmers.eda397.group8.pairprogramming.backlog.addedit;

import android.support.annotation.NonNull;

import java.util.List;

import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogItem;
import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogItemDataSource;
import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogStatus;
import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogStatusDataSource;
import se.chalmers.eda397.group8.pairprogramming.reqspec.Requirement;
import se.chalmers.eda397.group8.pairprogramming.reqspec.RequirementDataSource;
import se.chalmers.eda397.group8.pairprogramming.reqspec.RequirementSpecification;
import se.chalmers.eda397.group8.pairprogramming.reqspec.RequirementSpecificationDataSource;

public class AddEditBacklogPresenter implements AddEditBacklogContract.Presenter {

    private final AddEditBacklogContract.View mBacklogView;
    private final BacklogItemDataSource mItemDataSource;
    private final BacklogStatusDataSource mStatusDataSource;
    private final RequirementDataSource mRequirementDataSource;
    private RequirementSpecificationDataSource mReqSpecDataSource;
    private final String mItemId;
    private final String mDefaultStatusId;

    public AddEditBacklogPresenter(AddEditBacklogContract.View backlogView, String itemId,
                                   String defaultStatusId, BacklogItemDataSource itemDataSource,
                                   BacklogStatusDataSource statusDataSource,
                                   RequirementDataSource requirementDataSource,
                                   RequirementSpecificationDataSource reqSpecDataSource) {
        this.mBacklogView = backlogView;
        this.mItemDataSource = itemDataSource;
        this.mStatusDataSource = statusDataSource;
        this.mRequirementDataSource = requirementDataSource;
        this.mReqSpecDataSource = reqSpecDataSource;
        this.mItemId = itemId;
        this.mDefaultStatusId = defaultStatusId;
        mBacklogView.setPresenter(this);
    }

    @Override
    public void start() {
        List<RequirementSpecification> reqSpecs = mReqSpecDataSource.getAll();
        mBacklogView.showReqSpecs(reqSpecs);

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

        BacklogStatus status = mStatusDataSource.get(item.getStatusId());
        if (status != null) {
            mBacklogView.showSelectedStatus(status);
        }

        Requirement requirement = mRequirementDataSource.get(item.getRequirementId());
        if (requirement != null) {
            RequirementSpecification reqSpec = mReqSpecDataSource.get(requirement.getReqSpecId());
            mBacklogView.showPage(requirement.getPageNumber());
            mBacklogView.showSelectedRequirement(reqSpec);
        }
    }

    @Override
    public void onSaveItem(@NonNull String title, @NonNull String content, @NonNull String statusId,
                           @NonNull String reqSpecId, @NonNull String pageNumber) {
        if (title.isEmpty()) {
            mBacklogView.showTitleEmptyError();
            return;
        }

        Requirement requirement = new Requirement(reqSpecId, pageNumber);
        mRequirementDataSource.save(requirement);

        BacklogItem item;
        if (mItemId != null) {
            item = new BacklogItem(mItemId, title, content, statusId, requirement.getId());
        } else {
            item = new BacklogItem(title, content, statusId, requirement.getId());
        }
        mItemDataSource.save(item);
        mBacklogView.showBacklog();
    }
}
