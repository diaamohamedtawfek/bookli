
package com.example.bookli.actitvity.login.model.dataResponceLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataResponseLogin {

    @SerializedName("errorStatus")
    @Expose
    private Boolean errorStatus;
    @SerializedName("resultData")
    @Expose
    private ResultData resultData;

    /**
     * No args constructor for use in serialization
     * 
     */
    public DataResponseLogin() {
    }

    /**
     * 
     * @param resultData
     * @param errorStatus
     */
    public DataResponseLogin(Boolean errorStatus, ResultData resultData) {
        super();
        this.errorStatus = errorStatus;
        this.resultData = resultData;
    }

    public Boolean getErrorStatus() {
        return errorStatus;
    }

    public void setErrorStatus(Boolean errorStatus) {
        this.errorStatus = errorStatus;
    }

    public ResultData getResultData() {
        return resultData;
    }

    public void setResultData(ResultData resultData) {
        this.resultData = resultData;
    }

}
