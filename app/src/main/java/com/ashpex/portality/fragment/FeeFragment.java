package com.ashpex.portality.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ashpex.portality.R;
import com.ashpex.portality.adapter.UserFeeAdpater;
import com.ashpex.portality.api.ApiService;
import com.ashpex.portality.model.UserCourseOnStudying;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FeeFragment extends Fragment {

    private RecyclerView rycFee;
    private View view;
    private String userName;
    private String token;
    private Integer userId;
    private int type;
    private TextView tvTotal;
    private UserFeeAdpater userFeeAdpater;
    private List<UserCourseOnStudying> mList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_fee, container, false);
        mappingControls();


        getData();
        return view;

    }

    private List<UserCourseOnStudying> getListFee(){
        List<UserCourseOnStudying> list = new ArrayList<>();



        return list;
    }

    private void getData() {
        SharedPreferences sharedPref = view.getContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        userName = sharedPref.getString("user_name", "null");
        token = sharedPref.getString("token", "null");
        userId = sharedPref.getInt("user_id", 0);
        type = sharedPref.getInt("user_type", 0);

        ApiService.apiService.getUserCourse(userId, token).enqueue(new Callback<List<UserCourseOnStudying>>() {
            @Override
            public void onResponse(Call<List<UserCourseOnStudying>> call, Response<List<UserCourseOnStudying>> response) {
                if(response.code() == 200) {
                    mList = response.body();
                    Log.d("ALOO", String.valueOf(mList.size()));
                    userFeeAdpater = new UserFeeAdpater(getContext());
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                    rycFee.setLayoutManager(linearLayoutManager);
                    userFeeAdpater.setData(mList);
                    rycFee.setAdapter(userFeeAdpater);
                    /*
                    double total  = 0;
                    for(int i = 0; i < mList.size(); i++){
                        total  += Double.parseDouble(mList.get(i).getFee());
                    }
                    tvTotal.setText(String.valueOf(total));

                     */
                }
            }

            @Override
            public void onFailure(Call<List<UserCourseOnStudying>> call, Throwable t) {
                Log.d("Log", "Cant connect");
            }
        });
    }

    private void mappingControls(){
        rycFee = view.findViewById(R.id.ryc_fee);
        tvTotal = view.findViewById(R.id.tv_fee_total);
    }
}
