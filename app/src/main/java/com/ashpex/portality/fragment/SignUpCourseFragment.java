package com.ashpex.portality.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

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
    private ImageView btnAddNew;
    private Context context;
    private List<Course> list;
    private List<Integer> listSigned;
    private int type;
    private String token;
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
        SharedPreferences sharedPref = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        type = sharedPref.getInt("user_type", 2);
        token = sharedPref.getString("token","null");
    }

    private void addEvents() {
        getAvailableCourse();
    }

    private void getAvailableCourse() {
        if(type == 2)
            btnAddNew.setVisibility(View.INVISIBLE);
        else
            btnAddNew.setVisibility(View.VISIBLE);
        ApiService.apiService.getAvailableCourse(0).enqueue(new Callback<List<Course>>() {
            @Override
            public void onResponse(Call<List<Course>> call, Response<List<Course>> response) {
                if(response.code()==200) {
                    list.addAll(response.body());
                    rycSetUp();
                }
                else {
                    if(response.message() != null)
<<<<<<< HEAD
                        Toast.makeText(getContext(), response.message(), Toast.LENGTH_SHORT).show();
=======
                    Toast.makeText(getContext(), response.message(), Toast.LENGTH_SHORT).show();
>>>>>>> refs/remotes/origin/master
                    else
                        Toast.makeText(getContext(), "Lỗi server", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Course>> call, Throwable t) {
                Toast.makeText(getContext(), "Lỗi server", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void rycSetUp() {
        ryc_sign_up.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        signUpCourseAdapter = new SignUpCourseAdapter(list);
        signUpCourseAdapter.setContext(context);
        signUpCourseAdapter.setToken(token);
        ryc_sign_up.setAdapter(signUpCourseAdapter);
        ryc_sign_up.setHasFixedSize(true);
    }

    private void mappingControls(View view) {
        btnAddNew = view.findViewById(R.id.btnAddNew);
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