package com.ritesh.clock;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class StopwatchActivity extends AppCompatActivity {

    private TextView stopwatchTime;
    private Button startBtn, resetBtn;
    private boolean isRunning = false;
    private int seconds = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);

        stopwatchTime = findViewById(R.id.stopwatchTime);
        startBtn = findViewById(R.id.startBtn);
        resetBtn = findViewById(R.id.resetBtn);

        startBtn.setOnClickListener(v -> {
            isRunning = !isRunning;
            if (isRunning) {
                startBtn.setText("Pause");
                runStopwatch();
            } else {
                startBtn.setText("Start");
            }
        });

        resetBtn.setOnClickListener(v -> {
            isRunning = false;
            seconds = 0;
            stopwatchTime.setText("00:00:00");
            startBtn.setText("Start");
        });
    }

    private void runStopwatch() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isRunning) {
                    seconds++;
                    int hrs = seconds / 3600;
                    int mins = (seconds % 3600) / 60;
                    int secs = seconds % 60;

                    stopwatchTime.setText(String.format("%02d:%02d:%02d", hrs, mins, secs));
                    handler.postDelayed(this, 1000);
                }
            }
        }, 1000);
    }
}
