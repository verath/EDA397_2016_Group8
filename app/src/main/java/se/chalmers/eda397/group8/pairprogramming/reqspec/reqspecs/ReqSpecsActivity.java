package se.chalmers.eda397.group8.pairprogramming.reqspec.reqspecs;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import se.chalmers.eda397.group8.pairprogramming.BaseActivity;
import se.chalmers.eda397.group8.pairprogramming.R;

/**
 * Activity for selecting Requirements.
 */
public class ReqSpecsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_req_specs);

        setupToolbar();

        ReqSpecsFragment reqSpecsFragment;
        if (savedInstanceState == null) {
            reqSpecsFragment = ReqSpecsFragment.newInstance();
            addFragment(R.id.selectorContentFrame, reqSpecsFragment);
        } else {
            reqSpecsFragment = (ReqSpecsFragment) findFragment(R.id.selectorContentFrame);
        }

        new ReqSpecsPresenter(reqSpecsFragment);
    }

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, ReqSpecsActivity.class);
    }

}
