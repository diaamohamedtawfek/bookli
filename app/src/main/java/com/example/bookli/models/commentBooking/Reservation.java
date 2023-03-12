
package com.example.bookli.models.commentBooking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Reservation {

    @SerializedName("id")
    @Expose
    private Float id;

    public Float getId() {
        return id;
    }

    public void setId(Float id) {
        this.id = id;
    }

}
