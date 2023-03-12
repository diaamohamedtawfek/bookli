package com.example.bookli.actitvity.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.example.bookli.actitvity.login.model.Data_signUp.DataSignup;
import com.example.bookli.actitvity.login.model.Data_signUp.Role;
import com.example.bookli.actitvity.login.model.dataResponceLogin.DataResponseLogin;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SignUp extends AppCompatActivity {

    EditText username,password,phone,email;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        email=findViewById(R.id.ed_email_login);
        username=findViewById(R.id.ed_name_login);
        phone=findViewById(R.id.ed_phone_login);
        password=findViewById(R.id.ed_password_login);

        login=findViewById(R.id.bu_login_login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                startActivity(new Intent(getApplicationContext(), Dashboard.class));

                if (username.getText().toString().trim().isEmpty() && password.getText().toString().trim().isEmpty()
                &&email.getText().toString().trim().isEmpty() && phone.getText().toString().trim().isEmpty()) {

                } else {
//                    Toast.makeText(LogIn.this, "done", Toast.LENGTH_SHORT).show();
                    getjson(username.getText().toString().trim(), password.getText().toString().trim());
                }
            }
        });
    }


    private void getjson(final String usernames, final String passwords) {

        final ProgressDialog progressDialog=new ProgressDialog(SignUp.this);
        progressDialog.show();
        RequestQueue requestQueue = Volley.newRequestQueue(SignUp.this);
        Gson gson = new Gson();
//
        DataSignup contactsTop=new DataSignup();

        contactsTop.setEmail(email.getText().toString().trim());
        contactsTop.setFullName(username.getText().toString().trim());
        contactsTop.setPassword(password.getText().toString().trim());
        contactsTop.setPhoneNumber(phone.getText().toString().trim());
        contactsTop.setPatientFlag(1);
        Role role=new Role();
        role.setId("1");
        List<Role> itemList = new ArrayList<Role>();
        contactsTop.setRole(itemList);

        Log.e("bodySeana",gson.toJson(contactsTop));

        JSONObject jsonObject = null;
        try {
            jsonObject=new JSONObject(gson.toJson(contactsTop));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Urls.sign_up
                , jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progressDialog.dismiss();

                Log.e("signUp",response.toString());
//
//                try {
//
//                    Gson gson = new Gson();
//                    DataResponseLogin dataDelevery;
//                    dataDelevery = gson.fromJson(response.toString(), DataResponseLogin.class);
//
//
//                    if ( dataDelevery.getErrorStatus().equals(false) || dataDelevery.getErrorStatus()==false ) {
//                        SharedPrefManager_Login.getInstance(getApplicationContext())
//                                .userLogin(usernames, passwords,
//                                        dataDelevery.getResultData().getTokenPair().getJwt(),
//                                        dataDelevery.getResultData().getTokenPair().getRefreshToken());
//
//                        startActivity(new Intent(getApplicationContext(), Dashboard.class));
//                    }else{
//                        username.setError("enter");
//                        password.setError("enter");
//                    }
//                }catch (Exception e){

//                }


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