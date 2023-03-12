package com.example.bookli.models;

public class Data_Rates {

    String id,image,name_doctor,rates,comment,times;


    public Data_Rates(String id, String image, String name_doctor, String rates, String comment, String times) {
        this.id = id;
        this.image = image;
        this.name_doctor = name_doctor;
        this.rates = rates;
        this.comment = comment;
        this.times = times;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName_doctor() {
        return name_doctor;
    }

    public void setName_doctor(String name_doctor) {
        this.name_doctor = name_doctor;
    }

    public String getRates() {
        return rates;
    }

    public void setRates(String rates) {
        this.rates = rates;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }
}
