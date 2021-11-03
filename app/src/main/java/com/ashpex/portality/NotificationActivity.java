package com.ashpex.portality;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.ashpex.portality.adapter.NotiAdapter;

import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {
    private RecyclerView ryc_noti_activity;
    private ImageButton btnBack_noti;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        ryc_noti_activity = findViewById(R.id.ryc_noti_activity);
        btnBack_noti = findViewById(R.id.btnBack_noti);
        List<String> list = new ArrayList<String>();
        list.add("Hôm nay");list.add("Chúng ta của hiện tại");list.add("Hôm qua");

        ryc_noti_activity.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        ryc_noti_activity.setAdapter(new NotiAdapter(list, this));

        btnBack_noti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}