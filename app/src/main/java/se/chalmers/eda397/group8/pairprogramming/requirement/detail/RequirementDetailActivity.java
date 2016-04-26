package se.chalmers.eda397.group8.pairprogramming.requirement.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import group8.eda397.chalmers.se.pairprogramming.R;
import se.chalmers.eda397.group8.pairprogramming.BaseActivity;
import se.chalmers.eda397.group8.pairprogramming.requirement.Requirement;

/**
 * The activity for viewing requirements.
 */
public class RequirementDetailActivity extends BaseActivity {

    private final static String INTENT_EXTRA_PARAM_FILE_NAME = "group8.eda397.chalmers.se.pairprogramming.INTENT_PARAM_FILE_NAME";
    private Requirement mRequirement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requirement_detail);

        RequirementDetailFragment detailFragment;
        if (savedInstanceState == null) {
            mRequirement = new Requirement(getIntent().getStringExtra(INTENT_EXTRA_PARAM_FILE_NAME));
            detailFragment = RequirementDetailFragment.newInstance();
            addFragment(R.id.frameContainer, detailFragment);
        } else {
            mRequirement = new Requirement(savedInstanceState.getString(INTENT_EXTRA_PARAM_FILE_NAME));
            detailFragment = (RequirementDetailFragment) findFragment(R.id.frameContainer);
        }
        new RequirementDetailPresenter(detailFragment, mRequirement);
    }

    public static Intent getCallingIntent(Context context, Requirement requirement) {
        Intent intent = new Intent(context, RequirementDetailActivity.class);
        intent.putExtra(INTENT_EXTRA_PARAM_FILE_NAME, requirement.getFilePath());
        return intent;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (outState != null) {
            outState.putString(INTENT_EXTRA_PARAM_FILE_NAME, mRequirement.getFilePath());
        }
        super.onSaveInstanceState(outState);
    }
}
