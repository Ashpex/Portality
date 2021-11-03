package com.ashpex.portality;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText txtUserName_Login;
    private EditText txtPassword_Login;
    private Button btnLogin_Login;
    private Button btnForgotPassword_Login;
    private Button btnSignUp_Login;

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
                if(txtUserName_Login.getText().toString().equals("test") && txtPassword_Login.getText().toString().equals("test")){
                    setContentView(R.layout.activity_main);
                }
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

    private void mappingControls(){
        btnLogin_Login = (Button) findViewById(R.id.btnLogin_Login);
        btnForgotPassword_Login = (Button) findViewById(R.id.btnForgotPassword_Login);
        btnSignUp_Login = (Button) findViewById(R.id.btnSignUp_Login);
        txtUserName_Login = (EditText) findViewById(R.id.txtUserName_Login);
        txtPassword_Login = (EditText) findViewById(R.id.txtPassword_Login);

    }
}

