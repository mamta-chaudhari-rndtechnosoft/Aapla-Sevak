package com.vinodmapari.aaplasevak.Activity;

import static com.vinodmapari.aaplasevak.ApiConfig.ApiHandler.getRetrofitInstance;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.appbar.MaterialToolbar;
import com.vinodmapari.aaplasevak.ApiConfig.ApiInterface;
import com.vinodmapari.aaplasevak.Model.AddMultipleSurveyBody;
import com.vinodmapari.aaplasevak.Model.AddSurveyResponseData;
import com.vinodmapari.aaplasevak.Model.SearchItem;
import com.vinodmapari.aaplasevak.Model.SurveyDetailItem;
import com.vinodmapari.aaplasevak.R;
import com.vinodmapari.aaplasevak.VoterSurveyAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddVoterSurveyFamilyActivity extends AppCompatActivity {

    ArrayList<String> selectedVoterId;
    RecyclerView rvVoterSurvey;
    VoterSurveyAdapter adapter;
    LottieAnimationView loader;
    MaterialToolbar toolbar;

    Button btnAddAll;
    private boolean isKeyboardVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_voter_survey_family);

        selectedVoterId = new ArrayList<>();

        Intent intent = getIntent();
        selectedVoterId = intent.getStringArrayListExtra("selectedVoterId");


        toolbar = findViewById(R.id.toolbar);
        loader = findViewById(R.id.loader);
        btnAddAll = findViewById(R.id.btnAddMultipleSurvey);
        rvVoterSurvey = findViewById(R.id.rvVoterSurvey);
        rvVoterSurvey.setLayoutManager(new LinearLayoutManager(this));
        //rvVoterSurvey.setLayoutManager(new GridLayoutManager(this, 1, RecyclerView.HORIZONTAL, false));

        adapter = new VoterSurveyAdapter(AddVoterSurveyFamilyActivity.this, selectedVoterId, rvVoterSurvey);
        rvVoterSurvey.setAdapter(adapter);

        btnAddAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddVoterSurveyFamilyActivity.this);
                alertDialogBuilder.setTitle("Are you sure want to add the data?");
                alertDialogBuilder.setMessage("Add Data");

                alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                        loader.setVisibility(View.VISIBLE);
                        ArrayList<SurveyDetailItem> surveyDataList = adapter.getSurveyDetailItems();
                        //Toast.makeText(AddVoterSurveyFamilyActivity.this, "Data: " + surveyDataList.toString(), Toast.LENGTH_SHORT).show();
                        Log.d("Api Response","Data in btn: " + surveyDataList.toString());
                        addMultipleSurveyList();

                    }
                });

                // Create and show the dialog
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

    }

    public void addMultipleSurveyList() {

        ApiInterface apiInterface = getRetrofitInstance().create(ApiInterface.class);

        //ArrayList<SurveyDetailItem> surveyDataList = adapter.collectSurveyData();
        ArrayList<SurveyDetailItem> surveyDataList = adapter.getSurveyDetailItems();

        AddMultipleSurveyBody addMultipleSurveyBody = new AddMultipleSurveyBody(surveyDataList);

        Call<AddSurveyResponseData> call = apiInterface.addMultipleSurvey(addMultipleSurveyBody);

        call.enqueue(new Callback<AddSurveyResponseData>() {
            @Override
            public void onResponse(Call<AddSurveyResponseData> call, Response<AddSurveyResponseData> response) {
                if (response.isSuccessful()) {

                    loader.setVisibility(View.GONE);
                    AddSurveyResponseData surveyResponseData = response.body();
                    String status = surveyResponseData.getStatus();
                    String message = surveyResponseData.getMessage();

                    Log.d("Api Response","Data: " + surveyDataList.toString());
                    Toast.makeText(AddVoterSurveyFamilyActivity.this, "status: " + status + "mes: " + message, Toast.LENGTH_SHORT).show();

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddVoterSurveyFamilyActivity.this);
                    alertDialogBuilder.setTitle("Add Voter");
                    alertDialogBuilder.setMessage("Voter added successfully!");

                    alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //dialog.dismiss();
                            finish();
                        }
                    });

                    // Create and show the dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();


                } else {
                    loader.setVisibility(View.GONE);
                    Toast.makeText(AddVoterSurveyFamilyActivity.this, "Response Error..!!", Toast.LENGTH_SHORT).show();
                    Log.e("Tag", "Response Error..");
                }
            }

            @Override
            public void onFailure(Call<AddSurveyResponseData> call, Throwable throwable) {
                loader.setVisibility(View.GONE);
                Log.e("Tag", "Error.." + throwable.getLocalizedMessage());
                Toast.makeText(AddVoterSurveyFamilyActivity.this, "Error..", Toast.LENGTH_SHORT).show();
            }
        });
    }
}