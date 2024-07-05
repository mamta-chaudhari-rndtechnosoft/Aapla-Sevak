package com.vinodmapari.aaplasevak.Activity;

import static com.vinodmapari.aaplasevak.Model.Constants.colony_name;
import static com.vinodmapari.aaplasevak.Model.Constants.row_name;
import static com.vinodmapari.aaplasevak.Model.Constants.series_name;
import static com.vinodmapari.aaplasevak.Model.Constants.water_supply_slots;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;
import com.vinodmapari.aaplasevak.Model.Colony;
import com.vinodmapari.aaplasevak.Model.Constants;
import com.vinodmapari.aaplasevak.Model.Row;
import com.vinodmapari.aaplasevak.Model.Series;
import com.vinodmapari.aaplasevak.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class WhatsappActivity extends AppCompatActivity {
    SearchableSpinner spinner_series, spinner_colony, spinner_row, spinner_water_Supply;
    SearchableSpinner spinner_constituency, spinner_city_village, spinner_zone, spinner_ward;
    int selected_series, selected_colony, selected_row, selected_water_supply;
    String series_id, colony_id, row_id, water_supply_id;
    String series, colony, row, watersupply, house_no, message, image;
    Button getContacts, btnWhatsAppApi;
    long selected_series_id, selected_colony_id;
    ArrayList<Colony> colonies;
    EditText house_number, text;
    // EditText etMessage;
    //CheckBox checkBox;
    private String colonyName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whatsapp);

        spinner_series = (SearchableSpinner) findViewById(R.id.spinnerSeries);
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

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));

        toolbar.setTitle(Html.fromHtml("<b>" + "Send Message" + "</b>"));
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        spinner_colony.setTitle("select colony");
        spinner_row.setTitle("select row");
        spinner_water_Supply.setTitle("select watersupply slot");

        spinner_constituency.setTitle("Select Constituency");
        spinner_city_village.setTitle("Select City/Village");
        spinner_zone.setTitle("Select Zone");
        spinner_ward.setTitle("Select Ward/Prabhag");

        colonies = new ArrayList<>();
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


        getSeriesList();

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Constants.series_name);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_series.setAdapter(dataAdapter);
        spinner_series.setSelection(selected_series);


        ArrayAdapter<String> dataAdapter_colony = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, colony_name);
        dataAdapter_colony.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_colony.setAdapter(dataAdapter_colony);
        spinner_colony.setSelection(selected_colony);

        ArrayAdapter<String> dataAdapter_row = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, row_name);
        dataAdapter_row.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_row.setAdapter(dataAdapter_row);
        spinner_row.setSelection(selected_row);

        ArrayAdapter<String> dataAdapter_water_supply = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, water_supply_slots);
        dataAdapter_water_supply.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_water_Supply.setAdapter(dataAdapter_water_supply);
        spinner_water_Supply.setSelection(selected_water_supply);


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


        spinner_colony.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                colonyName = String.valueOf(spinner_colony.getSelectedItem());
                colony_id = "0";
                boolean colonyFound = false;

                for (int i = 0; i < colonies.size(); i++) {
                    if (colonies.get(i).getColony_name().equalsIgnoreCase(colonyName)) {
                        getRowList(selected_series_id, Long.parseLong(colonies.get(i).getId()));
                        colony_id = String.valueOf(Long.parseLong(colonies.get(i).getId()));
                        colony = parent.getItemAtPosition(position).toString();
                        colonyFound = true;
                        break;
                    }

                    if (!colonyFound) {
                        // If no matching colony was found, ensure colony_id remains "0"
                        colony_id = "0";
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
                watersupply = parent.getItemAtPosition(position).toString();
                // Toast.makeText(WhatsappActivity.this, ""+water_supply_id, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


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
                // }

            }
        });

        btnWhatsAppApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(WhatsappActivity.this, WhatsAppApiActivity.class);

                //intent.putExtra("house_no", house_number.getText().toString());
                intent.putExtra("series_id", series_id);
                intent.putExtra("row_id", row_id);
                intent.putExtra("water_Supply_id", water_supply_id);
                intent.putExtra("colony_id", colony_id);

                startActivity(intent);
                // }

            }
        });


    }


    private void getSeriesList() {
        spinner_series.setTitle("select series");
//        Constants.series.clear();
//        Constants.series=new ArrayList<>();

        final RequestQueue requestQueue = Volley.newRequestQueue(WhatsappActivity.this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.series_list, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Constants.series.clear();
                Constants.series = new ArrayList<>();

                Constants.series.add(new Series("0", "select series"));
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    //  Log.d("TAG", "onResponse: "+response);

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
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    private void getColonyList(long series_id) {

        //Toast.makeText(WhatsappActivity.this, "series-id here"+series_id, Toast.LENGTH_SHORT).show();
        //recreate();
        final RequestQueue requestQueue = Volley.newRequestQueue(WhatsappActivity.this);

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
                    //   Toast.makeText(WhatsappActivity.this, ""+response, Toast.LENGTH_SHORT).show();

                    JSONArray jsonArray = jsonObject.getJSONArray("COLONY");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String id = jsonObject1.getString("id");
                        String colony_name = jsonObject1.getString("colony_name");

                        // Toast.makeText(WhatsappActivity.this, "colonyName= "+colony_name, Toast.LENGTH_SHORT).show();


                        colonies.add(new Colony(id, colony_name));
                        Constants.colony_name.add(colony_name);
                        spinner_colony.setAdapter(new ArrayAdapter<String>(WhatsappActivity.this, android.R.layout.simple_spinner_item, Constants.colony_name));
//                        adapter = new CustomAdapter(UserSurveyActivity.this,colonies);
//                        spinner_colony.setAdapter(adapter);
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
        Constants.rows.add(new Row("0", "select row"));
        // Toast.makeText(WhatsappActivity.this, "colony_id= "+colony_id, Toast.LENGTH_SHORT).show();
        final RequestQueue requestQueue = Volley.newRequestQueue(WhatsappActivity.this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.row_list + "&series_id=" + series_id + "&colony_id=" + colony_id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Constants.rows.clear();
                Constants.row_name.clear();
                Constants.row_name = new ArrayList<>();
                Constants.rows = new ArrayList<>();
                spinner_row.setAdapter(null);


                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Log.d("TAG", "onResponse: " + response);

                    JSONArray jsonArray = jsonObject.getJSONArray("ROW");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String id = jsonObject1.getString("id");
                        String row_name = jsonObject1.getString("row_name");


                        Constants.rows.add(new Row(id, row_name));
                        Constants.row_name.add(row_name);
                        spinner_row.setAdapter(new ArrayAdapter<String>(WhatsappActivity.this, android.R.layout.simple_spinner_item, Constants.row_name));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                requestQueue.stop();
                Toast.makeText(WhatsappActivity.this, "" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(stringRequest);

    }

}