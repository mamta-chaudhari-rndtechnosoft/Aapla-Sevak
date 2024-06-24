package com.vinodmapari.aaplasevak.Activity;


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
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;
import com.vinodmapari.aaplasevak.Model.Constants;
import com.vinodmapari.aaplasevak.Model.MainMemberDetail;
import com.vinodmapari.aaplasevak.Model.Status;
import com.vinodmapari.aaplasevak.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class SubMemberActivity extends AppCompatActivity {

    int selected_relation, selected_status;
    String relation_id, status_id;
    Button btnSubmit;
    SearchableSpinner spinner_relation, spinner_status;
    String gender, boothNo, serialNo;
    ;
    String survey_id;
    TextView tv1, tv2, member_colony, series, member_row, member_watersupply, member_caste, house_number, surname;
    ArrayList<MainMemberDetail> mainMemberDetails;
    RadioButton selectedRadioButton;
    RadioGroup radioGroup;
    EditText votingcenter, dob, name, middle_name, relation, mob1, mob2, qualification, voterID, edevent, adharcard, etBoothNo, etSerialNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_member);

        house_number = findViewById(R.id.house_number);
        dob = findViewById(R.id.dob);
        votingcenter = findViewById(R.id.voting_center);
        name = findViewById(R.id.name);
        middle_name = findViewById(R.id.middle_name);
        surname = findViewById(R.id.surname);
        mob1 = findViewById(R.id.mobile1);
        mob2 = findViewById(R.id.mobile2);
        qualification = findViewById(R.id.qualification);
        member_caste = findViewById(R.id.caste);
        voterID = findViewById(R.id.voterID);
        edevent = findViewById(R.id.event);
        adharcard = findViewById(R.id.adharCard);
        btnSubmit = findViewById(R.id.btn_submit);
        series = findViewById(R.id.spinnerSeries);
        member_colony = findViewById(R.id.spinner_colony);
        member_row = findViewById(R.id.spinner_row);
        member_watersupply = findViewById(R.id.spinner_water_supply);
        // spinner_relation=(SearchableSpinner)findViewById(R.id.spinnerRelation);
        spinner_status = (SearchableSpinner) findViewById(R.id.spinner_status);
        radioGroup = findViewById(R.id.radioGroup);
        relation = findViewById(R.id.relation);
        etBoothNo = findViewById(R.id.etBoothNum);
        etSerialNo = findViewById(R.id.etSerialNum);

//        tv1=findViewById(R.id.tv1);
//        tv2=findViewById(R.id.tv2);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));

        toolbar.setTitle(Html.fromHtml("<b>" + "Add Family Member" + "</b>"));
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        mainMemberDetails = new ArrayList<>();
        // spinner_relation.setTitle("select relation");
        spinner_status.setTitle("select status");

        Intent intent = getIntent();
        String user_surname = intent.getStringExtra("surname");
        String user_series = intent.getStringExtra("series");
        String house_no = intent.getStringExtra("house_no");
        String colony = intent.getStringExtra("colony");
        String row = intent.getStringExtra("row");
        String caste = intent.getStringExtra("caste");
        String watersupply = intent.getStringExtra("watersupply");
        String id = intent.getStringExtra("id");
        String member_id = intent.getStringExtra("member_id");


