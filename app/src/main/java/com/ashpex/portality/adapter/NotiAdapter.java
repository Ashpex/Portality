package com.ashpex.portality.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ashpex.portality.R;

import java.util.ArrayList;
import java.util.List;

public class NotiAdapter extends RecyclerView.Adapter<NotiAdapter.NotiViewHolder>{
    private final List<String> mlist;
    private final Context mcontext;

    public NotiAdapter(List<String> list, Context context) {
        mlist = list; mcontext = context;
    }
    @NonNull
    @Override
    public NotiAdapter.NotiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new NotiViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ryc_noti, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotiViewHolder holder, int position) {
        holder.bindData(mlist.get(position));
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public static class NotiViewHolder extends RecyclerView.ViewHolder{

        private final TextView time_ryc_noti;
        private final RecyclerView ryc_ryc_noti;
        public NotiViewHolder(@NonNull View itemView) {
            super(itemView);
            time_ryc_noti = itemView.findViewById(R.id.time_ryc_noti);
            ryc_ryc_noti = itemView.findViewById(R.id.ryc_ryc_noti);
        }
        public void bindData(String pos) {
            time_ryc_noti.setText("Thông báo ngày XX");
            List<String> tlist = new ArrayList<String>();
            tlist.add("1");tlist.add("1");tlist.add("1");tlist.add("1");tlist.add("1");

            ryc_ryc_noti.setLayoutManager(new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.VERTICAL, false));
            ryc_ryc_noti.setAdapter(new NotificationAdapter(tlist));
        }
    }
}
