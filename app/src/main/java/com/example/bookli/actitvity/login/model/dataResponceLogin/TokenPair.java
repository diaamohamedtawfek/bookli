
package com.example.bookli.actitvity.login.model.dataResponceLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TokenPair {

    @SerializedName("jwt")
    @Expose
    private String jwt;
    @SerializedName("refreshToken")
    @Expose
    private String refreshToken;
    @SerializedName("expireTime")
    @Expose
    private Object expireTime;

    /**
     * No args constructor for use in serialization
     * 
     */
    public TokenPair() {
    }

    /**
     * 
     * @param expireTime
     * @param jwt
     * @param refreshToken
     */
    public TokenPair(String jwt, String refreshToken, Object expireTime) {
        super();
        this.jwt = jwt;
        this.refreshToken = refreshToken;
        this.expireTime = expireTime;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Object getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Object expireTime) {
        this.expireTime = expireTime;
    }

}