//        Toast.makeText(getApplicationContext(), ""+series, Toast.LENGTH_SHORT).show();

        surname.setText(user_surname);
        series.setText(user_series);
        member_colony.setText(colony);
        member_row.setText(row);
        member_watersupply.setText(watersupply);
        house_number.setText(house_no);
        member_caste.setText(caste);


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


        onResume();


        status_name.clear();
        status_name = new ArrayList<>();

        for (int i = 0; i < Constants.statuses.size(); i++) {
            Constants.status_name.add(Constants.statuses.get(i).getStatus_name());
            if (status_name != null && status_name.equals(Constants.statuses.get(i).getStatus_name())) {
                selected_status = i;
            }
        }

        ArrayAdapter<String> dataAdapter_Status = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Constants.status_name);
        dataAdapter_Status.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_status.setAdapter(dataAdapter_Status);
        spinner_status.setSelection(selected_status);


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
                String user_surname = surname.getText().toString();
                String mobile_no1 = calling_no;
                String mobile_no2 = wp_no;
                String user_dob = dob.getText().toString();
                String user_qualification = qualification.getText().toString();
                String event = edevent.getText().toString();
                String voterId = voterID.getText().toString();
                String user_adharcard = adharcard.getText().toString();
                String voting_center = votingcenter.getText().toString();
                String user_relation = relation.getText().toString();
                String series_id = user_series;
                String row_id = row;
                String colony_id = colony;
                String watersupply_id = watersupply;
                String user_caste = caste;

                String BoothNo = etBoothNo.getText().toString();
                String SerialNo = etSerialNo.getText().toString();


                if (house_no.equals("") || user_name.equals("") || user_middle_name.equals("") || user_surname.equals("") || mobile_no1.length() == 0 || mobile_no2.length() == 0
                        || user_dob.isEmpty() || user_qualification.equals("") || caste.equals("")  || voterId.equals("") || user_adharcard.equals("") || voting_center.equals("") || BoothNo.equals("") || SerialNo.equals("")) {
                    Toast.makeText(SubMemberActivity.this, "some fields are empty", Toast.LENGTH_SHORT).show();
                } else {

                    addMember(id, gender, user_name, user_middle_name, user_surname, mobile_no1, mobile_no2, user_dob, user_qualification, status_id, user_relation, event, voterId, user_adharcard, voting_center, house_no, row_id, series_id, colony_id, watersupply_id, user_caste, member_id, BoothNo, SerialNo);

                    Toast.makeText(SubMemberActivity.this, "family member added successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        });


//        spinner_relation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
//        {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
//            {
//                relation_id = Constants.relations.get(position).getId();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent)
//            {
//
//            }
//        });

        spinner_status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                status_id = Constants.statuses.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void addMember(String survey_id, String gender, String user_name, String user_middle_name, String user_surname, String mobile_no1, String mobile_no2, String user_dob, String user_qualification, String status_id, String relation, String event, String voterId, String adharCard, String voting_center, String house_no, String row_id, String series_id, String colony_id, String watersupply_id, String caste, String member_id, String BoothNo, String SerialNo) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.add_member,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Response
                        Log.d("TAG", "onResponse: " + response);

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            Log.d("TAG1", "onResponse: " + response);

                            JSONObject jsonObject1 = jsonObject.getJSONObject("ADD_MEMBER");

                            String message = jsonObject1.getString("message");

                            // Toast.makeText(SubMemberActivity.this, message, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            //Toast.makeText(SubMemberActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Error
                        //   Log.d("AddToCart->",error.toString());
                        // Toast.makeText(SubMemberActivity.this, ""+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();


                params.put("survey_id", survey_id);
                params.put("gender", gender);
                params.put("name", user_name);
                params.put("middle_name", user_middle_name);
                //params.put("surname", user_surname);
                params.put("mobile1", mobile_no1);
                params.put("mobile2", mobile_no2);
                params.put("dob", user_dob);
                params.put("qualification", user_qualification);
                params.put("status_id", status_id);
                params.put("relation", relation);
                params.put("event", event);
                params.put("voter_id", voterId);
                params.put("adhar_card", adharCard);
                params.put("voting_center", voting_center);
                params.put("member_id", member_id);
                params.put("booth_no", BoothNo);
                params.put("voting_sr_no", SerialNo);


                return params;
            }
        };
        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(SubMemberActivity.this);
        //adding the string request to request queue
        requestQueue.add(stringRequest);


    }


    public void getStatusList() {
        final RequestQueue requestQueue = Volley.newRequestQueue(SubMemberActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.status_list, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Constants.statuses.clear();
                Constants.statuses = new ArrayList<>();

                Constants.statuses.add(new Status("0", "select status"));
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("STATUS");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String id = jsonObject1.getString("id");
                        String status_name = jsonObject1.getString("status_name");

                        Constants.statuses.add(new Status(id, status_name));
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


    @Override
    protected void onResume() {
        super.onResume();
        //getRelationList();
        getStatusList();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //getRelationList();
        getStatusList();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //getRelationList();
        getStatusList();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

}