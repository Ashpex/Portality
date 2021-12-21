package com.ashpex.portality;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

    private EditText txtUserEmail_Login;
    private EditText txtPassword_Login;
    private Button btnLogin_Login;
    private Button btnForgotPassword_Login;
    private Button btnSignUp_Login;
    private String token ;
    private String password;
    private String user_email ;
    private String user_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        checkPre();
        mappingControls();
        eventsHandle();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(!user_email.equals("null")&&!user_password.equals("null")) {
            txtPassword_Login.setText(user_password);
            txtUserEmail_Login.setText(user_email);
        }
    }

    private void checkPre() {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        user_email = sharedPref.getString("user_email", " ");
        user_password = sharedPref.getString("user_password", "null");
    }

    private void eventsHandle(){
        btnLogin_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txtUserEmail_Login.getText().toString();
                password = txtPassword_Login.getText().toString();
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

        ProgressDialog mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading, please wait...");
        mProgressDialog.show();
        Log.d("Alo", loginRequest.getEmail());
        ApiService.apiService.loginAction(loginRequest).enqueue(new Callback<LoginStatus>() {
            @Override
            public void onResponse(Call<LoginStatus> call, Response<LoginStatus> response) {
                LoginStatus loginStatus = response.body();
                if(loginStatus != null && loginStatus.getMessage().contains("successful")) {

                    token = response.headers().get("Auth");
                    loginSuccess(loginStatus.getUser());
                }
                else {
                    Toast.makeText(getApplicationContext(), "Some things went wrong", Toast.LENGTH_SHORT).show();
                }
                mProgressDialog.cancel();
            }

            @Override
            public void onFailure(Call<LoginStatus> call, Throwable t) {
                mProgressDialog.cancel();
                Log.d("Alo", t.toString());

                Toast.makeText(getApplicationContext(), "Some things went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void loginSuccess(@NonNull InfoUser user) {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("user_id", user.get_id());
        editor.putInt("user_type", user.getType());
        editor.putString("user_password", password);
        editor.putString("user_name", user.getUser_name());
        editor.putString("user_email", user.getEmail());
        editor.putString("user_gender", user.getGender());
        editor.putString("user_birthday", user.getBirthday());
        editor.putString("user_address", user.getAddress());
        editor.putString("token", token);
        editor.apply();

        login();
    }

    private void login() {
        Intent intent = new Intent(this, MainScreen.class);
        startActivity(intent);
    }

    private void mappingControls(){
        btnLogin_Login = (Button) findViewById(R.id.btnLogin_Login);
        btnForgotPassword_Login = (Button) findViewById(R.id.btnForgotPassword_Login);
        btnSignUp_Login = (Button) findViewById(R.id.btnSignUp_Login);
        txtUserEmail_Login = (EditText) findViewById(R.id.txtUserEmail_Login);
        txtPassword_Login = (EditText) findViewById(R.id.txtPassword_Login);

    }

}

