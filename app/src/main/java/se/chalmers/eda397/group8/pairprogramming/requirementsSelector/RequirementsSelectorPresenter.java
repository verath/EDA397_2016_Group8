package se.chalmers.eda397.group8.pairprogramming.requirementsSelector;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.eda397.group8.pairprogramming.requirements.Requirement;

/**
 * Created by mysko1 on 2016-04-21.
 */
public class RequirementsSelectorPresenter implements RequirementsSelectorContract.Presenter {

    private RequirementsSelectorContract.View fragment;

    public RequirementsSelectorPresenter(RequirementsSelectorContract.View fragment) {
        this.fragment = fragment;
        fragment.setPresenter(this);
    }

    @Override
    public void start() {
        List<Requirement> requirements = new ArrayList<>();
        // Only add file extensions with .pdf
        String[] fileNames = fragment.getFileNames();
        for (String fileName : fileNames) {
            if (fileName.endsWith(".pdf")) {
                requirements.add(new Requirement(fileName));
            }
        }
        fragment.showRequirements(requirements);
    }

    @Override
    public void onRequirementClicked(Requirement requirement) {
        fragment.displayRequirement(requirement);
    }
}
