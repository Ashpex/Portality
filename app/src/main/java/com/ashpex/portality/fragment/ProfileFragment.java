package com.ashpex.portality.fragment;

import android.annotation.SuppressLint;
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

import com.ashpex.portality.R;
import com.ashpex.portality.model.InfoUser;


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
        infoUser.setCount_course(sharedPref.getInt("count_course", 0));
        Log.d("count course",String.valueOf(infoUser.getCount_course()));
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