package com.example.mdsouza5.hw6stopwatch;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mdsouza5.hw6stopwatch.StopwatchService.StopWatchBinder;

public class MainActivity extends AppCompatActivity {

    Context mainActivityContext;
    Handler boundServiceHandler;
    StopwatchService stopwatchServiceObj;
    Button startServicesButton;
    Button stopServicesButton;
    Button startTimerButton;
    Button stopTimerButton;
    Button resetTimerButton;
    TextView showTimerTextView;
    Boolean isServiceBound;
    Boolean isServiceBoundTag;
    long defaultMilisecTime, waitTime, updatedTime, initialTime = 0L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivityContext = this; // may substitute with this
        boundServiceHandler = new Handler();
        isServiceBound = false;
        isServiceBoundTag = false;
        startServicesButton = (Button) findViewById(R.id.startServicesMA);
        stopServicesButton = (Button) findViewById(R.id.stopServicesMA);
        startTimerButton = (Button) findViewById(R.id.startButtonMA);
        stopTimerButton = (Button) findViewById(R.id.stopButtonMA);
        resetTimerButton = (Button) findViewById(R.id.resetButtonMA);
        showTimerTextView = (TextView) findViewById(R.id.showTimerMA);

        //Create Service Connection For Binding

        final ServiceConnection serviceConnectionForBinding = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                StopWatchBinder stopWatchBinder = (StopWatchBinder) iBinder;
                stopwatchServiceObj = stopWatchBinder.getServiceFromStopwatchService();
                isServiceBound = true;
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                isServiceBound = false;
            }
        };

        // Create Runnable Instance and Override Methods
        final Runnable runnableInstanceForStopWatch = new Runnable() {
            @Override
            public void run() {
                showTimerTextView.setText(stopwatchServiceObj.CalculatedTimerValue(defaultMilisecTime, waitTime, updatedTime, initialTime));
                boundServiceHandler.postDelayed(this, 0);
            }
        };

        // Start and Stop Services Functions

        startServicesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentForStopwatchService = new Intent(mainActivityContext.getApplicationContext(), StopwatchService.class);
                startService(intentForStopwatchService);
                bindService(intentForStopwatchService, serviceConnectionForBinding, BIND_AUTO_CREATE);
            }
        });

        stopServicesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boundServiceHandler.removeCallbacks(runnableInstanceForStopWatch);
                unbindService(serviceConnectionForBinding);
            }
        });

        // Start-Stop-Reset Button Activities
        startTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initialTime = SystemClock.uptimeMillis();
                boundServiceHandler.postDelayed(runnableInstanceForStopWatch, 0);
            }
        });

        stopTimerButton.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
                waitTime = waitTime + defaultMilisecTime;
                boundServiceHandler.removeCallbacks(runnableInstanceForStopWatch);
            }
        });

        resetTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                defaultMilisecTime = 0L;
                waitTime = 0L;
                updatedTime = 0L;
                initialTime = 0L;
                boundServiceHandler.removeCallbacks(runnableInstanceForStopWatch);
                showTimerTextView.setText("00:00:000");
            }
        });
    }
}