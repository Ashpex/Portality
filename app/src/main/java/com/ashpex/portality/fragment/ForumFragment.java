package com.ashpex.portality.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ashpex.portality.CourseActivity;
import com.ashpex.portality.R;
import com.ashpex.portality.adapter.UserCourseForumAdapter;
import com.ashpex.portality.api.ApiService;
import com.ashpex.portality.model.UserCourseForum;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForumFragment extends Fragment {
    private LinearLayout layout_course_forum;
    private TextView txtNameUser_forum;
    private LinearLayout layout_Schedule_forum;
    private LinearLayout layout_task_forum;
    private LinearLayout layout_fee_forum;
    private RecyclerView ryc_forum;
    private View view;
    private String userName;
    private String token;
    private Integer userId;
    private UserCourseForumAdapter userCourseAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_forum, container, false);

        mappingControls();
        getData();
        addEvents();


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        txtNameUser_forum.setText(userName);
    }

    private void getData() {
        SharedPreferences sharedPref = view.getContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        userName = sharedPref.getString("user_name", "null");
        token = sharedPref.getString("token", "null");
        userId = sharedPref.getInt("user_id", 0);

        ApiService.apiService.getUserCourse(userId, token).enqueue(new Callback<List<UserCourseForum>>() {
            @Override
            public void onResponse(Call<List<UserCourseForum>> call, Response<List<UserCourseForum>> response) {
                if(response.code() == 200) {
                    List<UserCourseForum> mlist = response.body();

                    userCourseAdapter.setList(mlist);
                    ryc_forum.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                    ryc_forum.setAdapter(userCourseAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<UserCourseForum>> call, Throwable t) {

            }
        });
    }

    private void addEvents() {
        layout_course_forum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CourseActivity.class);
                startActivity(intent);
            }
        });


    }

    private void mappingControls() {
        layout_course_forum = view.findViewById(R.id.layout_course_forum);
        txtNameUser_forum = view.findViewById(R.id.txtNameUser_forum);
        layout_Schedule_forum = view.findViewById(R.id.layout_Schedule_forum);
        layout_task_forum = view.findViewById(R.id.layout_task_forum);
        layout_fee_forum = view.findViewById(R.id.layout_fee_forum);
        ryc_forum = view.findViewById(R.id.ryc_forum);

        userCourseAdapter = new UserCourseForumAdapter();
    }

}
