package com.example.bookli.models;

public class Data_All_Event_In_Same_Day {
    String image,name,start_end,location,section,id;

    public Data_All_Event_In_Same_Day(String image, String name, String start_end, String location, String section, String id) {
        this.image = image;
        this.name = name;
        this.start_end = start_end;
        this.location = location;
        this.section = section;
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart_end() {
        return start_end;
    }

    public void setStart_end(String start_end) {
        this.start_end = start_end;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }
}
