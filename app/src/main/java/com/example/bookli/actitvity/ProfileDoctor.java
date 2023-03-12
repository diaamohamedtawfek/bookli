package com.example.bookli.actitvity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
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
import com.example.bookli.actitvity.fragment.About;
import com.example.bookli.actitvity.fragment.Dates;
import com.example.bookli.actitvity.fragment.Rates;
import com.example.bookli.actitvity.login.LogIn;
import com.example.bookli.models.DataRecommended.DataRecomendded;
import com.example.bookli.models.Data_ProfileDoctor.DataProfile;
import com.example.bookli.models.Data_all_Doc_Catogry;
import com.example.bookli.models.SavedUnSavedDoctor;
import com.example.bookli.models.sendBookink.Calendar;
import com.example.bookli.models.sendBookink.Clinic;
import com.example.bookli.models.sendBookink.ClinicBranch;
import com.example.bookli.models.sendBookink.Doctor;
import com.example.bookli.models.sendBookink.SendBooking;
import com.example.bookli.service;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProfileDoctor extends AppCompatActivity {

    String idDoctor;
    ImageView imagedoctor,image_savesd_unsaved;
    TextView namedoctor,textRating;
    RatingBar ratingBar;
    TextView pricedoctoe,numrating,Reservations;
    int satesDactor_saved_unsaved;

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_profile_doctor);
        }catch (Exception e){

        }

        getSupportActionBar().hide();

        Bundle extras = getIntent().getExtras(); // to get move intent
        if (extras != null) {
            String a = extras.getString("id_doctor");

            if (a!=null){
                Log.e("id_doctor??",a);
                idDoctor= a;

//                idDoctor="1";
                SharedPrefManager_IdDoctor.getInstance(getApplicationContext()).userLogin_id(
                        a
                );
            }

        }
//        SharedPrefManager_IdDoctor.getInstance(getApplicationContext()).logout();

        imagedoctor=findViewById(R.id.image_doctorprofile);
        namedoctor=findViewById(R.id.text_namedoctor_profiledoctor);
        ratingBar=findViewById(R.id.rating);
        textRating=findViewById(R.id.text_Rating_profile);

        pricedoctoe=findViewById(R.id.text_pricedoctor_profile);
        numrating=findViewById(R.id.text_toatelrating_profile);
        Reservations=findViewById(R.id.text_numReservations_profile);

        image_savesd_unsaved=findViewById(R.id.image_save_insaved_profile);
        image_savesd_unsaved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                send_DataSavedUnsaved();
            }
        });

