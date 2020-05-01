package com.example.rssfeed.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.rssfeed.API.res.Feed;
import com.example.rssfeed.R;

import java.util.List;
@SuppressWarnings("ALL")

public class RssAdapter extends RecyclerView.Adapter<FeedHolder> {

    private final List<Feed> feedList;

    public RssAdapter(List<Feed> feedList) {
        this.feedList = feedList;
    }

    @Override
    public FeedHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_item_view, parent, false);
        return new FeedHolder(v);
    }

    @Override
    public void onBindViewHolder(FeedHolder holder, int position) {
        Feed f = this.feedList.get(position);
        holder.bind(f);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public void onViewRecycled(FeedHolder holder) {
        super.onViewRecycled(holder);
    }
}
