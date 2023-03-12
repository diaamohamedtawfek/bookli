package com.example.bookli.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookli.R;
import com.example.bookli.actitvity.CancelEvent;
import com.example.bookli.actitvity.ProfileDoctor;
import com.example.bookli.models.Data_All_Event_In_Same_Day;
import com.example.bookli.models.Data_all_Doc_Catogry;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter_All_doc_EventinDay extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int previousPosition = 0;

    List<Data_All_Event_In_Same_Day> List_Item;
    private Context context;

    public RecyclerViewAdapter_All_doc_EventinDay(List<Data_All_Event_In_Same_Day> list_Item, Context context) {
        List_Item = list_Item;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View menu1 = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.item_doctor_event_inday, viewGroup, false);
        return new MenuItemViewHolder(menu1);

    }

    public void filterList(ArrayList<Data_All_Event_In_Same_Day> filteredList) {
        List_Item = filteredList;
        //recyclerView_dAdapter.notifyDataSetChanged();

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        MenuItemViewHolder menuItemHolder = (MenuItemViewHolder) holder;

        //menuItemHolder.namemared.setText(List_Item.get(position).getType());

        if (!List_Item.get(position).getImage().isEmpty()) {
            Picasso.with(context)
                    .load(List_Item.get(position).getImage())
                    .into(menuItemHolder.imageView);
        }

        menuItemHolder.namedoc.setText(List_Item.get(position).getName());
        menuItemHolder.spiseal.setText(List_Item.get(position).getSection());

        menuItemHolder.timestart_end.setText(List_Item.get(position).getStart_end());
        menuItemHolder.location.setText(List_Item.get(position).getLocation());

        menuItemHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, CancelEvent.class);
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

        TextView timestart_end,location;
        CardView cardView;


        public MenuItemViewHolder(View view) {
            super(view);

            imageView = itemView.findViewById(R.id.profile_image_body_home);
            namedoc=itemView.findViewById(R.id.text_namedoc_body_home);
            timestart_end=itemView.findViewById(R.id.text_timestart_end_event);

            location=itemView.findViewById(R.id.text_location_Doctor_event);
            spiseal=itemView.findViewById(R.id.text_spisial_body_home);

            cardView=itemView.findViewById(R.id.card_alldoctor);


//            namecustomer=(TextView) view.findViewById(R.id.text_namecustomer_listdelvery);
//            phonecustomer=(TextView) view.findViewById(R.id.text_phonecustomer_listdelvery);
//
//            idorder=(TextView) view.findViewById(R.id.text_id_fatora_listdelvery);
        }
    }
}
