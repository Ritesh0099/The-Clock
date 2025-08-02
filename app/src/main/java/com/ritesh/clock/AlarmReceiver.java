package com.ritesh.clock;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
    public static MediaPlayer mediaPlayer;
    @Override public void onReceive(Context c, Intent i) {
        Toast.makeText(c, "Alarm Ringing!", Toast.LENGTH_SHORT).show();
        mediaPlayer = MediaPlayer.create(c, R.raw.funnyalarm);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
        Intent ring = new Intent(c, AlarmRingActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        c.startActivity(ring);
    }
}
