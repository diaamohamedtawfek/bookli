package com.example.bookli.actitvity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.bookli.actitvity.Dashboard.Dashboard;
import com.example.bookli.R;
import com.example.bookli.actitvity.login.LogIn;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(new Intent(getApplicationContext(), LogIn.class));
//        startActivity(new Intent(getApplicationContext(), Dashboard.class));
        finish();
//        startActivity(new Intent(getApplicationContext(), LogIn.class));
    }




}
