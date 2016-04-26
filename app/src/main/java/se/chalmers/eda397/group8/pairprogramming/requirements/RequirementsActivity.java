package se.chalmers.eda397.group8.pairprogramming.requirements;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import group8.eda397.chalmers.se.pairprogramming.R;
import se.chalmers.eda397.group8.pairprogramming.BaseActivity;

/**
 * The activity for viewing requirements.
 */
public class RequirementsActivity extends BaseActivity {

    private final static String INTENT_EXTRA_PARAM_FILE_NAME = "group8.eda397.chalmers.se.pairprogramming.INTENT_PARAM_FILE_NAME";
    private Requirement mRequirement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requirements);

        RequirementsFragment requirementsFragment;
        if (savedInstanceState == null) {
            mRequirement = new Requirement(getIntent().getStringExtra(INTENT_EXTRA_PARAM_FILE_NAME));
            requirementsFragment = RequirementsFragment.newInstance();
            addFragment(R.id.frameContainer, requirementsFragment);
        } else {
            mRequirement = new Requirement(savedInstanceState.getString(INTENT_EXTRA_PARAM_FILE_NAME));
            requirementsFragment = (RequirementsFragment) findFragment(R.id.frameContainer);
        }
        new RequirementsPresenter(requirementsFragment, mRequirement);
    }

    public static Intent getCallingIntent(Context context, Requirement requirement) {
        Intent intent = new Intent(context, RequirementsActivity.class);
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
