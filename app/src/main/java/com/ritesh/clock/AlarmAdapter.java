package com.ritesh.clock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.VH> {
    private final List<Alarm> list;
    public AlarmAdapter(List<Alarm> list) { this.list = list; }
    @NonNull @Override public VH onCreateViewHolder(@NonNull ViewGroup p, int vt) {
        return new VH(LayoutInflater.from(p.getContext()).inflate(android.R.layout.simple_list_item_1, p, false));
    }
    @Override public void onBindViewHolder(@NonNull VH h, int pos) { h.tv.setText(list.get(pos).getFormattedTime()); }
    @Override public int getItemCount() { return list.size(); }
    static class VH extends RecyclerView.ViewHolder {
        final TextView tv;
        VH(View v) { super(v); tv = v.findViewById(android.R.id.text1); }
    }
}
