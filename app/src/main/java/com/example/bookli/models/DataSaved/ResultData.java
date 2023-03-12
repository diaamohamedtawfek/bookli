
package com.example.bookli.models.DataSaved;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultData {

    @SerializedName("resultData")
    @Expose
    private List<ResultDatum> resultData = null;
    @SerializedName("totalItemsCount")
    @Expose
    private Integer totalItemsCount;

    public List<ResultDatum> getResultData() {
        return resultData;
    }

    public void setResultData(List<ResultDatum> resultData) {
        this.resultData = resultData;
    }

    public Integer getTotalItemsCount() {
        return totalItemsCount;
    }

    public void setTotalItemsCount(Integer totalItemsCount) {
        this.totalItemsCount = totalItemsCount;
    }

}
