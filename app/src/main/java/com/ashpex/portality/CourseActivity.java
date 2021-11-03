package com.ashpex.portality;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.ashpex.portality.adapter.CourseAdapter;

import java.util.ArrayList;
import java.util.List;

public class CourseActivity extends AppCompatActivity {
    private RecyclerView ryc_course_activity;
    private ImageButton btnBack_course;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        mappingControls();
        List<String> list = new ArrayList<String>();
        list.add("1");list.add("1");list.add("1");list.add("1");list.add("1");list.add("1");list.add("1");list.add("1");list.add("1");list.add("1");
        ryc_course_activity.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        ryc_course_activity.setAdapter(new CourseAdapter(list));

        btnBack_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void mappingControls() {
        ryc_course_activity = findViewById(R.id.ryc_course_activity);
        btnBack_course = findViewById(R.id.btnBack_course);
    }
}