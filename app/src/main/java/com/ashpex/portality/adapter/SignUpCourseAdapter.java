package com.ashpex.portality.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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
import com.ashpex.portality.model.Course;
import com.ashpex.portality.model.ErrorMessage;
import com.ashpex.portality.model.SubCourseId;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpCourseAdapter extends RecyclerView.Adapter<SignUpCourseAdapter.SignUpCourseViewHolder>{
    private final List<Course> mlist;
    private Context context;
    private int type = 2;
    private int user_id;
    private String token;
    public int state_filter = 0;

    public void setToken(String token) {
        this.token = token;
    }

    public SignUpCourseAdapter(List<Course> list) {
        mlist = list;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setState_filter(int state_filter) {
        this.state_filter = state_filter;
    }

    @NonNull
    @Override
    public SignUpCourseAdapter.SignUpCourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new SignUpCourseViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_course_sign_up, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SignUpCourseViewHolder holder, @SuppressLint("RecyclerView") int position) {
        SharedPreferences sharedPref = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        user_id = sharedPref.getInt("user_id", -1);
        type = sharedPref.getInt("user_type", 2);
        if(type == 1)
            holder.btnRegister.setVisibility(View.INVISIBLE);
        else
            holder.btnRegister.setVisibility(View.VISIBLE);

        if(state_filter == 1) {
            holder.btnRegister.setVisibility(View.VISIBLE);
            holder.btnRegister.setBackgroundResource(R.drawable.ic_cancel_course);
        }
        if(state_filter == 0) {
            if(mlist.get(position).getCurr_state()==0)
                holder.btnRegister.setBackgroundResource(R.drawable.ic_unregistered);
        }

        holder.bindData(mlist.get(position));
        holder.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(state_filter==0)
                    signUpCourse(mlist.get(position));
                else if(state_filter==1)
                    cancelCourse(mlist.get(position));
            }
        });

        holder.layout_color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.context, MainScreen.class);
                intent.putExtra("course_id", mlist.get(position).getCourse_id());
                holder.context.startActivity(intent);
            }
        });
    }

    private void cancelCourse(Course course) {
    }

    private void signUpCourse(Course course) {
        SubCourseId subCourseId = new SubCourseId();
        subCourseId.setCourse_id(course.getCourse_id());
        ApiService.apiService.signUpCourseRequestStudent(user_id, subCourseId, token).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.code()==200)
                    Toast.makeText(context, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                else if(response.code()==400){
                    Gson gson = new GsonBuilder().create();
                    ErrorMessage mError = new ErrorMessage();
                    try {
                        mError= gson.fromJson(response.errorBody().string(),ErrorMessage.class);
                        Toast.makeText(context,mError.getMessage(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        // handle failure to read error
                    }
                }else
                    Toast.makeText(context, "Lỗi server, vui lòng thử lại sau", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context, "Lỗi server, vui lòng thử lại sau", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public static class SignUpCourseViewHolder extends RecyclerView.ViewHolder{

        private final TextView nameCourse_item;
        private final TextView nameTeacher_item;
        public final ImageButton btnRegister;
        private final LinearLayout layout_color;
        private final Context context;
        public SignUpCourseViewHolder(@NonNull View itemView) {
            super(itemView);
            nameCourse_item = itemView.findViewById(R.id.nameCourse_item);
            nameTeacher_item = itemView.findViewById(R.id.nameTeacher_item);
            btnRegister = itemView.findViewById(R.id.btnRegister);
            layout_color = itemView.findViewById(R.id.layout_color);
            context = itemView.getContext();
        }
        public void bindData(Course pos) {
            layout_color.getBackground().setTint(Color.parseColor(pos.getColor()));
            nameCourse_item.setText(pos.getCourse_name());
            if(pos.getTeacher_name() == null)
                nameTeacher_item.setVisibility(View.INVISIBLE);
            else
            nameTeacher_item.setText("Giáo viên: " + pos.getTeacher_name());

        }
    }
}