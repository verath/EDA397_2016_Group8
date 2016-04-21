package group8.eda397.chalmers.se.pairprogramming.timer;

import android.os.Bundle;

import group8.eda397.chalmers.se.pairprogramming.BaseActivity;
import group8.eda397.chalmers.se.pairprogramming.R;

public class TimerActivity extends BaseActivity {

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
