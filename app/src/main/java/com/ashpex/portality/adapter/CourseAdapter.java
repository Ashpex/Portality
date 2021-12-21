package com.ashpex.portality.adapter;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ashpex.portality.R;
import com.ashpex.portality.model.CourseSigned;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder>{
    private  List<CourseSigned> mlist;

    public void notifyData() {
        notifyDataSetChanged();
    }
    public void setList(List<CourseSigned> list) {
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
        private final LinearLayout layout_color;
        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            nameCourse_item = itemView.findViewById(R.id.nameCourse_item);
            nameTeacher_item = itemView.findViewById(R.id.nameTeacher_item);
            btnRegister = itemView.findViewById(R.id.btnRegister);
            layout_color = itemView.findViewById(R.id.layout_color);
        }
        public void bindData(CourseSigned pos) {
            nameCourse_item.setText(pos.getCourse_name());
            nameTeacher_item.setText("Giáo viên: " + pos.getTeacher_name());
            if(pos.getCurr_state() == 2)
            btnRegister.setBackgroundResource(R.drawable.ic_finished);
            else
                btnRegister.setBackgroundResource(R.drawable.ic_unfinished);

            layout_color.setBackgroundColor(Color.parseColor(pos.getColor()));
        }
    }
}
