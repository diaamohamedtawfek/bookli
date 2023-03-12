package com.example.bookli.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bookli.R;
import com.example.bookli.actitvity.All_Doc_Categories;
import com.example.bookli.actitvity.SearchActivity;
import com.example.bookli.models.Data_all_Doc_Catogry;
import com.example.bookli.models.Data_categry_home;
import com.example.bookli.models.Data_location_location;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter_All_Location extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int previousPosition = 0;

    List<Data_location_location> List_Item;
    private Context context;
    String deirection;
    Activity activity;

    public RecyclerViewAdapter_All_Location(List<Data_location_location> list_Item, Activity activity, Context context, String deirection) {
        List_Item = list_Item;
        this.context = context;
        this.deirection=deirection;
        this.activity=activity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View menu1 = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.item_all_categry, viewGroup, false);
        return new MenuItemViewHolder(menu1);

    }

    public void filterList(ArrayList<Data_location_location> filteredList) {
        List_Item = filteredList;
        //recyclerView_dAdapter.notifyDataSetChanged();

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        MenuItemViewHolder menuItemHolder = (MenuItemViewHolder) holder;

        menuItemHolder.namemared.setText(List_Item.get(position).getNamelocation());

        menuItemHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, SearchActivity.class);
                intent.putExtra("id_loc", List_Item.get(position).getId());
                intent.putExtra("name_location", List_Item.get(position).getNamelocation());
                context.startActivity(intent);
                activity.finish();

//                if (deirection.equals("dash")) {
//                    Intent intent = new Intent(context, All_Doc_Categories.class);
//                    intent.putExtra("id_Cat", List_Item.get(position).getId());
//                    context.startActivity(intent);
//                    activity.finish();
//                }else {
//                    Intent intent = new Intent(context, SearchActivity.class);
//                    intent.putExtra("id_Cat", List_Item.get(position).getId());
//                    intent.putExtra("name", List_Item.get(position).getType());
//                    context.startActivity(intent);
//                    activity.finish();
//                }
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
