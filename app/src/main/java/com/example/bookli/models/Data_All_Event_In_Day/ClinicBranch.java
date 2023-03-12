
package com.example.bookli.models.Data_All_Event_In_Day;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClinicBranch {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("createdById")
    @Expose
    private Integer createdById;
    @SerializedName("createdDate")
    @Expose
    private Object createdDate;
    @SerializedName("lastModifiedById")
    @Expose
    private Integer lastModifiedById;
    @SerializedName("lastModifiedDate")
    @Expose
    private Object lastModifiedDate;
    @SerializedName("shortAddress")
    @Expose
    private Object shortAddress;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("detailAddress")
    @Expose
    private Object detailAddress;
    @SerializedName("clinicPolicy")
    @Expose
    private Object clinicPolicy;

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

    public Object getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Object createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getLastModifiedById() {
        return lastModifiedById;
    }

    public void setLastModifiedById(Integer lastModifiedById) {
        this.lastModifiedById = lastModifiedById;
    }

    public Object getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Object lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Object getShortAddress() {
        return shortAddress;
    }

    public void setShortAddress(Object shortAddress) {
        this.shortAddress = shortAddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Object getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(Object detailAddress) {
        this.detailAddress = detailAddress;
    }

    public Object getClinicPolicy() {
        return clinicPolicy;
    }

    public void setClinicPolicy(Object clinicPolicy) {
        this.clinicPolicy = clinicPolicy;
    }

}
