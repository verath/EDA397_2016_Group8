package se.chalmers.eda397.group8.pairprogramming.reqspec.reqspecbacklog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import se.chalmers.eda397.group8.pairprogramming.BaseActivity;
import se.chalmers.eda397.group8.pairprogramming.R;
import se.chalmers.eda397.group8.pairprogramming.reqspec.RequirementSpecification;

/**
 * The activity for viewing requirements.
 */
public class ReqSpecBacklogActivity extends BaseActivity {

    private final static String INTENT_EXTRA_PARAM_PAGE_NUMBER = "group8.eda397.chalmers.se.pairprogramming.INTENT_EXTRA_PARAM_PAGE_NUMBER";
    private String mPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_backlog);

        ReqSpecBacklogFragment detailFragment;
        if (savedInstanceState == null) {
            mPage = getIntent().getStringExtra(INTENT_EXTRA_PARAM_PAGE_NUMBER);
            detailFragment = ReqSpecBacklogFragment.newInstance();
            addFragment(R.id.frameContainer, detailFragment);
        } else {
            mPage = savedInstanceState.getString(INTENT_EXTRA_PARAM_PAGE_NUMBER);
            detailFragment = (ReqSpecBacklogFragment) findFragment(R.id.frameContainer);
        }
        new ReqSpecBacklogPresenter(detailFragment, mPage);
    }

    public static Intent getCallingIntent(Context context, String page) {
        Intent intent = new Intent(context, ReqSpecBacklogActivity.class);
        intent.putExtra(INTENT_EXTRA_PARAM_PAGE_NUMBER, page);
        return intent;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (outState != null) {
            outState.putString(INTENT_EXTRA_PARAM_PAGE_NUMBER, mPage);
        }
        super.onSaveInstanceState(outState);
    }
}
