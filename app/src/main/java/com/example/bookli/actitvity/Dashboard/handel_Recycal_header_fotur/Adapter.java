package com.example.bookli.actitvity.Dashboard.handel_Recycal_header_fotur;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

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
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.example.bookli.MySingleton;
import com.example.bookli.R;
import com.example.bookli.SharedPrefManager_IdDoctor;
import com.example.bookli.SharedPrefManager_Login;
import com.example.bookli.Urls;
import com.example.bookli.actitvity.Dashboard.handel_Recycal_header_fotur.models.FoodItem;
import com.example.bookli.actitvity.Dashboard.handel_Recycal_header_fotur.models.Footer;
import com.example.bookli.actitvity.Dashboard.handel_Recycal_header_fotur.models.Header;
import com.example.bookli.actitvity.Dashboard.handel_Recycal_header_fotur.models.RecyclerViewItem;
import com.example.bookli.actitvity.ProfileDoctor;
import com.example.bookli.models.Databaner.DataBaneerJson;
import com.example.bookli.service;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import technolifestyle.com.imageslider.FlipperLayout;


public class Adapter extends RecyclerView.Adapter implements  BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener{

    ArrayList<String> urlbaner=new ArrayList<>();

    //Declare List of Recyclerview Items
    List<RecyclerViewItem> recyclerViewItems;
    //Header Item Type
    private static final int HEADER_ITEM = 0;
    //Footer Item Type
    private static final int FOOTER_ITEM = 1;
    ////Food Item Type
    private static final int FOOD_ITEM = 2;
    Context mContext;
    Activity activity;
    public Adapter(List<RecyclerViewItem> recyclerViewItems, Context mContext) {
        this.recyclerViewItems = recyclerViewItems;
        this.mContext = mContext;
//        this.activity=activity;


    }

