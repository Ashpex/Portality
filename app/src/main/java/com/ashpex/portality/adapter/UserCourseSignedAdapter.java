package com.ashpex.portality.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ashpex.portality.MainScreen;
import com.ashpex.portality.R;
import com.ashpex.portality.api.ApiService;
import com.ashpex.portality.model.CourseSigned;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserCourseSignedAdapter extends RecyclerView.Adapter<UserCourseSignedAdapter.CourseViewHolder>{
    private List<CourseSigned> mlist;
    private int state = 0;
    private String token =" ";
    private int userId;
    private int type;
    public void setToken(String token) {
        this.token = token;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

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
    public void onBindViewHolder(@NonNull CourseViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.bindData(mlist.get(position));
        if(state==1)
            holder.btnRegister.setVisibility(View.INVISIBLE);
        else
            holder.btnRegister.setVisibility(View.VISIBLE);

        if(mlist.get(position).getCurr_state() == 0 && type ==2)
            holder.btnRegister.setBackgroundResource(R.drawable.ic_registered);
        if(mlist.get(position).getCurr_state() == 0 && type ==1)
            holder.btnRegister.setBackgroundResource(R.drawable.ic_finish_sign_up);
        if(mlist.get(position).getCurr_state()==0 ) {
            holder.btnRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(type == 2)
                        ApiService.apiService.unSignCourse(userId, mlist.get(position).getCourse_id(), token).enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                if(response.code()==200) {
                                    mlist.remove(position);
                                    notifyDataSetChanged();
                                    Toast.makeText(holder.context, "Hủy đăng ký thành công", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    if(response.message()!=null)
                                        Toast.makeText(holder.context, "Hủy đăng ký thành công", Toast.LENGTH_SHORT).show();
                                    else
                                        Toast.makeText(holder.context, "Lỗi server, vui lòng thử lại sau", Toast.LENGTH_SHORT).show();
                                }

                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Toast.makeText(holder.context, "Lỗi server, vui lòng thử lại sau", Toast.LENGTH_SHORT).show();
                            }
                        });

                    if(type==1) {

                    }
                }
            });
        }
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

    public static class CourseViewHolder extends RecyclerView.ViewHolder{

        private final TextView nameCourse_item;
        private final TextView nameTeacher_item;
        public final ImageButton btnRegister;
        private final LinearLayout layout_color;
        public Context context;
        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            nameCourse_item = itemView.findViewById(R.id.nameCourse_item);
            nameTeacher_item = itemView.findViewById(R.id.nameTeacher_item);
            btnRegister = itemView.findViewById(R.id.btnRegister);
            layout_color = itemView.findViewById(R.id.layout_color);
            context = itemView.getContext();
        }
        public void bindData(CourseSigned pos) {
            if(pos.getTeacher_name() == null)
                nameTeacher_item.setText("Ngày bắt đầu: " + pos.getDay_start());
            nameCourse_item.setText(pos.getCourse_name());
            nameTeacher_item.setText(pos.getTeacher_name());
            if(pos.getCurr_state() == 2)
                btnRegister.setBackgroundResource(R.drawable.ic_finished);
            if(pos.getCurr_state() == 1)
                btnRegister.setBackgroundResource(R.drawable.ic_unfinished);

            layout_color.setBackgroundColor(Color.parseColor(pos.getColor()));
        }
    }
}