package com.example.rssfeed;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rssfeed.API.res.Feed;
import com.example.rssfeed.adapter.feed.FeedAdapter;

import java.util.List;

public class ItemListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Feed> itemList;
    private FeedAdapter feedAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemlist);
        Toolbar toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.rv_items);
        setSupportActionBar(toolbar);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Intent intent = getIntent();
        itemList = intent.getExtras().getParcelableArrayList("items");
        Log.d("Bite", itemList.toString());
        feedAdapter = new FeedAdapter(itemList);
        recyclerView.setAdapter(feedAdapter);
    }
}
