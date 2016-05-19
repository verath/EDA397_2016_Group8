package se.chalmers.eda397.group8.pairprogramming.reqspec.detail;

import se.chalmers.eda397.group8.pairprogramming.reqspec.RequirementSpecification;
import se.chalmers.eda397.group8.pairprogramming.reqspec.RequirementSpecificationDataSource;

public class ReqSpecDetailPresenter implements ReqSpecDetailContract.Presenter {

    private final ReqSpecDetailContract.View mView;
    private final RequirementSpecificationDataSource mReqSpecDataSource;
    private final String mReqSpecId;

    public ReqSpecDetailPresenter(ReqSpecDetailContract.View view,
                                  RequirementSpecificationDataSource reqSpecDataSource,
                                  String reqSpecId) {
        mView = view;
        mReqSpecDataSource = reqSpecDataSource;
        mReqSpecId = reqSpecId;
        view.setPresenter(this);
    }

    @Override
    public void start() {
        RequirementSpecification reqSpec = mReqSpecDataSource.get(mReqSpecId);
        mView.showPDF(reqSpec.getFilePath());
    }

}
