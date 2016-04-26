package se.chalmers.eda397.group8.pairprogramming.requirement.requirements;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.eda397.group8.pairprogramming.requirement.Requirement;

/**
 * Created by mysko1 on 2016-04-21.
 */
public class RequirementsPresenter implements RequirementsContract.Presenter {

    private final RequirementsContract.View mView;

    public RequirementsPresenter(RequirementsContract.View view) {
        this.mView = view;
        view.setPresenter(this);
    }

    @Override
    public void start() {
        List<Requirement> requirements = new ArrayList<>();
        // Only add file extensions with .pdf
        String[] fileNames = mView.getFileNames();
        for (String fileName : fileNames) {
            if (fileName.endsWith(".pdf")) {
                requirements.add(new Requirement(fileName));
            }
        }
        mView.showRequirements(requirements);
    }

    @Override
    public void onRequirementClicked(Requirement requirement) {
        mView.displayRequirement(requirement);
    }
}
