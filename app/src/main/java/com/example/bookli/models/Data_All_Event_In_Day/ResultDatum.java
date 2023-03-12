
package com.example.bookli.models.Data_All_Event_In_Day;

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
    @SerializedName("doctor")
    @Expose
    private Doctor doctor;
    @SerializedName("calendar")
    @Expose
    private Calendar calendar;
    @SerializedName("clinicBranch")
    @Expose
    private ClinicBranch clinicBranch;
    @SerializedName("deletedFlg")
    @Expose
    private Integer deletedFlg;
    @SerializedName("version")
    @Expose
    private Integer version;
    @SerializedName("reservationStatusFlag")
    @Expose
    private String reservationStatusFlag;
    @SerializedName("medicalExaminationFlag")
    @Expose
    private String medicalExaminationFlag;
    @SerializedName("evaluationStatusFlag")
    @Expose
    private String evaluationStatusFlag;
    @SerializedName("reservationDiscountPercent")
    @Expose
    private Integer reservationDiscountPercent;
    @SerializedName("reservationAmount")
    @Expose
    private String reservationAmount;
    @SerializedName("evaluationValue")
    @Expose
    private Object evaluationValue;
    @SerializedName("reservationDate")
    @Expose
    private String reservationDate;

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

    public ClinicBranch getClinicBranch() {
        return clinicBranch;
    }

    public void setClinicBranch(ClinicBranch clinicBranch) {
        this.clinicBranch = clinicBranch;
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

    public String getReservationStatusFlag() {
        return reservationStatusFlag;
    }

    public void setReservationStatusFlag(String reservationStatusFlag) {
        this.reservationStatusFlag = reservationStatusFlag;
    }

    public String getMedicalExaminationFlag() {
        return medicalExaminationFlag;
    }

    public void setMedicalExaminationFlag(String medicalExaminationFlag) {
        this.medicalExaminationFlag = medicalExaminationFlag;
    }

    public String getEvaluationStatusFlag() {
        return evaluationStatusFlag;
    }

    public void setEvaluationStatusFlag(String evaluationStatusFlag) {
        this.evaluationStatusFlag = evaluationStatusFlag;
    }

    public Integer getReservationDiscountPercent() {
        return reservationDiscountPercent;
    }

    public void setReservationDiscountPercent(Integer reservationDiscountPercent) {
        this.reservationDiscountPercent = reservationDiscountPercent;
    }

    public String getReservationAmount() {
        return reservationAmount;
    }

    public void setReservationAmount(String reservationAmount) {
        this.reservationAmount = reservationAmount;
    }

    public Object getEvaluationValue() {
        return evaluationValue;
    }

    public void setEvaluationValue(Object evaluationValue) {
        this.evaluationValue = evaluationValue;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }

}
