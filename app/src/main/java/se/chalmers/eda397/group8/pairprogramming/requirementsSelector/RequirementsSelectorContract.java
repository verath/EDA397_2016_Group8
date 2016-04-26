package se.chalmers.eda397.group8.pairprogramming.requirementsSelector;

import java.util.List;

import se.chalmers.eda397.group8.pairprogramming.BasePresenter;
import se.chalmers.eda397.group8.pairprogramming.BaseView;
import se.chalmers.eda397.group8.pairprogramming.requirements.Requirement;

/**
 * Created by mysko1 on 2016-04-21.
 */
public interface RequirementsSelectorContract {
    interface View extends BaseView<Presenter> {
        void showRequirements(List<Requirement> requirements);

        void displayRequirement(Requirement requirement);

        String[] getFileNames();
    }

    interface Presenter extends BasePresenter {
        void onRequirementClicked(Requirement requirement);
    }
}
