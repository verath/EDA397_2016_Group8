package group8.eda397.chalmers.se.pairprogramming.backlog.add;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import group8.eda397.chalmers.se.pairprogramming.BaseActivity;
import group8.eda397.chalmers.se.pairprogramming.R;
import group8.eda397.chalmers.se.pairprogramming.backlog.BacklogFragment;
import group8.eda397.chalmers.se.pairprogramming.backlog.BacklogPresenter;

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