    //>>>>>>>>>>>>>>>>  change layout
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row;
        //Check fot view Type inflate layout according to it
        if (viewType == HEADER_ITEM) {
            row = inflater.inflate(R.layout.item_hader_singelqadea, parent, false);
            return new HeaderHolder(row);
        } else if (viewType == FOOTER_ITEM) {
            row = inflater.inflate(R.layout.item_footor_qadea, parent, false);
            //row.setVisibility(View.VISIBLE);
            return new FooterHolder(row);
        } else if (viewType == FOOD_ITEM) {
            row = inflater.inflate(R.layout.item_bodyhomerecommend, parent, false);
            return new FoodItemHolder(row);

        }
        return null;
    }//>>>>>>>>>>>>>>>> end change layout


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RecyclerViewItem recyclerViewItem = recyclerViewItems.get(position);
        //Check holder instance to populate data  according to it
        if (holder instanceof HeaderHolder) {//>>> Header
            //getjson_baner();
            final HeaderHolder headerHolder = (HeaderHolder) holder;
            StringRequest stringRequest = new StringRequest(Request.Method.GET,
                    Urls.baner ,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.length() > 0) {
                                //progressDialog.dismiss();

                                Log.e("baner >>> json >>>>",response);
                                Gson gson = new Gson();
                                DataBaneerJson dataCattgryJson;
                                dataCattgryJson = gson.fromJson(response.toString(), DataBaneerJson.class);

                                 int lengt_for=dataCattgryJson.getResultData().getResultData().size();
                                if (lengt_for > 0){
                                    HashMap<String, String> file_maps = new HashMap<>();
                                    for (int x=0;x < lengt_for;x++){
                                           // Log.e("urlbaners",dataCattgryJson.getResultData().getResultData().get(x).getBannerPath());
                                        urlbaner.add(dataCattgryJson.getResultData().getResultData().get(x).getBannerPath());
                                        try {
                                            if (dataCattgryJson.getResultData().getResultData().get(x).getBannerPath()==null
                                            ||dataCattgryJson.getResultData().getResultData().get(x).getBannerPath().equals(null)
                                            ||dataCattgryJson.getResultData().getResultData().get(x).getBannerPath()==""||
                                                    dataCattgryJson.getResultData().getResultData().get(x).getBannerPath().equals(""))
                                            {

                                            }else{
                                                file_maps.put("Hannibal", dataCattgryJson.getResultData().getResultData().get(x).getBannerPath());
                                            }
                                        for(String name : file_maps.keySet()){
                                            try {
                                                TextSliderView textSliderView = new TextSliderView(mContext);
                                                // initialize a SliderLayout
                                                textSliderView
                                                        //.description(name)
                                                        .image(file_maps.get(name))
                                                        .setScaleType(BaseSliderView.ScaleType.Fit);
                                                //.setOnSliderClickListener(mContext);
                                                //add your extra information
                                                textSliderView.bundle(new Bundle());
                                                textSliderView.getBundle()
                                                        .putString("extra",name);
                                                headerHolder.mDemoSlider.addSlider(textSliderView);
                                                textSliderView.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                                                    @Override public void onSliderClick(BaseSliderView slider) {
                                                        Log.e("MyActivity", "index selected:" + headerHolder.mDemoSlider.getCurrentPosition());
                                                    }
                                                });
                                            }catch (Exception e){
//                                                headerHolder.mDemoSlider.setVisibility(View.GONE);
                                            }

                                        }

                                            headerHolder.mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
                                            headerHolder.mDemoSlider.setCustomAnimation(new DescriptionAnimation());
                                            headerHolder.mDemoSlider.setDuration(50000);

                                    }catch (Exception e){
                                            headerHolder.mDemoSlider.setVisibility(View.GONE);
                                        }

                                    }
                                }else {
                                    headerHolder.mDemoSlider.setVisibility(View.GONE);
                                    Log.e("test", "no");
                                }
                            } else {
                                headerHolder.mDemoSlider.setVisibility(View.GONE);
                                Log.e("test", "no");
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            headerHolder.liner_header.setVisibility(View.GONE);
                            //progressDialog.dismiss();
                            if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                                Toast.makeText(mContext, "Error Network Time Out", Toast.LENGTH_LONG).show();
                            } else if (error instanceof AuthFailureError) {
//                                mContext.startService(new Intent(mContext, service.class));
//                                getjson_body_list(0);
//                                startActivity(new Intent(getActivity(),Log_In.class));
                                //Toast.makeText(getApplicationContext(), "AuthFailureError", Toast.LENGTH_LONG).show();
                                //TODO
                            } else if (error instanceof ServerError) {
                                Toast.makeText(mContext, "ServerError", Toast.LENGTH_LONG).show();
                                //TODO
                            } else if (error instanceof NetworkError) {
                                Toast.makeText(mContext, "NetworkError", Toast.LENGTH_LONG).show();
                                //TODO
                            } else if (error instanceof ParseError) {
                                Toast.makeText(mContext, "ParseError", Toast.LENGTH_LONG).show();
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
                            SharedPrefManager_Login.getInstance(mContext).getjwt());
                    return headers;
                }
            };
            MySingleton.getInstance(mContext).addToRequestQueue(stringRequest);



            //headerHolder.flipperLayout.setScrollTimeInSec(5);
