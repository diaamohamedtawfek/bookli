package com.example.bookli.actitvity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookli.R;

public class SearchActivity extends AppCompatActivity {


    LinearLayout location_lin,caticgry_lin;
    String idCateg,nameCateg,nameloca=null;
    String idloc=null;

    TextView namecategry_text;
    TextView namelocation_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        namecategry_text=findViewById(R.id.text_namecategry_search);
        namelocation_text=findViewById(R.id.text_namelocation_searrch);

        Bundle extras = getIntent().getExtras(); // to get move intent
        if (extras != null) {
            String ids = extras.getString("id_Cat");
            String name = extras.getString("name");
            String namelocs = extras.getString("name_location");
            String idlocs = extras.getString("id_loc");

            if (ids!=null){
                idCateg= ids;
                nameCateg=name;
                namecategry_text.setText(nameCateg);
            }

            if (idlocs!=null){
                idloc=idlocs;
                nameloca=namelocs;
                namelocation_text.setText(nameloca);
            }

        }

        location_lin=findViewById(R.id.linear_searchlocation_serach);
        location_lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), All_Location.class);
                intent.putExtra("deriction","search");
                startActivity(intent);
                finish();
            }
        });

        caticgry_lin=findViewById(R.id.linear_serachCategry_search);
        caticgry_lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (idloc!=null) {
                    Intent intent = new Intent(getApplicationContext(), All_CategoriesActivity.class);
                    intent.putExtra("deriction", "search");
                    intent.putExtra("idlocation", idloc);
                    intent.putExtra("namelocation", nameloca);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(SearchActivity.this, "get Location", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
/*

 Intent intent=new Intent(getActivity(), All_CategoriesActivity.class);
                intent.putExtra("deriction","dash");
                startActivity(intent);



                 Bundle extras = getIntent().getExtras(); // to get move intent
        if (extras != null) {
            String a = extras.getString("deriction");
            if (a!=null){
                deirection= a;
            }

        }
 */