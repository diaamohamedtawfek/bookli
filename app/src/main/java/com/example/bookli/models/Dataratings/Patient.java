
package com.example.bookli.models.Dataratings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Patient {

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
    @SerializedName("patientCode")
    @Expose
    private String patientCode;
    @SerializedName("patientFullName")
    @Expose
    private String patientFullName;
    @SerializedName("patientAddress")
    @Expose
    private String patientAddress;
    @SerializedName("patientAge")
    @Expose
    private Integer patientAge;
    @SerializedName("patientSex")
    @Expose
    private String patientSex;
    @SerializedName("typeOfDiseaseFlag")
    @Expose
    private String typeOfDiseaseFlag;
    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("alternatePhoneNumber")
    @Expose
    private String alternatePhoneNumber;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("patientProfilePicturePath")
    @Expose
    private String patientProfilePicturePath;

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

    public String getPatientCode() {
        return patientCode;
    }

    public void setPatientCode(String patientCode) {
        this.patientCode = patientCode;
    }

    public String getPatientFullName() {
        return patientFullName;
    }

    public void setPatientFullName(String patientFullName) {
        this.patientFullName = patientFullName;
    }

    public String getPatientAddress() {
        return patientAddress;
    }

    public void setPatientAddress(String patientAddress) {
        this.patientAddress = patientAddress;
    }

    public Integer getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(Integer patientAge) {
        this.patientAge = patientAge;
    }

    public String getPatientSex() {
        return patientSex;
    }

    public void setPatientSex(String patientSex) {
        this.patientSex = patientSex;
    }

    public String getTypeOfDiseaseFlag() {
        return typeOfDiseaseFlag;
    }

    public void setTypeOfDiseaseFlag(String typeOfDiseaseFlag) {
        this.typeOfDiseaseFlag = typeOfDiseaseFlag;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAlternatePhoneNumber() {
        return alternatePhoneNumber;
    }

    public void setAlternatePhoneNumber(String alternatePhoneNumber) {
        this.alternatePhoneNumber = alternatePhoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPatientProfilePicturePath() {
        return patientProfilePicturePath;
    }

    public void setPatientProfilePicturePath(String patientProfilePicturePath) {
        this.patientProfilePicturePath = patientProfilePicturePath;
    }

}
