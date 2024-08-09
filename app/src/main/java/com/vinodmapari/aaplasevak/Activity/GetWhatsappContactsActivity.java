package com.vinodmapari.aaplasevak.Activity;

import static android.view.View.GONE;

import static com.vinodmapari.aaplasevak.ApiConfig.ApiHandler.getRetrofitInstance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.appbar.MaterialToolbar;
import com.vinodmapari.aaplasevak.ApiConfig.ApiInterface;
import com.vinodmapari.aaplasevak.Model.GetContact;
import com.vinodmapari.aaplasevak.Model.WhatsAppNumList;
import com.vinodmapari.aaplasevak.Model.WhatsAppNumberResponseData;
import com.vinodmapari.aaplasevak.Model.Wp_Img;
import com.vinodmapari.aaplasevak.R;
import com.vinodmapari.aaplasevak.WhatsAppApiAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class GetWhatsappContactsActivity extends AppCompatActivity {



    LottieAnimationView loader, empty_icon;
    RecyclerView rvWhatsAppApi;
    WhatsAppApiAdapter adapter;
    LinearLayoutManager mLayoutManager;
    ArrayList<WhatsAppNumList> whatsAppNumLists;
    String  row, series, watersupply, colony, image, constituency, city, zone, ward;
    EditText etMessage;
    MaterialToolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_contacts);

        toolbar = findViewById(R.id.toolbar);
        etMessage = findViewById(R.id.etMessage);
        rvWhatsAppApi = findViewById(R.id.rv_contacts);
        loader = findViewById(R.id.loader);

        empty_icon = findViewById(R.id.empty_icon);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //getContacts = new ArrayList<>();
        whatsAppNumLists = new ArrayList<>();
        mLayoutManager = new LinearLayoutManager(GetWhatsappContactsActivity.this);
        rvWhatsAppApi.setLayoutManager(mLayoutManager);



        Intent in = getIntent();
        // houseNo = in.getStringExtra("house_no");
        colony = in.getStringExtra("colony_id");
        series = in.getStringExtra("series_id");
        row = in.getStringExtra("row_id");
        watersupply = in.getStringExtra("water_Supply_id");
        constituency = in.getStringExtra("constituency_id");
        city = in.getStringExtra("city_id");
        zone = in.getStringExtra("zone_id");
        ward = in.getStringExtra("ward_id");

        colony = (colony != null) ? colony : "0";
        series = (series != null) ? series : "0";
        row = (row != null) ? row : "0";
        watersupply = (watersupply != null) ? watersupply : "0";
        constituency = (constituency != null) ? constituency : "0";
        city = (city != null) ? city : "0";
        zone = (zone != null) ? zone : "0";
        ward = (ward != null) ? ward : "0";



        //getContactList();
        loader.setVisibility(View.VISIBLE);
        empty_icon.setVisibility(GONE);
        rvWhatsAppApi.setVisibility(GONE);
        getNumberList();


    }

    private void getNumberList() {

        ApiInterface apiInterface = getRetrofitInstance().create(ApiInterface.class);
        apiInterface.getWhatsAppContact(series, colony, row, watersupply, constituency, city, zone, ward).enqueue(new Callback<WhatsAppNumberResponseData>() {
            @Override
            public void onResponse(Call<WhatsAppNumberResponseData> call, retrofit2.Response<WhatsAppNumberResponseData> response) {
                if (response.isSuccessful()) {

                    loader.setVisibility(GONE);
                    rvWhatsAppApi.setVisibility(View.VISIBLE);
                    empty_icon.setVisibility(GONE);

                    WhatsAppNumberResponseData responseData = response.body();
                    whatsAppNumLists = responseData.getWhatsAppNumList();
                    adapter = new WhatsAppApiAdapter(GetWhatsappContactsActivity.this, whatsAppNumLists, etMessage);
                    rvWhatsAppApi.setAdapter(adapter);

                } else {
                    loader.setVisibility(GONE);
                    rvWhatsAppApi.setVisibility(GONE);
                    empty_icon.setVisibility(View.VISIBLE);

                    Toast.makeText(GetWhatsappContactsActivity.this, "Response Error..!!", Toast.LENGTH_SHORT).show();
                    Log.e("Tag", "Response Error..");
                }
            }

            @Override
            public void onFailure(Call<WhatsAppNumberResponseData> call, Throwable throwable) {
                loader.setVisibility(GONE);
                rvWhatsAppApi.setVisibility(GONE);
                empty_icon.setVisibility(View.VISIBLE);
                Log.e("Tag", "Error.." + throwable.getLocalizedMessage());
                Toast.makeText(GetWhatsappContactsActivity.this, "Error..", Toast.LENGTH_SHORT).show();
            }
        });
    }







    @Override
    protected void onRestart() {
        super.onRestart();
    }
}