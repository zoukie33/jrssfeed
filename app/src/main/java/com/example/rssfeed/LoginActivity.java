package com.example.rssfeed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rssfeed.API.ApiCli;
import com.example.rssfeed.API.ApiInterface;
import com.example.rssfeed.API.res.Auth;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private Button loginBut;
    private Button gotoRegisterBut;
    private EditText email;
    private EditText password;
    ApiInterface api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginBut = (Button) findViewById(R.id.login_but);
        gotoRegisterBut = (Button) findViewById(R.id.goto_register);
        email = (EditText) findViewById(R.id.Email_input);
        password = (EditText) findViewById(R.id.Password_input);
        api = ApiCli.getClient().create(ApiInterface.class);

        loginBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = email.getText().toString();
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
    public void login(String email, String password) {
        Call<Auth> callReg = api.loginUser(email, password);
        callReg.enqueue(new Callback<Auth>() {
            @Override
            public void onResponse(Call<Auth> call, Response<Auth> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "Code: "+response.code(),
                            Toast.LENGTH_LONG).show();
                    return;
                }
                response.headers().get("Set-Cookie");
                Response<Auth> saveRes = response;
                List<String> Cookielist = saveRes.headers().values("Set-Cookie");
                String myCookie = (Cookielist.get(0).split(";"))[0];
                Log.d("Response code:", "===========" + saveRes.code());

                Log.d("==Cookie==1===", "==="+myCookie);
                Log.d("==Cookie==2==", "==="+saveRes.headers().get("Set-Cookie"));
                Log.d("==Content-Type====", "==="+saveRes.headers().get("Content-Type"));

                Toast.makeText(LoginActivity.this, "Res: "+myCookie,
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Auth> call, Throwable t) {
                call.cancel();
            }
        });
    }
}
