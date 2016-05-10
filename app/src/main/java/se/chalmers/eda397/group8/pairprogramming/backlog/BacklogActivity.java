package se.chalmers.eda397.group8.pairprogramming.backlog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import se.chalmers.eda397.group8.pairprogramming.BaseActivity;
import se.chalmers.eda397.group8.pairprogramming.R;
import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogItemRepository;
import se.chalmers.eda397.group8.pairprogramming.data.local.BacklogLocalDataSource;


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
        new BacklogPresenter(backlogFragment, BacklogItemRepository.getInstance(BacklogLocalDataSource.getInstance(getApplicationContext())));
    }

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, BacklogActivity.class);
    }
}
