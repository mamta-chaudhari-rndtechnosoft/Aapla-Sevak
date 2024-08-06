package com.vinodmapari.aaplasevak.Activity;

import static com.vinodmapari.aaplasevak.ApiConfig.ApiHandler.getRetrofitInstance;
import static com.vinodmapari.aaplasevak.Model.Constants.colony_name;
import static com.vinodmapari.aaplasevak.Model.Constants.row_name;
import static com.vinodmapari.aaplasevak.Model.Constants.series_name;
import static com.vinodmapari.aaplasevak.Model.Constants.water_supply_slots;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;
import com.vinodmapari.aaplasevak.ApiConfig.ApiInterface;
import com.vinodmapari.aaplasevak.Model.CityVillageItem;
import com.vinodmapari.aaplasevak.Model.CityVillageResponse;
import com.vinodmapari.aaplasevak.Model.Colony;
import com.vinodmapari.aaplasevak.Model.ColonyItem;
import com.vinodmapari.aaplasevak.Model.ColonyResponse;
import com.vinodmapari.aaplasevak.Model.Constants;
import com.vinodmapari.aaplasevak.Model.ConstituencyItem;
import com.vinodmapari.aaplasevak.Model.ConstituencyResponse;
import com.vinodmapari.aaplasevak.Model.PrabhagWardItem;
import com.vinodmapari.aaplasevak.Model.PrabhagWardResponse;
import com.vinodmapari.aaplasevak.Model.Row;
import com.vinodmapari.aaplasevak.Model.RowItem;
import com.vinodmapari.aaplasevak.Model.RowResponse;
import com.vinodmapari.aaplasevak.Model.Series;
import com.vinodmapari.aaplasevak.Model.SeriesItem;
import com.vinodmapari.aaplasevak.Model.SeriesResponse;
import com.vinodmapari.aaplasevak.Model.WaterSupplyItem;
import com.vinodmapari.aaplasevak.Model.WaterSupplyResponse;
import com.vinodmapari.aaplasevak.Model.WhatsAppApiBody;
import com.vinodmapari.aaplasevak.Model.WhatsAppApiResponseData;
import com.vinodmapari.aaplasevak.Model.ZoneItem;
import com.vinodmapari.aaplasevak.Model.ZoneResponse;
import com.vinodmapari.aaplasevak.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
//import retrofit2.Response;

import android.Manifest;
import android.content.pm.PackageManager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.util.Map;


