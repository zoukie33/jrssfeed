package com.example.rssfeed.API;
import com.example.rssfeed.API.res.Auth;
import com.example.rssfeed.API.res.Rss;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("/login")
    Call<Auth> loginUser(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("/signup")
    Call<Auth> signupUser(@Field("password") String password, @Field("confirm") String confirm, @Field("username") String email);

    // authed routes
    @FormUrlEncoded
    @POST("/rss")
    Call<Rss> doGetUserList(@Query("rssLink") String rssLink);

    /*@POST("/addFeed?feed=<feed link>")
    Call<UserList> doCreateUserWithField(@Field("name") String name, @Field("job") String job);*/
}
