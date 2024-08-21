package com.vinodmapari.aaplasevak.Activity;

import static com.vinodmapari.aaplasevak.ApiConfig.ApiHandler.getRetrofitInstance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.vinodmapari.aaplasevak.ApiConfig.ApiInterface;
import com.vinodmapari.aaplasevak.Model.Constants;
import com.vinodmapari.aaplasevak.Model.LoggedInUser;
import com.vinodmapari.aaplasevak.Model.LoginResponseData;
import com.vinodmapari.aaplasevak.R;
import com.vinodmapari.aaplasevak.Util.SaveSharedPreference;
import com.vinodmapari.aaplasevak.VolleySingleton;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;

public class LoginActivity extends AppCompatActivity {
    Button button_login;
    EditText etMobile, etPassword;
    LottieAnimationView loader;
    private Vibrator vibrator;
    private String mobile = "";
    private String password = "";
    private RequestQueue rQueue;
    TextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button_login = (Button) findViewById(R.id.btnLogin);
        etMobile = (EditText) findViewById(R.id.etMobile);
        etPassword = (EditText) findViewById(R.id.etPassword);
        tvRegister = findViewById(R.id.tvRegister);

        loader = findViewById(R.id.loader);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);


        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });


        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validateUserData();

            }
        });

    }

    private void validateUserData() {

        //mobile = etMobile.getText().toString();
        //password = etPassword.getText().toString();

        if (etMobile.getText().toString().isEmpty()) {
            etMobile.setError("Please enter mobile number");
            etMobile.requestFocus();
            vibrator.vibrate(100);
        } else if (!(etMobile.getText().toString().length() == 10)) {
            etMobile.setError("Please enter valid mobile number");
            etMobile.requestFocus();
            vibrator.vibrate(100);
        } else if (etPassword.getText().toString().isEmpty()) {
            etPassword.setError("Please enter valid password");
            etPassword.requestFocus();
            vibrator.vibrate(100);
        } else {
            loader.setVisibility(View.VISIBLE);
            login();
        }

    }

   /* private void loginUser() {
        progress_login.setVisibility(View.VISIBLE);

        final String userName = edit_user.getText().toString().trim();
        final String password = edit_password.getText().toString().trim();
        String params = "&username=" + userName + "&password=" + password;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.loginURL + params,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progress_login.setVisibility(View.GONE);

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject jsonObject1 = jsonObject.getJSONObject("LOGGED_IN_USER");
                            if (jsonObject1.getString("error").equals("false")) {
                                final String id = jsonObject1.getString("id");
                                final String name = jsonObject1.getString("username");
                                final String password = jsonObject1.getString("password");

                                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefsUser", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                editor.putString("id", id);
                                editor.putString("username", name);
                                editor.putString("password", password);

                                editor.commit();

                                Toast.makeText(LoginActivity.this, "Login Successfully!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(LoginActivity.this, "user name or password is incorrect", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            progress_login.setVisibility(View.GONE);
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //          Toast.makeText(LoginActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                        progress_login.setVisibility(View.GONE);
                    }
                });


        VolleySingleton.getInstance(LoginActivity.this).addToRequestQueue(stringRequest);
    }*/

    public void login() {


        ApiInterface apiInterface = getRetrofitInstance().create(ApiInterface.class);

        Call<LoginResponseData> call = apiInterface.loginUser(etMobile.getText().toString(), etPassword.getText().toString());

        call.enqueue(new Callback<LoginResponseData>() {
            @Override
            public void onResponse(Call<LoginResponseData> call, retrofit2.Response<LoginResponseData> response) {
                if (response.isSuccessful()) {

                    loader.setVisibility(View.GONE);
                    LoginResponseData loginResponseData = response.body();
                    LoggedInUser loggedInUser = loginResponseData.getLoggedInUser();
                    String role = loggedInUser.getRole();
                    String id = loggedInUser.getId();
                    String msg = loggedInUser.getMessage();
                    String error = loggedInUser.getError();

                    SaveSharedPreference.setPrefUserId(LoginActivity.this, id);
                    SaveSharedPreference.setPrefUserId(LoginActivity.this, role);

                    Toast.makeText(LoginActivity.this, "Login Successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();

                } else {
                    loader.setVisibility(View.GONE);
                    Toast.makeText(LoginActivity.this, "Response Error..!!", Toast.LENGTH_SHORT).show();
                    Log.e("Api Response", "Response Error..");
                }
            }

            @Override
            public void onFailure(Call<LoginResponseData> call, Throwable throwable) {
                loader.setVisibility(View.GONE);
                Log.e("Api Response", "Error.." + throwable.getLocalizedMessage());
                Toast.makeText(LoginActivity.this, "Error.." + throwable.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}