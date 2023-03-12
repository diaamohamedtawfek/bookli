package com.example.bookli.actitvity.fragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.bookli.MySingleton;
import com.example.bookli.R;
import com.example.bookli.SharedPrefManager_IdDoctor;
import com.example.bookli.SharedPrefManager_Login;
import com.example.bookli.Urls;
import com.example.bookli.actitvity.Booking_Activity;
import com.example.bookli.models.Data_All_TimeHagz;
import com.example.bookli.models.Data_locationclincDoctor.DataLocationClincDoctor;
import com.example.bookli.models.Data_timeHagz_doctor.DataTimeHagzDoctor;
import com.example.bookli.service;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class Dates extends Fragment  {

    // this dete Doctors and booking
    String idDoctor;

    public Dates(String idDoctors) {
        idDoctor=idDoctors;
    }

    View view;

    NestedScrollView nestedScrollView;
    TextView location,termes;
    ArrayList<String> name_clinc_lication;
    ArrayList<String> id_clinc_lication;
    ArrayList<String> id_clinc_branch;
    ArrayList<String> termes_clinc_lication;

    Spinner spinner;

    RecyclerView recycal_deliverys;
    private RecyclerViewAdapter_timeHagz recyclerView_dAdapter;
    public List<Data_All_TimeHagz> listItems = new ArrayList<>();
    private GridLayoutManager gridLayoutManager;

    Button booknow;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        if(SharedPrefManager_IdDoctor.getInstance(getActivity()).isLoggedIn()){
//            idDoctor=SharedPrefManager_IdDoctor.getInstance(getActivity()).getjidDoctor();
//
//        }
        Log.e("idDoctorTest"," "+SharedPrefManager_IdDoctor.getInstance(getActivity()).getjidDoctor());
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_dates, container, false);

        getjson();

        nestedScrollView=view.findViewById(R.id.nestet);
        termes=view.findViewById(R.id.text_termes_about);
        booknow=view.findViewById(R.id.bu_booknow_date);

         spinner = view.findViewById(R.id.spinner1);
        name_clinc_lication=new ArrayList<String>();
        id_clinc_branch=new ArrayList<String>();
        //name_clinc_lication.add("Select Clinic Location");
        id_clinc_lication=new ArrayList<String>();
        //id_clinc_lication.add("S");
        termes_clinc_lication=new ArrayList<String>();
//        termes_clinc_lication.add("");





        recycal_deliverys = view.findViewById(R.id.recycal_allcatigry);
        recycal_deliverys.setHasFixedSize(true);
        gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        recycal_deliverys.setLayoutManager(gridLayoutManager);
        recyclerView_dAdapter = new RecyclerViewAdapter_timeHagz(listItems, getActivity());
//        StaggeredGridLayoutManager staggeredGridLayoutManager =
//                new StaggeredGridLayoutManager(
//                        2, //The number of Columns in the grid
//                        LinearLayoutManager.HORIZONTAL);
//        recycal_deliverys.setLayoutManager(new GridLayoutManager(getActivity(), 1));

        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recycal_deliverys.setLayoutManager(horizontalLayoutManagaer);
        recycal_deliverys.setAdapter(recyclerView_dAdapter);

        ///>>>>

        booknow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), Booking_Activity.class);

                startActivity(intent);
            }
        });


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String category=   spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString();
                //Toast.makeText(getActivity(),""+id_clinc_lication.get(spinner.getSelectedItemPosition()),Toast.LENGTH_LONG).show();
                getjson_timeHagz(""+id_clinc_branch.get(spinner.getSelectedItemPosition()));
                termes.setText(termes_clinc_lication.get(spinner.getSelectedItemPosition()));

                SharedPrefManager_IdDoctor.getInstance(getActivity()).ClincData(
                        id_clinc_lication.get(spinner.getSelectedItemPosition()),

                        id_clinc_branch.get(spinner.getSelectedItemPosition()),
                        name_clinc_lication.get(spinner.getSelectedItemPosition()),
                        termes_clinc_lication.get(spinner.getSelectedItemPosition())
                );
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });

        return view;
    }


