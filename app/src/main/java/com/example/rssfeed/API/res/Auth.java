package com.example.rssfeed.API.res;

import com.google.gson.annotations.SerializedName;

public class Auth {
    @SerializedName("password")
    public String password;
    @SerializedName("email")
    public String email;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
