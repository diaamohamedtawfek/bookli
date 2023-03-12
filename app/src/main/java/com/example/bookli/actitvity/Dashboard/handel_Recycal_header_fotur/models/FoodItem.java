package com.example.bookli.actitvity.Dashboard.handel_Recycal_header_fotur.models;

//Object of food item
public class FoodItem extends RecyclerViewItem {
    String image;
    String namedoc,specialist,id,saved;

    int rate;

    public FoodItem(String image, String namedoc, String specialist, int rate,String id,String saved) {
        this.image = image;
        this.namedoc = namedoc;
        this.specialist = specialist;
        this.rate = rate;
        this.id=id;
        this.saved=saved;
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
