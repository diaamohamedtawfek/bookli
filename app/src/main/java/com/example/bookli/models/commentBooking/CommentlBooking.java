
package com.example.bookli.models.commentBooking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommentlBooking {

    @SerializedName("drComment")
    @Expose
    private String drComment;
    @SerializedName("drRating")
    @Expose
    private Float drRating;
    @SerializedName("clinicComment")
    @Expose
    private String clinicComment;
    @SerializedName("clinicRating")
    @Expose
    private Float clinicRating;
    @SerializedName("reservation")
    @Expose
    private Reservation reservation;

    public String getDrComment() {
        return drComment;
    }

    public void setDrComment(String drComment) {
        this.drComment = drComment;
    }

    public Float getDrRating() {
        return drRating;
    }

    public void setDrRating(Float drRating) {
        this.drRating = drRating;
    }

    public String getClinicComment() {
        return clinicComment;
    }

    public void setClinicComment(String clinicComment) {
        this.clinicComment = clinicComment;
    }

    public Float getClinicRating() {
        return clinicRating;
    }

    public void setClinicRating(Float clinicRating) {
        this.clinicRating = clinicRating;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

}
