package com.vinodmapari.aaplasevak.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

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
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;
import com.vinodmapari.aaplasevak.Fragment.ContactFragment;
import com.vinodmapari.aaplasevak.Fragment.HomeFragment;
import com.vinodmapari.aaplasevak.Model.Colony;
import com.vinodmapari.aaplasevak.Model.Constants;
import com.vinodmapari.aaplasevak.Model.Relations;
import com.vinodmapari.aaplasevak.Model.Row;
import com.vinodmapari.aaplasevak.Model.SearchList;
import com.vinodmapari.aaplasevak.Model.Series;
import com.vinodmapari.aaplasevak.Model.Status;
import com.vinodmapari.aaplasevak.Model.Template;
import com.vinodmapari.aaplasevak.Model.WaterSupply;
import com.vinodmapari.aaplasevak.R;
import com.vinodmapari.aaplasevak.Fragment.SettingsFragment;
import com.vinodmapari.aaplasevak.SearchAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class DashBoardActivity extends AppCompatActivity {

    Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    TextView tvToolbarTitle;
    private TextView textView_appDevlopBy;
    ArrayList<String> permissions = new ArrayList<>();
    ArrayList<String> permissionsToRequest;
    final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 101;
    public final static int ALL_PERMISSIONS_RESULT = 102;
    ArrayList<String> permissionsRejected = new ArrayList<>();
    String androidID;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        checkPer();
        initView();

        getStatusList();
        getSeriesList();
        getColonyList();
        getRowList();
        getWaterSupply();

        addFragment(new HomeFragment(), "Home");

        SharedPreferences sharedPreferences1 = getSharedPreferences("MyPrefsUser" , Context.MODE_PRIVATE);
        name = sharedPreferences1.getString("username","");


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                drawer.closeDrawers();
                int id = item.getItemId();

                if (id == R.id.home) {
                    // Home
                    addFragment(new HomeFragment(), "Home");
                    tvToolbarTitle.setText("Home");
                } else if (id == R.id.contact_us) {
                    // Contact the app owner
                    addFragment(new ContactFragment(), "Contact-Us");
                    tvToolbarTitle.setText("Contact-Us");
                } else if (id == R.id.settings) {
                    // Settings
                    addFragment(new SettingsFragment(), "Settings");
                    tvToolbarTitle.setText("Settings");
                } else if (id == R.id.logout) {
                    // Logout Of the app
                    AlertDialog alertbox = new AlertDialog.Builder(DashBoardActivity.this)
                            .setTitle("Aapla Sevak")
                            .setMessage("Are you sure you want to Exit?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int arg1) {

                                    final String deviceToken = "0";
                                    SharedPreferences sharedPreferences1 = getSharedPreferences("MyPrefsUser", Context.MODE_PRIVATE);
                                    final String id = sharedPreferences1.getString("id", "");

                                    try {
                                        androidID = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                    Intent intent = new Intent(DashBoardActivity.this, LoginActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                    startActivity(intent);
                                    finish();

                                    @SuppressLint("UseRequireInsteadOfGet") SharedPreferences sharedPreferences = Objects.requireNonNull(getSharedPreferences("MyPrefsUser", Context.MODE_PRIVATE));
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("username", "");
                                    editor.apply();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int arg1) {
                                    dialog.dismiss();
                                }
                            })
                            .show();

                    alertbox.getButton(alertbox.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                    alertbox.getButton(alertbox.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                }

                return true;
            }
        });

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
                            Toast.makeText(DashBoardActivity.this, getResources().getString(R.string.cannot_use_save_permission), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
                new AlertDialog.Builder(DashBoardActivity.this)
                        .setMessage(message)
                        .setPositiveButton("OK", okListener)
                        .setNegativeButton("cancel", null)
                        .create()
                        .show();
            }

            private void initView() {
                toolbar = (Toolbar) findViewById(R.id.toolbar_main);
                tvToolbarTitle = (TextView) findViewById(R.id.tvToolbarTitle);
                textView_appDevlopBy = (TextView) findViewById(R.id.textView_app_developed_by);
                drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

                navigationView = (NavigationView) findViewById(R.id.nav_view);

                toolbar.setTitleTextColor(getResources().getColor(R.color.colorBlack));
                setSupportActionBar(toolbar);
                tvToolbarTitle.setText(getResources().getString(R.string.home));

                ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                        DashBoardActivity.this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
                drawer.addDrawerListener(toggle);
                toggle.syncState();
                toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_hamburger));

              //  navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);

