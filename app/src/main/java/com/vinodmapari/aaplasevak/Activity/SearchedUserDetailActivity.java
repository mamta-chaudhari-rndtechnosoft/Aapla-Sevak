package com.vinodmapari.aaplasevak.Activity;

import static com.vinodmapari.aaplasevak.ApiConfig.ApiHandler.getRetrofitInstance;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.vinodmapari.aaplasevak.ApiConfig.ApiInterface;
import com.vinodmapari.aaplasevak.Model.Constants;
import com.vinodmapari.aaplasevak.Model.HouseDetail;
import com.vinodmapari.aaplasevak.Model.HouseResponse;
import com.vinodmapari.aaplasevak.Model.MainMemberDetail;
import com.vinodmapari.aaplasevak.Model.SearchList;
import com.vinodmapari.aaplasevak.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchedUserDetailActivity extends AppCompatActivity {

    Button btnAdd, btnEdit;
    ImageView ivShare, ivWhatsapp, ivCall, ivEmail, ivSaveContact;
    private static final int REQUEST_CALL = 101;

    ArrayList<MainMemberDetail> mainMemberDetails;

    ArrayList<SearchList> searchLists;

    String id, member_id, name, series, middle_name, surname,
            gender, phone, dob, phone2, caste, houseNo, status,
            watersupply, voterID, adhar, votingCenter, row, colony,
            qualification, boothNo, srNo, apartment, flateNo, constituency, cityVillage,
            zone, ward, sNo, fullName, seriesId;
    String Details;
    TextView tv_Name, tv_Gender, tv_Mob, tv_Series, tv_mob2, tv_houseNo, tv_colony, tv_whatsapp_no, tv_dob, tv_qualificatoon, tv_caste, tv_status, tv_voterID,
            tv_adhar, tv_watersupply, tv_voting_center, tv_row, tvBoothNo, tvSrNo, tvApartment, tvFlateNo;

    int SeriesId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searched_user_detail);

        btnAdd = findViewById(R.id.btn);
        btnEdit = findViewById(R.id.btnEdit);
        ivShare = findViewById(R.id.share);
        ivWhatsapp = findViewById(R.id.whatsapp);
        ivCall = findViewById(R.id.call);
        ivEmail = findViewById(R.id.email);
        ivSaveContact = findViewById(R.id.save_contact);
        tv_Name = findViewById(R.id.etName);
        tv_Mob = findViewById(R.id.etMob);
        tv_mob2 = findViewById(R.id.tv_mob2);
        tv_houseNo = findViewById(R.id.house_number);
        tv_Series = findViewById(R.id.series);
        tv_colony = findViewById(R.id.tv_colony);
        tv_dob = findViewById(R.id.tv_dob);
        tv_caste = findViewById(R.id.tv_caste);
        tv_adhar = findViewById(R.id.tv_adhar);
        tv_watersupply = findViewById(R.id.tv_waterSupply);
        tv_voterID = findViewById(R.id.tv_voterID);
        tv_voting_center = findViewById(R.id.tv_voting_center);
        tv_status = findViewById(R.id.tv_status);
        tv_row = findViewById(R.id.tv_row);
        tv_qualificatoon = findViewById(R.id.tv_qualification);
        tv_Gender = findViewById(R.id.gender);
        tvBoothNo = findViewById(R.id.tvBoothNo);
        tvSrNo = findViewById(R.id.tvSrNo);
        tvApartment = findViewById(R.id.tvApartment);
        tvFlateNo = findViewById(R.id.tvFlateNo);


        searchLists = new ArrayList<>();

        //getSearchList();

        ////////////////////////////////////////////////////////////////////////////////////////////

        //From Search Adapter
        Intent in = getIntent();
        name = in.getStringExtra("name");
        middle_name = in.getStringExtra("middle_name");
        surname = in.getStringExtra("surname");
        voterID = in.getStringExtra("voter_id");
        adhar = in.getStringExtra("adhar_card");
        houseNo = in.getStringExtra("house_no");
        series = in.getStringExtra("series_name");
        seriesId = in.getStringExtra("series_id");
        row = in.getStringExtra("row_id");
        watersupply = in.getStringExtra("water_Supply_id");
        dob = in.getStringExtra("dob");
        caste = in.getStringExtra("caste");
        colony = in.getStringExtra("colony_id");
        phone = in.getStringExtra("mobile1");
        phone2 = in.getStringExtra("mobile2");
        gender = in.getStringExtra("gender");
        qualification = in.getStringExtra("qualification");
        id = in.getStringExtra("id");
        status = in.getStringExtra("status_id");
        votingCenter = in.getStringExtra("voting_center");
        boothNo = in.getStringExtra("booth_no");
        srNo = in.getStringExtra("voting_sr_no");
        member_id = in.getStringExtra("member_id");
        apartment = in.getStringExtra("apartment");
        flateNo = in.getStringExtra("flate_no");
        constituency = in.getStringExtra("constituency");
        cityVillage = in.getStringExtra("city_village");
        zone = in.getStringExtra("zone");
        ward = in.getStringExtra("prabhag_ward");
        sNo = in.getStringExtra("s_no");
        fullName = in.getStringExtra("fullname");

        //Toast.makeText(this, "Series: " + series, Toast.LENGTH_SHORT).show();
        //Toast.makeText(SearchedUserDetailActivity.this, "member-id= "+member_id, Toast.LENGTH_SHORT).show();

        ////////////////////////////////////////////////////////////////////////////////////////////

        tv_voterID.setText(voterID);
        tv_Name.setText(fullName);
        tv_adhar.setText(adhar);
        tv_houseNo.setText(houseNo);
        tv_Gender.setText(gender);
        tv_qualificatoon.setText(qualification);
        tv_Series.setText(series);
        tv_colony.setText(colony);
        tv_row.setText(row);
        tv_status.setText(status);
        tv_voterID.setText(voterID);
        tv_watersupply.setText(watersupply);
        tv_voting_center.setText(votingCenter);
        tv_caste.setText(caste);
        tv_dob.setText(dob);
        tv_Mob.setText("+91" + phone);
        tv_mob2.setText("+91" + phone2);
        tvBoothNo.setText(boothNo);
        tvSrNo.setText(sNo);
        tvApartment.setText(apartment);
        tvFlateNo.setText(flateNo);

        //Toast.makeText(this, "House: " + houseNo + "Series: " + SeriesId, Toast.LENGTH_SHORT).show();

        fetchHouseDetails(houseNo, seriesId);
        mainMemberDetails = new ArrayList<>();

        //Toast.makeText(this, "Details: " + Details, Toast.LENGTH_SHORT).show();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // getMainMemberDetails();
                Intent intent = new Intent(SearchedUserDetailActivity.this, SubMemberActivity.class);
                intent.putExtra("surname", surname);
                intent.putExtra("series", series);
                intent.putExtra("house_no", houseNo);
                intent.putExtra("colony", colony);
                intent.putExtra("row", row);
                intent.putExtra("caste", caste);
                intent.putExtra("watersupply", watersupply);
                intent.putExtra("id", id);
                intent.putExtra("member_id", member_id);
                startActivity(intent);

            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchedUserDetailActivity.this, EditMemberActivity.class);
                intent.putExtra("house_no", houseNo);
                intent.putExtra("series_id", seriesId);
                startActivity(intent);

            }
        });

        ivShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_SUBJECT, "My application");
                    String sAux = "\n" + "User Details:" + "\n\n";
                    sAux = sAux + Details;
                    i.putExtra(Intent.EXTRA_TEXT, sAux);
                    startActivity(Intent.createChooser(i, "choose one"));
                } catch (Exception e) {

                }
            }
        });

        ivWhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + tv_Mob.getText().toString() + "&text=" + Details));
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        ivCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeCall();
            }
        });

        ivEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + phone));
                intent.putExtra("sms_body", Details);
                startActivity(intent);
            }
        });

        ivSaveContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addContact(name, phone);
            }
        });
    }

    private void makeCall() {

        String number = tv_Mob.getText().toString();
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + number));
        startActivity(intent);
        if (number.trim().length() > 0) {
            if (ContextCompat.checkSelfPermission(SearchedUserDetailActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(SearchedUserDetailActivity.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
            }
        } else {
            Toast.makeText(this, "could not make a call", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makeCall();
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void addContact(String name, String phone) {
        // in this method we are calling an intent and passing data to that
        // intent for adding a new contact.
        Intent contactIntent = new Intent(ContactsContract.Intents.Insert.ACTION);
        contactIntent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
        contactIntent.putExtra(ContactsContract.Intents.Insert.NAME, name).putExtra(ContactsContract.Intents.Insert.PHONE, phone);
        startActivityForResult(contactIntent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // in on activity result method.
        if (requestCode == 1) {
            // we are checking if the request code is 1
            if (resultCode == Activity.RESULT_OK) {
                // if the result is ok we are displaying a toast message.
                Toast.makeText(this, "Contact has been added.", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, SearchedUserDetailActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
            // else we are displaying a message as contact addition has cancelled.
            if (resultCode == Activity.RESULT_CANCELED) {
                Intent i = new Intent(this, SearchedUserDetailActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        }
    }



    /*private void fetchHouseDetails(String houseNo, String seriesID) {
        //String url = API_URL + "?" + HOUSE_NO_PARAM + "=" + houseNo;
        //Toast.makeText(this, "Inside House Function", Toast.LENGTH_SHORT).show();

        //Toast.makeText(this, "house: " + houseNo + "series: " + seriesID, Toast.LENGTH_SHORT).show();

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.houseDetail + "&house_no=" + houseNo + "&series_id=" + seriesID,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray houseDetailArray = jsonObject.getJSONArray("HOUSE_DETAIL");

                            List<HouseDetail> houseDetails = new ArrayList<>();
                            StringBuilder allDetails = new StringBuilder();
                            int counter = 1;

                            for (int i = 0; i < houseDetailArray.length(); i++) {
                                JSONObject houseDetailObject = houseDetailArray.getJSONObject(i);
                                String name = houseDetailObject.getString("name");
                                String middleName = houseDetailObject.getString("middle_name");
                                String surname = houseDetailObject.getString("surname");
                                String votingCenter = houseDetailObject.getString("voting_center");
                                String boothNo = houseDetailObject.getString("booth_no");
                                int votingSrNo = houseDetailObject.getInt("voting_sr_no");
                                String voterId = houseDetailObject.getString("voter_id");
                                String seriesId = (String) houseDetailObject.get("series_id");

                                HouseDetail houseDetail = new HouseDetail(name, middleName, surname, votingCenter, boothNo, votingSrNo, voterId, seriesId);
                                houseDetails.add(houseDetail);

                                *//*if (!houseDetails.isEmpty()) {
                                    HouseDetail firstHouseDetail = houseDetails.get(i);
                                    Log.d("TAG", "Name: " + firstHouseDetail.getName() + " " +
                                            "Voting Center: " + firstHouseDetail.getVotingCenter());

                                    String Data = "Name: " + firstHouseDetail.getName() + " " + firstHouseDetail.getMiddleName() + " " + firstHouseDetail.getSurname() +
                                             "\n" + "Voter ID: " + firstHouseDetail.getVoterId() +
                                            "\n" + "Booth No: " + firstHouseDetail.getBoothNo() +
                                            "\n" + "Sr. No: " + firstHouseDetail.getVotingSrNo() +
                                            "\n" + "Voting Center: " + firstHouseDetail.getVotingCenter();

                                    Details = Data;

                                    // You can update UI or perform other actions here
                                }*//*

                               String data =
                                        counter + ")" + " Name: " + name + " " + middleName + " " + surname +
                                                "\nVoter ID: " + voterId +
                                                "\nBooth No: " + boothNo +
                                                "\nSr.No: " + votingSrNo +
                                                "\nVoting Center: " + votingCenter + "\n\n";

                                allDetails.append(data);
                                counter++;

                            }

                            Details = allDetails.toString() + "*Aapla Sevak - Vinod Mapari*";

                        } catch (JSONException e) {
                            e.printStackTrace();
                            //Toast.makeText(SearchedUserDetailActivity.this, "", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", "Error: " + error.getMessage());
                Toast.makeText(SearchedUserDetailActivity.this, "Error fetching data.", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(stringRequest);
    }*/

    private void fetchHouseDetails(String houseNo, String seriesId) {

        ApiInterface apiInterface = getRetrofitInstance().create(ApiInterface.class);

        Call<HouseResponse> call = apiInterface.getHouseDetails(houseNo, seriesId);
        call.enqueue(new Callback<HouseResponse>() {
            @Override
            public void onResponse(Call<HouseResponse> call, Response<HouseResponse> response) {
                if (response.isSuccessful()) {

                    List<HouseDetail> houseDetails = response.body().getHouseDetails();
                    StringBuilder allDetails = new StringBuilder();
                    int counter = 1;

                    for (HouseDetail houseDetail : houseDetails) {
                        String data = counter + ")" + " Name: " + houseDetail.getName() + " " + houseDetail.getMiddleName() + " " + houseDetail.getSurname() +
                                "\nVoter ID: " + houseDetail.getVoterId() +
                                "\nBooth No: " + houseDetail.getBoothNo() +
                                "\nSr.No: " + houseDetail.getVotingSrNo() +
                                "\nVoting Center: " + houseDetail.getVotingCenter() + "\n\n";

                        allDetails.append(data);
                        counter++;
                    }

                    Details = allDetails.toString() + "*Aapla Sevak - Vinod Mapari*";
                    //Toast.makeText(SearchedUserDetailActivity.this, "Details: " + Details, Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(SearchedUserDetailActivity.this, "Response Error..!!", Toast.LENGTH_SHORT).show();
                    Log.e("Tag", "Response Error..");
                }
            }

            @Override
            public void onFailure(Call<HouseResponse> call, Throwable throwable) {
                Log.e("Tag", "Error.." + throwable.getLocalizedMessage());
                Toast.makeText(SearchedUserDetailActivity.this, "Error..", Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        //  getSearchList();
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
}