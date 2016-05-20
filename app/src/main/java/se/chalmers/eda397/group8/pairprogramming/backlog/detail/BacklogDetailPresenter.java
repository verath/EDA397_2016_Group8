package se.chalmers.eda397.group8.pairprogramming.backlog.detail;

import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogItem;
import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogItemDataSource;
import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogStatus;
import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogStatusDataSource;
import se.chalmers.eda397.group8.pairprogramming.reqspec.data.Requirement;
import se.chalmers.eda397.group8.pairprogramming.reqspec.data.RequirementDataSource;
import se.chalmers.eda397.group8.pairprogramming.reqspec.data.RequirementSpecification;
import se.chalmers.eda397.group8.pairprogramming.reqspec.data.RequirementSpecificationDataSource;


public class BacklogDetailPresenter implements BacklogDetailContract.Presenter {

    private final BacklogDetailContract.View mDetailView;
    private final BacklogItemDataSource mItemDataSource;
    private final BacklogStatusDataSource mStatusDataSource;
    private final RequirementDataSource mRequirementDataSource;
    private final RequirementSpecificationDataSource mReqSpecDataSource;
    private final String mBacklogItemId;
    private BacklogItem mBacklogItem;

    public BacklogDetailPresenter(BacklogDetailContract.View mDetailView, String backlogItemId,
                                  BacklogItemDataSource itemDataSource,
                                  BacklogStatusDataSource statusDataSource,
                                  RequirementDataSource requirementDataSource,
                                  RequirementSpecificationDataSource reqSpecDataSource) {
        this.mDetailView = mDetailView;
        this.mItemDataSource = itemDataSource;
        this.mStatusDataSource = statusDataSource;
        this.mRequirementDataSource = requirementDataSource;
        this.mReqSpecDataSource = reqSpecDataSource;
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

            BacklogStatus status = mStatusDataSource.get(mBacklogItem.getStatusId());
            if (status != null) {
                mDetailView.showStatus(status);
            }

            Requirement requirement = mRequirementDataSource.get(mBacklogItem.getRequirementId());
            if (requirement != null) {
                RequirementSpecification reqSpec = mReqSpecDataSource.get(requirement.getReqSpecId());
                mDetailView.showPage(requirement.getPageNumber());
                mDetailView.showPdfName(reqSpec.getFilePath());
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
        mDetailView.showRequirement(mBacklogItem.getRequirementId());
    }


}
