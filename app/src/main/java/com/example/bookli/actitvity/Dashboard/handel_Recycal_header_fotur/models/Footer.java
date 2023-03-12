package com.example.bookli.actitvity.Dashboard.handel_Recycal_header_fotur.models;

/**
 * Created by amary on 25-03-2018.
 */
//Object of Footer item
public class Footer extends RecyclerViewItem{
    private String Quote;


    public Footer(String quote) {
        Quote = quote;
    }

    public String getQuote() {
        return Quote;
    }

    public void setQuote(String quote) {
        Quote = quote;
    }
}
