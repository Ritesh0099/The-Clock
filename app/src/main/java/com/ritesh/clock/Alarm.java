package com.ritesh.clock;
public class Alarm {
    private final int hour, minute;
    public Alarm(int hour, int minute) { this.hour = hour; this.minute = minute; }
    public String getFormattedTime() {
        String am = (hour >= 12) ? "PM" : "AM";
        int h12 = hour % 12; if (h12 == 0) h12 = 12;
        return String.format("%02d:%02d %s", h12, minute, am);
    }
}
