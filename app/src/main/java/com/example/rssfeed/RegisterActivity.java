package com.example.rssfeed;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rssfeed.API.ApiCli;
import com.example.rssfeed.API.ApiInterface;

public class RegisterActivity extends AppCompatActivity {
    private Button gotoLoginBut;
    private Button registerBut;
    private EditText email;
    private EditText password;
    private EditText password2;
    ApiInterface api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        gotoLoginBut = (Button) findViewById(R.id.goto_login_but);
        registerBut = (Button) findViewById(R.id.register_but);
        password = (EditText) findViewById(R.id.Register_Password_input);
        password2 = (EditText) findViewById(R.id.Register_Password2_input);
        email = (EditText) findViewById(R.id.Register_Email_input);
        api = ApiCli.getClient().create(ApiInterface.class);

        gotoLoginBut.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                openActivityLogin();
            }
        });
        registerBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass = password.getText().toString();
                String pass2 = password2.getText().toString();
                String mail = email.getText().toString();
                register(pass, pass2, mail);
            }
        });
    }

    public void openActivityLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    public void openActivityRegister() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void register(String password, String password2, String email) {
        Log.d("Pass1", "Password1: " + password);
        Log.d("pass2", "Password2: " + password2);
        if (password.equals(password2)) {
            Call<Void> callReg = api.signupUser(password, password2, email);
            callReg.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Log.d("Res", "Response: " + response.toString());
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {

                    Log.d("Res", "Bad Response: " + t.toString());
                    call.cancel();
                }
            });
        } else {
            Toast.makeText(RegisterActivity.this, "Les mots de passes ne sont pas identiques.",
                    Toast.LENGTH_SHORT).show();
        }
    }
}