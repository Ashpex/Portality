package com.ashpex.portality.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ashpex.portality.R;
import com.ashpex.portality.api.ApiService;
import com.ashpex.portality.model.Course;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import okhttp3.FormBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpCourseAdapter extends RecyclerView.Adapter<SignUpCourseAdapter.SignUpCourseViewHolder>{
    private final List<Course> mlist;
    private Context context;
    public SignUpCourseAdapter(List<Course> list) {
        mlist = list;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public SignUpCourseAdapter.SignUpCourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new SignUpCourseViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_course_sign_up, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SignUpCourseViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.bindData(mlist.get(position));
        holder.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUpCourse(mlist.get(position));
            }
        });
    }

    private void signUpCourse(Course course) {
        SharedPreferences sharedPref = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        int user_id = sharedPref.getInt("user_id", -1);
        JSONObject body = new JSONObject();
        try {
            body.put("course_name", course.getCourse_name());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        //if(user_id >0)
//        if(course.getCurr_state()==0) {
//            ApiService.apiService.signUpCourseRequestStudent(user_id, body).enqueue(new Callback<Course>() {
//                @Override
//                public void onResponse(Call<Course> call, Response<Course> response) {
//                    Log.d("ALo", String.valueOf(response.code()));
//                    if(response.code()==200) {
//                        Toast.makeText(context, "Đăng ký thành công", Toast.LENGTH_SHORT);
//                    }
//                    else
//                        Toast.makeText(context, "Đăng ký không thành công", Toast.LENGTH_SHORT);
//                }
//
//                @Override
//                public void onFailure(Call<Course> call, Throwable t) {
//                    Toast.makeText(context, "Đăng ký không thành công", Toast.LENGTH_SHORT);
//                }
//            });
//        }
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public static class SignUpCourseViewHolder extends RecyclerView.ViewHolder{

        private final TextView nameCourse_item;
        private final TextView nameTeacher_item;
        public final ImageButton btnRegister;
        public SignUpCourseViewHolder(@NonNull View itemView) {
            super(itemView);
            nameCourse_item = itemView.findViewById(R.id.nameCourse_item);
            nameTeacher_item = itemView.findViewById(R.id.nameTeacher_item);
            btnRegister = itemView.findViewById(R.id.btnRegister);
        }
        public void bindData(Course pos) {
            nameCourse_item.setText(pos.getCourse_name());
            nameTeacher_item.setText("Giáo viên: " + pos.getTeacher_name());
            if(pos.getCurr_state()==0)
            btnRegister.setBackgroundResource(R.drawable.ic_unregistered);
            else btnRegister.setBackgroundResource(R.drawable.ic_registered);
        }
    }
}
