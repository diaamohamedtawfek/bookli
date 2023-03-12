package com.example.bookli.actitvity.fragments_Dashbord;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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
import com.example.bookli.Adapter.RecyclerViewAdapter_limetCtogry;
import com.example.bookli.MySingleton;
import com.example.bookli.R;
import com.example.bookli.SharedPrefManager_Login;
import com.example.bookli.Urls;
import com.example.bookli.actitvity.All_CategoriesActivity;
import com.example.bookli.actitvity.Dashboard.handel_Recycal_header_fotur.Adapter;
import com.example.bookli.actitvity.Dashboard.handel_Recycal_header_fotur.models.FoodItem;
import com.example.bookli.actitvity.Dashboard.handel_Recycal_header_fotur.models.Footer;
import com.example.bookli.actitvity.Dashboard.handel_Recycal_header_fotur.models.Header;
import com.example.bookli.actitvity.Dashboard.handel_Recycal_header_fotur.models.RecyclerViewItem;
import com.example.bookli.actitvity.SearchActivity;
import com.example.bookli.models.DataCtegryHome.DataCategryHomeFinsh;
import com.example.bookli.models.DataRecommended.DataRecomendded;
import com.example.bookli.models.Data_categry_home;
import com.example.bookli.models.Databaners;
import com.example.bookli.service;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{


        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String someValue = intent.getStringExtra("Prodect");
//            text.setText(someValue);

            Log.e("refrech>>>>>>>",someValue);

            onRefresh();
        }
    };


    public HomeFragment() {
    }

    View view;

    LinearLayout linear_searsh;
    TextView seeAllCategory;

    RecyclerView recycal_deliverys;
    private RecyclerViewAdapter_limetCtogry recyclerView_dAdapter;
    public List<Data_categry_home> listItems = new ArrayList<>();
    private GridLayoutManager gridLayoutManager;


    RecyclerView recyclerView;

    ArrayList<String> imgesbody=new ArrayList<>();
    ArrayList<String> idbody=new ArrayList<>();
    ArrayList<String> fullname=new ArrayList<>();
    ArrayList<String> specialist=new ArrayList<>();
    ArrayList<Integer> rating=new ArrayList<>();
    ArrayList<String> saved=new ArrayList<>();
//fullname;specialist;specialist;rating;urlbaner;

    ArrayList<String> total_baner=new ArrayList<>();
    ArrayList<String> urlbaner=new ArrayList<>();
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_home, container, false);

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        /**
         * Showing Swipe Refresh animation on activity create
         * As animation won't start on onCreate, post runnable is used
         */
        mSwipeRefreshLayout.post(new Runnable() {

            @Override
            public void run() {

                mSwipeRefreshLayout.setRefreshing(true);

                // Fetching data from server
                defien_Ui();

                getjson_body_list(0);
                //Recycle hedar and footer
                initRecyclerView();
            }
        });

