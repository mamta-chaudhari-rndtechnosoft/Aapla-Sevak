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
import com.vinodmapari.aaplasevak.Model.Series;
import com.vinodmapari.aaplasevak.Model.SharedPref;
import com.vinodmapari.aaplasevak.Model.SurveyList;
import com.vinodmapari.aaplasevak.Model.UserDetail;
import com.vinodmapari.aaplasevak.Model.UserDetailResponse;
import com.vinodmapari.aaplasevak.Model.ZoneItem;
import com.vinodmapari.aaplasevak.Model.ZoneResponse;
import com.vinodmapari.aaplasevak.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateMemberActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    ArrayList<SurveyList> surveyLists;
    ArrayList<Colony> colonies;
    CustomAdapter adapter;
    int selected_series, selected_status, selected_colony, selected_row, selected_water_supply;
    String series_id, status_id, colony_id, row_id, water_supply_id;
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

    int ColonyId, RowId, WaterSupplyId, MemberId, VotingSrNo;
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


        getSeriesList();


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

        series_name.clear();
        series_name = new ArrayList<>();

        for (int i = 0; i < Constants.series.size(); i++) {
            Constants.series_name.add(Constants.series.get(i).getSeries_name());
            if (series_name != null && series_name.equals(Constants.series.get(i).getSeries_name())) {
                selected_series = i;
            } else {
                //Toast.makeText(UpdateMemberActivity.this, "Series name: " + selected_series_id, Toast.LENGTH_SHORT).show();
            }
        }

        status_name.clear();
        status_name = new ArrayList<>();

        for (int i = 0; i < Constants.statuses.size(); i++) {
            Constants.status_name.add(Constants.statuses.get(i).getStatus_name());
            if (status_name != null && status_name.equals(Constants.statuses.get(i).getStatus_name())) {
                selected_status = i;
            }
        }

        Constants.colony_name.clear();
        Constants.colony_name = new ArrayList<>();

        for (int i = 0; i < Constants.colonies.size(); i++) {
            Constants.colony_name.add(Constants.colonies.get(i).getColony_name());
            if (Constants.colony_name != null && Constants.colony_name.equals(Constants.colonies.get(i).getColony_name())) {
                selected_colony = i;
            }
        }


        Constants.row_name.clear();
        Constants.row_name = new ArrayList<>();

        for (int i = 0; i < Constants.rows.size(); i++) {
            Constants.row_name.add(Constants.rows.get(i).getRow_name());
            if (Constants.row_name != null && Constants.row_name.equals(Constants.rows.get(i).getRow_name())) {
                selected_row = i;
            }
        }

        water_supply_slots.clear();
        water_supply_slots = new ArrayList<>();

        for (int i = 0; i < Constants.waterSupplies.size(); i++) {
            Constants.water_supply_slots.add(Constants.waterSupplies.get(i).getSlot_name());
            if (water_supply_slots != null && water_supply_slots.equals(Constants.waterSupplies.get(i).getSlot_name())) {
                selected_water_supply = i;
            }
        }


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Constants.series_name);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_series.setAdapter(dataAdapter);
        spinner_series.setSelection(selected_series);

        ArrayAdapter<String> dataAdapter_Status = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Constants.status_name);
        dataAdapter_Status.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_status.setAdapter(dataAdapter_Status);
        spinner_status.setSelection(selected_status);

        ArrayAdapter<String> dataAdapter_Colony = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Constants.colony_name);
        dataAdapter_Colony.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_colony.setAdapter(dataAdapter_Colony);
        spinner_colony.setSelection(selected_colony);

        ArrayAdapter<String> dataAdapter_Row = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Constants.row_name);
        dataAdapter_Row.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_row.setAdapter(dataAdapter_Row);
        spinner_row.setSelection(selected_row);

        ArrayAdapter<String> dataAdapter_water_supply = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, water_supply_slots);
        dataAdapter_water_supply.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_water_Supply.setAdapter(dataAdapter_water_supply);
        spinner_water_Supply.setSelection(selected_water_supply);

        loader.setVisibility(View.VISIBLE);
        getUserDetail();


        spinner_series.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                series_id = Constants.series.get(position).getId();
                selected_series_id = spinner_series.getSelectedItemId();

                if (selected_series_id != 0) {
                    getColonyList(selected_series_id);
                } else {
                    Toast.makeText(UpdateMemberActivity.this, "Series Is: " + selected_series_id, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                status_id = Constants.statuses.get(position).getId();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinner_colony.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                colonyName = String.valueOf(spinner_colony.getSelectedItem());
                for (int i = 0; i < colonies.size(); i++) {

                    if (colonies.get(i).getColony_name().equalsIgnoreCase(colonyName)) {

                        getRowList(selected_series_id, Long.parseLong(colonies.get(i).getId()));
                        colony_id = String.valueOf(Long.parseLong(colonies.get(i).getId()));

                    }

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_row.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                row_id = Constants.rows.get(position).getId();
                //Toast.makeText(UserSurveyActivity.this, "row_id= "+row_id, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinner_water_Supply.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                water_supply_id = Constants.waterSupplies.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        fetchConstituencies();
        fetchZones();
        fetchPrabhagWards();
        fetchCityVillages();
        fetchConstituencies();
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
                VotingSrNo = Integer.parseInt(etSerial.getText().toString());
                SeriesId = String.valueOf(spinner_series.getSelectedItemPosition());
                ColonyId = spinner_colony.getSelectedItemPosition();
                RowId = spinner_row.getSelectedItemPosition();
                StatusId = String.valueOf(spinner_status.getSelectedItemPosition());
                Gender = gender;
                Mobile1 = etMob1.getText().toString();
                Mobile2 = etMob2.getText().toString();
                //Relation = etRelation.getText().toString();
                //Event = etEvent.getText().toString();
                AadharCard = etAadharCard.getText().toString();
                WaterSupplyId = spinner_water_Supply.getSelectedItemPosition();
                VoterId = etVoterId.getText().toString();
                Dob = etDob.getText().toString();


                constituency = spinner_constituency.getSelectedItem().toString();
                zone = spinner_zone.getSelectedItem().toString();
                cityVillage = spinner_city.getSelectedItem().toString();
                prabhagWard = spinner_ward.getSelectedItem().toString();
                Qualification = spinner_qualification.getSelectedItem().toString();
                Caste = spinner_caste.getSelectedItem().toString();

                apartment = etApartment.getText().toString();
                flateNo = etFlateNumber.getText().toString();

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

                    spinner_series.setSelection(seriesId);
                    //spinner_colony.setSelection(colonyId);
                    //spinner_row.setSelection(rowId);
                    //spinner_status.setSelection(Integer.parseInt(statusId));
                    spinner_water_Supply.setSelection(waterSupplyId);
                    // spinner_qualification.setSelection(Integer.parseInt(qualification));
                    // spinner_caste.setSelection(Integer.parseInt(caste));

                    MemberId = memberId;


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
                VotingSrNo, Integer.parseInt(SeriesId), ColonyId, RowId, Gender, Mobile1, Mobile2, Dob, Qualification, Caste, AadharCard, WaterSupplyId, MemberId,
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


    private void getSeriesList() {

        final RequestQueue requestQueue = Volley.newRequestQueue(UpdateMemberActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.series_list, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Constants.series.clear();
                Constants.series = new ArrayList<>();
                Constants.series.add(new Series("0", "select series"));
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    //  Log.d("TAG", "onResponse: "+response);

                    JSONArray jsonArray = jsonObject.getJSONArray("SERIES");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String id = jsonObject1.getString("id");
                        String series_name = jsonObject1.getString("series_name");

                        Constants.series.add(new Series(id, series_name));
                    }
                    //getColonyList(response.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                requestQueue.stop();
            }
        });
        requestQueue.add(stringRequest);

    }

    private void getRowList(long series_id, long colony_id) {

        Constants.rows.add(new Row("0", "select row"));
        final RequestQueue requestQueue = Volley.newRequestQueue(UpdateMemberActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.row_list + "&series_id=" + series_id + "&colony_id=" + colony_id, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                spinner_row.setAdapter(null);
                Constants.rows.clear();
                Constants.row_name.clear();
                Constants.row_name = new ArrayList<>();
                Constants.rows = new ArrayList<>();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Log.d("TAG", "onResponse: " + response);

                    JSONArray jsonArray = jsonObject.getJSONArray("ROW");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String id = jsonObject1.getString("id");
                        String row_name = jsonObject1.getString("row_name");
                        Constants.rows.add(new Row(id, row_name));

                        //Toast.makeText(UserSurveyActivity.this, "vaishnavi= "+id, Toast.LENGTH_SHORT).show();
                        Constants.row_name.add(row_name);
                        spinner_row.setAdapter(new ArrayAdapter<String>(UpdateMemberActivity.this, android.R.layout.simple_spinner_item, Constants.row_name));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                requestQueue.stop();
                // Toast.makeText(UserSurveyActivity.this, ""+error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(stringRequest);

    }

    private void getColonyList(long series_id) {

        // Toast.makeText(UserSurveyActivity.this, "series-id here"+series_id, Toast.LENGTH_SHORT).show();
        //recreate();
        final RequestQueue requestQueue = Volley.newRequestQueue(UpdateMemberActivity.this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.colony_list + "&series_id=" + series_id, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Constants.colonies.clear();
                Constants.colony_name.clear();
                Constants.colony_name = new ArrayList<>();
                Constants.colonies = new ArrayList<>();
                spinner_colony.setAdapter(null);

                Constants.colonies.add(new Colony("0", "select colony"));
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Log.d("TAG", "onResponse: " + response);
                    // Toast.makeText(UserSurveyActivity.this, ""+response, Toast.LENGTH_SHORT).show();

                    JSONArray jsonArray = jsonObject.getJSONArray("COLONY");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String id = jsonObject1.getString("id");
                        String colony_name = jsonObject1.getString("colony_name");
                        colonies.add(new Colony(id, colony_name));
                        Constants.colony_name.add(colony_name);
                        spinner_colony.setAdapter(new ArrayAdapter<String>(UpdateMemberActivity.this, android.R.layout.simple_spinner_item, Constants.colony_name));

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                requestQueue.stop();
            }
        });
        requestQueue.add(stringRequest);

    }

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
                    constituencyName.add("Select Constituency");

                    for (ConstituencyItem constituency : constituencies) {
                        constituencyName.add(constituency.getConstituencyName());
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
                    cityVillageNames.add("Select City/Village");

                    for (CityVillageItem item : cityVillages) {
                        cityVillageNames.add(item.getCityVillageName());
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
                                //Toast.makeText(UserSurveyActivity.this, "Selected: " + selectedCityVillage, Toast.LENGTH_SHORT).show();
                                // Perform any other actions based on selection
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
                    zoneNames.add("Select Zone");

                    for (ZoneItem zone : zones) {
                        zoneNames.add(zone.getZoneName());
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
                                //Toast.makeText(UserSurveyActivity.this, "Selected: " + selectedZone, Toast.LENGTH_SHORT).show();
                                // Perform any other actions based on selection
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
                    prabhagWardNames.add("Select Prabhag/Ward");

                    for (PrabhagWardItem prabhagWard : prabhagWards) {
                        prabhagWardNames.add(prabhagWard.getPrabhagWardName());
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
                                //Toast.makeText(UserSurveyActivity.this, "Selected: " + selectedPrabhagWard, Toast.LENGTH_SHORT).show();
                                // Perform any other actions based on selection
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
                    qualificationNames.add("Select Qualification");

                    for (QualificationItem qualification : qualificationResponse) {
                        qualificationNames.add(qualification.getQualificationName());
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
                                //Toast.makeText(UserSurveyActivity.this, "Selected: " + selectedPrabhagWard, Toast.LENGTH_SHORT).show();
                                // Perform any other actions based on selection
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
                    casteNames.add("Select Caste");

                    for (CasteItem caste : casteResponses) {
                        casteNames.add(caste.getCasteName());
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

                                //Toast.makeText(UserSurveyActivity.this, "Selected: " + selectedPrabhagWard, Toast.LENGTH_SHORT).show();
                                // Perform any other actions based on selection
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
                getSeriesList();
                //getColonyList(series_id);
            } else {
                swipe_refresh.setRefreshing(false);
            }
        } else {
            swipe_refresh.setRefreshing(false);
        }
    }

}