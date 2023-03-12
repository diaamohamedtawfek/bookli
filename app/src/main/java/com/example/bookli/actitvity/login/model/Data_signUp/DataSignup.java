
package com.example.bookli.actitvity.login.model.Data_signUp;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataSignup {

    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("fullName")
    @Expose
    private String fullName;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("patientFlag")
    @Expose
    private Integer patientFlag;
    @SerializedName("role")
    @Expose
    private List<Role> role = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public DataSignup() {
    }

    /**
     * 
     * @param password
     * @param phoneNumber
     * @param role
     * @param fullName
     * @param email
     * @param patientFlag
     */
    public DataSignup(String email, String fullName, String password, String phoneNumber, Integer patientFlag, List<Role> role) {
        super();
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.patientFlag = patientFlag;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getPatientFlag() {
        return patientFlag;
    }

    public void setPatientFlag(Integer patientFlag) {
        this.patientFlag = patientFlag;
    }

    public List<Role> getRole() {
        return role;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }

}
