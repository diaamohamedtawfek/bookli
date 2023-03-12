package com.example.bookli;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SharedPrefManager_IdDoctor {

    private static SharedPrefManager_IdDoctor mInstance;
    private static Context mCtx;

    private static final String SHARED_PREF_NAME = "iddocttor";
    private static final String KEY_idDoctor = "iddoctor";
    private static final String KEY_imageDoctoer = "imageDoctoer";
    private static final String KEY_nameDoctoer = "nameDoctor";


    private static final String KEY_idCilnc = "idCilnc";
    private static final String KEY_idClinkBranch = "idCilnc_branch";
    private static final String KEY_locationClinec = "nameClinec";
    private static final String KEY_termesClinec = "termesClinec";


    private static final String KEY_dayname_clander = "KEY_dayname_clander";
    private static final String KEY_numDay_clander = "KEY_numDay_clander";
    private static final String KEY_name_mnth_clander = "KEY_name_mnth_clander";
    private static final String KEY_start_clander = "KEY_start_clander";
    private static final String KEY_id_clander = "KEY_start_clandersss";

    private static final String KEY_date_calender = "KEY_datecalender";

    private SharedPrefManager_IdDoctor(Context context) {
        mCtx = context;

    }

    public static synchronized SharedPrefManager_IdDoctor getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager_IdDoctor(context);
        }
        return mInstance;
    }

    public boolean userLogin(String idDoctor ,String image ,String name){

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Log.e("iddoctoor>Serd",idDoctor);
        editor.putString(KEY_idDoctor, idDoctor);
        editor.putString(KEY_imageDoctoer, image);
        editor.putString(KEY_nameDoctoer, name);

        editor.apply();

        return true;
    }

    public boolean userLogin_id(String idDoctor) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

//        Log.e("iddoctoor>Serd",idDoctor);
        editor.putString(KEY_idDoctor, idDoctor);

        editor.apply();
        return true;
    }

    public boolean ClincData(String idCilnc,String idClinkBranch ,String nameClinec ,String termesClinec){

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(KEY_idCilnc, idCilnc);
        editor.putString(KEY_idClinkBranch, idClinkBranch);
        editor.putString(KEY_locationClinec, nameClinec);
        editor.putString(KEY_termesClinec, termesClinec);

        editor.apply();

        return true;
    }

    public boolean CalenderData(String id ,String nameDay ,String numDay,String nameMonth ,String startClander){

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(KEY_dayname_clander, nameDay);
        editor.putString(KEY_numDay_clander, numDay);
        editor.putString(KEY_name_mnth_clander, nameMonth);
        editor.putString(KEY_start_clander, startClander);
        editor.putString(KEY_id_clander ,id);

        editor.apply();

        return true;
    }
    public boolean DateRevervation(String calendarDate) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(KEY_date_calender, calendarDate);
        editor.apply();

        return true;


    }

    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        if(sharedPreferences.getString(KEY_idDoctor, null) != null){
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

    public String getjidDoctor(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_idDoctor, null);
    }

    public String daterervationdate(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_date_calender, null);
    }



    public String getimageDoctor(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_imageDoctoer, null);
    }

    public String getnameDoctor(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_nameDoctoer, null);
    }

    public String getjidClinc(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_idCilnc, null);
    }


    public String getlocationClinc(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_locationClinec, null);
    }

    public String gettermes_Clinc(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_termesClinec, null);
    }


    // calender

    public String getdayname_clander(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_dayname_clander, null);
    }

    public String getnumDay_clander(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_numDay_clander, null);
    }

    public String getname_mnth_clander(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_name_mnth_clander, null);
    }


    public String getid_clander(){//KEY_date_calender
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_id_clander, null);
    }

    public String getid_clinck_branc(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_idClinkBranch, null);
    }

    public String getstart_clander(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_start_clander, null);
    }



    public String get_doctor_id(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_idDoctor, null);
    }



}
