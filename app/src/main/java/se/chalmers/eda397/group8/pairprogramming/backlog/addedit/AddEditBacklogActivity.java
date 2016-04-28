package se.chalmers.eda397.group8.pairprogramming.backlog.addedit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import se.chalmers.eda397.group8.pairprogramming.BaseActivity;
import se.chalmers.eda397.group8.pairprogramming.R;
import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogItemRepository;


public class AddEditBacklogActivity extends BaseActivity {

    private static final String ARG_ITEM_ID = "item_id";
    private String mItemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_backlog);

        AddEditBacklogItemFragment backlogFragment;
        if (savedInstanceState == null) {
            backlogFragment = AddEditBacklogItemFragment.newInstance();
            mItemId = getIntent().getStringExtra(ARG_ITEM_ID);
            addFragment(R.id.contentFrame, backlogFragment);
        } else {
            mItemId = savedInstanceState.getString(ARG_ITEM_ID);
            backlogFragment = (AddEditBacklogItemFragment) findFragment(R.id.contentFrame);
        }

        setupToolbar();

        new AddEditBacklogPresenter(backlogFragment, mItemId, BacklogItemRepository.getInstance());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (outState != null) {
            outState.putString(ARG_ITEM_ID, mItemId);
        }
        super.onSaveInstanceState(outState);
    }

    public static Intent getCallingIntent(Context context, @Nullable String itemId) {
        Intent intent = new Intent(context, AddEditBacklogActivity.class);
        intent.putExtra(ARG_ITEM_ID, itemId);
        return intent;
    }

}
