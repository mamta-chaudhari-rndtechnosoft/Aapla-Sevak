package com.vinodmapari.aaplasevak.Activity;

import static android.view.View.GONE;
import static com.vinodmapari.aaplasevak.ApiConfig.ApiHandler.getRetrofitInstance;
import static com.vinodmapari.aaplasevak.Model.Constants.colony_name;
import static com.vinodmapari.aaplasevak.Model.Constants.row_name;
import static com.vinodmapari.aaplasevak.Model.Constants.series_name;
import static com.vinodmapari.aaplasevak.Model.Constants.template_name;
import static com.vinodmapari.aaplasevak.Model.Constants.templates;
import static com.vinodmapari.aaplasevak.Model.Constants.water_supply_slots;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import retrofit2.Call;
import retrofit2.Callback;

import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
import com.vinodmapari.aaplasevak.Model.Colony;
import com.vinodmapari.aaplasevak.Model.Constants;
import com.vinodmapari.aaplasevak.Model.GetContact;
import com.vinodmapari.aaplasevak.Model.Row;
import com.vinodmapari.aaplasevak.Model.Series;
import com.vinodmapari.aaplasevak.Model.Template;
import com.vinodmapari.aaplasevak.Model.TemplateResponse;
import com.vinodmapari.aaplasevak.R;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SendSmsActivity extends AppCompatActivity {

    SearchableSpinner spinner_series, spinner_colony, spinner_row, spinner_water_Supply, spinner_template;
    int selected_series, selected_colony, selected_row, selected_water_supply, selected_template;
    String series_id, colony_id, row_id, water_supply_id, template_id;
    String series, colony, row, templateText;
    Button btnSend;
    ArrayList<Colony> colonies;
    EditText house_number, etTemplateText;
    private String colonyName;
    long selected_series_id, selected_colony_id;
    AVLoadingIndicatorView progress;

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
        house_number = findViewById(R.id.house_number);
        progress = findViewById(R.id.progress);
        progress.hide();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));

        toolbar.setTitle(Html.fromHtml("<b>" + "Send SMS" + "</b>"));
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        spinner_series.setTitle("select series");
        spinner_colony.setTitle("select colony");
        spinner_row.setTitle("select row");
        spinner_template.setTitle("select template");
        spinner_water_Supply.setTitle("select watersupply slot");

        colonies = new ArrayList<>();
        getSeriesList();


        series_name.clear();
        series_name = new ArrayList<>();

        for (int i = 0; i < Constants.series.size(); i++) {
            Constants.series_name.add(Constants.series.get(i).getSeries_name());
            if (series_name != null && series_name.equals(Constants.series.get(i).getSeries_name())) {
                selected_series = i;
            }

        }


        colony_name.clear();
        colony_name = new ArrayList<>();

        for (int i = 0; i < Constants.colonies.size(); i++) {
            Constants.colony_name.add(Constants.colonies.get(i).getColony_name());
            if (colony_name != null && colony_name.equals(Constants.colonies.get(i).getColony_name())) {
                selected_colony = i;
            }

        }

        row_name.clear();
        row_name = new ArrayList<>();

        for (int i = 0; i < Constants.rows.size(); i++) {
            Constants.row_name.add(Constants.rows.get(i).getRow_name());
            if (row_name != null && row_name.equals(Constants.rows.get(i).getRow_name())) {
                selected_row = i;
            }

        }

        water_supply_slots.clear();
        water_supply_slots = new ArrayList<>();

        for (int i = 0; i < Constants.waterSupplies.size(); i++) {
            Constants.water_supply_slots.add(Constants.waterSupplies.get(i).getSlot_name());
            if (water_supply_slots != null && water_supply_slots.equals(Constants.waterSupplies.get(i).getSlot_name())) {
                selected_water_supply = i;
            }

        }

        template_name.clear();
        template_name = new ArrayList<>();

        for (int i = 0; i < Constants.templates.size(); i++) {
            Constants.template_name.add(Constants.templates.get(i).getTemplate());
            if (template_name != null && template_name.equals(Constants.templates.get(i).getTemplate())) {
                selected_template = i;
            }

        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Constants.series_name);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_series.setAdapter(dataAdapter);

        ArrayAdapter<String> dataAdapter_colony = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, colony_name);
        dataAdapter_colony.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_colony.setAdapter(dataAdapter_colony);

        ArrayAdapter<String> dataAdapter_row = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, row_name);
        dataAdapter_row.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_row.setAdapter(dataAdapter_row);

        ArrayAdapter<String> dataAdapter_water_supply = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, water_supply_slots);
        dataAdapter_water_supply.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_water_Supply.setAdapter(dataAdapter_water_supply);
        spinner_water_Supply.setSelection(selected_water_supply);

        ArrayAdapter<String> dataAdapter_template = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, template_name);
        dataAdapter_template.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_template.setAdapter(dataAdapter_template);


        spinner_series.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                series_id = Constants.templates.get(position).getId();
                series = parent.getItemAtPosition(position).toString();

                selected_series_id = spinner_series.getSelectedItemId();

                if (selected_series_id != 0) {
                    getColonyList(selected_series_id);

                }
            }

            @Override

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_template.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        });


        spinner_colony.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
                colonyName = String.valueOf(spinner_colony.getSelectedItem());
                for (int i = 0; i < colonies.size(); i++) {

                    if (colonies.get(i).getColony_name().equalsIgnoreCase(colonyName)) {
                        getRowList(selected_series_id, Long.parseLong(colonies.get(i).getId()));

                        colony_id = String.valueOf(Long.parseLong(colonies.get(i).getId()));
                    }
//                    colony_id = Constants.colonies.get(position).getId();


                    if (selected_colony_id != 0) {
//

                    }
                }
            }

            @Override

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinner_row.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                row_id = Constants.rows.get(position).getId();
                row = parent.getItemAtPosition(position).toString();
            }

            @Override

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_water_Supply.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                water_supply_id = Constants.waterSupplies.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                getSMSList();

            }
        });

    }

    private void getSeriesList() {
//        Constants.series.clear();
//        Constants.series=new ArrayList<>();

        final RequestQueue requestQueue = Volley.newRequestQueue(SendSmsActivity.this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.series_list, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Constants.series.clear();
                Constants.series = new ArrayList<>();

                Constants.series.add(new Series("0", "select series"));
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    //Log.d("TAG", "onResponse: "+response);

                    JSONArray jsonArray = jsonObject.getJSONArray("SERIES");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String id = jsonObject1.getString("id");
                        String series_name = jsonObject1.getString("series_name");

                        Constants.series.add(new Series(id, series_name));
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
        super.onResume();
        getSeriesList();


    }

    @Override
    protected void onStart() {
        super.onStart();
        getSeriesList();


    }

    private void getColonyList(long series_id) {

        // Toast.makeText(SendSmsActivity.this, "series-id here"+series_id, Toast.LENGTH_SHORT).show();
        //recreate();
        final RequestQueue requestQueue = Volley.newRequestQueue(SendSmsActivity.this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.colony_list + "&series_id=" + series_id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Constants.colonies.clear();
                Constants.colony_name.clear();
                Constants.colony_name = new ArrayList<>();
                Constants.colonies = new ArrayList<>();
                spinner_colony.setAdapter(null);

                Constants.colonies.add(new Colony("0", "select colony"));
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Log.d("TAG", "onResponse: " + response);
                    // Toast.makeText(SendSmsActivity.this, ""+response, Toast.LENGTH_SHORT).show();

                    JSONArray jsonArray = jsonObject.getJSONArray("COLONY");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String id = jsonObject1.getString("id");
                        String colony_name = jsonObject1.getString("colony_name");

                        //  Toast.makeText(SendSmsActivity.this, "colonyName= "+colony_name, Toast.LENGTH_SHORT).show();


                        colonies.add(new Colony(id, colony_name));
                        Constants.colony_name.add(colony_name);
                        spinner_colony.setAdapter(new ArrayAdapter<String>(SendSmsActivity.this, android.R.layout.simple_spinner_item, Constants.colony_name));
//
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

    private void getRowList(long series_id, long colony_id) {
        //
        //
        Constants.rows.add(new Row("0", "select row"));
        //Toast.makeText(SendSmsActivity.this, "colony_id= "+colony_id, Toast.LENGTH_SHORT).show();
        final RequestQueue requestQueue = Volley.newRequestQueue(SendSmsActivity.this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.row_list + "&series_id=" + series_id + "&colony_id=" + colony_id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                spinner_row.setAdapter(null);
                Constants.rows.clear();
                Constants.row_name.clear();
                Constants.row_name = new ArrayList<>();
                Constants.rows = new ArrayList<>();


                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Log.d("TAG", "onResponse: " + response);

                    JSONArray jsonArray = jsonObject.getJSONArray("ROW");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String id = jsonObject1.getString("id");
                        String row_name = jsonObject1.getString("row_name");


                        Constants.rows.add(new Row(id, row_name));

                        //Toast.makeText(SendSmsActivity.this, "vaishnavi= "+id, Toast.LENGTH_SHORT).show();
                        Constants.row_name.add(row_name);
                        spinner_row.setAdapter(new ArrayAdapter<String>(SendSmsActivity.this, android.R.layout.simple_spinner_item, Constants.row_name));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                requestQueue.stop();
                // Toast.makeText(SendSmsActivity.this, ""+error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(stringRequest);

    }


    private void getSMSList() {

        progress.show();
        btnSend.setEnabled(false);

        final RequestQueue requestQueue = Volley.newRequestQueue(SendSmsActivity.this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.send_sms + "&series_id=" + series_id + "&colony_id=" + colony_id + "&row_id=" + row_id + "&template_name=" + etTemplateText.getText().toString(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {
                    JSONObject json = new JSONObject(response);
                    // Toast.makeText(GetWhatsappContactsActivity.this, "onResponse"+response, Toast.LENGTH_SHORT).show();

                    Log.d("TAG", "onResponse: " + response);

                    // Toast.makeText(SendSmsActivity.this, ""+response, Toast.LENGTH_SHORT).show();

                    JSONArray jsonArray = json.getJSONArray("SMS_LIST");

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject object = jsonArray.getJSONObject(i);
                        //here sms need to be send

                    }
                    progress.hide();
                    btnSend.setEnabled(true);
                    Toast.makeText(SendSmsActivity.this, "Sms has been sent", Toast.LENGTH_SHORT).show();


                } catch (JSONException e) {
                    e.printStackTrace();
                    progress.hide();
                    btnSend.setEnabled(true);

//
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                requestQueue.stop();
                progress.hide();
                btnSend.setEnabled(true);
            }
        });
        requestQueue.add(stringRequest);
    }


    /*private void templateDescription() {
        ApiInterface apiInterface = getRetrofitInstance().create(ApiInterface.class);

        //Call<List<List<Product>>> call = apiInterface.getAllProducts();
        Call<TemplateResponse> call = apiInterface.getTemplateData(template_id);
        call.enqueue(new Callback<TemplateResponse>() {
            @Override
            public void onResponse(Call<TemplateResponse> call, retrofit2.Response<TemplateResponse> response) {
                if (response.isSuccessful()) {

                    TemplateResponse templateResponse = response.body();
                    String templateDesc = templateResponse.getTEMPLATE_DESC().toString();
                    etTemplateText.setText(templateDesc);
                    Toast.makeText(SendSmsActivity.this, "Temp: " + templateDesc, Toast.LENGTH_SHORT).show();

                } else {
                    //mProgress.dismiss();
                    // Handle the case where the response body is null
                    Log.e("Tag", "Response is not get");
                    Toast.makeText(SendSmsActivity.this, "Response body is null.", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<TemplateResponse> call, Throwable throwable) {
                Log.e("Tag", "Error : " + throwable.getLocalizedMessage());
                Toast.makeText(SendSmsActivity.this, " Server Taking Too much Time to load ...", Toast.LENGTH_SHORT).show();
            }
        });


    }
*/

    private void fetchTemplateDescription() {
        RequestQueue requestQueue = Volley.newRequestQueue(SendSmsActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.templateDec + "&template_id=" + template_id ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Handle the JSON response


                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray templateDescArray = jsonObject.getJSONArray("TEMPLATE_DESC");

                            List<TemplateResponse> templateDescItems = new ArrayList<>();
                            for (int i = 0; i < templateDescArray.length(); i++) {
                                JSONObject templateDescObject = templateDescArray.getJSONObject(i);
                                String error = templateDescObject.getString("error");
                                String id = templateDescObject.getString("id");
                                String template = templateDescObject.getString("template");
                                String templateDesc = templateDescObject.getString("template_desc");

                                // Set the template description text to the TextView
                                etTemplateText.setText(templateDesc);

                                // Optionally, load an image using Picasso if there's an image URL
                                // For example: Picasso.get().load(imageUrl).into(imgLogo);

                               // templateDescItems.add(new TemplateResponse(error, id, template, templateDesc));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            //progress_splash.setVisibility(View.GONE);
                            Toast.makeText(SendSmsActivity.this, "Error parsing JSON response.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //progress_splash.setVisibility(View.GONE);
                Log.e("Tag", "Error : " + error.getLocalizedMessage());
                Toast.makeText(SendSmsActivity.this, "Server Taking Too Much Time to Load...", Toast.LENGTH_SHORT).show();
            }
        });

        // Add the request to the RequestQueue
        requestQueue.add(stringRequest);
    }







    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

}