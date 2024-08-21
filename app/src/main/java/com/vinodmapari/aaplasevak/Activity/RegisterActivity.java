package com.vinodmapari.aaplasevak.Activity;

import static com.vinodmapari.aaplasevak.ApiConfig.ApiHandler.getRetrofitInstance;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.vinodmapari.aaplasevak.ApiConfig.ApiInterface;
import com.vinodmapari.aaplasevak.Model.RegisterBody;
import com.vinodmapari.aaplasevak.Model.RegisterResponseData;
import com.vinodmapari.aaplasevak.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    EditText etName, etMiddleName, etSurName, etNumber, etPassword;
    MaterialButton btnRegister;
    LottieAnimationView loader;
    String name, middleName, surName, mobile;
    TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = findViewById(R.id.etName);
        etMiddleName = findViewById(R.id.etMiddleName);
        etSurName = findViewById(R.id.etSurName);
        etNumber = findViewById(R.id.etMobile);
        etPassword = findViewById(R.id.etPassword);
        loader = findViewById(R.id.loader);
        btnRegister = findViewById(R.id.btnRegister);
        tvLogin = findViewById(R.id.tvLogin);

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etName.getText().toString().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Enter Name", Toast.LENGTH_SHORT).show();
                } else if (etMiddleName.getText().toString().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Enter Middle Name", Toast.LENGTH_SHORT).show();
                } else if (etSurName.getText().toString().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Enter Surname", Toast.LENGTH_SHORT).show();
                } else if (etPassword.getText().toString().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
                } else if (etNumber.getText().toString().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Enter Mobile Name", Toast.LENGTH_SHORT).show();
                } else if (!(etNumber.getText().toString().length() == 10)) {
                    Toast.makeText(RegisterActivity.this, "Mobile Number must be 10 digit long.", Toast.LENGTH_SHORT).show();
                } else {
                    loader.setVisibility(View.VISIBLE);
                    registerUser();
                }
            }
        });


    }

    public void registerUser() {

        ApiInterface apiInterface = getRetrofitInstance().create(ApiInterface.class);

        RegisterBody registerBody = new RegisterBody(etName.getText().toString(), etMiddleName.getText().toString(),
                etSurName.getText().toString(), etNumber.getText().toString(), etPassword.getText().toString());


        Call<RegisterResponseData> call = apiInterface.registerUser(registerBody);

        call.enqueue(new Callback<RegisterResponseData>() {
            @Override
            public void onResponse(Call<RegisterResponseData> call, Response<RegisterResponseData> response) {
                if(response.isSuccessful()){

                    loader.setVisibility(View.GONE);

                    RegisterResponseData responseData = response.body();
                    String status = responseData.getStatus();
                    String message = responseData.getMessage();
                    int id = responseData.getId();

                    Toast.makeText(RegisterActivity.this, "Status: " + status + "\nmessage: " + message, Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(RegisterActivity.this, OtpActivity.class);
                    intent.putExtra("id",id);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
                else{
                    loader.setVisibility(View.GONE);
                    Toast.makeText(RegisterActivity.this, "Response Error..!!", Toast.LENGTH_SHORT).show();
                    Log.e("Api Response", "Response Error..");
                }
            }

            @Override
            public void onFailure(Call<RegisterResponseData> call, Throwable throwable) {
                loader.setVisibility(View.GONE);
                Log.e("Api Response", "Error.." + throwable.getLocalizedMessage());
                Toast.makeText(RegisterActivity.this, "Error.." + throwable.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}