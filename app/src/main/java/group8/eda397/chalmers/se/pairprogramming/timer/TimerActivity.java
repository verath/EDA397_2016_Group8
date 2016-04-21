package group8.eda397.chalmers.se.pairprogramming.timer;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import group8.eda397.chalmers.se.pairprogramming.R;

public class TimerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        // Setup toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // Try to find the notes fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        TimerFragment timerFragment = (TimerFragment) fragmentManager
                .findFragmentById(R.id.contentFrame);

        // Create the note fragment if it did not exist
        if (timerFragment == null) {
            timerFragment = TimerFragment.newInstance();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.contentFrame, timerFragment);
            fragmentTransaction.commit();
        }

        // Create the presenter
        TimerContract.Presenter timerPresenter = new TimerPresenter(timerFragment);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
