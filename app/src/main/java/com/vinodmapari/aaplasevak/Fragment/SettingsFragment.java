package com.vinodmapari.aaplasevak.Fragment;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.vinodmapari.aaplasevak.Activity.GetWhatsappContactsActivity;
import com.vinodmapari.aaplasevak.Model.Constants;
import com.vinodmapari.aaplasevak.Model.Wp_Img;
import com.vinodmapari.aaplasevak.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class SettingsFragment extends Fragment
{
   ArrayList<Wp_Img> imgs;
    ImageView iv;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
         View v=inflater.inflate(R.layout.fragment_settings, container, false);

        // iv=v.findViewById(R.id.iv);


        imgs=new ArrayList<>();
     //   getImage();
        return v;


    }
//    private void getImage() {
//        imgs.clear();
//      imgs=new ArrayList<>();
//
//        final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
//
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.whatsapp_img, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//
//
//                try {
//                    JSONObject jsonObject = new JSONObject(response);
//                 //   Log.d("TAG", "onResponse: " + response);
//
//                    JSONArray jsonArray = jsonObject.getJSONArray("WTSIMG");
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
//                        String id = jsonObject1.getString("id");
//                        String image = jsonObject1.getString("image");
//
//
//
//                        Glide.with(getActivity()).load(image).thumbnail(Glide.with(getActivity()).load(R.drawable.loading)).into(iv);
//
//
//
//                    }
//
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                requestQueue.stop();
//            }
//        });
//        requestQueue.add(stringRequest);
//
//    }

}