//        SharedPrefManager_IdDoctor.getInstance(getApplicationContext()).logout();


        getjson();

        final TabLayout tabLayout = findViewById(R.id.tab_layout);
        final ViewPager viewPager = findViewById(R.id.view_pager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new Dates(idDoctor), "Dates");
        viewPagerAdapter.addFragment(new About(idDoctor), "About");
        viewPagerAdapter.addFragment(new Rates(idDoctor), "Rates");

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);



    }

    private void send_DataSavedUnsaved() {
            final ProgressDialog progressDialog=new ProgressDialog(ProfileDoctor.this);
            progressDialog.show();

            final RequestQueue requestQueue = Volley.newRequestQueue(ProfileDoctor.this);
            Gson gson = new Gson();

            SavedUnSavedDoctor contactsTop=new SavedUnSavedDoctor();
            contactsTop.setDoctorId(Integer.valueOf(idDoctor));
            if (satesDactor_saved_unsaved==1) {
                contactsTop.setFavoriteFlag(0);
            }
        if (satesDactor_saved_unsaved==0) {
            contactsTop.setFavoriteFlag(1);
        }

            Log.e("Volley:Response ", gson.toJson(contactsTop));

            JSONObject jsonObject = null;
            try {
                jsonObject=new JSONObject(gson.toJson(contactsTop));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Urls.savedDactor
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
                        send_DataSavedUnsaved();
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


    private void getjson() {
//        Toast.makeText(this, ""+idDoctor, Toast.LENGTH_SHORT).show();
        Log.e("url profile",Urls.get_doctor +idDoctor);
        ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conMgr.getActiveNetworkInfo() != null && conMgr.getActiveNetworkInfo().isAvailable() && conMgr.getActiveNetworkInfo().isConnected()) {
            StringRequest stringRequest = new StringRequest(Request.Method.GET,
                    Urls.get_doctor +idDoctor,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.length() > 0) {
                                // progressDialog.dismiss();
                                Gson gson = new Gson();
                                DataProfile dataCattgryJson;
                                dataCattgryJson = gson.fromJson(response.toString(), DataProfile.class);

                                try {
                                    Picasso.with(getApplicationContext())
                                            .load(dataCattgryJson.getResultData().getProfilePicPath())
                                            .into(imagedoctor);
                                }catch (Exception e){

                                }


                                if (dataCattgryJson.getResultData().getSavedFlag()==null||dataCattgryJson.getResultData().getSavedFlag()==0){
                                    satesDactor_saved_unsaved=0;
                                    image_savesd_unsaved.setImageResource(R.mipmap.ic_unsaved);
                                }else{
                                    satesDactor_saved_unsaved=1;
                                    image_savesd_unsaved.setImageResource(R.drawable.ic_save);
                                }

                                namedoctor.setText(""+dataCattgryJson.getResultData().getFullName());

                                ratingBar.setRating(dataCattgryJson.getResultData().getRating());
                                ratingBar.setIsIndicator(true);
                                ratingBar.setActivated(false);

                                textRating.setText(""+dataCattgryJson.getResultData().getRating());


                                pricedoctoe.setText(""+dataCattgryJson.getResultData().getReservationsFees()+"\n EGP");

                                numrating.setText(""+dataCattgryJson.getResultData().getRatingCount()+"\n Ratings");
                                Reservations.setText(""+dataCattgryJson.getResultData().getReservationsCount()+"\n Reservations");


//                                SharedPrefManager_IdDoctor.getInstance(getApplicationContext()).userLogin_id(
//                                        idDoctor
//                                );
                                SharedPrefManager_IdDoctor.getInstance(getApplicationContext()).userLogin(
                                        idDoctor,
                                        dataCattgryJson.getResultData().getProfilePicPath(),
                                        dataCattgryJson.getResultData().getFullName()
                                        );
                                Log.e("iddoctoer>",idDoctor);






                            } else {
                                //progressDialog.dismiss();
                                Log.e("test", "no");
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // progressDialog.dismiss();
                            if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                                Toast.makeText(getApplicationContext(), "Error Network Time Out", Toast.LENGTH_LONG).show();
                            } else if (error instanceof AuthFailureError) {

                                startService(new Intent(getApplicationContext(), service.class));
                                getjson();
                                //Toast.makeText(getApplicationContext(), "AuthFailureError", Toast.LENGTH_LONG).show();
                                //TODO
                            } else if (error instanceof ServerError) {
                                Toast.makeText(getApplicationContext(), "ServerError profile", Toast.LENGTH_LONG).show();
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
        };
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {

//        @Override
//        public float getPageWidth(int page) {
//            if(page==0) {
//                Display display = getWindowManager().getDefaultDisplay();
//                Point size = new Point();
//                display.getSize(size);
//                return (float) size.x;
//            }
//            else
//                return super.getPageWidth(page);
//        }

        private ArrayList<Fragment> fragments;
        private ArrayList<String> titles;

        ViewPagerAdapter(FragmentManager fm){
            super(fm);
            this.fragments = new ArrayList<>();
            this.titles = new ArrayList<>();
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return 3;
        }
//        public int getCount() {
//            return fragments.size();
//        }

        public void addFragment(Fragment fragment, String title){
            fragments.add(fragment);
            titles.add(title);
        }

        // Ctrl + O

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
//        status("online");
    }

    @Override
    protected void onPause() {
        super.onPause();
        //      status("offline");
    }




}
