package com.vinodmapari.aaplasevak.Activity;

import static com.vinodmapari.aaplasevak.ApiConfig.ApiHandler.getRetrofitInstance;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.toptoche.searchablespinnerlibrary.SearchableSpinner;
import com.vinodmapari.aaplasevak.ApiConfig.ApiInterface;
import com.vinodmapari.aaplasevak.CustomAdapter;
import com.vinodmapari.aaplasevak.Model.Colony;
import com.vinodmapari.aaplasevak.Model.SurveyList;
import com.vinodmapari.aaplasevak.Model.UserDetail;
import com.vinodmapari.aaplasevak.Model.UserDetailResponse;
import com.vinodmapari.aaplasevak.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateMemberActivity extends AppCompatActivity {

    String colony, row, watersupply, series;
    ArrayList<SurveyList> surveyLists;
    ArrayList<Colony> colonies;
    CustomAdapter adapter;
    int selected_series, selected_status, selected_colony, selected_row, selected_water_supply;
    String series_id, status_id, colony_id, row_id, water_supply_id, boothNo, serialNo;
    Button btnSubmit;
    long selected_series_id, selected_colony_id;
    String gender;
    RadioButton selectedRadioButton;
    RadioGroup radioGroup;
    TextView tv1, tv2;
    SwipeRefreshLayout swipe_refresh;
    SearchableSpinner spinner_series, spinner_status, spinner_colony, spinner_row, spinner_water_Supply;
    EditText house_number, voting_center, dob, name, middle_name, surname, mob1, mob2, qualification, caste, relation, voterID, edevent, adharcard, etBooth, etSerial;
    private String colonyName;

    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_member);


        id = getIntent().getIntExtra("id", 0);





    }


    public void getUserDetail() {

        ApiInterface apiInterface = getRetrofitInstance().create(ApiInterface.class);

        Call<UserDetailResponse> call = apiInterface.getUserDetail(id);
        call.enqueue(new Callback<UserDetailResponse>() {
            @Override
            public void onResponse(Call<UserDetailResponse> call, Response<UserDetailResponse> response) {
                if (response.isSuccessful()) {

                    ArrayList<UserDetail> userDetails = response.body().getUserDetails();
                    int id = userDetails.get(0).getId();
                    String name = userDetails.get(0).getName();
                    String middleName = userDetails.get(0).getMiddleName();
                    String surname = userDetails.get(0).getSurname();
                    String votingCenter = userDetails.get(0).getVotingCenter();
                    String boothNo = userDetails.get(0).getBoothNo();
                    int votingSrNo = userDetails.get(0).getVotingSrNo();
                    String seriesId = userDetails.get(0).getSeriesId();
                    int colonyId = userDetails.get(0).getColonyId();
                    int rowId = userDetails.get(0).getRowId();
                    String gender = userDetails.get(0).getGender();
                    String mobile1 = userDetails.get(0).getMobile1();
                    String mobile2 = userDetails.get(0).getMobile2();
                    String dob = userDetails.get(0).getDob();
                    String qualification = userDetails.get(0).getQualification();
                    String caste = userDetails.get(0).getCaste();
                    String relation = userDetails.get(0).getRelation();
                    String event = userDetails.get(0).getEvent();
                    String adharCard = userDetails.get(0).getAdharCard();
                    int waterSupplyId = userDetails.get(0).getWatersupplyId();
                    int memberId = userDetails.get(0).getMemberId();
                    String voterId = userDetails.get(0).getVoterId();





                } else {
                    Toast.makeText(UpdateMemberActivity.this, "Response Error..!!", Toast.LENGTH_SHORT).show();
                    Log.e("Tag", "Response Error..");
                }
            }

            @Override
            public void onFailure(Call<UserDetailResponse> call, Throwable throwable) {
                Log.e("Tag", "Error.." + throwable.getLocalizedMessage());
                Toast.makeText(UpdateMemberActivity.this, "Error..", Toast.LENGTH_SHORT).show();
            }
        });


    }

}