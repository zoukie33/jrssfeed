package com.example.rssfeed.API.res;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.lang.reflect.Array;

public class Feed implements Serializable, Parcelable {
    @SerializedName("author")
    private String author;
    @SerializedName("link")
    private String link;
    @SerializedName("title")
    private String title;
    @SerializedName("pubDate")
    private String pubDate;

    public Feed(String author, String link, String title, String pubDate) {
        if (author.isEmpty()) {
            this.author = "n/a";
        } else {
            this.author = author;
        }
        if (link.isEmpty()) {
            this.link = "n/a";
        } else {
            this.link = link;
        }
        if (title.isEmpty()) {
            this.title = "n/a";
        } else {
            this.title = title;
        }
        if (pubDate.isEmpty()) {
            this.pubDate = "n/a";
        } else {
            this.pubDate = pubDate;
        }
    }

    protected Feed(Parcel in) {
        author = in.readString();
        link = in.readString();
        title = in.readString();
        pubDate = in.readString();
    }

    public static final Creator<Feed> CREATOR = new Creator<Feed>() {
        @Override
        public Feed createFromParcel(Parcel in) {
            return new Feed(in);
        }

        @Override
        public Feed[] newArray(int size) {
            return new Feed[size];
        }
    };

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(pubDate);
        dest.writeString(author);
        dest.writeString(title);
        dest.writeString(link);

    }

}
