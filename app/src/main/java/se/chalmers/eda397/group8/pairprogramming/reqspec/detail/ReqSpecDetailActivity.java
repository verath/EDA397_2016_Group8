package se.chalmers.eda397.group8.pairprogramming.reqspec.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import se.chalmers.eda397.group8.pairprogramming.BaseActivity;
import se.chalmers.eda397.group8.pairprogramming.R;
import se.chalmers.eda397.group8.pairprogramming.reqspec.data.RequirementSpecificationRepository;

/**
 * The activity for viewing requirements.
 */
public class ReqSpecDetailActivity extends BaseActivity {

    private final static String PARAM_REQ_SPEC_ID = "group8.eda397.chalmers.se.pairprogramming.PARAM_REQ_SPEC_ID";

    private String mReqSpecId;

    public static Intent getCallingIntent(Context context, String reqSpecId) {
        Intent intent = new Intent(context, ReqSpecDetailActivity.class);
        intent.putExtra(PARAM_REQ_SPEC_ID, reqSpecId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_req_spec_detail);

        ReqSpecDetailFragment detailFragment;
        if (savedInstanceState == null) {
            mReqSpecId = getIntent().getStringExtra(PARAM_REQ_SPEC_ID);
            detailFragment = ReqSpecDetailFragment.newInstance();
            addFragment(R.id.frameContainer, detailFragment);
        } else {
            mReqSpecId = savedInstanceState.getString(PARAM_REQ_SPEC_ID);
            detailFragment = (ReqSpecDetailFragment) findFragment(R.id.frameContainer);
        }

        new ReqSpecDetailPresenter(detailFragment,
                RequirementSpecificationRepository.getInstance(getApplicationContext()),
                mReqSpecId);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (outState != null) {
            outState.putString(PARAM_REQ_SPEC_ID, mReqSpecId);
        }
        super.onSaveInstanceState(outState);
    }
}
