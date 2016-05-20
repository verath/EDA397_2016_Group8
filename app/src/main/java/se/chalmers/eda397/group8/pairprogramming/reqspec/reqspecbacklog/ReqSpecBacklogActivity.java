package se.chalmers.eda397.group8.pairprogramming.reqspec.reqspecbacklog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import se.chalmers.eda397.group8.pairprogramming.BaseActivity;
import se.chalmers.eda397.group8.pairprogramming.R;
import se.chalmers.eda397.group8.pairprogramming.reqspec.data.RequirementRepository;
import se.chalmers.eda397.group8.pairprogramming.reqspec.data.RequirementSpecificationRepository;
import se.chalmers.eda397.group8.pairprogramming.reqspec.data.local.RequirementLocalDataSource;

/**
 * The activity for viewing requirements.
 */
public class ReqSpecBacklogActivity extends BaseActivity {
    private final static String PARAM_REQUIREMENT_ID = "group8.eda397.chalmers.se.pairprogramming.PARAM_REQUIREMENT_ID";

    private String mRequirementId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_backlog);

        ReqSpecBacklogFragment detailFragment;
        if (savedInstanceState == null) {
            mRequirementId = getIntent().getStringExtra(PARAM_REQUIREMENT_ID);
            detailFragment = ReqSpecBacklogFragment.newInstance();
            addFragment(R.id.frameContainer, detailFragment);
        } else {
            mRequirementId = savedInstanceState.getString(PARAM_REQUIREMENT_ID);
            detailFragment = (ReqSpecBacklogFragment) findFragment(R.id.frameContainer);
        }
        new ReqSpecBacklogPresenter(detailFragment,
                RequirementRepository.getInstance(RequirementLocalDataSource.getInstance(getApplicationContext())),
                RequirementSpecificationRepository.getInstance(getApplicationContext()),
                mRequirementId);
    }

    public static Intent getCallingIntent(Context context, String requirementId) {
        Intent intent = new Intent(context, ReqSpecBacklogActivity.class);
        intent.putExtra(PARAM_REQUIREMENT_ID, requirementId);
        return intent;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (outState != null) {
            outState.putString(PARAM_REQUIREMENT_ID, mRequirementId);
        }
        super.onSaveInstanceState(outState);
    }
}