public class WhatsappActivity extends AppCompatActivity {
    SearchableSpinner spinner_series, spinner_colony, spinner_row, spinner_water_Supply;
    SearchableSpinner spinner_constituency, spinner_city_village, spinner_zone, spinner_ward;
    //int selected_series, selected_colony, selected_row, selected_water_supply;
    String series_id, colony_id, row_id, water_supply_id, constituency_id, city_id, zone_id, ward_id;
    //String series, colony, row, watersupply, house_no, message, image;
    Button getContacts, btnWhatsAppApi;
    //long selected_series_id, selected_colony_id;
    //ArrayList<Colony> colonies;
    EditText text;
    EditText etMessage;
    ImageButton btnImageCamera, btnVideoPick;
    private static final int PERMISSION_REQUEST_CODE = 100;
    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int PICK_VIDEO_REQUEST = 2;
    String base64Image, base64Video;
    RequestBody requestImageFile, requestVideoFile;
    Uri selectedImageUri, selectedVideuri, selectedUri;
    LottieAnimationView loader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whatsapp);

        spinner_series = findViewById(R.id.spinnerSeries);
        spinner_colony = findViewById(R.id.spinner_colony);
        spinner_row = findViewById(R.id.spinner_row);
        getContacts = findViewById(R.id.send_sms);
        btnWhatsAppApi = findViewById(R.id.send_sms_with_api);
        //text=findViewById(R.id.var1);
        //checkBox=findViewById(R.id.checkbox);
        spinner_water_Supply = findViewById(R.id.spinner_water_supply);
        //house_number=findViewById(R.id.house_number);

        spinner_constituency = findViewById(R.id.spinner_constituency);
        spinner_city_village = findViewById(R.id.spinner_city_village);
        spinner_zone = findViewById(R.id.spinner_zone);
        spinner_ward = findViewById(R.id.spinner_ward);
        etMessage = findViewById(R.id.etTemplate);

        btnImageCamera = findViewById(R.id.btnImgCamera);
        btnVideoPick = findViewById(R.id.btnVideoPick);
        loader = findViewById(R.id.loader);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));

        toolbar.setTitle(Html.fromHtml("<b>" + "Send Message" + "</b>"));
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        fetchSeries();
        fetchColony("0");
        fetchRow("0", "0");
        fetchWaterSupplyId();

        fetchConstituencies();
        fetchCityVillages();
        fetchZones();
        fetchPrabhagWards();


        //getSeriesList();

        getContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(WhatsappActivity.this, GetWhatsappContactsActivity.class);
                //intent.putExtra("house_no", house_number.getText().toString());
                intent.putExtra("series_id", series_id);
                intent.putExtra("row_id", row_id);
                intent.putExtra("water_Supply_id", water_supply_id);
                intent.putExtra("colony_id", colony_id);
                startActivity(intent);


            }
        });

        btnWhatsAppApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                /*Intent intent = new Intent(WhatsappActivity.this, WhatsAppApiActivity.class);
                //intent.putExtra("house_no", house_number.getText().toString());
                intent.putExtra("series_id", series_id);
                intent.putExtra("row_id", row_id);
                intent.putExtra("water_Supply_id", water_supply_id);
                intent.putExtra("colony_id", colony_id);
                startActivity(intent);*/

                if (etMessage.getText().toString() == "") {
                    Toast.makeText(WhatsappActivity.this, "PLease Write the Message.", Toast.LENGTH_SHORT).show();
                }
                /*else if(selectedImageUri == null || selectedVideuri == null){
                    Toast.makeText(WhatsappActivity.this, "Please Select the Media.", Toast.LENGTH_SHORT).show();
                }*/
                else {
                    loader.setVisibility(View.VISIBLE);
                    sentWhatsAppMessage();
                }

            }
        });

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }


        btnImageCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* if (ContextCompat.checkSelfPermission(WhatsappActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_GRANTED) {
                    openImageChooser();
                } else {
                    ActivityCompat.requestPermissions(WhatsappActivity.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            PERMISSION_REQUEST_CODE);
                }*/
                checkAndRequestPermissions(PICK_IMAGE_REQUEST);
            }
        });

        btnVideoPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if (ContextCompat.checkSelfPermission(WhatsappActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_GRANTED) {
                    openVideoChooser();
                } else {
                    ActivityCompat.requestPermissions(WhatsappActivity.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
                }*/
                checkAndRequestPermissions(PICK_VIDEO_REQUEST);
            }
        });

    }

    //----------------------------- on create finish ------------------

    private void checkAndRequestPermissions(int requestType) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) { // Android 14
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES)
                    == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_VIDEO)
                            == PackageManager.PERMISSION_GRANTED) {
                if (requestType == PICK_IMAGE_REQUEST) {
                    openImageChooser();
                } else {
                    openVideoChooser();
                }
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_MEDIA_IMAGES, Manifest.permission.READ_MEDIA_VIDEO},
                        PERMISSION_REQUEST_CODE);
            }
        } else {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                if (requestType == PICK_IMAGE_REQUEST) {
                    openImageChooser();
                } else {
                    openVideoChooser();
                }
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        PERMISSION_REQUEST_CODE);
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        //getSeriesList();
        fetchSeries();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
    //------------------------------------------

    private void fetchSeries() {
        ApiInterface apiInterface = getRetrofitInstance().create(ApiInterface.class);
        Call<SeriesResponse> call = apiInterface.seriesResponse();

        call.enqueue(new Callback<SeriesResponse>() {
            @Override
            public void onResponse(Call<SeriesResponse> call, retrofit2.Response<SeriesResponse> response) {
                if (response.isSuccessful()) {

                    List<SeriesItem> seriesItems = response.body().getSeries();

                    List<String> seriesNames = new ArrayList<>();
                    final Map<String, String> seriesIdMap = new HashMap<>();
                    seriesNames.add("Select Series");

                    for (SeriesItem item : seriesItems) {
                        seriesNames.add(item.getSeriesName());
                        seriesIdMap.put(item.getSeriesName(), item.getId());
                    }

                    // Populate the spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(WhatsappActivity.this,
                            android.R.layout.simple_spinner_item, seriesNames);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_series.setAdapter(adapter);

                    // Handle spinner item selection
                    spinner_series.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String selectedSeries = (String) parent.getItemAtPosition(position);
                            if (!selectedSeries.equals("Select Series")) {
                                //fetchColony();
                                String selectedSeriesId = seriesIdMap.get(selectedSeries);
                                fetchColony(selectedSeriesId);
                                series_id = selectedSeriesId;
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            // Handle nothing selected
                        }
                    });

                } else {
                    Log.e("API Error", "Failed to fetch Series");
                }
            }

            @Override
            public void onFailure(Call<SeriesResponse> call, Throwable throwable) {
                Toast.makeText(WhatsappActivity.this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("API Error", "Error fetching series" + throwable.getMessage());
            }
        });
    }

    private void fetchColony(String SeriesId) {
        ApiInterface apiInterface = getRetrofitInstance().create(ApiInterface.class);

        Call<ColonyResponse> call = apiInterface.colonyResponse(SeriesId);
        call.enqueue(new Callback<ColonyResponse>() {
            @Override
            public void onResponse(Call<ColonyResponse> call, retrofit2.Response<ColonyResponse> response) {
                if (response.isSuccessful()) {
                    List<ColonyItem> colonyItems = response.body().getColonies();

                    List<String> colonyNames = new ArrayList<>();
                    final Map<String, String> colonyIdMap = new HashMap<>();
                    colonyNames.add("Select Colony");

                    for (ColonyItem item : colonyItems) {
                        colonyNames.add(item.getColonyName());
                        colonyIdMap.put(item.getColonyName(), item.getId());
                    }

                    // Populate the spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(WhatsappActivity.this,
                            android.R.layout.simple_spinner_item, colonyNames);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_colony.setAdapter(adapter);

                    // Handle spinner item selection
                    spinner_colony.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String selectedColony = (String) parent.getItemAtPosition(position);
                            if (!selectedColony.equals("Select Colony")) {
                                //Toast.makeText(UserSurveyActivity.this, "Selected: " + selectedCityVillage, Toast.LENGTH_SHORT).show();
                                // Perform any other actions based on selection
                                String selectedColonyId = colonyIdMap.get(selectedColony);
                                fetchRow(SeriesId, selectedColonyId);
                                colony_id = selectedColonyId;
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            // Handle nothing selected
                        }
                    });
                } else {
                    Toast.makeText(WhatsappActivity.this, "Response Not Success: " + response.code(), Toast.LENGTH_SHORT).show();
                    Log.e("API Error", "Response Not Success: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ColonyResponse> call, Throwable throwable) {
                Toast.makeText(WhatsappActivity.this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("API Error", "Error fetching  Colony " + throwable.getMessage());
            }
        });
    }

    private void fetchRow(String seriesId, String colonyId) {
        ApiInterface apiInterface = getRetrofitInstance().create(ApiInterface.class);
        Call<RowResponse> call = apiInterface.rowResponse(seriesId, colonyId);
        call.enqueue(new Callback<RowResponse>() {
            @Override
            public void onResponse(Call<RowResponse> call, retrofit2.Response<RowResponse> response) {
                if (response.isSuccessful()) {
                    List<RowItem> rowItems = response.body().getRows();

                    List<String> rowNames = new ArrayList<>();
                    final Map<String, String> rowIdMap = new HashMap<>();
                    rowNames.add("Select Row");

                    for (RowItem item : rowItems) {
                        rowNames.add(item.getRowName());
                        rowIdMap.put(item.getRowName(), item.getId());
                    }


                    // Populate the spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(WhatsappActivity.this,
                            android.R.layout.simple_spinner_item, rowNames);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_row.setAdapter(adapter);

                    // Handle spinner item selection
                    spinner_row.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String selectedRows = (String) parent.getItemAtPosition(position);
                            if (!selectedRows.equals("Select Row")) {
                                //fetchColony();
                                String selectedRowId = rowIdMap.get(selectedRows);
                                row_id = selectedRowId;

                                //Toast.makeText(SendSmsActivity.this, "S: " + series_id + " C: " + colony_id + " R: " + row_id, Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            // Handle nothing selected
                        }
                    });
                } else {
                    Log.e("API Error", "Failed to fetch Row");
                }
            }

            @Override
            public void onFailure(Call<RowResponse> call, Throwable throwable) {
                Toast.makeText(WhatsappActivity.this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("API Error", "Error fetching prabhag wards: " + throwable.getMessage());
            }
        });
    }

    private void fetchWaterSupplyId() {
        ApiInterface apiInterface = getRetrofitInstance().create(ApiInterface.class);

        Call<WaterSupplyResponse> call = apiInterface.waterSupplyResponse();
        call.enqueue(new Callback<WaterSupplyResponse>() {
            @Override
            public void onResponse(Call<WaterSupplyResponse> call, retrofit2.Response<WaterSupplyResponse> response) {
                if (response.isSuccessful()) {

                    List<WaterSupplyItem> waterSupplyItems = response.body().getWaterSupply();

                    List<String> waterSupplyNames = new ArrayList<>();
                    final Map<String, String> waterSupplyIdMap = new HashMap<>();
                    waterSupplyNames.add("Select Water Supply");

                    for (WaterSupplyItem item : waterSupplyItems) {
                        waterSupplyNames.add(item.getSlotName());
                        waterSupplyIdMap.put(item.getSlotName(), item.getId());
                    }


                    // Populate the spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(WhatsappActivity.this,
                            android.R.layout.simple_spinner_item, waterSupplyNames);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_water_Supply.setAdapter(adapter);

                    // Handle spinner item selection
                    spinner_water_Supply.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String selectedWaterSupply = (String) parent.getItemAtPosition(position);
                            if (!selectedWaterSupply.equals("Select Water Supply")) {
                                //fetchColony();
                                String selectedWaterSupplyId = waterSupplyIdMap.get(selectedWaterSupply);
                                water_supply_id = selectedWaterSupplyId;

                                //Toast.makeText(UserSurveyActivity.this, "S: " + series_id + " C: " + colony_id  + " R: " + row_id, Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            // Handle nothing selected
                        }
                    });

                } else {
                    Log.e("API Error", "Failed to fetch water supply");
                }
            }

            @Override
            public void onFailure(Call<WaterSupplyResponse> call, Throwable throwable) {
                Toast.makeText(WhatsappActivity.this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("API Error", "Error fetching prabhag wards: " + throwable.getMessage());
            }
        });
    }


    //----------------------------------------------------------------
    private void fetchConstituencies() {

        ApiInterface apiInterface = getRetrofitInstance().create(ApiInterface.class);
        Call<ConstituencyResponse> call = apiInterface.getConstituencyList();
        call.enqueue(new Callback<ConstituencyResponse>() {
            @Override
            public void onResponse(Call<ConstituencyResponse> call, retrofit2.Response<ConstituencyResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<ConstituencyItem> constituencies = response.body().getConstituency();

                    // Create a list of constituency names
                    List<String> constituencyName = new ArrayList<>();
                    final Map<String, String> constituencyIdMap = new HashMap<>();
                    constituencyName.add("Select Constituency");

                    for (ConstituencyItem constituency : constituencies) {
                        constituencyName.add(constituency.getConstituencyName());
                        constituencyIdMap.put(constituency.getConstituencyName(), constituency.getId());
                    }

                    // Populate the spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(WhatsappActivity.this, android.R.layout.simple_spinner_item, constituencyName);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_constituency.setAdapter(adapter);

                    // Handle spinner item selection
                    spinner_constituency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String selectedConstituency = (String) parent.getItemAtPosition(position);
                            if (!selectedConstituency.equals("Select Constituency")) {
                                // Toast.makeText(WhatsappActivity.this, "Selected: " + spinner_constituency.getSelectedItemPosition(), Toast.LENGTH_SHORT).show();
                                String selectedConstituencyId = constituencyIdMap.get(selectedConstituency);
                                constituency_id = selectedConstituencyId;
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            // Handle nothing selected
                        }
                    });
                } else {
                    // Handle unsuccessful response
                    Log.e("API Error", "Failed to fetch constituencies");
                }
            }

            @Override
            public void onFailure(Call<ConstituencyResponse> call, Throwable throwable) {
                //Toast.makeText(UserSurveyActivity.this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(WhatsappActivity.this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("API Error", "Error fetching prabhag wards: " + throwable.getMessage());
            }
        });
    }

    private void fetchCityVillages() {
        ApiInterface apiInterface = getRetrofitInstance().create(ApiInterface.class);

        Call<CityVillageResponse> call = apiInterface.getCityVillage();

        call.enqueue(new Callback<CityVillageResponse>() {
            @Override
            public void onResponse(Call<CityVillageResponse> call, retrofit2.Response<CityVillageResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<CityVillageItem> cityVillages = response.body().getCityVillage();

                    // Create a list of city or village names
                    List<String> cityVillageNames = new ArrayList<>();
                    final Map<String, String> cityVillageIdMap = new HashMap<>();
                    cityVillageNames.add("Select City/Village");

                    for (CityVillageItem item : cityVillages) {
                        cityVillageNames.add(item.getCityVillageName());
                        cityVillageIdMap.put(item.getCityVillageName(), item.getId());
                    }

                    // Populate the spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(WhatsappActivity.this, android.R.layout.simple_spinner_item, cityVillageNames);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_city_village.setAdapter(adapter);

                    // Handle spinner item selection
                    spinner_city_village.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String selectedCityVillage = (String) parent.getItemAtPosition(position);
                            if (!selectedCityVillage.equals("Select City or Village")) {
                                String selectedCityVillageId = cityVillageIdMap.get(selectedCityVillage);
                                city_id = selectedCityVillageId;
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            // Handle nothing selected
                        }
                    });

                } else {
                    // Handle unsuccessful response
                    Log.e("API Error", "Failed to fetch city or village data");
                }
            }

            @Override
            public void onFailure(Call<CityVillageResponse> call, Throwable throwable) {
                Toast.makeText(WhatsappActivity.this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("API Error", "Error fetching prabhag wards: " + throwable.getMessage());
            }
        });

    }

    private void fetchZones() {
        ApiInterface apiInterface = getRetrofitInstance().create(ApiInterface.class);

        Call<ZoneResponse> call = apiInterface.getZoneList();

        call.enqueue(new Callback<ZoneResponse>() {
            @Override
            public void onResponse(Call<ZoneResponse> call, retrofit2.Response<ZoneResponse> response) {

                if (response.isSuccessful() && response.body() != null) {
                    List<ZoneItem> zones = response.body().getZone();

                    // Create a list of zone names
                    List<String> zoneNames = new ArrayList<>();
                    final Map<String, String> zoneIdMap = new HashMap<>();
                    zoneNames.add("Select Zone");

                    for (ZoneItem zone : zones) {
                        zoneNames.add(zone.getZoneName());
                        zoneIdMap.put(zone.getZoneName(), zone.getId());
                    }

                    // Populate the spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(WhatsappActivity.this, android.R.layout.simple_spinner_item, zoneNames);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_zone.setAdapter(adapter);

                    // Handle spinner item selection
                    spinner_zone.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String selectedZone = (String) parent.getItemAtPosition(position);
                            if (!selectedZone.equals("Select Zone")) {
                                String selectedZoneId = zoneIdMap.get(selectedZone);
                                zone_id = selectedZoneId;
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            // Handle nothing selected
                        }
                    });

                } else {
                    // Handle unsuccessful response
                    Log.e("API Error", "Failed to fetch zones");
                }
            }

            @Override
            public void onFailure(Call<ZoneResponse> call, Throwable throwable) {
                Toast.makeText(WhatsappActivity.this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("API Error", "Error fetching prabhag wards: " + throwable.getMessage());
            }
        });

    }

    private void fetchPrabhagWards() {
        ApiInterface apiInterface = getRetrofitInstance().create(ApiInterface.class);

        Call<PrabhagWardResponse> call = apiInterface.getPrabhagWardList();

        call.enqueue(new Callback<PrabhagWardResponse>() {
            @Override
            public void onResponse(Call<PrabhagWardResponse> call, retrofit2.Response<PrabhagWardResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<PrabhagWardItem> prabhagWards = response.body().getPrabhagWard();

                    // Create a list of prabhag ward names
                    List<String> prabhagWardNames = new ArrayList<>();
                    final Map<String, String> prabhagWardIdMap = new HashMap<>();
                    prabhagWardNames.add("Select Prabhag/Ward");

                    for (PrabhagWardItem prabhagWard : prabhagWards) {
                        prabhagWardNames.add(prabhagWard.getPrabhagWardName());
                        prabhagWardIdMap.put(prabhagWard.getPrabhagWardName(), prabhagWard.getId());
                    }

                    // Populate the spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(WhatsappActivity.this, android.R.layout.simple_spinner_item, prabhagWardNames);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_ward.setAdapter(adapter);

                    // Handle spinner item selection
                    spinner_ward.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String selectedPrabhagWard = (String) parent.getItemAtPosition(position);
                            if (!selectedPrabhagWard.equals("Select Prabhag/Ward")) {
                                String selectedPrabhagWardId = prabhagWardIdMap.get(selectedPrabhagWard);
                                ward_id = selectedPrabhagWardId;
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            // Handle nothing selected
                        }
                    });

                } else {
                    // Handle unsuccessful response
                    Log.e("API Error", "Failed to fetch prabhag wards");
                }
            }

            @Override
            public void onFailure(Call<PrabhagWardResponse> call, Throwable throwable) {
                Toast.makeText(WhatsappActivity.this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("API Error", "Error fetching prabhag wards: " + throwable.getMessage());
            }
        });

    }

    private void sentWhatsAppMessage() {
        ApiInterface apiInterface = getRetrofitInstance().create(ApiInterface.class);

        //WhatsAppApiBody whatsAppApiBody;
        // Determine if there is media to send


        /*RequestBody seriesId = RequestBody.create(MediaType.parse("multipart/form-data"), series_id);
        RequestBody colonyId = RequestBody.create(MediaType.parse("multipart/form-data"), colony_id);
        RequestBody rowId = RequestBody.create(MediaType.parse("multipart/form-data"), row_id);
        RequestBody waterSupplyId = RequestBody.create(MediaType.parse("multipart/form-data"), water_supply_id);
        RequestBody constituencyId = RequestBody.create(MediaType.parse("multipart/form-data"), constituency_id);
        RequestBody cityVillageId = RequestBody.create(MediaType.parse("multipart/form-data"), city_id);
        RequestBody zoneId = RequestBody.create(MediaType.parse("multipart/form-data"), zone_id);
        RequestBody wardId = RequestBody.create(MediaType.parse("multipart/form-data"), ward_id);
        RequestBody message = RequestBody.create(MediaType.parse("multipart/form-data"), etMessage.getText().toString());*/

        RequestBody seriesId = createRequestBody(series_id);
        RequestBody colonyId = createRequestBody(colony_id);
        RequestBody rowId = createRequestBody(row_id);
        RequestBody waterSupplyId = createRequestBody(water_supply_id);
        RequestBody constituencyId = createRequestBody(constituency_id);
        RequestBody cityVillageId = createRequestBody(city_id);
        RequestBody zoneId = createRequestBody(zone_id);
        RequestBody wardId = createRequestBody(ward_id);
        RequestBody message = createRequestBody(etMessage.getText().toString());

        Log.d("Api Response", "WhatsAppData: " + "series: " + series_id + " colonyId: " + colony_id + " row: " + row_id + " water: " + water_supply_id);
        Log.d("Api Response", "WhatsAppData: " + " constituency: " + constituency_id + " cityVillageId: " + city_id + " zoneId: " + zone_id + " wardId: " + ward_id + " message: " + message);


        MultipartBody.Part body = null;
        Call<WhatsAppApiResponseData> call = null;


        if (selectedVideuri == null && selectedImageUri != null) {

            // all request with images
            File file = new File(getRealPathFromURI(selectedImageUri));
            RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg"), file);
            body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
            //call = apiInterface.sentWhatsAppMessage(seriesId, colonyId, rowId, waterSupplyId, constituencyId, cityVillageId, zoneId, wardId, message, body);

            if (water_supply_id == null) {
                call = apiInterface.sentWhatsAppMessage(seriesId, colonyId, rowId, constituencyId, cityVillageId, zoneId, wardId, message, body);
            } else if (water_supply_id != null) {
                call = apiInterface.sentWhatsAppMessage(waterSupplyId, message, body);
            } else if (water_supply_id == null && row_id == null) {
                call = apiInterface.sentWhatsAppMessage(seriesId, colonyId, constituencyId, cityVillageId, zoneId, wardId, message, body);
            }


        } else if (selectedImageUri == null && selectedVideuri != null) {
            //all request with video
            File file = new File(getRealPathFromURI(selectedVideuri));
            RequestBody requestFile = RequestBody.create(MediaType.parse("video/mp4"), file);
            body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
            //call = apiInterface.sentWhatsAppMessage(seriesId, colonyId, rowId, waterSupplyId, constituencyId, cityVillageId, zoneId, wardId, message, body);
            if (water_supply_id == null) {
                call = apiInterface.sentWhatsAppMessage(seriesId, colonyId, rowId, constituencyId, cityVillageId, zoneId, wardId, message, body);
            } else if (water_supply_id != null) {
                call = apiInterface.sentWhatsAppMessage(waterSupplyId, message, body);
            } else if (water_supply_id == null && row_id == null) {
                call = apiInterface.sentWhatsAppMessage(seriesId, colonyId, constituencyId, cityVillageId, zoneId, wardId, message, body);
            }

        } else if (selectedImageUri == null && selectedVideuri == null) {
            // without image and video requests
            //call = apiInterface.sentWhatsAppMessage(seriesId, colonyId, rowId, waterSupplyId, constituencyId, cityVillageId, zoneId, wardId, message);
            if (water_supply_id == null) {
                call = apiInterface.sentWhatsAppMessage(seriesId, colonyId, rowId, constituencyId, cityVillageId, zoneId, wardId, message);
            } else if (water_supply_id != null) {
                call = apiInterface.sentWhatsAppMessage(waterSupplyId, message);
            } else if (water_supply_id == null && row_id == null) {
                call = apiInterface.sentWhatsAppMessage(seriesId, colonyId, constituencyId, cityVillageId, zoneId, wardId, message);
            }
        }

        /*if (selectedVideuri == null && selectedImageUri != null) {
            File file = new File(getRealPathFromURI(selectedImageUri));
            RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg"), file);
            body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
        } else if (selectedImageUri == null && selectedVideuri != null) {
            File file = new File(getRealPathFromURI(selectedVideuri));
            RequestBody requestFile = RequestBody.create(MediaType.parse("video/mp4"), file);
            body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
        }

        // Assign the correct call based on conditions
        if (body != null) {
            if (water_supply_id == null) {
                call = apiInterface.sentWhatsAppMessage(seriesId, colonyId, rowId, constituencyId, cityVillageId, zoneId, wardId, message, body);
            } else if (water_supply_id != null) {
                call = apiInterface.sentWhatsAppMessage(waterSupplyId, message, body);
            } else if (water_supply_id == null && row_id == null) {
                call = apiInterface.sentWhatsAppMessage(seriesId, colonyId, constituencyId, cityVillageId, zoneId, wardId, message, body);
            }
        } else {
            if (water_supply_id == null) {
                call = apiInterface.sentWhatsAppMessage(seriesId, colonyId, rowId, constituencyId, cityVillageId, zoneId, wardId, message);
            } else if (water_supply_id != null) {
                call = apiInterface.sentWhatsAppMessage(waterSupplyId, message);
            } else if (water_supply_id == null && row_id == null) {
                call = apiInterface.sentWhatsAppMessage(seriesId, colonyId, constituencyId, cityVillageId, zoneId, wardId, message);
            }
        }*/




        //Call<WhatsAppApiResponseData> call = apiInterface.sentWhatsAppMessage(seriesId, colonyId, rowId, waterSupplyId, constituencyId, cityVillageId, zoneId, wardId, message, body);
        //Log.d("Api Response", "WhatsApp: " + whatsAppApiBody.toString());

        call.enqueue(new Callback<WhatsAppApiResponseData>() {
            @Override
            public void onResponse(Call<WhatsAppApiResponseData> call, retrofit2.Response<WhatsAppApiResponseData> response) {
                if (response.isSuccessful()) {

                    WhatsAppApiResponseData responseData = response.body();
                    String status = responseData.getStatus();

                    loader.setVisibility(View.GONE);
                    Toast.makeText(WhatsappActivity.this, "Success!! " + status, Toast.LENGTH_SHORT).show();
                    Log.d("Api Response", "data: " + status);
                    finish();
                } else {
                    loader.setVisibility(View.GONE);
                    Toast.makeText(WhatsappActivity.this, "Response Error..!!", Toast.LENGTH_SHORT).show();
                    Log.e("Api Response", "Response Error..");
                }
            }

            @Override
            public void onFailure(Call<WhatsAppApiResponseData> call, Throwable throwable) {
                loader.setVisibility(View.GONE);
                Log.e("Api Response", "Error.." + throwable.getLocalizedMessage());
                Toast.makeText(WhatsappActivity.this, "Error.." + throwable.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                throwable.printStackTrace();
            }
        });

    }

    private RequestBody createRequestBody(String value) {
        return value == null ? null : RequestBody.create(MediaType.parse("multipart/form-data"), value);
    }




    private void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    private void openVideoChooser() {
        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Video"), PICK_VIDEO_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedMediaUri = data.getData();
            if (requestCode == PICK_IMAGE_REQUEST) {
                // Handle the image chosen from gallery
                selectedImageUri = selectedMediaUri;

            } else if (requestCode == PICK_VIDEO_REQUEST) {
                // Handle the video chosen from gallery
                selectedVideuri = selectedMediaUri;
            }
        }
    }


    /*private String getRealPathFromURI(Uri contentUri) {
        //---------------- only for Image ----------------------//
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String filePath = cursor.getString(column_index);
        cursor.close();
        return filePath;

    }*/

    private String getRealPathFromURI(Uri contentUri) {
        String[] projection;
        if (getContentResolver().getType(contentUri).contains("image")) {
            projection = new String[]{MediaStore.Images.Media.DATA};
        } else if (getContentResolver().getType(contentUri).contains("video")) {
            projection = new String[]{MediaStore.Video.Media.DATA};
        } else {
            return null; // Unsupported URI type
        }

        Cursor cursor = getContentResolver().query(contentUri, projection, null, null, null);
        if (cursor == null)
            return null;

        int column_index = cursor.getColumnIndexOrThrow(projection[0]);
        cursor.moveToFirst();
        String filePath = cursor.getString(column_index);
        cursor.close();
        return filePath;
    }


    private File uriToFile(Uri uri) throws IOException {
        InputStream inputStream = getContentResolver().openInputStream(uri);
        File file = new File(getCacheDir(), "tempFile");
        try (OutputStream outputStream = new FileOutputStream(file)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
        }
        return file;
    }


    // ------------------------------------------- for base 64 ------------------------------------

    /*private void handleImage(Uri imageUri) {

        try {
            InputStream inputStream = getContentResolver().openInputStream(imageUri);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            base64Image = convertBitmapToBase64(bitmap);
            Log.d("Api Response",base64Image);
            // Use the base64Image string as needed
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void handleVideo(Uri videoUri) {
        try {
            InputStream inputStream = getContentResolver().openInputStream(videoUri);
            byte[] videoBytes = getBytes(inputStream);
            base64Video = Base64.encodeToString(videoBytes, Base64.DEFAULT);
            // Use the base64Video string as needed
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    /* private String convertBitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

    private byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];
        int len;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }*/

}