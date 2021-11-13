package com.ashpex.portality.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ashpex.portality.R;
import com.ashpex.portality.model.Course;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder>{
    private  List<Course> mlist;

    public void notifyData() {
        notifyDataSetChanged();
    }
    public void setList(List<Course> list) {
        mlist = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public CourseAdapter.CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new CourseViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_course_sign_up, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        holder.bindData(mlist.get(position));
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public static class CourseViewHolder extends RecyclerView.ViewHolder{

        private final TextView nameCourse_item;
        private final TextView nameTeacher_item;
        private final ImageButton btnRegister;
        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            nameCourse_item = itemView.findViewById(R.id.nameCourse_item);
            nameTeacher_item = itemView.findViewById(R.id.nameTeacher_item);
            btnRegister = itemView.findViewById(R.id.btnRegister);
        }
        public void bindData(Course pos) {
            nameCourse_item.setText(pos.getCourse_name());
            nameTeacher_item.setText(String.valueOf(pos.getSubject_id()));
            btnRegister.setBackgroundResource(R.drawable.ic_finished);
        }
    }
}