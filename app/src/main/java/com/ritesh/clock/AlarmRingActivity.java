package com.ritesh.clock;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class AlarmRingActivity extends AppCompatActivity {
    @Override protected void onCreate(Bundle s) {
        super.onCreate(s);
        setContentView(R.layout.activity_alarm_ring);
        Button b = findViewById(R.id.stopAlarmBtn);
        b.setOnClickListener(v -> {
            if (AlarmReceiver.mediaPlayer != null && AlarmReceiver.mediaPlayer.isPlaying()) {
                AlarmReceiver.mediaPlayer.stop();
                AlarmReceiver.mediaPlayer.release();
                AlarmReceiver.mediaPlayer = null;
            }
            finish();
        });
    }
}
