package com.example.bookli.actitvity.fragments_Dashbord;


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
import com.example.bookli.MySingleton;
import com.example.bookli.R;
import com.example.bookli.SharedPrefManager_Login;
import com.example.bookli.Urls;
import com.example.bookli.models.DataSaved.DataSaved;
import com.example.bookli.models.Data_Saved;
import com.example.bookli.service;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class SaveFragment extends Fragment {


    public SaveFragment() {
        // Required empty public constructor
    }

    RecyclerView recycal_deliverys;
    private RecyclerViewAdapter_All_Saved recyclerView_dAdapter;
    public List<Data_Saved> listItems = new ArrayList<>();
    private GridLayoutManager gridLayoutManager;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_save, container, false);


        recycal_deliverys = view.findViewById(R.id.recycal_allcatigry);
        recycal_deliverys.setHasFixedSize(true);
        gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        recycal_deliverys.setLayoutManager(gridLayoutManager);
        recyclerView_dAdapter = new RecyclerViewAdapter_All_Saved(listItems, getActivity());
        recycal_deliverys.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        recycal_deliverys.setAdapter(recyclerView_dAdapter);

        getjson();

        return view;

    }


    private void getjson() {
        ConnectivityManager conMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conMgr.getActiveNetworkInfo() != null && conMgr.getActiveNetworkInfo().isAvailable() && conMgr.getActiveNetworkInfo().isConnected()) {
            StringRequest stringRequest = new StringRequest(Request.Method.GET,
                    Urls.Saved +"1",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.length() > 0) {
//                                mSwipeRefreshLayout.setRefreshing(false);
//                                // progressDialog.dismiss();
                                Gson gson = new Gson();
                                DataSaved dataCattgryJson;
                                dataCattgryJson = gson.fromJson(response.toString(), DataSaved.class);
//
//
                                if (dataCattgryJson.getErrorStatus().equals(false)){
                                    for (int i=0;i<dataCattgryJson.getResultData().getResultData().size();i++){
                                        listItems.add(new Data_Saved(""+dataCattgryJson.getResultData().getResultData().get(i).getId(),
                                                dataCattgryJson.getResultData().getResultData().get(i).getProfilePicPath(),
                                                dataCattgryJson.getResultData().getResultData().get(i).getScientificDegree(),
                                                ""+dataCattgryJson.getResultData().getResultData().get(i).getRating(),
                                                ""+dataCattgryJson.getResultData().getResultData().get(i).getFullName(),
                                                ""+dataCattgryJson.getResultData().getResultData().get(i).getSavedFlag()
                                                ));

                                    }
                                    recyclerView_dAdapter.notifyDataSetChanged();
//
                                }
//
//                                pricedoctoe.setText(""+dataCattgryJson.getResultData().getReservationsFees()+"\n EGP");
//
//                                numrating.setText(""+dataCattgryJson.getResultData().getRatingCount()+"\n Ratings");
//                                Reservations.setText(""+dataCattgryJson.getResultData().getReservationsCount()+"\n Reservations");



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
                                Toast.makeText(getActivity(), "Error Network Time Out", Toast.LENGTH_LONG).show();
                            } else if (error instanceof AuthFailureError) {
                                getActivity().startService(new Intent(getActivity(), service.class));
                                getjson();
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
}
