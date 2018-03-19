package com.example.mdsouza5.hw6stopwatch;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.widget.Chronometer;
import android.widget.Toast;

import java.text.MessageFormat;

/**
 * Created by merv on 3/18/18.
 */

public class StopwatchService extends Service {

    protected Chronometer stopwatchChronometer;
    protected IBinder iBinder = new StopWatchBinder();

    public class StopWatchBinder extends Binder {
        StopwatchService getServiceFromStopwatchService() {
            return StopwatchService.this;
        }
    }

    //Chronometer Methods

    protected void StopChronomter() {
        stopwatchChronometer.stop();
    }

    //Binder Methods

    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    //StopwatchService Methods

    @Override
    public void onCreate() {
        super.onCreate();
        stopwatchChronometer = new Chronometer(this);
        stopwatchChronometer.setBase(SystemClock.elapsedRealtime());
        stopwatchChronometer.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopwatchChronometer.stop();
    }

    //Calculate Timer value

    protected String CalculatedTimerValue(long defaultMilisecTime, long waitTime, long updatedTime, long initialTime) {

        defaultMilisecTime = SystemClock.uptimeMillis() - initialTime;
        updatedTime = waitTime + defaultMilisecTime;
        int seconds = (int) (updatedTime / 1000);
        int minutes = seconds / 60;
        seconds = seconds % 60;
        int milliseconds = (int) (updatedTime % 1000);

        return String.format(minutes + " : " + "%02d" + " : " + "%03d", seconds, milliseconds);
    }
}
