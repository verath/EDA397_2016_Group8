package se.chalmers.eda397.group8.pairprogramming.requirements;

public class RequirementsPresenter implements RequirementsContract.Presenter {

    private RequirementsContract.View fragment;
    private Requirement requirement;

    public RequirementsPresenter(RequirementsContract.View fragment, Requirement requirement) {
        this.fragment = fragment;
        this.requirement = requirement;
        fragment.setPresenter(this);
    }

    @Override
    public void start() {
        // Only testing for PDF files in assets folder for now.
        fragment.showPDF(requirement.getFilePath());
    }

}
