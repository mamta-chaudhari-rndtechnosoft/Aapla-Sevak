package com.vinodmapari.aaplasevak.Activity;

import static com.vinodmapari.aaplasevak.ApiConfig.ApiHandler.getRetrofitInstance;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
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
import com.vinodmapari.aaplasevak.Model.HouseResponse;
import com.vinodmapari.aaplasevak.Model.SearchBody;
import com.vinodmapari.aaplasevak.Model.SearchItem;
import com.vinodmapari.aaplasevak.Model.SearchResponse;
import com.vinodmapari.aaplasevak.R;
import com.vinodmapari.aaplasevak.SearchSurveyMemberAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchSurveyMemberActivity extends AppCompatActivity {


    EditText etSearchFullName;
    RecyclerView rvSearch;
    SearchSurveyMemberAdapter adapter;
    String fullName;
    MaterialToolbar toolbar;
    ArrayList<SearchItem> searchItemsList;
    LinearLayout layoutNoData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_survey_member);

        rvSearch = findViewById(R.id.rvSearch);
        etSearchFullName = findViewById(R.id.etSearchFullName);
        layoutNoData = findViewById(R.id.layoutNoDataSearch);
        toolbar = findViewById(R.id.toolbarSearch);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        rvSearch.setLayoutManager(new LinearLayoutManager(this));

        searchItemsList = new ArrayList<>();

        fullName = etSearchFullName.getText().toString();

        adapter = new SearchSurveyMemberAdapter(SearchSurveyMemberActivity.this,searchItemsList);
        rvSearch.setAdapter(adapter);


        getVoterSearchList();

        etSearchFullName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                fullName = s.toString();
                getVoterSearchList();
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Do nothing
            }
        });
    }

    private void getVoterSearchList() {

        ApiInterface apiInterface = getRetrofitInstance().create(ApiInterface.class);

        SearchBody searchBody = new SearchBody(fullName);

        Call<SearchResponse> call = apiInterface.getVoterSearchList(searchBody);

        call.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                if (response.isSuccessful()) {
                    SearchResponse searchVoterListResponse = response.body();
                    ArrayList<SearchItem> voterSearchList = searchVoterListResponse.getSEARCH();
                     //searchItemsList = searchVoterListResponse.getSEARCH();
                     searchItemsList.clear();
                     searchItemsList.addAll(voterSearchList);
                     adapter.notifyDataSetChanged();

                    if (voterSearchList.isEmpty()) {
                        rvSearch.setVisibility(View.GONE);
                        layoutNoData.setVisibility(View.VISIBLE);
                    } else {
                        rvSearch.setVisibility(View.VISIBLE);
                        layoutNoData.setVisibility(View.GONE);
                    }

                } else {
                    Toast.makeText(SearchSurveyMemberActivity.this, "Response Error..!!", Toast.LENGTH_SHORT).show();
                    Log.e("Tag", "Response Error..");
                }

            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable throwable) {
                Log.e("Tag", "Error.." + throwable.getLocalizedMessage());
                Toast.makeText(SearchSurveyMemberActivity.this, "Error.." + throwable.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}