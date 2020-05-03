package com.example.rssfeed.API;
import com.example.rssfeed.API.res.Feeds;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("/login")
    Call<Void> loginUser(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("/signup")
    Call<Void> signupUser(@Field("password") String password, @Field("confirm") String confirm, @Field("username") String email);

    // authed routes

    @POST("/rss")
    Call<Feeds> getRssFeed(@Header("Cookie") String cookieSession);

    @POST("/addFeed")
    Call<Void> addFeed(@Header("Cookie") String cookieSession, @Query("feed") String feed);

    @POST("/rmFeed")
    Call<Void> delFeed(@Header("Cookie") String cookieSession, @Query("feed") String feed);

    @POST("/deleteAccount")
    Call<Void> delAccount(@Header("Cookie") String cookieSession);

    /*@POST("/addFeed?feed=<feed link>")
    Call<UserList> doCreateUserWithField(@Field("name") String name, @Field("job") String job);*/
}
