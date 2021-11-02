package com.ashpex.portality.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ashpex.portality.R;

import java.util.List;

public class ForumAdapter extends RecyclerView.Adapter<ForumAdapter.ForumViewHolder> {
    @NonNull
    private List<String> mlist;
    public ForumAdapter(List<String> list) {
        mlist = list;
    }
    @Override
    public ForumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ForumViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_course_forum, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ForumViewHolder holder, int position) {
        holder.bindData(mlist.get(position));
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class ForumViewHolder extends RecyclerView.ViewHolder{

        private TextView nameCourse;
        private TextView timeCourse;
        private TextView nameTeacher;
        public ForumViewHolder(@NonNull View itemView) {
            super(itemView);
            nameCourse = itemView.findViewById(R.id.nameCourse);
            timeCourse = itemView.findViewById(R.id.timeCourse);
            nameTeacher = itemView.findViewById(R.id.nameTeacher);
        }
        public void bindData(String pos) {
            nameCourse.setText("Toán tổ hợp");
            timeCourse.setText("Thời gian: 7:30 AM - 11:30 AM");
            nameTeacher.setText("Giảng viên: Lê Pink Quân");
        }
    }
}
