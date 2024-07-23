package com.vinodmapari.aaplasevak.Activity;

import static com.vinodmapari.aaplasevak.ApiConfig.ApiHandler.getRetrofitInstance;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vinodmapari.aaplasevak.ApiConfig.ApiInterface;
import com.vinodmapari.aaplasevak.EditFamilyAdapter;
import com.vinodmapari.aaplasevak.Model.DeleteMemberResponseData;
import com.vinodmapari.aaplasevak.Model.HouseDetail;
import com.vinodmapari.aaplasevak.Model.HouseResponse;
import com.vinodmapari.aaplasevak.R;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditMemberActivity extends AppCompatActivity implements EditFamilyAdapter.OnDeleteClickListener{

    ArrayList<HouseDetail> houseDetailArrayList;
    RecyclerView rvFamilyMember;
    EditFamilyAdapter editFamilyAdapter;
    String houseNo,seriesId;
    int SeriesId;
    LinearLayout layoutNoData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_member);


        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));

        toolbar.setTitle(Html.fromHtml("<b>" + "Edit Family Member" + "</b>"));
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        houseNo = getIntent().getStringExtra("house_no");
        seriesId = getIntent().getStringExtra("series_id");
        //Toast.makeText(this, "Series: " + seriesId + "house: " + houseNo, Toast.LENGTH_SHORT).show();

       /*if(seriesId.equalsIgnoreCase("A")){
           SeriesId = 1;
       } else if (seriesId.equalsIgnoreCase("B")) {
           SeriesId = 2;
       } else if (seriesId.equalsIgnoreCase("C")) {
           SeriesId = 3;
       }*/


        layoutNoData = findViewById(R.id.layoutNoData);
        rvFamilyMember = findViewById(R.id.rvEditFamilyMember);
        rvFamilyMember.setLayoutManager(new LinearLayoutManager(this));


        houseDetailArrayList = new ArrayList<>();

        getFamilyMember();

        editFamilyAdapter = new EditFamilyAdapter(EditMemberActivity.this, houseDetailArrayList);
        editFamilyAdapter.setOnDeleteClickListener(EditMemberActivity.this);
        rvFamilyMember.setAdapter(editFamilyAdapter);

    }

    private void getFamilyMember() {

        ApiInterface apiInterface = getRetrofitInstance().create(ApiInterface.class);

        Call<HouseResponse> call = apiInterface.getHouseDetails(houseNo,seriesId);

        call.enqueue(new Callback<HouseResponse>() {
            @Override
            public void onResponse(Call<HouseResponse> call, Response<HouseResponse> response) {
                if (response.isSuccessful()) {

                    //Toast.makeText(EditMemberActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    //HouseResponse houseResponse = response.body();
                    ArrayList<HouseDetail> houseDetails = response.body().getHouseDetails();
                    //houseDetailArrayList = houseDetails;
                    houseDetailArrayList.clear();
                    houseDetailArrayList.addAll(houseDetails);
                    editFamilyAdapter.notifyDataSetChanged();


                    if (houseDetails.isEmpty()) {
                        rvFamilyMember.setVisibility(View.GONE);
                        layoutNoData.setVisibility(View.VISIBLE);
                    } else {
                        rvFamilyMember.setVisibility(View.VISIBLE);
                        layoutNoData.setVisibility(View.GONE);
                    }

                    //Log.d("Tag","Data: " + houseDetails);
                    //Toast.makeText(EditMemberActivity.this, "Data: " + houseDetails.toString(), Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(EditMemberActivity.this, "Response Error..!!", Toast.LENGTH_SHORT).show();
                    Log.e("Tag", "Response Error..");
                }
            }

            @Override
            public void onFailure(Call<HouseResponse> call, Throwable throwable) {
                Log.e("Tag", "Error.." + throwable.getLocalizedMessage());
                Toast.makeText(EditMemberActivity.this, "Error..", Toast.LENGTH_SHORT).show();
            }
        });

    }



    public void deleteMember( int position,int memberId){

        ApiInterface apiInterface = getRetrofitInstance().create(ApiInterface.class);

        Call<DeleteMemberResponseData> call = apiInterface.deleteFamilyMember(memberId);

       // Toast.makeText(this, "ID: " + memberId, Toast.LENGTH_SHORT).show();
        call.enqueue(new Callback<DeleteMemberResponseData>() {
            @Override
            public void onResponse(Call<DeleteMemberResponseData> call, Response<DeleteMemberResponseData> response) {
                if(response.isSuccessful()){

                    //Toast.makeText(EditMemberActivity.this, "Success..!!", Toast.LENGTH_SHORT).show();
                    houseDetailArrayList.remove(position);
                    editFamilyAdapter.notifyItemRemoved(position);
                    Toast.makeText(EditMemberActivity.this, "Family Member Delete Successfully..!", Toast.LENGTH_SHORT).show();

                }
                else{
                    Log.e("Tag","Response not Success");
                    Toast.makeText(EditMemberActivity.this, "Response not Success..", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DeleteMemberResponseData> call, Throwable t) {
                Log.e("Tag","Error..." + t.getLocalizedMessage());
                Toast.makeText(EditMemberActivity.this, "Error..", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    @Override
    public void onDeleteMember(int position, int memberId) {
        //Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
        deleteMember(position,memberId);
    }

    @Override
    protected void onResume() {
        super.onResume();

        getFamilyMember();
    }
}