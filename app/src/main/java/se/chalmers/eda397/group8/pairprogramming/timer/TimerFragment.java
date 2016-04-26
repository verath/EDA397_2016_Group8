package se.chalmers.eda397.group8.pairprogramming.timer;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import group8.eda397.chalmers.se.pairprogramming.R;

/**
 * Created by Vidar on 14/04/16.
 */
public class TimerFragment extends Fragment implements TimerContract.View, TimerService.Listener {

    private TimerContract.Presenter mPresenter;
    private TimerService mTimerService;

    private TextView mTimerTime;
    private NumberPicker mMinutePicker;
    private Button mStartStopButton;

    public static TimerFragment newInstance() {
        return new TimerFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timer, container, false);
        mTimerTime = (TextView) view.findViewById(R.id.timer_fragment_timer);

        mStartStopButton = (Button) view.findViewById(R.id.start_stop_btn_timer);
        mStartStopButton.setOnClickListener(mOnStartStopButtonClick);

        mMinutePicker = (NumberPicker) view.findViewById(R.id.minute_picker);
        mMinutePicker.setMinValue(1);
        mMinutePicker.setMaxValue(60);
        mMinutePicker.setWrapSelectorWheel(false);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        // Explicitly start the TimerService, so that it is not destroyed
        // when we unbind on onPause.
        Intent timerServiceIntent = TimerService.getStartingIntent(getContext());
        getActivity().startService(timerServiceIntent);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
        // Bind to the TimerService
        Intent timerServiceIntent = TimerService.getStartingIntent(getContext());
        getActivity().bindService(timerServiceIntent, mTimerServiceConnection,
                Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mTimerService != null) {
            // Unbind from the TimerService
            mTimerService.setListener(null);
            getActivity().unbindService(mTimerServiceConnection);
            mTimerService = null;
        }
    }

    @Override
    public void displayRemainingTime(long millisUntilFinished) {
        long minutes = millisUntilFinished / (60 * 1000);
        long seconds = (millisUntilFinished / 1000) % 60;
        String parsedTime = String.format("%02d:%02d", minutes, seconds);
        if (millisUntilFinished < 30000) {
            mTimerTime.setTextColor(Color.parseColor("#ff0000"));
        } else {
            mTimerTime.setTextColor(Color.parseColor("#616161"));
        }

        mTimerTime.setText(parsedTime);
    }

    @Override
    public void displayFinished() {
        mTimerTime.setText("Switch");
    }

    @Override
    public void disableTimerInput() {
        mStartStopButton.setEnabled(false);
        mMinutePicker.setEnabled(false);
    }

    @Override
    public void enableTimerInput() {
        mStartStopButton.setEnabled(true);
        mMinutePicker.setEnabled(true);
    }

    @Override
    public void startTimer(long millisInFuture) {
        if (mTimerService != null) {
            mTimerService.startTimer(millisInFuture);
        }
    }

    @Override
    public void stopTimer() {
        if (mTimerService != null) {
            mTimerService.cancelTimer();
        }
    }

    @Override
    public void showStartButton() {
        mStartStopButton.setText("START");
    }

    @Override
    public void showStopButton() {
        mStartStopButton.setText("STOP");
    }

    @Override
    public void setPresenter(@NonNull TimerContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onTimerTick(long millisUntilFinished) {
        mPresenter.onTimerTick(millisUntilFinished);
    }

    @Override
    public void onTimerFinish() {
        mPresenter.onTimerFinish();
    }

    private final View.OnClickListener mOnStartStopButtonClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            int minutes = mMinutePicker.getValue();
            long millisInFuture = minutes * 60 * 1000;
            mPresenter.onStartStopButtonClick(millisInFuture);
        }
    };

    private final ServiceConnection mTimerServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // This is method is called when we have successfully bound to the TimerService.
            // We check isResumed here, to make sure the listener we attach here will be
            // removed in onPause.
            if (isResumed()) {
                mTimerService = ((TimerService.TimerServiceBinder) service).getTimerService();
                int timerState = mTimerService.getTimerState();
                long millisUntilFinished = mTimerService.getMillisUntilFinished();
                mPresenter.onTimerServiceConnected(timerState, millisUntilFinished);
                mTimerService.setListener(TimerFragment.this);
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mTimerService = null;
        }
    };

}

