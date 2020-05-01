package com.example.rssfeed.API.res;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Feed implements Serializable {
    @SerializedName("author")
    private String author;
    @SerializedName("link")
    private String link;
    @SerializedName("foreignMarkup")
    private String foreignMarkup;
    @SerializedName("categories")
    private String categories;
    @SerializedName("title")
    private String title;
    @SerializedName("enclosures")
    private String enclosures;
    @SerializedName("pubDate")
    private String pubDate;
    @SerializedName("modules")
    private String modules;

    public Feed(String author, String link, String foreignMarkup, String categories, String title, String enclosures, String pubDate, String modules) {
        this.author = author;
        this.link = link;
        this.foreignMarkup = foreignMarkup;
        this.categories = categories;
        this.title = title;
        this.enclosures = enclosures;
        this.pubDate = pubDate;
        this.modules = modules;
    }

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

    public String getForeignMarkup() {
        return foreignMarkup;
    }

    public void setForeignMarkup(String foreignMarkup) {
        this.foreignMarkup = foreignMarkup;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEnclosures() {
        return enclosures;
    }

    public void setEnclosures(String enclosures) {
        this.enclosures = enclosures;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getModules() {
        return modules;
    }

    public void setModules(String modules) {
        this.modules = modules;
    }

}