//        SharedPrefManager_IdDoctor.getInstance(getActivity()).logout();

        return view;
    }

    @Override
    public void onRefresh() {
        defien_Ui();

        getjson_body_list(0);

//        getjson_baner();

        //Recycle hedar and footer
        initRecyclerView();
    }

    private void defien_Ui() {
        listItems.clear();
        linear_searsh=view.findViewById(R.id.linear_search_home);
        linear_searsh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SearchActivity.class));
            }
        });


        seeAllCategory=view.findViewById(R.id.text_seeAllCategry_home);
        seeAllCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), All_CategoriesActivity.class);
                intent.putExtra("deriction","dash");
                startActivity(intent);
            }
        });

        recycal_deliverys = view.findViewById(R.id.recycal_categry_home);
        recycal_deliverys.setHasFixedSize(true);
        gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        recycal_deliverys.setLayoutManager(gridLayoutManager);
        recycal_deliverys.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
        recyclerView_dAdapter = new RecyclerViewAdapter_limetCtogry(listItems, getActivity());
        recycal_deliverys.setAdapter(recyclerView_dAdapter);
        getjson(0);
    }


    private void initRecyclerView() {
        imgesbody.clear();
        fullname.clear();specialist.clear();specialist.clear();rating.clear();
       urlbaner.clear();
        recyclerView = view.findViewById(R.id.my_recycler_view_allQadea);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //add space item decoration and pass space you want to give
//        recyclerView.addItemDecoration(new Space(5));
        //finally set adapter
        recyclerView.setAdapter(new Adapter(createDummyList(), getActivity()));

//        recyclerView.smoothScrollToPosition(theListAdapter.getCount() -1);
        //recyclerView.scrollToPosition(recyclerView.getAdapter().getItemCount() - 1);
    }

    private List<RecyclerViewItem> createDummyList() {
        List<RecyclerViewItem> recyclerViewItems = new ArrayList<>();
        recyclerViewItems.clear();
        recyclerViewItems.clear();

        Header header = new Header("");
        recyclerViewItems.add(header);


        //if(imgesbody.size()> 0) {
            for (int i = 0; i < urlbaner.size(); i++) {
                Log.e("urlbanersss",urlbaner.get(i));
                Databaners databaners = new Databaners(urlbaner.get(i));
            }
        //}



        Footer footer = new Footer("Recomended");
            //add footer
            recyclerViewItems.add(footer);

        for (int i = 0; i < imgesbody.size(); i++) {
            FoodItem foodItem = new FoodItem(imgesbody.get(i),fullname.get(i),
                    specialist.get(i),rating.get(i),idbody.get(i),saved.get(i));
            //add food items
            recyclerViewItems.add(foodItem);
        }

//
//        Database_taskassigncomments_local database_taskassigncomments_local=new Database_taskassigncomments_local(Singel_Qadea.this);
//        database_taskassigncomments_local.open();
//        ArrayList<Data_commend_local> data_commend_locals = new ArrayList<>();
//        if (database_taskassigncomments_local.chkDB()==true){
//            data_commend_locals=database_taskassigncomments_local.getcommentby_userid_taskid(userid,taskassignedpk);
//            if (!data_commend_locals.isEmpty()){
//                for (int x=0;x<data_commend_locals.size();x++) {
//                    Footer footer = new Footer(data_commend_locals.get(x).getCommenttext(),
//                            "" + data_commend_locals.get(x).getCommentdate(), "https://cdn.pixabay.com/photo/2016/12/26/17/28/background-1932466_640.jpg");
//                    //add footer
//                    recyclerViewItems.add(footer);
//                }
//            }
//        }
//
////        for (int x=0;x<4;x++){
////            Footer footer = new Footer("Your diet is a bank account. Good food choices are good investments.",
////                    ""+x, "https://cdn.pixabay.com/photo/2016/12/26/17/28/background-1932466_640.jpg");
////            //add footer
////            recyclerViewItems.add(footer);
////        }
        return recyclerViewItems;
    }



    private void getjson(int i) {
        // to fetch data to show in layout data
//        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
//        progressDialog.show();

        ConnectivityManager conMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conMgr.getActiveNetworkInfo() != null && conMgr.getActiveNetworkInfo().isAvailable() && conMgr.getActiveNetworkInfo().isConnected()) {
            StringRequest stringRequest = new StringRequest(Request.Method.GET,
                    Urls.category_Home,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.length() > 0) {
                               // progressDialog.dismiss();
                                Gson gson = new Gson();
                                DataCategryHomeFinsh dataCattgryJson;
                                dataCattgryJson = gson.fromJson(response.toString(), DataCategryHomeFinsh.class);

                                if (dataCattgryJson.getErrorStatus().equals(false)){
                                    int lengt_for=dataCattgryJson.getResultData().getResultData().size();
                                    if (lengt_for > 0){

                                        for (int x=0;x < lengt_for;x++){
                                            Log.e("image homr",""+dataCattgryJson.getResultData().getResultData().get(x).getImagePath());
                                            listItems.add(new Data_categry_home(
                                                    dataCattgryJson.getResultData().getResultData().get(x).getImagePath(),
                                                    dataCattgryJson.getResultData().getResultData().get(x).getSectionTitleAr(),
                                                    ""+dataCattgryJson.getResultData().getResultData().get(x).getId()
                                            ));

                                            recyclerView_dAdapter.notifyDataSetChanged();
                                        }
                                    }
                                }

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
                                Toast.makeText(getActivity(), "Error Network Time Out", Toast.LENGTH_LONG).show();
                            } else if (error instanceof AuthFailureError) {
                                getActivity().startService(new Intent(getActivity(), service.class));
                                getjson(0);
//                                startActivity(new Intent(getActivity(),Log_In.class));
                                //Toast.makeText(getApplicationContext(), "AuthFailureError", Toast.LENGTH_LONG).show();
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
        };
    }



    // baner and receomend
    private void getjson_body_list(int i) {
        // to fetch data to show in layout data
//        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
//        progressDialog.show();

        Log.e("recmmend_home",Urls.recmmend_home);
        ConnectivityManager conMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conMgr.getActiveNetworkInfo() != null && conMgr.getActiveNetworkInfo().isAvailable() && conMgr.getActiveNetworkInfo().isConnected()) {
            StringRequest stringRequest = new StringRequest(Request.Method.GET,
                    Urls.recmmend_home,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.length() > 0) {
                                mSwipeRefreshLayout.setRefreshing(false);
                               // progressDialog.dismiss();
                                Gson gson = new Gson();
                                DataRecomendded dataCattgryJson;
                                dataCattgryJson = gson.fromJson(response.toString(), DataRecomendded.class);

                                int lengt_for=dataCattgryJson.getResultData().getResultData().size();
                                if (lengt_for > 0){

                                    for (int x=0;x < lengt_for;x++){
                                        idbody.add(""+dataCattgryJson.getResultData().getResultData().get(x).getId());
                                        saved.add(""+dataCattgryJson.getResultData().getResultData().get(x).getSavedFlag());
                                        imgesbody.add(dataCattgryJson.getResultData().getResultData().get(x).getProfilePicPath());
                                        fullname.add(dataCattgryJson.getResultData().getResultData().get(x).getFullName());
                                        specialist.add(dataCattgryJson.getResultData().getResultData().get(x).getScientificDegree());
                                        rating.add(dataCattgryJson.getResultData().getResultData().get(x).getRating());
                                        Log.e("rateee",""+dataCattgryJson.getResultData().getResultData().get(x).getRating());

                                        recyclerView.setAdapter(new Adapter(createDummyList(), getActivity()));
                                    }
                                }
                            } else {
                               // progressDialog.dismiss();
                                Log.e("test", "no");
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            mSwipeRefreshLayout.setRefreshing(false);
                           // progressDialog.dismiss();
                            if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                                Toast.makeText(getActivity(), "Error Network Time Out", Toast.LENGTH_LONG).show();
                            } else if (error instanceof AuthFailureError) {
//                                startActivity(new Intent(getActivity(),Log_In.class));
//                                Toast.makeText(getActivity(), "AuthFailureError", Toast.LENGTH_LONG).show();
                                getActivity().startService(new Intent(getActivity(), service.class));
                                getjson_body_list(0);
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
        };
    }
}
