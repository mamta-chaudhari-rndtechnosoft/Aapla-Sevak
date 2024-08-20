package com.vinodmapari.aaplasevak.Activity;

import static com.vinodmapari.aaplasevak.ApiConfig.ApiHandler.getRetrofitInstance;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintManager;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.vinodmapari.aaplasevak.ApiConfig.ApiInterface;
import com.vinodmapari.aaplasevak.Model.FamilyDetailsItem;
import com.vinodmapari.aaplasevak.Model.HouseDetail;
import com.vinodmapari.aaplasevak.Model.PrintHouseDetailResponse;
import com.vinodmapari.aaplasevak.PrintFamilyListAdapter;
import com.vinodmapari.aaplasevak.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FamilyPrintActivity extends AppCompatActivity {

    EditText etHouseNo;
    String houseNo;
    MaterialToolbar toolbar;
    RecyclerView rvFamilyList;
    PrintFamilyListAdapter adapter;
    ArrayList<FamilyDetailsItem> familyDetailsItemArrayList;
    ImageButton imgSearch;
    String Details;

    Button btnPrint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_print);

        toolbar = findViewById(R.id.toolbar);
        etHouseNo = findViewById(R.id.etHouseNo);
        rvFamilyList = findViewById(R.id.rvFamilyList);
        imgSearch = findViewById(R.id.imgSearch);
        btnPrint = findViewById(R.id.btnPrint);

        houseNo = etHouseNo.getText().toString();
        familyDetailsItemArrayList = new ArrayList<>();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etHouseNo.getText().toString().isEmpty()){
                    Toast.makeText(FamilyPrintActivity.this, "Please Enter House No. ", Toast.LENGTH_SHORT).show();
                }else{
                    getFamilyDetailsForPrint();
                    printFamilyDetails();
                }
            }
        });

        rvFamilyList.setLayoutManager(new LinearLayoutManager(FamilyPrintActivity.this));

        btnPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etHouseNo.getText().toString().isEmpty()){
                    Toast.makeText(FamilyPrintActivity.this, "Please Enter House No. ", Toast.LENGTH_SHORT).show();
                }else{
                    printFamilyDetails();
                    btnPrintFamily();
                }
            }
        });

    }

    public void getFamilyDetailsForPrint() {
        ApiInterface apiInterface = getRetrofitInstance().create(ApiInterface.class);

        Call<PrintHouseDetailResponse> call = apiInterface.printHouseDetail(etHouseNo.getText().toString());

        call.enqueue(new Callback<PrintHouseDetailResponse>() {
            @Override
            public void onResponse(Call<PrintHouseDetailResponse> call, Response<PrintHouseDetailResponse> response) {
                if (response.isSuccessful()) {
                       PrintHouseDetailResponse houseDetailResponse = response.body();
                       familyDetailsItemArrayList = houseDetailResponse.getFamilyDetailsItems();
                       adapter = new PrintFamilyListAdapter(FamilyPrintActivity.this,familyDetailsItemArrayList);
                       rvFamilyList.setAdapter(adapter);

                } else {
                    Toast.makeText(FamilyPrintActivity.this, "Response Error..!!", Toast.LENGTH_SHORT).show();
                    Log.e("Api Response", "Response Error..");
                }
            }

            @Override
            public void onFailure(Call<PrintHouseDetailResponse> call, Throwable throwable) {
                Log.e("Api Response", "Error.." + throwable.getLocalizedMessage());
                Toast.makeText(FamilyPrintActivity.this, "Error.." + throwable.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void printFamilyDetails(){
        ApiInterface apiInterface = getRetrofitInstance().create(ApiInterface.class);

        Call<PrintHouseDetailResponse> call = apiInterface.printHouseDetail(etHouseNo.getText().toString());
        call.enqueue(new Callback<PrintHouseDetailResponse>() {
            @Override
            public void onResponse(Call<PrintHouseDetailResponse> call, Response<PrintHouseDetailResponse> response) {
                if (response.isSuccessful()) {

                    ArrayList<FamilyDetailsItem> houseDetails = response.body().getFamilyDetailsItems();
                    StringBuilder allDetails = new StringBuilder();
                    int counter = 1;

                    for (FamilyDetailsItem houseDetail : houseDetails) {

                        String voterId = houseDetail.getVoterId();
                        String boothNo = houseDetail.getBoothNo();
                        String votingSrNo = String.valueOf(houseDetail.getSerialNo());

                        //Toast.makeText(SearchedUserDetailActivity.this, "voterId: " + voterId + " booth: " + boothNo  + " voting: " + votingSrNo, Toast.LENGTH_SHORT).show();

                        /*if (voterId != null && boothNo != null  &&
                                !voterId.equalsIgnoreCase("") &&
                                !boothNo.equalsIgnoreCase("") &&
                                !votingSrNo.equalsIgnoreCase("0")) {
                            String data = counter + ")" + " Name: " + houseDetail.getName() + " " + houseDetail.getMiddleName() + " " + houseDetail.getSurname() +
                                    "\nVoter ID: " + voterId +
                                    "\nBooth No: " + boothNo +
                                    "\nSr.No: " + votingSrNo +
                                    "\nVoting Center: " + houseDetail.getVotingCenter() + "\n\n";

                            allDetails.append(data);
                            counter++;
                        }*/


                        String data = counter + ")" + " Name: " + houseDetail.getName() + " " + houseDetail.getMiddleName() + " " + houseDetail.getSurname() +
                                "\nVoter ID: " + voterId +
                                "\nBooth No: " + boothNo +
                                "\nSr.No: " + votingSrNo +
                                "\nVoting Center: " + houseDetail.getVotingCenter() + "\n\n";

                        allDetails.append(data);
                        counter++;
                    }

                    Details = allDetails.toString() + "*Aapla Sevak - Vinod Mapari*";

                } else {
                    Toast.makeText(FamilyPrintActivity.this, "Response Error..!!", Toast.LENGTH_SHORT).show();
                    Log.e("Api Response", "Response Error..");
                }
            }

            @Override
            public void onFailure(Call<PrintHouseDetailResponse> call, Throwable throwable) {
                Log.e("Api Response", "Error.." + throwable.getLocalizedMessage());
                Toast.makeText(FamilyPrintActivity.this, "Error.." + throwable.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getBase64StringFromDrawable(int drawableId) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), drawableId);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }


    public void btnPrintFamily(){

        String headerBase64 = getBase64StringFromDrawable(R.drawable.header_demo);
        String footerBase64 = getBase64StringFromDrawable(R.drawable.footer_demo);

        /*String htmlContent = "<html>" +
                "<head>" +
                "<style>" +
                "@page { size: auto; margin: 0mm; }" + // No extra margin around the page
                "body { margin: 0; padding: 0; }" +
                ".page-container { position: relative; padding-top: 70px; padding-bottom: 70px; }" + // Padding for content to avoid overlap
                ".header, .footer { width: 100%; text-align: center; position: absolute; left: 0; right: 0; }" +
                ".header { top: 0; height: 50px; padding: 10px 0; box-sizing: border-box; background-color: #fff; }" + // Header styles
                ".footer { bottom: 0; height: 50px; padding: 10px 0; box-sizing: border-box; background-color: #fff; }" + // Footer styles
                ".content { padding-left: 50px; padding-right: 50px; }" + // Padding for content
                "</style>" +
                "</head>" +
                "<body>" +
                "<div class='header'>" +
                "<img src='data:image/png;base64," + headerBase64 + "' alt='Header Image' style='width:100%;height:auto;'>" +
                "</div>" +
                "<div class='page-container'>" +
                "<div class='content'>" +
                Details.replace("\n", "<br>") +
                Details.replace("\n", "<br>") + // Replacing \n with <br>
                "</div>" +
                "<div class='footer'>" +
                "<img src='data:image/png;base64," + footerBase64 + "' alt='Footer Image' style='width:100%;height:auto;'>" +
                "</div>" +
                "</div>" +
                "</body>" +
                "</html>";*/


        /*String htmlContent = "<html>" +
                "<body>" +
                "<div style='text-align: center;'>" +
                "<img src='data:image/png;base64," + headerBase64 + "' alt='Header Image' style='width:100%;height:auto;'>" +
                "</div>" +
                "<div style='margin-top: 20px;'>" +
                 Details.replace("\n", "<br>") + // Replacing \n with <br>
                "</div>" +
                "<div style='text-align: center; margin-top: 20px;'>" +
                "<img src='data:image/png;base64," + footerBase64 + "' alt='Footer Image' style='width:100%;height:auto;'>" +
                "</div>" +
                "</body>" +
                "</html>";*/

        /*String htmlContent = "<html>" +
                "<head>" +
                "<style>" +
                "@page { size: auto; margin: 0mm; }" +
                "body { margin: 0; padding: 0; }" +
                ".header, .footer { width: 100%; text-align: center; position: fixed; left: 0; right: 0; }" +
                ".header { top: 0; }" + // Adjust height and padding
                ".footer { bottom: 0; padding: 0px}" + // Adjust height and padding
                ".content { margin-top: 200px; margin-bottom: 200px; padding-left:50px; padding-right:50px; }" + // Adjust these values to create space for header and footer
                "</style>" +
                "</head>" +
                "<body>" +
                "<div class='header'>" +
                "<img src='data:image/png;base64," + headerBase64 + "' alt='Header Image' style='width:100%; height:auto;'>" +
                "</div>" +
                "<div class='content'>" +
                Details.replace("\n", "<br>") + // Replacing \n with <br>
                "</div>" +
                "<div class='footer'>" +
                "<img src='data:image/png;base64," + footerBase64 + "' alt='Footer Image' style='width:100%; height:auto;'>" +
                "</div>" +
                "</body>" +
                "</html>";*/

        String htmlContent = "<html>" +
                "<head>" +
                "<style>" +
                "@page { size: auto; margin: 0mm; }" +
                "body { margin: 0; padding: 0; }" +
                ".header, .footer { width: 100%; text-align: center; position: fixed; left: 0; right: 0; }" +
                ".header { top: 0; }" +
                ".footer { bottom: 0; padding: 0px}" +
                ".content { margin-top: 200px; margin-bottom: 200px; padding-left: 50px; padding-right: 50px; }" +
                "body { margin-top: 250px; margin-bottom: 100px; }" + // Ensure content starts after the header and ends before the footer
                "</style>" +
                "</head>" +
                "<body>" +
                "<div class='header'>" +
                "<img src='data:image/png;base64," + headerBase64 + "' alt='Header Image' style='width:100%; height:auto;'>" +
                "</div>" +
                "<div class='content'>" +
                Details.replace("\n", "<br>") +
                "</div>" +
                "<div class='footer'>" +
                "<img src='data:image/png;base64," + footerBase64 + "' alt='Footer Image' style='width:100%; height:auto;'>" +
                "</div>" +
                "</body>" +
                "</html>";

        WebView webView = new WebView(this);
        //webView.loadData(htmlContent,"text/html","UTF-8");
        webView.loadDataWithBaseURL(null,htmlContent,"text/html","UTF-8",null);

        PrintManager printManager = (PrintManager) getSystemService(PRINT_SERVICE);
        PrintDocumentAdapter printDocumentAdapter = webView.createPrintDocumentAdapter("TextView_PrintDemo");
        printManager.print("TextView Print Test",printDocumentAdapter,new PrintAttributes.Builder().build());
    }

}