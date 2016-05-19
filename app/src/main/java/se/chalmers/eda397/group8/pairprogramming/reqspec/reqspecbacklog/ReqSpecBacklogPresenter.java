package se.chalmers.eda397.group8.pairprogramming.reqspec.reqspecbacklog;


import se.chalmers.eda397.group8.pairprogramming.reqspec.data.Requirement;
import se.chalmers.eda397.group8.pairprogramming.reqspec.data.RequirementDataSource;
import se.chalmers.eda397.group8.pairprogramming.reqspec.data.RequirementSpecification;
import se.chalmers.eda397.group8.pairprogramming.reqspec.data.RequirementSpecificationDataSource;

public class ReqSpecBacklogPresenter implements ReqSpecBacklogContract.Presenter {

    private final ReqSpecBacklogContract.View mView;
    private final RequirementDataSource mRequirementDataSource;
    private final RequirementSpecificationDataSource mReqSpecDataSource;
    private final String mRequirementId;

    public ReqSpecBacklogPresenter(ReqSpecBacklogContract.View view,
                                   RequirementDataSource requirementDataSource,
                                   RequirementSpecificationDataSource reqSpecDataSource,
                                   String requirementId) {
        mView = view;
        mRequirementDataSource = requirementDataSource;
        mReqSpecDataSource = reqSpecDataSource;
        mRequirementId = requirementId;
        view.setPresenter(this);
    }

    @Override
    public void start() {
        Requirement requirement = mRequirementDataSource.get(mRequirementId);
        String reqSpecId = requirement.getReqSpecId();
        RequirementSpecification reqSpec = mReqSpecDataSource.get(reqSpecId);

        mView.showPDF(reqSpec.getFilePath(), requirement.getPageNumber());
    }

}
