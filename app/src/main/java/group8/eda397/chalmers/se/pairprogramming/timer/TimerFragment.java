package group8.eda397.chalmers.se.pairprogramming.timer;

import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.widget.EditText;

import group8.eda397.chalmers.se.pairprogramming.R;

/**
 * Created by Vidar on 14/04/16.
 */
public class TimerFragment extends Fragment implements TimerContract.View {

    @Override
    public void showTimer() {
        final EditText mTextField  = (EditText)(R.id.mTextField);

        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                assert mTextField != null;
                mTextField.setText(String.valueOf(millisUntilFinished / 1000));
            }

            public void onFinish() {
                assert mTextField != null;
                mTextField.setText("done!");
            }
        }.start();

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
    public void setPresenter(TimerContract.Presenter presenter) {

    }
}
