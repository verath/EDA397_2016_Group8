package se.chalmers.eda397.group8.pairprogramming.requirement.detail;

import se.chalmers.eda397.group8.pairprogramming.requirement.Requirement;

public class RequirementDetailPresenter implements RequirementDetailContract.Presenter {

    private final RequirementDetailContract.View mView;
    private final Requirement mRequirement;

    public RequirementDetailPresenter(RequirementDetailContract.View view, Requirement requirement) {
        mView = view;
        mRequirement = requirement;
        view.setPresenter(this);
    }

    @Override
    public void start() {
        // Only testing for PDF files in assets folder for now.
        mView.showPDF(mRequirement.getFilePath());
    }

}
