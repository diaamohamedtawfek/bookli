package com.example.bookli.actitvity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bookli.MySingleton;
import com.example.bookli.R;
import com.example.bookli.SharedPrefManager_IdDoctor;
import com.example.bookli.SharedPrefManager_Login;
import com.example.bookli.Urls;
import com.example.bookli.actitvity.login.LogIn;
import com.example.bookli.models.DATA_DateEvent.DateEvent;
import com.example.bookli.models.Event_Doctor_in_cancel_Activty.EventDoctorInCancelActivty;
import com.example.bookli.models.commentBooking.CommentlBooking;
import com.example.bookli.models.commentBooking.Reservation;
import com.example.bookli.models.sendBookink.Calendar;
import com.example.bookli.models.sendBookink.Clinic;
import com.example.bookli.models.sendBookink.ClinicBranch;
import com.example.bookli.models.sendBookink.Doctor;
import com.example.bookli.models.sendBookink.SendBooking;
import com.example.bookli.service;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CancelEvent extends AppCompatActivity {

    String idDoctor;

    String idtoCancel;

    ImageView imageDoctor;
    Button cansel,comment;
    TextView nameDoctor,time_start_end,daytitel,location,termesAndcond,day_date,namemonth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_event);

        Bundle extras = getIntent().getExtras(); // to get move intent
        if (extras != null) {
            String a = extras.getString("id_doctor");
            Log.e("id_doctor",a);
            if (a!=null){
                idDoctor= a;
            }

        }

        imageDoctor=findViewById(R.id.image_doctorprofile);
        nameDoctor=findViewById(R.id.text_nameDoctor_booking);
        time_start_end=findViewById(R.id.text_timestart_end_date);
        daytitel=findViewById(R.id.text_dayTitle_date);
        location=findViewById(R.id.text_locationClinc_Booking);
        termesAndcond=findViewById(R.id.text_nameClinc_Booking);
        day_date=findViewById(R.id.text_date_dates);
        namemonth=findViewById(R.id.text_name_date_dates);


        get_Event_in_Day();

        comment=findViewById(R.id.bu_Commentbooking);
        comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                done_task();

            }
        });

        cansel=findViewById(R.id.bu_Canselbooking);
        cansel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendid();
            }
        });
    }

    private void sendid() {

        Log.e("idtoCancel",idtoCancel);

        ConnectivityManager conMgr = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conMgr.getActiveNetworkInfo() != null && conMgr.getActiveNetworkInfo().isAvailable() && conMgr.getActiveNetworkInfo().isConnected()) {
            StringRequest stringRequest = new StringRequest(Request.Method.GET,
                    Urls.cansel+idtoCancel,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // REGISTER_URL++;
                            Log.e("cansel",""+response);
                            //Toast.makeText(Delvery.this, ""+response, Toast.LENGTH_SHORT).show();
                            if (response.length() > 0) {
//                                Gson gson = new Gson();
//                                EventDoctorInCancelActivty dataDelevery;
//                                dataDelevery = gson.fromJson(response.toString(), EventDoctorInCancelActivty.class);
//
//                                if (dataDelevery.getErrorStatus().equals(false)) {
//
//                                }
                            } else {

                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //progressDialog.dismiss();
                            if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                                Toast.makeText(getApplicationContext(), "Error Network Time Out", Toast.LENGTH_LONG).show();
                            } else if (error instanceof AuthFailureError) {
                                startService(new Intent(getApplicationContext(), service.class));
                                sendid();
                                //Toast.makeText(getApplicationContext(), "AuthFailureError", Toast.LENGTH_LONG).show();
                                //TODO
                            } else if (error instanceof ServerError) {
                                Toast.makeText(getApplicationContext(), "ServerError", Toast.LENGTH_LONG).show();
                                //TODO
                            } else if (error instanceof NetworkError) {
                                Toast.makeText(getApplicationContext(), "NetworkError", Toast.LENGTH_LONG).show();
                                //TODO
                            } else if (error instanceof ParseError) {
                                Toast.makeText(getApplicationContext(), "ParseError", Toast.LENGTH_LONG).show();
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
                            SharedPrefManager_Login.getInstance(getApplicationContext()).getjwt());
                    return headers;
                }
            };
            MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
        } else {
        }
    }


    private void get_Event_in_Day() {

        ConnectivityManager conMgr = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conMgr.getActiveNetworkInfo() != null && conMgr.getActiveNetworkInfo().isAvailable() && conMgr.getActiveNetworkInfo().isConnected()) {
            StringRequest stringRequest = new StringRequest(Request.Method.GET,
                    Urls.Event_Doctor_in_cancel_Activty+idDoctor,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // REGISTER_URL++;
                            //Toast.makeText(Delvery.this, ""+response, Toast.LENGTH_SHORT).show();
                            if (response.length() > 0) {
                                Gson gson = new Gson();
                                EventDoctorInCancelActivty dataDelevery;
                                dataDelevery = gson.fromJson(response.toString(), EventDoctorInCancelActivty.class);

                                idtoCancel=""+dataDelevery.getResultData().getId();

                                if (dataDelevery.getErrorStatus().equals(false)){

                                    if (dataDelevery.getResultData().getDoctor() !=null){
                                        if (dataDelevery.getResultData().getDoctor().getProfilePicPath() !=null ){
                                            Picasso.with(getApplicationContext())
                                                    .load(dataDelevery.getResultData().getDoctor().getProfilePicPath())
                                                    .into(imageDoctor);
                                        }
                                        nameDoctor.setText(dataDelevery.getResultData().getDoctor().getFullName());
                                    }

                                    if (dataDelevery.getResultData().getCalendar() !=null){
                                        time_start_end.setText("From "+dataDelevery.getResultData().getCalendar().getWeekDay().getReservationTimeFrom()+"\n to "+
                                                        dataDelevery.getResultData().getCalendar().getWeekDay().getReservationTimeTo()
                                                );

                                        daytitel.setText(""+dataDelevery.getResultData().getCalendar().getWeekDay().getDayTitle());
                                    }

                                    if (dataDelevery.getResultData().getClinicBranch() !=null){
                                        location.setText(""+dataDelevery.getResultData().getClinicBranch().getAddress());
                                        termesAndcond.setText(""+dataDelevery.getResultData().getClinicBranch().getClinicPolicy());
                                    }


                                    String test = dataDelevery.getResultData().getReservationDate();
                                    String s=test.substring(0,10);
                                    Log.e("D<><><><>",s);
                                    String ss=test.substring(8,10);
                                    day_date.setText(""+ss);


                                    String month =test.substring(5,7);
                                    Log.e("D<><><><!!!!!>",month);
                                    String[] monthName = {"يناير", "فبراير",
                                            "مارس", "أبريل", "مايو", "يونيو", "يوليو",
                                            "أغسطس", "سبتمبر", "أكتوبر", "نوفمبر",
                                            "ديسمبر"};
                                    int time=Integer.parseInt(month)-1;
                                    String monthzz = monthName[time];
                                    namemonth.setText(""+monthzz);

                                }
                            } else {

                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //progressDialog.dismiss();
                            if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                                Toast.makeText(getApplicationContext(), "Error Network Time Out", Toast.LENGTH_LONG).show();
                            } else if (error instanceof AuthFailureError) {
                                startService(new Intent(getApplicationContext(), service.class));
                                get_Event_in_Day();
                                //Toast.makeText(getApplicationContext(), "AuthFailureError", Toast.LENGTH_LONG).show();
                                //TODO
                            } else if (error instanceof ServerError) {
                                Toast.makeText(getApplicationContext(), "ServerError", Toast.LENGTH_LONG).show();
                                //TODO
                            } else if (error instanceof NetworkError) {
                                Toast.makeText(getApplicationContext(), "NetworkError", Toast.LENGTH_LONG).show();
                                //TODO
                            } else if (error instanceof ParseError) {
                                Toast.makeText(getApplicationContext(), "ParseError", Toast.LENGTH_LONG).show();
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
                            SharedPrefManager_Login.getInstance(getApplicationContext()).getjwt());
                    return headers;
                }
            };
            MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
        } else {
        }

    }

    /// use this

    public void showAlert(){

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(CancelEvent.this);
        LayoutInflater inflater = ((Activity) CancelEvent.this).getLayoutInflater();
        View alertView = inflater.inflate(R.layout.diolog_donetask, null);
        alertDialog.setView(alertView);

        final AlertDialog show = alertDialog.show();

//            Button alertButton = (Button) alertView.findViewById(R.id.btn_test);
//            alertButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    show.dismiss();
//                }
//            });
    }


    public void done_task(){
        final Dialog myDialog = new Dialog(CancelEvent.this);;
        myDialog.setContentView(R.layout.diolog_donetask);
//        LayoutInflater li = LayoutInflater.from(context);
//        View view = li.inflate(R.layout.diolog_donetask, null);
//
//        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
//        alertDialogBuilder.setView(view);


        final RatingBar ratingdoctor = myDialog.findViewById(R.id.rating_doctor);
        final EditText comment_doctor = myDialog.findViewById(R.id.ed_comment_doctork);

        final RatingBar ratingclinc = myDialog.findViewById(R.id.rating_clinic);
        final EditText comment_clinc = myDialog.findViewById(R.id.ed_commentclinc);

        Button submit = myDialog.findViewById(R.id.bu_supmit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendBooKing( ratingdoctor.getRating(), comment_doctor.getText().toString().trim()
                        , ratingclinc.getRating(), comment_clinc.getText().toString().trim());
            }
        });
