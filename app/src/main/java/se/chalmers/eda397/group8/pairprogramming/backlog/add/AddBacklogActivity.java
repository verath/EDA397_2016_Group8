package se.chalmers.eda397.group8.pairprogramming.backlog.add;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import se.chalmers.eda397.group8.pairprogramming.BaseActivity;
import group8.eda397.chalmers.se.pairprogramming.R;


public class AddBacklogActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_backlog);

        AddBacklogItemFragment backlogFragment;
        if (savedInstanceState == null) {
            backlogFragment = AddBacklogItemFragment.newInstance();
            addFragment(R.id.contentFrame, backlogFragment);
        } else {
            backlogFragment = (AddBacklogItemFragment) findFragment(R.id.contentFrame);
        }

        setupToolbar();

        new AddBacklogPresenter(backlogFragment);
    }

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, AddBacklogActivity.class);
    }

}