//            headerHolder.text_id.setText("رقم الطلب: : "+header.getId());
//            headerHolder.text_email.setText("your email : "+header.getEmail()+"\n and : "+header.getNew_email());

            //Glide.with(mContext).load(header.getImageUrl()).into(headerHolder.imageViewHeader);

        } else if (holder instanceof FooterHolder) {//Footer
            FooterHolder footerHolder = (FooterHolder) holder;
            Footer footer = (Footer) recyclerViewItem;
//            //set data
//            String returnedString = footer.getQuote().replace( "</br>", "\n" );
//            footerHolder.texViewQuote.setText(returnedString);
            footerHolder.texViewQuote.setText(footer.getQuote());
//            Glide.with(mContext).load(footer.getImageUrl()).into(footerHolder.imageViewFooter);

        } else if (holder instanceof FoodItemHolder) {//>>> item  ??????????????????????????????
            FoodItemHolder foodItemHolder = (FoodItemHolder) holder;
            final FoodItem foodItem = (FoodItem) recyclerViewItem;
            //set data
            Log.e("images>>",foodItem.getImage());

            if (foodItem.getImage().equals(null)||foodItem.getImage().isEmpty()){

               foodItemHolder.imageView.setImageResource(R.drawable.person);

//                Picasso.with(mContext)
//                        .placeholder(R.drawable.person)
//                        .load(R.drawable.person)
//                        .into(foodItemHolder.imageView);
            }else {
                try {
                    Picasso.with(mContext)
                            .load(foodItem.getImage())
                            .placeholder(R.drawable.person)
                            .into(foodItemHolder.imageView);
                }catch (Exception e){}

//                Picasso.with(mContext)
//                        .placeholder(R.drawable.person)
//                        .load(foodItem.getImage())
//                        .into(foodItemHolder.imageView);
            }

            try {
                if (foodItem.getSaved().trim() == "1"){
                    Log.e(">>>>>>>>>>Saved",foodItem.getSaved());
                    foodItemHolder.saved_Unsaved.setImageResource(R.drawable.ic_save);
                }else{
                    foodItemHolder.saved_Unsaved.setImageResource(R.mipmap.ic_unsaved);
                }
            }catch (Exception e){}

            foodItemHolder.namedoc.setText(foodItem.getNamedoc());
            foodItemHolder.spiseal.setText(foodItem.getSpecialist());
            foodItemHolder.ratingBar.setRating(foodItem.getRate());
            foodItemHolder.ratingBar.setIsIndicator(true);
            foodItemHolder.ratingBar.setActivated(false);
            foodItemHolder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

//                    Toast.makeText(mContext, ""+foodItem.getId(), Toast.LENGTH_SHORT).show();
                    SharedPrefManager_IdDoctor.getInstance(mContext).userLogin_id(
                            foodItem.getId());
                    Log.e("idDpctor_Adaptor",foodItem.getId());

                    Intent intent=new Intent(mContext, ProfileDoctor.class);
                    intent.putExtra("id_doctor",foodItem.getId());
                    mContext.startActivity(intent);
                }
            });

