package com.example.bookli.actitvity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
import com.example.bookli.MySingleton;
import com.example.bookli.R;
import com.example.bookli.SharedPrefManager_Login;
import com.example.bookli.Urls;
import com.example.bookli.models.Data_categry_home;
import com.example.bookli.models.datacategry_jsonSh.DataCattgryJson;
import com.example.bookli.service;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class All_CategoriesActivity extends AppCompatActivity  implements SwipeRefreshLayout.OnRefreshListener {

    RecyclerView recycal_deliverys;
    private RecyclerViewAdapter_AllCatigry recyclerView_dAdapter;
    public List<Data_categry_home> listItems = new ArrayList<>();
    private GridLayoutManager gridLayoutManager;

    int REGISTER_URL=0;
    int REGISTER_URL_mareds;
    Boolean isScrolling = false;
    boolean isLoading = false;
    int currentItems, totalItems, scrollOutItems;
    SwipeRefreshLayout mSwipeRefreshLayout;

    TextView numDoc;
    EditText search_categ;

    String deirection=null;
    String idlocation,namelocation;
    SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all__categories);

        getSupportActionBar().hide();

        Bundle extras = getIntent().getExtras(); // to get move intent
        if (extras != null) {
            String a = extras.getString("deriction");
            String id_Cat = extras.getString("idlocation");
            String id = extras.getString("idlocation");
            String name = extras.getString("namelocation");
            if (a!=null){
                deirection= a;
                idlocation=id;
                namelocation=name;
            }

        }

        searchView=findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //adapter.getFilter().filter(newText);
                if (newText.length()>0) {
                    getjson_onSearch(newText.trim());
                }else {
                    startUI();
                }
                return false;
            }
        });

        search_categ=findViewById(R.id.ed_serch_catery_allcategry);
        search_categ.setFocusableInTouchMode(false);
        search_categ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search_categ.setFocusableInTouchMode(true);
                search_categ.setFocusableInTouchMode(true);
                search_categ.requestFocus();
                InputMethodManager imm = (InputMethodManager)   getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(search_categ, InputMethodManager.SHOW_IMPLICIT);
            }
        });

        search_categ.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //filtercustomer(s.toString());
                if (search_categ.getText().toString().length()>0) {
                    getjson_onSearch(search_categ.getText().toString().trim());
                }else {
                    startUI();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });


        numDoc=findViewById(R.id.text_numDoc_Allcategry);

        BottomNavigationView bottomNav = findViewById(R.id.nav_view);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

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
                    getjson_onSearch(search_categ.getText().toString().trim());
                }

            }
        });


    }

    private void startUI() {
        listItems.clear();
        REGISTER_URL=0;
        recycal_deliverys = findViewById(R.id.recycal_allcatigry);
        recycal_deliverys.setHasFixedSize(true);
        gridLayoutManager = new GridLayoutManager(All_CategoriesActivity.this, 1);
        recycal_deliverys.setLayoutManager(gridLayoutManager);
        recyclerView_dAdapter = new RecyclerViewAdapter_AllCatigry(listItems,All_CategoriesActivity.this,
                All_CategoriesActivity.this,deirection,namelocation,idlocation);
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
            String url = null;
            if (deirection.equals("dash")){
                url= Urls.category +i+"&size=15";

            }else {
                url=Urls.category_fromSerch+idlocation;
            }

            StringRequest stringRequest = new StringRequest(Request.Method.GET,
                    url,
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
                                DataCattgryJson dataDelevery;
                                dataDelevery = gson.fromJson(response.toString(), DataCattgryJson.class);

//                                 int ids = dataDelevery.getResultDataDto().get(0).getId();
//                                Log.e("id", "" + ids);

                                numDoc.setText("More than  "+ dataDelevery.getResultData().getTotalItemsCount() +"  categories, find what you want.");
                                float x = dataDelevery.getResultData().getTotalItemsCount() / 15;
                                int x_tkreb = (int) x;
                                REGISTER_URL_mareds = x_tkreb + 1;


                                int lengt_for = dataDelevery.getResultData().getResultData().size();
                                if (lengt_for > 0) {

                                    for (int I = 0; I < lengt_for; I++) {
                                        listItems.add(new Data_categry_home(
                                                dataDelevery.getResultData().getResultData().get(I).getImagePath(),
                                                dataDelevery.getResultData().getResultData().get(I).getSectionTitleAr(),
                                                ""+dataDelevery.getResultData().getResultData().get(I).getId()
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
                                startService(new Intent(getApplicationContext(), service.class));
                                REGISTER_URL=0;
                                getjson(0);
                                //startActivity(new Intent(getApplicationContext(),Log_In.class));
//                                finish();
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

    private void getjson_onSearch(String serchtext) {// to fetch data to show in layout All

        listItems.clear();

//        final ProgressDialog progressDialog = new ProgressDialog(All_CategoriesActivity.this);
//        progressDialog.show();

        ConnectivityManager conMgr = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conMgr.getActiveNetworkInfo() != null && conMgr.getActiveNetworkInfo().isAvailable() && conMgr.getActiveNetworkInfo().isConnected()) {
            //Toast.makeText(context, "you don't have update "+REGISTER_URL, Toast.LENGTH_SHORT).show();
            StringRequest stringRequest = new StringRequest(Request.Method.GET,
                    Urls.searchCategry +serchtext,
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
                                DataCattgryJson dataDelevery;
                                dataDelevery = gson.fromJson(response.toString(), DataCattgryJson.class);

//                                 int ids = dataDelevery.getResultDataDto().get(0).getId();
//                                Log.e("id", "" + ids);

                                numDoc.setText("More than  "+ dataDelevery.getResultData().getTotalItemsCount() +"  categories, find what you want.");
                                float x = dataDelevery.getResultData().getTotalItemsCount() / 15;
                                int x_tkreb = (int) x;
                                REGISTER_URL_mareds = x_tkreb + 1;


                                int lengt_for = dataDelevery.getResultData().getResultData().size();
                                if (lengt_for > 0) {

                                    for (int I = 0; I < lengt_for; I++) {
                                        listItems.add(new Data_categry_home(
                                                dataDelevery.getResultData().getResultData().get(I).getImagePath(),
                                                dataDelevery.getResultData().getResultData().get(I).getSectionTitleAr(),
                                                ""+dataDelevery.getResultData().getResultData().get(I).getId()
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
                                //startActivity(new Intent(getApplicationContext(),Log_In.class));
//                                finish();
                                startService(new Intent(getApplicationContext(), service.class));
                                REGISTER_URL=0;
                                getjson(0);
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
            getjson_onSearch(search_categ.getText().toString().trim());
        }
    }




    private BottomNavigationView.OnNavigationItemSelectedListener navListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //selectedFragment = new HomeFragment();
                    break;
                case R.id.navigation_more:
//                    selectedFragment = new MoreFragment();
                    break;
                case R.id.navigation_date:
//                    selectedFragment = new DateFragment();
                    break;

                case R.id.navigation_save:
//                    selectedFragment = new SaveFragment();
                    break;
            }

//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                    selectedFragment).commit();
            return true;
        }
    };


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (deirection.equals("dash")) {
            finish();
        }else {
            Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
            intent.putExtra("id_loc", idlocation);
            intent.putExtra("name_location", namelocation);
            startActivity(intent);
            finish();
        }

    }
}
