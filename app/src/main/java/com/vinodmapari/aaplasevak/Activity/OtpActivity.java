package com.vinodmapari.aaplasevak.Activity;

import static com.vinodmapari.aaplasevak.ApiConfig.ApiHandler.getRetrofitInstance;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.button.MaterialButton;
import com.vinodmapari.aaplasevak.ApiConfig.ApiInterface;
import com.vinodmapari.aaplasevak.Model.OtpResponseData;
import com.vinodmapari.aaplasevak.R;

import retrofit2.Response;
import retrofit2.Call;
import retrofit2.Callback;

public class OtpActivity extends AppCompatActivity {

    EditText etOtp1, etOtp2, etOtp3, etOtp4, etOtp5, etOtp6;
    MaterialButton btnContinue;
    Long mobile;
    Double otp;
    LottieAnimationView loader;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        etOtp1 = findViewById(R.id.etOtp1);
        etOtp2 = findViewById(R.id.etOtp2);
        etOtp3 = findViewById(R.id.etOtp3);
        etOtp4 = findViewById(R.id.etOtp4);
        etOtp5 = findViewById(R.id.etOtp5);
        etOtp6 = findViewById(R.id.etOtp6);
        loader = findViewById(R.id.loader);

        setEditTextListener(etOtp1, etOtp2, null);
        setEditTextListener(etOtp2, etOtp3, etOtp1);
        setEditTextListener(etOtp3, etOtp4, etOtp2);
        setEditTextListener(etOtp4, etOtp5, etOtp3);
        setEditTextListener(etOtp5, etOtp6, etOtp4);
        setEditTextListener(etOtp6, null, etOtp5);

        btnContinue = findViewById(R.id.btnOtpContinue);

        id = getIntent().getIntExtra("id", 0);
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //mProgress.show();
                String otpAll = etOtp1.getText().toString() +
                        etOtp2.getText().toString() +
                        etOtp3.getText().toString() +
                        etOtp4.getText().toString() +
                        etOtp5.getText().toString() +
                        etOtp6.getText().toString();

                //otp = Double.parseDouble(otpAll);
                //mobile = getIntent().getDoubleExtra("number", 0.0);

                if (otpAll.isEmpty() || otpAll.length() != 6) {
                    Toast.makeText(OtpActivity.this, "Please enter a valid OTP ", Toast.LENGTH_SHORT).show();
                } else {
                    otp = Double.parseDouble(otpAll);
                    //mobile = getIntent().getDoubleExtra("number", 0.0);
                    mobile = getIntent().getLongExtra("number", 0);
                    //SaveSharedPreference.setPrefUserMobile(OtpActivity.this,mobile);
                    loader.setVisibility(View.VISIBLE);
                    loginUser(mobile, otp);
                }

                // loginUser(mobile, otp);
            }
        });


    }

    private void setEditTextListener(final EditText currentEditText, final EditText nextEditText, final EditText previousEditText) {
        currentEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    if (nextEditText != null) {
                        nextEditText.requestFocus();
                    }
                } else if (s.length() == 0 && previousEditText != null) {
                    previousEditText.requestFocus();
                }
            }
        });
    }

    public void loginUser(Long mobile, Double otp) {
        ApiInterface apiInterface = getRetrofitInstance().create(ApiInterface.class);

        String Otp = String.valueOf(otp);

        Call<OtpResponseData> call = apiInterface.submitOtp(Otp, id);

        call.enqueue(new Callback<OtpResponseData>() {
            @Override
            public void onResponse(Call<OtpResponseData> call, Response<OtpResponseData> response) {
                if (response.isSuccessful()) {
                    //startActivity(new Intent(OtpActivity.this,MainActivity.class));
                    loader.setVisibility(View.GONE);
                    OtpResponseData otpResponseData = response.body();
                    String error = otpResponseData.getError();
                    String msg = otpResponseData.getMsg();

                    if (error.equals("false")) {
                        loader.setVisibility(View.GONE);
                        Toast.makeText(OtpActivity.this, "Register Successfully", Toast.LENGTH_SHORT).show();

                        //SaveSharedPreference.setPrefUserId(OtpActivity.this,id);

                        Intent intent = new Intent(OtpActivity.this, LoginActivity.class);
                        //intent.putExtra("id", SaveSharedPreference.getUserId(OtpActivity.this));
                        intent.putExtra("id", id);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();

                    } else if (error.equals("true")) {
                        loader.setVisibility(View.GONE);
                        Toast.makeText(OtpActivity.this, "Please Enter Correct Otp", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    loader.setVisibility(View.GONE);
                    Log.e("Tag", "Response Error..");
                    Toast.makeText(OtpActivity.this, "Response Error..", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OtpResponseData> call, Throwable t) {
                loader.setVisibility(View.GONE);
                Log.e("Tag", "Error.." + t.getLocalizedMessage());
                Toast.makeText(OtpActivity.this, "Error..", Toast.LENGTH_SHORT).show();
            }
        });


    }

}