package com.ashpex.portality.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ashpex.portality.R;
import com.ashpex.portality.api.ApiService;
import com.ashpex.portality.model.Course;
import com.ashpex.portality.model.ErrorMessage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoCourseFragment extends Fragment {
    private int course_id;
    private TextView txtNameCourse;
    private TextView txtNameTeacher;
    private TextView bntInfo;
    private TextView btnDiscuss;
    private TextView txtTimeStart;
    private TextView time;
    private TextView txtDes;
    private TextView txtLinkZoom;
    private TextView txtFee;
    private TextView txtRequire;
    private TextView txtTimeEnd;
    private View view;
    private Course data;
    public InfoCourseFragment(int course_id) {
        this.course_id = course_id;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_info_course, container, false);

        mappingControls();
        addEvents();
        return view;
    }

    private void addEvents() {
        ApiService.apiService.getInfoCourse(course_id).enqueue(new Callback<Course>() {
            @Override
            public void onResponse(Call<Course> call, Response<Course> response) {
                if(response.code()==200){
                    data = response.body();
                    printToast("Lấy dữ liệu thành công");
                    setData();
                }
            }

            @Override
            public void onFailure(Call<Course> call, Throwable t) {
                printToast("Đã có lỗi xảy ra");
            }
        });
    }

    private void setData() {
        txtNameCourse.setText(data.getCourse_name());
        txtFee.setText(data.getFee());
        txtDes.setText(data.getDescription());
        txtLinkZoom.setText(data.getRoom());
        txtRequire.setText(data.getRequirement());
        txtNameTeacher.setText(data.getTeacher_name());
        time.setText(data.getTime_start().substring(0, 5) + " - " + data.getTime_end().substring(0, 5));
        txtTimeStart.setText(data.getDay_start().substring(0, 10));
        txtTimeEnd.setText(data.getDay_end().substring(0, 10));
    }

    private void printToast(String mess) {
        Toast.makeText(getContext(), mess, Toast.LENGTH_SHORT).show();
    }
    private void mappingControls() {
        txtNameCourse = view.findViewById(R.id.txtNameCourse);
        txtNameTeacher = view.findViewById(R.id.txtNameTeacher);
        bntInfo = view.findViewById(R.id.bntInfo);
        btnDiscuss = view.findViewById(R.id.btnDiscuss);
        txtTimeStart = view.findViewById(R.id.txtTimeStart);
        time = view.findViewById(R.id.time);
        txtDes = view.findViewById(R.id.txtDes);
        txtNameCourse = view.findViewById(R.id.txtNameCourse);
        txtLinkZoom = view.findViewById(R.id.txtLinkZoom);
        txtFee = view.findViewById(R.id.txtFee);
        txtRequire = view.findViewById(R.id.txtRequire);
        txtTimeEnd = view.findViewById(R.id.txtTimeEnd);
    }
}