//            String returnedString = foodItem.getTextcomment().replace( "</br>", "\n \n" );
////
//            String returnedStringcolor =returnedString;
//
//            foodItemHolder.datecomment.setText(foodItem.getDate());
//            foodItemHolder.textcomment.setText(foodItem.getTextcomment());
//            foodItemHolder.useroremp.setText(foodItem.getDatecooment());

        }

    }

    @Override
    public int getItemViewType(int position) {
        //here we can set view type
        RecyclerViewItem recyclerViewItem = recyclerViewItems.get(position);
        //if its header then return header item
        if (recyclerViewItem instanceof Header) {
            return HEADER_ITEM;
        }
            //if its Footer then return Footer item
        else if (recyclerViewItem instanceof Footer)
            return FOOTER_ITEM;
        //if its FoodItem then return Food item
        else if (recyclerViewItem instanceof FoodItem)
            return FOOD_ITEM;
        else
            return super.getItemViewType(position);

    }

    @Override
    public int getItemCount() {
        return recyclerViewItems.size();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    //Food item holder  >>>>>>>>>>>>>>>>>>>>>>
    private class FoodItemHolder extends RecyclerView.ViewHolder {

        ImageView saved_Unsaved;
        CircleImageView imageView;
        TextView namedoc,spiseal;
        RatingBar ratingBar;
        CardView cardView;
        FoodItemHolder(View itemView) {
            super(itemView);

            saved_Unsaved = itemView.findViewById(R.id.image_save_unsave_recommend);
            imageView = itemView.findViewById(R.id.profile_image_body_home);
            cardView=itemView.findViewById(R.id.card_alldoctor);
            namedoc=itemView.findViewById(R.id.text_namedoc_body_home);
            spiseal=itemView.findViewById(R.id.text_spisial_body_home);
            ratingBar=itemView.findViewById(R.id.rating);
//            textViewIsHot = itemView.findViewById(R.id.textViewIsHot);
        }
    }
    //header holder >>>>>>>>>>>>>>>>>>>>>>>>>>>
    private class HeaderHolder extends RecyclerView.ViewHolder {
        TextView text_id, text_email;
        FlipperLayout flipperLayout;
         SliderLayout mDemoSlider;
         LinearLayout liner_header;
        HeaderHolder(View itemView) {
            super(itemView);
            mDemoSlider = (SliderLayout)itemView.findViewById(R.id.slider);
            liner_header=itemView.findViewById(R.id.liner_header);
//            text_id = itemView.findViewById(R.id.text_header_id);
//            Num_foldear =itemView.findViewById(R.id.bu_getdat_issu_hedarSingelqadea);
        }
    }
    //footer holder   >>>>>>>>>>>>>>>>>>>>>>>>>>>
    private class FooterHolder extends RecyclerView.ViewHolder {
        TextView texViewQuote;

        FooterHolder(View itemView) {
            super(itemView);
            texViewQuote = itemView.findViewById(R.id.texViewQuote);
        }
    }











//    private void getjson_baner() {
//
//        ConnectivityManager conMgr = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
//        if (conMgr.getActiveNetworkInfo() != null && conMgr.getActiveNetworkInfo().isAvailable() && conMgr.getActiveNetworkInfo().isConnected()) {
//            StringRequest stringRequest = new StringRequest(Request.Method.GET,
//                    Urls.baner ,
//                    new Response.Listener<String>() {
//                        @Override
//                        public void onResponse(String response) {
//                            if (response.length() > 0) {
//                                //progressDialog.dismiss();
//                                Gson gson = new Gson();
//                                DataBaneerJson dataCattgryJson;
//                                dataCattgryJson = gson.fromJson(response.toString(), DataBaneerJson.class);
//
//                                int lengt_for=dataCattgryJson.getResultData().getResultData().size();
//                                if (lengt_for > 0){
//
//                                    for (int x=0;x < lengt_for;x++){
//                                        urlbaner.add(dataCattgryJson.getResultData().getResultData().get(x).getBannerPath());
////                                        Databaners databaners=new Databaners(dataCattgryJson.getResultData().getResultData().get(x).getBannerPath());
////                                        Log.e("urlbaner",dataCattgryJson.getResultData().getResultData().get(x).getBannerPath());
//                                    }
//                                }
//                            } else {
//                                //progressDialog.dismiss();
//                                Log.e("test", "no");
//                            }
//                        }
//                    },
//                    new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//                            //progressDialog.dismiss();
//                            if (error instanceof TimeoutError || error instanceof NoConnectionError) {
//                                Toast.makeText(mContext, "Error Network Time Out", Toast.LENGTH_LONG).show();
//                            } else if (error instanceof AuthFailureError) {
////                                startActivity(new Intent(getActivity(),Log_In.class));
//                                //Toast.makeText(getApplicationContext(), "AuthFailureError", Toast.LENGTH_LONG).show();
//                                //TODO
//                            } else if (error instanceof ServerError) {
//                                Toast.makeText(mContext, "ServerError", Toast.LENGTH_LONG).show();
//                                //TODO
//                            } else if (error instanceof NetworkError) {
//                                Toast.makeText(mContext, "NetworkError", Toast.LENGTH_LONG).show();
//                                //TODO
//                            } else if (error instanceof ParseError) {
//                                Toast.makeText(mContext, "ParseError", Toast.LENGTH_LONG).show();
//                                //TODO
//                            }
//                        }
//                    }
//            );
//            MySingleton.getInstance(mContext).addToRequestQueue(stringRequest);
//        };
//    }





}
