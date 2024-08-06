package com.vinodmapari.aaplasevak.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;
import com.vinodmapari.aaplasevak.Activity.SearchActivity;
import com.vinodmapari.aaplasevak.Activity.SearchSurveyMemberActivity;
import com.vinodmapari.aaplasevak.Activity.SendSmsActivity;
import com.vinodmapari.aaplasevak.Activity.UserSurveyActivity;
import com.vinodmapari.aaplasevak.Activity.WhatsappActivity;
import com.vinodmapari.aaplasevak.Model.Constants;
import com.vinodmapari.aaplasevak.Model.HomeOption;
import com.vinodmapari.aaplasevak.Model.HomeOptionsItem;
import com.vinodmapari.aaplasevak.Model.HomeOptionsResponseData;
import com.vinodmapari.aaplasevak.Model.SliderModel;
import com.vinodmapari.aaplasevak.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;

public class HomeFragment extends Fragment {

    CardView cdSurvey, cdSearch, cdSms, cdWhatsApp,cd_msg_member;
    LinearLayout sliderDotspanel;
    private int dotscount;
    private ViewPager viewPager;
    private SliderView slider_home;
    SliderAdapter sliderAdapter;
    SliderModel sliderModel;
    ArrayList<SliderModel> sliderModels;
    TextView tvSurvey,tvSearch, tvMsg, tv_whatsapp;
    ImageView imgSurvey, imgSearch, imgSms, imgWhatsApp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);
        cdSurvey = v.findViewById(R.id.cd_survey);
        cdSearch = v.findViewById(R.id.cd_search);
        cdSms = v.findViewById(R.id.cd_msg);
        cdWhatsApp = v.findViewById(R.id.cd_whatsapp);
        cd_msg_member = v.findViewById(R.id.cd_msg_member);
        slider_home = (SliderView) v.findViewById(R.id.slider_home);

        tvSurvey = v.findViewById(R.id.tv_survey);
        tvSearch = v.findViewById(R.id.tv_search);
        tvMsg = v.findViewById(R.id.tv_msg);
        tv_whatsapp = v.findViewById(R.id.tv_whatsapp);

        imgSurvey = v.findViewById(R.id.iv_survey);
        imgSearch = v.findViewById(R.id.iv_search);
        imgSms = v.findViewById(R.id.iv_msg);
        imgWhatsApp = v.findViewById(R.id.iv_whatsapp);

        homeOptions();

        Slider();
        sliderModels = new ArrayList<>();

        cdSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), UserSurveyActivity.class);
                startActivity(i);
            }
        });


        cdSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), SearchActivity.class);
                startActivity(i);
            }
        });

        cdSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), SendSmsActivity.class);
                startActivity(i);

            }
        });

        cdWhatsApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), WhatsappActivity.class);
                startActivity(i);
            }
        });

        cd_msg_member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), SearchSurveyMemberActivity.class);
                startActivity(i);
            }
        });

        return v;
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
                            sliderAdapter = new SliderAdapter(getActivity(), sliderModels);
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
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        //adding the string request to request queue
        requestQueue.add(stringRequest);

    }


    @Override
    public void onResume() {
        Slider();
        homeOptions();
        super.onResume();
    }


    private void addBottomDots(LinearLayout layout_dots, int size, int current) {
        ImageView[] dots = new ImageView[size];

        layout_dots.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new ImageView(getActivity());
            int width_height = 25;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(width_height, width_height));
            params.setMargins(10, 10, 10, 10);
            dots[i].setLayoutParams(params);
            dots[i].setImageResource(R.drawable.shape_circle);
            dots[i].setColorFilter(ContextCompat.getColor(getActivity(), R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
            layout_dots.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[current].setColorFilter(ContextCompat.getColor(getActivity(), R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
        }
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


    private class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterVH> {

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
            return new SliderAdapterVH(inflate);

        }

        @Override
        public void onBindViewHolder(SliderAdapterVH holder, final int position) {

            SliderModel sliderItem = mSliderItems.get(position);


            displayImageOriginal(getContext(), holder.imageSlider, mSliderItems, position);


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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Slider();
        super.onViewCreated(view, savedInstanceState);
    }

    private void homeOptions() {
        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());
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

                                homeOptions.add(new HomeOptionsItem(id,image,name1));
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
                            Toast.makeText(requireContext(), "Error parsing JSON response.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //progressBar.setVisibility(View.GONE);
                Log.e("Tag", "Error: " + error.getLocalizedMessage());
                Toast.makeText(requireContext(), "Server taking too much time to load...", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(stringRequest);
    }


}







