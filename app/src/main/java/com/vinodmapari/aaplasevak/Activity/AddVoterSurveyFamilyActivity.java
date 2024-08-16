package com.vinodmapari.aaplasevak.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vinodmapari.aaplasevak.R;
import com.vinodmapari.aaplasevak.VoterSurveyAdapter;

import java.util.ArrayList;

public class AddVoterSurveyFamilyActivity extends AppCompatActivity {

    ArrayList<String> selectedVoterId;
     RecyclerView rvVoterSurvey;
    VoterSurveyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_voter_survey_family);

        selectedVoterId = new ArrayList<>();

        Intent intent = getIntent();
        selectedVoterId =  intent.getStringArrayListExtra("selectedVoterId");

        rvVoterSurvey = findViewById(R.id.rvVoterSurvey);
        rvVoterSurvey.setLayoutManager(new LinearLayoutManager(this));

        adapter = new VoterSurveyAdapter(AddVoterSurveyFamilyActivity.this,selectedVoterId);
        rvVoterSurvey.setAdapter(adapter);

    }
}