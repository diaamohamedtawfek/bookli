package com.example.bookli.models;

public class Data_Saved {

    String id,image,spem_,rate,name,saved;

    public Data_Saved(String id, String image, String spem_, String rate, String name, String saved) {
        this.id = id;
        this.image = image;
        this.spem_ = spem_;
        this.rate = rate;
        this.saved = saved;
        this.name = name;
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

    public String getSpem_() {
        return spem_;
    }

    public void setSpem_(String spem_) {
        this.spem_ = spem_;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
