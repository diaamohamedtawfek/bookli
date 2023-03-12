package com.example.bookli.actitvity.Dashboard;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.bookli.R;
import com.example.bookli.actitvity.fragments_Dashbord.DateFragment;
import com.example.bookli.actitvity.fragments_Dashbord.HomeFragment;
import com.example.bookli.actitvity.fragments_Dashbord.MoreFragment;
import com.example.bookli.actitvity.fragments_Dashbord.SaveFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


public class Dashboard extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getSupportActionBar().hide();

        if (savedInstanceState == null) {
            // to show fragment
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
        }
//
        BottomNavigationView bottomNav = findViewById(R.id.nav_view);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {


            Fragment selectedFragment = null;


            switch (item.getItemId()) {
                case R.id.navigation_home:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.navigation_more:
                    selectedFragment = new MoreFragment();
                    break;
                case R.id.navigation_date:
                    selectedFragment = new DateFragment();
                    break;

                case R.id.navigation_save:
                    selectedFragment = new SaveFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    selectedFragment).commit();
            return true;
        }
    };


}
