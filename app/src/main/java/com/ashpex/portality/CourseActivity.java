package com.ashpex.portality;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ashpex.portality.adapter.UserCourseSignedAdapter;
import com.ashpex.portality.api.ApiService;
import com.ashpex.portality.model.CourseSigned;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseActivity extends AppCompatActivity {
    private RecyclerView ryc_course_activity;
    private ImageButton btnBack_course;
    private TextView txtView;
    private ImageButton btnViewAll;
    private UserCourseSignedAdapter userCourseSignedAdapter;
    private List<CourseSigned> listCourseSigned;
    private String userName ;
    private String token ;
    private int userId ;
    private int type;
    private int cur_state =0;
    SharedPreferences sharedPref ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        mappingControls();
        addData();
        addEvents();

    }

    private void addData() {
        sharedPref = this.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        userName = sharedPref.getString("user_name", "null");
        token = sharedPref.getString("token", "null");
        userId = sharedPref.getInt("user_id", 0);
        type = sharedPref.getInt("user_type", 0);
    }

    private void addEvents() {

        callApiGetDataCourse();

        btnBack_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnViewAll.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(cur_state==0)
                    viewAllAction();
                else
                    callApiGetDataCourse();
                cur_state = 1 - cur_state;
            }
        });
    }

    private void viewAllAction() {
        ProgressDialog mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading, please wait...");
        mProgressDialog.show();
        txtView.setText("Tất cả khóa học");
        ApiService.apiService.getAllCourse(0).enqueue(new Callback<List<CourseSigned>>() {
            @Override
            public void onResponse(Call<List<CourseSigned>> call, Response<List<CourseSigned>> response) {
                if(response.code()==200) {
                    setViewRyc(response.body(), 1);
                }
                else
                    Toast.makeText(getApplicationContext(),"Đã xảy ra lỗi", Toast.LENGTH_SHORT).show();
                mProgressDialog.cancel();
            }

            @Override
            public void onFailure(Call<List<CourseSigned>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Đã xảy ra lỗi", Toast.LENGTH_SHORT).show();
                mProgressDialog.cancel();
            }
        });
    }


    private void callApiGetDataCourse() {
        txtView.setText("Đã đăng ký");
        ProgressDialog mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading, please wait...");
        mProgressDialog.show();

        ApiService.apiService.getAllUserCourseSigned(userId, token).enqueue(new Callback<List<CourseSigned>>() {
            @Override
            public void onResponse(Call<List<CourseSigned>> call, Response<List<CourseSigned>> response) {
                if(response.code()==200) {
                    setViewRyc(response.body(), 0);
                }
                else
                    Toast.makeText(getApplicationContext(),"Đã xảy ra lỗi", Toast.LENGTH_SHORT).show();
                mProgressDialog.cancel();
            }
            @Override
            public void onFailure(Call<List<CourseSigned>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Đã xảy ra lỗi", Toast.LENGTH_SHORT).show();
                mProgressDialog.cancel();
            }
        });
    }

    private void setViewRyc(List<CourseSigned> body, int state) {
        listCourseSigned = body;
        userCourseSignedAdapter.setList(listCourseSigned);
        userCourseSignedAdapter.setState(state);
        userCourseSignedAdapter.setUserId(userId);
        userCourseSignedAdapter.setToken(token);
        userCourseSignedAdapter.setType(type);
        ryc_course_activity.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        ryc_course_activity.setAdapter(userCourseSignedAdapter);
    }

    private void mappingControls() {
        ryc_course_activity = findViewById(R.id.ryc_course_activity);
        btnBack_course = findViewById(R.id.btnBack_course);
        txtView = findViewById(R.id.txtView);
        btnViewAll = findViewById(R.id.btnViewAll);
        userCourseSignedAdapter = new UserCourseSignedAdapter();
    }

    @Override
    protected void onStart() {
        super.onStart();
        userCourseSignedAdapter.setList(listCourseSigned);
    }
}