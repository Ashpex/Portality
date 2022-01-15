package com.ashpex.portality.adapter;

import android.content.Context;
import android.content.Intent;
<<<<<<< HEAD
=======
import android.graphics.Color;
>>>>>>> refs/remotes/origin/master
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.ashpex.portality.MainScreen;
import com.ashpex.portality.R;
import com.ashpex.portality.model.UserCourseOnStudying;

import java.util.List;

public class UserCourseOnStudyingAdapter extends RecyclerView.Adapter<UserCourseOnStudyingAdapter.UserCourseViewHolder>{
    private List<UserCourseOnStudying> mlist;

    public void notifyData() {
        notifyDataSetChanged();
    }
    public void setList(List<UserCourseOnStudying> list) {
        mlist = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public UserCourseOnStudyingAdapter.UserCourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new UserCourseOnStudyingAdapter.UserCourseViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_course_on_studying, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserCourseOnStudyingAdapter.UserCourseViewHolder holder, int position) {
        holder.bindData(mlist.get(position));

        holder.layout_color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.context, MainScreen.class);
                intent.putExtra("course_id", mlist.get(position).getCourse_id());
                holder.context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public static class UserCourseViewHolder extends RecyclerView.ViewHolder{

        private final TextView nameCourse;
        private final TextView timeCourse;
        private final TextView nameTeacher;
        private final LinearLayout layout_color;
        private final Context context;
        public UserCourseViewHolder(@NonNull View itemView) {
            super(itemView);
            nameCourse = itemView.findViewById(R.id.nameCourse);
            timeCourse = itemView.findViewById(R.id.timeCourse);
            nameTeacher = itemView.findViewById(R.id.nameTeacher);
            layout_color = itemView.findViewById(R.id.layout_color);
            context = itemView.getContext();
        }
        public void bindData(UserCourseOnStudying pos) {
            nameCourse.setText(pos.getCourse_name());
            timeCourse.setText("Thời gian: "+ pos.getTime_start().substring(0,5)+ " - " + pos.getTime_end().substring(0,5));
            nameTeacher.setText("Giáo viên: " + pos.getTeacher_name());
            layout_color.getBackground().setTint(Color.parseColor(pos.getColor()));
        }
    }
}