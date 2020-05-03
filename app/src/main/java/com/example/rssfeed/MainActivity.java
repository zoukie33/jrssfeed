package com.example.rssfeed;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.rssfeed.API.ApiCli;
import com.example.rssfeed.API.ApiInterface;
import com.example.rssfeed.API.res.Feeds;
import com.example.rssfeed.API.res.Rss;
import com.example.rssfeed.adapter.rss.RssAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ApiInterface api;
    private RecyclerView recyclerView;
    private List<Rss> rssRssList;
    private RssAdapter rssAdapter;
    private SharedPreferences sharedPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.rv_feeds);
        setSupportActionBar(toolbar);
        sharedPref = getSharedPreferences("mySession", MODE_PRIVATE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        api = ApiCli.getClient().create(ApiInterface.class);
        getUserFeed(sharedPref.getString("mySession", null));
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
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                MainActivity.this.startActivity(i);
                return true;
            case R.id.action_profile:
                Intent i2 = new Intent(MainActivity.this, ProfileActivity.class);
                MainActivity.this.startActivity(i2);
                return true;
            case R.id.action_logout:
                sharedPref.edit().remove("mySession").commit();
                Intent i3 = new Intent(MainActivity.this, LoginActivity.class);
                MainActivity.this.startActivity(i3);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void getUserFeed(String cookieSession) {
        Log.d("cookieSession", "Cookie: " + cookieSession);
        Call<Feeds> callFeeds = api.getRssFeed(cookieSession);
        callFeeds.enqueue(new Callback<Feeds>() {
            @Override
            public void onResponse(Call<Feeds> call, Response<Feeds> response) {

                rssRssList = response.body().getFeeds();
                rssAdapter = new RssAdapter(rssRssList);
                recyclerView.setAdapter(rssAdapter);

            }

            @Override
            public void onFailure(Call<Feeds> call, Throwable t) {
                Log.e("ERR", "Err route /rss: " + t.toString());
            }
        });
    }
}
