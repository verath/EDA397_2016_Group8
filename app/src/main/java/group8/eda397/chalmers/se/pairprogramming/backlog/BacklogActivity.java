package group8.eda397.chalmers.se.pairprogramming.backlog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import group8.eda397.chalmers.se.pairprogramming.BaseActivity;
import group8.eda397.chalmers.se.pairprogramming.R;
import group8.eda397.chalmers.se.pairprogramming.backlog.BacklogFragment;
import group8.eda397.chalmers.se.pairprogramming.backlog.add.AddBacklogItemFragment;
import group8.eda397.chalmers.se.pairprogramming.backlog.add.AddBacklogPresenter;
import group8.eda397.chalmers.se.pairprogramming.backlog.model.BacklogItem;

public class BacklogActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backlog);
        setupToolbar();

        BacklogFragment backlogFragment;
        if (savedInstanceState == null) {
            backlogFragment = BacklogFragment.newInstance();
            addFragment(R.id.contentFrame, backlogFragment);
        } else {
            backlogFragment = (BacklogFragment) findFragment(R.id.contentFrame);
        }
        setupToolbar();
        new BacklogPresenter(backlogFragment);
    }
    public static Intent getCallingIntent(Context context) {
        return new Intent(context, BacklogActivity.class);
    }
}
