package com.example.bmiapp;

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

    public void backToMain(View view) {
        Intent intent = new Intent( this, MainActivity.class);
        intent.putExtra("WEIGHT_UNIT", "lbs");
        startActivity(intent);

    //    Intent intent2 = new Intent (Intent.ACTION_VIEW);
    //    intent2.setData(Uri.parse("https://google.com"));
    //    startActivity(intent2);
    }
}