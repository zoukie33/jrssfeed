package com.example.rssfeed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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
    private ApiInterface api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginBut = (Button) findViewById(R.id.login_but);
        gotoRegisterBut = (Button) findViewById(R.id.goto_register);
        email = (EditText) findViewById(R.id.Email_input);
        password = (EditText) findViewById(R.id.Password_input);
        api = ApiCli.getClient().create(ApiInterface.class);
        SharedPreferences sharedPref = getSharedPreferences("mySession", MODE_PRIVATE);
        String mySetting = sharedPref.getString("mySession", null);

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

    @Override
    public void onBackPressed() {
        Log.d("GoBack", "Nope stay here.");
    }

    public void onLoginOpenActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void openActivityRegister() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
    public void login(String email, String password) {
        Call<Void> callReg = api.loginUser(email, password);
        callReg.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "Code: "+response.code(),
                            Toast.LENGTH_LONG).show();
                    return;
                }

                Response<Void> saveRes = response;
                SharedPreferences sharedPref = getSharedPreferences("mySession", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("mySession", saveRes.headers().get("Set-Cookie"));
                editor.commit();
                Toast.makeText(LoginActivity.this, "LOGED IN",
                        Toast.LENGTH_LONG).show();
                onLoginOpenActivity();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                call.cancel();
                Toast.makeText(LoginActivity.this, "FAILED CALL",
                        Toast.LENGTH_LONG).show();
                Log.d("==ERR CALL==", "==="+ t.toString());
            }
        });
    }
}
