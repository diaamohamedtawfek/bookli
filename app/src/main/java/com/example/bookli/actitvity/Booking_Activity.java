package com.example.bookli.actitvity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import com.android.volley.toolbox.Volley;
import com.example.bookli.R;
import com.example.bookli.SharedPrefManager_IdDoctor;
import com.example.bookli.SharedPrefManager_Login;
import com.example.bookli.Urls;
import com.example.bookli.actitvity.login.LogIn;
import com.example.bookli.models.SendBookingEnd;
import com.example.bookli.models.sendBookink.Calendar;
import com.example.bookli.models.sendBookink.Clinic;
import com.example.bookli.models.sendBookink.ClinicBranch;
import com.example.bookli.models.sendBookink.Doctor;
import com.example.bookli.models.sendBookink.SendBooking;
import com.example.bookli.service;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class Booking_Activity extends AppCompatActivity {

    String idDoctor;

    CircleImageView imageView;
    TextView nameDoctor, termesClinc,loctionclinc;

    TextView dayname,numday,namemonth,timestart;

    Button booking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_);
        getSupportActionBar().hide();

        imageView=findViewById(R.id.image_doctorprofile);
        nameDoctor=findViewById(R.id.text_nameDoctor_booking);

        termesClinc =findViewById(R.id.text_nameClinc_Booking);
        loctionclinc=findViewById(R.id.text_locationClinc_Booking);

        if(SharedPrefManager_IdDoctor.getInstance(getApplicationContext()).isLoggedIn()){
            idDoctor=SharedPrefManager_IdDoctor.getInstance(getApplicationContext()).getjidDoctor();

            nameDoctor.setText(SharedPrefManager_IdDoctor.getInstance(getApplicationContext()).getnameDoctor());

            try {
                Picasso.with(getApplicationContext())
                        .load(SharedPrefManager_IdDoctor.getInstance(getApplicationContext()).getimageDoctor())
                        .into(imageView);
            }catch (Exception e){
                imageView.setImageResource(R.drawable.person);
            }

            termesClinc.setText("."+SharedPrefManager_IdDoctor.getInstance(getApplicationContext()).gettermes_Clinc());
            loctionclinc.setText(SharedPrefManager_IdDoctor.getInstance(getApplicationContext()).getlocationClinc());

        }

        Log.e("id doctor from booking", SharedPrefManager_IdDoctor.getInstance(getApplicationContext()).getjidDoctor());


        dayname=findViewById(R.id.text_dayTitle_date);
        dayname.setText(SharedPrefManager_IdDoctor.getInstance(getApplicationContext()).getdayname_clander());


        numday=findViewById(R.id.text_date_dates);
        numday.setText(SharedPrefManager_IdDoctor.getInstance(getApplicationContext()).getnumDay_clander());


        namemonth=findViewById(R.id.text_name_date_dates);
        namemonth.setText(SharedPrefManager_IdDoctor.getInstance(getApplicationContext()).getname_mnth_clander());


        timestart=findViewById(R.id.text_timestart_end_date);
        timestart.setText(SharedPrefManager_IdDoctor.getInstance(getApplicationContext()).getstart_clander());


        booking=findViewById(R.id.bu_booking);
        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Booking_Activity.this, "Done", Toast.LENGTH_LONG).show();
                sendBooKing();
            }
        });

        Log.e("id doctor","  "+SharedPrefManager_IdDoctor.getInstance(getApplicationContext()).getjidDoctor());
        Log.e("id calender","  "+SharedPrefManager_IdDoctor.getInstance(getApplicationContext()).getid_clander());
    }








    private void sendBooKing() {
        final ProgressDialog progressDialog=new ProgressDialog(Booking_Activity.this);
        progressDialog.show();

        final RequestQueue requestQueue = Volley.newRequestQueue(Booking_Activity.this);
        Gson gson = new Gson();

//        SendBookingEnd contactsTop=new SendBookingEnd();


        SendBooking contactsTop=new SendBooking();
        Doctor doctor=new Doctor();
        doctor.setId(Integer.valueOf(SharedPrefManager_IdDoctor.getInstance(getApplicationContext()).getjidDoctor()));

        Clinic clinic=new Clinic();
        clinic.setId(Integer.valueOf(SharedPrefManager_IdDoctor.getInstance(getApplicationContext()).getjidClinc()));

        ClinicBranch clinicBranch=new ClinicBranch();
        clinicBranch.setId(Integer.valueOf(SharedPrefManager_IdDoctor.getInstance(getApplicationContext()).getid_clinck_branc()));

        Calendar calendar=new Calendar();
        calendar.setId(Integer.valueOf(SharedPrefManager_IdDoctor.getInstance(getApplicationContext()).getid_clander()));
//
//        contactsTop.setCalendar(Integer.valueOf(SharedPrefManager_IdDoctor.getInstance(getApplicationContext()).getid_clander()));
//        contactsTop.setClinic(Integer.valueOf(SharedPrefManager_IdDoctor.getInstance(getApplicationContext()).getjidClinc()));
//        contactsTop.setClinicBranch(Integer.valueOf(SharedPrefManager_IdDoctor.getInstance(getApplicationContext()).getid_clinck_branc()));
//        contactsTop.setDoctor(Integer.valueOf(SharedPrefManager_IdDoctor.getInstance(getApplicationContext()).getjidDoctor()));

        contactsTop.setCalendar(calendar);
        contactsTop.setClinic(clinic);
        contactsTop.setClinicBranch(clinicBranch);
        contactsTop.setDoctor(doctor);
        contactsTop.setReservationDate(SharedPrefManager_IdDoctor.getInstance(getApplicationContext()).daterervationdate());

        Log.e("Volley:Response ", gson.toJson(contactsTop));

        JSONObject jsonObject = null;
        try {
            jsonObject=new JSONObject(gson.toJson(contactsTop));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Urls.Booking
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
                    sendBooKing();
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
