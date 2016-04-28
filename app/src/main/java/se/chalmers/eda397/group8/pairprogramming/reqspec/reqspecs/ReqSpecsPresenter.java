package se.chalmers.eda397.group8.pairprogramming.reqspec.reqspecs;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.eda397.group8.pairprogramming.reqspec.RequirementSpecification;

/**
 * Created by mysko1 on 2016-04-21.
 */
public class ReqSpecsPresenter implements ReqSpecsContract.Presenter {

    private final ReqSpecsContract.View mView;

    public ReqSpecsPresenter(ReqSpecsContract.View view) {
        this.mView = view;
        view.setPresenter(this);
    }

    @Override
    public void start() {
        List<RequirementSpecification> requirementSpecifications = new ArrayList<>();
        // Only add file extensions with .pdf
        String[] fileNames = mView.getFileNames();
        for (String fileName : fileNames) {
            if (fileName.endsWith(".pdf")) {
                requirementSpecifications.add(new RequirementSpecification(fileName));
            }
        }
        mView.showRequirements(requirementSpecifications);
    }

    @Override
    public void onRequirementClicked(RequirementSpecification requirementSpecification) {
        mView.displayRequirement(requirementSpecification);
    }
}
