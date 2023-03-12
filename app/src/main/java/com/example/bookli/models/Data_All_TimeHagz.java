package com.example.bookli.models;

public class Data_All_TimeHagz {

    String id,dayTitle,timestart,timeEnd,calendarDate;

    public Data_All_TimeHagz(String id, String dayTitle, String timestart, String timeEnd, String calendarDate) {
        this.id = id;
        this.dayTitle = dayTitle;
        this.timestart = timestart;
        this.timeEnd = timeEnd;
        this.calendarDate = calendarDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDayTitle() {
        return dayTitle;
    }

    public void setDayTitle(String dayTitle) {
        this.dayTitle = dayTitle;
    }

    public String getTimestart() {
        return timestart;
    }

    public void setTimestart(String timestart) {
        this.timestart = timestart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getCalendarDate() {
        return calendarDate;
    }

    public void setCalendarDate(String calendarDate) {
        this.calendarDate = calendarDate;
    }
}