//    @Override
//    public void onItemClick(RecyclerViewAdapter_timeHagz.MenuItemViewHolder item, int position) {
//        Toast.makeText(getActivity(),
//                "Remove " + position + " : " + item.getItemName(),
//                Toast.LENGTH_SHORT).show();
//    }


    private void getjson_timeHagz(String branch_id) {
        SharedPrefManager_IdDoctor.getInstance(getActivity()).userLogin_id(
                idDoctor
        );
        listItems.clear();
        Log.e("url_cal",Urls.get_timeHageDoctor +branch_id+"&doctor-id="+idDoctor+"&page=0&size=100");
        ConnectivityManager conMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conMgr.getActiveNetworkInfo() != null && conMgr.getActiveNetworkInfo().isAvailable() && conMgr.getActiveNetworkInfo().isConnected()) {
            StringRequest stringRequest = new StringRequest(Request.Method.GET,
                    Urls.get_timeHageDoctor +branch_id+"&doctor-id="+
                            idDoctor
                            +"&page=0&size=100",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Log.e("getjson_timeHagz",""+response);
                            if (response.length() > 0) {



                                recycal_deliverys.setVisibility(View.VISIBLE);
                                nestedScrollView.setVisibility(View.VISIBLE);
                                Gson gson = new Gson();
                                DataTimeHagzDoctor dataCattgryJson;
                                dataCattgryJson = gson.fromJson(response.toString(), DataTimeHagzDoctor.class);

                                for (int x=0;x < dataCattgryJson.getResultData().getResultData().size();x++) {
                                    listItems.add(new Data_All_TimeHagz(
                                            ""+dataCattgryJson.getResultData().getResultData().get(x).getId(),
                                            dataCattgryJson.getResultData().getResultData().get(x).getWeekDay().getDayTitle(),
                                            dataCattgryJson.getResultData().getResultData().get(x).getWeekDay().getReservationTimeFrom(),
                                            dataCattgryJson.getResultData().getResultData().get(x).getWeekDay().getReservationTimeTo(),
                                            dataCattgryJson.getResultData().getResultData().get(x).getCalendarDate()
                                    ));
                                }
                                recyclerView_dAdapter.notifyDataSetChanged();

                            } else {
                                Log.e("test", "no");
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            recycal_deliverys.setVisibility(View.GONE);
                            nestedScrollView.setVisibility(View.GONE);
                            if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                                Toast.makeText(getActivity(), "Error Network Time Out_date", Toast.LENGTH_LONG).show();
                            } else if (error instanceof AuthFailureError) {
                                getActivity().startService(new Intent(getActivity(), service.class));
//                                getjson();
                                //TODO
                            } else if (error instanceof ServerError) {
                                Toast.makeText(getActivity(), "ServerError Date time date?>", Toast.LENGTH_LONG).show();
                                //TODO
                            } else if (error instanceof NetworkError) {
                                Toast.makeText(getActivity(), "NetworkError", Toast.LENGTH_LONG).show();
                                //TODO
                            } else if (error instanceof ParseError) {
                                Toast.makeText(getActivity(), "ParseError", Toast.LENGTH_LONG).show();
                                //TODO
                            }
                        }
                    }
            ){
                //            /** Passing some request headers* */
                @Override
                public Map getHeaders() throws AuthFailureError {
                    HashMap headers = new HashMap();
                    headers.put("Authorization",
                            SharedPrefManager_Login.getInstance(getActivity()).getjwt());
                    return headers;
                }
            };
            MySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);
        };
    }


    private void getjson() {
        Log.e("get_location",Urls.get_location_clinc_doctor +idDoctor+"&page=0"+"&size=100");
        ConnectivityManager conMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conMgr.getActiveNetworkInfo() != null && conMgr.getActiveNetworkInfo().isAvailable() && conMgr.getActiveNetworkInfo().isConnected()) {
            StringRequest stringRequest = new StringRequest(Request.Method.GET,
                    Urls.get_location_clinc_doctor +
                            idDoctor
                            +"&page=0&size=100",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.e("response_locatio",""+response);
                            if (response.length() > 0) {
                                Gson gson = new Gson();
                                DataLocationClincDoctor dataCattgryJson;
                                dataCattgryJson = gson.fromJson(response.toString(), DataLocationClincDoctor.class);
                                if (!dataCattgryJson.getResultData().equals(null)) {
                                    for (int i = 0; i < dataCattgryJson.getResultData().getClinicBranchMobileDtoList().size(); i++) {
                                        name_clinc_lication.add(dataCattgryJson.getResultData().getClinicBranchMobileDtoList().get(i).getAddress());
                                        id_clinc_lication.add("" + dataCattgryJson.getResultData().getClinicId());
                                        termes_clinc_lication.add("" + dataCattgryJson.getResultData().getClinicBranchMobileDtoList().get(i).getClinicPolicy());

                                        id_clinc_branch.add("" + dataCattgryJson.getResultData().getClinicBranchMobileDtoList().get(i).getId());

                                        }
                                }
                                    spinner.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item,
                                            name_clinc_lication));
                            } else {
                                Log.e("test", "no");
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            recycal_deliverys.setVisibility(View.GONE);
                            nestedScrollView.setVisibility(View.GONE);
                            if (error instanceof TimeoutError || error instanceof NoConnectionError) {
//                                Toast.makeText(getActivity(), "Error Network Time Out", Toast.LENGTH_LONG).show();
                            } else if (error instanceof AuthFailureError) {
                                getActivity().startService(new Intent(getActivity(), service.class));
                                getjson();
                                //TODO
                            } else if (error instanceof ServerError) {
                                Toast.makeText(getActivity(), "ServerError Date_date", Toast.LENGTH_LONG).show();

                                //TODO
                            } else if (error instanceof NetworkError) {
                                Toast.makeText(getActivity(), "NetworkError", Toast.LENGTH_LONG).show();
                                //TODO
                            } else if (error instanceof ParseError) {
                                Toast.makeText(getActivity(), "ParseError", Toast.LENGTH_LONG).show();
                                //TODO
                            }
                        }
                    }
            ){
                //            /** Passing some request headers* */
                @Override
                public Map getHeaders() throws AuthFailureError {
                    HashMap headers = new HashMap();
                    headers.put("Authorization",
                            SharedPrefManager_Login.getInstance(getActivity()).getjwt());
                    return headers;
                }
            };
            MySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);
        };
    }














    public class RecyclerViewAdapter_timeHagz extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private int previousPosition = 0;

        List<Data_All_TimeHagz> List_Item;
        private Context context;
        int row_index=0;

        public RecyclerViewAdapter_timeHagz(List<Data_All_TimeHagz> list_Item, Context context) {
            List_Item = list_Item;
            this.context = context;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

            View menu1 = LayoutInflater.from(viewGroup.getContext()).inflate(
                    R.layout.item_time_hagz, viewGroup, false);
            return new MenuItemViewHolder(menu1,this);

        }

        public void filterList(ArrayList<Data_All_TimeHagz> filteredList) {
            List_Item = filteredList;
            //recyclerView_dAdapter.notifyDataSetChanged();

        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

            final MenuItemViewHolder menuItemHolder = (MenuItemViewHolder) holder;

            if (List_Item.get(position).getDayTitle().trim().equals("today")){
                menuItemHolder.datytitel.setText("اليوم");
            }
            if (List_Item.get(position).getDayTitle().trim().equals("Tomorrow")){
                menuItemHolder.datytitel.setText("غدا");
            }
            if (List_Item.get(position).getDayTitle().trim().equals("Saturday")){
                menuItemHolder.datytitel.setText("السبت");
            }
            if (List_Item.get(position).getDayTitle().trim().equals("Sunday")){
                menuItemHolder.datytitel.setText("الاحد");
            }
            if (List_Item.get(position).getDayTitle().trim().equals("Monday")){
                menuItemHolder.datytitel.setText("الاثنين");
            }
            if (List_Item.get(position).getDayTitle().trim().equals("Tuesday")){
                menuItemHolder.datytitel.setText("الثلاثاء");
            }
            if (List_Item.get(position).getDayTitle().trim().equals("Wednesday")){
                menuItemHolder.datytitel.setText("الاربعاء");
            }
            if (List_Item.get(position).getDayTitle().trim().equals("Thursday")){
                menuItemHolder.datytitel.setText("الخميس");
            }
            if (List_Item.get(position).getDayTitle().trim().equals("Friday")){
                menuItemHolder.datytitel.setText("الجمعة");
            }

            String value = List_Item.get(position).getCalendarDate();
            String lastTwo = null;
            if (value != null && value.length() >= 2) {
                lastTwo = value.substring(value.length() - 2);
                menuItemHolder.date.setText(lastTwo);
            }
            String test = List_Item.get(position).getCalendarDate();
            String s=test.substring(5,7);
            String[] monthName = {"يناير", "فبراير",
                    "مارس", "أبريل", "مايو", "يونيو", "يوليو",
                    "أغسطس", "سبتمبر", "أكتوبر", "نوفمبر",
                    "ديسمبر"};
            int time=Integer.parseInt(s)-1;
            String month = monthName[time];
            menuItemHolder.namedate.setText(month);
            menuItemHolder.timestart_end.setText("From "+List_Item.get(position).getTimeEnd()+"\n To"+List_Item.get(position).getTimestart());
            if (position > previousPosition) { //scrolling DOWN
            } else { // scrolling UP
            }
            previousPosition = position;

//        int row_index=0;

            menuItemHolder.linear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    row_index=position;
                    notifyDataSetChanged();

                    booknow.setBackgroundResource(R.drawable.busin);

//                    CalenderData(String id ,String nameDay ,String numDay,String nameMonth ,String startClander)
                    SharedPrefManager_IdDoctor.getInstance(getActivity()).CalenderData(
//                            List_Item.get(position).getId(),

                            menuItemHolder.text_id_date.getText().toString(),
                            menuItemHolder.namedate.getText().toString(),
                            menuItemHolder.namedate.getText().toString(),
                            "",""

                            );
                    SharedPrefManager_IdDoctor.getInstance(getActivity()).DateRevervation(
                            menuItemHolder.text_id_date.getText().toString()
                    );
//                    SharedPrefManager_IdDoctor.getInstance(getActivity()).CalenderData(
//                            menuItemHolder.timestart_end.getText().toString()  ,
//                            menuItemHolder.datytitel.getText().toString() ,
//                            menuItemHolder.date.getText().toString(),
//                            menuItemHolder.namedate.getText().toString() ,
//                            List_Item.get(position).getId()
//                    );
                }
            });




            menuItemHolder.text_id_date.setText(""+List_Item.get(position).getId());

            if(row_index==position){
                menuItemHolder.imagetime.setImageResource(R.mipmap.ic_time_w);
                menuItemHolder.linear.setBackgroundColor(Color.parseColor("#007a6f"));
//                menuItemHolder.linear.setRadius((float) 20.0);
                menuItemHolder.namedate.setTextColor(Color.parseColor("#ffffff"));
                menuItemHolder.date.setTextColor(Color.parseColor("#ffffff"));
                menuItemHolder.datytitel.setTextColor(Color.parseColor("#ffffff"));
                menuItemHolder.timestart_end.setTextColor(Color.parseColor("#ffffff"));


                SharedPrefManager_IdDoctor.getInstance(getActivity()).CalenderData(
//                            List_Item.get(position).getId(),
                        ""+menuItemHolder.text_id_date.getText().toString(),
                        menuItemHolder.namedate.getText().toString(),
                        menuItemHolder.namedate.getText().toString(),
                        "",""

                );
                SharedPrefManager_IdDoctor.getInstance(getActivity()).DateRevervation(
                        ""+menuItemHolder.text_id_date.getText().toString()
                );

            }
            else
            {
                menuItemHolder.imagetime.setImageResource(R.mipmap.ic_time);
//                menuItemHolder.linear.setRadius((float) 20.0);
                menuItemHolder.linear.setBackgroundColor(Color.parseColor("#ffffff"));
////            holder.tv1.setTextColor(Color.parseColor("#000000"));
            menuItemHolder.namedate.setTextColor(Color.parseColor("#000000"));
            menuItemHolder.date.setTextColor(Color.parseColor("#000000"));
            menuItemHolder.datytitel.setTextColor(Color.parseColor("#000000"));
                menuItemHolder.timestart_end.setTextColor(Color.parseColor("#007a6f"));
            }



        }
        @Override
        public int getItemCount() {
            return (null != List_Item ? List_Item.size() : 0);
        }

        public class MenuItemViewHolder extends RecyclerView.ViewHolder{
            //        CircleImageView imageView;
            TextView datytitel,date,timestart_end,namedate,text_id_date;
            RatingBar ratingBar;
            LinearLayout linear;
            ImageView imagetime;
            CardView cardView;
            private RecyclerViewAdapter_timeHagz parent;


            public MenuItemViewHolder(View view, RecyclerViewAdapter_timeHagz parent) {
                super(view);

                this.parent = parent;

                linear =itemView.findViewById(R.id.linear);
                cardView =itemView.findViewById(R.id.card);
                datytitel=itemView.findViewById(R.id.text_dayTitle_date);
                text_id_date=itemView.findViewById(R.id.text_id_date);
                text_id_date.setVisibility(View.GONE);

                date=itemView.findViewById(R.id.text_date_dates);
                namedate=itemView.findViewById(R.id.text_name_date_dates);

                timestart_end=itemView.findViewById(R.id.text_timestart_end_date);
                imagetime=itemView.findViewById(R.id.imagetime);
            }

        }
    }

}
