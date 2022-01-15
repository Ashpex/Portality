package com.ashpex.portality;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.ashpex.portality.api.ApiService;
import com.ashpex.portality.model.ErrorMessage;
import com.ashpex.portality.model.SignCourseForm;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    private EditText txtEmail_SignUp;
    private EditText txtSurname_SignUp;
    private EditText txtLastName_SignUp;
    private EditText txtPassword_SignUp;
    private EditText txtRePassword_SignUp;
    private EditText txtDOB_SignUp;
    private TextInputLayout dob_text_input_layout;
    private Button btnSignUp_SignUp;
    private CheckBox ckAgree;
    private AutoCompleteTextView txtDuty_SignUp;
    private AutoCompleteTextView txtSex_SignUp;
    private final int STATE_DUTY = 0;
    private final int STATE_SEX = 1;
    private String date;
    private int duty_id = 0;
    private String gender ="";
    int selectedYear = 2000;
    int selectedMonth = 5;
    int selectedDayOfMonth = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mappingControls();
        
        addEvents();
    }

    private void addEvents() {
        txtDuty_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickFunction(STATE_DUTY);
            }
        });
        txtSex_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickFunction(STATE_SEX);
            }
        });

        btnSignUp_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveAction();
            }
        });

        txtDOB_SignUp.setShowSoftInputOnFocus(false);
        txtDOB_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogDate();
            }
        });

    }

    private void dialogDate() {

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
                txtDOB_SignUp.setText(date);
                printToast(date);
            }
        };
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                dateSetListener, selectedYear, selectedMonth, selectedDayOfMonth);

        datePickerDialog.show();
    }

    private void saveAction() {
        if(checkInfo())
            return;
        SignCourseForm body = new SignCourseForm(txtSurname_SignUp.getText().toString()+" " + txtLastName_SignUp.getText().toString()
                , txtEmail_SignUp.getText().toString(), txtPassword_SignUp.getText().toString(), duty_id, gender, date);
        ApiService.apiService.signUpUser(body).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.code()==200) {
                    printToast("Đăng ký thành công");
                } else if(response.code()==400){
                    Gson gson = new GsonBuilder().create();
                    ErrorMessage mError = new ErrorMessage();
                    try {
                        mError= gson.fromJson(response.errorBody().string(),ErrorMessage.class);
                        printToast(mError.getMessage());
                    } catch (IOException e) {
                        // handle failure to read error
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private boolean checkInfo() {
        if(TextUtils.isEmpty(txtEmail_SignUp.getText())) {
            printToast("Mời nhập email");
            return true;
        }
        if(TextUtils.isEmpty(txtSurname_SignUp.getText())) {
            printToast("Mời nhập Họ");
            return true;
        }
        if(TextUtils.isEmpty(txtLastName_SignUp.getText())) {
            printToast("Mời nhập tên");
            return true;
        }
        if(TextUtils.isEmpty(txtPassword_SignUp.getText())) {
            printToast("Mời nhập mật khẩu");
            return true;
        }
        if(TextUtils.isEmpty(txtRePassword_SignUp.getText())) {
            printToast("Mời nhập mật khẩu xác nhận");
            return true;
        }
        if(TextUtils.isEmpty(txtDOB_SignUp.getText())) {
            printToast("Mời nhập ngày sinh");
            return true;
        }
        if(!ckAgree.isChecked()) {
            printToast("Bạn chưa đồng ý với chính sách của chúng tôi");
            return true;
        }
        if(!txtPassword_SignUp.getText().toString().equals(txtRePassword_SignUp.getText().toString())) {
            printToast("Mật khẩu không khớp");
            return true;
        }
        if(!txtEmail_SignUp.getText().toString().contains("@")) {
            printToast("Kiểm tra lại định dạng email");
            return true;
        }
        if(txtPassword_SignUp.getText().toString().length() < 6) {
            printToast("Mật khẩu quá ngắn");
            return true;
        }
        if(duty_id == 0 ) {
            printToast("Mời nhập chức vụ");
            return true;
        }
        if(gender.equals("")) {
            printToast("Mời nhập giới tính");
            return true;
        }
        return false;
    }

    private void printToast(String mess) {
        Toast.makeText(getApplicationContext(), mess, Toast.LENGTH_SHORT).show();
    }

    private void onClickFunction(int state) {
        switch (state) {
            case STATE_DUTY:
                dialogDuty();
                break;
            case STATE_SEX:
                dialogSex();
                break;
        }
    }

    private void dialogSex() {
        final String[] sex = {
                "Nam", "Nữ"
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
        builder.setTitle("Giới tính");
        builder.setItems(sex, new DialogInterface.OnClickListener() {@
                Override
        public void onClick(DialogInterface dialog, int which) {
            if(sex[which].equals("Nam")) {
                gender = "male";
                txtSex_SignUp.setText(sex[which]);
            }
            else if(sex[which].equals("Nữ")) {
                txtSex_SignUp.setText(sex[which]);
                gender = "female";
            }
            dialog.cancel();
        }
        });
        builder.show();
    }

    private void dialogDuty() {
        final String[] duty = {
                "Sinh viên", "Giảng viên"
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
        builder.setTitle("Chức vụ");
        builder.setItems(duty, new DialogInterface.OnClickListener() {@
                Override
        public void onClick(DialogInterface dialog, int which) {
            if(duty[which].equals("Sinh viên")) {
                duty_id = 2;
                txtDuty_SignUp.setText(duty[which]);
            }
            else if(duty[which].equals("Giảng viên")) {
                txtDuty_SignUp.setText(duty[which]);
                duty_id = 1;
            }
            dialog.cancel();
        }
        });
        builder.show();
    }

    private void mappingControls() {
        txtEmail_SignUp = findViewById(R.id.txtEmail_SignUp);
        txtSurname_SignUp = findViewById(R.id.txtSurname_SignUp);
        txtLastName_SignUp = findViewById(R.id.txtLastName_SignUp);
        txtPassword_SignUp = findViewById(R.id.txtPassword_SignUp);
        txtRePassword_SignUp = findViewById(R.id.txtRePassword_SignUp);
        txtDOB_SignUp = findViewById(R.id.txtDOB_SignUp);
        btnSignUp_SignUp = findViewById(R.id.btnSignUp_SignUp);
        ckAgree = findViewById(R.id.ckAgree);
        txtDuty_SignUp = findViewById(R.id.txtDuty_SignUp);
        txtSex_SignUp = findViewById(R.id.txtSex_SignUp);

        dob_text_input_layout = findViewById(R.id.dob_text_input_layout);
    }
}