package com.example.bookli.actitvity.fragment;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.bookli.Adapter.RecyclerViewAdapter_All_Saved;
import com.example.bookli.Adapter.RecyclerViewAdapter_Rates;
import com.example.bookli.Adapter.RecyclerViewAdapter_serviceDoctor;
import com.example.bookli.MySingleton;
import com.example.bookli.R;
import com.example.bookli.SharedPrefManager_IdDoctor;
import com.example.bookli.SharedPrefManager_Login;
import com.example.bookli.Urls;
import com.example.bookli.models.Data_ProfileDoctor.DataProfile;
import com.example.bookli.models.Data_Rates;
import com.example.bookli.models.Data_doctorService;
import com.example.bookli.models.Dataratings.DataRate;
import com.example.bookli.service;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class Rates extends Fragment {


    public Rates(String idDoctors) {
        idDoctor=idDoctors;
        // Required empty public constructor
    }

    String idDoctor;
    View view;
    RecyclerView recycal_deliverys;
    private RecyclerViewAdapter_Rates recyclerView_dAdapter;
    public List<Data_Rates> listItems = new ArrayList<>();

    ArrayList<String> servicedoctor=new ArrayList<>();

    private GridLayoutManager gridLayoutManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_rates, container, false);


        recycal_deliverys = view.findViewById(R.id.recycal_allcatigry);
        recycal_deliverys.setHasFixedSize(true);
        gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        recycal_deliverys.setLayoutManager(gridLayoutManager);
        recyclerView_dAdapter = new RecyclerViewAdapter_Rates(listItems, getActivity());
        recycal_deliverys.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        recycal_deliverys.setAdapter(recyclerView_dAdapter);

//        if(SharedPrefManager_IdDoctor.getInstance(getActivity()).isLoggedIn()){
//            idDoctor=SharedPrefManager_IdDoctor.getInstance(getActivity()).getjidDoctor();
//
//        }

        getjson();
        return view;
    }



    private void getjson() {
        ConnectivityManager conMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conMgr.getActiveNetworkInfo() != null && conMgr.getActiveNetworkInfo().isAvailable() && conMgr.getActiveNetworkInfo().isConnected()) {
            StringRequest stringRequest = new StringRequest(Request.Method.GET,
                    Urls.rates_doctor +idDoctor,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.e("respone  _ tate",""+response);
                            if (response.length() > 0) {
//                                mSwipeRefreshLayout.setRefreshing(false);
//                                // progressDialog.dismiss();
                                Gson gson = new Gson();
                                DataRate dataCattgryJson;
                                dataCattgryJson = gson.fromJson(response.toString(), DataRate.class);

                                if (dataCattgryJson.getErrorStatus().equals(false)) {

                                    if (dataCattgryJson.getResultData().getResultData() != null) {


                                        if (dataCattgryJson.getResultData().getResultData().size() > 0) {

                                            for (int i = 0; i < dataCattgryJson.getResultData().getResultData().size(); i++) {
                                                Log.e("image_rate", dataCattgryJson.getResultData().getResultData().get(i).getPatient().getPatientProfilePicturePath());

                                                listItems.add(new Data_Rates(
                                                        "" + dataCattgryJson.getResultData().getResultData().get(i).getId(),
                                                        dataCattgryJson.getResultData().getResultData().get(i).getPatient().getPatientProfilePicturePath()
                                                        , dataCattgryJson.getResultData().getResultData().get(i).getPatient().getPatientFullName(),
                                                        "" + dataCattgryJson.getResultData().getResultData().get(i).getDrRating(),
                                                        dataCattgryJson.getResultData().getResultData().get(i).getDrComment()
                                                        , dataCattgryJson.getResultData().getResultData().get(i).getCommentDate()));

                                            }

                                            recyclerView_dAdapter.notifyDataSetChanged();
                                        }
                                    }
                                }
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
                                Toast.makeText(getActivity(), "Error Network Time Out_rate", Toast.LENGTH_LONG).show();
                            } else if (error instanceof AuthFailureError) {
                                getActivity().startService(new Intent(getActivity(), service.class));
                                getjson();
                                //TODO
                            } else if (error instanceof ServerError) {
//                                Toast.makeText(getActivity(), "ServerError", Toast.LENGTH_LONG).show();
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
