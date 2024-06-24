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
import com.vinodmapari.aaplasevak.Model.Colony;
import com.vinodmapari.aaplasevak.Model.Constants;
import com.vinodmapari.aaplasevak.Model.EditFamilyMemberBody;
import com.vinodmapari.aaplasevak.Model.Method;
import com.vinodmapari.aaplasevak.Model.Row;
import com.vinodmapari.aaplasevak.Model.Series;
import com.vinodmapari.aaplasevak.Model.SharedPref;
import com.vinodmapari.aaplasevak.Model.SurveyList;
import com.vinodmapari.aaplasevak.Model.UserDetail;
import com.vinodmapari.aaplasevak.Model.UserDetailResponse;
import com.vinodmapari.aaplasevak.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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
    SearchableSpinner spinner_series, spinner_status, spinner_colony, spinner_row, spinner_water_Supply;
    EditText etHouseNumber, etVotingCenter, etDob, etName, etMiddleName, etSurname, etMob1, etMob2,
            etQualification, etCaste, etRelation, etVoterId, etEvent, etAadharCard, etBooth, etSerial;
    private String colonyName;
    int id;
    String colony, row, waterSupply, series, status;
    //Field Entry
    String HouseNo, Name, MiddleName, Surname, VotingCenter, BoothNo, SeriesId, Gender,
            Mobile1, Mobile2, Dob, Qualification, Caste, Relation, Event, VoterId, AadharCard;

    int ColonyId, RowId, WaterSupplyId, MemberId, VotingSrNo;
    LottieAnimationView loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_member);

        id = getIntent().getIntExtra("id", 0);

        //Edittext
        etHouseNumber = findViewById(R.id.house_number);
        etVotingCenter = findViewById(R.id.voting_center);
        etDob = findViewById(R.id.dob);
        etName = findViewById(R.id.name);
        etMiddleName = findViewById(R.id.middle_name);
        etSurname = findViewById(R.id.surname);
        etMob1 = findViewById(R.id.mobile1);
        etMob2 = findViewById(R.id.mobile2);
        etQualification = findViewById(R.id.qualification);
        etCaste = findViewById(R.id.caste);
        etRelation = findViewById(R.id.relation);
        etVoterId = findViewById(R.id.voterID);
        etEvent = findViewById(R.id.event);
        etAadharCard = findViewById(R.id.adharCard);
        etBooth = findViewById(R.id.etBoothNo);
        etSerial = findViewById(R.id.etSerialNo);

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
                Gender = gender;
                Mobile1 = etMob1.getText().toString();
                Mobile2 = etMob2.getText().toString();
                Qualification = etQualification.getText().toString();
                Caste = etCaste.getText().toString();
                Relation = etRelation.getText().toString();
                Event = etEvent.getText().toString();
                AadharCard = etAadharCard.getText().toString();
                WaterSupplyId = spinner_water_Supply.getSelectedItemPosition();
                VoterId = etVoterId.getText().toString();

                loader.setVisibility(View.VISIBLE);
                updateMemberData();

            }
        });


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

                    Log.d("mamta", String.valueOf(userDetails));

                    loader.setVisibility(View.GONE);

                    int id = userDetails.get(0).getId();
                    String name = userDetails.get(0).getName();
                    String middleName = userDetails.get(0).getMiddle_name();
                    String surname = userDetails.get(0).getSurname();
                    String votingCenter = userDetails.get(0).getVoting_center();
                    String boothNo = userDetails.get(0).getBooth_No();
                    String mobile1 = userDetails.get(0).getMobile1();
                    String mobile2 = userDetails.get(0).getMobile2();
                    String dob = userDetails.get(0).getDob();
                    String qualification = userDetails.get(0).getQualification();
                    String caste = userDetails.get(0).getCaste();
                    String relation = userDetails.get(0).getRelation();
                    String event = userDetails.get(0).getEvent();
                    String voterId = userDetails.get(0).getVoter_id();
                    String adharCard = userDetails.get(0).getAdhar_card();
                    String houseNo = userDetails.get(0).getHouse_no();
                    String gender = userDetails.get(0).getGender();
                    String seriesId = userDetails.get(0).getSeries_id();
                    int votingSrNo = userDetails.get(0).getVoting_sr_no();
                    int colonyId = userDetails.get(0).getColony_id();
                    int rowId = userDetails.get(0).getRow_id();
                    int waterSupplyId = userDetails.get(0).getWatersupply_id();
                    String statusId = userDetails.get(0).getStatus_id();
                    int memberId = userDetails.get(0).getMember_id();

                    //Toast.makeText(UpdateMemberActivity.this, "c: " + colonyId + "R: " + rowId + "W: " + waterSupplyId + "S: " + seriesId, Toast.LENGTH_SHORT).show();

                    etHouseNumber.setText(houseNo);
                    etName.setText(name);
                    etMiddleName.setText(middleName);
                    etSurname.setText(surname);
                    etVotingCenter.setText(votingCenter);
                    etBooth.setText(boothNo);
                    etMob1.setText(mobile1);
                    etMob2.setText(mobile2);
                    etDob.setText(dob);
                    etQualification.setText(qualification);
                    etCaste.setText(caste);
                    etRelation.setText(relation);
                    etEvent.setText(event);
                    etAadharCard.setText(adharCard);
                    etVoterId.setText(voterId);
                    etSerial.setText(String.valueOf(votingSrNo));

                    spinner_series.setSelection(Integer.parseInt(seriesId));
                    spinner_colony.setSelection(colonyId);
                    spinner_row.setSelection(rowId);
                    //spinner_status.setSelection(Integer.parseInt(statusId));
                    spinner_water_Supply.setSelection(waterSupplyId);


                    /*//if (seriesId != null) spinner_series.setSelection(Integer.parseInt(seriesId));
                    if (colonyId != null) spinner_colony.setSelection(colonyId);
                    if (rowId != null) spinner_row.setSelection(rowId);
                    if (statusId != null) spinner_status.setSelection(statusId);

                   // if (waterSupplyId != null) spinner_water_Supply.setSelection(waterSupplyId);

                    if(waterSupplyId != null) {
                        spinner_water_Supply.setSelection(waterSupplyId);
                    }
                    else{
                        Toast.makeText(UpdateMemberActivity.this, "water supply Id is null", Toast.LENGTH_SHORT).show();
                    }

                    if(seriesId != null) {
                        spinner_series.setSelection(Integer.parseInt(seriesId));
                    }
                    else{
                        Toast.makeText(UpdateMemberActivity.this, "Series Id is null", Toast.LENGTH_SHORT).show();
                    }*/


                    /*if (statusId != null) {
                        spinner_status.setSelection(Integer.parseInt(statusId));
                    } else {
                        Toast.makeText(UpdateMemberActivity.this, "status Id is null", Toast.LENGTH_SHORT).show();
                    }*/


                    //radioGroup.setSelected(true);
                    if (gender.equals("Male") || gender.equalsIgnoreCase("male")) {
                        radioButton1.setChecked(true);
                    } else if (gender.equals("Female") || gender.equalsIgnoreCase("female")) {
                        radioButton2.setChecked(true);
                    } else if (gender.equalsIgnoreCase("others")) {
                        radioButton3.setChecked(true);
                    } else {
                        if (gender == null) {
                            Log.d("Tag", "Gender is null");
                        } else {
                            Log.d("Tag", "Gender is not recognized: " + gender);
                        }
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

        EditFamilyMemberBody editFamilyMemberBody = new EditFamilyMemberBody(id, Name, MiddleName, Surname, VotingCenter, BoothNo, VotingSrNo, SeriesId, ColonyId, RowId, Gender, Mobile1, Mobile2, Qualification,
                Caste, Relation, Event, AadharCard, WaterSupplyId, VoterId);
        Log.d("Api Response", editFamilyMemberBody.toString());

        Call<ResponseBody> call = apiInterface.updateFamilyDetails(editFamilyMemberBody);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    loader.setVisibility(View.GONE);
                    Toast.makeText(UpdateMemberActivity.this, "Success..!!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    loader.setVisibility(View.GONE);
                    Toast.makeText(UpdateMemberActivity.this, "Response Error..!!", Toast.LENGTH_SHORT).show();
                    Log.e("Tag", "Response Error..");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
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