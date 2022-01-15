package com.ashpex.portality;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

public class CreateCourseActivity extends AppCompatActivity {
    private ImageButton btnBack_course;
    private EditText txtNameCourse;
    private EditText txtTime;
    private EditText txtDay_of_wwek;
    private EditText txtTimeStart;
    private EditText txtTimeEnd;
    private EditText txtDes;
    private EditText txtLink;
    private EditText txtFee;
    private EditText txtRequire;
    private ImageButton btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_course);
        
        mappingControls();
    }

    private void mappingControls() {
    }
}