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
import com.ashpex.portality.model.UserCourse;

import java.util.List;

public class UserCourseAdapter extends RecyclerView.Adapter<UserCourseAdapter.UserCourseViewHolder>{
    private List<UserCourse> mlist;

    public void notifyData() {
        notifyDataSetChanged();
    }
    public void setList(List<UserCourse> list) {
        mlist = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public UserCourseAdapter.UserCourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new UserCourseAdapter.UserCourseViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_course_sign_up, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserCourseAdapter.UserCourseViewHolder holder, int position) {
        holder.bindData(mlist.get(position));
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public static class UserCourseViewHolder extends RecyclerView.ViewHolder{

        private final TextView nameCourse_item;
        private final TextView nameTeacher_item;
        private final ImageButton btnRegister;
        public UserCourseViewHolder(@NonNull View itemView) {
            super(itemView);
            nameCourse_item = itemView.findViewById(R.id.nameCourse_item);
            nameTeacher_item = itemView.findViewById(R.id.nameTeacher_item);
            btnRegister = itemView.findViewById(R.id.btnRegister);
        }
        public void bindData(UserCourse pos) {
            nameCourse_item.setText(pos.getCourse_name());
            nameTeacher_item.setText(String.valueOf(pos.getSubject_id()));
            btnRegister.setBackgroundResource(R.drawable.ic_finished);
        }
    }
}