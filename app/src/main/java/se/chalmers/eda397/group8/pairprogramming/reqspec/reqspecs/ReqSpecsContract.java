package se.chalmers.eda397.group8.pairprogramming.reqspec.reqspecs;

import java.util.List;

import se.chalmers.eda397.group8.pairprogramming.BasePresenter;
import se.chalmers.eda397.group8.pairprogramming.BaseView;
import se.chalmers.eda397.group8.pairprogramming.reqspec.RequirementSpecification;

/**
 * Created by mysko1 on 2016-04-21.
 */
public interface ReqSpecsContract {
    interface View extends BaseView<Presenter> {
        void showRequirements(List<RequirementSpecification> requirementSpecifications);

        void displayRequirement(RequirementSpecification requirementSpecification);

        String[] getFileNames();
    }

    interface Presenter extends BasePresenter {
        void onRequirementClicked(RequirementSpecification requirementSpecification);
    }
}
