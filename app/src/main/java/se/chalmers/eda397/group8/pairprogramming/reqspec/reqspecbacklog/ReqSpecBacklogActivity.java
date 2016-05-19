package se.chalmers.eda397.group8.pairprogramming.reqspec.reqspecbacklog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import se.chalmers.eda397.group8.pairprogramming.BaseActivity;
import se.chalmers.eda397.group8.pairprogramming.R;

/**
 * The activity for viewing requirements.
 */
public class ReqSpecBacklogActivity extends BaseActivity {

    private final static String INTENT_EXTRA_PARAM_PDF_NAME = "group8.eda397.chalmers.se.pairprogramming.INTENT_EXTRA_PARAM_PDF_NAME";
    private final static String INTENT_EXTRA_PARAM_PAGE_NUMBER = "group8.eda397.chalmers.se.pairprogramming.INTENT_EXTRA_PARAM_PAGE_NUMBER";

    private String mPage;
    private String mPDFName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_backlog);

        ReqSpecBacklogFragment detailFragment;
        if (savedInstanceState == null) {
            mPDFName = getIntent().getStringExtra(INTENT_EXTRA_PARAM_PDF_NAME);
            mPage = getIntent().getStringExtra(INTENT_EXTRA_PARAM_PAGE_NUMBER);
            detailFragment = ReqSpecBacklogFragment.newInstance();
            addFragment(R.id.frameContainer, detailFragment);
        } else {
            mPDFName = savedInstanceState.getString(INTENT_EXTRA_PARAM_PDF_NAME);
            mPage = savedInstanceState.getString(INTENT_EXTRA_PARAM_PAGE_NUMBER);
            detailFragment = (ReqSpecBacklogFragment) findFragment(R.id.frameContainer);
        }
        new ReqSpecBacklogPresenter(detailFragment, mPDFName, mPage);
    }

    public static Intent getCallingIntent(Context context, String page, String pdfName) {
        Intent intent = new Intent(context, ReqSpecBacklogActivity.class);
        intent.putExtra(INTENT_EXTRA_PARAM_PDF_NAME, pdfName);
        intent.putExtra(INTENT_EXTRA_PARAM_PAGE_NUMBER, page);
        return intent;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (outState != null) {
            outState.putString(INTENT_EXTRA_PARAM_PDF_NAME, mPDFName);
            outState.putString(INTENT_EXTRA_PARAM_PAGE_NUMBER, mPage);


        }
        super.onSaveInstanceState(outState);
    }
}
