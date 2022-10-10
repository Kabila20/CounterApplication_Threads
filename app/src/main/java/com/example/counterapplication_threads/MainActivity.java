package com.example.counterapplication_threads;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.counterapplication_threads.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "thread";
    Button startBtn, stopBtn;
    TextView counterApp;
    Handler mainHandler = new Handler();
    int count;
    boolean running = false;

    void startThread() {
        NewThread nObj = new NewThread();
        nObj.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        counterApp = findViewById(R.id.textView);
        startBtn = findViewById(R.id.button);
        stopBtn = findViewById(R.id.button2);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = 0;
                running = true;
                startThread();
            }
        });
        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                running = false;
            }
        });

    }
    class NewThread extends Thread {
        @Override
        public void run() {
            while (running) {
                count++;
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        counterApp.setText(String.valueOf(count));
                    }
                });


                try {
                    Thread.sleep(2000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
