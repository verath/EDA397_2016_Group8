package se.chalmers.eda397.group8.pairprogramming.requirements;

public class RequirementsPresenter implements RequirementsContract.Presenter {

    private final RequirementsContract.View mView;
    private final Requirement mRequirement;

    public RequirementsPresenter(RequirementsContract.View view, Requirement requirement) {
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
