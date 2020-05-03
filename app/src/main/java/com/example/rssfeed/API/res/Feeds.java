package com.example.rssfeed.API.res;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Feeds implements Serializable {
    @SerializedName("feeds")
    private ArrayList<Rss> feeds;
    public Feeds(ArrayList<Rss> feeds) {
        this.feeds = feeds;
    }
    public ArrayList<Rss> getFeeds(){
        return feeds;
    }
    public void setFeeds(ArrayList<Rss> feeds) {
        this.feeds = feeds;
    }
}

