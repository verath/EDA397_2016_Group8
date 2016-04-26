package group8.eda397.chalmers.se.pairprogramming.requirements;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import group8.eda397.chalmers.se.pairprogramming.BaseActivity;
import group8.eda397.chalmers.se.pairprogramming.R;

/**
 * The activity for viewing requirements.
 */
public class RequirementsActivity extends BaseActivity {

    private final static String INTENT_EXTRA_PARAM_FILE_NAME = "group8.eda397.chalmers.se.pairprogramming.INTENT_PARAM_FILE_NAME";
    private Requirement requirement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requirements);

        RequirementsFragment requirementsFragment;
        if (savedInstanceState == null) {
            requirement = new Requirement(getIntent().getStringExtra(INTENT_EXTRA_PARAM_FILE_NAME));
            requirementsFragment = RequirementsFragment.newInstance();
            addFragment(R.id.frameContainer, requirementsFragment);
        } else {
            requirement = new Requirement(savedInstanceState.getString(INTENT_EXTRA_PARAM_FILE_NAME));
            requirementsFragment = (RequirementsFragment) findFragment(R.id.frameContainer);
        }
        new RequirementsPresenter(requirementsFragment, requirement);
    }

    public static Intent getCallingIntent(Context context, Requirement requirement) {
        Intent intent = new Intent(context, RequirementsActivity.class);
        intent.putExtra(INTENT_EXTRA_PARAM_FILE_NAME, requirement.getFilePath());
        return intent;
    }

}
