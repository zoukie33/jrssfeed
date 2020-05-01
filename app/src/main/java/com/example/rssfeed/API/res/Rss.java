package com.example.rssfeed.API.res;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Rss implements Serializable {
    @SerializedName("description")
    private String description;
    @SerializedName("title")
    private String title;
    @SerializedName("items")
    private ArrayList<Feed> items;

    public Rss(String description, String title, ArrayList<Feed> items){
        this.description = description;
        this.title = title;
        this.items = items;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getTitle() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public ArrayList<Feed> getItems() {
        return items;
    }

    public void setItems(ArrayList<Feed> items) {
        this.items = items;
    }
}
