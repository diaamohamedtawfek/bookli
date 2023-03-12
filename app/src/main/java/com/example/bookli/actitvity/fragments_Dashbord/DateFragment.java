package com.example.bookli.actitvity.fragments_Dashbord;


import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import com.example.bookli.Adapter.RecyclerViewAdapter_All_doc_EventinDay;
import com.example.bookli.MySingleton;
import com.example.bookli.R;
import com.example.bookli.SharedPrefManager_Login;
import com.example.bookli.Urls;
import com.example.bookli.models.DATA_DateEvent.DateEvent;
import com.example.bookli.models.Data_All_Event_In_Day.DataAllEventInDay;
import com.example.bookli.models.Data_All_Event_In_Same_Day;
import com.example.bookli.service;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
//import com.skyhope.eventcalenderlibrary.CalenderEvent;
import com.google.gson.Gson;
import com.skyhope.eventcalenderlibrary.CalenderEvent;
import com.skyhope.eventcalenderlibrary.listener.CalenderDayClickListener;
import com.skyhope.eventcalenderlibrary.model.DayContainerModel;
import com.skyhope.eventcalenderlibrary.model.Event;

import android.graphics.Color;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


//import android.content.Context;
//
//import com.github.sundeepk.compactcalendarview.domain.Event;
//import com.google.gson.Gson;
//import com.prolificinteractive.materialcalendarview.CalendarDay;
//import com.prolificinteractive.materialcalendarview.CalendarMode;
//import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
//import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
//import com.skyhope.eventcalenderlibrary.CalenderEvent;
//import com.skyhope.eventcalenderlibrary.listener.CalenderDayClickListener;
//import com.skyhope.eventcalenderlibrary.model.DayContainerModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class DateFragment extends Fragment  implements SwipeRefreshLayout.OnRefreshListener{


    String image,  name,  start_end,  location,  section,  id;


    CompactCalendarView compactCalendar;
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM- yyyy", Locale.getDefault());

    public DateFragment() {
        // Required empty public constructor
    }

    Event ev1;
    long timeInMilliseconds;

    Date date;

    View view;
    SwipeRefreshLayout mSwipeRefreshLayout;

    RecyclerView recycal_deliverys;
    private RecyclerViewAdapter_All_doc_EventinDay  recyclerView_dAdapter;
    public List<Data_All_Event_In_Same_Day> listItems = new ArrayList<>();
    private GridLayoutManager gridLayoutManager;

    int REGISTER_URL=0;
    int REGISTER_URL_mareds;
    Boolean isScrolling = false;
    boolean isLoading = false;
    int currentItems, totalItems, scrollOutItems;

    TextView nameDate;
    CalenderEvent calenderEvent;
    Calendar calendar;

    private static final String TAG = "CalenderTest";
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view= inflater.inflate(R.layout.fragment_date, container, false);

        recycal_deliverys = view.findViewById(R.id.recycal_allcatigry);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);


        nameDate=view.findViewById(R.id.text_date_date);
        calenderEvent = view.findViewById(R.id.calender_event);



        calenderEvent.initCalderItemClickCallback(new CalenderDayClickListener() {
            @Override
            public void onGetDay(DayContainerModel dayContainerModel) {
                Log.d(TAG, dayContainerModel.getDate());

                java.util.Date fecha = new java.util.Date(String.valueOf(dayContainerModel.getDate()));
                DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);
                //Date date;
                try {
                    date = (Date)formatter.parse(fecha.toString());
                    getActivity().setTitle(dateFormatMonth.format(date));
                    nameDate.setText(""+dateFormatMonth.format(date));

                    Calendar cal = Calendar.getInstance();
                    cal.setTime(date);
//                    Toast.makeText(getActivity(), ""+Calendar.MONTH, Toast.LENGTH_SHORT).show();

                    listItems.clear();
                    get_Event_in_Day(cal.get(Calendar.DATE),(cal.get(Calendar.MONTH)+1),cal.get(Calendar.YEAR) );
//                    get_Event_in_Day(cal.get(Calendar.DATE),(cal.get(Calendar.MONTH) + 1),cal.get(Calendar.YEAR) );
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        });


        compactCalendar = (CompactCalendarView) view.findViewById(R.id.compactcalendar_view);
//        compactCalendar.setUseThreeLetterAbbreviation(true);
//        compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
//            @Override
//            public void onDayClick(Date dateClicked) {
//                java.util.Date fecha = new java.util.Date(String.valueOf(dateClicked));
//                DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);
//                //Date date;
//                try {
//                    date = (Date)formatter.parse(fecha.toString());
//                    getActivity().setTitle(dateFormatMonth.format(date));
//                    nameDate.setText(""+dateFormatMonth.format(date));
//
//                    Calendar cal = Calendar.getInstance();
//                    cal.setTime(date);
////                    Toast.makeText(getActivity(), ""+Calendar.MONTH, Toast.LENGTH_SHORT).show();
//
////                    get_Event_in_Day(cal.get(Calendar.DATE),(cal.get(Calendar.MONTH)-1),cal.get(Calendar.YEAR) );
//                    get_Event_in_Day(cal.get(Calendar.DATE),(cal.get(Calendar.MONTH) + 1),cal.get(Calendar.YEAR) );
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onMonthScroll(Date firstDayOfNewMonth) {
//                nameDate.setText(""+dateFormatMonth.format(firstDayOfNewMonth));
//                getActivity().setTitle(dateFormatMonth.format(firstDayOfNewMonth));
//            }
//        });



        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);
        mSwipeRefreshLayout.post(new Runnable() {

            @Override
            public void run() {

                mSwipeRefreshLayout.setRefreshing(false);

                Calendar calendarw = Calendar.getInstance();
                Date dateq = calendarw.getTime();
                Log.e("dateq",""+new SimpleDateFormat("dd", Locale.ENGLISH).format(dateq.getTime()));
                int year_json= (int) Integer.parseInt(String.valueOf(calendarw.get(Calendar.YEAR)));
                int day_json= Integer.parseInt(new SimpleDateFormat("dd", Locale.ENGLISH).format(dateq.getTime()));
//                int month_json= Integer.parseInt(new SimpleDateFormat("MM", Locale.ENGLISH).format(dateq.getTime()+1));
                int month_json= Integer.parseInt(new SimpleDateFormat("MM", Locale.ENGLISH).format(dateq.getTime()));

                get_Event_in_Day(day_json,month_json,year_json );


            }
        });

        compactCalendar.setVisibility(View.GONE);

         return view;
    }
    @TargetApi(Build.VERSION_CODES.N)
    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(false);
