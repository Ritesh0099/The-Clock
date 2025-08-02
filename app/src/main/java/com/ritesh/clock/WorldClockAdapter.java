package com.ritesh.clock;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class WorldClockAdapter extends BaseAdapter {

    private final Context context;
    private final String[] timeZones;
    private final LayoutInflater inflater;
    private final SimpleDateFormat timeFormat;

    public WorldClockAdapter(Context context, String[] timeZones) {
        this.context = context;
        this.timeZones = timeZones;
        this.inflater = LayoutInflater.from(context);
        this.timeFormat = new SimpleDateFormat("hh:mm a", Locale.getDefault());
    }

    @Override
    public int getCount() {
        return timeZones.length;
    }

    @Override
    public Object getItem(int position) {
        return timeZones[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_white_text, parent, false);
            holder = new ViewHolder();
            holder.timezoneName = convertView.findViewById(R.id.timezoneName);
            holder.timezoneTime = convertView.findViewById(R.id.timezoneTime);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String tzId = timeZones[position];
        holder.timezoneName.setText(tzId);

        TimeZone tz = TimeZone.getTimeZone(tzId);
        timeFormat.setTimeZone(tz);
        String currentTime = timeFormat.format(new Date());

        holder.timezoneTime.setText(currentTime);

        return convertView;
    }

    static class ViewHolder {
        TextView timezoneName;
        TextView timezoneTime;
    }
}
