//package com.example.bookli.Adapter;
//
//import android.content.Context;
//import android.graphics.Color;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.RatingBar;
//import android.widget.TextView;
//
//import androidx.cardview.widget.CardView;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.bookli.InterFace.itemOnClickListener;
//import com.example.bookli.R;
//import com.example.bookli.models.Data_All_TimeHagz;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class RecyclerViewAdapter_timeHagz extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
//
//    private int previousPosition = 0;
//
//    com.example.bookli.InterFace.itemOnClickListener itemOnClickListener;
//    List<Data_All_TimeHagz> List_Item;
//    private Context context;
//    int row_index=0;
//
//    public RecyclerViewAdapter_timeHagz(List<Data_All_TimeHagz> list_Item, Context context) {
//        List_Item = list_Item;
//        this.context = context;
//    }
//
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
//
//        View menu1 = LayoutInflater.from(viewGroup.getContext()).inflate(
//                R.layout.item_time_hagz, viewGroup, false);
//        return new MenuItemViewHolder(menu1,this);
//
//    }
//
//    public void filterList(ArrayList<Data_All_TimeHagz> filteredList) {
//        List_Item = filteredList;
//        //recyclerView_dAdapter.notifyDataSetChanged();
//
//    }
//
//    @Override
//    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
//
//        MenuItemViewHolder menuItemHolder = (MenuItemViewHolder) holder;
//
//        menuItemHolder.datytitel.setText(List_Item.get(position).getDayTitle());
//
//        String value = List_Item.get(position).getCalendarDate();
//        String lastTwo = null;
//        if (value != null && value.length() >= 2) {
//            lastTwo = value.substring(value.length() - 2);
//            menuItemHolder.date.setText(lastTwo);
//        }
//        String test = List_Item.get(position).getCalendarDate();
//        String s=test.substring(5,7);
//        String[] monthName = {"يناير", "فبراير",
//                "مارس", "أبريل", "مايو", "يونيو", "يوليو",
//                "أغسطس", "سبتمبر", "أكتوبر", "نوفمبر",
//                "ديسمبر"};
//        int time=Integer.parseInt(s)-1;
//        String month = monthName[time];
//        menuItemHolder.namedate.setText(month);
//        menuItemHolder.timestart_end.setText("From "+List_Item.get(position).getTimeEnd()+"\n To"+List_Item.get(position).getTimestart());
//        if (position > previousPosition) { //scrolling DOWN
//        } else { // scrolling UP
//             }
//        previousPosition = position;
//
////        int row_index=0;
//
//        menuItemHolder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                row_index=position;
//                notifyDataSetChanged();
//            }
//        });
//
//        if(row_index==position){
//            menuItemHolder.cardView.setBackgroundColor(Color.parseColor("#007a6f"));
//            menuItemHolder.namedate.setTextColor(Color.parseColor("#000000"));
//            menuItemHolder.date.setTextColor(Color.parseColor("#000000"));
//            menuItemHolder.datytitel.setTextColor(Color.parseColor("#000000"));
//            menuItemHolder.timestart_end.setTextColor(Color.parseColor("#000000"));
//        }
//        else
//        {
//            menuItemHolder.cardView.setBackgroundColor(Color.parseColor("#ffffff"));
//////            holder.tv1.setTextColor(Color.parseColor("#000000"));
////            menuItemHolder.namedate.setTextColor(Color.parseColor("#ffffff"));
////            menuItemHolder.date.setTextColor(Color.parseColor("#ffffff"));
////            menuItemHolder.datytitel.setTextColor(Color.parseColor("#ffffff"));
//            menuItemHolder.timestart_end.setTextColor(Color.parseColor("#007a6f"));
//        }
//
//
//
//    }
//    @Override
//    public int getItemCount() {
//        return (null != List_Item ? List_Item.size() : 0);
//    }
//
//    public class MenuItemViewHolder extends RecyclerView.ViewHolder{
////        CircleImageView imageView;
//        TextView datytitel,date,timestart_end,namedate;
//        RatingBar ratingBar;
//        CardView cardView;
//        private RecyclerViewAdapter_timeHagz parent;
//
//
//        public MenuItemViewHolder(View view, RecyclerViewAdapter_timeHagz parent) {
//            super(view);
//
//            this.parent = parent;
//
//            cardView=itemView.findViewById(R.id.card);
//            datytitel=itemView.findViewById(R.id.text_dayTitle_date);
//
//            date=itemView.findViewById(R.id.text_date_dates);
//            namedate=itemView.findViewById(R.id.text_name_date_dates);
//
//            timestart_end=itemView.findViewById(R.id.text_timestart_end_date);
//        }
//
//    }
//}
