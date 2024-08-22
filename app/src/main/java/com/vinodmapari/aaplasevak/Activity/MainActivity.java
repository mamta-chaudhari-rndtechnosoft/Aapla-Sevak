package com.vinodmapari.aaplasevak.Activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.navigation.NavigationView;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;
import com.vinodmapari.aaplasevak.Model.Constants;
import com.vinodmapari.aaplasevak.Model.HomeOptionsItem;
import com.vinodmapari.aaplasevak.Model.SliderModel;
import com.vinodmapari.aaplasevak.R;
import com.vinodmapari.aaplasevak.Util.SaveSharedPreference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    String androidID;
    String name;
    ArrayList<String> permissions = new ArrayList<>();
    ArrayList<String> permissionsToRequest;
    final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 101;
    public final static int ALL_PERMISSIONS_RESULT = 102;
    ArrayList<String> permissionsRejected = new ArrayList<>();
    CardView cdSurvey, cdSearch, cdSms, cdWhatsApp, cd_msg_member, cdSurveyorMember;
    private SliderView slider_home;
    SliderAdapter sliderAdapter;
    ArrayList<SliderModel> sliderModels;
    TextView tvSurvey, tvSearch, tvMsg, tv_whatsapp;
    ImageView imgSurvey, imgSearch, imgSms, imgWhatsApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkPer();

        SharedPreferences sharedPreferences1 = getSharedPreferences("MyPrefsUser", Context.MODE_PRIVATE);
        name = sharedPreferences1.getString("username", "");

        drawerLayout = findViewById(R.id.drawerLayout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        cdSurvey = findViewById(R.id.cd_survey);
        cdSearch = findViewById(R.id.cd_search);
        cdSms = findViewById(R.id.cd_msg);
        cdWhatsApp = findViewById(R.id.cd_whatsapp);
        cd_msg_member = findViewById(R.id.cd_msg_member);
        slider_home = (SliderView) findViewById(R.id.slider_home);

        tvSurvey = findViewById(R.id.tv_survey);
        tvSearch = findViewById(R.id.tv_search);
        tvMsg = findViewById(R.id.tv_msg);
        tv_whatsapp = findViewById(R.id.tv_whatsapp);

        imgSurvey = findViewById(R.id.iv_survey);
        imgSearch = findViewById(R.id.iv_search);
        imgSms = findViewById(R.id.iv_msg);
        imgWhatsApp = findViewById(R.id.iv_whatsapp);

        cdSurveyorMember = findViewById(R.id.cdSurveyorMember);

        homeOptions();
        //Toast.makeText(this, "Role: "+ SaveSharedPreference.getUserRole(MainActivity.this), Toast.LENGTH_SHORT).show();

        Slider();
        sliderModels = new ArrayList<>();

        cdSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, UserSurveyActivity.class);
                startActivity(i);
            }
        });


        cdSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(i);
            }
        });

        cdSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent i = new Intent(MainActivity.this, SendSmsActivity.class);
                Intent i = new Intent(MainActivity.this, PrintOptionActivity.class);
                startActivity(i);

            }
        });

        cdWhatsApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, WhatsappActivity.class);
                startActivity(i);
            }
        });

        cd_msg_member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SearchSurveyMemberActivity.class);
                startActivity(i);
            }
        });

        cdSurveyorMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SurveyorMemberListActivity.class);
                startActivity(i);
            }
        });


        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.isChecked()) {
                    item.setChecked(false);
                } else {
                    item.setChecked(true);
                }
                drawerLayout.closeDrawers();
                int id = item.getItemId();

                if (id == R.id.home) {
                    //startActivity(this);
                } else if (id == R.id.contact_us) {
                    startActivity(new Intent(MainActivity.this, ContactActivity.class));
                } else if (id == R.id.settings) {
                    startActivity(new Intent(MainActivity.this, SettingActivity.class));
                } else if (id == R.id.logout) {
                    AlertDialog alertbox = new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Aapla Sevak")
                            .setMessage("Are you sure you want to Exit?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int arg1) {

                                   /* final String deviceToken = "0";
                                    SharedPreferences sharedPreferences1 = getSharedPreferences("MyPrefsUser", Context.MODE_PRIVATE);
                                    final String id = sharedPreferences1.getString("id", "");*/

                                    try {
                                        androidID = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                    SaveSharedPreference.clearUserId(MainActivity.this);
                                    SaveSharedPreference.clearUserRole(MainActivity.this);
                                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                    startActivity(intent);
                                    finish();

                                    /*@SuppressLint("UseRequireInsteadOfGet") SharedPreferences sharedPreferences = Objects.requireNonNull(getSharedPreferences("MyPrefsUser", Context.MODE_PRIVATE));
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("username", "");
                                    editor.apply();*/
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int arg1) {
                                    dialog.dismiss();
                                }
                            }).show();

                    alertbox.getButton(alertbox.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                    alertbox.getButton(alertbox.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                }


                drawerLayout.closeDrawer(GravityCompat.START);
                //drawer.closeDrawer(Gravity.LEFT);
                return true;
            }
        });

        ImageButton menuButton = findViewById(R.id.menuBurger);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });

    }

    private void Slider() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.banner,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Constants.sliderModels.clear();
                        Constants.sliderModels = new ArrayList<>();

                        //Constants.sliderModels.add(new SliderModel("0",""));

                        //Response

                        // Log.d("TAG", "onResponse: "+response);

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            //    Log.d("TAG1", "onResponse: "+response);

                            JSONArray jsonArray = jsonObject.getJSONArray("HOME_BANNER");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                                String id = jsonObject1.getString("id");
                                String banner_image = jsonObject1.getString("banner_image");

                                sliderModels.add(new SliderModel(id, banner_image));

                            }
                            //sliderAdapter = new HomeFragment.SliderAdapter(this, sliderModels);
                            sliderAdapter = new SliderAdapter(MainActivity.this, sliderModels);
                            slider_home.setSliderAdapter(sliderAdapter);
                            slider_home.setIndicatorAnimation(IndicatorAnimationType.FILL);
                            slider_home.setIndicatorVisibility(false);
                            //slider_home.setIndicatorEnabled(true);
                            slider_home.setSliderTransformAnimation(SliderAnimations.CUBEINROTATIONTRANSFORMATION);
                            slider_home.setScrollTimeInSec(2);
                            slider_home.startAutoCycle();


                        } catch (JSONException e) {
                            e.printStackTrace();
                            //Toast.makeText(activity, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Error
                        //  Log.d("AddToCart->",error.toString());
                    }
                });
        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        //adding the string request to request queue
        requestQueue.add(stringRequest);

    }

    private static void displayImageOriginal(Context context, ImageView img, List<SliderModel> items, int position) {


        try {
            Glide.with(context).load(items.get(position).getBanner_image())
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(img);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public class SliderAdapter extends SliderViewAdapter<MainActivity.SliderAdapter.SliderAdapterVH> {

        private Context context;
        private List<SliderModel> mSliderItems;


        public SliderAdapter(Context context, List<SliderModel> mSliderItems) {
            this.context = context;
            this.mSliderItems = mSliderItems;
        }


        public void renewItems(List<SliderModel> sliderItems) {
            this.mSliderItems = sliderItems;
            notifyDataSetChanged();
        }


        public void deleteItem(int position) {
            this.mSliderItems.remove(position);
            notifyDataSetChanged();
        }


        public void addItem(SliderModel sliderItem) {
            this.mSliderItems.add(sliderItem);
            notifyDataSetChanged();
        }

        @Override
        public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {

            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_slider_image, null);
            //return new HomeFragment.SliderAdapter.SliderAdapterVH(inflate);
            return new MainActivity.SliderAdapter.SliderAdapterVH(inflate);

        }

        @Override
        public void onBindViewHolder(SliderAdapterVH viewHolder, int position) {
            SliderModel sliderItem = mSliderItems.get(position);


            displayImageOriginal(context, viewHolder.imageSlider, mSliderItems, position);
        }


        @Override
        public int getCount() {
            //slider view count could be dynamic size
            return mSliderItems.size();
        }

        class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

            ImageView imageSlider;

            public SliderAdapterVH(View itemView) {
                super(itemView);
                imageSlider = itemView.findViewById(R.id.image);

            }

        }
    }

    private void homeOptions() {
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.homePageOptions,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //progressBar.setVisibility(View.GONE);

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray homeOptionArray = jsonObject.getJSONArray("HOME_OPTION");

                            List<HomeOptionsItem> homeOptions = new ArrayList<>();
                            for (int i = 0; i < homeOptionArray.length(); i++) {
                                JSONObject homeOptionObject = homeOptionArray.getJSONObject(i);
                                String id = homeOptionObject.getString("id");
                                String name1 = homeOptionObject.getString("name1");
                                String image = homeOptionObject.getString("image");

                                // Toast.makeText(requireContext(), "ID: " + id + " " + "NAME: " + name1  , Toast.LENGTH_SHORT).show();
                                //  Log.d("Api Response","ID: " + id + " " + "NAME: " + name1);
                                //homeOptions.add(new HomeOption(id, name1, image));

                                homeOptions.add(new HomeOptionsItem(id, image, name1));
                            }

                            if (!homeOptions.isEmpty()) {
                                HomeOptionsItem firstOption = homeOptions.get(0);
                                tvSurvey.setText(firstOption.getName());
                                Picasso.get().load(firstOption.getImage()).into(imgSurvey);
                            }

                            if (!homeOptions.isEmpty()) {
                                HomeOptionsItem secondOption = homeOptions.get(1);
                                tvSearch.setText(secondOption.getName());
                                Picasso.get().load(secondOption.getImage()).into(imgSearch);
                            }

                            if (!homeOptions.isEmpty()) {
                                HomeOptionsItem thirdOption = homeOptions.get(2);
                                tvMsg.setText(thirdOption.getName());
                                Picasso.get().load(thirdOption.getImage()).into(imgSms);
                            }

                            if (!homeOptions.isEmpty()) {
                                HomeOptionsItem fourthOption = homeOptions.get(3);
                                tv_whatsapp.setText(fourthOption.getName());
                                Picasso.get().load(fourthOption.getImage()).into(imgWhatsApp);
                            }


                            // Example: Display the first item (customize this part as needed)


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "Error parsing JSON response.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //progressBar.setVisibility(View.GONE);
                Log.e("Tag", "Error: " + error.getLocalizedMessage());
                Toast.makeText(MainActivity.this, "Server taking too much time to load...", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(stringRequest);
    }


    public void checkPer() {
        permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        permissions.add(Manifest.permission.CAMERA);
        permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
        permissionsToRequest = findUnAskedPermissions(permissions);

        // check permissions
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (permissionsToRequest.size() > 0) {
                requestPermissions(permissionsToRequest.toArray(new String[permissionsToRequest.size()]),
                        ALL_PERMISSIONS_RESULT);

            }
        }
    }

    private ArrayList findUnAskedPermissions(ArrayList<String> wanted) {
        ArrayList result = new ArrayList();

        for (String perm : wanted) {
            if (!hasPermission(perm)) {
                result.add(perm);
            }
        }

        return result;
    }

    private boolean hasPermission(String permission) {
        if (canAskPermission()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return (checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED);
            }
        }
        return true;
    }

    private boolean canAskPermission() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean canUseExternalStorage = false;

        switch (requestCode) {
            case ALL_PERMISSIONS_RESULT:
                try {
                    for (String perms : permissionsToRequest) {
                        if (!hasPermission(perms)) {
                            permissionsRejected.add(perms);
                        }
                    }

                    if (permissionsRejected.size() > 0) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (shouldShowRequestPermissionRationale(permissionsRejected.get(0))) {
                                showMessageOKCancel("These permissions are mandatory for the application. Please allow access.",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                    requestPermissions(permissionsRejected.toArray(
                                                            new String[permissionsRejected.size()]), ALL_PERMISSIONS_RESULT);
                                                }
                                            }
                                        });
                                return;
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {

                }
                break;
            case MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    canUseExternalStorage = true;
                }
                if (!canUseExternalStorage) {
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.cannot_use_save_permission), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(MainActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("cancel", null)
                .create()
                .show();
    }

}