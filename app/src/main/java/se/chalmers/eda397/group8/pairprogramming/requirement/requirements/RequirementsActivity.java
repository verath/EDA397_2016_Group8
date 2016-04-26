package se.chalmers.eda397.group8.pairprogramming.requirement.requirements;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import se.chalmers.eda397.group8.pairprogramming.BaseActivity;
import group8.eda397.chalmers.se.pairprogramming.R;

/**
 * Activity for selecting Requirements.
 */
public class RequirementsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requirements);

        setupToolbar();

        RequirementsFragment requirementsFragment;
        if (savedInstanceState == null) {
            requirementsFragment = RequirementsFragment.newInstance();
            addFragment(R.id.selectorContentFrame, requirementsFragment);
        } else {
            requirementsFragment = (RequirementsFragment) findFragment(R.id.selectorContentFrame);
        }

        new RequirementsPresenter(requirementsFragment);
    }

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, RequirementsActivity.class);
    }

}
