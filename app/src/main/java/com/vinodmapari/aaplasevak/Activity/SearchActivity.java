package com.vinodmapari.aaplasevak.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.vinodmapari.aaplasevak.Model.Constants;
import com.vinodmapari.aaplasevak.Model.SearchList;
import com.vinodmapari.aaplasevak.R;
import com.vinodmapari.aaplasevak.SearchAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    ImageView search;
    SearchAdapter searchAdapter;
    RecyclerView rv;
    TextView textView_empty;
    LottieAnimationView loader, empty_icon;
    ArrayList<SearchList> searchLists;
    EditText etName, etLname, etMname, etVoterId, etAdharCard, etFullName;
    String name, lname, mname, voterId, adharcard, fullName;
    Button btn;
    LinearLayoutManager mLayoutManager;
    private int positionSelect;
    private int pageNo = 0;
    private int pageLimit = 20;
    private Boolean loadingMore = false;
    private Boolean viewMore = false;
    int pastVisiblesItems = 0, visibleItemCount = 0, totalItemCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        search = findViewById(R.id.iv_search_icon);
        rv = findViewById(R.id.rv_search);
        etAdharCard = findViewById(R.id.adharCard);
        etVoterId = findViewById(R.id.et_voterID);
        etFullName = findViewById(R.id.et_fullName);
        /*etName = findViewById(R.id.et_name);
        etMname = findViewById(R.id.et_mname);
        etLname = findViewById(R.id.et_lname);*/

        btn = findViewById(R.id.btnview);

        pageNo = 0;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));

        toolbar.setTitle(Html.fromHtml("<b>" + "Search" + "</b>"));
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        searchLists = new ArrayList<>();

        textView_empty = findViewById(R.id.tv_empty_search);
        textView_empty.setVisibility(View.VISIBLE);
        textView_empty.setText(R.string.type_search);
        loader = findViewById(R.id.loader);
        empty_icon = findViewById(R.id.empty_icon);
        loader.setVisibility(View.GONE);
        empty_icon.setVisibility(View.GONE);

        mLayoutManager = new LinearLayoutManager(SearchActivity.this);
        rv.setLayoutManager(mLayoutManager);

        addTextWatchers();

        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) //check for scroll down
                {
                    visibleItemCount = mLayoutManager.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();

                    if (!loadingMore) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount && (viewMore)) {
                            loadingMore = true;
                            getSearchList();
                        }
                    }
                }
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //name = etName.getText().toString();
                //mname = etMname.getText().toString();
                //lname = etLname.getText().toString();
                fullName = etFullName.getText().toString();
                voterId = etVoterId.getText().toString();
                adharcard = etAdharCard.getText().toString();

                getSearchList();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //name = etName.getText().toString();
                //mname = etMname.getText().toString();
                //lname = etLname.getText().toString();
                fullName = etFullName.getText().toString();
                voterId = etVoterId.getText().toString();
                adharcard = etAdharCard.getText().toString();
                pageNo = pageNo + 1;
                getSearchList();
            }
        });
    }

    private void addTextWatchers() {
        //etName.addTextChangedListener(textWatcher);
        //etMname.addTextChangedListener(textWatcher);
        //etLname.addTextChangedListener(textWatcher);
        etFullName.addTextChangedListener(textWatcher);
        etVoterId.addTextChangedListener(textWatcher);
        etAdharCard.addTextChangedListener(textWatcher);
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            //name = etName.getText().toString();
            //mname = etMname.getText().toString();
            //lname = etLname.getText().toString();
            fullName = etFullName.getText().toString();
            voterId = etVoterId.getText().toString();
            adharcard = etAdharCard.getText().toString();

            pageNo = 0; // Reset to the first page for new search
            getSearchList();
        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    };

    /*private void getSearchList() {
        loader.setVisibility(View.VISIBLE);
        empty_icon.setVisibility(View.GONE);

        final RequestQueue requestQueue = Volley.newRequestQueue(SearchActivity.this);
        String url = Constants.search_list + "&name=" + name + "&middle_name=" + mname + "&surname=" + lname + "&voter_id=" + voterId + "&adharcard=" + adharcard + "&page=" + pageNo;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (pageNo == 0) {
                    searchLists.clear();
                }

                try {
                    JSONObject json = new JSONObject(response);
                    Log.d("search", response);

                    JSONArray jsonArray = json.getJSONArray("SEARCH");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        String id = object.getString("id");
                        String name = object.getString("name");
                        String middle_name = object.getString("middle_name");
                        String surname = object.getString("surname");
                        String voterId = object.getString("voter_id");
                        String adhar_card = object.getString("adhar_card");
                        String dob = object.getString("dob");
                        String event = object.getString("event");
                        String qualification = object.getString("qualification");
                        String relation = object.getString("relation");
                        String mobile1 = object.getString("mobile1");
                        String mobile2 = object.getString("mobile2");
                        String row_name = object.getString("row_name");
                        String gender = object.getString("gender");
                        String house_no = object.getString("house_no");
                        String series_name = object.getString("series_name");
                        String status_name = object.getString("status_name");
                        String colony_name = object.getString("colony_name");
                        String slot_name = object.getString("slot_name");
                        String caste = object.getString("caste");
                        String voting_center = object.getString("voting_center");
                        String member_id = object.getString("member_id");

                        searchLists.add(new SearchList(id, house_no, series_name, colony_name, row_name, gender, name, middle_name, surname, mobile1, mobile2, dob, qualification, caste, status_name, relation, event, voterId, adhar_card, slot_name, voting_center, member_id));
                    }

                    viewMore = jsonArray.length() == pageLimit;
                    loadingMore = false;
                    if (searchLists.size() > 0) {
                        loader.setVisibility(View.GONE);
                        textView_empty.setVisibility(View.GONE);
                        empty_icon.setVisibility(View.GONE);
                        rv.setVisibility(View.VISIBLE);
                        if (searchAdapter == null) {
                            searchAdapter = new SearchAdapter(SearchActivity.this, searchLists);
                            rv.setAdapter(searchAdapter);
                        } else {
                            searchAdapter.notifyDataSetChanged();
                        }
                        rv.scrollToPosition(0);
                    } else {
                        loader.setVisibility(View.GONE);
                        rv.setVisibility(View.GONE);
                        textView_empty.setText("No matches found");
                        textView_empty.setVisibility(View.VISIBLE);
                        empty_icon.setVisibility(View.VISIBLE);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    loader.setVisibility(View.GONE);
                    rv.setVisibility(View.GONE);
                    textView_empty.setVisibility(View.VISIBLE);
                    textView_empty.setText("No matches found");
                    empty_icon.setVisibility(View.VISIBLE);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                requestQueue.stop();
                loader.setVisibility(View.GONE);
                rv.setVisibility(View.GONE);
                textView_empty.setVisibility(View.VISIBLE);
            }
        });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);
    }*/

    private void getSearchList() {
        loader.setVisibility(View.VISIBLE);
        empty_icon.setVisibility(View.GONE);

        final RequestQueue requestQueue = Volley.newRequestQueue(SearchActivity.this);
        // Construct the search query URL dynamically
        String url = Constants.search_list +
                "&fullname=" + etFullName.getText().toString().trim() +
                "&voter_id=" + etVoterId.getText().toString().trim() +
                "&adharcard=" + etAdharCard.getText().toString().trim() +
                "&page=" + pageNo;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (pageNo == 0) {
                    searchLists.clear();
                }

                try {
                    JSONObject json = new JSONObject(response);
                    Log.d("search", response);

                    JSONArray jsonArray = json.getJSONArray("SEARCH");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        String id = object.getString("id");
                        String name = object.getString("name");
                        String middle_name = object.getString("middle_name");
                        String surname = object.getString("surname");
                        String voterId = object.getString("voter_id");
                        String adhar_card = object.getString("adhar_card");
                        String dob = object.getString("dob");
                        String event = object.getString("event");
                        String qualification = object.getString("qualification");
                        String relation = object.getString("relation");
                        String mobile1 = object.getString("mobile1");
                        String mobile2 = object.getString("mobile2");
                        String row_name = object.getString("row_name");
                        String gender = object.getString("gender");
                        String house_no = object.getString("house_no");
                        String series_name = object.getString("series_name");
                        String status_name = object.getString("status_name");
                        String colony_name = object.getString("colony_name");
                        String slot_name = object.getString("slot_name");
                        String caste = object.getString("caste");
                        String voting_center = object.getString("voting_center");
                        String member_id = object.getString("member_id");

                        searchLists.add(new SearchList(id, house_no, series_name, colony_name, row_name, gender, name, middle_name, surname, mobile1, mobile2, dob, qualification, caste, status_name, relation, event, voterId, adhar_card, slot_name, voting_center, member_id));
                    }

                    viewMore = jsonArray.length() == pageLimit;
                    loadingMore = false;
                    if (searchLists.size() > 0) {
                        loader.setVisibility(View.GONE);
                        textView_empty.setVisibility(View.GONE);
                        empty_icon.setVisibility(View.GONE);
                        rv.setVisibility(View.VISIBLE);
                        if (searchAdapter == null) {
                            searchAdapter = new SearchAdapter(SearchActivity.this, searchLists);
                            rv.setAdapter(searchAdapter);
                        } else {
                            searchAdapter.notifyDataSetChanged();
                        }
                        rv.scrollToPosition(0);
                    } else {
                        loader.setVisibility(View.GONE);
                        rv.setVisibility(View.GONE);
                        textView_empty.setText("No matches found");
                        textView_empty.setVisibility(View.VISIBLE);
                        empty_icon.setVisibility(View.VISIBLE);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    loader.setVisibility(View.GONE);
                    rv.setVisibility(View.GONE);
                    textView_empty.setVisibility(View.VISIBLE);
                    textView_empty.setText("No matches found");
                    empty_icon.setVisibility(View.VISIBLE);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                requestQueue.stop();
                loader.setVisibility(View.GONE);
                rv.setVisibility(View.GONE);
                textView_empty.setVisibility(View.VISIBLE);
            }
        });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);
    }



    @Override
    protected void onResume() {
        pageNo = 0;
        getSearchList();
        super.onResume();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
}
