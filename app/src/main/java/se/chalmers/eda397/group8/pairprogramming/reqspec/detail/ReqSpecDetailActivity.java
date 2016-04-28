package se.chalmers.eda397.group8.pairprogramming.reqspec.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import group8.eda397.chalmers.se.pairprogramming.R;
import se.chalmers.eda397.group8.pairprogramming.BaseActivity;
import se.chalmers.eda397.group8.pairprogramming.reqspec.RequirementSpecification;

/**
 * The activity for viewing requirements.
 */
public class ReqSpecDetailActivity extends BaseActivity {

    private final static String INTENT_EXTRA_PARAM_FILE_NAME = "group8.eda397.chalmers.se.pairprogramming.INTENT_PARAM_FILE_NAME";
    private RequirementSpecification mRequirementSpecification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_req_spec_detail);

        ReqSpecDetailFragment detailFragment;
        if (savedInstanceState == null) {
            mRequirementSpecification = new RequirementSpecification(getIntent().getStringExtra(INTENT_EXTRA_PARAM_FILE_NAME));
            detailFragment = ReqSpecDetailFragment.newInstance();
            addFragment(R.id.frameContainer, detailFragment);
        } else {
            mRequirementSpecification = new RequirementSpecification(savedInstanceState.getString(INTENT_EXTRA_PARAM_FILE_NAME));
            detailFragment = (ReqSpecDetailFragment) findFragment(R.id.frameContainer);
        }
        new ReqSpecDetailPresenter(detailFragment, mRequirementSpecification);
    }

    public static Intent getCallingIntent(Context context, RequirementSpecification requirementSpecification) {
        Intent intent = new Intent(context, ReqSpecDetailActivity.class);
        intent.putExtra(INTENT_EXTRA_PARAM_FILE_NAME, requirementSpecification.getFilePath());
        return intent;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (outState != null) {
            outState.putString(INTENT_EXTRA_PARAM_FILE_NAME, mRequirementSpecification.getFilePath());
        }
        super.onSaveInstanceState(outState);
    }
}
