package com.ashpex.portality.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
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
    private int type = 2;
    private int user_id;

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
        SharedPreferences sharedPref = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        user_id = sharedPref.getInt("user_id", -1);
        type = sharedPref.getInt("user_type", 2);
        if(type == 1)
            holder.btnRegister.setVisibility(View.INVISIBLE);
        else
            holder.btnRegister.setVisibility(View.VISIBLE);

        holder.bindData(mlist.get(position));
        holder.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUpCourse(mlist.get(position));
            }
        });
    }

    private void signUpCourse(Course course) {

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
        public SignUpCourseViewHolder(@NonNull View itemView) {
            super(itemView);
            nameCourse_item = itemView.findViewById(R.id.nameCourse_item);
            nameTeacher_item = itemView.findViewById(R.id.nameTeacher_item);
            btnRegister = itemView.findViewById(R.id.btnRegister);
            layout_color = itemView.findViewById(R.id.layout_color);
        }
        public void bindData(Course pos) {
            layout_color.setBackgroundColor(Color.parseColor(pos.getColor()));
            nameCourse_item.setText(pos.getCourse_name());
            nameTeacher_item.setText("Giáo viên: " + pos.getTeacher_name());
            if(pos.getCurr_state()==0)
            btnRegister.setBackgroundResource(R.drawable.ic_unregistered);
            else btnRegister.setBackgroundResource(R.drawable.ic_registered);
        }
    }
}
