package com.vinodmapari.aaplasevak.Fragment;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.vinodmapari.aaplasevak.R;


public class ContactFragment extends Fragment {

    public Toolbar toolbar;
    private TextView textView_app_name, textView_app_version, textView_app_author, textView_app_contact, textView_app_email, textView_app_website, textView_app_description,
            textView_titleVerson, textView_titleAuthore, textView_titleCompany, textView_titleEmail, textView_titleWebside, textView_titleAbout;

    private ImageView app_logo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_contact, container, false);


        return v;
    }
}