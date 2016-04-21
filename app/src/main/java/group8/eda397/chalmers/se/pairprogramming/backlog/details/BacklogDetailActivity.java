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

public class BacklogDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backlog_detail);

        BacklogDetailFragment backlogFragment;
        if (savedInstanceState == null) {
            backlogFragment = BacklogDetailFragment.newInstance();
            addFragment(R.id.contentFrame, backlogFragment);
        } else {
            backlogFragment = (BacklogDetailFragment) findFragment(R.id.contentFrame);
        }
        setupToolbar();
        new BacklogDetailPresenter(backlogFragment);
    }

    public static Intent getCallingIntent(Context context, BacklogItem item) {
        Intent intent = new Intent(context, BacklogDetailActivity.class);
        return intent;
    }
}
