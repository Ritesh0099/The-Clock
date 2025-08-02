package com.ritesh.clock;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.TimeZone;

public class WorldClockActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world_clock);

        ListView worldClockList = findViewById(R.id.worldClockList);

        // Get all available timezone IDs
        String[] timeZones = TimeZone.getAvailableIDs();

        // Set custom adapter that shows timezone and current time
        WorldClockAdapter adapter = new WorldClockAdapter(this, timeZones);
        worldClockList.setAdapter(adapter);
    }
}
