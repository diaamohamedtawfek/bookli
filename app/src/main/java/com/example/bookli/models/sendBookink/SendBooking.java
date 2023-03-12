
package com.example.bookli.models.sendBookink;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SendBooking {

    @SerializedName("reservationDate")
    @Expose
    private String reservationDate;
    @SerializedName("doctor")
    @Expose
    private Doctor doctor;
    @SerializedName("calendar")
    @Expose
    private Calendar calendar;
    @SerializedName("clinic")
    @Expose
    private Clinic clinic;
    @SerializedName("clinicBranch")
    @Expose
    private ClinicBranch clinicBranch;

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public ClinicBranch getClinicBranch() {
        return clinicBranch;
    }

    public void setClinicBranch(ClinicBranch clinicBranch) {
        this.clinicBranch = clinicBranch;
    }

}
