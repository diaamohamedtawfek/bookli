package com.example.bookli.actitvity.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.bookli.R;

public class FotgetPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fotget_password);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
