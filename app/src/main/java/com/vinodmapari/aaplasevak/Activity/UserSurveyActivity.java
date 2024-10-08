package com.vinodmapari.aaplasevak.Activity;

import static com.vinodmapari.aaplasevak.ApiConfig.ApiHandler.getRetrofitInstance;
import static com.vinodmapari.aaplasevak.Model.Constants.city_village_name;
import static com.vinodmapari.aaplasevak.Model.Constants.constituency_name;
import static com.vinodmapari.aaplasevak.Model.Constants.prabhag_ward_name;
import static com.vinodmapari.aaplasevak.Model.Constants.series_name;
import static com.vinodmapari.aaplasevak.Model.Constants.status_name;
import static com.vinodmapari.aaplasevak.Model.Constants.water_supply_slots;
import static com.vinodmapari.aaplasevak.Model.Constants.zone_name;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.appbar.MaterialToolbar;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import com.vinodmapari.aaplasevak.ApiConfig.ApiHandler;
import com.vinodmapari.aaplasevak.ApiConfig.ApiInterface;
import com.vinodmapari.aaplasevak.CustomAdapter;
import com.vinodmapari.aaplasevak.Model.AddSurveyBody;
import com.vinodmapari.aaplasevak.Model.AddSurveyResponseData;
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
import com.vinodmapari.aaplasevak.Model.HouseResponse;
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
import com.vinodmapari.aaplasevak.Model.WaterSupplyItem;
import com.vinodmapari.aaplasevak.Model.WaterSupplyResponse;
import com.vinodmapari.aaplasevak.Model.ZoneItem;
import com.vinodmapari.aaplasevak.Model.ZoneResponse;
import com.vinodmapari.aaplasevak.R;
import com.vinodmapari.aaplasevak.VoterSearchItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserSurveyActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    Boolean isDataHave;
    RadioButton selectedRadioButton;
    TextView tv1, tv2;
    ArrayList<SurveyList> surveyLists;
    ArrayList<Colony> colonies;
    CustomAdapter adapter;
    String series_id, status_id, colony_id, row_id, water_supply_id, constituency_id, city_id, zone_id, ward_id, qualification_id, caste_id;
    Button btnSubmit;
    long selected_series_id, selected_colony_id;

    MaterialToolbar toolbar;

    RadioGroup radioGroup;
    SwipeRefreshLayout swipe_refresh;
    SearchableSpinner spinner_series, spinner_status, spinner_colony, spinner_row,
            spinner_water_Supply, spinner_constituency, spinner_zone, spinner_ward,
            spinner_city, spinner_qualification, spinner_caste;

    EditText house_number, voting_center,  name, middle_name, surname, mob1, mob2,
            voterID, adharcard, etBooth, etSerial, etApartment,
            etFlateNumber;
    TextView dob;
    private String colonyName;
    ImageButton imgSearch;

    String nameAdapter, surnameAdapter, middleNameAdapter,
            voterIdAdapter, boothAdapter, sNoAdapter,
            votingCenterAdapter, genderAdapter, epicNoAdapter, dobAdapter,
            constituencyAdapter, cityVillageAdapter, zoneAdapter, prabhagWardAdapter;

    String calling_no,wp_no,House_no,user_name,user_middle_name,user_surname,mobile_no1,mobile_no2,user_dob,gender,
            voterId,user_adharcard,votingcenter,BoothNo,SerialNo,qulification,user_caste,apartment,flateNo;

    int selectedItemCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        // Toast.makeText(this, "Hello...", Toast.LENGTH_SHORT).show();

        house_number = findViewById(R.id.house_number);
        dob = findViewById(R.id.dob);
        name = findViewById(R.id.name);
        middle_name = findViewById(R.id.middle_name);
        surname = findViewById(R.id.surname);
        mob1 = findViewById(R.id.mobile1);
        mob2 = findViewById(R.id.mobile2);
        voterID = findViewById(R.id.voterID);
        adharcard = findViewById(R.id.adharCard);
        btnSubmit = findViewById(R.id.btn_submit);
        radioGroup = findViewById(R.id.radioGroup);
        etBooth = findViewById(R.id.etBoothNo);
        etSerial = findViewById(R.id.etSerialNo);
        etApartment = findViewById(R.id.etApartment);
        etFlateNumber = findViewById(R.id.etFlatNo);
        imgSearch = findViewById(R.id.imgSearch);
        voting_center = findViewById(R.id.voting_center);

        //Spinners
        spinner_series = findViewById(R.id.spinnerSeries);
        spinner_colony = findViewById(R.id.spinner_colony);
        spinner_row = findViewById(R.id.spinner_row);
        spinner_water_Supply = findViewById(R.id.spinner_water_supply);
        spinner_status = findViewById(R.id.spinner_status);

        spinner_constituency = findViewById(R.id.spinnerConstituency);
        spinner_city = findViewById(R.id.spinnerCity_Village);
        spinner_zone = findViewById(R.id.spinnerZone);
        spinner_ward = findViewById(R.id.spinnerPrabhag_Ward);
        spinner_qualification = findViewById(R.id.spinner_qualification);
        spinner_caste = findViewById(R.id.spinner_caste);


        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //--------------------------------------------------------

        dob.setText("DOB");

        //isDataHave = false;
        //Here get the data

       /* Intent intent = getIntent();
        if (intent != null) {
            isDataHave = intent.getBooleanExtra("isDataHave", true);
            if (isDataHave) {
                nameAdapter = intent.getStringExtra("name");
                surnameAdapter = intent.getStringExtra("surname");
                middleNameAdapter = intent.getStringExtra("middlename");
                voterIdAdapter = intent.getStringExtra("voterId");
                boothAdapter = intent.getStringExtra("boothNo");
                sNoAdapter = intent.getStringExtra("sNo");
                votingCenterAdapter = intent.getStringExtra("votingCenter");
                genderAdapter = intent.getStringExtra("gender");
                epicNoAdapter = intent.getStringExtra("epicNo");
                constituencyAdapter = intent.getStringExtra("constituency");
                cityVillageAdapter = intent.getStringExtra("cityVillage");
                zoneAdapter = intent.getStringExtra("zone");
                prabhagWardAdapter = intent.getStringExtra("prabhagWard");

                dobAdapter = intent.getStringExtra("dob");
                if (dobAdapter != null && !dobAdapter.isEmpty()) {
                    dob.setText(dobAdapter);
                } else {
                    dob.setText("DOB");
                }

                //Toast.makeText(this, "Data: " + nameAdapter + " " + surnameAdapter + " " + middleNameAdapter, Toast.LENGTH_SHORT).show();

                // Set the retrieved values to your UI elements if needed
                name.setText(nameAdapter);
                middle_name.setText(middleNameAdapter);
                surname.setText(surnameAdapter);
                voterID.setText(voterIdAdapter);
                etBooth.setText(boothAdapter);
                etSerial.setText(sNoAdapter);
                voting_center.setText(votingCenterAdapter);
                voterID.setText(epicNoAdapter);

                //spinner_constituency.setSelection(Integer.parseInt(constituencyAdapter));
                //fetchConstituencies();
                //spinner_city.setSelection(Integer.parseInt(cityVillageAdapter));
                //spinner_zone.setSelection(Integer.parseInt(cityVillageAdapter));
                //spinner_ward.setSelection(Integer.parseInt(prabhagWardAdapter));
                constituency_id = constituencyAdapter;
                city_id = cityVillageAdapter;
                zone_id = zoneAdapter;
                ward_id = prabhagWardAdapter;
                //dob.setText(dobAdapter);

                fetchConstituencies();
                fetchCityVillages();
                fetchZones();
                fetchPrabhagWards();


                // Handle gender radio button selection if needed
                if (genderAdapter != null && !genderAdapter.isEmpty()) {
                    if (genderAdapter.equalsIgnoreCase("Male")) {
                        radioGroup.check(R.id.radioButton);
                    } else if (genderAdapter.equalsIgnoreCase("Female")) {
                        radioGroup.check(R.id.radioButton2);
                    } else {
                        radioGroup.check(R.id.radioButton3);
                    }
                }
            }
        }*/

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("selectedItems") ) {
            ArrayList<VoterSearchItem> selectedItems = intent.getParcelableArrayListExtra("selectedItems");
            selectedItemCount = intent.getIntExtra("selectedItemCount",0);
            if (selectedItems != null) {
                // Handle the list of selected items
                //displaySelectedItems(selectedItems);
                Toast.makeText(this, "Data: " + selectedItems, Toast.LENGTH_SHORT).show();
                Log.d("Api Response","Data: " + selectedItems);
            } else {
                Toast.makeText(this, "No selected items found", Toast.LENGTH_SHORT).show();
            }
        }


        // -------------------------------------------------------------------

        surveyLists = new ArrayList<>();
        colonies = new ArrayList<>();

        adapter = new CustomAdapter(UserSurveyActivity.this, colonies);


        spinner_constituency.setTitle("Constituency");
        spinner_zone.setTitle("Zone");
        spinner_ward.setTitle("Prabhag/Ward");
        spinner_city.setTitle("City/Village");
        spinner_qualification.setTitle("Qualification");


        swipe_refresh = findViewById(R.id.swipe_refresh);
        swipe_refresh.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.colorWhite));
        int col = getResources().getColor(R.color.colorAccent);
        swipe_refresh.setColorSchemeColors(col, col, col);
        swipe_refresh.setOnRefreshListener(this);


        //getSeriesList();
        fetchSeries();
        fetchColony("0");
        fetchRow("0", "0");
        fetchWaterSupplyId();
        fetchStatus();
        fetchConstituencies();
        fetchCityVillages();
        fetchZones();
        fetchPrabhagWards();
        fetchQualification();
        fetchCaste();


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

        // ---- Toast.makeText(UserSurveyActivity.this, "Selected Radio Button is:" + gender , Toast.LENGTH_LONG).show();


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 calling_no = mob1.getText().toString();
                 wp_no = mob2.getText().toString();
                 House_no = house_number.getText().toString();

                 user_name = name.getText().toString();
                 user_middle_name = middle_name.getText().toString();
                 user_surname = surname.getText().toString();


                 mobile_no1 = calling_no;
                 mobile_no2 = wp_no;
                 user_dob = dob.getText().toString();

                 voterId = voterID.getText().toString();
                 user_adharcard = adharcard.getText().toString();
                 votingcenter = voting_center.getText().toString();
                 BoothNo = etBooth.getText().toString();
                 SerialNo = etSerial.getText().toString();
                 qulification = spinner_qualification.getSelectedItem().toString();
                 user_caste = spinner_caste.getSelectedItem().toString();
                 apartment = etApartment.getText().toString();
                 flateNo = etFlateNumber.getText().toString();


                if (    user_name.equals("")
                        || user_middle_name.equals("")
                        || user_surname.equals("")
                        || gender.equals("")
                        || House_no.equals("")
                        //|| voterId.equals("")
                        //|| votingcenter.equals("")
                        //|| user_dob.isEmpty()
                        //|| user_adharcard.equals("")
                        //|| BoothNo.equals("")
                        //|| SerialNo.equals("")

                ) {
                    Toast.makeText(UserSurveyActivity.this, "some fields are empty", Toast.LENGTH_SHORT).show();
                } else if (spinner_series.getSelectedItem().toString().equalsIgnoreCase("Select Series")) {
                    Toast.makeText(UserSurveyActivity.this, "Please Select Series", Toast.LENGTH_SHORT).show();
                } else if (spinner_status.getSelectedItem().toString().equalsIgnoreCase("Select Status")) {
                    Toast.makeText(UserSurveyActivity.this, "Please Select Status", Toast.LENGTH_SHORT).show();
                }/* else if (spinner_row.getSelectedItem().toString().equalsIgnoreCase("Select Row")) {
                    Toast.makeText(UserSurveyActivity.this, "Please Select Row", Toast.LENGTH_SHORT).show();
                }*/ else if (spinner_colony.getSelectedItem().toString().equalsIgnoreCase("Select Colony")) {
                    Toast.makeText(UserSurveyActivity.this, "Please Select Colony", Toast.LENGTH_SHORT).show();
                }/* else if (spinner_water_Supply.getSelectedItem().toString().equalsIgnoreCase("Select WaterSupply")) {
                    Toast.makeText(UserSurveyActivity.this, "Please Select Water Supply", Toast.LENGTH_SHORT).show();
                }*/ else if (spinner_constituency.getSelectedItem().toString().equalsIgnoreCase("Select Constituency")) {
                    Toast.makeText(UserSurveyActivity.this, "Please Select Constituency", Toast.LENGTH_SHORT).show();
                } else if (spinner_city.getSelectedItem().toString().equalsIgnoreCase("Select City/Village")) {
                    Toast.makeText(UserSurveyActivity.this, "Please Select City/Village", Toast.LENGTH_SHORT).show();
                } else if (spinner_zone.getSelectedItem().toString().equalsIgnoreCase("Select Zone")) {
                    Toast.makeText(UserSurveyActivity.this, "Please Select Zone", Toast.LENGTH_SHORT).show();
                } else if (spinner_ward.getSelectedItem().toString().equalsIgnoreCase("Select Prabhag/Ward")) {
                    Toast.makeText(UserSurveyActivity.this, "Please Select Ward", Toast.LENGTH_SHORT).show();
                } else if (spinner_qualification.getSelectedItem().toString().equalsIgnoreCase("Select Qualification")) {
                    Toast.makeText(UserSurveyActivity.this, "Please Select Qualification", Toast.LENGTH_SHORT).show();
                } else if (spinner_caste.getSelectedItem().toString().equalsIgnoreCase("Select Caste")) {
                    Toast.makeText(UserSurveyActivity.this, "Please Select Caste", Toast.LENGTH_SHORT).show();
                } else {
                    addSurvey(House_no, series_id, colony_id, row_id, gender, user_name, user_middle_name, user_surname, mobile_no1, mobile_no2,
                            user_dob, qulification, user_caste, status_id, voterId, user_adharcard, water_supply_id, votingcenter,
                            BoothNo, SerialNo, constituency_id, city_id, zone_id, ward_id, apartment, flateNo);
                    // Toast.makeText(UserSurveyActivity.this, "new family added successfully", Toast.LENGTH_SHORT).show();
                    // finish();
                }
            }
        });



        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(UserSurveyActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                dob.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });


        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserSurveyActivity.this, SearchSurveyMemberActivity.class);
                startActivity(intent);
            }
        });
    }


    //------------------------------------------------------------------------------ Instance Saved-----------------------------------------
    @Override
    protected void onSaveInstanceState( Bundle outState) {
        // put string value
        outState.putString("constituency",constituency_id);
        outState.putString("city",city_id);
        outState.putString("zone",zone_id);
        outState.putString("ward",ward_id);
        outState.putString("series",series_id);
        outState.putString("colony",colony_id);
        outState.putString("row",row_id);
        outState.putString("houseNum",House_no);
        outState.putString("status",status_id);
        outState.putString("apartment",apartment);
        outState.putString("flateNum",flateNo);
        outState.putString("gender",gender);
        outState.putString("name",user_name);
        outState.putString("middleName",user_middle_name);
        outState.putString("surname",user_surname);
        outState.putString("whatNumber",mobile_no1);
        outState.putString("callingNumber",mobile_no2);
        outState.putString("dob",user_dob);
        outState.putString("qualification",qulification);
        outState.putString("caste",user_caste);
        outState.putString("adharCard",user_adharcard);
        outState.putString("epicNo",voterId);
        outState.putString("boothNo",BoothNo);
        outState.putString("serialNo",SerialNo);
        outState.putString("votingCenter",votingcenter);
        outState.putString("waterSupply",water_supply_id);
        super.onSaveInstanceState(outState);
    }

    // --------------------------------------- Restore the instance ---------------------------------

    @Override
    protected void onRestoreInstanceState( Bundle savedInstanceState) {
        // get values from saved state
        constituency_id = savedInstanceState.getString("constituency", "");
        city_id = savedInstanceState.getString("city", "");
        zone_id = savedInstanceState.getString("zone", "");
        ward_id = savedInstanceState.getString("ward", "");
        series_id = savedInstanceState.getString("series", "");
        colony_id = savedInstanceState.getString("colony", "");
        row_id = savedInstanceState.getString("row", "");
        House_no = savedInstanceState.getString("houseNum", "");
        status_id = savedInstanceState.getString("status", "");
        apartment = savedInstanceState.getString("apartment", "");
        flateNo = savedInstanceState.getString("flateNum", "");
        gender = savedInstanceState.getString("gender", "");
        user_name = savedInstanceState.getString("name", "");
        user_middle_name = savedInstanceState.getString("middleName", "");
        user_surname = savedInstanceState.getString("surname", "");
        mobile_no1 = savedInstanceState.getString("whatNumber", "");
        mobile_no2 = savedInstanceState.getString("callingNumber", "");
        user_dob = savedInstanceState.getString("dob", "");
        qulification = savedInstanceState.getString("qualification", "");
        user_caste = savedInstanceState.getString("caste", "");
        user_adharcard = savedInstanceState.getString("adharCard", "");
        voterId = savedInstanceState.getString("epicNo", "");
        BoothNo = savedInstanceState.getString("boothNo", "");
        SerialNo = savedInstanceState.getString("serialNo", "");
        votingcenter = savedInstanceState.getString("votingCenter", "");
        water_supply_id = savedInstanceState.getString("waterSupply", "");
        // display toast
        //Toast.makeText(getApplicationContext(),string+" - "+ aBoolean+" - "+anInt,Toast.LENGTH_SHORT).show();
        super.onRestoreInstanceState(savedInstanceState);
    }


    // ---------------------------------------------------------------------------------- on Create end here --------------------------------------------------------------------------------


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
                    seriesNames.add("Series*");

                    //List<String> seriesId = new ArrayList<>();
                    //seriesId.add("0");

                    for (SeriesItem item : seriesItems) {
                        seriesNames.add(item.getSeriesName());
                        seriesIdMap.put(item.getSeriesName(), item.getId());
                    }

                    // Populate the spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(UserSurveyActivity.this,
                            android.R.layout.simple_spinner_item, seriesNames);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_series.setAdapter(adapter);

                    // Handle spinner item selection
                    spinner_series.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String selectedSeries = (String) parent.getItemAtPosition(position);
                            if (!selectedSeries.equals("Series*")) {
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
                Toast.makeText(UserSurveyActivity.this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("API Error", "Error fetching prabhag wards: " + throwable.getMessage());
            }
        });
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
                    colonyNames.add("Colony*");

                    for (ColonyItem item : colonyItems) {
                        colonyNames.add(item.getColonyName());
                        colonyIdMap.put(item.getColonyName(), item.getId());
                    }

                    // Populate the spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(UserSurveyActivity.this,
                            android.R.layout.simple_spinner_item, colonyNames);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_colony.setAdapter(adapter);

                    // Handle spinner item selection
                    spinner_colony.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String selectedColony = (String) parent.getItemAtPosition(position);
                            if (!selectedColony.equals("Colony*")) {
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
                    Toast.makeText(UserSurveyActivity.this, "Response Not Success: " + response.code(), Toast.LENGTH_SHORT).show();
                    Log.e("API Error", "Response Not Success: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ColonyResponse> call, Throwable throwable) {
                Toast.makeText(UserSurveyActivity.this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("API Error", "Error fetching prabhag wards: " + throwable.getMessage());
            }
        });
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
                    rowNames.add("Row");

                    for (RowItem item : rowItems) {
                        rowNames.add(item.getRowName());
                        rowIdMap.put(item.getRowName(), item.getId());
                    }


                    // Populate the spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(UserSurveyActivity.this,
                            android.R.layout.simple_spinner_item, rowNames);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_row.setAdapter(adapter);

                    // Handle spinner item selection
                    spinner_row.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String selectedRows = (String) parent.getItemAtPosition(position);
                            if (!selectedRows.equals("Row")) {
                                //fetchColony();
                                String selectedRowId = rowIdMap.get(selectedRows);
                                row_id = selectedRowId;

                                //Toast.makeText(UserSurveyActivity.this, "S: " + series_id + " C: " + colony_id + " R: " + row_id, Toast.LENGTH_SHORT).show();

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
                Toast.makeText(UserSurveyActivity.this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("API Error", "Error fetching prabhag wards: " + throwable.getMessage());
            }
        });
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
                    waterSupplyNames.add("Water Supply");

                    for (WaterSupplyItem item : waterSupplyItems) {
                        waterSupplyNames.add(item.getSlotName());
                        waterSupplyIdMap.put(item.getSlotName(), item.getId());
                    }


                    // Populate the spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(UserSurveyActivity.this,
                            android.R.layout.simple_spinner_item, waterSupplyNames);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_water_Supply.setAdapter(adapter);

                    // Handle spinner item selection
                    spinner_water_Supply.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String selectedWaterSupply = (String) parent.getItemAtPosition(position);
                            if (!selectedWaterSupply.equals("Water Supply")) {
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
                Toast.makeText(UserSurveyActivity.this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("API Error", "Error fetching prabhag wards: " + throwable.getMessage());
            }
        });
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
                    statusNames.add("Status*");

                    for (StatusItem item : statusItems) {
                        statusNames.add(item.getStatusName());
                        statusIdMap.put(item.getStatusName(), item.getId());
                    }

                    // Populate the spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(UserSurveyActivity.this,
                            android.R.layout.simple_spinner_item, statusNames);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_status.setAdapter(adapter);

                    // Handle spinner item selection
                    spinner_status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String selectedStatus = (String) parent.getItemAtPosition(position);
                            if (!selectedStatus.equals("Status*")) {
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
                Toast.makeText(UserSurveyActivity.this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("API Error", "Error fetching prabhag wards: " + throwable.getMessage());
            }
        });

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
                    final Map<String, String> constituencyIdMap = new HashMap<>();
                    constituencyName.add("Constituency*");

                    for (ConstituencyItem constituency : constituencies) {
                        constituencyName.add(constituency.getConstituencyName());
                        constituencyIdMap.put(constituency.getConstituencyName(), constituency.getId());
                    }
                    // Populate the spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(UserSurveyActivity.this, android.R.layout.simple_spinner_item, constituencyName);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_constituency.setAdapter(adapter);

                    // for predefault value
                    String selectedConstituencyId = constituency_id;
                    if (selectedConstituencyId != null) {
                        String selectedConstituencyName = getConstituencyById(constituencyIdMap, selectedConstituencyId);
                        if (selectedConstituencyName != null) {
                            int position = adapter.getPosition(selectedConstituencyName);
                            if (position >= 0) {
                                spinner_constituency.setSelection(position);
                            }
                        }
                    }

                    spinner_constituency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String selectedConstituency = (String) parent.getItemAtPosition(position);
                            if (!selectedConstituency.equals("Constituency")) {
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
                Toast.makeText(UserSurveyActivity.this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("API Error", "Error fetching prabhag wards: " + throwable.getMessage());
            }
        });
    }
    private String getConstituencyById(Map<String, String> constituencyIdMap, String constituencyId) {
        for (Map.Entry<String, String> entry : constituencyIdMap.entrySet()) {
            if (entry.getValue().equals(constituencyId)) {
                return entry.getKey();
            }
        }
        return null;
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
                    cityVillageNames.add("City/Village*");

                    for (CityVillageItem item : cityVillages) {
                        cityVillageNames.add(item.getCityVillageName());
                        cityVillageIdMap.put(item.getCityVillageName(), item.getId());
                    }

                    // Populate the spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(UserSurveyActivity.this,
                            android.R.layout.simple_spinner_item, cityVillageNames);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_city.setAdapter(adapter);

                    // Handle spinner item selection
                    // for predefault value
                    String selectedCityId = city_id;
                    if (selectedCityId != null) {
                        String selectedCityName = getCityById(cityVillageIdMap, selectedCityId);
                        if (selectedCityName != null) {
                            int position = adapter.getPosition(selectedCityName);
                            if (position >= 0) {
                                spinner_city.setSelection(position);
                            }
                        }
                    }


                    spinner_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String selectedCityVillage = (String) parent.getItemAtPosition(position);
                            if (!selectedCityVillage.equals("City/Village")) {

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
                Toast.makeText(UserSurveyActivity.this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("API Error", "Error fetching prabhag wards: " + throwable.getMessage());
            }
        });
    }

    private String getCityById(Map<String, String> cityIdMap, String cityId) {
        for (Map.Entry<String, String> entry : cityIdMap.entrySet()) {
            if (entry.getValue().equals(cityId)) {
                return entry.getKey();
            }
        }
        return null;
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
                    zoneNames.add("Zone*");

                    for (ZoneItem zone : zones) {
                        zoneNames.add(zone.getZoneName());
                        zoneIdMap.put(zone.getZoneName(), zone.getId());
                    }

                    // Populate the spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(UserSurveyActivity.this,
                            android.R.layout.simple_spinner_item, zoneNames);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_zone.setAdapter(adapter);

                    String selectedZoneId = zone_id;
                    if (selectedZoneId != null) {
                        String selectedZoneName = getZoneById(zoneIdMap, selectedZoneId);
                        if (selectedZoneName != null) {
                            int position = adapter.getPosition(selectedZoneName);
                            if (position >= 0) {
                                spinner_zone.setSelection(position);
                            }
                        }
                    }


                    // Handle spinner item selection
                    spinner_zone.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String selectedZone = (String) parent.getItemAtPosition(position);
                            if (!selectedZone.equals("Zone")) {
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
                Toast.makeText(UserSurveyActivity.this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("API Error", "Error fetching prabhag wards: " + throwable.getMessage());
            }
        });

    }

    private String getZoneById(Map<String, String> zoneIdMap, String zoneId) {
        for (Map.Entry<String, String> entry : zoneIdMap.entrySet()) {
            if (entry.getValue().equals(zoneId)) {
                return entry.getKey();
            }
        }
        return null;
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
                    prabhagWardNames.add("Prabhag/Ward*");

                    for (PrabhagWardItem prabhagWard : prabhagWards) {
                        prabhagWardNames.add(prabhagWard.getPrabhagWardName());
                        prabhagWardIdMap.put(prabhagWard.getPrabhagWardName(), prabhagWard.getId());
                    }

                    // Populate the spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(UserSurveyActivity.this,
                            android.R.layout.simple_spinner_item, prabhagWardNames);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_ward.setAdapter(adapter);

                    String selectedWardId = ward_id;
                    if (selectedWardId != null) {
                        String selectedWardName = getWardById(prabhagWardIdMap, selectedWardId);
                        if (selectedWardName != null) {
                            int position = adapter.getPosition(selectedWardName);
                            if (position >= 0) {
                                spinner_ward.setSelection(position);
                            }
                        }
                    }

                    // Handle spinner item selection
                    spinner_ward.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String selectedPrabhagWard = (String) parent.getItemAtPosition(position);
                            if (!selectedPrabhagWard.equals("Prabhag/Ward")) {
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
                Toast.makeText(UserSurveyActivity.this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("API Error", "Error fetching prabhag wards: " + throwable.getMessage());
            }
        });

    }

    private String getWardById(Map<String, String> wardIdMap, String wardId) {
        for (Map.Entry<String, String> entry : wardIdMap.entrySet()) {
            if (entry.getValue().equals(wardId)) {
                return entry.getKey();
            }
        }
        return null;
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
                    qualificationNames.add("Qualification*");

                    for (QualificationItem qualification : qualificationResponse) {
                        qualificationNames.add(qualification.getQualificationName());
                        qualificationIdMap.put(qualification.getQualificationName(), qualification.getId());
                    }

                    // Populate the spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(UserSurveyActivity.this,
                            android.R.layout.simple_spinner_item, qualificationNames);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_qualification.setAdapter(adapter);

                    // Handle spinner item selection
                    spinner_qualification.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String selectedQualification = (String) parent.getItemAtPosition(position);
                            if (!selectedQualification.equals("Qualification")) {
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
                    Toast.makeText(UserSurveyActivity.this, "Response Error..!!", Toast.LENGTH_SHORT).show();
                    Log.e("Api Response", "Response Error..");
                }
            }

            @Override
            public void onFailure(Call<QualificationsResponse> call, Throwable throwable) {
                Log.e("Api Response", "Error.." + throwable.getLocalizedMessage());
                Toast.makeText(UserSurveyActivity.this, "Error.." + throwable.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
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
                    casteNames.add("Caste*");

                    for (CasteItem caste : casteResponses) {
                        casteNames.add(caste.getCasteName());
                        casteIdMap.put(caste.getCasteName(), caste.getId());
                    }

                    // Populate the spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(UserSurveyActivity.this,
                            android.R.layout.simple_spinner_item, casteNames);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_caste.setAdapter(adapter);

                    // Handle spinner item selection
                    spinner_caste.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String selectedCaste = (String) parent.getItemAtPosition(position);
                            if (!selectedCaste.equals("Caste*")) {
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
                    Toast.makeText(UserSurveyActivity.this, "Response Error..!!", Toast.LENGTH_SHORT).show();
                    Log.e("Api Response", "Response Error..");
                }
            }

            @Override
            public void onFailure(Call<CasteResponse> call, Throwable throwable) {
                Log.e("Api Response", "Error.." + throwable.getLocalizedMessage());
                Toast.makeText(UserSurveyActivity.this, "Error.." + throwable.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void addSurvey(String house_no, String series_id, String colony_id, String row_id, String gender, String name, String middle_name,
                          String surname, String mobile1, String mobile2, String dob, String qualification_id, String caste_id, String status_id,
                          String voter_id, String adhar_card, String watersupply_id, String voting_center, String BoothNo, String SerialNo,
                          String constituency_id, String city_id, String zone_id, String ward_id, String apartment, String flate) {

        ApiInterface apiInterface = getRetrofitInstance().create(ApiInterface.class);

        AddSurveyBody addSurveyBody = new AddSurveyBody(house_no, series_id, colony_id, row_id, gender, name, middle_name, surname, mobile1, mobile2, dob, qualification_id, caste_id, status_id, voter_id, adhar_card,
                watersupply_id, voting_center, BoothNo, SerialNo, apartment, flate, constituency_id, city_id, zone_id, ward_id);

        Call<AddSurveyResponseData> call = apiInterface.addSurvey(addSurveyBody);

        call.enqueue(new Callback<AddSurveyResponseData>() {
            @Override
            public void onResponse(Call<AddSurveyResponseData> call, retrofit2.Response<AddSurveyResponseData> response) {
                if (response.isSuccessful()) {

                    AddSurveyResponseData responseData = response.body();
                    String status = responseData.getStatus();
                    String message = responseData.getMessage();

                    Toast.makeText(UserSurveyActivity.this, "Message: " + message + " Status: " + status, Toast.LENGTH_SHORT).show();
                    finish();

                   /* if(status == "error"){
                        Toast.makeText(UserSurveyActivity.this, "Message: " + message + " Status: " + status, Toast.LENGTH_SHORT).show();
                    }
                    else if(status == "success"){
                        Toast.makeText(UserSurveyActivity.this, "Message: " + message, Toast.LENGTH_SHORT).show();
                        finish();
                    }*/

                } else {
                    Toast.makeText(UserSurveyActivity.this, "Response Error..!!", Toast.LENGTH_SHORT).show();
                    Log.e("Api Response", "Response Error..");
                }
            }

            @Override
            public void onFailure(Call<AddSurveyResponseData> call, Throwable throwable) {
                Log.e("Api Response", "Error.." + throwable.getLocalizedMessage());
                Toast.makeText(UserSurveyActivity.this, "Error.." + throwable.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }


    @Override
    protected void onResume() {

        /*if (Method.haveNetworkConnection(this)) {
            if (SharedPref.getAppStatus(this).equalsIgnoreCase("on")) {

                //getSeriesList();
                //getColonyList(series_id);

            } else {
                swipe_refresh.setRefreshing(false);
            }
        } else {
            swipe_refresh.setRefreshing(false);
        }*/
        super.onResume();
    }


   /* @Override
    protected void onStop() {
        Intent intent = new Intent(UserSurveyActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
        super.onStop();
    }*/

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
