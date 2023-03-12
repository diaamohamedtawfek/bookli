package com.example.bookli.models;

public class Data_categry_home {

    String image,type,id;

    public Data_categry_home(String image, String type, String id) {
        this.image = image;
        this.type = type;
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
