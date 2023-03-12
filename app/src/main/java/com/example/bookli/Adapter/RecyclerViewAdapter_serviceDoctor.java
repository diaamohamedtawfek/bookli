package com.example.bookli.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bookli.R;
import com.example.bookli.models.Data_doctorService;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter_serviceDoctor extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int previousPosition = 0;

    List<Data_doctorService> List_Item;
    private Context context;

    public RecyclerViewAdapter_serviceDoctor(List<Data_doctorService> list_Item, Context context) {
        List_Item = list_Item;
        this.context = context;
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View menu1 = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.item_doctorservices, viewGroup, false);
        return new RecyclerViewAdapter_serviceDoctor.MenuItemViewHolder(menu1);

    }

    public void filterList(ArrayList<Data_doctorService> filteredList) {
        List_Item = filteredList;
        //recyclerView_dAdapter.notifyDataSetChanged();

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        MenuItemViewHolder menuItemHolder = (MenuItemViewHolder) holder;

        //menuItemHolder.namemared.setText(List_Item.get(position).getType());

        menuItemHolder.servisedoctor.setText(List_Item.get(position).getServices());

        previousPosition = position;


    }
    @Override
    public int getItemCount() {
        return (null != List_Item ? List_Item.size() : 0);
    }

    protected class MenuItemViewHolder extends RecyclerView.ViewHolder {

        TextView servisedoctor;


        public MenuItemViewHolder(View view) {
            super(view);


            servisedoctor=itemView.findViewById(R.id.text_servicedoctor_about);

        }
    }
}
