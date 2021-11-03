package com.ashpex.portality.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ashpex.portality.R;

import java.util.ArrayList;
import java.util.List;

public class RycAdapter extends RecyclerView.Adapter<RycAdapter.RycViewHolder>{
    private final List<String> mlist;


    public RycAdapter(List<String> list) {
        mlist = list;
    }
    @NonNull
    @Override
    public RycAdapter.RycViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

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
            content_item_noti.setText("Thông báo đuổi học vĩnh viễn");
            time_item_noti.setText("1 ngày trước");
        }
    }
}
