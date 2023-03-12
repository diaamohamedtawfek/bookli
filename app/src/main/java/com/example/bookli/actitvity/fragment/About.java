package com.example.bookli.actitvity.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.example.bookli.Adapter.RecyclerViewAdapter_serviceDoctor;
import com.example.bookli.MySingleton;
import com.example.bookli.R;
import com.example.bookli.SharedPrefManager_IdDoctor;
import com.example.bookli.SharedPrefManager_Login;
import com.example.bookli.Urls;
import com.example.bookli.models.Data_ProfileDoctor.DataProfile;
import com.example.bookli.models.Data_doctorService;
import com.example.bookli.service;
import com.google.gson.Gson;
import com.skyhope.materialtagview.TagView;
import com.skyhope.materialtagview.enums.TagSeparator;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class About extends Fragment {

    public About(String idDoctors) {
        idDoctor=idDoctors;
        // Required empty public constructor
    }


    String idDoctor;
    View view;
    TextView overview,address;
    Button numservice;

    RecyclerView recycal_deliverys;
    private RecyclerViewAdapter_serviceDoctor recyclerView_dAdapter;
    public List<Data_doctorService> listItems = new ArrayList<>();

    ArrayList<String> servicedoctor=new ArrayList<>();

    private GridLayoutManager gridLayoutManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        if(SharedPrefManager_IdDoctor.getInstance(getActivity()).isLoggedIn()){
//            idDoctor=SharedPrefManager_IdDoctor.getInstance(getActivity()).getjidDoctor();
//
//        }
        view=inflater.inflate(R.layout.fragment_about, container, false);
        overview=view.findViewById(R.id.text_overview_about);
        address=view.findViewById(R.id.text_addressdoctor_about);
        numservice=view.findViewById(R.id.bu_numservicedoctor_about);


        recycal_deliverys = view.findViewById(R.id.recycal_allcatigry);
        recycal_deliverys.setHasFixedSize(true);
        gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        recycal_deliverys.setLayoutManager(gridLayoutManager);
        recyclerView_dAdapter = new RecyclerViewAdapter_serviceDoctor(listItems, getActivity());
        recycal_deliverys.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recycal_deliverys.setAdapter(recyclerView_dAdapter);


        getjson();

        return view;
    }



    private void getjson() {
        Log.e("about >>>",Urls.get_doctor +idDoctor);
        ConnectivityManager conMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conMgr.getActiveNetworkInfo() != null && conMgr.getActiveNetworkInfo().isAvailable() && conMgr.getActiveNetworkInfo().isConnected()) {
            StringRequest stringRequest = new StringRequest(Request.Method.GET,
                    Urls.get_doctor +idDoctor,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.e("respose_services",""+response);
                            if (response.length() > 0) {
//                                mSwipeRefreshLayout.setRefreshing(false);
//                                // progressDialog.dismiss();
                                Gson gson = new Gson();
                                DataProfile dataCattgryJson;
                                dataCattgryJson = gson.fromJson(response.toString(), DataProfile.class);
//
//
                                overview.setText(""+dataCattgryJson.getResultData().getDoctorOverview());
                                address.setText(""+dataCattgryJson.getResultData().getClinicFullAddress());


                                for (int x=0;x < dataCattgryJson.getResultData().getDoctorServiceMobileDtoList().size();x++){
                                    servicedoctor.add(dataCattgryJson.getResultData().getDoctorServiceMobileDtoList().get(x).getService());
                                    //listItems.add(new Data_doctorService(""+dataCattgryJson.getResultData().getDoctorServiceMobileDtoList().get(x).getService()
                                    //));
                                }



                                if (servicedoctor.size()>4){
                                    for (int x=0;x < 4;x++){

                                        listItems.add(new Data_doctorService(""+servicedoctor.get(x)
                                        ));


                                    }
                                    TagView tagView = view.findViewById(R.id.tag_view_test);
                                    String[] tagList = new String[]{"Hello1", "Hello2", "Hello3"};
                                    tagView.addTagSeparator(TagSeparator.AT_SEPARATOR);
                                    tagView.setTagList(tagList);

                                }

                                if (dataCattgryJson.getResultData().getDoctorServiceMobileDtoList().size() >4){
                                    int size=dataCattgryJson.getResultData().getDoctorServiceMobileDtoList().size()-4;
                                    numservice.setText("+"+size);
                                    numservice.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            for (int x=4;x < servicedoctor.size();x++){

                                                listItems.add(new Data_doctorService(""+servicedoctor.get(x)  ));
                                                recyclerView_dAdapter.notifyDataSetChanged();
                                                numservice.setVisibility(View.GONE);
                                            }
                                        }
                                    });
                                }else{
                                    numservice.setVisibility(View.GONE);
                                }
                                recyclerView_dAdapter.notifyDataSetChanged();
//

                            } else {
//                                mSwipeRefreshLayout.setRefreshing(false);
                                //progressDialog.dismiss();
                                Log.e("test", "no");
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
//                            mSwipeRefreshLayout.setRefreshing(false);
                            // progressDialog.dismiss();
                            if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                                Toast.makeText(getActivity(), "Error Network Time Out_about", Toast.LENGTH_LONG).show();
                            } else if (error instanceof AuthFailureError) {
                                getActivity().startService(new Intent(getActivity(), service.class));
                                getjson();
                                //TODO
                            } else if (error instanceof ServerError) {
                                Toast.makeText(getActivity(), "ServerError About", Toast.LENGTH_LONG).show();
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