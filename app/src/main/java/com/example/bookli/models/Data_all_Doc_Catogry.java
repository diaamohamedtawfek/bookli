package com.example.bookli.models;

public class Data_all_Doc_Catogry {

    String image,id;
    String namedoc,specialist,saved;

    int rate;

    public Data_all_Doc_Catogry(String image, String namedoc, String specialist, int rate,String id,String saved) {
        this.image = image;
        this.namedoc = namedoc;
        this.specialist = specialist;
        this.rate = rate;
        this.id = id;
        this.saved = saved;
    }

    public String getSaved() {
        return saved;
    }

    public void setSaved(String saved) {
        this.saved = saved;
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

    public String getNamedoc() {
        return namedoc;
    }

    public void setNamedoc(String namedoc) {
        this.namedoc = namedoc;
    }

    public String getSpecialist() {
        return specialist;
    }

    public void setSpecialist(String specialist) {
        this.specialist = specialist;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
