
package com.example.bookli.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SavedUnSavedDoctor {

    @SerializedName("doctorId")
    @Expose
    private Integer doctorId;
    @SerializedName("favoriteFlag")
    @Expose
    private Integer favoriteFlag;

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public Integer getFavoriteFlag() {
        return favoriteFlag;
    }

    public void setFavoriteFlag(Integer favoriteFlag) {
        this.favoriteFlag = favoriteFlag;
    }

}
