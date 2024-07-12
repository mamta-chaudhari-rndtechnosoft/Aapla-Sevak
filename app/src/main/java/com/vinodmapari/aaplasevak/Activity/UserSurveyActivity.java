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
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import com.vinodmapari.aaplasevak.ApiConfig.ApiHandler;
import com.vinodmapari.aaplasevak.ApiConfig.ApiInterface;
import com.vinodmapari.aaplasevak.CustomAdapter;
import com.vinodmapari.aaplasevak.Model.CityVillageItem;
import com.vinodmapari.aaplasevak.Model.CityVillageResponse;
import com.vinodmapari.aaplasevak.Model.Colony;
import com.vinodmapari.aaplasevak.Model.Constants;
import com.vinodmapari.aaplasevak.Model.ConstituencyItem;
import com.vinodmapari.aaplasevak.Model.ConstituencyResponse;
import com.vinodmapari.aaplasevak.Model.HouseResponse;
import com.vinodmapari.aaplasevak.Model.Method;
import com.vinodmapari.aaplasevak.Model.PrabhagWardItem;
import com.vinodmapari.aaplasevak.Model.PrabhagWardResponse;
import com.vinodmapari.aaplasevak.Model.Row;
import com.vinodmapari.aaplasevak.Model.Series;
import com.vinodmapari.aaplasevak.Model.SharedPref;
import com.vinodmapari.aaplasevak.Model.SurveyList;
import com.vinodmapari.aaplasevak.Model.ZoneItem;
import com.vinodmapari.aaplasevak.Model.ZoneResponse;
import com.vinodmapari.aaplasevak.R;

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

