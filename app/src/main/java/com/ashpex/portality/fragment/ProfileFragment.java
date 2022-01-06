package com.ashpex.portality.fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ashpex.portality.R;
import com.ashpex.portality.api.ApiService;
import com.ashpex.portality.model.CourseSigned;
import com.ashpex.portality.model.InfoUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileFragment extends Fragment {
    private InfoUser infoUser;
    private View view;
    private TextView txtName;
    private TextView txtCountCourse;
    private ImageButton btnEditProfile;
    private TextView email_profile;
    private TextView sex_profile;
    private TextView dob_profile;
    private TextView address_profile;

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        getData();
        mappingControls();
        addEvents();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        addEvents();
    }

    private void getData() {
        SharedPreferences sharedPref = view.getContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        infoUser = new InfoUser();
        infoUser.setType(sharedPref.getInt("user_type", -1));
        infoUser.setUser_name(sharedPref.getString("user_name", "null"));
        infoUser.set_id(sharedPref.getInt("user_id", -1));
        infoUser.setGender(sharedPref.getString("user_gender", "null"));
        infoUser.setEmail(sharedPref.getString("user_email", "null"));
        infoUser.setAddress(sharedPref.getString("user_address", "null"));
        infoUser.setBirthday(sharedPref.getString("user_birthday", "null"));
        infoUser.setPassword(sharedPref.getString("user_password", "null"));

        ApiService.apiService.getAllUserCourseSigned(infoUser.get_id(), sharedPref.getString("token", "null")).enqueue(new Callback<List<CourseSigned>>() {
            @Override
            public void onResponse(Call<List<CourseSigned>> call, Response<List<CourseSigned>> response) {
                if(response.code()==200) {
                    if(infoUser.getType()==2)
                    txtCountCourse.setText("Đã tham gia "+ response.body().size() +" khóa học");
                    else
                        txtCountCourse.setText("Đã tạo "+ response.body().size() +" khóa học");
                }
                else
                if(response.message() != null)
                    Toast.makeText(getContext(), response.message(), Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getContext(), "Lỗi server", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<List<CourseSigned>> call, Throwable t) {
                Toast.makeText(getContext(), "Lỗi server", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void addEvents() {
        txtName.setText(infoUser.getUser_name());
        txtCountCourse.setText("Đã tham gia "+ infoUser.getCount_course()+ " khóa học");
        email_profile.setText(infoUser.getEmail());
        sex_profile.setText(infoUser.getGender());
        dob_profile.setText(infoUser.getBirthday());
        address_profile.setText(infoUser.getAddress());
    }

    private void mappingControls() {
        txtName = view.findViewById(R.id.txtName);
        txtCountCourse = view.findViewById(R.id.txtCountCourse);
        btnEditProfile = view.findViewById(R.id.btnEditProfile);
        email_profile = view.findViewById(R.id.email_profile);
        sex_profile = view.findViewById(R.id.sex_profile);
        dob_profile = view.findViewById(R.id.dob_profile);
        address_profile = view.findViewById(R.id.address_profile);
    }
}