//        compactCalendar.removeAllEvents();
//        Calendar calendarw = Calendar.getInstance();
//        Date dateq = calendarw.getTime();
//        Log.e("dateq",""+new SimpleDateFormat("dd", Locale.ENGLISH).format(dateq.getTime()));
//        int year_json= calendarw.get(Calendar.YEAR);
//        int day_json= Integer.parseInt(new SimpleDateFormat("dd", Locale.ENGLISH).format(dateq.getTime()));
////        int month_json= Integer.parseInt(new SimpleDateFormat("MM", Locale.ENGLISH).format(dateq.getTime()+1));
//        int month_json= Integer.parseInt(new SimpleDateFormat("MM", Locale.ENGLISH).format(dateq.getTime()));
//
//        get_Event_in_Day(day_json,month_json,year_json );
    }


    private void get_Event_in_Day(final int day,final int month, final int year) {

        compactCalendar.removeAllEvents();
        ConnectivityManager conMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conMgr.getActiveNetworkInfo() != null && conMgr.getActiveNetworkInfo().isAvailable() && conMgr.getActiveNetworkInfo().isConnected()) {
            StringRequest stringRequest = new StringRequest(Request.Method.GET,
                    Urls.Clender_Event+month+"&year="+year,
                    new Response.Listener<String>() {
                        @SuppressLint("NewApi")
                        @Override
                        public void onResponse(String response) {
                            Log.e("url_date",Urls.Clender_Event+month+"&year="+year);
                            Log.e("response calender",Urls.Clender_Event+month+"&year="+year);
                            // REGISTER_URL++;
                            //Toast.makeText(Delvery.this, ""+response, Toast.LENGTH_SHORT).show();
                            if (response.length() > 0) {
                                mSwipeRefreshLayout.setRefreshing(false);
                                Gson gson = new Gson();
                                DateEvent dataDelevery;
                                dataDelevery = gson.fromJson(response.toString(), DateEvent.class);

                                if (dataDelevery.getResultData().getData().size()>0) {

                                    Log.e("lenth_event",""+dataDelevery.getResultData().getData().size());
                                    for (int i = 0; i < dataDelevery.getResultData().getData().size(); i++) {
                                        Log.e("lenth_event",""+dataDelevery.getResultData().getData().get(i));
                                        Log.e("lenth_event",year + "-" + month + "-" + String.valueOf(dataDelevery.getResultData().getData().get(i)
                                        ));

                                        String dates = year + "-" + month + "-" + String.valueOf(dataDelevery.getResultData().getData().get(i));
                                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
//                                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                                        try {
                                            Date mDate = sdf.parse(dates);
                                            timeInMilliseconds = mDate.getTime();
//                                            Log.e("########", "" + timeInMilliseconds);
//                                            System.out.println("Date in milli :: " + timeInMilliseconds);

//                                            ev1 = new Event(Color.BLACK, timeInMilliseconds, "Teachers' Professional Day");
//                                            compactCalendar.addEvent(ev1);



//                                            Event event_calenderEvent = new Event(timeInMilliseconds, "Teachers' Professional Day");
//                                            calenderEvent.addEvent(ev1);
                                            DayContainerModel dayContainerModel=new DayContainerModel();

                                            calendar = Calendar.getInstance();
                                            calendar.add(Calendar.DAY_OF_MONTH, 1);

                                            Event event = new Event(calendar.getTimeInMillis(), "Test",Color.RED);
                                            calenderEvent.addEvent(event);
                                            Log.e("########", "" + event);

                                        } catch (ParseException e) {
                                            Log.e(">>>>>>exeptio >",e.toString());
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        }
                                    }

                                    get_All_Event_in_this_Day(day,month,year);

                                }else{
                                    mSwipeRefreshLayout.setRefreshing(false);
                                }
                                } else {
                                    mSwipeRefreshLayout.setRefreshing(false);
                                    // progressDialog.dismiss();
//                                    Log.e("test", "no");
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @RequiresApi(api = Build.VERSION_CODES.N)
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //progressDialog.dismiss();
                            mSwipeRefreshLayout.setRefreshing(false);
                            if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                                Toast.makeText(getActivity(), "Error Network Time Out", Toast.LENGTH_LONG).show();
                            } else if (error instanceof AuthFailureError) {
                                //startActivity(new Intent(getApplicationContext(),Log_In.class));
//                                finish();
                                //Toast.makeText(getApplicationContext(), "AuthFailureError", Toast.LENGTH_LONG).show();

                                getActivity().startService(new Intent(getActivity(), service.class));

                                Calendar calendarw = Calendar.getInstance();
                                Date dateq = calendarw.getTime();
                                Log.e("dateq",""+new SimpleDateFormat("dd", Locale.ENGLISH).format(dateq.getTime()));
                                int year_json= (int) Integer.parseInt(String.valueOf(calendarw.get(Calendar.YEAR)));
                                int day_json= Integer.parseInt(new SimpleDateFormat("dd", Locale.ENGLISH).format(dateq.getTime()));
//                int month_json= Integer.parseInt(new SimpleDateFormat("MM", Locale.ENGLISH).format(dateq.getTime()+1));
                                int month_json= Integer.parseInt(new SimpleDateFormat("MM", Locale.ENGLISH).format(dateq.getTime()));

                                get_Event_in_Day(day_json,month_json,year_json );
                                //TODO
                            } else if (error instanceof ServerError) {
                                Toast.makeText(getActivity(), "ServerError", Toast.LENGTH_LONG).show();
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
        } else {
        }

    }

    private void get_All_Event_in_this_Day(final int day,final int month,final int year) {

        listItems.clear();
        REGISTER_URL=0;
        recycal_deliverys.setHasFixedSize(true);
        gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        recycal_deliverys.setLayoutManager(gridLayoutManager);
        recyclerView_dAdapter = new RecyclerViewAdapter_All_doc_EventinDay(listItems,getActivity());
        recycal_deliverys.setAdapter(recyclerView_dAdapter);



        listItems.clear();
        try {


            Log.e("calender", Urls.Clender_Event_in_this_Day + day + "&month=" + month + "&year=" + year + "&page=0&size=100");
            ConnectivityManager conMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
            if (conMgr.getActiveNetworkInfo() != null && conMgr.getActiveNetworkInfo().isAvailable() && conMgr.getActiveNetworkInfo().isConnected()) {
                StringRequest stringRequest = new StringRequest(Request.Method.GET,
                        Urls.Clender_Event_in_this_Day + day + "&month=" + month + "&year=" + year + "&page=0&size=100",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // REGISTER_URL++;
                                Log.e("Evend in day ", response);
                                //Toast.makeText(Delvery.this, ""+response, Toast.LENGTH_SHORT).show();
                                if (response.length() > 0) {
                                    mSwipeRefreshLayout.setRefreshing(false);
                                    Gson gson = new Gson();
                                    DataAllEventInDay dataDelevery;
                                    dataDelevery = gson.fromJson(response.toString(), DataAllEventInDay.class);
//
                                    if (dataDelevery.getResultData().getTotalItemsCount() > 0) {

                                        if (dataDelevery.getResultData().getResultData().size() > 0) {


                                            for (int x = 0; x < dataDelevery.getResultData().getResultData().size(); x++) {


                                                if (dataDelevery.getResultData().getResultData().get(x).getDoctor() != null) {
                                                    image = "" + dataDelevery.getResultData().getResultData().get(x).getDoctor().getProfilePicPath();
                                                    name = dataDelevery.getResultData().getResultData().get(x).getDoctor().getFullName();
                                                } else {
                                                    image = "";
                                                    name = "";
                                                }


                                                if (dataDelevery.getResultData().getResultData().get(x).getCalendar() != null
                                                        && dataDelevery.getResultData().getResultData().get(x).getCalendar().getWeekDay() != null) {
                                                    start_end = "from " + dataDelevery.getResultData().getResultData().get(x).getCalendar().getWeekDay().getReservationTimeFrom()
                                                            + "\n to "
                                                            + dataDelevery.getResultData().getResultData().get(x).getCalendar().getWeekDay().getReservationTimeTo();
                                                } else {
                                                    start_end = "";
                                                }

                                                listItems.add(new Data_All_Event_In_Same_Day(
                                                        "" + image,
                                                        "" + name,
                                                        start_end
                                                        , "" + dataDelevery.getResultData().getResultData().get(x).getClinicBranch().getAddress()
                                                        , ">>>>"
                                                        , "" + dataDelevery.getResultData().getResultData().get(x).getId()
                                                ));
                                                recyclerView_dAdapter.notifyDataSetChanged();
                                            }
                                        }
//
//
                                    } else {
                                        mSwipeRefreshLayout.setRefreshing(false);
                                    }
                                } else {
                                    mSwipeRefreshLayout.setRefreshing(false);
                                    // progressDialog.dismiss();
                                    Log.e("test", "no");
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @RequiresApi(api = Build.VERSION_CODES.N)
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                //progressDialog.dismiss();
                                mSwipeRefreshLayout.setRefreshing(false);
                                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                                    Toast.makeText(getActivity(), "Error Network Time Out", Toast.LENGTH_LONG).show();
                                } else if (error instanceof AuthFailureError) {
                                    //startActivity(new Intent(getApplicationContext(),Log_In.class));
//                                finish();
                                    //Toast.makeText(getApplicationContext(), "AuthFailureError", Toast.LENGTH_LONG).show();
                                    getActivity().startService(new Intent(getActivity(), service.class));

                                    Calendar calendarw = Calendar.getInstance();
                                    Date dateq = calendarw.getTime();
                                    Log.e("dateq",""+new SimpleDateFormat("dd", Locale.ENGLISH).format(dateq.getTime()));
                                    int year_json= (int) Integer.parseInt(String.valueOf(calendarw.get(Calendar.YEAR)));
                                    int day_json= Integer.parseInt(new SimpleDateFormat("dd", Locale.ENGLISH).format(dateq.getTime()));
//                int month_json= Integer.parseInt(new SimpleDateFormat("MM", Locale.ENGLISH).format(dateq.getTime()+1));
                                    int month_json= Integer.parseInt(new SimpleDateFormat("MM", Locale.ENGLISH).format(dateq.getTime()));

                                    get_Event_in_Day(day_json,month_json,year_json );
                                    //TODO
                                } else if (error instanceof ServerError) {
                                    Toast.makeText(getActivity(), "ServerError", Toast.LENGTH_LONG).show();
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
            } else {
            }


        }catch (Exception e){}
    }

}
