package group8.eda397.chalmers.se.pairprogramming.timer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import group8.eda397.chalmers.se.pairprogramming.R;

public class TimerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        TimerFragment timerFragment =
                (TimerFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);


    }
}
