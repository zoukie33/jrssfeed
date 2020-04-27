package com.example.rssfeed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.rssfeed.API.ApiCli;
import com.example.rssfeed.API.ApiInterface;
import com.example.rssfeed.API.res.Auth;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private Button loginBut;
    private Button gotoRegisterBut;
    private EditText username;
    private EditText password;
    ApiInterface api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginBut = (Button) findViewById(R.id.login_but);
        gotoRegisterBut = (Button) findViewById(R.id.goto_register);
        username = (EditText) findViewById(R.id.Username_input);
        password = (EditText) findViewById(R.id.Password_input);
        api = ApiCli.getClient().create(ApiInterface.class);

        loginBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                login(user , pass);
            }
        });
        gotoRegisterBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityRegister();
            }
        });
    }

    public void openActivityLogin() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void openActivityRegister() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
    public void login(String username, String password) {
        Call<Auth> callReg = api.loginUser(username, password);
        callReg.enqueue(new Callback<Auth>() {
            @Override
            public void onResponse(Call<Auth> call, Response<Auth> response) {

            }

            @Override
            public void onFailure(Call<Auth> call, Throwable t) {
                call.cancel();
            }
        });
    }
}
