package com.vinodmapari.aaplasevak.Activity;

import static com.vinodmapari.aaplasevak.ApiConfig.ApiHandler.getRetrofitInstance;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.appbar.MaterialToolbar;
import com.vinodmapari.aaplasevak.ApiConfig.ApiInterface;
import com.vinodmapari.aaplasevak.Model.BanSurveyorResponseData;
import com.vinodmapari.aaplasevak.Model.DeleteMemberResponseData;
import com.vinodmapari.aaplasevak.Model.DeleteSurveyorResponseData;
import com.vinodmapari.aaplasevak.Model.SurveyDetailItem;
import com.vinodmapari.aaplasevak.Model.SurveyorItem;
import com.vinodmapari.aaplasevak.Model.SurveyorResponseData;
import com.vinodmapari.aaplasevak.R;
import com.vinodmapari.aaplasevak.SurveyorAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SurveyorMemberListActivity extends AppCompatActivity implements SurveyorAdapter.OnDeleteClickListener, SurveyorAdapter.OnToggleStatusListener {

    MaterialToolbar toolbar;
    RecyclerView rvSurveyor;
    ArrayList<SurveyorItem> surveyorList;
    SurveyorAdapter adapter;
    LottieAnimationView loader;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surveyor_member_list);

        toolbar = findViewById(R.id.toolbar);
        rvSurveyor = findViewById(R.id.rvSurveyor);
        loader = findViewById(R.id.loader);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshSurveyMember);


        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        surveyorList = new ArrayList<>();

        rvSurveyor.setLayoutManager(new LinearLayoutManager(SurveyorMemberListActivity.this));

        loader.setVisibility(View.VISIBLE);
        getSurveyorList();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                loader.setVisibility(View.VISIBLE);
                getSurveyorList();
                swipeRefreshLayout.setRefreshing(false);

            }
        });

    }

    public void getSurveyorList() {

        ApiInterface apiInterface = getRetrofitInstance().create(ApiInterface.class);

        Call<SurveyorResponseData> call = apiInterface.surveyorList();

        call.enqueue(new Callback<SurveyorResponseData>() {
            @Override
            public void onResponse(Call<SurveyorResponseData> call, Response<SurveyorResponseData> response) {
                if (response.isSuccessful()) {
                    loader.setVisibility(View.GONE);
                    SurveyorResponseData responseData = response.body();
                    surveyorList = responseData.getSurveyor();

                    adapter = new SurveyorAdapter(SurveyorMemberListActivity.this, surveyorList, SurveyorMemberListActivity.this, SurveyorMemberListActivity.this);

                    rvSurveyor.setAdapter(adapter);


                } else {
                    loader.setVisibility(View.GONE);
                    Toast.makeText(SurveyorMemberListActivity.this, "Response Error..!!", Toast.LENGTH_SHORT).show();
                    Log.e("Api Response", "Response Error..");
                }
            }

            @Override
            public void onFailure(Call<SurveyorResponseData> call, Throwable throwable) {
                loader.setVisibility(View.GONE);
                Log.e("Api Response", "Error.." + throwable.getLocalizedMessage());
                Toast.makeText(SurveyorMemberListActivity.this, "Error.." + throwable.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void deleteSurveyor(String userId) {
        ApiInterface apiInterface = getRetrofitInstance().create(ApiInterface.class);
        Call<DeleteMemberResponseData> call = apiInterface.deleteSurveyor(userId);

        call.enqueue(new Callback<DeleteMemberResponseData>() {
            @Override
            public void onResponse(Call<DeleteMemberResponseData> call, Response<DeleteMemberResponseData> response) {
                if (response.isSuccessful()) {

                    loader.setVisibility(View.GONE);
                    DeleteMemberResponseData responseData = response.body();
                    String error = responseData.getError();
                    String message = responseData.getMessage();

                    Toast.makeText(SurveyorMemberListActivity.this, message, Toast.LENGTH_SHORT).show();


                } else {
                    loader.setVisibility(View.GONE);
                    Toast.makeText(SurveyorMemberListActivity.this, "Response Error..!!", Toast.LENGTH_SHORT).show();
                    Log.e("Api Response", "Response Error..");
                }
            }

            @Override
            public void onFailure(Call<DeleteMemberResponseData> call, Throwable throwable) {
                loader.setVisibility(View.GONE);
                Log.e("Api Response", "Error.." + throwable.getLocalizedMessage());
                Toast.makeText(SurveyorMemberListActivity.this, "Error.." + throwable.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void banSurveyor(String userId, String status) {

        ApiInterface apiInterface = getRetrofitInstance().create(ApiInterface.class);
        Call<BanSurveyorResponseData> call = apiInterface.banSurveyor(userId, status);

        call.enqueue(new Callback<BanSurveyorResponseData>() {
            @Override
            public void onResponse(Call<BanSurveyorResponseData> call, Response<BanSurveyorResponseData> response) {
                if (response.isSuccessful()) {
                    loader.setVisibility(View.GONE);
                    BanSurveyorResponseData responseData = response.body();
                    String error = responseData.getError();
                    String message = responseData.getMessage();
                    Toast.makeText(SurveyorMemberListActivity.this, message, Toast.LENGTH_SHORT).show();
                } else {
                    loader.setVisibility(View.GONE);
                    Toast.makeText(SurveyorMemberListActivity.this, "Response Error..!!", Toast.LENGTH_SHORT).show();
                    Log.e("Api Response", "Response Error..");
                }
            }

            @Override
            public void onFailure(Call<BanSurveyorResponseData> call, Throwable throwable) {
                loader.setVisibility(View.GONE);
                Log.e("Api Response", "Error.." + throwable.getLocalizedMessage());
                Toast.makeText(SurveyorMemberListActivity.this, "Error.." + throwable.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDeleteClick(String userId) {

        loader.setVisibility(View.VISIBLE);
        deleteSurveyor(userId);
    }


    @Override
    public void onStatusToggle(String userId, String newStatus) {
        loader.setVisibility(View.VISIBLE);
        banSurveyor(userId, newStatus);
    }


}
