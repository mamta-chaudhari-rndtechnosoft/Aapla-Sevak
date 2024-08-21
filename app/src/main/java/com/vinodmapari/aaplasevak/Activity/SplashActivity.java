package com.vinodmapari.aaplasevak.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import com.vinodmapari.aaplasevak.Model.Constants;
import com.vinodmapari.aaplasevak.Model.HomeSplashItem;
import com.vinodmapari.aaplasevak.Model.HomeSplashResponse;
import com.vinodmapari.aaplasevak.R;
import com.vinodmapari.aaplasevak.Util.SaveSharedPreference;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 4000;
    ImageView imgLogo;
    TextView tvTitleSplash;
    AVLoadingIndicatorView progress_splash;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imgLogo = findViewById(R.id.imageView2);
        tvTitleSplash = findViewById(R.id.tvTitleSplash);
        progress_splash = findViewById(R.id.progress_splash);

        isInternetOn();
        fetchSplashData();

    }

    public final boolean isInternetOn() {

        // get Connectivity Manager object to check connection
        ConnectivityManager connec =
                (ConnectivityManager) getSystemService(getBaseContext().CONNECTIVITY_SERVICE);

        // Check for network connections
        if (connec.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED) {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    //SharedPreferences sharedPreferences1 = getSharedPreferences("MyPrefsUser", Context.MODE_PRIVATE);
                    //String username = sharedPreferences1.getString("username", "");
                    String userId = SaveSharedPreference.getUserId(SplashActivity.this);

                    if (userId.equalsIgnoreCase("")) {
                        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    /*
                    else if (!username.equalsIgnoreCase("")) {
                        //Intent intent = new Intent(SplashActivity.this,DashBoardActivity.class);
                        //MainActivity
                        Intent intent = new Intent(SplashActivity.this, RegisterActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    */
                    else {
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }, 2000);

            return true;

        } else if (
                connec.getNetworkInfo(0).getState() == NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() == NetworkInfo.State.DISCONNECTED) {

            android.app.AlertDialog.Builder builderDialog = new android.app.AlertDialog.Builder(SplashActivity.this);

            builderDialog.setMessage("Internet connection not available check your internet connection").setCancelable(false)
                    .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    finish();
                                    dialog.cancel();

                                }
                            }
                    );

            android.app.AlertDialog alertDialog = builderDialog.create();

            alertDialog.show();

            Toast.makeText(this, " Internet Connection Not Available ", Toast.LENGTH_LONG).show();
            return false;
        }
        return false;
    }


    private void fetchSplashData() {

        progress_splash.setVisibility(View.VISIBLE);

        String url = Constants.splashURL;

        RequestQueue requestQueue = Volley.newRequestQueue(SplashActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Handle the JSON response
                        progress_splash.setVisibility(View.GONE);

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray splashArray = jsonObject.getJSONArray("HOME_SPLASH");

                            List<HomeSplashItem> splashItems = new ArrayList<>();
                            for (int i = 0; i < splashArray.length(); i++) {
                                JSONObject splashObject = splashArray.getJSONObject(i);
                                String id = splashObject.getString("id");
                                String image = splashObject.getString("image");
                                String name = splashObject.getString("name1");

                                Picasso.get().load(image).into(imgLogo);
                                tvTitleSplash.setText(name);

                                splashItems.add(new HomeSplashItem(id, image, name));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            progress_splash.setVisibility(View.GONE);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle error
                Log.e("TAG", "Error fetching splash data: " + error.toString());
                progress_splash.setVisibility(View.GONE);
            }
        });
        // Add the request to the RequestQueue
        requestQueue.add(stringRequest);
    }


}