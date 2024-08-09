package com.vinodmapari.aaplasevak.Activity;


import static com.vinodmapari.aaplasevak.ApiConfig.ApiHandler.getRetrofitInstance;
import static com.vinodmapari.aaplasevak.Model.Constants.constituency_name;
import static com.vinodmapari.aaplasevak.Model.Constants.status_name;

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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.appbar.MaterialToolbar;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;
import com.vinodmapari.aaplasevak.ApiConfig.ApiInterface;
import com.vinodmapari.aaplasevak.Model.AddMemberBody;
import com.vinodmapari.aaplasevak.Model.AddMemberResponseData;
import com.vinodmapari.aaplasevak.Model.CasteItem;
import com.vinodmapari.aaplasevak.Model.CasteResponse;
import com.vinodmapari.aaplasevak.Model.CityVillageItem;
import com.vinodmapari.aaplasevak.Model.CityVillageResponse;
import com.vinodmapari.aaplasevak.Model.Constants;
import com.vinodmapari.aaplasevak.Model.ConstituencyItem;
import com.vinodmapari.aaplasevak.Model.ConstituencyResponse;
import com.vinodmapari.aaplasevak.Model.MainMemberDetail;
import com.vinodmapari.aaplasevak.Model.PrabhagWardItem;
import com.vinodmapari.aaplasevak.Model.PrabhagWardResponse;
import com.vinodmapari.aaplasevak.Model.QualificationItem;
import com.vinodmapari.aaplasevak.Model.QualificationsResponse;
import com.vinodmapari.aaplasevak.Model.Status;
import com.vinodmapari.aaplasevak.Model.StatusItem;
import com.vinodmapari.aaplasevak.Model.StatusResponse;
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
import retrofit2.Response;

public class SubMemberActivity extends AppCompatActivity {

