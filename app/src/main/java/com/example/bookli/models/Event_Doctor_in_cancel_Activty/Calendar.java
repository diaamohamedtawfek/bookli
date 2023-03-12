
package com.example.bookli.models.Event_Doctor_in_cancel_Activty;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Calendar {

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
    @SerializedName("weekDay")
    @Expose
    private WeekDay weekDay;
    @SerializedName("drId")
    @Expose
    private Object drId;
    @SerializedName("clinicBranchId")
    @Expose
    private Object clinicBranchId;
    @SerializedName("calendarDate")
    @Expose
    private Object calendarDate;

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

    public WeekDay getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(WeekDay weekDay) {
        this.weekDay = weekDay;
    }

    public Object getDrId() {
        return drId;
    }

    public void setDrId(Object drId) {
        this.drId = drId;
    }

    public Object getClinicBranchId() {
        return clinicBranchId;
    }

    public void setClinicBranchId(Object clinicBranchId) {
        this.clinicBranchId = clinicBranchId;
    }

    public Object getCalendarDate() {
        return calendarDate;
    }

    public void setCalendarDate(Object calendarDate) {
        this.calendarDate = calendarDate;
    }

}
