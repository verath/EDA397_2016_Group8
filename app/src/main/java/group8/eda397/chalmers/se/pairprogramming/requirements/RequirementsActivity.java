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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requirements);

        RequirementsFragment requirementsFragment;
        if (savedInstanceState == null) {
            requirementsFragment = RequirementsFragment.newInstance();
            addFragment(R.id.frameContainer, requirementsFragment);
        } else {
            requirementsFragment = (RequirementsFragment) findFragment(R.id.frameContainer);
        }
        Requirement requirement = new Requirement(getIntent().getStringExtra("fileName"));
        new RequirementsPresenter(requirementsFragment, requirement);
    }

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, RequirementsActivity.class);
    }

}
