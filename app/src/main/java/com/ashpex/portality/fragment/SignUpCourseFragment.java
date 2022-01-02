package com.ashpex.portality.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ashpex.portality.R;
import com.ashpex.portality.SignUpInterface;
import com.ashpex.portality.adapter.SignUpCourseAdapter;
import com.ashpex.portality.adapter.TaskBotAdapter;
import com.ashpex.portality.adapter.TaskTopAdapter;
import com.ashpex.portality.api.ApiService;
import com.ashpex.portality.model.Course;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpCourseFragment extends Fragment implements SignUpInterface {
    private RecyclerView ryc_sign_up;
    private Context context;
    private List<Course> list;
    private List<Integer> listSigned;
    private SignUpCourseAdapter signUpCourseAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        mappingControls(view);
        setUpData(view);
        addEvents();

        return view;
    }

    private void setUpData(View view) {
        context = view.getContext();
        list = new ArrayList<>();
    }

    private void addEvents() {
        getAvailableCourse();
    }

    private void getAvailableCourse() {
        ApiService.apiService.getAvailableCourse(0).enqueue(new Callback<List<Course>>() {
            @Override
            public void onResponse(Call<List<Course>> call, Response<List<Course>> response) {
                Log.d("ALo", String.valueOf(response.code()));
                if(response.code()==200) {
                    list.addAll(response.body());
                    rycSetUp();
                }
            }

            @Override
            public void onFailure(Call<List<Course>> call, Throwable t) {

            }
        });
    }

    private void rycSetUp() {
        ryc_sign_up.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        signUpCourseAdapter = new SignUpCourseAdapter(list);
        signUpCourseAdapter.setContext(context);
        ryc_sign_up.setAdapter(signUpCourseAdapter);
        ryc_sign_up.setHasFixedSize(true);
    }

    private void mappingControls(View view) {

        ryc_sign_up = view.findViewById(R.id.ryc_sign_up);
    }

    @Override
    public void addList(Integer courseId) {
        listSigned.add(courseId);
    }

    @Override
    public void removeList(Integer courseId) {
        listSigned.remove(courseId);
    }
}
