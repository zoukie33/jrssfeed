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
        if (description.isEmpty() || description == "") {
            this.description = "n/a";
        } else {
            this.description = description;
        }
        if (title.isEmpty() || title == "") {
            this.title = "n/a";
        } else {
            this.title = title;
        }
        this.items = items;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description.isEmpty() || description == "") {
            this.description = "n/a";
        } else {
            this.description = description;
        }
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title.isEmpty() || title == "") {
            this.title = "n/a";
        } else {
            this.title = title;
        }
    }
    public ArrayList<Feed> getItems() {
        return items;
    }

    public void setItems(ArrayList<Feed> items) {
        this.items = items;
    }
}
