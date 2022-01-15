package com.ashpex.portality;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import com.ashpex.portality.api.ApiService;
import com.ashpex.portality.model.ErrorMessage;
import com.ashpex.portality.model.SignCourseForm;
import com.ashpex.portality.model.SignUpCourseForm;
import com.ashpex.portality.model.Subject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateCourseActivity extends AppCompatActivity {
    private ImageButton btnBack_course;
    private EditText txtNameCourse;
    private EditText txtDateStart;
    private EditText txtDateEnd;
    private EditText txtDay_of_wwek;
    private EditText txtTimeStart;
    private EditText txtTimeEnd;
    private EditText txtSubject;
    private EditText txtMax;
    private EditText txtDes;
    private EditText txtLink;
    private EditText txtFee;
    private EditText txtRequire;
    private ImageButton btnSave;
    int selectedYear = 2022;
    int selectedMonth = 5;
    int selectedDayOfMonth = 10;
    int selectedYearEnd = 2022;
    int selectedMonthEnd = 5;
    int selectedDayOfMonthEnd = 10;

    private int subject_id = -1;
    private String date = "";
    private String dateEnd = "";
    private int dayOfWeek = -1;
    private String timeStart="";
    private String timeEnd="";

    private int selectedHourStart = 10;
    private int selectedMinuteStart = 20;
    private int selectedHourEnd = 10;
    private int selectedMinuteEnd = 20;
    private boolean is24HView = true;
    private List<Subject> listSubject;

    private String token;
    private int user_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_course);
        
        mappingControls();
        setUpData();
        addEvents();
    }

    private void setUpData() {
        txtDateStart.setShowSoftInputOnFocus(false);
        txtDay_of_wwek.setShowSoftInputOnFocus(false);
        txtTimeStart.setShowSoftInputOnFocus(false);
        txtTimeEnd.setShowSoftInputOnFocus(false);
        txtSubject.setShowSoftInputOnFocus(false);
        listSubject = new ArrayList<>();

        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        user_id = sharedPref.getInt("user_id", -1);
        token = sharedPref.getString("token", "");
    }

    private void addEvents() {
        btnBack_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        txtDateStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogDateStart();
            }
        });
        txtDateEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogDateEnd();
            }
        });
        txtDay_of_wwek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogDayOfWeek();
            }
        });
        txtTimeStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogTimeStart();
            }
        });
        txtTimeEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogTimeEnd();
            }
        });
        txtSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSubjectList();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveEvents();
            }
        });
    }

    private void saveEvents() {
        if(checkInfo() || user_id == -1)
            return;

        SignUpCourseForm body = new SignUpCourseForm(txtNameCourse.getText().toString(),
                subject_id, txtDes.getText().toString(), timeStart, timeEnd, dayOfWeek, date,
                txtDateEnd.getText().toString(), Integer.valueOf(txtMax.getText().toString()),
                Long.valueOf(txtFee.getText().toString()),txtLink.getText().toString(), txtRequire.getText().toString());

        ApiService.apiService.signUpCourseRequestTeacher(user_id, body, token).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.code()==200) {
                    printToast("Đăng ký thành công");
                    finish();
                }else if(response.code()==400) {
                    Gson gson = new GsonBuilder().create();
                    ErrorMessage mError = new ErrorMessage();
                    try {
                        mError= gson.fromJson(response.errorBody().string(),ErrorMessage.class);
                        printToast(mError.getMessage());
                    } catch (IOException e) {

                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private boolean checkInfo() {
        if(TextUtils.isEmpty(txtNameCourse.getText().toString())) {
            printToast("Tên khóa học trống");
            return true;
        }
        if(TextUtils.isEmpty(txtDateStart.getText().toString())) {
            printToast("Ngày bắt đầu trống");
            return true;
        }
        if(TextUtils.isEmpty(txtDateEnd.getText().toString())) {
            printToast("Ngày kết thúc trống");
            return true;
        }
        if(TextUtils.isEmpty(txtTimeStart.getText().toString())) {
            printToast("Thời gian bắt đầu trống");
            return true;
        }
        if(TextUtils.isEmpty(txtTimeEnd.getText().toString())) {
            printToast("Thời gian kết thúc trống");
            return true;
        }
        if(TextUtils.isEmpty(txtDay_of_wwek.getText().toString())) {
            printToast("Thứ trống");
            return true;
        }
        if(TextUtils.isEmpty(txtDes.getText().toString())) {
            printToast("Mô tả trống");
            return true;
        }
        if(TextUtils.isEmpty(txtLink.getText().toString())) {
            printToast("Phòng học trống");
            return true;
        }
        if(TextUtils.isEmpty(txtFee.getText().toString())) {
            printToast("Học phí trống");
            return true;
        }
        if(TextUtils.isEmpty(txtRequire.getText().toString())) {
            printToast("Yêu cầu trống");
            return true;
        }
        if(TextUtils.isEmpty(txtMax.getText().toString())) {
            printToast("Số lượng học viên trống");
            return true;
        }
        if(selectedHourStart*60+selectedMinuteStart > selectedMinuteEnd + selectedHourEnd*60) {
            printToast("Giờ bắt đầu và kết thúc không hợp lệ");
            return true;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date_start = formatter.parse(date);
            Date date_end= formatter.parse(dateEnd);
            if(date_start.toInstant().isAfter(date_end.toInstant())) {
                printToast("Ngày bắt đầu và kết thúc không hợp lệ");
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void printToast(String mess) {
        Toast.makeText(getApplicationContext(), mess, Toast.LENGTH_SHORT).show();
    }
    private void showSubjectList() {
        ProgressDialog progressDialog = new ProgressDialog(CreateCourseActivity.this);
        progressDialog.show();
        ApiService.apiService.getAllSubject().enqueue(new Callback<List<Subject>>() {
            @Override
            public void onResponse(Call<List<Subject>> call, Response<List<Subject>> response) {
                if(response.code()==200) {
                    listSubject = response.body();
                    if(listSubject !=null) {
                        showList();
                        progressDialog.cancel();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Subject>> call, Throwable t) {

            }
        });
    }

    private void showList() {
        List<String> list = new ArrayList<>();
        for(int i =0 ;i < listSubject.size();i++) {
            list.add(listSubject.get(i).getSubject_name());
        }
        String[] list_1 = list.toArray(new String[0]);
        AlertDialog.Builder builder = new AlertDialog.Builder(CreateCourseActivity.this);
        builder.setTitle("Khoa: ");
        builder.setItems(list_1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                subject_id = listSubject.get(which).get_id();
                txtSubject.setText(listSubject.get(which).getSubject_name());
                dialogInterface.cancel();
            }
        });
        builder.show();
    }

    private void dialogTimeEnd() {
        // Time Set Listener.
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                String h = String.valueOf(hourOfDay);
                String m = String.valueOf(minute);
                if(h.length()==1)
                    h = "0" + h;
                if(m.length() == 1)
                    m = "0" + m;
                timeEnd = h + ":" + m;
                selectedHourEnd= hourOfDay;
                selectedMinuteEnd = minute;
                txtTimeEnd.setText(timeEnd);
            }
        };
// Create TimePickerDialog:
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                timeSetListener, selectedHourEnd, selectedMinuteEnd, is24HView);

// Show
        timePickerDialog.show();
    }

    private void dialogTimeStart() {
// Time Set Listener.
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                String h = String.valueOf(hourOfDay);
                String m = String.valueOf(minute);
                if(h.length()==1)
                    h = "0" + h;
                if(m.length() == 1)
                    m = "0" + m;
                timeStart = h + ":" + m;
                selectedHourStart = hourOfDay;
                selectedMinuteStart = minute;
                txtTimeStart.setText(timeStart);
            }
        };
// Create TimePickerDialog:
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                timeSetListener, selectedHourStart, selectedMinuteStart, is24HView);

