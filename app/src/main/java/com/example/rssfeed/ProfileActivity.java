package com.example.rssfeed;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.rssfeed.API.ApiCli;
import com.example.rssfeed.API.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    private Button btnAddFeed;
    private Button btnDelFeed;
    private Button btnDelAccount;
    private EditText urlAddFeed;
    private EditText urlDelFeed;
    private String cookie;
    private SharedPreferences sharedPref;
    private ApiInterface api;

    @Override
    protected void  onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnAddFeed = (Button) findViewById(R.id.btn_addFeed);
        btnDelFeed = (Button) findViewById(R.id.btn_delFeed);
        btnDelAccount = (Button) findViewById(R.id.btn_delete_account);
        urlAddFeed = (EditText) findViewById(R.id.input_addFeed);
        urlDelFeed = (EditText) findViewById(R.id.input_delFeed);
        api = ApiCli.getClient().create(ApiInterface.class);
        sharedPref = getSharedPreferences("mySession", MODE_PRIVATE);
        cookie = sharedPref.getString("mySession", null);

        btnAddFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFeed(urlAddFeed.getText().toString());
            }
        });

        btnDelFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delFeed(urlDelFeed.getText().toString());
            }
        });

        btnDelAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);

                builder.setTitle("Confirmation");
                builder.setMessage("Êtes vous sûr ?");

                builder.setPositiveButton("oui", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ProfileActivity.this, "Votre compte a été supprimé.",
                                Toast.LENGTH_SHORT).show();
                        sharedPref.edit().remove("mySession").commit();
                        Call<Void> callDelAccount = api.delAccount(cookie);
                        callDelAccount.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {

                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                Log.d("CallAddFeed", "Bad Response: " + t.toString());
                                call.cancel();
                                Toast.makeText(ProfileActivity.this, "Une erreur est survenue.",
                                        Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(ProfileActivity.this, RegisterActivity.class);
                                ProfileActivity.this.startActivity(i);
                            }
                        });
                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("NON", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_accueil:
                Intent i = new Intent(ProfileActivity.this, MainActivity.class);
                ProfileActivity.this.startActivity(i);
                return true;
            case R.id.action_profile:
                Intent i2 = new Intent(ProfileActivity.this, ProfileActivity.class);
                ProfileActivity.this.startActivity(i2);
                return true;
            case R.id.action_logout:
                sharedPref.edit().remove("mySession").commit();
                Intent i3 = new Intent(ProfileActivity.this, LoginActivity.class);
                ProfileActivity.this.startActivity(i3);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addFeed(String urlFeed) {
        if (!urlFeed.isEmpty()) {
            Call<Void> callAddFeed = api.addFeed(cookie, urlFeed);
            callAddFeed.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.code() == 200) {
                        Toast.makeText(ProfileActivity.this, "Vous avez bien ajouté un feed.",
                                Toast.LENGTH_SHORT).show();
                        urlAddFeed.setText("");
                    } else if (response.code() == 400) {
                        Toast.makeText(ProfileActivity.this, "Erreur : Vous avez déjà ajouté ce feed.",
                                Toast.LENGTH_SHORT).show();
                        urlAddFeed.setText("");
                    } else {
                        Toast.makeText(ProfileActivity.this, "Une erreur est survenue.",
                                Toast.LENGTH_SHORT).show();
                        Log.e("CallAddFeedER", response.toString());
                        urlAddFeed.setText("");
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.d("CallAddFeed", "Bad Response: " + t.toString());
                    call.cancel();
                }
            });
        } else {
            Toast.makeText(ProfileActivity.this, "Vous devez insérer une url.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void delFeed(String urlFeed) {
        if (!urlFeed.isEmpty()) {
            Call<Void> callDelFeed = api.delFeed( cookie, urlFeed);
            callDelFeed.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.code() == 200) {
                        Toast.makeText(ProfileActivity.this, "Vous avez bien supprimé un feed.",
                                Toast.LENGTH_SHORT).show();
                        urlDelFeed.setText("");
                    } else if (response.code() == 400) {
                        Toast.makeText(ProfileActivity.this, "Erreur : Vous n'êtes pas inscris à ce feed.",
                                Toast.LENGTH_SHORT).show();
                        urlAddFeed.setText("");
                    } else {
                        Toast.makeText(ProfileActivity.this, "Une erreur est survenue.",
                                Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.d("CallDelFeed", "Bad Response: " + t.toString());
                    call.cancel();
                }
            });
        } else {
            Toast.makeText(ProfileActivity.this, "Vous devez insérer une url.",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
