package se.chalmers.eda397.group8.pairprogramming.backlog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import se.chalmers.eda397.group8.pairprogramming.BaseActivity;
import se.chalmers.eda397.group8.pairprogramming.R;
import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogItemDataSource;
import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogItemRepository;
import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogStatusRepository;
import se.chalmers.eda397.group8.pairprogramming.backlog.model.database.local.BacklogItemLocalDataSource;


public class BacklogActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backlog);

        BacklogFragment backlogFragment;
        if (savedInstanceState == null) {
            backlogFragment = BacklogFragment.newInstance();
            addFragment(R.id.contentFrame, backlogFragment);
        } else {
            backlogFragment = (BacklogFragment) findFragment(R.id.contentFrame);
        }
        setupToolbar();
        new BacklogPresenter(backlogFragment, BacklogStatusRepository.getInstance());
    }

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, BacklogActivity.class);
    }

    public void createBacklogStatusPresenter(BacklogStatusFragment backlogStatusFragment, String statusId) {
        BacklogItemLocalDataSource localDataSource = BacklogItemLocalDataSource.getInstance(getApplicationContext());
        BacklogItemDataSource dataSource = BacklogItemRepository.getInstance(localDataSource);

        new BacklogStatusPresenter(backlogStatusFragment, dataSource, statusId);
    }
}
