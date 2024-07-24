package com.vinodmapari.aaplasevak.Activity;

import static com.vinodmapari.aaplasevak.ApiConfig.ApiHandler.getRetrofitInstance;
import static com.vinodmapari.aaplasevak.Model.Constants.series_name;
import static com.vinodmapari.aaplasevak.Model.Constants.status_name;
import static com.vinodmapari.aaplasevak.Model.Constants.water_supply_slots;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;
import com.vinodmapari.aaplasevak.ApiConfig.ApiInterface;
import com.vinodmapari.aaplasevak.CustomAdapter;
import com.vinodmapari.aaplasevak.Model.CasteItem;
import com.vinodmapari.aaplasevak.Model.CasteResponse;
import com.vinodmapari.aaplasevak.Model.CityVillageItem;
import com.vinodmapari.aaplasevak.Model.CityVillageResponse;
import com.vinodmapari.aaplasevak.Model.Colony;
import com.vinodmapari.aaplasevak.Model.ColonyItem;
import com.vinodmapari.aaplasevak.Model.ColonyResponse;
import com.vinodmapari.aaplasevak.Model.Constants;
import com.vinodmapari.aaplasevak.Model.ConstituencyItem;
import com.vinodmapari.aaplasevak.Model.ConstituencyResponse;
import com.vinodmapari.aaplasevak.Model.EditFamilyMemberBody;
import com.vinodmapari.aaplasevak.Model.EditMemberRespponseData;
import com.vinodmapari.aaplasevak.Model.Method;
import com.vinodmapari.aaplasevak.Model.PrabhagWardItem;
import com.vinodmapari.aaplasevak.Model.PrabhagWardResponse;
import com.vinodmapari.aaplasevak.Model.QualificationItem;
import com.vinodmapari.aaplasevak.Model.QualificationsResponse;
import com.vinodmapari.aaplasevak.Model.Row;
import com.vinodmapari.aaplasevak.Model.RowItem;
import com.vinodmapari.aaplasevak.Model.RowResponse;
import com.vinodmapari.aaplasevak.Model.Series;
import com.vinodmapari.aaplasevak.Model.SeriesItem;
import com.vinodmapari.aaplasevak.Model.SeriesResponse;
import com.vinodmapari.aaplasevak.Model.SharedPref;
import com.vinodmapari.aaplasevak.Model.StatusItem;
import com.vinodmapari.aaplasevak.Model.StatusResponse;
import com.vinodmapari.aaplasevak.Model.SurveyList;
import com.vinodmapari.aaplasevak.Model.UserDetail;
import com.vinodmapari.aaplasevak.Model.UserDetailResponse;
import com.vinodmapari.aaplasevak.Model.WaterSupplyItem;
import com.vinodmapari.aaplasevak.Model.WaterSupplyResponse;
import com.vinodmapari.aaplasevak.Model.ZoneItem;
import com.vinodmapari.aaplasevak.Model.ZoneResponse;
import com.vinodmapari.aaplasevak.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateMemberActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    ArrayList<SurveyList> surveyLists;
    ArrayList<Colony> colonies;
    CustomAdapter adapter;
    int selected_series, selected_status, selected_colony, selected_row, selected_water_supply;
    String series_id, status_id, colony_id, row_id, water_supply_id, constituency_id, city_id, zone_id, ward_id, qualification_id, caste_id;
    Button btnSubmit;
    long selected_series_id, selected_colony_id;
    String gender;
    RadioButton radioButton1, radioButton2, radioButton3;
    RadioGroup radioGroup;
    TextView tv1, tv2;
    SwipeRefreshLayout swipe_refresh;
    SearchableSpinner spinner_series, spinner_status, spinner_colony, spinner_row,
            spinner_water_Supply, spinner_constituency, spinner_zone, spinner_ward,
            spinner_city, spinner_qualification, spinner_caste;
    EditText etHouseNumber, etVotingCenter, etDob, etName, etMiddleName, etSurname, etMob1, etMob2,
            etVoterId, etAadharCard, etBooth, etSerial,
            etApartment, etFlateNumber;
    private String colonyName;
    int id;
    String colony, row, waterSupply, series, status;
    //Field Entry
    String HouseNo, Name, MiddleName, Surname, VotingCenter, BoothNo, SeriesId, Gender,
            Mobile1, Mobile2, Dob, Qualification, Caste, Relation, Event, VoterId, AadharCard,
            constituency, zone, cityVillage, prabhagWard, apartment, flateNo, StatusId;

    String ColonyId, RowId, WaterSupplyId, MemberId, VotingSrNo;
    LottieAnimationView loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_member);

        //from edit family adapter
        id = getIntent().getIntExtra("id", 0);
        //Toast.makeText(this, "Id: " + id, Toast.LENGTH_SHORT).show();

        //Edittext
        etHouseNumber = findViewById(R.id.house_number);
        etVotingCenter = findViewById(R.id.voting_center);
        etDob = findViewById(R.id.dob);
        etName = findViewById(R.id.name);
        etMiddleName = findViewById(R.id.middle_name);
        etSurname = findViewById(R.id.surname);
        etMob1 = findViewById(R.id.mobile1);
        etMob2 = findViewById(R.id.mobile2);
        //etQualification = findViewById(R.id.qualification);

        //etRelation = findViewById(R.id.relation);
        //etEvent = findViewById(R.id.event);
        etVoterId = findViewById(R.id.voterID);
        etAadharCard = findViewById(R.id.adharCard);
        etBooth = findViewById(R.id.etBoothNo);
        etSerial = findViewById(R.id.etSerialNo);
        etFlateNumber = findViewById(R.id.etFlatNo);
        etApartment = findViewById(R.id.etApartment);


        //radio
        radioGroup = findViewById(R.id.radioGroup);
        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);


        //Spinner
        spinner_series = findViewById(R.id.spinnerSeries);
        spinner_colony = findViewById(R.id.spinner_colony);
        spinner_row = findViewById(R.id.spinner_row);
        spinner_status = findViewById(R.id.spinner_status);
        spinner_water_Supply = findViewById(R.id.spinner_water_supply);

        spinner_constituency = findViewById(R.id.spinnerConstituency);
        spinner_city = findViewById(R.id.spinnerCity_Village);
        spinner_zone = findViewById(R.id.spinnerZone);
        spinner_ward = findViewById(R.id.spinnerPrabhag_Ward);
        spinner_qualification = findViewById(R.id.spinner_qualification);
        spinner_caste = findViewById(R.id.spinner_caste);


        //loader
        loader = findViewById(R.id.loaderUpdate);

        //btn
        btnSubmit = findViewById(R.id.btn_submit_update);


        //Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));

        toolbar.setTitle(Html.fromHtml("<b>" + "Edit Details" + "</b>"));
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        surveyLists = new ArrayList<>();
        colonies = new ArrayList<>();

        adapter = new CustomAdapter(UpdateMemberActivity.this, colonies);

        spinner_series.setTitle("Select Series");
        spinner_status.setTitle("Select Status");
        spinner_colony.setTitle("Select Colony");
        spinner_row.setTitle("Select Row");
        spinner_water_Supply.setTitle("Select Water Supply Slot");

        spinner_constituency.setTitle("Select Constituency");
        spinner_zone.setTitle("Select Zone");
        spinner_ward.setTitle("Select Prabhag/Ward");
        spinner_city.setTitle("Select City/Village");

        swipe_refresh = findViewById(R.id.swipe_refresh);
        swipe_refresh.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.colorWhite));
        int col = getResources().getColor(R.color.colorAccent);
        swipe_refresh.setColorSchemeColors(col, col, col);
        swipe_refresh.setOnRefreshListener(UpdateMemberActivity.this);


        //getSeriesList();


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup rg, int checkedId) {
                for (int i = 0; i < rg.getChildCount(); i++) {
                    RadioButton btn = (RadioButton) rg.getChildAt(i);
                    if (btn.getId() == checkedId) {
                        gender = btn.getText().toString();
                        // do something with text
                        // Toast.makeText(UserSurveyActivity.this, ""+gender, Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            }
        });


        loader.setVisibility(View.VISIBLE);
        getUserDetail();


        fetchConstituencies();
        fetchZones();
        fetchPrabhagWards();
        fetchCityVillages();
        fetchQualification();
        fetchCaste();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HouseNo = etHouseNumber.getText().toString();
                Name = etName.getText().toString();
                MiddleName = etMiddleName.getText().toString();
                Surname = etSurname.getText().toString();
                VotingCenter = etVotingCenter.getText().toString();
                BoothNo = etBooth.getText().toString();
                VotingSrNo = etSerial.getText().toString();
                Gender = gender;
                Mobile1 = etMob1.getText().toString();
                Mobile2 = etMob2.getText().toString();
                //Relation = etRelation.getText().toString();
                //Event = etEvent.getText().toString();
                AadharCard = etAadharCard.getText().toString();

                VoterId = etVoterId.getText().toString();
                Dob = etDob.getText().toString();
                apartment = etApartment.getText().toString();
                flateNo = etFlateNumber.getText().toString();

                /*SeriesId = String.valueOf(spinner_series.getSelectedItemPosition());
                ColonyId = spinner_colony.getSelectedItemPosition();
                RowId = spinner_row.getSelectedItemPosition();
                StatusId = String.valueOf(spinner_status.getSelectedItemPosition());
                WaterSupplyId = spinner_water_Supply.getSelectedItemPosition();
                constituency = spinner_constituency.getSelectedItem().toString();
                zone = spinner_zone.getSelectedItem().toString();
                cityVillage = spinner_city.getSelectedItem().toString();
                prabhagWard = spinner_ward.getSelectedItem().toString();
                Qualification = spinner_qualification.getSelectedItem().toString();
                Caste = spinner_caste.getSelectedItem().toString();*/

                SeriesId = series_id;
                ColonyId = colony_id;
                RowId = row_id;
                StatusId = status_id;
                WaterSupplyId = water_supply_id;
                constituency = constituency_id;
                zone = zone_id;
                cityVillage = city_id;
                prabhagWard = ward_id;
                Qualification = qualification_id;
                Caste = caste_id;

                loader.setVisibility(View.VISIBLE);
                updateMemberData();

            }
        });

        //Toast.makeText(this, "SeriesId: " + spinner_series.getSelectedItemPosition(), Toast.LENGTH_SHORT).show();

    }

    //on create finish

    public void getUserDetail() {

        ApiInterface apiInterface = getRetrofitInstance().create(ApiInterface.class);

        Call<UserDetailResponse> call = apiInterface.getUserDetail(id);
        call.enqueue(new Callback<UserDetailResponse>() {
            @Override
            public void onResponse(Call<UserDetailResponse> call, Response<UserDetailResponse> response) {
                if (response.isSuccessful()) {

                    ArrayList<UserDetail> userDetails = response.body().getUserDetails();

                    Log.d("mamta", "Data: " + userDetails.toString());

                    loader.setVisibility(View.GONE);

                    int id = userDetails.get(0).getId();
                    String name = userDetails.get(0).getName();
                    String middleName = userDetails.get(0).getMiddleName();
                    String surname = userDetails.get(0).getSurname();
                    String votingCenter = userDetails.get(0).getVotingCenter();
                    String boothNo = userDetails.get(0).getBoothNo();
                    String mobile1 = userDetails.get(0).getMobile1();
                    String mobile2 = userDetails.get(0).getMobile2();
                    String dob = userDetails.get(0).getDob();
                    String qualification = userDetails.get(0).getQualification();
                    String caste = userDetails.get(0).getCaste();
                    String voterId = userDetails.get(0).getVoterId();
                    String adharCard = userDetails.get(0).getAdharCard();
                    String houseNo = userDetails.get(0).getHouseNo();
                    String gender = userDetails.get(0).getGender();
                    int seriesId = userDetails.get(0).getSeriesId();
                    int votingSrNo = userDetails.get(0).getVotingSrNo();
                    int colonyId = userDetails.get(0).getColonyId();
                    int rowId = userDetails.get(0).getRowId();
                    int waterSupplyId = userDetails.get(0).getWaterSupplyId();
                    String statusId = userDetails.get(0).getStatusId();
                    int memberId = userDetails.get(0).getMemberId();
                    String flateNo = userDetails.get(0).getFlatNo();
                    String apartment = userDetails.get(0).getApartment();


                    //Toast.makeText(UpdateMemberActivity.this, "c: " + colonyId + "R: " + rowId + "W: " + waterSupplyId + "S: " + seriesId, Toast.LENGTH_SHORT).show();

                    //passing the house no in global String
                    //HouseNo = houseNo;

                   /* if (seriesId == null) {
                        seriesId = "0";
                    }*/

                    if (statusId == null) {
                        statusId = "0";
                    }


                    etHouseNumber.setText(houseNo);
                    etName.setText(name);
                    etMiddleName.setText(middleName);
                    etSurname.setText(surname);
                    etVotingCenter.setText(votingCenter);
                    etBooth.setText(boothNo);
                    etMob1.setText(mobile1);
                    etMob2.setText(mobile2);
                    etDob.setText(dob);
                    etAadharCard.setText(adharCard);
                    etVoterId.setText(voterId);
                    etSerial.setText(String.valueOf(votingSrNo));
                    etApartment.setText(apartment);
                    etFlateNumber.setText(flateNo);

                    //spinner_series.setSelection(seriesId);
                    //spinner_colony.setSelection(colonyId);
                    //spinner_row.setSelection(rowId);
                    //spinner_status.setSelection(Integer.parseInt(statusId));
                    //spinner_water_Supply.setSelection(waterSupplyId);
                    // spinner_qualification.setSelection(Integer.parseInt(qualification));
                    // spinner_caste.setSelection(Integer.parseInt(caste));
                    //spinner_series.setSelection(seriesId);

                    series_id = String.valueOf(seriesId);
                    colony_id = String.valueOf(colonyId);
                    row_id = String.valueOf(rowId);
                    water_supply_id = String.valueOf(waterSupplyId);
                    status_id = statusId;

                    fetchSeries();
                    fetchStatus();
                    fetchWaterSupplyId();
                    fetchColony("0");
                    fetchRow("0","0");

                    MemberId = String.valueOf(memberId);

                    //radioGroup.setSelected(true);
                    if (gender != null) {
                        if (gender.equals("Male") || gender.equalsIgnoreCase("male")) {
                            radioButton1.setChecked(true);
                        } else if (gender.equals("Female") || gender.equalsIgnoreCase("female")) {
                            radioButton2.setChecked(true);
                        } else if (gender.equalsIgnoreCase("others")) {
                            radioButton3.setChecked(true);
                        }
                         /*else {
                        if (gender == null) {
                            Log.d("Tag", "Gender is null");
                        } else {
                            Log.d("Tag", "Gender is not recognized: " + gender);
                        }
                    }*/
                    } else {
                        Toast.makeText(UpdateMemberActivity.this, "Gender is null", Toast.LENGTH_SHORT).show();
                        Log.d("Tag", "Gender is null");
                    }


                } else {
                    loader.setVisibility(View.GONE);
                    Toast.makeText(UpdateMemberActivity.this, "Response Error..!!", Toast.LENGTH_SHORT).show();
                    Log.e("Tag", "Response Error..");
                }
            }

            @Override
            public void onFailure(Call<UserDetailResponse> call, Throwable throwable) {
                loader.setVisibility(View.GONE);
                Log.e("Tag", "Error.." + throwable.getLocalizedMessage());
                Toast.makeText(UpdateMemberActivity.this, "Error..", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void updateMemberData() {
        ApiInterface apiInterface = getRetrofitInstance().create(ApiInterface.class);

        //VotingSrNo and Aadhar card in Int or long

        EditFamilyMemberBody editFamilyMemberBody = new EditFamilyMemberBody(id, HouseNo, Name, MiddleName, Surname, VotingCenter, BoothNo,
                VotingSrNo, SeriesId, ColonyId, RowId, Gender, Mobile1, Mobile2, Dob, Qualification, Caste, AadharCard, WaterSupplyId, MemberId,
                VoterId, StatusId, apartment, flateNo, constituency, cityVillage, zone, prabhagWard);

        Log.d("Api Response", editFamilyMemberBody.toString());

        //Toast.makeText(this, "Data: " + editFamilyMemberBody.toString(), Toast.LENGTH_SHORT).show();

        Call<EditMemberRespponseData> call = apiInterface.updateFamilyDetails(editFamilyMemberBody);

        call.enqueue(new Callback<EditMemberRespponseData>() {
            @Override
            public void onResponse(Call<EditMemberRespponseData> call, Response<EditMemberRespponseData> response) {
                if (response.isSuccessful()) {
                    loader.setVisibility(View.GONE);

                    EditMemberRespponseData responseData = response.body();
                    String status = responseData.getStatus();
                    String message = responseData.getMessage();

                    Toast.makeText(UpdateMemberActivity.this, "Message: " + message, Toast.LENGTH_SHORT).show();
                    Toast.makeText(UpdateMemberActivity.this, "Status: " + status, Toast.LENGTH_SHORT).show();

                    finish();
                } else {
                    loader.setVisibility(View.GONE);
                    Toast.makeText(UpdateMemberActivity.this, "Response Error..!!", Toast.LENGTH_SHORT).show();
                    Log.e("Tag", "Response Error..");
                }
            }

            @Override
            public void onFailure(Call<EditMemberRespponseData> call, Throwable throwable) {
                loader.setVisibility(View.GONE);
                Log.e("Tag", "Error.." + throwable.getLocalizedMessage());
                Toast.makeText(UpdateMemberActivity.this, "Error..", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //--------------------------------------------------------------------------------------

    private void fetchSeries() {
        ApiInterface apiInterface = getRetrofitInstance().create(ApiInterface.class);
        Call<SeriesResponse> call = apiInterface.seriesResponse();

        call.enqueue(new Callback<SeriesResponse>() {
            @Override
            public void onResponse(Call<SeriesResponse> call, retrofit2.Response<SeriesResponse> response) {
                if (response.isSuccessful()) {

                    List<SeriesItem> seriesItems = response.body().getSeries();

                    List<String> seriesNames = new ArrayList<>();
                    final Map<String, String> seriesIdMap = new HashMap<>();
                    seriesNames.add("Select Series");

                    //List<String> seriesId = new ArrayList<>();
                    //seriesId.add("0");

                    for (SeriesItem item : seriesItems) {
                        seriesNames.add(item.getSeriesName());
                        seriesIdMap.put(item.getSeriesName(), item.getId());
                    }

                    // Populate the spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(UpdateMemberActivity.this,
                            android.R.layout.simple_spinner_item, seriesNames);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_series.setAdapter(adapter);

                    // Handle spinner item selection
                    // Set spinner selection based on series ID (if you have the ID)

                    String selectedSeriesId = series_id; // Replace with your series ID
                    String selectedRowId = row_id;
                    String selectedColonyId = colony_id;

                    if (selectedSeriesId != null) {
                        String selectedSeriesName = getSeriesNameById(seriesIdMap, selectedSeriesId);
                        if (selectedSeriesName != null) {
                            int position = adapter.getPosition(selectedSeriesName);
                            if (position >= 0) {
                                spinner_series.setSelection(position);
                            }
                        }
                    }

                    spinner_series.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String selectedSeries = (String) parent.getItemAtPosition(position);
                            if (!selectedSeries.equals("Select Series")) {
                                //fetchColony();
                                String selectedSeriesId = seriesIdMap.get(selectedSeries);
                                fetchColony(selectedSeriesId);
                                series_id = selectedSeriesId;
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            // Handle nothing selected
                        }
                    });

                } else {
                    Log.e("API Error", "Failed to fetch Series");
                }
            }

            @Override
            public void onFailure(Call<SeriesResponse> call, Throwable throwable) {
                Toast.makeText(UpdateMemberActivity.this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("API Error", "Error fetching prabhag wards: " + throwable.getMessage());
            }
        });
    }

    private String getSeriesNameById(Map<String, String> seriesIdMap, String seriesId) {
        for (Map.Entry<String, String> entry : seriesIdMap.entrySet()) {
            if (entry.getValue().equals(seriesId)) {
                return entry.getKey();
            }
        }
        return null;
    }

    private void fetchColony(String SeriesId) {
        ApiInterface apiInterface = getRetrofitInstance().create(ApiInterface.class);

        Call<ColonyResponse> call = apiInterface.colonyResponse(SeriesId);
        call.enqueue(new Callback<ColonyResponse>() {
            @Override
            public void onResponse(Call<ColonyResponse> call, retrofit2.Response<ColonyResponse> response) {
                if (response.isSuccessful()) {
                    List<ColonyItem> colonyItems = response.body().getColonies();

                    List<String> colonyNames = new ArrayList<>();
                    final Map<String, String> colonyIdMap = new HashMap<>();
                    colonyNames.add("Select Colony");

                    for (ColonyItem item : colonyItems) {
                        colonyNames.add(item.getColonyName());
                        colonyIdMap.put(item.getColonyName(), item.getId());
                    }

                    // Populate the spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(UpdateMemberActivity.this,
                            android.R.layout.simple_spinner_item, colonyNames);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_colony.setAdapter(adapter);

                    //String selectedSeriesId = series_id; // Replace with your series ID
                    //String selectedRowId = row_id;
                    String selectedColonyId = colony_id;

                    if (selectedColonyId != null) {
                        String selectedColonyName = getColonyById(colonyIdMap, selectedColonyId);
                        if (selectedColonyName != null) {
                            int position = adapter.getPosition(selectedColonyName);
                            if (position >= 0) {
                                spinner_colony.setSelection(position);
                            }
                        }
                    }

                    // Handle spinner item selection
                    spinner_colony.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String selectedColony = (String) parent.getItemAtPosition(position);
                            if (!selectedColony.equals("Select Colony")) {
                                //Toast.makeText(UserSurveyActivity.this, "Selected: " + selectedCityVillage, Toast.LENGTH_SHORT).show();
                                // Perform any other actions based on selection
                                String selectedColonyId = colonyIdMap.get(selectedColony);
                                fetchRow(SeriesId, selectedColonyId);
                                colony_id = selectedColonyId;
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            // Handle nothing selected
                        }
                    });
                } else {
                    Toast.makeText(UpdateMemberActivity.this, "Response Not Success: " + response.code(), Toast.LENGTH_SHORT).show();
                    Log.e("API Error", "Response Not Success: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ColonyResponse> call, Throwable throwable) {
                Toast.makeText(UpdateMemberActivity.this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("API Error", "Error fetching prabhag wards: " + throwable.getMessage());
            }
        });
    }
    private String getColonyById(Map<String, String> colonyIdMap, String colonyId) {
        for (Map.Entry<String, String> entry : colonyIdMap.entrySet()) {
            if (entry.getValue().equals(colonyId)) {
                return entry.getKey();
            }
        }
        return null;
    }

    private void fetchRow(String seriesId, String colonyId) {
        ApiInterface apiInterface = getRetrofitInstance().create(ApiInterface.class);
        Call<RowResponse> call = apiInterface.rowResponse(seriesId, colonyId);
        call.enqueue(new Callback<RowResponse>() {
            @Override
            public void onResponse(Call<RowResponse> call, retrofit2.Response<RowResponse> response) {
                if (response.isSuccessful()) {
                    List<RowItem> rowItems = response.body().getRows();

                    List<String> rowNames = new ArrayList<>();
                    final Map<String, String> rowIdMap = new HashMap<>();
                    rowNames.add("Select Row");

                    for (RowItem item : rowItems) {
                        rowNames.add(item.getRowName());
                        rowIdMap.put(item.getRowName(), item.getId());
                    }


                    // Populate the spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(UpdateMemberActivity.this,
                            android.R.layout.simple_spinner_item, rowNames);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_row.setAdapter(adapter);

                    String selectedRowId = row_id;
                    //String selectedColonyId = colony_id;

                    if (selectedRowId != null) {
                        String selectedRowName = getRowById(rowIdMap, selectedRowId);
                        if (selectedRowName != null) {
                            int position = adapter.getPosition(selectedRowName);
                            if (position >= 0) {
                                spinner_row.setSelection(position);
                            }
                        }
                    }

                    // Handle spinner item selection
                    spinner_row.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String selectedRows = (String) parent.getItemAtPosition(position);
                            if (!selectedRows.equals("Select Row")) {
                                //fetchColony();
                                String selectedRowId = rowIdMap.get(selectedRows);
                                row_id = selectedRowId;
                                //Toast.makeText(UpdateMemberActivity.this, "S: " + series_id + " C: " + colony_id + " R: " + row_id, Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            // Handle nothing selected
                        }
                    });
                } else {
                    Log.e("API Error", "Failed to fetch Row");
                }
            }

            @Override
            public void onFailure(Call<RowResponse> call, Throwable throwable) {
                Toast.makeText(UpdateMemberActivity.this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("API Error", "Error fetching prabhag wards: " + throwable.getMessage());
            }
        });
    }
    private String getRowById(Map<String, String> rowIdMap, String rowId) {
        for (Map.Entry<String, String> entry : rowIdMap.entrySet()) {
            if (entry.getValue().equals(rowId)) {
                return entry.getKey();
            }
        }
        return null;
    }

    private void fetchWaterSupplyId() {
        ApiInterface apiInterface = getRetrofitInstance().create(ApiInterface.class);

        Call<WaterSupplyResponse> call = apiInterface.waterSupplyResponse();
        call.enqueue(new Callback<WaterSupplyResponse>() {
            @Override
            public void onResponse(Call<WaterSupplyResponse> call, Response<WaterSupplyResponse> response) {
                if (response.isSuccessful()) {

                    List<WaterSupplyItem> waterSupplyItems = response.body().getWaterSupply();

                    List<String> waterSupplyNames = new ArrayList<>();
                    final Map<String, String> waterSupplyIdMap = new HashMap<>();
                    waterSupplyNames.add("Select WaterSupply");

                    for (WaterSupplyItem item : waterSupplyItems) {
                        waterSupplyNames.add(item.getSlotName());
                        waterSupplyIdMap.put(item.getSlotName(), item.getId());
                    }

                    // Populate the spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(UpdateMemberActivity.this,
                            android.R.layout.simple_spinner_item, waterSupplyNames);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_water_Supply.setAdapter(adapter);

                    String selectedWaterSupplyId = water_supply_id;

                    if (selectedWaterSupplyId != null) {
                        String selectedWaterSupplyName = getWaterSupplyNameById(waterSupplyIdMap, selectedWaterSupplyId);
                        if (selectedWaterSupplyName != null) {
                            int position = adapter.getPosition(selectedWaterSupplyName);
                            if (position >= 0) {
                                spinner_water_Supply.setSelection(position);
                            }
                        }
                    }


                    // Handle spinner item selection
                    spinner_water_Supply.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String selectedWaterSupply = (String) parent.getItemAtPosition(position);
                            if (!selectedWaterSupply.equals("Select WaterSupply")) {
                                //fetchColony();
                                String selectedWaterSupplyId = waterSupplyIdMap.get(selectedWaterSupply);
                                water_supply_id = selectedWaterSupplyId;
                                //Toast.makeText(UserSurveyActivity.this, "S: " + series_id + " C: " + colony_id  + " R: " + row_id, Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            // Handle nothing selected
                        }
                    });

                } else {
                    Log.e("API Error", "Failed to fetch water supply");
                }
            }

            @Override
            public void onFailure(Call<WaterSupplyResponse> call, Throwable throwable) {
                Toast.makeText(UpdateMemberActivity.this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("API Error", "Error fetching prabhag wards: " + throwable.getMessage());
            }
        });
    }
    private String getWaterSupplyNameById(Map<String, String> waterSupplyIdMap, String waterSupplyId) {
        for (Map.Entry<String, String> entry : waterSupplyIdMap.entrySet()) {
            if (entry.getValue().equals(waterSupplyId)) {
                return entry.getKey();
            }
        }
        return null;
    }

    private void fetchStatus() {

        ApiInterface apiInterface = getRetrofitInstance().create(ApiInterface.class);
        Call<StatusResponse> call = apiInterface.statusResponse();

        call.enqueue(new Callback<StatusResponse>() {
            @Override
            public void onResponse(Call<StatusResponse> call, Response<StatusResponse> response) {
                if (response.isSuccessful()) {

                    List<StatusItem> statusItems = response.body().getStatus();

                    List<String> statusNames = new ArrayList<>();
                    final Map<String, String> statusIdMap = new HashMap<>();
                    statusNames.add("Select Status");

                    for (StatusItem item : statusItems) {
                        statusNames.add(item.getStatusName());
                        statusIdMap.put(item.getStatusName(), item.getId());
                    }

                    // Populate the spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(UpdateMemberActivity.this,
                            android.R.layout.simple_spinner_item, statusNames);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_status.setAdapter(adapter);

                    String selectedStatusId = status_id;

                    if (selectedStatusId != null) {
                        String selectedStatusName = getStatusByName(statusIdMap, selectedStatusId);
                        if (selectedStatusName != null) {
                            int position = adapter.getPosition(selectedStatusName);
                            if (position >= 0) {
                                spinner_status.setSelection(position);
                            }
                        }
                    }

                    // Handle spinner item selection
                    spinner_status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String selectedStatus = (String) parent.getItemAtPosition(position);
                            if (!selectedStatus.equals("Select Status")) {
                                //fetchColony();
                                String selectedStatusId = statusIdMap.get(selectedStatus);
                                status_id = selectedStatusId;

                                //Toast.makeText(UserSurveyActivity.this, "S: " + series_id + " C: " + colony_id  + " R: " + row_id, Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            // Handle nothing selected
                        }
                    });
                } else {
                    Log.e("API Error", "Failed to fetch water supply");
                }
            }

            @Override
            public void onFailure(Call<StatusResponse> call, Throwable throwable) {
                Toast.makeText(UpdateMemberActivity.this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("API Error", "Error fetching prabhag wards: " + throwable.getMessage());
            }
        });

    }

    private String getStatusByName(Map<String, String> statusIdMap, String statusId) {
        for (Map.Entry<String, String> entry : statusIdMap.entrySet()) {
            if (entry.getValue().equals(statusId)) {
                return entry.getKey();
            }
        }
        return null;
    }

    //----------------------------------------------------------------------------------------

    private void fetchConstituencies() {

        ApiInterface apiInterface = getRetrofitInstance().create(ApiInterface.class);
        Call<ConstituencyResponse> call = apiInterface.getConstituencyList();
        call.enqueue(new Callback<ConstituencyResponse>() {
            @Override
            public void onResponse(Call<ConstituencyResponse> call, retrofit2.Response<ConstituencyResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<ConstituencyItem> constituencies = response.body().getConstituency();

                    // Create a list of constituency names
                    List<String> constituencyName = new ArrayList<>();
                    final Map<String, String> constituencyIdMap = new HashMap<>();
                    constituencyName.add("Select Constituency");

                    for (ConstituencyItem constituency : constituencies) {
                        constituencyName.add(constituency.getConstituencyName());
                        constituencyIdMap.put(constituency.getConstituencyName(), constituency.getId());
                    }

                    //Toast.makeText(UserSurveyActivity.this, "Names: " + constituencyName, Toast.LENGTH_SHORT).show();
                    //Log.d("Api Response","Names: " + constituencyName);

                    // Populate the spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(UpdateMemberActivity.this, android.R.layout.simple_spinner_item, constituencyName);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_constituency.setAdapter(adapter);

                    // Handle spinner item selection
                    spinner_constituency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String selectedConstituency = (String) parent.getItemAtPosition(position);
                            if (!selectedConstituency.equals("Select Constituency")) {
                                //Toast.makeText(UserSurveyActivity.this, "Selected: " + selectedConstituency, Toast.LENGTH_SHORT).show();
                                String selectedConstituencyId = constituencyIdMap.get(selectedConstituency);
                                constituency_id = selectedConstituencyId;
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            // Handle nothing selected
                        }
                    });

                } else {
                    // Handle unsuccessful response
                    Log.e("API Error", "Failed to fetch constituencies");
                }
            }

            @Override
            public void onFailure(Call<ConstituencyResponse> call, Throwable throwable) {
                //Toast.makeText(UserSurveyActivity.this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(UpdateMemberActivity.this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("API Error", "Error fetching prabhag wards: " + throwable.getMessage());
            }
        });
    }

    private void fetchCityVillages() {
        ApiInterface apiInterface = getRetrofitInstance().create(ApiInterface.class);

        Call<CityVillageResponse> call = apiInterface.getCityVillage();

        call.enqueue(new Callback<CityVillageResponse>() {
            @Override
            public void onResponse(Call<CityVillageResponse> call, retrofit2.Response<CityVillageResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<CityVillageItem> cityVillages = response.body().getCityVillage();

                    // Create a list of city or village names
                    List<String> cityVillageNames = new ArrayList<>();
                    final Map<String, String> cityVillageIdMap = new HashMap<>();
                    cityVillageNames.add("Select City/Village");

                    for (CityVillageItem item : cityVillages) {
                        cityVillageNames.add(item.getCityVillageName());
                        cityVillageIdMap.put(item.getCityVillageName(), item.getId());
                    }

                    // Populate the spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(UpdateMemberActivity.this,
                            android.R.layout.simple_spinner_item, cityVillageNames);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_city.setAdapter(adapter);

                    // Handle spinner item selection
                    spinner_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String selectedCityVillage = (String) parent.getItemAtPosition(position);
                            if (!selectedCityVillage.equals("Select City or Village")) {
                                String selectedCityVillageId = cityVillageIdMap.get(selectedCityVillage);
                                city_id = selectedCityVillageId;
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            // Handle nothing selected
                        }
                    });

                } else {
                    // Handle unsuccessful response
                    Log.e("API Error", "Failed to fetch city or village data");
                }
            }

            @Override
            public void onFailure(Call<CityVillageResponse> call, Throwable throwable) {
                Toast.makeText(UpdateMemberActivity.this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("API Error", "Error fetching prabhag wards: " + throwable.getMessage());
            }
        });

    }

    private void fetchZones() {
        ApiInterface apiInterface = getRetrofitInstance().create(ApiInterface.class);

        Call<ZoneResponse> call = apiInterface.getZoneList();

        call.enqueue(new Callback<ZoneResponse>() {
            @Override
            public void onResponse(Call<ZoneResponse> call, retrofit2.Response<ZoneResponse> response) {

                if (response.isSuccessful() && response.body() != null) {
                    List<ZoneItem> zones = response.body().getZone();

                    // Create a list of zone names
                    List<String> zoneNames = new ArrayList<>();
                    final Map<String, String> zoneIdMap = new HashMap<>();
                    zoneNames.add("Select Zone");

                    for (ZoneItem zone : zones) {
                        zoneNames.add(zone.getZoneName());
                        zoneIdMap.put(zone.getZoneName(), zone.getId());
                    }

                    // Populate the spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(UpdateMemberActivity.this,
                            android.R.layout.simple_spinner_item, zoneNames);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_zone.setAdapter(adapter);

                    // Handle spinner item selection
                    spinner_zone.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String selectedZone = (String) parent.getItemAtPosition(position);
                            if (!selectedZone.equals("Select Zone")) {
                                String selectedZoneId = zoneIdMap.get(selectedZone);
                                zone_id = selectedZoneId;
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            // Handle nothing selected
                        }
                    });

                } else {
                    // Handle unsuccessful response
                    Log.e("API Error", "Failed to fetch zones");
                }
            }

            @Override
            public void onFailure(Call<ZoneResponse> call, Throwable throwable) {
                Toast.makeText(UpdateMemberActivity.this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("API Error", "Error fetching prabhag wards: " + throwable.getMessage());
            }
        });

    }

    private void fetchPrabhagWards() {
        ApiInterface apiInterface = getRetrofitInstance().create(ApiInterface.class);

        Call<PrabhagWardResponse> call = apiInterface.getPrabhagWardList();
        call.enqueue(new Callback<PrabhagWardResponse>() {
            @Override
            public void onResponse(Call<PrabhagWardResponse> call, retrofit2.Response<PrabhagWardResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<PrabhagWardItem> prabhagWards = response.body().getPrabhagWard();

                    // Create a list of prabhag ward names
                    List<String> prabhagWardNames = new ArrayList<>();
                    final Map<String, String> prabhagWardIdMap = new HashMap<>();
                    prabhagWardNames.add("Select Prabhag/Ward");

                    for (PrabhagWardItem prabhagWard : prabhagWards) {
                        prabhagWardNames.add(prabhagWard.getPrabhagWardName());
                        prabhagWardIdMap.put(prabhagWard.getPrabhagWardName(), prabhagWard.getId());
                    }

                    // Populate the spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(UpdateMemberActivity.this,
                            android.R.layout.simple_spinner_item, prabhagWardNames);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_ward.setAdapter(adapter);

                    // Handle spinner item selection
                    spinner_ward.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String selectedPrabhagWard = (String) parent.getItemAtPosition(position);
                            if (!selectedPrabhagWard.equals("Select Prabhag Ward")) {
                                String selectedPrabhagWardId = prabhagWardIdMap.get(selectedPrabhagWard);
                                ward_id = selectedPrabhagWardId;
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            // Handle nothing selected
                        }
                    });

                } else {
                    // Handle unsuccessful response
                    Log.e("API Error", "Failed to fetch prabhag wards");
                }
            }

            @Override
            public void onFailure(Call<PrabhagWardResponse> call, Throwable throwable) {
                Toast.makeText(UpdateMemberActivity.this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("API Error", "Error fetching prabhag wards: " + throwable.getMessage());
            }
        });
    }

    private void fetchQualification() {

        ApiInterface apiInterface = getRetrofitInstance().create(ApiInterface.class);

        Call<QualificationsResponse> call = apiInterface.qualificationResponse();

        call.enqueue(new Callback<QualificationsResponse>() {
            @Override
            public void onResponse(Call<QualificationsResponse> call, retrofit2.Response<QualificationsResponse> response) {
                if (response.isSuccessful()) {

                    List<QualificationItem> qualificationResponse = response.body().getQualifications();

                    // Create a list of prabhag ward names
                    List<String> qualificationNames = new ArrayList<>();
                    final Map<String, String> qualificationIdMap = new HashMap<>();
                    qualificationNames.add("Select Qualification");

                    for (QualificationItem qualification : qualificationResponse) {
                        qualificationNames.add(qualification.getQualificationName());
                        qualificationIdMap.put(qualification.getQualificationName(), qualification.getId());
                    }

                    // Populate the spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(UpdateMemberActivity.this,
                            android.R.layout.simple_spinner_item, qualificationNames);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_qualification.setAdapter(adapter);

                    // Handle spinner item selection
                    spinner_qualification.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String selectedQualification = (String) parent.getItemAtPosition(position);
                            if (!selectedQualification.equals("Select Qualification")) {
                                String selectedQualificationId = qualificationIdMap.get(selectedQualification);
                                qualification_id = selectedQualificationId;
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            // Handle nothing selected
                        }
                    });

                } else {
                    Toast.makeText(UpdateMemberActivity.this, "Response Error..!!", Toast.LENGTH_SHORT).show();
                    Log.e("Api Response", "Response Error..");
                }
            }

            @Override
            public void onFailure(Call<QualificationsResponse> call, Throwable throwable) {
                Log.e("Api Response", "Error.." + throwable.getLocalizedMessage());
                Toast.makeText(UpdateMemberActivity.this, "Error.." + throwable.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchCaste() {
        ApiInterface apiInterface = getRetrofitInstance().create(ApiInterface.class);

        Call<CasteResponse> call = apiInterface.casteResponse();
        call.enqueue(new Callback<CasteResponse>() {
            @Override
            public void onResponse(Call<CasteResponse> call, retrofit2.Response<CasteResponse> response) {
                if (response.isSuccessful()) {
                    List<CasteItem> casteResponses = response.body().getCastes();

                    // Create a list of prabhag ward names
                    List<String> casteNames = new ArrayList<>();
                    final Map<String, String> casteIdMap = new HashMap<>();
                    casteNames.add("Select Caste");

                    for (CasteItem caste : casteResponses) {
                        casteNames.add(caste.getCasteName());
                        casteIdMap.put(caste.getCasteName(), caste.getId());
                    }

                    // Populate the spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(UpdateMemberActivity.this,
                            android.R.layout.simple_spinner_item, casteNames);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_caste.setAdapter(adapter);

                    // Handle spinner item selection
                    spinner_caste.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String selectedCaste = (String) parent.getItemAtPosition(position);
                            if (!selectedCaste.equals("Select Caste")) {

                                String selectedCasteId = casteIdMap.get(selectedCaste);
                                caste_id = selectedCasteId;
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            // Handle nothing selected
                        }
                    });
                } else {
                    Toast.makeText(UpdateMemberActivity.this, "Response Error..!!", Toast.LENGTH_SHORT).show();
                    Log.e("Api Response", "Response Error..");
                }
            }

            @Override
            public void onFailure(Call<CasteResponse> call, Throwable throwable) {
                Log.e("Api Response", "Error.." + throwable.getLocalizedMessage());
                Toast.makeText(UpdateMemberActivity.this, "Error.." + throwable.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onRefresh() {

        if (Method.haveNetworkConnection(this)) {
            if (SharedPref.getAppStatus(this).equalsIgnoreCase("on")) {
                //getSeriesList();
                //getColonyList(series_id);
            } else {
                swipe_refresh.setRefreshing(false);
            }
        } else {
            swipe_refresh.setRefreshing(false);
        }
    }

}