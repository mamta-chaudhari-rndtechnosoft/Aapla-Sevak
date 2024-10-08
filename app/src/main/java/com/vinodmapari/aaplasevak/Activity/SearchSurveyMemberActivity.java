package com.vinodmapari.aaplasevak.Activity;

import static com.vinodmapari.aaplasevak.ApiConfig.ApiHandler.getRetrofitInstance;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
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
import com.vinodmapari.aaplasevak.Model.SurveyCountItem;
import com.vinodmapari.aaplasevak.Model.SurveyCountResponse;
import com.vinodmapari.aaplasevak.Model.VoterCountItem;
import com.vinodmapari.aaplasevak.Model.VoterCountResponse;
import com.vinodmapari.aaplasevak.R;
import com.vinodmapari.aaplasevak.SearchSurveyMemberAdapter;
import com.vinodmapari.aaplasevak.VoterSearchItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchSurveyMemberActivity extends AppCompatActivity implements SearchSurveyMemberAdapter.OnSelectionChangedListener {


    EditText etSearchFullName;
    RecyclerView rvSearch;
    SearchSurveyMemberAdapter adapter;
    String fullName;
    MaterialToolbar toolbar;
    ArrayList<SearchItem> searchItemsList;
    private ArrayList<String> selectedVoterId;
    LinearLayout layoutNoData;
    int selectedItemCount;
    Button btnCount, btnNext;
    TextView tvVoterCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_survey_member);

        rvSearch = findViewById(R.id.rvSearch);
        etSearchFullName = findViewById(R.id.etSearchFullName);
        layoutNoData = findViewById(R.id.layoutNoDataSearch);
        toolbar = findViewById(R.id.toolbarSearch);
        tvVoterCount = findViewById(R.id.tvVoterCount);
        //btnCount = findViewById(R.id.btnCount);
        btnNext = findViewById(R.id.btnNext);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        rvSearch.setLayoutManager(new LinearLayoutManager(this));

        searchItemsList = new ArrayList<>();
        selectedVoterId = new ArrayList<>();

        fullName = etSearchFullName.getText().toString();

        adapter = new SearchSurveyMemberAdapter(SearchSurveyMemberActivity.this, searchItemsList, SearchSurveyMemberActivity.this);
        rvSearch.setAdapter(adapter);

      /*  btnNext.setOnClickListener(v -> {
            Intent intent = new Intent(SearchSurveyMemberActivity.this, UserSurveyActivity.class);
            intent.putExtra("selectedItems", searchItemsList);
            intent.putExtra("selectedItemCount",selectedItemCount);
            startActivity(intent);
        });*/

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // from here i need to send the selected product names in another class

                selectedVoterId = adapter.getSelectedVoterId();

                // Start the Weight class
                if (selectedVoterId.isEmpty()) {
                    Toast.makeText(SearchSurveyMemberActivity.this, "Please Select Voter First", Toast.LENGTH_SHORT).show();
                } else {
                    //Toast.makeText(SearchSurveyMemberActivity.this, "Dat: " + selectedVoterId.toString(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SearchSurveyMemberActivity.this, AddVoterSurveyFamilyActivity.class);
                    intent.putStringArrayListExtra("selectedVoterId", selectedVoterId);
                    startActivity(intent);
                }

            }
        });

        getVoterSearchList();
        voterCount();

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

    public void voterCount() {
        ApiInterface apiInterface = getRetrofitInstance().create(ApiInterface.class);

        Call<VoterCountResponse> call = apiInterface.voterCount();

        call.enqueue(new Callback<VoterCountResponse>() {
            @Override
            public void onResponse(Call<VoterCountResponse> call, Response<VoterCountResponse> response) {
                if (response.isSuccessful()) {
                    VoterCountResponse voterCountResponse = response.body();
                    List<VoterCountItem> items = voterCountResponse.getVoterCountItems();

                    String count = items.get(0).getVoterCount();
                    tvVoterCount.setText("Voters " + count);
                    //btnCount.setText("Voters " + count);
                } else {
                    Toast.makeText(SearchSurveyMemberActivity.this, "Response Error..!!", Toast.LENGTH_SHORT).show();
                    Log.e("Api Response", "Response Error..");
                }
            }

            @Override
            public void onFailure(Call<VoterCountResponse> call, Throwable throwable) {
                Log.e("Api Response", "Error.." + throwable.getLocalizedMessage());
                Toast.makeText(SearchSurveyMemberActivity.this, "Error.." + throwable.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



    /* @Override
       public void onSelectionChanged(boolean hasSelection, ArrayList<SearchItem> selectedItems, int selectedItemCount) {
        this.searchItemsList = selectedItems;
        btnNext.setVisibility(hasSelection ? View.VISIBLE : View.GONE);
        this.selectedItemCount = selectedItemCount;
    }*/

    @Override
    public void onSelectionChanged(boolean hasSelection) {
        btnNext.setVisibility(hasSelection ? View.VISIBLE : View.GONE);
    }
}