package group8.eda397.chalmers.se.pairprogramming.requirementsSelector;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import group8.eda397.chalmers.se.pairprogramming.BaseActivity;
import group8.eda397.chalmers.se.pairprogramming.R;

/**
 * Created by mysko1 on 2016-04-21.
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
