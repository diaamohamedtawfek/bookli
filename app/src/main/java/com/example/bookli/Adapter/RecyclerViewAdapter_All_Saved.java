package com.example.bookli.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookli.R;
import com.example.bookli.actitvity.ProfileDoctor;
import com.example.bookli.models.Data_Saved;
import com.example.bookli.models.Data_all_Doc_Catogry;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter_All_Saved  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int previousPosition = 0;

    List<Data_Saved> List_Item;
    private Context context;

    public RecyclerViewAdapter_All_Saved(List<Data_Saved> list_Item, Context context) {
        List_Item = list_Item;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View menu1 = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.item_saved, viewGroup, false);
        return new RecyclerViewAdapter_All_Saved.MenuItemViewHolder(menu1);

    }

    public void filterList(ArrayList<Data_Saved> filteredList) {
        List_Item = filteredList;
        //recyclerView_dAdapter.notifyDataSetChanged();

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        RecyclerViewAdapter_All_Saved.MenuItemViewHolder menuItemHolder = (RecyclerViewAdapter_All_Saved.MenuItemViewHolder) holder;

        //menuItemHolder.namemared.setText(List_Item.get(position).getType());

        try {
            Picasso.with(context)
                    .load(List_Item.get(position).getImage())
                    .into(menuItemHolder.imageView);
        }catch (Exception e){}


        if (List_Item.get(position).getSaved() == "1"){
            menuItemHolder.saved_unSaved.setImageResource(R.drawable.ic_save);
        }else{
            menuItemHolder.saved_unSaved.setImageResource(R.mipmap.ic_unsaved);
        }

        menuItemHolder.namedoc.setText(List_Item.get(position).getName());
        menuItemHolder.spiseal.setText(List_Item.get(position).getSpem_());
        menuItemHolder.ratingBar.setRating(Float.parseFloat(List_Item.get(position).getRate()));
        menuItemHolder.ratingBar.setIsIndicator(true);
        menuItemHolder.ratingBar.setActivated(false);

        menuItemHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ProfileDoctor.class);
                intent.putExtra("id_doctor",List_Item.get(position).getId());
                context.startActivity(intent);
                // finish();
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
        CircleImageView imageView;
        TextView namedoc,spiseal;
        RatingBar ratingBar;
        CardView cardView;

        ImageView saved_unSaved;

        public MenuItemViewHolder(View view) {
            super(view);

            imageView = itemView.findViewById(R.id.profile_image_body_home);
            saved_unSaved = itemView.findViewById(R.id.image_saved_unsaved);

            namedoc=itemView.findViewById(R.id.text_namedoc_body_home);
            spiseal=itemView.findViewById(R.id.text_spisial_body_home);
            ratingBar=itemView.findViewById(R.id.rating);
            cardView=itemView.findViewById(R.id.card_alldoctor);


//            namecustomer=(TextView) view.findViewById(R.id.text_namecustomer_listdelvery);
//            phonecustomer=(TextView) view.findViewById(R.id.text_phonecustomer_listdelvery);
//
//            idorder=(TextView) view.findViewById(R.id.text_id_fatora_listdelvery);
        }
    }
}

