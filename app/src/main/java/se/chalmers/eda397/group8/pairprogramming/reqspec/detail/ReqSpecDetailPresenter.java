package se.chalmers.eda397.group8.pairprogramming.reqspec.detail;

import se.chalmers.eda397.group8.pairprogramming.reqspec.RequirementSpecification;

public class ReqSpecDetailPresenter implements ReqSpecDetailContract.Presenter {

    private final ReqSpecDetailContract.View mView;
    private final RequirementSpecification mRequirementSpecification;

    public ReqSpecDetailPresenter(ReqSpecDetailContract.View view, RequirementSpecification requirementSpecification) {
        mView = view;
        mRequirementSpecification = requirementSpecification;
        view.setPresenter(this);
    }

    @Override
    public void start() {
        // Only testing for PDF files in assets folder for now.
        mView.showPDF(mRequirementSpecification.getFilePath());
    }

}
