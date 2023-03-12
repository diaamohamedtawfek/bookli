
package com.example.bookli.models.DataSaved;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultDatum {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("createdById")
    @Expose
    private Object createdById;
    @SerializedName("createdDate")
    @Expose
    private Object createdDate;
    @SerializedName("lastModifiedById")
    @Expose
    private Object lastModifiedById;
    @SerializedName("lastModifiedDate")
    @Expose
    private Object lastModifiedDate;
    @SerializedName("drCode")
    @Expose
    private String drCode;
    @SerializedName("fullName")
    @Expose
    private Object fullName;
    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("rating")
    @Expose
    private Integer rating;
    @SerializedName("alternatePhone")
    @Expose
    private Object alternatePhone;
    @SerializedName("scientificDegree")
    @Expose
    private String scientificDegree;
    @SerializedName("profilePicPath")
    @Expose
    private String profilePicPath;
    @SerializedName("email")
    @Expose
    private Object email;
    @SerializedName("fullNameEn")
    @Expose
    private Object fullNameEn;
    @SerializedName("savedFlag")
    @Expose
    private Object savedFlag;
    @SerializedName("reservationsFees")
    @Expose
    private Object reservationsFees;
    @SerializedName("reservationsCount")
    @Expose
    private Object reservationsCount;
    @SerializedName("ratingCount")
    @Expose
    private Object ratingCount;
    @SerializedName("clinicFullAddress")
    @Expose
    private Object clinicFullAddress;
    @SerializedName("doctorOverview")
    @Expose
    private Object doctorOverview;
    @SerializedName("doctorServiceMobileDtoList")
    @Expose
    private List<Object> doctorServiceMobileDtoList = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Object getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Object createdById) {
        this.createdById = createdById;
    }

    public Object getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Object createdDate) {
        this.createdDate = createdDate;
    }

    public Object getLastModifiedById() {
        return lastModifiedById;
    }

    public void setLastModifiedById(Object lastModifiedById) {
        this.lastModifiedById = lastModifiedById;
    }

    public Object getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Object lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getDrCode() {
        return drCode;
    }

    public void setDrCode(String drCode) {
        this.drCode = drCode;
    }

    public Object getFullName() {
        return fullName;
    }

    public void setFullName(Object fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Object getAlternatePhone() {
        return alternatePhone;
    }

    public void setAlternatePhone(Object alternatePhone) {
        this.alternatePhone = alternatePhone;
    }

    public String getScientificDegree() {
        return scientificDegree;
    }

    public void setScientificDegree(String scientificDegree) {
        this.scientificDegree = scientificDegree;
    }

    public String getProfilePicPath() {
        return profilePicPath;
    }

    public void setProfilePicPath(String profilePicPath) {
        this.profilePicPath = profilePicPath;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public Object getFullNameEn() {
        return fullNameEn;
    }

    public void setFullNameEn(Object fullNameEn) {
        this.fullNameEn = fullNameEn;
    }

    public Object getSavedFlag() {
        return savedFlag;
    }

    public void setSavedFlag(Object savedFlag) {
        this.savedFlag = savedFlag;
    }

    public Object getReservationsFees() {
        return reservationsFees;
    }

    public void setReservationsFees(Object reservationsFees) {
        this.reservationsFees = reservationsFees;
    }

    public Object getReservationsCount() {
        return reservationsCount;
    }

    public void setReservationsCount(Object reservationsCount) {
        this.reservationsCount = reservationsCount;
    }

    public Object getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(Object ratingCount) {
        this.ratingCount = ratingCount;
    }

    public Object getClinicFullAddress() {
        return clinicFullAddress;
    }

    public void setClinicFullAddress(Object clinicFullAddress) {
        this.clinicFullAddress = clinicFullAddress;
    }

    public Object getDoctorOverview() {
        return doctorOverview;
    }

    public void setDoctorOverview(Object doctorOverview) {
        this.doctorOverview = doctorOverview;
    }

    public List<Object> getDoctorServiceMobileDtoList() {
        return doctorServiceMobileDtoList;
    }

    public void setDoctorServiceMobileDtoList(List<Object> doctorServiceMobileDtoList) {
        this.doctorServiceMobileDtoList = doctorServiceMobileDtoList;
    }

}
