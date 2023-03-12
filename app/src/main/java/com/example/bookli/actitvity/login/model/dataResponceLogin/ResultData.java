
package com.example.bookli.actitvity.login.model.dataResponceLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultData {

    @SerializedName("tokenPair")
    @Expose
    private TokenPair tokenPair;
    @SerializedName("user")
    @Expose
    private User user;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ResultData() {
    }

    /**
     * 
     * @param user
     * @param tokenPair
     */
    public ResultData(TokenPair tokenPair, User user) {
        super();
        this.tokenPair = tokenPair;
        this.user = user;
    }

    public TokenPair getTokenPair() {
        return tokenPair;
    }

    public void setTokenPair(TokenPair tokenPair) {
        this.tokenPair = tokenPair;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
