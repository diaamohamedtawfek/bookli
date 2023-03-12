package com.example.bookli.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookli.R;
import com.example.bookli.actitvity.All_Doc_Categories;
import com.example.bookli.models.Data_categry_home;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter_limetCtogry extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int previousPosition = 0;

   public List<Data_categry_home> List_Item;
    private Context context;

    public RecyclerViewAdapter_limetCtogry(List<Data_categry_home> list_Item, Context context) {
        List_Item = list_Item;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View menu1 = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.item_categry_home, viewGroup, false);
        return new RecyclerViewAdapter_limetCtogry.MenuItemViewHolder(menu1);

    }

//    public void filterList(ArrayList<Data_categry_home> filteredList) {
//        List_Item = filteredList;
//        recyclerView_dAdapter.notifyDataSetChanged();
//
//    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        MenuItemViewHolder menuItemHolder = (MenuItemViewHolder) holder;

        menuItemHolder.namecategry.setText(List_Item.get(position).getType());
        Picasso.with(context)
                .load(List_Item.get(position).getImage())
                .into(menuItemHolder.imagecategry);

      menuItemHolder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, All_Doc_Categories.class);
                    intent.putExtra("id_Cat",List_Item.get(position).getId());
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
        CardView cardView;
        CircleImageView imagecategry;
        TextView namecategry;

        public MenuItemViewHolder(View view) {
            super(view);
            cardView = (CardView) view.findViewById(R.id.card_listdelvery);
            //imageView = (ImageView) view.findViewById(R.id.imageView);
            namecategry = (TextView) view.findViewById(R.id.text_namecategry_home);
            imagecategry = (CircleImageView) view.findViewById(R.id.profile_image);


        }
    }

}
