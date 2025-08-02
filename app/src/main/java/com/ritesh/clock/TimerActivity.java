package com.ritesh.clock;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class TimerActivity extends AppCompatActivity {
    private EditText in;
    private TextView display;
    private Button start, stop;
    private CountDownTimer timer;
    private boolean running;
    @Override protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_timer);
        in = findViewById(R.id.inputMinutes);
        display = findViewById(R.id.countdownText);
        start = findViewById(R.id.startTimerButton);
        stop = findViewById(R.id.stopTimerButton);
        start.setOnClickListener(v -> {
            if (running) { Toast.makeText(this, "Already running", Toast.LENGTH_SHORT).show(); return; }
            String t = in.getText().toString().trim();
            if (t.isEmpty()) { Toast.makeText(this, "Enter minutes", Toast.LENGTH_SHORT).show(); return; }
            long ms = Long.parseLong(t) * 60000;
            timer = new CountDownTimer(ms, 1000) {
                public void onTick(long m) {
                    long mns = m / 60000;
                    long secs = (m % 60000) / 1000;
                    display.setText(String.format("%02d:%02d", mns, secs));
                }
                public void onFinish() {
                    display.setText("00:00");
                    Toast.makeText(TimerActivity.this, "Time's up!", Toast.LENGTH_SHORT).show();
                    running = false;
                }
            }.start();
            running = true;
        });
        stop.setOnClickListener(v -> {
            if (timer != null) {
                timer.cancel();
                display.setText("00:00");
                running = false;
            }
        });
    }
}
