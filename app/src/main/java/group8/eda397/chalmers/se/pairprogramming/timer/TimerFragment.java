package group8.eda397.chalmers.se.pairprogramming.timer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import group8.eda397.chalmers.se.pairprogramming.R;

/**
 * Created by Vidar on 14/04/16.
 */
public class TimerFragment extends Fragment implements TimerContract.View {

    private TimerContract.Presenter mPresenter;
    private TextView mTimerTime;
    private Button mStart;
    private boolean timerHasStarted = false;

    public static TimerFragment newInstance() {
        return new TimerFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timer, container, false);
        mTimerTime = (TextView) view.findViewById(R.id.timer_fragment_timer);
        mStart = (Button) view.findViewById(R.id.start_btn_timer);
        mStart.setOnClickListener(onStartButtonClick);
        return view;
    }

    public TimerFragment() {
    }

    @Override
    public void showTimer() {

    }

    @Override
    public void setTitle(String title) {

    }

    @Override
    public void setDescription(String description) {

    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void displayRemainingTime(long millisUntilFinished) {
        String timeparsed = String.format("%02d:%02d",

                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) -
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));

        mTimerTime.setText(timeparsed);

    }

    @Override
    public void displayFinished() {
        mTimerTime.setText("Switch");
        mStart.setText("START");
    }

    @Override
    public void setPresenter(TimerContract.Presenter presenter){ mPresenter = presenter; }

    private View.OnClickListener onStartButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!timerHasStarted) {
                mPresenter.startTimer();
                timerHasStarted = true;
                mStart.setText("STOP");
            } else {
                mPresenter.stopTimer();
                timerHasStarted = false;
                mStart.setText("RESTART");

            }
        }
    };
}

