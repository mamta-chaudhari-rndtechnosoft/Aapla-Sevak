package com.vinodmapari.aaplasevak.Activity;

import static com.vinodmapari.aaplasevak.ApiConfig.ApiHandler.getRetrofitInstance;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.button.MaterialButton;
import com.vinodmapari.aaplasevak.ApiConfig.ApiInterface;
import com.vinodmapari.aaplasevak.Model.WhatsAppNumList;
import com.vinodmapari.aaplasevak.Model.WhatsAppNumberResponseData;
import com.vinodmapari.aaplasevak.R;
import com.vinodmapari.aaplasevak.WhatsAppApiAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WhatsAppApiActivity extends AppCompatActivity {

    MaterialButton btnSend;
    RecyclerView rvWhatsAppApi;
    LottieAnimationView loader, empty_icon;
    ImageView iv;
    EditText etMessage;
    String colony, series, row, waterSupply;
    LinearLayoutManager mLayoutManager;
    ArrayList<WhatsAppNumList> whatsAppNumLists;
    WhatsAppApiAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whats_app_api);

        etMessage = findViewById(R.id.etMessage);
        btnSend = findViewById(R.id.btnSendWhatsAppApiMsg);
        rvWhatsAppApi = findViewById(R.id.rvWhatsAppApi);
        loader = findViewById(R.id.loader);
        empty_icon = findViewById(R.id.empty_icon);
        loader.setVisibility(View.GONE);
        empty_icon.setVisibility(View.GONE);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));

        toolbar.setTitle(Html.fromHtml("<b>" + "Contacts" + "</b>"));
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // from whatsApp activity
        Intent in = getIntent();
        // houseNo = in.getStringExtra("house_no");
        colony = in.getStringExtra("colony_id");
        series = in.getStringExtra("series_id");
        row = in.getStringExtra("row_id");
        waterSupply = in.getStringExtra("water_Supply_id");

        whatsAppNumLists = new ArrayList<>();

        mLayoutManager = new LinearLayoutManager(WhatsAppApiActivity.this);
        rvWhatsAppApi.setLayoutManager(mLayoutManager);


        getNumberList();


    }

    private void getNumberList() {

        ApiInterface apiInterface = getRetrofitInstance().create(ApiInterface.class);
        apiInterface.getWhatsAppContact(waterSupply, row, colony, series).enqueue(new Callback<WhatsAppNumberResponseData>() {
            @Override
            public void onResponse(Call<WhatsAppNumberResponseData> call, Response<WhatsAppNumberResponseData> response) {
                if (response.isSuccessful()) {
                    WhatsAppNumberResponseData responseData = response.body();
                    whatsAppNumLists = responseData.getWhatsAppNumList();
                    adapter = new WhatsAppApiAdapter(WhatsAppApiActivity.this, whatsAppNumLists);
                    rvWhatsAppApi.setAdapter(adapter);

                } else {
                    Toast.makeText(WhatsAppApiActivity.this, "Response Error..!!", Toast.LENGTH_SHORT).show();
                    Log.e("Tag", "Response Error..");
                }
            }

            @Override
            public void onFailure(Call<WhatsAppNumberResponseData> call, Throwable throwable) {
                Log.e("Tag", "Error.." + throwable.getLocalizedMessage());
                Toast.makeText(WhatsAppApiActivity.this, "Error..", Toast.LENGTH_SHORT).show();
            }
        });

    }
}