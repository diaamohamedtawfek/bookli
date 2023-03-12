
package com.example.bookli.models.Data_timeHagz_doctor;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeekDay {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("dayTitle")
    @Expose
    private String dayTitle;
    @SerializedName("reservationTimeFrom")
    @Expose
    private String reservationTimeFrom;
    @SerializedName("reservationTimeTo")
    @Expose
    private String reservationTimeTo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDayTitle() {
        return dayTitle;
    }

    public void setDayTitle(String dayTitle) {
        this.dayTitle = dayTitle;
    }

    public String getReservationTimeFrom() {
        return reservationTimeFrom;
    }

    public void setReservationTimeFrom(String reservationTimeFrom) {
        this.reservationTimeFrom = reservationTimeFrom;
    }

    public String getReservationTimeTo() {
        return reservationTimeTo;
    }

    public void setReservationTimeTo(String reservationTimeTo) {
        this.reservationTimeTo = reservationTimeTo;
    }

}