// Show
        timePickerDialog.show();
    }

    private void dialogDateStart() {

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {
                selectedYear = year;
                selectedMonth = monthOfYear;
                selectedDayOfMonth = dayOfMonth;
                String moth = String.valueOf(monthOfYear+1);
                String day = String.valueOf(dayOfMonth);
                if(moth.length()==1) moth = "0" + moth;
                if(day.length()==1) day = "0" + day;
                date = year+"-" +moth+"-"+day;
                txtDateStart.setText(date);
            }
        };
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                dateSetListener, selectedYear, selectedMonth, selectedDayOfMonth);

        datePickerDialog.show();
    }
    private void dialogDateEnd() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {
                selectedYearEnd = year;
                selectedMonthEnd = monthOfYear;
                selectedDayOfMonthEnd = dayOfMonth;
                String moth = String.valueOf(monthOfYear+1);
                String day = String.valueOf(dayOfMonth);
                if(moth.length()==1) moth = "0" + moth;
                if(day.length()==1) day = "0" + day;
                dateEnd = year+"-" +moth+"-"+day;
                txtDateEnd.setText(dateEnd);
            }
        };
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                dateSetListener, selectedYearEnd, selectedMonthEnd, selectedDayOfMonthEnd);

        datePickerDialog.show();
    }
    private void dialogDayOfWeek() {
        final String[] dayOfW = {
                "Thứ 2", "Thứ 3","Thứ 4","Thứ 5","Thứ 6","Thứ 7","Chủ nhật"
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(CreateCourseActivity.this);
        builder.setTitle("Thứ trong ngày");
        builder.setItems(dayOfW, new DialogInterface.OnClickListener() {@
                Override
        public void onClick(DialogInterface dialog, int which) {
            txtDay_of_wwek.setText(dayOfW[which]);
            dayOfWeek = which +2;
            dialog.cancel();
        }
        });
        builder.show();
    }
    private void mappingControls() {
        btnBack_course = findViewById(R.id.btnBack_course);
        txtNameCourse = findViewById(R.id.txtNameCourse);
        txtDateStart = findViewById(R.id.txtDateStart);
        txtDateEnd = findViewById(R.id.txtDateEnd);
        txtTimeStart = findViewById(R.id.txtTimeStart);
        txtTimeEnd = findViewById(R.id.txtTimeEnd);
        txtDes = findViewById(R.id.txtDes);
        txtDay_of_wwek = findViewById(R.id.txtDay_of_wwek);
        txtLink = findViewById(R.id.txtLink);
        txtFee = findViewById(R.id.txtFee);
        txtRequire = findViewById(R.id.txtRequire);
        btnSave = findViewById(R.id.btnSave);
        txtSubject = findViewById(R.id.txtSubject);
        txtMax = findViewById(R.id.txtMax);
    }
}