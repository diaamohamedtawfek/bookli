package com.example.bookli.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bookli.R;
import com.example.bookli.models.Data_Rates;
import com.example.bookli.models.Data_doctorService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter_Rates extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int previousPosition = 0;

    List<Data_Rates> List_Item;
    private Context context;

    public RecyclerViewAdapter_Rates(List<Data_Rates> list_Item, Context context) {
        List_Item = list_Item;
        this.context = context;
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View menu1 = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.item_rates, viewGroup, false);
        return new RecyclerViewAdapter_Rates.MenuItemViewHolder(menu1);

    }

    public void filterList(ArrayList<Data_Rates> filteredList) {
        List_Item = filteredList;
        //recyclerView_dAdapter.notifyDataSetChanged();

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        MenuItemViewHolder menuItemHolder = (MenuItemViewHolder) holder;

        //menuItemHolder.namemared.setText(List_Item.get(position).getType());

        menuItemHolder.name.setText(List_Item.get(position).getName_doctor());
        menuItemHolder.comment.setText(List_Item.get(position).getComment());
        menuItemHolder.time.setText(List_Item.get(position).getTimes());

        try {
            Picasso.with(context)
                    .load(List_Item.get(position).getImage())
                    .placeholder(R.drawable.person)
                    .into(menuItemHolder.image);
        }catch (Exception e){
            menuItemHolder.image.setImageResource(R.drawable.person);
        }

        if (!List_Item.get(position).getRates().equals(null)||!List_Item.get(position).getRates().equals("null")||
                List_Item.get(position).getRates()!="null"||List_Item.get(position).getRates()!=null) {
            try {
                menuItemHolder.ratingBar.setRating(Float.parseFloat(List_Item.get(position).getRates()));
                menuItemHolder.ratingBar.setIsIndicator(true);
                menuItemHolder.ratingBar.setActivated(false);
            }catch (Exception e){

            }
        }else{
            menuItemHolder.ratingBar.setRating(Float.parseFloat("0"));
            menuItemHolder.ratingBar.setIsIndicator(true);
            menuItemHolder.ratingBar.setActivated(false);
        }

        previousPosition = position;


    }
    @Override
    public int getItemCount() {
        return (null != List_Item ? List_Item.size() : 0);
    }

    protected class MenuItemViewHolder extends RecyclerView.ViewHolder {

        TextView name,comment,time;
        RatingBar ratingBar;

        CircleImageView image;

        public MenuItemViewHolder(View view) {
            super(view);


            name=itemView.findViewById(R.id.text_namedoc_body_home);
            comment=itemView.findViewById(R.id.text_comment_rates);
            time=itemView.findViewById(R.id.text_time_rate);
            ratingBar=itemView.findViewById(R.id.rating);
            image=itemView.findViewById(R.id.profile_image_body_home);

        }
    }
}
