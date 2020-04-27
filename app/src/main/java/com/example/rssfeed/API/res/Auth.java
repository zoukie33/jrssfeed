package com.example.rssfeed.API.res;

import com.google.gson.annotations.SerializedName;

public class Auth {
    @SerializedName("username")
    public String username;
    @SerializedName("password")
    public String password;
    @SerializedName("email")
    public String email;
}
