package com.ashpex.portality.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ashpex.portality.R;
import com.ashpex.portality.model.CourseSigned;

import java.util.List;

public class UserCourseSignedAdapter extends RecyclerView.Adapter<UserCourseSignedAdapter.CourseViewHolder>{
    private List<CourseSigned> mlist;
    private int state = 0;

    public void setState(int state) {
        this.state = state;
    }

    public void notifyData() {
        notifyDataSetChanged();
    }
    public void setList(List<CourseSigned> list) {
        mlist = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public UserCourseSignedAdapter.CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new CourseViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_course_sign_up, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        holder.bindData(mlist.get(position));
        if(state == 1) {
            holder.btnRegister.setVisibility(View.INVISIBLE);
        }
        else
            holder.btnRegister.setVisibility(View.INVISIBLE);
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public static class CourseViewHolder extends RecyclerView.ViewHolder{

        private final TextView nameCourse_item;
        private final TextView nameTeacher_item;
        public final ImageButton btnRegister;
        private final LinearLayout layout_color;
        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            nameCourse_item = itemView.findViewById(R.id.nameCourse_item);
            nameTeacher_item = itemView.findViewById(R.id.nameTeacher_item);
            btnRegister = itemView.findViewById(R.id.btnRegister);
            layout_color = itemView.findViewById(R.id.layout_color);
        }
        public void bindData(CourseSigned pos) {
            if(pos.getTeacher_name() == null)
                nameTeacher_item.setText("Ngày bắt đầu: " + pos.getDay_start());
            nameCourse_item.setText(pos.getCourse_name());
            if(pos.getCurr_state() == 2)
                btnRegister.setBackgroundResource(R.drawable.ic_finished);
            else
                btnRegister.setBackgroundResource(R.drawable.ic_unfinished);

            layout_color.setBackgroundColor(Color.parseColor(pos.getColor()));
        }
    }
}