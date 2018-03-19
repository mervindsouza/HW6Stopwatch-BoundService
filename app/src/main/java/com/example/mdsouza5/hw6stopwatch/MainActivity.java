package com.example.mdsouza5.hw6stopwatch;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Context mainActivityContext;
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
        startServicesButton = (Button)findViewById(R.id.startServicesMA);
        stopServicesButton  = (Button)findViewById(R.id.stopServicesMA);
        startTimerButton = (Button)findViewById(R.id.startButtonMA);
        stopTimerButton = (Button)findViewById(R.id.stopButtonMA);
        resetTimerButton = (Button)findViewById(R.id.resetButtonMA);
        showTimerTextView = (TextView)findViewById(R.id.showTimerMA);
    }
}