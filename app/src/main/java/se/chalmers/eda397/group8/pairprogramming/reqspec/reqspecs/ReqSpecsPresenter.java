package se.chalmers.eda397.group8.pairprogramming.reqspec.reqspecs;

import java.util.List;

import se.chalmers.eda397.group8.pairprogramming.reqspec.RequirementSpecification;
import se.chalmers.eda397.group8.pairprogramming.reqspec.RequirementSpecificationDataSource;

/**
 * Created by mysko1 on 2016-04-21.
 */
public class ReqSpecsPresenter implements ReqSpecsContract.Presenter {

    private final ReqSpecsContract.View mView;
    private final RequirementSpecificationDataSource mReqSpecDataSource;

    public ReqSpecsPresenter(ReqSpecsContract.View view,
                             RequirementSpecificationDataSource requirementSpecificationDataSource) {
        mView = view;
        mReqSpecDataSource = requirementSpecificationDataSource;
        view.setPresenter(this);
    }

    @Override
    public void start() {
        List<RequirementSpecification> requirementSpecifications = mReqSpecDataSource.getAll();
        mView.showRequirements(requirementSpecifications);
    }

    @Override
    public void onRequirementClicked(RequirementSpecification requirementSpecification) {
        mView.displayRequirement(requirementSpecification.getId());
    }
}
