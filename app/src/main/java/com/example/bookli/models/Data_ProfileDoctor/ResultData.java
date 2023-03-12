
package com.example.bookli.models.Data_ProfileDoctor;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultData {

    @SerializedName("id")
    @Expose
    private Float id;
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
    private String fullName;
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
    private String email;
    @SerializedName("fullNameEn")
    @Expose
    private Object fullNameEn;
    @SerializedName("savedFlag")
    @Expose
    private Integer savedFlag;
    @SerializedName("reservationsFees")
    @Expose
    private Object reservationsFees;
    @SerializedName("reservationsCount")
    @Expose
    private Integer reservationsCount;
    @SerializedName("ratingCount")
    @Expose
    private Integer ratingCount;
    @SerializedName("clinicFullAddress")
    @Expose
    private String clinicFullAddress;
    @SerializedName("doctorOverview")
    @Expose
    private String doctorOverview;
    @SerializedName("doctorServiceMobileDtoList")
    @Expose
    private List<DoctorServiceMobileDtoList> doctorServiceMobileDtoList = null;

    public Float getId() {
        return id;
    }

    public void setId(Float id) {
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object getFullNameEn() {
        return fullNameEn;
    }

    public void setFullNameEn(Object fullNameEn) {
        this.fullNameEn = fullNameEn;
    }

    public Integer getSavedFlag() {
        return savedFlag;
    }

    public void setSavedFlag(Integer savedFlag) {
        this.savedFlag = savedFlag;
    }

    public Object getReservationsFees() {
        return reservationsFees;
    }

    public void setReservationsFees(Object reservationsFees) {
        this.reservationsFees = reservationsFees;
    }

    public Integer getReservationsCount() {
        return reservationsCount;
    }

    public void setReservationsCount(Integer reservationsCount) {
        this.reservationsCount = reservationsCount;
    }

    public Integer getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(Integer ratingCount) {
        this.ratingCount = ratingCount;
    }

    public String getClinicFullAddress() {
        return clinicFullAddress;
    }

    public void setClinicFullAddress(String clinicFullAddress) {
        this.clinicFullAddress = clinicFullAddress;
    }

    public String getDoctorOverview() {
        return doctorOverview;
    }

    public void setDoctorOverview(String doctorOverview) {
        this.doctorOverview = doctorOverview;
    }

    public List<DoctorServiceMobileDtoList> getDoctorServiceMobileDtoList() {
        return doctorServiceMobileDtoList;
    }

    public void setDoctorServiceMobileDtoList(List<DoctorServiceMobileDtoList> doctorServiceMobileDtoList) {
        this.doctorServiceMobileDtoList = doctorServiceMobileDtoList;
    }

}
