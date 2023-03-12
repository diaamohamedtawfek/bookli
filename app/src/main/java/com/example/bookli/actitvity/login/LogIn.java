package com.example.bookli.actitvity.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.example.bookli.SharedPrefManager_Login;
import com.example.bookli.Urls;
import com.example.bookli.actitvity.Dashboard.Dashboard;
import com.example.bookli.actitvity.login.model.DataLogin;
import com.example.bookli.actitvity.login.model.dataResponceLogin.DataResponseLogin;
import com.example.bookli.models.dataLocation.DataLocation;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class LogIn extends AppCompatActivity {

    TextView forgetPassord,text_createnewAcount_login;

    EditText username,password;

    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        getSupportActionBar().hide();

//        editText2.setKeyListener(null);

        login=findViewById(R.id.bu_login_login);
        forgetPassord=findViewById(R.id.text_forgetpassword_login);
        forgetPassord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),FotgetPassword.class));
            }
        });

        text_createnewAcount_login=findViewById(R.id.text_createnewAcount_login);
        text_createnewAcount_login.setMovementMethod(LinkMovementMethod.getInstance());
        text_createnewAcount_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SignUp.class));
            }
        });

        username=findViewById(R.id.ed_username_login);
        password=findViewById(R.id.ed_upassword_login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                startActivity(new Intent(getApplicationContext(), Dashboard.class));

                if (username.getText().toString().trim().isEmpty() && password.getText().toString().trim().isEmpty()) {

                } else {
//                    Toast.makeText(LogIn.this, "done", Toast.LENGTH_SHORT).show();
                    getjson(username.getText().toString().trim(), password.getText().toString().trim());
                }
            }
        });

    }

    private void getjson(final String usernames, final String passwords) {

        final ProgressDialog progressDialog=new ProgressDialog(LogIn.this);
        progressDialog.show();
        RequestQueue requestQueue = Volley.newRequestQueue(LogIn.this);
        Gson gson = new Gson();
//
        DataLogin contactsTop=new DataLogin();

        contactsTop.setUserEmail(usernames);
        contactsTop.setPassword(passwords);

        Log.e("bodySeana",gson.toJson(contactsTop));

        JSONObject jsonObject = null;
        try {
            jsonObject=new JSONObject(gson.toJson(contactsTop));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Urls.login
                , jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progressDialog.dismiss();

                Log.e("login",response.toString());

                try {

                    Gson gson = new Gson();
                    DataResponseLogin dataDelevery;
                    dataDelevery = gson.fromJson(response.toString(), DataResponseLogin.class);


                    if ( dataDelevery.getErrorStatus().equals(false) || dataDelevery.getErrorStatus()==false ) {
                        SharedPrefManager_Login.getInstance(getApplicationContext())
                                .userLogin(usernames, passwords,
                                        dataDelevery.getResultData().getTokenPair().getJwt(),
                                        dataDelevery.getResultData().getTokenPair().getRefreshToken());

                        startActivity(new Intent(getApplicationContext(), Dashboard.class));
                    }else{
                        username.setError("enter");
                        password.setError("enter");
                    }
                }catch (Exception e){

                }


//                Toast.makeText(Log_In.this, ""+response, Toast.LENGTH_SHORT).show();

//                JSONObject jsonObj = null;
//                try {
//
//                    //jsonObj = new JSONObject(response);
//                    String jwt = response.getString("jwt");
//                    Log.e("login_ndf_jwt",jwt);
//
//                    if (jwt != null) {
//                        String accessType = response.getString("accessType");
//                        Log.e("login_ndf_accessType", accessType);
//                        String status = response.getString("status");
//                        Log.e("login_ndf_status", status);
//                        String statusMessage = response.getString("statusMessage");
//                        Log.e("login_ndf_statusMessage", statusMessage);
//
////                        SharedPrefManager_Login.getInstance(getApplicationContext())
////                                .userLogin(
////                                        jwt, accessType, statusMessage, status, emails
////                                );
////                        startActivity(new Intent(getApplicationContext(), Dashboard.class));
////                        finish();
//                    }

//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    e.getMessage();
//                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

//                Log.e("");
                progressDialog.dismiss();
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Toast.makeText(getApplicationContext(), "Error Network Time Out", Toast.LENGTH_LONG).show();
                } else if (error instanceof AuthFailureError) {
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
        });
        requestQueue.add(jsonObjectRequest);

    }
}
