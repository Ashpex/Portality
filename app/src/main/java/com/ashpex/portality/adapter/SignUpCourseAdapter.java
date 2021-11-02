package com.ashpex.portality.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ashpex.portality.R;

import java.util.List;

public class SignUpCourseAdapter extends RecyclerView.Adapter<SignUpCourseAdapter.SignUpCourseViewHolder>{
    @NonNull
    private final List<String> mlist;
    public SignUpCourseAdapter(List<String> list) {
        mlist = list;
    }
    @Override
    public SignUpCourseAdapter.SignUpCourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new SignUpCourseAdapter.SignUpCourseViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_course, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SignUpCourseViewHolder holder, int position) {
        holder.bindData(mlist.get(position));
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class SignUpCourseViewHolder extends RecyclerView.ViewHolder{

        private TextView nameCourse_item;
        private TextView nameTeacher_item;
        private TextView txtSignUp_item;
        public SignUpCourseViewHolder(@NonNull View itemView) {
            super(itemView);
            nameCourse_item = itemView.findViewById(R.id.nameCourse_item);
            nameTeacher_item = itemView.findViewById(R.id.nameTeacher_item);
            txtSignUp_item = itemView.findViewById(R.id.txtSignUp_item);
        }
        public void bindData(String pos) {
            nameCourse_item.setText("Giáo dục công dân");
            nameTeacher_item.setText("Giảng viên: Lee Quan");
            txtSignUp_item.setText("Hủy đăng ký");
        }
    }
}
