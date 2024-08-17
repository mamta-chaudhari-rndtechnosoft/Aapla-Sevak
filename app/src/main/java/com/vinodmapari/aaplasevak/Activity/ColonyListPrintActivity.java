package com.vinodmapari.aaplasevak.Activity;

import static com.vinodmapari.aaplasevak.ApiConfig.ApiHandler.getRetrofitInstance;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.vinodmapari.aaplasevak.ApiConfig.ApiInterface;
import com.vinodmapari.aaplasevak.ColonyListAdapter;
import com.vinodmapari.aaplasevak.Model.ColonyItem;
import com.vinodmapari.aaplasevak.Model.ColonyListResponseData;
import com.vinodmapari.aaplasevak.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ColonyListPrintActivity extends AppCompatActivity {

    MaterialToolbar toolbar;
    RecyclerView rvColonyList;
    ColonyListAdapter adapter;
    ArrayList<ColonyItem> colonyItemArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colony_list_print);

        toolbar = findViewById(R.id.toolbar);
        rvColonyList = findViewById(R.id.rvColonyList);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        colonyItemArrayList = new ArrayList<>();
        rvColonyList.setLayoutManager(new LinearLayoutManager(ColonyListPrintActivity.this));

        getAllColonyList();

    }

    public void getAllColonyList() {
        ApiInterface apiInterface = getRetrofitInstance().create(ApiInterface.class);

        Call<ColonyListResponseData> call = apiInterface.colonyList();
        call.enqueue(new Callback<ColonyListResponseData>() {
            @Override
            public void onResponse(Call<ColonyListResponseData> call, Response<ColonyListResponseData> response) {
                if (response.isSuccessful()) {

                    ColonyListResponseData responseData = response.body();

                    colonyItemArrayList = responseData.getColonies();

                    adapter = new ColonyListAdapter(ColonyListPrintActivity.this,colonyItemArrayList);
                    rvColonyList.setAdapter(adapter);


                } else {
                    Toast.makeText(ColonyListPrintActivity.this, "Response Error..!!", Toast.LENGTH_SHORT).show();
                    Log.e("Api Response", "Response Error..");
                }

            }

            @Override
            public void onFailure(Call<ColonyListResponseData> call, Throwable throwable) {
                Log.e("Api Response", "Error.." + throwable.getLocalizedMessage());
                Toast.makeText(ColonyListPrintActivity.this, "Error.." + throwable.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}