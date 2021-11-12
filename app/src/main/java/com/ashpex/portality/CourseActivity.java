package com.ashpex.portality;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.ashpex.portality.adapter.CourseAdapter;
import com.ashpex.portality.api.ApiService;
import com.ashpex.portality.model.Course;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseActivity extends AppCompatActivity {
    private RecyclerView ryc_course_activity;
    private ImageButton btnBack_course;
    private CourseAdapter courseAdapter;
    private List<Course> listCourse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        mappingControls();
        addEvents();

    }

    private void addEvents() {
        callApiGetDataCourse();

        btnBack_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void callApiGetDataCourse() {
        ApiService.apiService.getCourse(0).enqueue(new Callback<List<Course>>() {
            @Override
            public void onResponse(Call<List<Course>> call, Response<List<Course>> response) {
                if(response.code()==200) {
                    listCourse = response.body();
                    courseAdapter.setList(listCourse);
                    ryc_course_activity.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
                    ryc_course_activity.setAdapter(courseAdapter);
                }
            }
            @Override
            public void onFailure(Call<List<Course>> call, Throwable t) {
            }
        });
    }

    private void mappingControls() {
        ryc_course_activity = findViewById(R.id.ryc_course_activity);
        btnBack_course = findViewById(R.id.btnBack_course);
        courseAdapter = new CourseAdapter();
    }

    @Override
    protected void onStart() {
        super.onStart();
        courseAdapter.setList(listCourse);
    }
}