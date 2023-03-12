package com.example.bookli.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookli.R;
import com.example.bookli.actitvity.All_Doc_Categories;
import com.example.bookli.actitvity.SearchActivity;
import com.example.bookli.models.Data_categry_home;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter_AllCatigry  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int previousPosition = 0;

    List<Data_categry_home> List_Item;
    private Context context;
    String deirection;
    Activity activity;
    String namelocation;
    String idlocation;

    public RecyclerViewAdapter_AllCatigry(List<Data_categry_home> list_Item, Activity activity,
                                          Context context, String deirection,String namelocation,String idlocation ) {
        List_Item = list_Item;
        this.context = context;
        this.deirection=deirection;
        this.activity=activity;
        this.namelocation=namelocation;
        this.idlocation=idlocation;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View menu1 = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.item_all_categry, viewGroup, false);
        return new MenuItemViewHolder(menu1);

    }

    public void filterList(ArrayList<Data_categry_home> filteredList) {
        List_Item = filteredList;
        //recyclerView_dAdapter.notifyDataSetChanged();

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        MenuItemViewHolder menuItemHolder = (MenuItemViewHolder) holder;

        menuItemHolder.namemared.setText(List_Item.get(position).getType());

        menuItemHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (deirection.equals("dash")) {
                    Intent intent = new Intent(context, All_Doc_Categories.class);
                    intent.putExtra("id_Cat", List_Item.get(position).getId());
                    intent.putExtra("from", "");
                    context.startActivity(intent);
//                    activity.finish();
                }else {
                    Intent intent = new Intent(context, SearchActivity.class);
                    intent.putExtra("id_Cat", List_Item.get(position).getId());
                    intent.putExtra("name", List_Item.get(position).getType());

                    intent.putExtra("id_loc", idlocation);
                    intent.putExtra("name_location", namelocation);

                    context.startActivity(intent);
//                    activity.finish();
                }
            }
        });
        if (position > previousPosition) { //scrolling DOWN
            //AnimationUtil.animate(menuItemHolder, true);

        } else { // scrolling UP

            // AnimationUtil.animate(menuItemHolder, false);
        }
        previousPosition = position;


    }
    @Override
    public int getItemCount() {
        return (null != List_Item ? List_Item.size() : 0);
    }

    protected class MenuItemViewHolder extends RecyclerView.ViewHolder {
        LinearLayout cardView;
        ImageView imageView;
        TextView namemared,namecustomer,phonecustomer,idorder;

        public MenuItemViewHolder(View view) {
            super(view);
            cardView = (LinearLayout) view.findViewById(R.id.card_listdelvery);
            //imageView = (ImageView) view.findViewById(R.id.imageView);
            namemared = (TextView) view.findViewById(R.id.text_namemared_listdelvery);

//            namecustomer=(TextView) view.findViewById(R.id.text_namecustomer_listdelvery);
//            phonecustomer=(TextView) view.findViewById(R.id.text_phonecustomer_listdelvery);
//
//            idorder=(TextView) view.findViewById(R.id.text_id_fatora_listdelvery);
        }
    }
}
