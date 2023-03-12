package com.example.bookli.actitvity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;
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
import com.example.bookli.Adapter.RecyclerViewAdapter_AllCatigry;
import com.example.bookli.Adapter.RecyclerViewAdapter_All_Location;
import com.example.bookli.MySingleton;
import com.example.bookli.R;
import com.example.bookli.SharedPrefManager_Login;
import com.example.bookli.Urls;
import com.example.bookli.models.Data_categry_home;
import com.example.bookli.models.Data_location_location;
import com.example.bookli.models.dataLocation.DataLocation;
import com.example.bookli.models.datacategry_jsonSh.DataCattgryJson;
import com.example.bookli.service;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class All_Location extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    RecyclerView recycal_deliverys;
    private RecyclerViewAdapter_All_Location recyclerView_dAdapter;
    public List<Data_location_location> listItems = new ArrayList<>();
    private GridLayoutManager gridLayoutManager;

    int REGISTER_URL=0;
    int REGISTER_URL_mareds;
    Boolean isScrolling = false;
    boolean isLoading = false;
    int currentItems, totalItems, scrollOutItems;
    SwipeRefreshLayout mSwipeRefreshLayout;

    TextView numDoc;
    EditText search_categ;

    String deirection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all__location);



        getSupportActionBar().hide();

        Bundle extras = getIntent().getExtras(); // to get move intent
        if (extras != null) {
            String a = extras.getString("deriction");
            if (a!=null){
                deirection= a;
            }

        }

        search_categ=findViewById(R.id.ed_serch_catery_allcategry);
        search_categ.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //filtercustomer(s.toString());
                if (search_categ.getText().toString().length()>0) {
                    getjson_onSearch();
                }else {
                    startUI();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });


        numDoc=findViewById(R.id.text_numDoc_Allcategry);

        // startUI();

        // SwipeRefreshLayout
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);
        mSwipeRefreshLayout.post(new Runnable() {

            @Override
            public void run() {

                mSwipeRefreshLayout.setRefreshing(true);

                // Fetching data from server
                if (search_categ.getText().toString().length()<=0){
                    startUI();
                }else{
                    getjson_onSearch();
                }

            }
        });


    }

    private void startUI() {
        listItems.clear();
        REGISTER_URL=0;
        recycal_deliverys = findViewById(R.id.recycal_allcatigry);
        recycal_deliverys.setHasFixedSize(true);
        gridLayoutManager = new GridLayoutManager(All_Location.this, 1);
        recycal_deliverys.setLayoutManager(gridLayoutManager);
        recyclerView_dAdapter = new RecyclerViewAdapter_All_Location(listItems,All_Location.this, All_Location.this,deirection);
        recycal_deliverys.setAdapter(recyclerView_dAdapter);

        getjson(0);

        recycal_deliverys.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                isScrolling=true;
                currentItems = gridLayoutManager.getChildCount();
                totalItems = gridLayoutManager.getItemCount();
                scrollOutItems = gridLayoutManager.findFirstVisibleItemPosition();

                if (gridLayoutManager.findLastCompletelyVisibleItemPosition() == listItems.size() - 1) {
                    if (REGISTER_URL<=REGISTER_URL_mareds) {
                        //getjson(Integer.parseInt(listItems.get(listItems.size()-1).getType()));
                        isScrolling = false;
                        getjson(REGISTER_URL);

//                        REGISTER_URL++;
                    }

                }
                //

            }
        });
    }

    private void getjson(int i) {// to fetch data to show in layout All
//        final ProgressDialog progressDialog = new ProgressDialog(All_CategoriesActivity.this);
//        progressDialog.show();

        ConnectivityManager conMgr = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conMgr.getActiveNetworkInfo() != null && conMgr.getActiveNetworkInfo().isAvailable() && conMgr.getActiveNetworkInfo().isConnected()) {
            //Toast.makeText(context, "you don't have update "+REGISTER_URL, Toast.LENGTH_SHORT).show();
            StringRequest stringRequest = new StringRequest(Request.Method.GET,
                    Urls.all_location +i+"&size=15",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // REGISTER_URL++;
                            //Toast.makeText(Delvery.this, ""+response, Toast.LENGTH_SHORT).show();
                            if (response.length() > 0) {
                                REGISTER_URL++;
                                mSwipeRefreshLayout.setRefreshing(false);
                                //progressDialog.dismiss();
                                Gson gson = new Gson();
                                DataLocation dataDelevery;
                                dataDelevery = gson.fromJson(response.toString(), DataLocation.class);

//                                 int ids = dataDelevery.getResultDataDto().get(0).getId();
//                                Log.e("id", "" + ids);

                                numDoc.setText("More than  "+ dataDelevery.getResultData().getTotalItemsCount() +"  categories, find what you want.");
                                float x = dataDelevery.getResultData().getTotalItemsCount() / 15;
                                int x_tkreb = (int) x;
                                REGISTER_URL_mareds = x_tkreb + 1;


                                int lengt_for = dataDelevery.getResultData().getResultData().size();
                                if (lengt_for > 0) {

                                    for (int I = 0; I < lengt_for; I++) {
                                        listItems.add(new Data_location_location(
                                                ""+dataDelevery.getResultData().getResultData().get(I).getId(),
                                                dataDelevery.getResultData().getResultData().get(I).getRegionTitle()
                                        ));
                                        recyclerView_dAdapter.notifyDataSetChanged();
                                    }
                                    ////


                                    /////
                                    Log.e("REGISTER_URL_mareds", "" + REGISTER_URL_mareds);
                                    Log.e("REGISTER_URL_mareds", "" + REGISTER_URL);
                                    Log.e("REGISTER_URL_mareds", "___________________");

                                } else {
                                    mSwipeRefreshLayout.setRefreshing(false);
                                    // progressDialog.dismiss();
                                    Log.e("test", "no");
                                }
                            }



                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //progressDialog.dismiss();
                            mSwipeRefreshLayout.setRefreshing(false);
                            if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                                Toast.makeText(getApplicationContext(), "Error Network Time Out", Toast.LENGTH_LONG).show();
                            } else if (error instanceof AuthFailureError) {
                                REGISTER_URL=0;
                                startService(new Intent(getApplicationContext(), service.class));
                                getjson(REGISTER_URL);
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

    private void getjson_onSearch() {// to fetch data to show in layout All

        listItems.clear();

//        final ProgressDialog progressDialog = new ProgressDialog(All_CategoriesActivity.this);
//        progressDialog.show();

        ConnectivityManager conMgr = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conMgr.getActiveNetworkInfo() != null && conMgr.getActiveNetworkInfo().isAvailable() && conMgr.getActiveNetworkInfo().isConnected()) {
            //Toast.makeText(context, "you don't have update "+REGISTER_URL, Toast.LENGTH_SHORT).show();
            StringRequest stringRequest = new StringRequest(Request.Method.GET,
                    Urls.serch_location +search_categ.getText().toString(),
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // REGISTER_URL++;
                            //Toast.makeText(Delvery.this, ""+response, Toast.LENGTH_SHORT).show();
                            if (response.length() > 0) {
                                REGISTER_URL++;
                                mSwipeRefreshLayout.setRefreshing(false);
                                //progressDialog.dismiss();
                                Gson gson = new Gson();
                                DataLocation dataDelevery;
                                dataDelevery = gson.fromJson(response.toString(), DataLocation.class);

//                                 int ids = dataDelevery.getResultDataDto().get(0).getId();
//                                Log.e("id", "" + ids);

                                numDoc.setText("More than  "+ dataDelevery.getResultData().getTotalItemsCount() +"  categories, find what you want.");
                                float x = dataDelevery.getResultData().getTotalItemsCount() / 15;
                                int x_tkreb = (int) x;
                                REGISTER_URL_mareds = x_tkreb + 1;


                                int lengt_for = dataDelevery.getResultData().getResultData().size();
                                if (lengt_for > 0) {

                                    for (int I = 0; I < lengt_for; I++) {
                                        listItems.add(new Data_location_location(
                                                ""+dataDelevery.getResultData().getResultData().get(I).getId(),
                                                dataDelevery.getResultData().getResultData().get(I).getRegionTitle()
                                        ));
                                        recyclerView_dAdapter.notifyDataSetChanged();
                                    }
                                    ////
                                    Log.e("REGISTER_URL_mareds", "" + REGISTER_URL_mareds);
                                    Log.e("REGISTER_URL_mareds", "" + REGISTER_URL);
                                    Log.e("REGISTER_URL_mareds", "___________________");

                                } else {
                                    mSwipeRefreshLayout.setRefreshing(false);
                                    // progressDialog.dismiss();
                                    Log.e("test", "no");
                                }
                            }



                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //progressDialog.dismiss();
                            mSwipeRefreshLayout.setRefreshing(false);
                            if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                                Toast.makeText(getApplicationContext(), "Error Network Time Out", Toast.LENGTH_LONG).show();
                            } else if (error instanceof AuthFailureError) {
                                REGISTER_URL=0;
                                startService(new Intent(getApplicationContext(), service.class));
                                getjson(REGISTER_URL);
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


    @Override
    public void onRefresh() {
        if (search_categ.getText().toString().length()<=0){
            startUI();
        }else{
            getjson_onSearch();
        }
    }





    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (deirection.equals("dash")) {
            finish();
        }else {
            Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
            startActivity(intent);
            finish();
        }

    }
}
