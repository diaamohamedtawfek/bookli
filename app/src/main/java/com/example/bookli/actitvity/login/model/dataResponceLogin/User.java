
package com.example.bookli.actitvity.login.model.dataResponceLogin;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("userEmail")
    @Expose
    private String userEmail;
    @SerializedName("fullName")
    @Expose
    private String fullName;
    @SerializedName("imagePath")
    @Expose
    private String imagePath;
    @SerializedName("activeFlag")
    @Expose
    private Integer activeFlag;
    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("patient")
    @Expose
    private Object patient;
    @SerializedName("clinic")
    @Expose
    private Object clinic;
    @SerializedName("employee")
    @Expose
    private Object employee;
    @SerializedName("roleList")
    @Expose
    private List<RoleList> roleList = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public User() {
    }

    /**
     * 
     * @param phoneNumber
     * @param imagePath
     * @param patient
     * @param fullName
     * @param userEmail
     * @param id
     * @param clinic
     * @param employee
     * @param roleList
     * @param activeFlag
     */
    public User(Integer id, String userEmail, String fullName, String imagePath, Integer activeFlag, String phoneNumber, Object patient, Object clinic, Object employee, List<RoleList> roleList) {
        super();
        this.id = id;
        this.userEmail = userEmail;
        this.fullName = fullName;
        this.imagePath = imagePath;
        this.activeFlag = activeFlag;
        this.phoneNumber = phoneNumber;
        this.patient = patient;
        this.clinic = clinic;
        this.employee = employee;
        this.roleList = roleList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Integer getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(Integer activeFlag) {
        this.activeFlag = activeFlag;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Object getPatient() {
        return patient;
    }

    public void setPatient(Object patient) {
        this.patient = patient;
    }

    public Object getClinic() {
        return clinic;
    }

    public void setClinic(Object clinic) {
        this.clinic = clinic;
    }

    public Object getEmployee() {
        return employee;
    }

    public void setEmployee(Object employee) {
        this.employee = employee;
    }

    public List<RoleList> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<RoleList> roleList) {
        this.roleList = roleList;
    }

}
