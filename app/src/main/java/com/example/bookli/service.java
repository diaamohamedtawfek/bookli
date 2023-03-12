package com.example.bookli;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

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
import com.example.bookli.actitvity.Dashboard.Dashboard;
import com.example.bookli.actitvity.login.model.RefrechToken;
import com.example.bookli.actitvity.login.model.dataResponceLogin.DataResponseLogin;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class service extends IntentService {
    public static volatile boolean shouldStop = false;
    public service() {
        super(service.class.getSimpleName());
    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    protected void onHandleIntent(Intent intent) {

        final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        Gson gson = new Gson();
        RefrechToken contactsTop = new RefrechToken();
        contactsTop.setRefreshToken(SharedPrefManager_Login.getInstance(getApplicationContext()).getrefrechToken());

        Log.e("Volley:Response ", gson.toJson(contactsTop));

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(gson.toJson(contactsTop));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Urls.refresh_token
                , jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
//                progressDialog.dismiss();

//                JSONObject sys = response;//// result
//
//                Gson gson = new Gson();
//                DataResposeLogin dataDelevery;
//                dataDelevery = gson.fromJson(response.toString(), DataResposeLogin.class);
//                if (dataDelevery.getErrorStatus().equals(true)) {
//
////                    email.setError("يرجي التاكد من الاميل");
////                    password.setError("يرجي التاكد من الباسورد");
//
//                } else {
//                    getjson(dataDelevery.getResultData().getToken(), dataDelevery.getResultData().getType(), dataDelevery.getResultData().getAuthValue());
//                }

                try {

                    Gson gson = new Gson();
                    DataResponseLogin dataDelevery;
                    dataDelevery = gson.fromJson(response.toString(), DataResponseLogin.class);


                    if ( dataDelevery.getErrorStatus().equals(false) || dataDelevery.getErrorStatus()==false ) {
                        SharedPrefManager_Login.getInstance(getApplicationContext())
                                .userLogin(SharedPrefManager_Login.getInstance(getApplicationContext()).getemail(),
                                        SharedPrefManager_Login.getInstance(getApplicationContext()).getpassword(),
                                        dataDelevery.getResultData().getTokenPair().getJwt(),
                                        dataDelevery.getResultData().getTokenPair().getRefreshToken());

                        startActivity(new Intent(getApplicationContext(), Dashboard.class));
                    }else{
                    }
                }catch (Exception e){

                }
                Log.e("?>>>response>>>>>>",response.toString());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               // progressDialog.dismiss();
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Toast.makeText(getApplicationContext(), "Error Network Time Out", Toast.LENGTH_LONG).show();
                } else if (error instanceof AuthFailureError) {
//                    startActivity(new Intent(getApplicationContext(), LogIn.class));
//                    finish();
                    Toast.makeText(getApplicationContext(), "AuthFailureError", Toast.LENGTH_LONG).show();
                    //TODO
                } else if (error instanceof ServerError) {
                    Toast.makeText(getApplicationContext(), "يرجي التاكد من ادخال البيانات", Toast.LENGTH_LONG).show();
                    //TODO
                } else if (error instanceof NetworkError) {
                    Toast.makeText(getApplicationContext(), "NetworkError", Toast.LENGTH_LONG).show();
                    //TODO
                } else if (error instanceof ParseError) {
                    Toast.makeText(getApplicationContext(), "ParseError", Toast.LENGTH_LONG).show();
                    //TODO
                }
            }

        });
//        {
//            /** Passing some request headers* */
//            @Override
//            public Map getHeaders() throws AuthFailureError {
//                HashMap headers = new HashMap();
//                headers.put("Authorization",
//                        SharedPrefManager_Login.getInstance(getApplicationContext()).getaccessType()+" "+
//                                SharedPrefManager_Login.getInstance(getApplicationContext()).getjwt());
//                return headers;
//            }
//        };;
        requestQueue.add(jsonObjectRequest);


        Intent intent1 = new Intent("com.example.sharpestapp");
        intent1.putExtra("someName", "Tutorialspoint.com");
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent1);
        if(shouldStop){
            stopSelf();
            return;
        }
    }
}