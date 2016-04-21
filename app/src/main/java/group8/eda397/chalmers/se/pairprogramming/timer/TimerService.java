package group8.eda397.chalmers.se.pairprogramming.timer;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class TimerService extends Service {
    public TimerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
