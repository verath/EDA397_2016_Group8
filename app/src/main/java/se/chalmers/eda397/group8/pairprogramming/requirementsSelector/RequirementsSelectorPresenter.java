package se.chalmers.eda397.group8.pairprogramming.requirementsSelector;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.eda397.group8.pairprogramming.requirements.Requirement;

/**
 * Created by mysko1 on 2016-04-21.
 */
public class RequirementsSelectorPresenter implements RequirementsSelectorContract.Presenter {

    private final RequirementsSelectorContract.View mView;

    public RequirementsSelectorPresenter(RequirementsSelectorContract.View view) {
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
