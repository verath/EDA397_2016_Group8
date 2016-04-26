package se.chalmers.eda397.group8.pairprogramming.timer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import se.chalmers.eda397.group8.pairprogramming.BaseActivity;
import group8.eda397.chalmers.se.pairprogramming.R;

public class TimerActivity extends BaseActivity {

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, TimerActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        // Setup fragment
        TimerFragment timerFragment;
        if (savedInstanceState == null) {
            timerFragment = TimerFragment.newInstance();
            addFragment(R.id.contentFrame, timerFragment);
        } else {
            timerFragment = (TimerFragment) findFragment(R.id.contentFrame);
        }

        // Setup toolbar
        setupToolbar();

        // Create the presenter
        new TimerPresenter(timerFragment);
    }
}
