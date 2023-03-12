package com.example.bookli.actitvity.Dashboard.handel_Recycal_header_fotur.models;

/**
 * Created by amary on 25-03-2018.
 */
//Object of Header item
public class Header extends RecyclerViewItem{

    String urlpathBaner;

    public Header(String urlpathBaner) {
        this.urlpathBaner = urlpathBaner;
    }

    public String getUrlpathBaner() {
        return urlpathBaner;
    }

    public void setUrlpathBaner(String urlpathBaner) {
        this.urlpathBaner = urlpathBaner;
    }
}
