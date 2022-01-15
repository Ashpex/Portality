package com.ashpex.portality.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ashpex.portality.R;

import java.util.List;
import java.util.Random;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.RycViewHolder>{
    private final List<String> mlist;


    public NotificationAdapter(List<String> list) {
        mlist = list;
    }
    @NonNull
    @Override
    public NotificationAdapter.RycViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new RycViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_noti, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RycViewHolder holder, int position) {
        holder.bindData(mlist.get(position));
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public static class RycViewHolder extends RecyclerView.ViewHolder{
        private String[] list1 = {"Thông báo học online", "Thông báo nghỉ học ngày 15/7", "Thông báo nghỉ tết dương lịch năm 2022"};
        private String[] list2 = {"Ngày hôm qua", "Ngày 15/7", "Ngày mai"};
        private final TextView content_item_noti;
        private final ImageButton btnCourse_item_noti;
        private final TextView time_item_noti;

        public RycViewHolder(@NonNull View itemView) {
            super(itemView);
            content_item_noti = itemView.findViewById(R.id.content_item_noti);
            btnCourse_item_noti = itemView.findViewById(R.id.btnCourse_item_noti);
            time_item_noti = itemView.findViewById(R.id.time_item_noti);
        }
        public void bindData(String pos) {
            Random generator = new Random();
            int ran = generator.nextInt(3);
            content_item_noti.setText(list1[ran]);

            time_item_noti.setText(list2[ran]);
        }
    }
}
