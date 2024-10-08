package com.vinodmapari.aaplasevak.Activity;


import static com.vinodmapari.aaplasevak.ApiConfig.ApiHandler.getRetrofitInstance;
import static com.vinodmapari.aaplasevak.Model.Constants.colony_name;
import static com.vinodmapari.aaplasevak.Model.Constants.row_name;
import static com.vinodmapari.aaplasevak.Model.Constants.series_name;
import static com.vinodmapari.aaplasevak.Model.Constants.template_name;
import static com.vinodmapari.aaplasevak.Model.Constants.water_supply_slots;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.appbar.MaterialToolbar;
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
import com.vinodmapari.aaplasevak.Model.SendSmsBody;
import com.vinodmapari.aaplasevak.Model.SendSmsResponseData;
import com.vinodmapari.aaplasevak.Model.Series;
import com.vinodmapari.aaplasevak.Model.SeriesItem;
import com.vinodmapari.aaplasevak.Model.SeriesResponse;
import com.vinodmapari.aaplasevak.Model.TemplateResponse;
import com.vinodmapari.aaplasevak.Model.WaterSupplyItem;
import com.vinodmapari.aaplasevak.Model.WaterSupplyResponse;
import com.vinodmapari.aaplasevak.Model.WhatsAppApiResponseData;
import com.vinodmapari.aaplasevak.Model.ZoneItem;
import com.vinodmapari.aaplasevak.Model.ZoneResponse;
import com.vinodmapari.aaplasevak.R;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SendSmsActivity extends AppCompatActivity {

    SearchableSpinner spinner_series, spinner_colony, spinner_row, spinner_water_Supply, spinner_template;
    SearchableSpinner spinner_constituency, spinner_city_village, spinner_zone, spinner_ward;
    int  selected_template;
    String series_id, colony_id, row_id, water_supply_id, template_id, constituency_id, city_id, zone_id, ward_id;
    Button btnSend;
    ArrayList<Colony> colonies;
    EditText  etTemplateText;
    private String colonyName;
    //AVLoadingIndicatorView progress;
    LottieAnimationView loader;
    MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_sms);

        spinner_series = findViewById(R.id.spinnerSeries);
        spinner_colony = findViewById(R.id.spinner_colony);
        spinner_row = findViewById(R.id.spinner_row);
        btnSend = findViewById(R.id.send_sms);
        etTemplateText = findViewById(R.id.etTemplate);
        spinner_template = findViewById(R.id.spinner_template);
        spinner_water_Supply = findViewById(R.id.spinner_water_supply);
        loader = findViewById(R.id.loader);


        spinner_constituency = findViewById(R.id.spinner_constituency);
        spinner_city_village = findViewById(R.id.spinner_city_village);
        spinner_zone = findViewById(R.id.spinner_zone);
        spinner_ward = findViewById(R.id.spinner_ward_prabhag);


         toolbar = findViewById(R.id.toolbar);
         toolbar.setNavigationOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 finish();
             }
         });

        //spinner_template.setTitle("select template");


        //spinners
        fetchSeries();
        fetchColony("0");
        fetchRow("0", "0");
        fetchWaterSupplyId();

        fetchConstituencies();
        fetchZones();
        fetchCityVillages();
        fetchPrabhagWards();
        fetchTemplateDesciption();


        //template_name.clear();
       // template_name = new ArrayList<>();

       /* for (int i = 0; i < Constants.templates.size(); i++) {
            Constants.template_name.add(Constants.templates.get(i).getTemplate());
            if (template_name != null && template_name.equals(Constants.templates.get(i).getTemplate())) {
                selected_template = i;
            }
        }*/


       /* ArrayAdapter<String> dataAdapter_template = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, template_name);
        dataAdapter_template.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_template.setAdapter(dataAdapter_template);*/


       /* spinner_template.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                template_id = Constants.templates.get(position).getId();
                //Toast.makeText(SendSmsActivity.this, "ID: " + template_id, Toast.LENGTH_SHORT).show();
                String message = parent.getItemAtPosition(position).toString();
                if (message.equalsIgnoreCase("Select Template for Sms")) {
                    //templateText.setText("");
                    etTemplateText.setText("");
                } else {
                    //templateText.setText(message);
                    //etTemplateText.setText(message);
                    fetchTemplateDescription();
                }
            }

            @Override

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/





        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getSMSList();
                loader.setVisibility(View.VISIBLE);
                sendSms();
            }
        });


        /* btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SendSmsActivity.this,SearchVoterMemberActivity.class);
                startActivity(intent);
            }
        });
        */

    }


    @Override
    protected void onResume() {
        super.onResume();
        //getSeriesList();
        //fetchSeries();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //getSeriesList();
        //fetchSeries();
    }

    private void fetchTemplateDesciption(){
        ApiInterface apiInterface = getRetrofitInstance().create(ApiInterface.class);

        Call<TemplateResponse> call = apiInterface.getTemplateData();

        call.enqueue(new Callback<TemplateResponse>() {
            @Override
            public void onResponse(Call<TemplateResponse> call, retrofit2.Response<TemplateResponse> response) {
                if(response.isSuccessful()){

                    List<TemplateResponse.TemplateDesc> templateItems = response.body().getTEMPLATE_DESC();

                    List<String> templateNames = new ArrayList<>();
                    final Map<String, String> templateIdMap = new HashMap<>();
                    templateNames.add("Template");

                    for (TemplateResponse.TemplateDesc item: templateItems){
                        templateNames.add(item.getTemplate());
                        templateIdMap.put(item.getTemplate(), item.getTemplateDesc());
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(SendSmsActivity.this,
                            android.R.layout.simple_spinner_item, templateNames);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_template.setAdapter(adapter);

                    // Handle spinner item selection
                    spinner_template.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String selectedTemplate = (String) parent.getItemAtPosition(position);
                            if (!selectedTemplate.equals("Template")) {
                                //fetchColony();
                                String selectedTemplateId = templateIdMap.get(selectedTemplate);
                                etTemplateText.setText(selectedTemplateId);

                                //Toast.makeText(UserSurveyActivity.this, "S: " + series_id + " C: " + colony_id  + " R: " + row_id, Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            // Handle nothing selected
                        }
                    });


                }
                else{
                    Toast.makeText(SendSmsActivity.this, "Response Error..!!" + response.code(), Toast.LENGTH_SHORT).show();
                    Log.e("Tag", "Response Error..");
                }
            }

            @Override
            public void onFailure(Call<TemplateResponse> call, Throwable throwable) {
                Log.e("Tag", "Error.." + throwable.getLocalizedMessage());
                Toast.makeText(SendSmsActivity.this, "Error..", Toast.LENGTH_SHORT).show();
            }
        });

    }





    public void sendSms() {

        ApiInterface apiInterface = getRetrofitInstance().create(ApiInterface.class);

        //SendSmsBody sendSmsBody = new SendSmsBody(selected_series,selected_row,selected_colony,selected_water_supply,etTemplateText.getText().toString());

        RequestBody seriesId = createRequestBody(series_id);
        RequestBody colonyId = createRequestBody(colony_id);
        RequestBody rowId = createRequestBody(row_id);
        RequestBody waterSupplyId = createRequestBody(water_supply_id);
        RequestBody constituencyId = createRequestBody(constituency_id);
        RequestBody cityVillageId = createRequestBody(city_id);
        RequestBody zoneId = createRequestBody(zone_id);
        RequestBody wardId = createRequestBody(ward_id);
        RequestBody message = createRequestBody(etTemplateText.getText().toString());


        Call<SendSmsResponseData> call = null;


        if (water_supply_id == null) {
            call = apiInterface.sendSms(seriesId, colonyId, rowId, constituencyId, cityVillageId, zoneId, wardId, message);
        } else if (water_supply_id != null) {
            call = apiInterface.sendSms(waterSupplyId, message);
        } else if (water_supply_id == null && row_id == null) {
            call = apiInterface.sendSms(seriesId, colonyId, constituencyId, cityVillageId, zoneId, wardId, message);
        }


        call.enqueue(new Callback<SendSmsResponseData>() {
            @Override
            public void onResponse(Call<SendSmsResponseData> call, retrofit2.Response<SendSmsResponseData> response) {
                if (response.isSuccessful()) {

                    loader.setVisibility(View.GONE);
                    SendSmsResponseData responseData = response.body();
                    String status = responseData.getStatus();

                    Toast.makeText(SendSmsActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    //finish();


                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SendSmsActivity.this);
                    alertDialogBuilder.setTitle("Message Status");
                    alertDialogBuilder.setMessage("Message sent successfully!");

                    alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    // Create and show the dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();


                } else {
                    loader.setVisibility(View.GONE);
                    Toast.makeText(SendSmsActivity.this, "Response Error..!!", Toast.LENGTH_SHORT).show();
                    Log.e("Tag", "Response Error..");
                }
            }

            @Override
            public void onFailure(Call<SendSmsResponseData> call, Throwable throwable) {
                loader.setVisibility(View.GONE);
                Log.e("Tag", "Error.." + throwable.getLocalizedMessage());
                Toast.makeText(SendSmsActivity.this, "Error..", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private RequestBody createRequestBody(String value) {
        return value == null ? null : RequestBody.create(MediaType.parse("multipart/form-data"), value);
    }


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
                    seriesNames.add("Series");

                    for (SeriesItem item : seriesItems) {
                        seriesNames.add(item.getSeriesName());
                        seriesIdMap.put(item.getSeriesName(), item.getId());
                    }

                    // Populate the spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(SendSmsActivity.this,
                            android.R.layout.simple_spinner_item, seriesNames);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_series.setAdapter(adapter);

                    // Handle spinner item selection
                    spinner_series.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String selectedSeries = (String) parent.getItemAtPosition(position);
                            if (!selectedSeries.equals("Series")) {
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
                Toast.makeText(SendSmsActivity.this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("API Error", "Error fetching prabhag wards: " + throwable.getMessage());
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
                    colonyNames.add("Colony");

                    for (ColonyItem item : colonyItems) {
                        colonyNames.add(item.getColonyName());
                        colonyIdMap.put(item.getColonyName(), item.getId());
                    }

                    // Populate the spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(SendSmsActivity.this,
                            android.R.layout.simple_spinner_item, colonyNames);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_colony.setAdapter(adapter);

                    // Handle spinner item selection
                    spinner_colony.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String selectedColony = (String) parent.getItemAtPosition(position);
                            if (!selectedColony.equals("Colony")) {
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
                    Toast.makeText(SendSmsActivity.this, "Response Not Success: " + response.code(), Toast.LENGTH_SHORT).show();
                    Log.e("API Error", "Response Not Success: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ColonyResponse> call, Throwable throwable) {
                Toast.makeText(SendSmsActivity.this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("API Error", "Error fetching prabhag wards: " + throwable.getMessage());
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
                    rowNames.add("Row");

                    for (RowItem item : rowItems) {
                        rowNames.add(item.getRowName());
                        rowIdMap.put(item.getRowName(), item.getId());
                    }


                    // Populate the spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(SendSmsActivity.this,
                            android.R.layout.simple_spinner_item, rowNames);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_row.setAdapter(adapter);

                    // Handle spinner item selection
                    spinner_row.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String selectedRows = (String) parent.getItemAtPosition(position);
                            if (!selectedRows.equals("Row")) {
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
                Toast.makeText(SendSmsActivity.this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
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
                    waterSupplyNames.add("Water Supply");

                    for (WaterSupplyItem item : waterSupplyItems) {
                        waterSupplyNames.add(item.getSlotName());
                        waterSupplyIdMap.put(item.getSlotName(), item.getId());
                    }


                    // Populate the spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(SendSmsActivity.this,
                            android.R.layout.simple_spinner_item, waterSupplyNames);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_water_Supply.setAdapter(adapter);

                    // Handle spinner item selection
                    spinner_water_Supply.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String selectedWaterSupply = (String) parent.getItemAtPosition(position);
                            if (!selectedWaterSupply.equals("Water Supply")) {
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
                Toast.makeText(SendSmsActivity.this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("API Error", "Error fetching prabhag wards: " + throwable.getMessage());
            }
        });
    }

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
                    constituencyName.add("Constituency");

                    for (ConstituencyItem constituency : constituencies) {
                        constituencyName.add(constituency.getConstituencyName());
                        constituencyIdMap.put(constituency.getConstituencyName(), constituency.getId());
                    }

                    //Toast.makeText(UserSurveyActivity.this, "Names: " + constituencyName, Toast.LENGTH_SHORT).show();
                    //Log.d("Api Response","Names: " + constituencyName);

                    // Populate the spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(SendSmsActivity.this, android.R.layout.simple_spinner_item, constituencyName);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_constituency.setAdapter(adapter);

                    // Handle spinner item selection
                    spinner_constituency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String selectedConstituency = (String) parent.getItemAtPosition(position);
                            if (!selectedConstituency.equals("Constituency")) {
                                //Toast.makeText(UserSurveyActivity.this, "Selected: " + selectedConstituency, Toast.LENGTH_SHORT).show();
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
                Toast.makeText(SendSmsActivity.this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
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
                    cityVillageNames.add("City/Village");

                    for (CityVillageItem item : cityVillages) {
                        cityVillageNames.add(item.getCityVillageName());
                        cityVillageIdMap.put(item.getCityVillageName(), item.getId());
                    }

                    // Populate the spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(SendSmsActivity.this, android.R.layout.simple_spinner_item, cityVillageNames);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_city_village.setAdapter(adapter);

                    // Handle spinner item selection
                    spinner_city_village.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String selectedCityVillage = (String) parent.getItemAtPosition(position);
                            if (!selectedCityVillage.equals("City/Village")) {
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
                Toast.makeText(SendSmsActivity.this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
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
                    zoneNames.add("Zone");

                    for (ZoneItem zone : zones) {
                        zoneNames.add(zone.getZoneName());
                        zoneIdMap.put(zone.getZoneName(), zone.getId());
                    }

                    // Populate the spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(SendSmsActivity.this, android.R.layout.simple_spinner_item, zoneNames);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_zone.setAdapter(adapter);

                    // Handle spinner item selection
                    spinner_zone.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String selectedZone = (String) parent.getItemAtPosition(position);
                            if (!selectedZone.equals("Zone")) {
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
                Toast.makeText(SendSmsActivity.this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
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
                    prabhagWardNames.add("Prabhag/Ward");

                    for (PrabhagWardItem prabhagWard : prabhagWards) {
                        prabhagWardNames.add(prabhagWard.getPrabhagWardName());
                        prabhagWardIdMap.put(prabhagWard.getPrabhagWardName(), prabhagWard.getId());
                    }

                    // Populate the spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(SendSmsActivity.this, android.R.layout.simple_spinner_item, prabhagWardNames);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_ward.setAdapter(adapter);

                    // Handle spinner item selection
                    spinner_ward.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String selectedPrabhagWard = (String) parent.getItemAtPosition(position);
                            if (!selectedPrabhagWard.equals("Prabhag Ward")) {
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
                Toast.makeText(SendSmsActivity.this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("API Error", "Error fetching prabhag wards: " + throwable.getMessage());
            }
        });
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

}