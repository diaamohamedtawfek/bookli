package com.example.bookli;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager_Login {

    private static SharedPrefManager_Login mInstance;
    private static Context mCtx;

    private static final String SHARED_PREF_NAME = "mysharedpref122";

    private static final String KEY_email = "email";
    private static final String KEY_password = "password";

    private static final String KEY_Token = "Token";
    private static final String KEY_refreshToken = "refreshToken";

//    private static final String KEY_jwt = "jwt";
//    private static final String KEY_accessType = "accessType";
//    private static final String KEY_statusMessage = "statusMessage";
//    private static final String KEY_status = "status";
//    private static final String KEY_email = "email";
//    private static final String KEY_roleId = "roleId";


    private SharedPrefManager_Login(Context context) {
        mCtx = context;

    }

    public static synchronized SharedPrefManager_Login getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager_Login(context);
        }
        return mInstance;
    }

        public boolean userLogin(String email,String password,String token ,String refrechToken){

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();


        editor.putString(KEY_password, password);
        editor.putString(KEY_Token, token);
        editor.putString(KEY_email, email);
        editor.putString(KEY_refreshToken, refrechToken);
        editor.apply();

        return true;
    }

//    public boolean userLogin(String jwt, String accessType, String statusMessage, String status, String email, String roleId){
//
//        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//
//        editor.putString(KEY_jwt, jwt);
//        editor.putString(KEY_accessType, accessType);
//        editor.putString(KEY_statusMessage, statusMessage);
//
//        editor.putString(KEY_status, status);
//        editor.putString(KEY_email, email);
//        editor.putString(KEY_roleId, roleId);
//        editor.apply();
//
//        return true;
//    }

    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        if(sharedPreferences.getString(KEY_email, null) != null){
            return true;
        }
        return false;
    }

    public boolean logout(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }


    public String getjwt(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_Token, null);
    }


    public String getrefrechToken(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_refreshToken, null);
    }

//    public String getroleId(){
//        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        return sharedPreferences.getString(KEY_roleId, null);
//    }

//    public String getaccessType(){
//        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        return sharedPreferences.getString(KEY_accessType, null);
//    }
//    public String getstatusMessage(){
//        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        return sharedPreferences.getString(KEY_statusMessage, null);
//    }

//    public String getstatus(){
//        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        return sharedPreferences.getString(KEY_status, null);
//    }

    public String getemail(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_email, null);
    }

    public String getpassword(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_password, null);
    }
}
