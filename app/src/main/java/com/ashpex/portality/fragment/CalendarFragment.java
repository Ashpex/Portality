package com.ashpex.portality.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ashpex.portality.R;
import com.ashpex.portality.adapter.UserCourseOnStudyingAdapter;
import com.ashpex.portality.api.ApiService;
import com.ashpex.portality.model.UserCourseOnStudying;
import com.github.tibolte.agendacalendarview.AgendaCalendarView;
import com.github.tibolte.agendacalendarview.CalendarPickerController;
import com.github.tibolte.agendacalendarview.models.BaseCalendarEvent;
import com.github.tibolte.agendacalendarview.models.CalendarEvent;
import com.github.tibolte.agendacalendarview.models.DayItem;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CalendarFragment extends Fragment {

    private AgendaCalendarView mAgendaCalendarView;
    private View view;
    private String userName;
    private String token;
    private Integer userId;
    List<UserCourseOnStudying> mlist;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        view =  inflater.inflate(R.layout.fragment_calendar, container, false);
        mappingControls();
        calendarSetup();

        return view;

    }

    private void calendarSetup(){
        Calendar minDate = Calendar.getInstance();
        Calendar maxDate = Calendar.getInstance();
        CalendarPickerController calendarPickerController = new CalendarPickerController() {
            @Override
            public void onDaySelected(DayItem dayItem) {
            }

            @Override
            public void onEventSelected(CalendarEvent event) {

            }

            @Override
            public void onScrollToDate(Calendar calendar) {

            }
        };

        minDate.add(Calendar.MONTH, -2);
        minDate.set(Calendar.DAY_OF_MONTH, 1);
        maxDate.add(Calendar.YEAR, 1);

        List<CalendarEvent> eventList = new ArrayList<>();
        mockList(eventList);

        mAgendaCalendarView.init(eventList, minDate, maxDate, Locale.getDefault(), calendarPickerController);
    }

    private void mockList(List<CalendarEvent> eventList) {

        getData(mlist);

        Calendar startTime1 = Calendar.getInstance();
        Calendar endTime1 = Calendar.getInstance();
        endTime1.add(Calendar.MONTH, 1);
        BaseCalendarEvent event1 = new BaseCalendarEvent("Lập trình hướng đối tượng", "Hồ Tuấn Thanh", "Hồ Tuấn Thanh",
                R.color.dark_blue, startTime1, endTime1, true);
        eventList.add(event1);

        Calendar startTime2 = Calendar.getInstance();
        startTime2.add(Calendar.DAY_OF_YEAR, 1);
        Calendar endTime2 = Calendar.getInstance();
        endTime2.add(Calendar.DAY_OF_YEAR, 3);
        BaseCalendarEvent event2 = new BaseCalendarEvent("Nhập môn Công nghệ phần mềm", "Hồ Tuấn Thanh", "Hồ Tuấn Thanh",
                R.color.darker_blue, startTime2, endTime2, true);
        eventList.add(event2);

        // test api
        /*
        Calendar startTime3 = Calendar.getInstance();
        startTime3.add(Calendar.DAY_OF_YEAR, 1);
        Calendar endTime3 = Calendar.getInstance();
        endTime3.add(Calendar.DAY_OF_YEAR, 3);
        BaseCalendarEvent event3 = new BaseCalendarEvent(mlist.get(0).getCourse_name(),mlist.get(0).getTeacher_name(),mlist.get(0).getTeacher_name(),
                R.color.darker_blue, startTime3, endTime3, true);
        eventList.add(event3);
        */
    }

    private void getData(List<UserCourseOnStudying> mlist){
        SharedPreferences sharedPref = view.getContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        userName = sharedPref.getString("user_name", "null");
        token = sharedPref.getString("token", "null");
        userId = sharedPref.getInt("user_id", 0);

        ApiService.apiService.getUserCourse(userId, token).enqueue(new Callback<List<UserCourseOnStudying>>() {
            @Override
            public void onResponse(Call<List<UserCourseOnStudying>> call, Response<List<UserCourseOnStudying>> response) {
                if(response.code() == 200) {
                    //mlist = response.body();
                    //filterList(mlist);
                }
            }

            @Override
            public void onFailure(Call<List<UserCourseOnStudying>> call, Throwable t) {
                Log.d("alo", "Cant connect");
            }
        });

    }

    private void filterList(List<UserCourseOnStudying> mlist) {
        Date currentTime = Calendar.getInstance().getTime();
        int day = currentTime.getDay();
        mlist.removeIf(i -> i.getDay_study() == day);
    }

    private void mappingControls() {
        mAgendaCalendarView = view.findViewById(R.id.agenda_calendar_view);
    }
}