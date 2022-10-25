package com.example.coinapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }
    public void backToMainEuros(View view) {
        Intent intent = new Intent( this, MainActivity.class);
        intent.putExtra("CURRENCY", "eur");
        startActivity(intent);
    }
    public void backToMainDollars(View view) {
        Intent intent = new Intent( this, MainActivity.class);
        intent.putExtra("CURRENCY", "usd");
        startActivity(intent);
    }
}