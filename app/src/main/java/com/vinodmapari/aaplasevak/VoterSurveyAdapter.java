package com.vinodmapari.aaplasevak;

import static com.vinodmapari.aaplasevak.ApiConfig.ApiHandler.getRetrofitInstance;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.toptoche.searchablespinnerlibrary.SearchableSpinner;
import com.vinodmapari.aaplasevak.Activity.UserSurveyActivity;
import com.vinodmapari.aaplasevak.ApiConfig.ApiInterface;
import com.vinodmapari.aaplasevak.Model.CityVillageItem;
import com.vinodmapari.aaplasevak.Model.CityVillageResponse;
import com.vinodmapari.aaplasevak.Model.ConstituencyItem;
import com.vinodmapari.aaplasevak.Model.ConstituencyResponse;
import com.vinodmapari.aaplasevak.Model.PrabhagWardItem;
import com.vinodmapari.aaplasevak.Model.PrabhagWardResponse;
import com.vinodmapari.aaplasevak.Model.SurveyDetailItem;
import com.vinodmapari.aaplasevak.Model.VoterCountResponse;
import com.vinodmapari.aaplasevak.Model.VoterSurveyByIdResponse;
import com.vinodmapari.aaplasevak.Model.ZoneItem;
import com.vinodmapari.aaplasevak.Model.ZoneResponse;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class VoterSurveyAdapter extends RecyclerView.Adapter<VoterSurveyAdapter.ViewHolder> {
    private final Context context;
    private final RecyclerView recyclerView;
    private ArrayList<String> voterIds;
    String series_id, status_id, colony_id, row_id, water_supply_id, constituency_id, city_id, zone_id, ward_id, qualification_id, caste_id;
    private ArrayList<SurveyDetailItem> surveyDetailItems = new ArrayList<>();


    public VoterSurveyAdapter(Context context, ArrayList<String> voterIds, RecyclerView recyclerView) {
        this.context = context;
        this.voterIds = voterIds;
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public VoterSurveyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_voter_survey, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VoterSurveyAdapter.ViewHolder holder, int position) {
        String voterId = voterIds.get(position);
        getSurveyDetail(voterId, holder);
    }

    @Override
    public int getItemCount() {
        return voterIds.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        SearchableSpinner spinner_constituency, spinner_city, spinner_ward, spinner_zone;
        EditText etName, etMiddleName, etSurName, etEpicNo, etBoothNo, etSerialNo, etVotingCenter;
        String gender, dob;
        RadioGroup radioGroup;
        TextView tvDob;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDob = itemView.findViewById(R.id.dob);
            spinner_constituency = itemView.findViewById(R.id.spinnerConstituency);
            spinner_city = itemView.findViewById(R.id.spinnerCity_Village);
            spinner_zone = itemView.findViewById(R.id.spinnerZone);
            spinner_ward = itemView.findViewById(R.id.spinnerPrabhag_Ward);

            etName = itemView.findViewById(R.id.name);
            etMiddleName = itemView.findViewById(R.id.middle_name);
            etSurName = itemView.findViewById(R.id.surname);
            etBoothNo = itemView.findViewById(R.id.etBoothNo);
            etSerialNo = itemView.findViewById(R.id.etSerialNo);
            etVotingCenter = itemView.findViewById(R.id.voting_center);
            etEpicNo = itemView.findViewById(R.id.voterID);
            radioGroup = itemView.findViewById(R.id.radioGroup);
        }
    }

    public void getSurveyDetail(String surveyId, ViewHolder holder) {


        ApiInterface apiInterface = getRetrofitInstance().create(ApiInterface.class);

        Call<VoterSurveyByIdResponse> call = apiInterface.getSurveyVoterById(surveyId);

        call.enqueue(new Callback<VoterSurveyByIdResponse>() {
            @Override
            public void onResponse(Call<VoterSurveyByIdResponse> call, Response<VoterSurveyByIdResponse> response) {
                if (response.isSuccessful()) {
                    VoterSurveyByIdResponse voterSurveyByIdResponse = response.body();
                    //surveyDetailItemArrayList = voterSurveyByIdResponse.getSurveyDetailItems();
                    SurveyDetailItem surveyDetail = voterSurveyByIdResponse.getSurveyDetailItems().get(0);


                    /*String houseNo = surveyDetailItemArrayList.get(0).getHouseNo();
                    String seriesName = surveyDetailItemArrayList.get(0).getSeriesName();
                    String colonyName = surveyDetailItemArrayList.get(0).getColonyName();
                    String rowName = surveyDetailItemArrayList.get(0).getRowName();
                    String statusName = surveyDetailItemArrayList.get(0).getStatusName();
                    String slotName = surveyDetailItemArrayList.get(0).getSlotName();
                    String gender = surveyDetailItemArrayList.get(0).getGender();
                    String name = surveyDetailItemArrayList.get(0).getName();
                    String middleName = surveyDetailItemArrayList.get(0).getMiddleName();
                    String surName = surveyDetailItemArrayList.get(0).getSurname();
                    String dob = surveyDetailItemArrayList.get(0).getDob();
                    String qualification = surveyDetailItemArrayList.get(0).getQualification();
                    String caste = surveyDetailItemArrayList.get(0).getCaste();
                    String relation = surveyDetailItemArrayList.get(0).getRelation();
                    String event = surveyDetailItemArrayList.get(0).getEvent();
                    String voterId = surveyDetailItemArrayList.get(0).getVoterId();
                    String adharCard = surveyDetailItemArrayList.get(0).getAdharCard();
                    String votingCenter = surveyDetailItemArrayList.get(0).getVotingCenter();
                    String memberId = surveyDetailItemArrayList.get(0).getMemberId();
                    String boothNo = surveyDetailItemArrayList.get(0).getBoothNo();
                    String votingSrNo = surveyDetailItemArrayList.get(0).getVotingSrNo();*/


                    holder.etName.setText(surveyDetail.getName());
                    holder.etMiddleName.setText(surveyDetail.getMiddleName());
                    holder.etSurName.setText(surveyDetail.getSurname());
                    holder.etBoothNo.setText(surveyDetail.getBoothNo());
                    holder.etSerialNo.setText(surveyDetail.getSerialNo());
                    holder.etVotingCenter.setText(surveyDetail.getVotingCenter());
                    holder.etEpicNo.setText(surveyDetail.getEpicNo());

                    constituency_id = surveyDetail.getConstituencyName();
                    fetchConstituencies(holder);

                    city_id = surveyDetail.getCityVillageName();
                    fetchCityVillages(holder);

                    zone_id = surveyDetail.getZoneName();
                    fetchZones(holder);

                    ward_id = surveyDetail.getPrabhagWardName();
                    fetchPrabhagWards(holder);

                    //Toast.makeText(context, "C: " + constituency_id + " Ci: " + city_id + " Z: " + zone_id + " W: " + ward_id,  Toast.LENGTH_SHORT).show();

                    if (surveyDetail.getGender() != null && !surveyDetail.getGender().isEmpty()) {
                        if (surveyDetail.getGender().equalsIgnoreCase("Male")) {
                            holder.radioGroup.check(R.id.radioButton);
                        } else if (surveyDetail.getGender().equalsIgnoreCase("Female")) {
                            holder.radioGroup.check(R.id.radioButton2);
                        } else {
                            holder.radioGroup.check(R.id.radioButton3);
                        }
                    }

                    if (surveyDetail.getDob() != null) {
                        holder.tvDob.setText(surveyDetail.getDob());
                    } else {
                        holder.tvDob.setText("DOB");
                    }

                    holder.tvDob.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            final Calendar c = Calendar.getInstance();
                            int mYear = c.get(Calendar.YEAR);
                            int mMonth = c.get(Calendar.MONTH);
                            int mDay = c.get(Calendar.DAY_OF_MONTH);

                            DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                                    new DatePickerDialog.OnDateSetListener() {
                                        @Override
                                        public void onDateSet(DatePicker view, int year,
                                                              int monthOfYear, int dayOfMonth) {
                                            holder.tvDob.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                        }
                                    }, mYear, mMonth, mDay);
                            datePickerDialog.show();
                        }
                    });

                    // add data to list
                    surveyDetailItems.add(surveyDetail);

                } else {
                    Toast.makeText(context, "Response not success", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<VoterSurveyByIdResponse> call, Throwable throwable) {
                Toast.makeText(context, "Response Error", Toast.LENGTH_SHORT).show();
            }
        });
    }


  //----------- Collect all the item data in array to send in api ---------------------
    public ArrayList<SurveyDetailItem> collectSurveyData() {
        ArrayList<SurveyDetailItem> surveyDataList = new ArrayList<>();
        for (int i = 0; i < getItemCount(); i++) {
            ViewHolder holder = (ViewHolder) recyclerView.findViewHolderForAdapterPosition(i);

            if (holder != null) {
                SurveyDetailItem surveyDetail = new SurveyDetailItem();
                surveyDetail.setGender(holder.radioGroup.getCheckedRadioButtonId() == R.id.radioButton ? "Male" :
                        holder.radioGroup.getCheckedRadioButtonId() == R.id.radioButton2 ? "Female" : "Others");
                surveyDetail.setName(holder.etName.getText().toString());
                surveyDetail.setMiddleName(holder.etMiddleName.getText().toString());
                surveyDetail.setSurname(holder.etSurName.getText().toString());
                surveyDetail.setMobile1(holder.etEpicNo.getText().toString());
                surveyDetail.setDob(holder.tvDob.getText().toString());
                surveyDetail.setEpicNo(holder.etEpicNo.getText().toString());
                surveyDetail.setVotingCenter(holder.etVotingCenter.getText().toString());
                surveyDetail.setBoothNo(holder.etBoothNo.getText().toString());
                surveyDetail.setSerialNo(holder.etSerialNo.getText().toString());
                //surveyDetail.setConstituencyName(holder.spinner_constituency.getSelectedItem().toString());
                surveyDetail.setConstituencyName(constituency_id);
                surveyDetail.setCityVillageName(holder.spinner_city.getSelectedItem().toString());
                surveyDetail.setZoneName(holder.spinner_zone.getSelectedItem().toString());
                surveyDetail.setPrabhagWardName(holder.spinner_ward.getSelectedItem().toString());

                surveyDataList.add(surveyDetail);
            }
        }
        return surveyDataList;
    }

    public ArrayList<SurveyDetailItem> getSurveyDetailItems() {
        return surveyDetailItems;
    }

    //------------------------------------------------ All the spinner api call below ------------------------------

    private void fetchConstituencies(ViewHolder holder) {

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
                    constituencyName.add("Constituency*");

                    for (ConstituencyItem constituency : constituencies) {
                        constituencyName.add(constituency.getConstituencyName());
                        constituencyIdMap.put(constituency.getConstituencyName(), constituency.getId());
                    }
                    // Populate the spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, constituencyName);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    holder.spinner_constituency.setAdapter(adapter);

                    // for predefault value
                    String selectedConstituencyId = constituency_id;
                    if (selectedConstituencyId != null) {
                        String selectedConstituencyName = getConstituencyById(constituencyIdMap, selectedConstituencyId);
                        if (selectedConstituencyName != null) {
                            int position = adapter.getPosition(selectedConstituencyName);
                            if (position >= 0) {
                                holder.spinner_constituency.setSelection(position);
                            }
                        }
                    }

                    holder.spinner_constituency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                Toast.makeText(context, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("API Error", "Error fetching prabhag wards: " + throwable.getMessage());
            }
        });
    }

    private String getConstituencyById(Map<String, String> constituencyIdMap, String constituencyId) {
        for (Map.Entry<String, String> entry : constituencyIdMap.entrySet()) {
            if (entry.getValue().equals(constituencyId)) {
                return entry.getKey();
            }
        }
        return null;
    }

    private void fetchCityVillages(ViewHolder holder) {
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
                    cityVillageNames.add("City/Village*");

                    for (CityVillageItem item : cityVillages) {
                        cityVillageNames.add(item.getCityVillageName());
                        cityVillageIdMap.put(item.getCityVillageName(), item.getId());
                    }

                    // Populate the spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(context,
                            android.R.layout.simple_spinner_item, cityVillageNames);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    holder.spinner_city.setAdapter(adapter);

                    // Handle spinner item selection
                    // for predefault value
                    String selectedCityId = city_id;
                    if (selectedCityId != null) {
                        String selectedCityName = getCityById(cityVillageIdMap, selectedCityId);
                        if (selectedCityName != null) {
                            int position = adapter.getPosition(selectedCityName);
                            if (position >= 0) {
                                holder.spinner_city.setSelection(position);
                            }
                        }
                    }


                    holder.spinner_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                Toast.makeText(context, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("API Error", "Error fetching prabhag wards: " + throwable.getMessage());
            }
        });
    }

    private String getCityById(Map<String, String> cityIdMap, String cityId) {
        for (Map.Entry<String, String> entry : cityIdMap.entrySet()) {
            if (entry.getValue().equals(cityId)) {
                return entry.getKey();
            }
        }
        return null;
    }

    private void fetchZones(ViewHolder holder) {
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
                    zoneNames.add("Zone*");

                    for (ZoneItem zone : zones) {
                        zoneNames.add(zone.getZoneName());
                        zoneIdMap.put(zone.getZoneName(), zone.getId());
                    }

                    // Populate the spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(context,
                            android.R.layout.simple_spinner_item, zoneNames);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    holder.spinner_zone.setAdapter(adapter);

                    String selectedZoneId = zone_id;
                    if (selectedZoneId != null) {
                        String selectedZoneName = getZoneById(zoneIdMap, selectedZoneId);
                        if (selectedZoneName != null) {
                            int position = adapter.getPosition(selectedZoneName);
                            if (position >= 0) {
                                holder.spinner_zone.setSelection(position);
                            }
                        }
                    }


                    // Handle spinner item selection
                    holder.spinner_zone.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                Toast.makeText(context, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("API Error", "Error fetching prabhag wards: " + throwable.getMessage());
            }
        });

    }

    private String getZoneById(Map<String, String> zoneIdMap, String zoneId) {
        for (Map.Entry<String, String> entry : zoneIdMap.entrySet()) {
            if (entry.getValue().equals(zoneId)) {
                return entry.getKey();
            }
        }
        return null;
    }

    private void fetchPrabhagWards(ViewHolder holder) {
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
                    prabhagWardNames.add("Prabhag/Ward*");

                    for (PrabhagWardItem prabhagWard : prabhagWards) {
                        prabhagWardNames.add(prabhagWard.getPrabhagWardName());
                        prabhagWardIdMap.put(prabhagWard.getPrabhagWardName(), prabhagWard.getId());
                    }

                    // Populate the spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(context,
                            android.R.layout.simple_spinner_item, prabhagWardNames);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    holder.spinner_ward.setAdapter(adapter);

                    String selectedWardId = ward_id;
                    if (selectedWardId != null) {
                        String selectedWardName = getWardById(prabhagWardIdMap, selectedWardId);
                        if (selectedWardName != null) {
                            int position = adapter.getPosition(selectedWardName);
                            if (position >= 0) {
                                holder.spinner_ward.setSelection(position);
                            }
                        }
                    }

                    // Handle spinner item selection
                    holder.spinner_ward.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String selectedPrabhagWard = (String) parent.getItemAtPosition(position);
                            if (!selectedPrabhagWard.equals("Prabhag/Ward")) {
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
                Toast.makeText(context, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("API Error", "Error fetching prabhag wards: " + throwable.getMessage());
            }
        });

    }

    private String getWardById(Map<String, String> wardIdMap, String wardId) {
        for (Map.Entry<String, String> entry : wardIdMap.entrySet()) {
            if (entry.getValue().equals(wardId)) {
                return entry.getKey();
            }
        }
        return null;
    }

}
