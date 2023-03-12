
package com.example.bookli.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SendBookingEnd {

    @SerializedName("calendar")
    @Expose
    private Integer calendar;
    @SerializedName("clinic")
    @Expose
    private Integer clinic;
    @SerializedName("clinicBranch")
    @Expose
    private Integer clinicBranch;
    @SerializedName("doctor")
    @Expose
    private Integer doctor;
    @SerializedName("reservationDate")
    @Expose
    private String reservationDate;

    public Integer getCalendar() {
        return calendar;
    }

    public void setCalendar(Integer calendar) {
        this.calendar = calendar;
    }

    public Integer getClinic() {
        return clinic;
    }

    public void setClinic(Integer clinic) {
        this.clinic = clinic;
    }

    public Integer getClinicBranch() {
        return clinicBranch;
    }

    public void setClinicBranch(Integer clinicBranch) {
        this.clinicBranch = clinicBranch;
    }

    public Integer getDoctor() {
        return doctor;
    }

    public void setDoctor(Integer doctor) {
        this.doctor = doctor;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }

}
