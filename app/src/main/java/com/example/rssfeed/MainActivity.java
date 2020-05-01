package com.example.rssfeed;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.rssfeed.API.ApiCli;
import com.example.rssfeed.API.ApiInterface;
import com.example.rssfeed.API.res.Feed;
import com.example.rssfeed.API.res.Rss;
import com.example.rssfeed.adapter.FeedClickListener;
import com.example.rssfeed.adapter.RssAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ApiInterface api;
    private RecyclerView recyclerView;
    private List<Feed> FeedResults;
    private RssAdapter rssAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.rv_feeds);
        setSupportActionBar(toolbar);

        GridLayoutManager manager = new GridLayoutManager(this, 2);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return 1;
            }
        });

        recyclerView.setLayoutManager(manager);
        api = ApiCli.getClient().create(ApiInterface.class);
        SharedPreferences sharedPref = getSharedPreferences("mySession", MODE_PRIVATE);
        Log.d("SharedPrefs", sharedPref.getAll().toString());
        getUserFeed(sharedPref.getString("mySession", null));


    }

    public void getUserFeed(String cookieSession) {
        Log.d("cookieSession", "Cookie: " + cookieSession);
        Call<Rss> callRss = api.getRssFeed(cookieSession);
        callRss.enqueue(new Callback<Rss>() {
            @Override
            public void onResponse(Call<Rss> call, Response<Rss> response) {
                Log.d("Res", "Content Body:" + response.body().getItems());
                FeedResults = response.body().getItems();
                rssAdapter = new RssAdapter(FeedResults);
                recyclerView.setAdapter(rssAdapter);
                for(Feed feed : FeedResults) {
                    Log.d("Mon feed", feed.getAuthor() + " " + feed.getPubDate() + " " + feed.getLink());
                }
            }

            @Override
            public void onFailure(Call<Rss> call, Throwable t) {
                Log.e("ERR", "Err route /rss: " + t.toString());
            }
        });
    }
}
