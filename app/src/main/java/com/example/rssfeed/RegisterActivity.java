package com.example.rssfeed;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rssfeed.API.ApiCli;
import com.example.rssfeed.API.ApiInterface;
import com.example.rssfeed.API.res.Auth;

public class RegisterActivity extends AppCompatActivity {
    private Button gotoLoginBut;
    private Button registerBut;
    private EditText email;
    private EditText username;
    private EditText password;
    private EditText password2;
    ApiInterface api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        gotoLoginBut = (Button) findViewById(R.id.goto_login_but);
        registerBut = (Button) findViewById(R.id.register_but);
        username = (EditText) findViewById(R.id.Register_Username_input);
        password = (EditText) findViewById(R.id.Register_Password_input);
        password2 = (EditText) findViewById(R.id.Register_Password2_input);
        email = (EditText) findViewById(R.id.Register_Email_input);
        api = ApiCli.getClient().create(ApiInterface.class);

        gotoLoginBut.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String pass2 = password2.getText().toString();
                String mail = email.getText().toString();
                register(user, pass, pass2, mail);

            }
        });
        registerBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // openActivityRegister();
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

    public void register(String username, String password, String password2, String email) {
        if (password == password2) {
            Call<Auth> callReg = api.signupUser(username, password, email);
            callReg.enqueue(new Callback<Auth>() {
                @Override
                public void onResponse(Call<Auth> call, Response<Auth> response) {

                }

                @Override
                public void onFailure(Call<Auth> call, Throwable t) {
                    call.cancel();
                }
            });
        } else {
            Toast.makeText(RegisterActivity.this, "Les mots de passes ne sont pas identiques.",
                    Toast.LENGTH_SHORT).show();
        }
    }
}