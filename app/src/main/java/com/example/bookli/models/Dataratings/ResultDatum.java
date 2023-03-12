
package com.example.bookli.models.Dataratings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultDatum {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("createdById")
    @Expose
    private Integer createdById;
    @SerializedName("createdDate")
    @Expose
    private String createdDate;
    @SerializedName("lastModifiedById")
    @Expose
    private Integer lastModifiedById;
    @SerializedName("lastModifiedDate")
    @Expose
    private String lastModifiedDate;
    @SerializedName("deletedFlg")
    @Expose
    private Integer deletedFlg;
    @SerializedName("version")
    @Expose
    private Integer version;
    @SerializedName("drComment")
    @Expose
    private String drComment;
    @SerializedName("drRating")
    @Expose
    private Float drRating;
    @SerializedName("reservation")
    @Expose
    private Object reservation;
    @SerializedName("doctor")
    @Expose
    private Object doctor;
    @SerializedName("commentDate")
    @Expose
    private String commentDate;
    @SerializedName("clinic")
    @Expose
    private Object clinic;
    @SerializedName("patient")
    @Expose
    private Patient patient;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Integer createdById) {
        this.createdById = createdById;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getLastModifiedById() {
        return lastModifiedById;
    }

    public void setLastModifiedById(Integer lastModifiedById) {
        this.lastModifiedById = lastModifiedById;
    }

    public String getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Integer getDeletedFlg() {
        return deletedFlg;
    }

    public void setDeletedFlg(Integer deletedFlg) {
        this.deletedFlg = deletedFlg;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

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

    public Object getReservation() {
        return reservation;
    }

    public void setReservation(Object reservation) {
        this.reservation = reservation;
    }

    public Object getDoctor() {
        return doctor;
    }

    public void setDoctor(Object doctor) {
        this.doctor = doctor;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }

    public Object getClinic() {
        return clinic;
    }

    public void setClinic(Object clinic) {
        this.clinic = clinic;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

}
