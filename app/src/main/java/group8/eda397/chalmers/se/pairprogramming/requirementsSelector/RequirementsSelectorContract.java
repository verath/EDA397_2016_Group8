package group8.eda397.chalmers.se.pairprogramming.requirementsSelector;

import java.util.List;

import group8.eda397.chalmers.se.pairprogramming.BasePresenter;
import group8.eda397.chalmers.se.pairprogramming.BaseView;
import group8.eda397.chalmers.se.pairprogramming.requirements.Requirement;

/**
 * Created by mysko1 on 2016-04-21.
 */
public interface RequirementsSelectorContract {
    interface View extends BaseView<Presenter>{
        void showRequirements(List<Requirement> requirements);
        void displayRequirement(Requirement requirement);
        String[] getFileNames();
    }

    interface Presenter extends BasePresenter{
        void onRequirementClicked(Requirement requirement);
    }
}