    int selected_relation, selected_status;
    //String relation_id,survey_id;
    Button btnSubmit;
    SearchableSpinner spinner_relation, spinner_status, spinner_constituency, spinner_zone, spinner_ward, spinner_city, spinner_qualification, spinner_caste;
    String gender, boothNo, serialNo;
    String id;
    TextView tv1, tv2, member_colony, series, member_row, member_watersupply, house_number,
            tvSpinnerConstituency, tvSpinnerCIty, tvSpinnerZone, tvSpinnerWard;
    ArrayList<MainMemberDetail> mainMemberDetails;
    RadioButton selectedRadioButton;
    RadioGroup radioGroup;
    EditText votingcenter, dob, name, middle_name, etSurname, mob1, mob2, voterID, adharcard, etBoothNo, etSerialNo, etApartment, etFlateNumber;
    String series_id, status_id, colony_id, row_id, water_supply_id, constituency_id, city_id, zone_id, ward_id, qualification_id, caste_id;
    //TextView surname;
    String constituencyId, villageId, zoneId, wardId;
    MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_member);

        house_number = findViewById(R.id.house_number);
        dob = findViewById(R.id.dob);
        votingcenter = findViewById(R.id.voting_center);
        name = findViewById(R.id.name);
        middle_name = findViewById(R.id.middle_name);
        etSurname = findViewById(R.id.etSurname);
        mob1 = findViewById(R.id.mobile1);
        mob2 = findViewById(R.id.mobile2);
        /*tvSpinnerConstituency = findViewById(R.id.tv_spinner_Constituency);
        tvSpinnerCIty = findViewById(R.id.tvSpinnerCity);
        tvSpinnerZone = findViewById(R.id.tvSpinnerZone);
        tvSpinnerWard = findViewById(R.id.tvSpinnerWard);*/
        //qualification = findViewById(R.id.qualification);
        voterID = findViewById(R.id.voterID);
        //edevent = findViewById(R.id.event);
        adharcard = findViewById(R.id.adharCard);
        btnSubmit = findViewById(R.id.btn_submit);
        series = findViewById(R.id.spinnerSeries);
        member_colony = findViewById(R.id.spinner_colony);
        member_row = findViewById(R.id.spinner_row);
        member_watersupply = findViewById(R.id.spinner_water_supply);
        // spinner_relation=(SearchableSpinner)findViewById(R.id.spinnerRelation);
        spinner_status = (SearchableSpinner) findViewById(R.id.spinner_status);
        radioGroup = findViewById(R.id.radioGroup);
        //relation = findViewById(R.id.relation);
        etBoothNo = findViewById(R.id.etBoothNum);
        etSerialNo = findViewById(R.id.etSerialNum);
        etFlateNumber = findViewById(R.id.etFlatNo);
        etApartment = findViewById(R.id.etApartment);


        //Spinners
        spinner_constituency = findViewById(R.id.spinnerConstituency);
        spinner_city = findViewById(R.id.spinnerCity_Village);
        spinner_zone = findViewById(R.id.spinnerZone);
        spinner_ward = findViewById(R.id.spinnerPrabhag_Ward);
        spinner_qualification = findViewById(R.id.spinner_qualification);
        spinner_caste = findViewById(R.id.spinner_caste);

        //tv1=findViewById(R.id.tv1);
        //tv2=findViewById(R.id.tv2);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        mainMemberDetails = new ArrayList<>();
        //spinner_relation.setTitle("select relation");
        spinner_status.setTitle("status");
        spinner_constituency.setTitle("Constituency");
        spinner_zone.setTitle("Zone");
        spinner_ward.setTitle("Prabhag /Ward");
        spinner_city.setTitle("City/Village");


        // from SearchedUserDetail Activity
        Intent intent = getIntent();
        String user_surname = intent.getStringExtra("surname");
        String user_series = intent.getStringExtra("series");
        String house_no = intent.getStringExtra("house_no");
        String colony = intent.getStringExtra("colony");
        String row = intent.getStringExtra("row");
        String caste = intent.getStringExtra("caste");
        String watersupply = intent.getStringExtra("watersupply");
        id = intent.getStringExtra("id");
        String member_id = intent.getStringExtra("member_id");
        constituencyId = intent.getStringExtra("constituency");
        villageId = intent.getStringExtra("village");
        zoneId = intent.getStringExtra("zone");
        wardId = intent.getStringExtra("ward");

        //Toast.makeText(this, "C: " + constituencyId + " V: " + villageId + " Z: " + zoneId + " W: " + wardId, Toast.LENGTH_SHORT).show();


        etSurname.setText(user_surname);
        series.setText(user_series);
        member_colony.setText(colony);
        member_row.setText(row);
        member_watersupply.setText(watersupply);
        house_number.setText(house_no);


        fetchConstituencies();
        fetchCityVillages();
        fetchZones();
        fetchPrabhagWards();
        fetchQualification();
        fetchCaste();
        fetchStatus();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup rg, int checkedId) {
                for (int i = 0; i < rg.getChildCount(); i++) {
                    RadioButton btn = (RadioButton) rg.getChildAt(i);
                    if (btn.getId() == checkedId) {
                        gender = btn.getText().toString();
                        // do something with text
                        //  Toast.makeText(UserSurveyActivity.this, ""+text, Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            }
        });


        //onResume();


        // getRelationList();

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(SubMemberActivity.this,
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


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String calling_no = mob1.getText().toString();
                String wp_no = mob2.getText().toString();
                String house_no = house_number.getText().toString();
                String user_name = name.getText().toString();
                String user_middle_name = middle_name.getText().toString();
                String user_surname = etSurname.getText().toString();
                String mobile_no1 = calling_no;
                String mobile_no2 = wp_no;
                String user_dob = dob.getText().toString();
                String voterId = voterID.getText().toString();
                String user_adharcard = adharcard.getText().toString();
                String voting_center = votingcenter.getText().toString();
                String series_id = user_series;
                String row_id = row;
                String colony_id = colony;
                String watersupply_id = watersupply;
                //String user_caste = caste;

                //String constituency = spinner_constituency.getSelectedItem().toString();
                //String zone = spinner_zone.getSelectedItem().toString();
                //String cityVillage = spinner_city.getSelectedItem().toString();
                //String prabhagWard = spinner_ward.getSelectedItem().toString();
                String user_caste = spinner_caste.getSelectedItem().toString();

                String user_qualification = spinner_qualification.getSelectedItem().toString();
                String user_status = spinner_status.getSelectedItem().toString();
                String apartment = etApartment.getText().toString();
                String flateNo = etFlateNumber.getText().toString();
                String BoothNo = etBoothNo.getText().toString();
                String SerialNo = etSerialNo.getText().toString();


                if (house_no.equals("")
                        || user_name.equals("")
                        || user_middle_name.equals("")
                        || user_surname.equals("")
                        || gender.equals("")
                ) {
                    Toast.makeText(SubMemberActivity.this, "some fields are empty", Toast.LENGTH_SHORT).show();
                } else if (constituency_id == null && constituency_id.equalsIgnoreCase("")) {
                    Toast.makeText(SubMemberActivity.this, "Please Select Constituency", Toast.LENGTH_SHORT).show();
                } else if (city_id == null && city_id.equalsIgnoreCase("")) {
                    Toast.makeText(SubMemberActivity.this, "Please Select City/Village", Toast.LENGTH_SHORT).show();
                } else if (zone_id == null && zone_id.equalsIgnoreCase("")) {
                    Toast.makeText(SubMemberActivity.this, "Please Select Zone", Toast.LENGTH_SHORT).show();
                } else if (ward_id == null && ward_id.equalsIgnoreCase("")) {
                    Toast.makeText(SubMemberActivity.this, "Please Select Ward", Toast.LENGTH_SHORT).show();
                } else if (spinner_status.getSelectedItem().toString().equalsIgnoreCase("Select Status")) {
                    Toast.makeText(SubMemberActivity.this, "Please Select Status", Toast.LENGTH_SHORT).show();
                } else if (spinner_qualification.getSelectedItem().toString().equalsIgnoreCase("Select Qualification")) {
                    Toast.makeText(SubMemberActivity.this, "Please Select Qualification", Toast.LENGTH_SHORT).show();
                } else {
                    // id and member id is missing from here
                    addMember(house_no, gender, user_name, user_middle_name, user_surname,
                            mobile_no1, mobile_no2, user_dob, user_qualification, user_status, voterId, user_adharcard,
                            voting_center, BoothNo, SerialNo, constituency_id, city_id, zone_id, ward_id, apartment, flateNo);

                    //Toast.makeText(SubMemberActivity.this, "family member added successfully", Toast.LENGTH_SHORT).show();
                    //finish();
                }
            }
        });

    }

    private void addMember(String house_no, String gender, String name, String middle_name,
                           String surname, String mobile1, String mobile2, String dob, String qualification_id, String status_id,
                           String voter_id, String adhar_card, String voting_center, String BoothNo, String SerialNo,
                           String constituency_id, String city_id, String zone_id, String ward_id, String apartment, String flate) {

        ApiInterface apiInterface = getRetrofitInstance().create(ApiInterface.class);

        // check for id and member id and also for what is survey

        AddMemberBody addMemberBody = new AddMemberBody(house_no, gender, name, middle_name, surname, mobile1, mobile2, dob, qualification_id, status_id, voter_id,
                adhar_card, voting_center, BoothNo, SerialNo, apartment, flate, constituency_id, city_id, zone_id, ward_id);

        Call<AddMemberResponseData> call = apiInterface.addMember(addMemberBody);
        Log.d("Api Response", "AddMember: " + addMemberBody.toString());

        call.enqueue(new Callback<AddMemberResponseData>() {
            @Override
            public void onResponse(Call<AddMemberResponseData> call, retrofit2.Response<AddMemberResponseData> response) {
                if (response.isSuccessful()) {

                    AddMemberResponseData responseData = response.body();
                    String status = responseData.getStatus();
                    String message = responseData.getMessage();
                    Toast.makeText(SubMemberActivity.this, "Success: " + status + " " + message, Toast.LENGTH_SHORT).show();
                    finish();

                } else {
                    Toast.makeText(SubMemberActivity.this, "Response Error..!!", Toast.LENGTH_SHORT).show();
                    Log.e("Api Response", "Response Error..");
                }
            }

            @Override
            public void onFailure(Call<AddMemberResponseData> call, Throwable throwable) {
                Log.e("Api Response", "Error.." + throwable.getLocalizedMessage());
                Toast.makeText(SubMemberActivity.this, "Error.." + throwable.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
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
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(SubMemberActivity.this,
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
                Toast.makeText(SubMemberActivity.this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
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
                    constituencyName.add("Constituency");

                    for (ConstituencyItem constituency : constituencies) {
                        constituencyName.add(constituency.getConstituencyName());
                        constituencyIdMap.put(constituency.getConstituencyName(), constituency.getId());
                    }

                    //Toast.makeText(UserSurveyActivity.this, "Names: " + constituencyName, Toast.LENGTH_SHORT).show();
                    //Log.d("Api Response","Names: " + constituencyName);
                    // Populate the spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(SubMemberActivity.this, android.R.layout.simple_spinner_item, constituencyName);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_constituency.setAdapter(adapter);


                    String selectedConstituencyId = constituencyId;
                    if (selectedConstituencyId != null) {
                        String selectedConstituencyName = getConstituencyById(constituencyIdMap, selectedConstituencyId);
                        if (selectedConstituencyName != null) {
                            int position = adapter.getPosition(selectedConstituencyName);
                            if (position >= 0) {
                                spinner_constituency.setSelection(position);
                            }
                        }
                    }

                    // Handle spinner item selection
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
                Toast.makeText(SubMemberActivity.this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
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
                    cityVillageNames.add("City/Village");

                    for (CityVillageItem item : cityVillages) {
                        cityVillageNames.add(item.getCityVillageName());
                        cityVillageIdMap.put(item.getCityVillageName(), item.getId());
                    }

                    // Populate the spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(SubMemberActivity.this,
                            android.R.layout.simple_spinner_item, cityVillageNames);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_city.setAdapter(adapter);

                    String selectedCityId = villageId;
                    if (selectedCityId != null) {
                        String selectedCityName = getCityById(cityVillageIdMap, selectedCityId);
                        if (selectedCityName != null) {
                            int position = adapter.getPosition(selectedCityName);
                            if (position >= 0) {
                                spinner_city.setSelection(position);
                            }
                        }
                    }

                    // Handle spinner item selection
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
                Toast.makeText(SubMemberActivity.this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
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
                    zoneNames.add("Zone");

                    for (ZoneItem zone : zones) {
                        zoneNames.add(zone.getZoneName());
                        zoneIdMap.put(zone.getZoneName(), zone.getId());
                    }

                    // Populate the spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(SubMemberActivity.this,
                            android.R.layout.simple_spinner_item, zoneNames);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_zone.setAdapter(adapter);

                    String selectedZoneId = zoneId;
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
                Toast.makeText(SubMemberActivity.this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
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
                    prabhagWardNames.add("Prabhag/Ward");

                    for (PrabhagWardItem prabhagWard : prabhagWards) {
                        prabhagWardNames.add(prabhagWard.getPrabhagWardName());
                        prabhagWardIdMap.put(prabhagWard.getPrabhagWardName(), prabhagWard.getId());
                    }

                    // Populate the spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(SubMemberActivity.this,
                            android.R.layout.simple_spinner_item, prabhagWardNames);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_ward.setAdapter(adapter);

                    String selectedWardId = wardId;
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
                            if (!selectedPrabhagWard.equals("Prabhag Ward")) {
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
                Toast.makeText(SubMemberActivity.this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
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
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(SubMemberActivity.this,
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
                    Toast.makeText(SubMemberActivity.this, "Response Error..!!", Toast.LENGTH_SHORT).show();
                    Log.e("Api Response", "Response Error..");
                }
            }

            @Override
            public void onFailure(Call<QualificationsResponse> call, Throwable throwable) {
                Log.e("Api Response", "Error.." + throwable.getLocalizedMessage());
                Toast.makeText(SubMemberActivity.this, "Error.." + throwable.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
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
                    casteNames.add("Caste");

                    for (CasteItem caste : casteResponses) {
                        casteNames.add(caste.getCasteName());
                        casteIdMap.put(caste.getCasteName(), caste.getId());
                    }

                    // Populate the spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(SubMemberActivity.this,
                            android.R.layout.simple_spinner_item, casteNames);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_caste.setAdapter(adapter);

                    // Handle spinner item selection
                    spinner_caste.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String selectedCaste = (String) parent.getItemAtPosition(position);
                            if (!selectedCaste.equals("Caste")) {
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
                    Toast.makeText(SubMemberActivity.this, "Response Error..!!", Toast.LENGTH_SHORT).show();
                    Log.e("Api Response", "Response Error..");
                }
            }

            @Override
            public void onFailure(Call<CasteResponse> call, Throwable throwable) {
                Log.e("Api Response", "Error.." + throwable.getLocalizedMessage());
                Toast.makeText(SubMemberActivity.this, "Error.." + throwable.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        //getRelationList();
        //getStatusList();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //getRelationList();
        //getStatusList();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //getRelationList();
        //getStatusList();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

}