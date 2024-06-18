package com.vinodmapari.aaplasevak.Activity;

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
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.vinodmapari.aaplasevak.Model.Constants;
import com.vinodmapari.aaplasevak.Model.HouseDetail;
import com.vinodmapari.aaplasevak.Model.MainMemberDetail;
import com.vinodmapari.aaplasevak.Model.SearchList;
import com.vinodmapari.aaplasevak.R;
import com.vinodmapari.aaplasevak.SearchAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchedUserDetailActivity extends AppCompatActivity {

    Button btnAdd, btnEdit;
    ImageView ivShare, ivWhatsapp, ivCall, ivEmail, ivSaveContact;
    private static final int REQUEST_CALL = 101;

    ArrayList<MainMemberDetail> mainMemberDetails;

    ArrayList<SearchList> searchLists;

    String id, member_id, name, series, middle_name, surname, gender, phone, dob, phone2, caste, houseNo, status, relation, watersupply, voterID, adhar, votingCenter, event, row, colony, qualification;
    String srNo;
    String Details;

    TextView tv_Name, tv_Gender, tv_Mob, tv_Series, tv_mob2, tv_houseNo, tv_colony, tv_whatsapp_no, tv_dob, tv_qualificatoon, tv_caste, tv_status, tv_relation, tv_event, tv_voterID,
            tv_adhar, tv_watersupply, tv_voting_center, tv_row;

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
        tv_relation = findViewById(R.id.tv_relation);
        tv_caste = findViewById(R.id.tv_caste);
        tv_adhar = findViewById(R.id.tv_adhar);
        tv_watersupply = findViewById(R.id.tv_waterSupply);
        tv_voterID = findViewById(R.id.tv_voterID);
        tv_voting_center = findViewById(R.id.tv_voting_center);
        tv_event = findViewById(R.id.tv_event);
        tv_status = findViewById(R.id.tv_status);
        tv_row = findViewById(R.id.tv_row);
        tv_qualificatoon = findViewById(R.id.tv_qualification);
        tv_Gender = findViewById(R.id.gender);

        searchLists = new ArrayList<>();

        getSearchList();

        ////////////////////////////////////////////////////////////////////////////////////////////

        Intent in = getIntent();
        name = in.getStringExtra("name");
        voterID = in.getStringExtra("voter_id");
        adhar = in.getStringExtra("adhar_card");
        middle_name = in.getStringExtra("middle_name");
        surname = in.getStringExtra("surname");
        houseNo = in.getStringExtra("house_no");
        gender = in.getStringExtra("gender");
        phone = in.getStringExtra("mobile1");
        phone2 = in.getStringExtra("mobile2");
        dob = in.getStringExtra("dob");
        colony = in.getStringExtra("colony_id");
        qualification = in.getStringExtra("qualification");
        status = in.getStringExtra("status_id");
        id = in.getStringExtra("id");
        votingCenter = in.getStringExtra("voting_center");
        series = in.getStringExtra("series_no");
        row = in.getStringExtra("row_id");
        event = in.getStringExtra("event");
        relation = in.getStringExtra("relation");
        caste = in.getStringExtra("caste");
        watersupply = in.getStringExtra("water_Supply_id");
        member_id = in.getStringExtra("member_id");


        //Toast.makeText(SearchedUserDetailActivity.this, "member-id= "+member_id, Toast.LENGTH_SHORT).show();


        ////////////////////////////////////////////////////////////////////////////////////////////


        tv_voterID.setText(voterID);
        tv_Name.setText(surname + " " + name + " " + middle_name);
        tv_adhar.setText(adhar);
        tv_houseNo.setText(houseNo);
        tv_Gender.setText(gender);
        tv_qualificatoon.setText(qualification);
        tv_relation.setText(relation);
        tv_Series.setText(series);
        tv_colony.setText(colony);
        tv_row.setText(row);
        tv_status.setText(status);
        tv_voterID.setText(voterID);
        tv_watersupply.setText(watersupply);
        tv_voting_center.setText(votingCenter);
        tv_caste.setText(caste);
        tv_dob.setText(dob);
        tv_event.setText(event);
        tv_Mob.setText("+91" + phone);
        tv_mob2.setText("+91" + phone2);




        /*tv_Mob.setText(phone);
        tv_mob2.setText(phone2);*/


        ////////////////////////////////////////////////////////////////////////////////////////////

//        name = tv_Name.getText().toString();
//        phone = tv_Mob.getText().toString();
//        colony= tv_colony.getText().toString();
//        row= tv_row.getText().toString();
//        houseNo= tv_houseNo.getText().toString();
//        watersupply= tv_watersupply.getText().toString();
//        voterID= tv_voterID.getText().toString();
//        adhar= tv_adhar.getText().toString();
//        dob= tv_dob.getText().toString();
//        qualification= tv_qualificatoon.getText().toString();
//        phone2= tv_mob2.getText().toString();
//        caste= tv_caste.getText().toString();
//        relation= tv_relation.getText().toString();
//        status=tv_status.getText().toString();
//        gender=tv_Gender.getText().toString();
//        series=tv_Series.getText().toString();


        fetchHouseDetails(houseNo);

        mainMemberDetails = new ArrayList<>();

       /* String Details= "Name : "+surname+""+name+""+middle_name+"\n"+"Series No."+series+"\n"+"Colony : "+colony+"\n"+"Row : "+row+"\n"+"HouseNo : "+houseNo+"\n"
                +"Dob : "+dob+"\n"+"Gender : "+gender+"\n"+"Mobile1 : "+phone+"\n"+"Mobile2 : "+phone2+"\n"+"Qualification : "+qualification+"\n"
                +"Caste : "+caste+"\n"+"Relation : "+relation+"\n"+"Status : "+status+"\n"+"VoterID : "+voterID+"\n"+"Adhar Card : "+adhar+"\n"+"WaterSupply : "+watersupply+"\n"
                +"Voting-Center : "+votingCenter;*/


       /* String Details =
                "Name : " + surname + "" + name + "" + middle_name + "\n"
                        + "Series No.: " + series + "\n"
                        + "VoterID : " + voterID + "\n"
                        + "Voting-Center : " + votingCenter;*/




        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                getMainMemberDetails();

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

                //Toast.makeText(getApplicationContext(), ""+series, Toast.LENGTH_SHORT).show();

                startActivity(intent);

                //Toast.makeText(SearchedUserDetailActivity.this, ""+surname, Toast.LENGTH_SHORT).show();


            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SearchedUserDetailActivity.this, EditMemberActivity.class);

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
                    String sAux = "\n" + "User Details" + "\n\n";
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
//                Intent intent = new Intent(Intent.ACTION_SEND);
//
//                intent.setType("text/plain");
//                intent.setPackage("com.whatsapp");
//
//                // Give your message here
//                intent.putExtra(Intent.EXTRA_TEXT, Details);
//
//                // Checking whether Whatsapp
//                // is installed or not
//                if (intent.resolveActivity(
//                        getPackageManager()) == null) {
//                    Toast.makeText(SearchedUserDetailActivity.this, "Please install whatsapp first.", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                // Starting Whatsapp
//                startActivity(intent);


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
                Toast.makeText(this, "permission denied", Toast.LENGTH_SHORT).show();
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


    public void getSearchList() {


        final RequestQueue requestQueue = Volley.newRequestQueue(SearchedUserDetailActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.search_list + "&name=" + name + "&middle_name=" + middle_name + "&surname=" + surname + "&voter_id=" + voterID + "&adhar_card=" + adhar, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                searchLists.clear();
                searchLists = new ArrayList<>();
                try {
                    JSONObject json = new JSONObject(response);
                    //   Log.d("search", response);

                    JSONArray jsonArray = json.getJSONArray("SEARCH");

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject object = jsonArray.getJSONObject(i);
                        String id = object.getString("id");
                        String name = object.getString("name");
                        String middle_name = object.getString("middle_name");
                        String surname = object.getString("surname");
                        String voterId = object.getString("voter_id");
                        String adhar_card = object.getString("adhar_card");
                        String dob = object.getString("dob");
                        String event = object.getString("event");
                        String qualification = object.getString("qualification");
                        String relation = object.getString("relation");
                        String mobile1 = object.getString("mobile1");
                        String mobile2 = object.getString("mobile2");
                        String row_name = object.getString("row_name");
                        String gender = object.getString("gender");
                        String house_no = object.getString("house_no");
                        String series_name = object.getString("series_name");
                        String status_name = object.getString("status_name");
                        String colony_name = object.getString("colony_name");
                        String slot_name = object.getString("slot_name");
                        String caste = object.getString("caste");
                        String voting_center = object.getString("voting_center");
                        String member_id = object.getString("member_id");


                        searchLists.add(new SearchList(id, house_no, series_name, colony_name, row_name, gender, name, middle_name, surname, mobile1, mobile2, dob, qualification, caste, status_name, relation, event, voterId, adhar_card, slot_name, voting_center, member_id));


                        tv_houseNo.setText(house_no);
                        Toast.makeText(SearchedUserDetailActivity.this, "" + tv_houseNo.getText().toString(), Toast.LENGTH_SHORT).show();
                        tv_dob.setText(dob);


                    }


                } catch (JSONException e) {
                    e.printStackTrace();

                    //Toast.makeText(SearchActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
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


    private void fetchHouseDetails(String houseNo) {
        //String url = API_URL + "?" + HOUSE_NO_PARAM + "=" + houseNo;

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.houseDetail + "&house_no=" + houseNo,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray houseDetailArray = jsonObject.getJSONArray("HOUSE_DETAIL");

                            List<HouseDetail> houseDetails = new ArrayList<>();
                            for (int i = 0; i < houseDetailArray.length(); i++) {
                                JSONObject houseDetailObject = houseDetailArray.getJSONObject(i);
                                String name = houseDetailObject.getString("name");
                                String middleName = houseDetailObject.getString("middle_name");
                                String surname = houseDetailObject.getString("surname");
                                String votingCenter = houseDetailObject.getString("voting_center");
                                String boothNo = houseDetailObject.getString("booth_no");
                                int votingSrNo = houseDetailObject.getInt("voting_sr_no");
                                String voterId = houseDetailObject.getString("voter_id");

                                HouseDetail houseDetail = new HouseDetail(name, middleName, surname, votingCenter, boothNo, votingSrNo, voterId);
                                houseDetails.add(houseDetail);

                                if (!houseDetails.isEmpty()) {
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
                                }
                            }

                            // Example: Log the first item in the list
                           /* if (!houseDetails.isEmpty()) {
                                HouseDetail firstHouseDetail = houseDetails.get(0);
                                Log.d("TAG", "Name: " + firstHouseDetail.getName() + " " +
                                        "Voting Center: " + firstHouseDetail.getVotingCenter());
                                // You can update UI or perform other actions here
                            }*/

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(SearchedUserDetailActivity.this, "Error parsing JSON response.", Toast.LENGTH_SHORT).show();
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