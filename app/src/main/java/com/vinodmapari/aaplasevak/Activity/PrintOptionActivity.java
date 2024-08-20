package com.vinodmapari.aaplasevak.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;
import com.vinodmapari.aaplasevak.R;

public class PrintOptionActivity extends AppCompatActivity {

    MaterialCardView cardColony, cardFamily;
    MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print);

        cardColony = findViewById(R.id.cardColony);
        cardFamily = findViewById(R.id.cardFamily);
        toolbar = findViewById(R.id.toolbar);

        cardColony.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PrintOptionActivity.this, ColonyListPrintActivity.class);
                startActivity(i);
            }
        });

        cardFamily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PrintOptionActivity.this, FamilyPrintActivity.class);
                startActivity(i);
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}