//       AlertDialog alertDialogCongratulations = alertDialogBuilder.create();
//        alertDialogCongratulations.show();
//        alertDialogCongratulations.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);//
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();

    }


    private void sendBooKing(float rate_doctor, String comment_doctor, float rate_clinc, String comment_clink) {
        final ProgressDialog progressDialog=new ProgressDialog(CancelEvent.this);
        progressDialog.show();

        final RequestQueue requestQueue = Volley.newRequestQueue(CancelEvent.this);
        Gson gson = new Gson();

//        SendBookingEnd contactsTop=new SendBookingEnd();

        CommentlBooking contactsTop=new CommentlBooking();

        contactsTop.setClinicComment(comment_clink);
        contactsTop.setClinicRating(rate_clinc);

        contactsTop.setDrComment(comment_doctor);
        contactsTop.setDrRating(rate_doctor);

        Reservation reservation=new Reservation();
        reservation.setId(Float.valueOf(idtoCancel));

        contactsTop.setReservation(reservation);

        Log.e("Volley:Response ", gson.toJson(contactsTop));

        JSONObject jsonObject = null;
        try {
            jsonObject=new JSONObject(gson.toJson(contactsTop));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Urls.CommentBookink
                , jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progressDialog.dismiss();
                Log.e("response_booking",""+response);



                JSONObject sys = response;//// result

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Toast.makeText(getApplicationContext(), "Error Network Time Out", Toast.LENGTH_LONG).show();
                } else if (error instanceof AuthFailureError) {
                    startService(new Intent(getApplicationContext(), service.class));
                    done_task();
//                        Toast.makeText(getApplicationContext(), "AuthFailureError", Toast.LENGTH_LONG).show();
                    //TODO
                } else if (error instanceof ServerError) {
                    Toast.makeText(getApplicationContext(), "ServerError", Toast.LENGTH_LONG).show();
                    //TODO
                } else if (error instanceof NetworkError) {
                    Toast.makeText(getApplicationContext(), "NetworkError", Toast.LENGTH_LONG).show();
                    //TODO
                } else if (error instanceof ParseError) {
                    Toast.makeText(getApplicationContext(), "ParseError", Toast.LENGTH_LONG).show();
                    //TODO
                }
            }

        }){
            //            /** Passing some request headers* */
            @Override
            public Map getHeaders() throws AuthFailureError {
                HashMap headers = new HashMap();
                headers.put("Authorization",
                        SharedPrefManager_Login.getInstance(getApplicationContext()).getjwt());
                return headers;
            }
        };
        requestQueue.add(jsonObjectRequest);
    }
}