//        loadAppDetail();
            }




            private void addFragment(Fragment fragment, String tag) {

                toolbar.setTitle(tag);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, fragment, tag)
                        .commit();


                drawer.closeDrawer(Gravity.LEFT);
            }

            private void setToolBarTitle(String title) {
                toolbar.setTitle(title);
            }


    private void getSeriesList()
    {
//        Constants.series.clear();
//        Constants.series=new ArrayList<>();

        final RequestQueue requestQueue = Volley.newRequestQueue(DashBoardActivity.this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.series_list, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {

                Constants.series.clear();
                Constants.series=new ArrayList<>();

                Constants.series.add(new Series("0","Select Series"));
                try {
                    JSONObject jsonObject = new JSONObject(response);
                  //  Log.d("TAG", "onResponse: "+response);

                    JSONArray jsonArray = jsonObject.getJSONArray("SERIES");
                    for(int i = 0; i < jsonArray.length(); i++)
                    {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String id = jsonObject1.getString("id");
                        String series_name = jsonObject1.getString("series_name");

                        Constants.series.add(new Series(id,series_name));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
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

    public void getStatusList()
    {
//        Constants.statuses.clear();
//        Constants.statuses=new ArrayList<>();

        final RequestQueue requestQueue = Volley.newRequestQueue(DashBoardActivity.this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.status_list, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Constants.statuses.clear();
                Constants.statuses=new ArrayList<>();

                Constants.statuses.add(new Status("0","Select Status"));
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("STATUS");
                    for(int i = 0; i < jsonArray.length(); i++)
                    {

                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String id = jsonObject1.getString("id");
                        String status_name = jsonObject1.getString("status_name");

                        Constants.statuses.add(new Status(id,status_name));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error) {
                requestQueue.stop();
            }
        });
        requestQueue.add(stringRequest);

    }

    private void getColonyList()
    {

        final RequestQueue requestQueue = Volley.newRequestQueue(DashBoardActivity.this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.colony_list, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {

                Constants.colonies.clear();
                Constants.colonies=new ArrayList<>();

                Constants.colonies.add(new Colony("0","Select Colony"));
                try {
                    JSONObject jsonObject = new JSONObject(response);
//                    Log.d("TAG", "onResponse: "+response);

                    JSONArray jsonArray = jsonObject.getJSONArray("COLONY");
                    for(int i = 0; i < jsonArray.length(); i++)
                    {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String id = jsonObject1.getString("id");
                        String colony_name = jsonObject1.getString("colony_name");

                        Constants.colonies.add(new Colony(id,colony_name));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
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

    private void getRowList()
    {
        final RequestQueue requestQueue = Volley.newRequestQueue(DashBoardActivity.this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.row_list, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {

                Constants.rows.clear();
                Constants.rows=new ArrayList<>();

                Constants.rows.add(new Row("0","Select Row"));
                try {
                    JSONObject jsonObject = new JSONObject(response);
//                    Log.d("TAG", "onResponse: "+response);

                    JSONArray jsonArray = jsonObject.getJSONArray("ROW");
                    for(int i = 0; i < jsonArray.length(); i++)
                    {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String id = jsonObject1.getString("id");
                        String row_name = jsonObject1.getString("row_name");

                        Constants.rows.add(new Row(id,row_name));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
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

    private void getWaterSupply()
    {
//        Constants.statuses.clear();
//        Constants.statuses=new ArrayList<>();

        final RequestQueue requestQueue = Volley.newRequestQueue(DashBoardActivity.this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.water_Supply_list, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Constants.waterSupplies.clear();
                Constants.waterSupplies=new ArrayList<>();
                Constants.waterSupplies.add(new WaterSupply("0","Select Watersupply Slot"));

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("WATERSUPPLY");
                    for(int i = 0; i < jsonArray.length(); i++)
                    {

                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String id = jsonObject1.getString("id");
                        String slot_name = jsonObject1.getString("slot_name");

                        Constants.waterSupplies.add(new WaterSupply(id,slot_name));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error) {
                requestQueue.stop();
            }
        });
        requestQueue.add(stringRequest);

    }

    private void getTemplateList()
    {
//        Constants.series.clear();
//        Constants.series=new ArrayList<>();

        final RequestQueue requestQueue = Volley.newRequestQueue(DashBoardActivity.this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.template, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {

                Constants.templates.clear();
                Constants.templates=new ArrayList<>();

                Constants.templates.add(new Template("0","Select Template for Sms"));
                try {
                    JSONObject jsonObject = new JSONObject(response);
//                    Log.d("TAG", "onResponse: "+response);

                    JSONArray jsonArray = jsonObject.getJSONArray("TEMPLATE");
                    for(int i = 0; i < jsonArray.length(); i++)
                    {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String id = jsonObject1.getString("id");
                        String template = jsonObject1.getString("template");

                        Constants.templates.add(new Template(id,template));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
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

    private void getRelationList()
    {
//        Constants.relations.clear();
//        Constants.relations=new ArrayList<>();

        final RequestQueue requestQueue = Volley.newRequestQueue(DashBoardActivity.this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.relation_list, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Constants.relations.clear();
                Constants.relations=new ArrayList<>();

                Constants.relations.add(new Relations("0","Select Relation"));
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    //   Log.d("TAG", "onResponse: "+response);
                    JSONArray jsonArray = jsonObject.getJSONArray("RELATION");
                    for(int i = 0; i < jsonArray.length(); i++){
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String id = jsonObject1.getString("id");
                        String relation_name = jsonObject1.getString("relation_name");

                        Constants.relations.add(new Relations(id,relation_name));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
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



    @Override
    protected void onResume() {
        getStatusList();
        getSeriesList();
        getRowList();
        getColonyList();
        getWaterSupply();
        getTemplateList();
        getRelationList();
        super.onResume();
    }
}