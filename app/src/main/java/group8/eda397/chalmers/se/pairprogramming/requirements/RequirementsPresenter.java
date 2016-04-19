package group8.eda397.chalmers.se.pairprogramming.requirements;

public class RequirementsPresenter implements RequirementsContract.Presenter {

    private RequirementsContract.View fragment;
    private Requirement requirement;

    public RequirementsPresenter(RequirementsContract.View fragment) {
        this.fragment = fragment;
        fragment.setPresenter(this);
    }

    @Override
    public void start() {
        requirement = new Requirement("sample.pdf");
        // Only testing for PDF files in assets folder for now.
        fragment.showPDF(requirement.getFilePath());
    }

}
