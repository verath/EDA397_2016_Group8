package group8.eda397.chalmers.se.pairprogramming.backlog.details;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import group8.eda397.chalmers.se.pairprogramming.BaseActivity;
import group8.eda397.chalmers.se.pairprogramming.R;
import group8.eda397.chalmers.se.pairprogramming.backlog.add.AddBacklogItemFragment;
import group8.eda397.chalmers.se.pairprogramming.backlog.add.AddBacklogPresenter;
import group8.eda397.chalmers.se.pairprogramming.backlog.model.BacklogItem;
import group8.eda397.chalmers.se.pairprogramming.backlog.model.BacklogItemDataSource;
import group8.eda397.chalmers.se.pairprogramming.backlog.model.BacklogItemRepository;

public class BacklogDetailActivity extends BaseActivity {

    private static final String ARG_ITEM_ID = "itemid";
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
        new BacklogDetailPresenter(backlogFragment, mItemId, BacklogItemRepository.getInstance());
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
