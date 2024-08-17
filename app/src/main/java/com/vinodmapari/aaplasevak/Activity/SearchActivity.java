package com.vinodmapari.aaplasevak.Activity;

import static com.vinodmapari.aaplasevak.ApiConfig.ApiHandler.getRetrofitInstance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.appbar.MaterialToolbar;
import com.vinodmapari.aaplasevak.ApiConfig.ApiInterface;
import com.vinodmapari.aaplasevak.Model.Constants;
import com.vinodmapari.aaplasevak.Model.SearchBody;
import com.vinodmapari.aaplasevak.Model.SearchList;
import com.vinodmapari.aaplasevak.Model.SearchListBody;
import com.vinodmapari.aaplasevak.Model.SearchListItem;
import com.vinodmapari.aaplasevak.Model.SearchListResponseData;
import com.vinodmapari.aaplasevak.Model.SearchResponse;
import com.vinodmapari.aaplasevak.Model.SurveyCountItem;
import com.vinodmapari.aaplasevak.Model.SurveyCountResponse;
import com.vinodmapari.aaplasevak.R;
import com.vinodmapari.aaplasevak.SearchAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    //ImageView search;
    SearchAdapter searchAdapter;
    RecyclerView rv;
    TextView textView_empty, tvSurveyCount;
    LottieAnimationView loader, empty_icon;
    List<SearchListItem> searchLists;
    EditText etName, etLname, etMname, etVoterId, etAdharCard, etFullName, etHouseNumber, etMobileNumber, etMobileNumberTwo;
    String name, lname, mname, voterId, adharcard, fullName, houseNo, mobileNumber, mobileNumberTwo;
    Button btn;
    MaterialToolbar toolbar;
    LinearLayoutManager mLayoutManager;
    //private int positionSelect;
    //private int pageNo = 0;
    //private int pageLimit = 20;
    //private Boolean loadingMore = false;
    //private Boolean viewMore = false;
    //int pastVisiblesItems = 0, visibleItemCount = 0, totalItemCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //search = findViewById(R.id.iv_search_icon);
        toolbar =  findViewById(R.id.toolbar);
        rv = findViewById(R.id.rv_search);
        etAdharCard = findViewById(R.id.adharCard);
        etVoterId = findViewById(R.id.et_voterID);
        etFullName = findViewById(R.id.et_fullName);
        etHouseNumber = findViewById(R.id.et_house_no);
        etMobileNumber = findViewById(R.id.et_number);
        etMobileNumberTwo = findViewById(R.id.et_number_two);


        tvSurveyCount = findViewById(R.id.tvCount);
        textView_empty = findViewById(R.id.tv_empty_search);
        textView_empty.setVisibility(View.VISIBLE);
        textView_empty.setText(R.string.type_search);
        loader = findViewById(R.id.loader);
        empty_icon = findViewById(R.id.empty_icon);
        empty_icon.setVisibility(View.GONE);


      /*
         etName = findViewById(R.id.et_name);
         etMname = findViewById(R.id.et_mname);
         etLname = findViewById(R.id.et_lname);
      */

        btn = findViewById(R.id.btnview);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchActivity.this, PrintOptionActivity.class));
                Toast.makeText(SearchActivity.this, "..........Test...........", Toast.LENGTH_SHORT).show();
            }
        });
        //pageNo = 0;

         toolbar.setNavigationOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 finish();
             }
         });

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);

        searchLists = new ArrayList<>();

        mLayoutManager = new LinearLayoutManager(SearchActivity.this);
        rv.setLayoutManager(mLayoutManager);

        searchAdapter = new SearchAdapter(this, searchLists);
        rv.setAdapter(searchAdapter);

        fullName = "";
        voterId = "";
        adharcard = "";
        houseNo = "";
        mobileNumber = "";
        mobileNumberTwo = "";

        loader.setVisibility(View.VISIBLE);
        getSearchList();

        surveyCount();
        addTextWatchers();

        /*search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               *//*
                name = etName.getText().toString();
                mname = etMname.getText().toString();
                lname = etLname.getText().toString();
                *//*

                fullName = etFullName.getText().toString();
                voterId = etVoterId.getText().toString();
                adharcard = etAdharCard.getText().toString();
                houseNo = etHouseNumber.getText().toString();
                mobileNumber = etMobileNumber.getText().toString();
                mobileNumberTwo = etMobileNumberTwo.getText().toString();
                loader.setVisibility(View.VISIBLE);
                getSearchList();

            }
        });*/

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /* name = etName.getText().toString();
                mname = etMname.getText().toString();
                lname = etLname.getText().toString();*/
                fullName = etFullName.getText().toString();
                voterId = etVoterId.getText().toString();
                adharcard = etAdharCard.getText().toString();
                houseNo = etHouseNumber.getText().toString();
                mobileNumber = etMobileNumber.getText().toString();
                mobileNumberTwo = etMobileNumberTwo.getText().toString();
                //pageNo = pageNo + 1;
                loader.setVisibility(View.VISIBLE);
                getSearchList();

            }
        });
    }


    private void addTextWatchers() {
       /* etName.addTextChangedListener(textWatcher);
        etMname.addTextChangedListener(textWatcher);
        etLname.addTextChangedListener(textWatcher);*/
        etFullName.addTextChangedListener(textWatcher);
        etVoterId.addTextChangedListener(textWatcher);
        etAdharCard.addTextChangedListener(textWatcher);
        etHouseNumber.addTextChangedListener(textWatcher);
        etMobileNumber.addTextChangedListener(textWatcher);
        etMobileNumberTwo.addTextChangedListener(textWatcher);
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            /*
            name = etName.getText().toString();
            mname = etMname.getText().toString();
            lname = etLname.getText().toString();
            */

            fullName = etFullName.getText().toString();
            voterId = etVoterId.getText().toString();
            adharcard = etAdharCard.getText().toString();
            houseNo = etHouseNumber.getText().toString();
            mobileNumber = etMobileNumber.getText().toString();
            mobileNumberTwo = etMobileNumberTwo.getText().toString();
            //pageNo = 0;
            //Reset to the first page for new search
            loader.setVisibility(View.VISIBLE);
            getSearchList();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };


    private void getSearchList() {

        ApiInterface apiInterface = getRetrofitInstance().create(ApiInterface.class);

        SearchListBody searchListBody = new SearchListBody(fullName, voterId, adharcard, houseNo, mobileNumber);

        Call<SearchListResponseData> call = apiInterface.searchList(searchListBody);

        call.enqueue(new Callback<SearchListResponseData>() {
            @Override
            public void onResponse(Call<SearchListResponseData> call, Response<SearchListResponseData> response) {
                if (response.isSuccessful()) {

                    SearchListResponseData searchListResponseData = response.body();
                    List<SearchListItem> list = searchListResponseData.getSearchList();
                    textView_empty.setVisibility(View.GONE);
                    searchLists.clear();
                    searchLists.addAll(list);
                    searchAdapter.notifyDataSetChanged();
                    loader.setVisibility(View.GONE);

                } else {
                    loader.setVisibility(View.GONE);
                    Toast.makeText(SearchActivity.this, "Response Error..!!", Toast.LENGTH_SHORT).show();
                    Log.e("Api Response", "Response Error..");
                }
            }

            @Override
            public void onFailure(Call<SearchListResponseData> call, Throwable throwable) {
                loader.setVisibility(View.GONE);
                Log.e("Api Response", "Error.." + throwable.getLocalizedMessage());
                Toast.makeText(SearchActivity.this, "Error.." + throwable.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void surveyCount() {
        ApiInterface apiInterface = getRetrofitInstance().create(ApiInterface.class);
        Call<SurveyCountResponse> call = apiInterface.surveyCount();

        call.enqueue(new Callback<SurveyCountResponse>() {
            @Override

            public void onResponse(Call<SurveyCountResponse> call, Response<SurveyCountResponse> response) {
                if (response.isSuccessful()) {
                    SurveyCountResponse surveyCountResponse = response.body();
                    List<SurveyCountItem> items = surveyCountResponse.getSurveyCountItems();

                    int count = items.get(0).getSurveyCount();
                    tvSurveyCount.setText(String.valueOf(count));
                    toolbar.setTitle("Search From " + count);
                    //Toast.makeText(SearchActivity.this, "Count: " + count, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SearchActivity.this, "Response Error..!!", Toast.LENGTH_SHORT).show();
                    Log.e("Api Response", "Response Error..");
                }
            }

            @Override
            public void onFailure(Call<SurveyCountResponse> call, Throwable throwable) {
                Log.e("Api Response", "Error.." + throwable.getLocalizedMessage());
                Toast.makeText(SearchActivity.this, "Error.." + throwable.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    protected void onResume() {
        //pageNo = 0;
        //Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
        loader.setVisibility(View.VISIBLE);
        getSearchList();
        super.onResume();
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
    }
}
