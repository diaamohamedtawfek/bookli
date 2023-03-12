package com.example.bookli.actitvity.login.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RefrechToken {

    @SerializedName("refreshToken")
    @Expose
    private String refreshToken;


    public RefrechToken() {
    }


    public RefrechToken(String userEmail) {
        super();
        this.refreshToken = userEmail;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
