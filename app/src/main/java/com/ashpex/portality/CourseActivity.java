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

import com.ashpex.portality.adapter.CourseAdapter;
import com.ashpex.portality.api.ApiService;
import com.ashpex.portality.model.CourseSigned;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseActivity extends AppCompatActivity {
    private RecyclerView ryc_course_activity;
    private ImageButton btnBack_course;
    private CourseAdapter courseAdapter;
    private List<CourseSigned> listCourseSigned;
    private String userName ;
    private String token ;
    private int userId ;
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
        ProgressDialog mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading, please wait...");
        mProgressDialog.show();

        ApiService.apiService.getAllUserCourseSigned(userId, token).enqueue(new Callback<List<CourseSigned>>() {
            @Override
            public void onResponse(Call<List<CourseSigned>> call, Response<List<CourseSigned>> response) {
                if(response.code()==200) {
                    listCourseSigned = response.body();
                    CountCourseFinished countCourseFinished = new CountCourseFinished();
                    countCourseFinished.execute();

                    courseAdapter.setList(listCourseSigned);
                    ryc_course_activity.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
                    ryc_course_activity.setAdapter(courseAdapter);
                }
                mProgressDialog.cancel();
            }
            @Override
            public void onFailure(Call<List<CourseSigned>> call, Throwable t) {
                mProgressDialog.cancel();
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
        courseAdapter.setList(listCourseSigned);
    }

    class CountCourseFinished extends AsyncTask<Void, Integer, Integer> {


        @Override
        protected Integer doInBackground(Void... voids) {

            int count =0;
            for(CourseSigned i : listCourseSigned) {
                if(i.getCurr_state() == 2)
                    count ++;
            }
            Log.d("alo", String.valueOf(count));
            return count;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt("count_course", integer);
            editor.apply();
        }
    }
}