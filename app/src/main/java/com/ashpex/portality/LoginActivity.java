package com.ashpex.portality;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ashpex.portality.api.ApiService;
import com.ashpex.portality.model.InfoUser;
import com.ashpex.portality.model.LoginRequest;
import com.ashpex.portality.model.LoginStatus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText txtUserName_Login;
    private EditText txtPassword_Login;
    private Button btnLogin_Login;
    private Button btnForgotPassword_Login;
    private Button btnSignUp_Login;
    private String email;
    private String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mappingControls();
        eventsHandle();
    }

    private void eventsHandle(){
        btnLogin_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txtUserName_Login.getText().toString();
                String password = txtPassword_Login.getText().toString();
                if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
                    loginChecking(email, password);
                }
                else
                    Toast.makeText(getApplicationContext(), "Mời nhập đầy đủ thông tin đăng nhập", Toast.LENGTH_SHORT).show();
            }
        });

        btnSignUp_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.sign_up);
            }
        });

        btnForgotPassword_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.fragment_forgot_password1);
            }
        });

    }

    private void loginChecking(String email, String password) {
        LoginRequest loginRequest = new LoginRequest(email, password);
        ApiService.apiService.loginAction(loginRequest).enqueue(new Callback<LoginStatus>() {
            @Override
            public void onResponse(Call<LoginStatus> call, Response<LoginStatus> response) {
                LoginStatus loginStatus = response.body();
                if(loginStatus != null && loginStatus.getMessage().contains("successful")) {
                    loginSuccess(loginStatus.getUser());
                }
                else
                    Toast.makeText(getApplicationContext(), "Some things went wrong", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<LoginStatus> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Some things went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void loginSuccess(@NonNull InfoUser user) {
        Intent intent = new Intent(this, MainScreen.class);
        intent.putExtra("user_id", user.get_id());
        intent.putExtra("user_password", user.getPassword());
        intent.putExtra("user_name", user.getUser_name());
        intent.putExtra("user_type", user.getType());
        intent.putExtra("user_email", user.getEmail());
        intent.putExtra("user_gender", user.getGender());
        intent.putExtra("user_birthday", user.getBirthday());
        intent.putExtra("user_address", user.getAddress());

        startActivity(intent);
    }

    private void mappingControls(){
        btnLogin_Login = (Button) findViewById(R.id.btnLogin_Login);
        btnForgotPassword_Login = (Button) findViewById(R.id.btnForgotPassword_Login);
        btnSignUp_Login = (Button) findViewById(R.id.btnSignUp_Login);
        txtUserName_Login = (EditText) findViewById(R.id.txtUserName_Login);
        txtPassword_Login = (EditText) findViewById(R.id.txtPassword_Login);

    }

}

