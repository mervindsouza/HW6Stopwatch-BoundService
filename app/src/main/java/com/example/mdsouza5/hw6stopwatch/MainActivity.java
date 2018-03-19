package com.example.mdsouza5.hw6stopwatch;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivityContext = this.getBaseContext(); // may substitute with this
        boundServiceHandler = new Handler();
        startServicesButton = (Button) findViewById(R.id.startServicesMA);
        stopServicesButton = (Button) findViewById(R.id.stopServicesMA);
        startTimerButton = (Button) findViewById(R.id.startButtonMA);
        stopTimerButton = (Button) findViewById(R.id.stopButtonMA);
        resetTimerButton = (Button) findViewById(R.id.resetButtonMA);
        showTimerTextView = (TextView) findViewById(R.id.showTimerMA);

        ServiceConnection serviceConnectionForBinding = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        }

        startServicesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Declare Intents
                Intent intentForStopwatchService = new Intent(mainActivityContext.getApplicationContext(), StopwatchService.class);
                try {
                    startService(intentForStopwatchService);
                    bindService(intentForStopwatchService, serviceConnectionForBinding, )
                }catch (SecurityException secEx){
                    Toast.makeText(getApplicationContext(), "Security Exception Occured!", Toast.LENGTH_LONG).show();
                }catch(IllegalStateException illEx){
                    Toast.makeText(getApplicationContext(), "Illegal State Exception Occured!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}