package com.ritesh.clock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final List<Alarm> alarms = new ArrayList<>();
    private AlarmAdapter adapter;
    private RecyclerView recyclerView;
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.alarmRecyclerView);
        adapter = new AlarmAdapter(alarms);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        findViewById(R.id.fab).setOnClickListener(v -> showTimePicker());

        findViewById(R.id.btnAlarm).setOnClickListener(v -> Toast.makeText(this, "Already on Alarm", Toast.LENGTH_SHORT).show());
        findViewById(R.id.btnWorldClock).setOnClickListener(v -> startActivity(new Intent(this, WorldClockActivity.class)));
        findViewById(R.id.btnStopwatch).setOnClickListener(v -> startActivity(new Intent(this, StopwatchActivity.class)));
        findViewById(R.id.btnTimer).setOnClickListener(v -> startActivity(new Intent(this, TimerActivity.class)));
    }

    private void showTimePicker() {
        Calendar c = Calendar.getInstance();
        new TimePickerDialog(this, (TimePicker view, int hour, int min) -> {
            c.set(Calendar.HOUR_OF_DAY, hour);
            c.set(Calendar.MINUTE, min);
            c.set(Calendar.SECOND, 0);
            scheduleAlarm(c);
        }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), false).show();
    }

    private void scheduleAlarm(Calendar c) {
        alarms.add(new Alarm(c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE)));
        adapter.notifyItemInserted(alarms.size() - 1);

        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(this, (int) System.currentTimeMillis(),
                intent, PendingIntent.FLAG_IMMUTABLE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && !am.canScheduleExactAlarms()) {
            startActivity(new Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM));
            Toast.makeText(this, "Allow exact alarms in settings", Toast.LENGTH_LONG).show();
            return;
        }
        am.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pi);
        Toast.makeText(this, "Alarm set for " + String.format("%02d:%02d", c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE)),
                Toast.LENGTH_LONG).show();
    }
}
