package com.ashpex.portality;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ashpex.portality.adapter.UserCourseSignedAdapter;
import com.ashpex.portality.api.ApiService;
import com.ashpex.portality.model.Course;
import com.ashpex.portality.model.CourseSigned;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchResultActivity extends AppCompatActivity {
    private String textSearch = "null";
    private RecyclerView ryc_course_activity;
    private ImageButton btnBack_course;
    private UserCourseSignedAdapter userCourseSignedAdapter;
    private List<CourseSigned> listCourse;
    private String token ;
    private int userId ;
    private int type;
    SharedPreferences sharedPref ;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        setUpdata();
        mappingControls();
        actionSearch();
        events();
    }

    private void events() {
        btnBack_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setUpdata() {
        intent = getIntent();
        textSearch = intent.getStringExtra("text_search");
        sharedPref = this.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        token = sharedPref.getString("token", "null");
        userId = sharedPref.getInt("user_id", 0);
        type = sharedPref.getInt("user_type", 0);
        userCourseSignedAdapter = new UserCourseSignedAdapter();
    }
    private void actionSearch() {
        ApiService.apiService.search(textSearch, 1).enqueue(new Callback<List<CourseSigned>>() {
            @Override
            public void onResponse(Call<List<CourseSigned>> call, Response<List<CourseSigned>> response) {
                if(response.code()==200){
                    if(response.body().size() ==0)
                        Toast.makeText(getApplicationContext(), "Danh sách trống", Toast.LENGTH_SHORT).show();
                    else {
                        setViewRyc(response.body(), 1);
                    }
                }
                else
                {
                    if(response.message() !=null) {
                        Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(getApplicationContext(), "Lỗi server", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<CourseSigned>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Lỗi server", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setViewRyc(List<CourseSigned> body, int state) {
        listCourse = body;
        userCourseSignedAdapter.setList(listCourse);
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
    }
}