public class UserSurveyActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    Boolean isDataHave;
    String colony, row, waterSupply, series;
    RadioButton selectedRadioButton;
    TextView tv1, tv2;
    ArrayList<SurveyList> surveyLists;
    ArrayList<Colony> colonies;
    CustomAdapter adapter;
    int selected_series, selected_status, selected_colony, selected_row, selected_water_supply, selected_constituency, selected_zone, selected_ward, selected_city_village;
    String series_id, status_id, colony_id, row_id, water_supply_id, constituency_id, city_id, zone_id, ward_id;
    Button btnSubmit;
    long selected_series_id, selected_colony_id;
    String gender;
    RadioGroup radioGroup;
    SwipeRefreshLayout swipe_refresh;
    SearchableSpinner spinner_series, spinner_status, spinner_colony, spinner_row, spinner_water_Supply, spinner_constituency, spinner_zone, spinner_ward, spinner_city;
    EditText house_number, voting_center, dob, name, middle_name, surname, mob1, mob2, qualification, caste, voterID, adharcard, etBooth, etSerial, etApartment, etFlateNumber;
    private String colonyName;
    ImageButton imgSearch;

    String nameAdapter,surnameAdapter,middleNameAdapter,voterIdAdapter,boothAdapter,sNoAdapter,votingCenterAdapter,genderAdapter;

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
        qualification = findViewById(R.id.qualification);
        caste = findViewById(R.id.caste);
        voterID = findViewById(R.id.voterID);
        adharcard = findViewById(R.id.adharCard);
        btnSubmit = findViewById(R.id.btn_submit);
        spinner_series = findViewById(R.id.spinnerSeries);
        spinner_colony = findViewById(R.id.spinner_colony);
        spinner_row = findViewById(R.id.spinner_row);
        spinner_water_Supply = findViewById(R.id.spinner_water_supply);
        spinner_status = findViewById(R.id.spinner_status);
        voting_center = findViewById(R.id.voting_center);
        radioGroup = findViewById(R.id.radioGroup);
        etBooth = findViewById(R.id.etBoothNo);
        etSerial = findViewById(R.id.etSerialNo);
        etApartment = findViewById(R.id.etApartment);
        etFlateNumber = findViewById(R.id.etFlatNo);
        imgSearch = findViewById(R.id.imgSearch);

        //Spinners
        spinner_constituency = findViewById(R.id.spinnerConstituency);
        spinner_city = findViewById(R.id.spinnerCity_Village);
        spinner_zone = findViewById(R.id.spinnerZone);
        spinner_ward = findViewById(R.id.spinnerPrabhag_Ward);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        toolbar.setTitle(Html.fromHtml("<b>" + "Add Family" + "</b>"));
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // -------------------------------------------------------

        //isDataHave = false;
        //Here get the data

        Intent intent = getIntent();
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

                //Toast.makeText(this, "Data: " + nameAdapter + " " + surnameAdapter + " " + middleNameAdapter, Toast.LENGTH_SHORT).show();

                // Use the data as needed
                /*nameAdapter = name;
                surnameAdapter = surname;
                middleNameAdapter = middleName;
                voterIdAdapter = voterId;
                boothAdapter = boothNo;
                sNoAdapter = sNo;
                votingCenterAdapter = votingCenter;
                genderAdapter = gender;*/

                // Set the retrieved values to your UI elements if needed
                name.setText(nameAdapter);
                middle_name.setText(middleNameAdapter);
                surname.setText(surnameAdapter);
                voterID.setText(voterIdAdapter);
                etBooth.setText(boothAdapter);
                etSerial.setText(sNoAdapter);
                voting_center.setText(votingCenterAdapter);

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
        }



        //----------------------------------------------------

        surveyLists = new ArrayList<>();
        colonies = new ArrayList<>();

        adapter = new CustomAdapter(UserSurveyActivity.this, colonies);


        spinner_constituency.setTitle("Select Constituency");
        spinner_zone.setTitle("Select Zone");
        spinner_ward.setTitle("Select Prabhag /Ward");
        spinner_city.setTitle("Select City/Village");


        swipe_refresh = findViewById(R.id.swipe_refresh);
        swipe_refresh.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.colorWhite));
        int col = getResources().getColor(R.color.colorAccent);
        swipe_refresh.setColorSchemeColors(col, col, col);
        swipe_refresh.setOnRefreshListener(this);


        fetchConstituencies();
        getSeriesList();
        fetchCityVillages();
        fetchZones();
        fetchPrabhagWards();



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

        //Toast.makeText(UserSurveyActivity.this, "Selected Radio Button is:" + gender , Toast.LENGTH_LONG).show();


        series_name.clear();
        series_name = new ArrayList<>();

        for (int i = 0; i < Constants.series.size(); i++) {
            Constants.series_name.add(Constants.series.get(i).getSeries_name());
            if (series_name != null && series_name.equals(Constants.series.get(i).getSeries_name())) {
                //here add 1
                selected_series = i ;
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




        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String calling_no = mob1.getText().toString();
                String wp_no = mob2.getText().toString();
                String house_no = house_number.getText().toString();
                String user_name = name.getText().toString();
                String user_middle_name = middle_name.getText().toString();
                String user_surname = surname.getText().toString();
                String mobile_no1 = calling_no;
                String mobile_no2 = wp_no;
                String user_dob = dob.getText().toString();
                String user_qualification = qualification.getText().toString();
                String user_caste = caste.getText().toString();
                String voterId = voterID.getText().toString();
                String user_adharcard = adharcard.getText().toString();
                String votingcenter = voting_center.getText().toString();
                String BoothNo = etBooth.getText().toString();
                String SerialNo = etSerial.getText().toString();
                String constituency = spinner_constituency.getSelectedItem().toString();
                String city = spinner_city.getSelectedItem().toString();
                String zone = spinner_zone.getSelectedItem().toString();
                String ward = spinner_ward.getSelectedItem().toString();
                String apartment = etApartment.getText().toString();
                String flateNo = etFlateNumber.getText().toString();



                if (house_no.equals("") || user_name.equals("")
                        || user_middle_name.equals("") || user_surname.equals("")
                        || mobile_no1.length() == 0 || mobile_no2.length() == 0
                        || user_dob.isEmpty() || user_qualification.equals("") || user_caste.equals("")
                        || votingcenter.equals("")
                        || voterId.equals("")
                        || user_adharcard.equals("")
                        || gender.equals("")
                        || BoothNo.equals("")
                        || SerialNo.equals("")
                        || apartment.equals("")
                        || flateNo.equals("")
                ) {
                    Toast.makeText(UserSurveyActivity.this, "some fields are empty", Toast.LENGTH_SHORT).show();
                } else if (series_id == null && series_id.equalsIgnoreCase("")) {
                    Toast.makeText(UserSurveyActivity.this, "Please Select Series", Toast.LENGTH_SHORT).show();
                } else if (status_id == null && status_id.equalsIgnoreCase("")) {
                    Toast.makeText(UserSurveyActivity.this, "Please Select Status", Toast.LENGTH_SHORT).show();
                } else if (row_id == null && row_id.equalsIgnoreCase("")) {
                    Toast.makeText(UserSurveyActivity.this, "Please Select Status", Toast.LENGTH_SHORT).show();
                } else if (colony_id == null && colony_id.equalsIgnoreCase("")) {
                    Toast.makeText(UserSurveyActivity.this, "Please Select Status", Toast.LENGTH_SHORT).show();
                } else if (water_supply_id == null && water_supply_id.equalsIgnoreCase("")) {
                    Toast.makeText(UserSurveyActivity.this, "Please Select Status", Toast.LENGTH_SHORT).show();
                }
                else if(spinner_constituency.getId() == 0){
                    Toast.makeText(UserSurveyActivity.this, "Please Select Constituency", Toast.LENGTH_SHORT).show();
                }
                else if(spinner_city.getId() == 0){
                    Toast.makeText(UserSurveyActivity.this, "Please Select City/Village", Toast.LENGTH_SHORT).show();
                }
                else if(spinner_zone.getId() == 0){
                    Toast.makeText(UserSurveyActivity.this, "Please Select Zone", Toast.LENGTH_SHORT).show();
                }
                else if(spinner_ward.getId() == 0){
                    Toast.makeText(UserSurveyActivity.this, "Please Select Ward", Toast.LENGTH_SHORT).show();
                }

                else {
                    addSurvey(house_no, series_id, colony_id, row_id, gender, user_name, user_middle_name, user_surname, mobile_no1, mobile_no2, user_dob, user_qualification, user_caste, status_id, voterId, user_adharcard, water_supply_id, votingcenter, BoothNo, SerialNo,constituency,city,zone,ward,apartment,flateNo);
                    Toast.makeText(UserSurveyActivity.this, "new family added successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        });


        spinner_series.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                series_id = Constants.series.get(position).getId() ;
                selected_series_id = spinner_series.getSelectedItemId();

                if (selected_series_id != 0) {
                    getColonyList(selected_series_id);

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
                        // Toast.makeText(UserSurveyActivity.this, "colony_id= "+colony_id, Toast.LENGTH_SHORT).show();
                    }
                    //           colony_id = Constants.colonies.get(position).getId();


                    if (selected_colony_id != 0) {


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
                Intent intent = new Intent(UserSurveyActivity.this,SearchSurveyMemberActivity.class);
                startActivity(intent);
            }
        });


    }




    // ---------------------------------------------------------------------------------- on Create end here --------------------------------------------------------------------------------


    private void fetchConstituencies() {
        // Clear the list and add the title
        //Constants.constituency_name.clear();
        //Constants.constituency_name.add("Select a Constituency");
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
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(UserSurveyActivity.this, android.R.layout.simple_spinner_item, constituencyName);
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
                Toast.makeText(UserSurveyActivity.this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
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
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(UserSurveyActivity.this,
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
                Toast.makeText(UserSurveyActivity.this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
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
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(UserSurveyActivity.this,
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
                Toast.makeText(UserSurveyActivity.this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
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
                    prabhagWardNames.add("Select Prabhag Ward");

                    for (PrabhagWardItem prabhagWard : prabhagWards) {
                        prabhagWardNames.add(prabhagWard.getPrabhagWardName());
                    }

                    // Populate the spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(UserSurveyActivity.this,
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
                Toast.makeText(UserSurveyActivity.this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("API Error", "Error fetching prabhag wards: " + throwable.getMessage());
            }
        });

    }



    public void addSurvey(String house_no,
                          String series_id,
                          String colony_id,
                          String row_id,
                          String gender,
                          String name,
                          String middle_name,
                          String surname,
                          String mobile1,
                          String mobile2,
                          String dob,
                          String qualification,
                          String caste,
                          String status_id,
                          String voter_id,
                          String adhar_card,
                          String watersupply_id,
                          String voting_center,
                          String BoothNo,
                          String SerialNo,
                          String constituency,
                          String city,
                          String zone,
                          String ward,
                          String apartment,
                          String flate) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.add_survey,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Response
                        Log.d("TAG", "onResponse: " + response);

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            Log.d("TAG", "onResponse1: " + response);
                            // Toast.makeText(UserSurveyActivity.this, "onResponse: ."+response, Toast.LENGTH_SHORT).show();

                            JSONObject jsonObject1 = jsonObject.getJSONObject("SURVEY");

                            String message = jsonObject1.getString("message");

                            Toast.makeText(UserSurveyActivity.this, message, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            //Toast.makeText(activity, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Error
                        // Log.d("AddToCart->",error.toString());
                    }
                }) {
            @Override
            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("house_no", house_no);
                params.put("series_id", series_id);
                params.put("colony_id", colony_id);
                params.put("row_id", row_id);
                params.put("gender", gender);
                params.put("name", name);
                params.put("middle_name", middle_name);
                params.put("surname", surname);
                params.put("mobile1", mobile1);
                params.put("mobile2", mobile2);
                params.put("dob", dob);
                params.put("qualification", qualification);
                params.put("caste", caste);
                params.put("status_id", status_id);
                params.put("voter_id", voter_id);
                params.put("adhar_card", adhar_card);
                params.put("watersupply_id", watersupply_id);
                params.put("voting_center", voting_center);
                params.put("booth_no", BoothNo);
                params.put("voting_sr_no", SerialNo);
                params.put("constituency",constituency);
                params.put("city_village",city);
                params.put("zone",zone);
                params.put("prabhag_ward",ward);
                params.put("apartment",apartment);
                params.put("flat_no",flate);
                return params;
            }
        };
        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(UserSurveyActivity.this);
        //adding the string request to request queue
        requestQueue.add(stringRequest);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    private void getColonyList(long series_id) {

        // Toast.makeText(UserSurveyActivity.this, "series-id here"+series_id, Toast.LENGTH_SHORT).show();
        //recreate();
        final RequestQueue requestQueue = Volley.newRequestQueue(UserSurveyActivity.this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.colony_list + "&series_id=" + series_id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Constants.colonies.clear();
                Constants.colony_name.clear();
                Constants.colony_name = new ArrayList<>();
                Constants.colonies = new ArrayList<>();
                spinner_colony.setAdapter(null);

                Constants.colonies.add(new Colony("0", "Select Colony"));
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Log.d("TAG", "onResponse: " + response);
                    // Toast.makeText(UserSurveyActivity.this, ""+response, Toast.LENGTH_SHORT).show();

                    JSONArray jsonArray = jsonObject.getJSONArray("COLONY");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String id = jsonObject1.getString("id");
                        String colony_name = jsonObject1.getString("colony_name");

                        //  Toast.makeText(UserSurveyActivity.this, "colonyName= "+colony_name, Toast.LENGTH_SHORT).show();


                        colonies.add(new Colony(id, colony_name));
                        Constants.colony_name.add(colony_name);
                        spinner_colony.setAdapter(new ArrayAdapter<String>(UserSurveyActivity.this, android.R.layout.simple_spinner_item, Constants.colony_name));
//                        adapter = new CustomAdapter(UserSurveyActivity.this,colonies);
//                        spinner_colony.setAdapter(adapter);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                requestQueue.stop();
            }
        });
        requestQueue.add(stringRequest);

    }

    private void getRowList(long series_id, long colony_id) {
        //
        //
        Constants.rows.add(new Row("0", "Select Row"));
        //Toast.makeText(UserSurveyActivity.this, "colony_id= "+colony_id, Toast.LENGTH_SHORT).show();
        final RequestQueue requestQueue = Volley.newRequestQueue(UserSurveyActivity.this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.row_list + "&series_id=" + series_id + "&colony_id=" + colony_id, new Response.Listener<String>() {
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
                        spinner_row.setAdapter(new ArrayAdapter<String>(UserSurveyActivity.this, android.R.layout.simple_spinner_item, Constants.row_name));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                requestQueue.stop();
                // Toast.makeText(UserSurveyActivity.this, ""+error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(stringRequest);

    }

    private void getSeriesList() {


        final RequestQueue requestQueue = Volley.newRequestQueue(UserSurveyActivity.this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.series_list, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Constants.series.clear();
                Constants.series = new ArrayList<>();

                Constants.series.add(new Series("0", "Select Series"));
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
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                requestQueue.stop();
            }
        });
        requestQueue.add(stringRequest);

    }

    @Override
    protected void onResume() {


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
        super.onResume();
    }


    @Override
    protected void onStop() {
        Intent intent = new Intent(UserSurveyActivity.this, DashBoardActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
        super.onStop();
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
