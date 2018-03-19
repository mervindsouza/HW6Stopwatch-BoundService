package com.example.mdsouza5.hw6stopwatch;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.widget.Chronometer;

/**
 * Created by merv on 3/18/18.
 */

public class StopwatchService extends Service {

    protected Chronometer stopwatchChronometer;
    protected IBinder iBinder = new StopWatchBinder();

    public class StopWatchBinder extends Binder{
        StopwatchService getServiceFromStopwatchService(){
            return StopwatchService.this;
        }
    }

    //Chronometer Methods

    protected void StopChronomter(){
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
}
