package se.chalmers.eda397.group8.pairprogramming.requirementsSelector;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import se.chalmers.eda397.group8.pairprogramming.BaseActivity;
import group8.eda397.chalmers.se.pairprogramming.R;

/**
 * Activity for selecting Requirements.
 */
public class RequirementsSelectorActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reqselector);

        setupToolbar();

        RequirementsSelectorFragment reqSelectorFragment;
        if (savedInstanceState == null) {
            reqSelectorFragment = RequirementsSelectorFragment.newInstance();
            addFragment(R.id.selectorContentFrame, reqSelectorFragment);
        } else {
            reqSelectorFragment = (RequirementsSelectorFragment) findFragment(R.id.selectorContentFrame);
        }

        new RequirementsSelectorPresenter(reqSelectorFragment);
    }

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, RequirementsSelectorActivity.class);
    }

}
