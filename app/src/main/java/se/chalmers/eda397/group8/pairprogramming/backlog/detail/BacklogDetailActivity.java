package se.chalmers.eda397.group8.pairprogramming.backlog.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import se.chalmers.eda397.group8.pairprogramming.BaseActivity;
import se.chalmers.eda397.group8.pairprogramming.R;
import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogItemRepository;
import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogStatusRepository;

public class BacklogDetailActivity extends BaseActivity {

    private static final String ARG_ITEM_ID = "item_id";
    private String mItemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backlog_detail);

        BacklogDetailFragment backlogFragment;
        if (savedInstanceState == null) {
            backlogFragment = BacklogDetailFragment.newInstance();
            mItemId = getIntent().getStringExtra(ARG_ITEM_ID);
            addFragment(R.id.contentFrame, backlogFragment);
        } else {
            mItemId = savedInstanceState.getString(ARG_ITEM_ID);
            backlogFragment = (BacklogDetailFragment) findFragment(R.id.contentFrame);
        }
        setupToolbar();
        new BacklogDetailPresenter(backlogFragment, mItemId,
                BacklogItemRepository.getInstance(), BacklogStatusRepository.getInstance());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (outState != null) {
            outState.putString(ARG_ITEM_ID, mItemId);
        }
        super.onSaveInstanceState(outState);
    }

    public static Intent getCallingIntent(Context context, String itemId) {
        Intent intent = new Intent(context, BacklogDetailActivity.class);
        intent.putExtra(ARG_ITEM_ID, itemId);
        return intent;
    }
}
