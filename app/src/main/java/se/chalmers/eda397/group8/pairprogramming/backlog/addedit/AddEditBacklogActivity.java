package se.chalmers.eda397.group8.pairprogramming.backlog.addedit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import se.chalmers.eda397.group8.pairprogramming.BaseActivity;
import se.chalmers.eda397.group8.pairprogramming.R;
import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogItemRepository;
import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogStatusRepository;


public class AddEditBacklogActivity extends BaseActivity {

    private static final String ARG_ITEM_ID = "se.chalmers.eda397.group8.pairprogramming.backlog_item_id";
    private static final String ARG_STATUS_ID = "se.chalmers.eda397.group8.pairprogramming.backlog_status_id";
    private String mItemId;
    private String mStatusId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_backlog);

        AddEditBacklogItemFragment backlogFragment;
        if (savedInstanceState == null) {
            backlogFragment = AddEditBacklogItemFragment.newInstance();
            mItemId = getIntent().getStringExtra(ARG_ITEM_ID);
            mStatusId = getIntent().getStringExtra(ARG_STATUS_ID);
            addFragment(R.id.contentFrame, backlogFragment);
        } else {
            mItemId = savedInstanceState.getString(ARG_ITEM_ID);
            mStatusId = savedInstanceState.getString(ARG_STATUS_ID);
            backlogFragment = (AddEditBacklogItemFragment) findFragment(R.id.contentFrame);
        }

        setupToolbar();

        new AddEditBacklogPresenter(backlogFragment, mItemId, mStatusId,
                BacklogItemRepository.getInstance(), BacklogStatusRepository.getInstance());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (outState != null) {
            outState.putString(ARG_ITEM_ID, mItemId);
            outState.putString(ARG_STATUS_ID, mStatusId);
        }
        super.onSaveInstanceState(outState);
    }

    /**
     * Creates the intent for starting the activity.
     *
     * @param context  the application context
     * @param itemId   the ID of the item to edit, or <code>null</code> if a new item should be created     *
     * @param statusId the default status to display
     * @return the intent
     */
    public static Intent getCallingIntent(Context context, @Nullable String itemId, @Nullable String statusId) {
        Intent intent = new Intent(context, AddEditBacklogActivity.class);
        intent.putExtra(ARG_ITEM_ID, itemId);
        intent.putExtra(ARG_STATUS_ID, statusId);
        return intent;
    }

}
