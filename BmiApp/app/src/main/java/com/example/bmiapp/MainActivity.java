package com.example.bmiapp;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Set;

public class MainActivity extends AppCompatActivity {

    String mWeightUnit = "kg";
    float mLatestBmiResult = 0.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //tänne tullaan joko launcherista tai intentistä


        if(savedInstanceState != null) {
            mLatestBmiResult = savedInstanceState.getFloat("LATEST_BMI", 0.0f);
            mWeightUnit = savedInstanceState.getString("WEIGHT_UNIT", "kg");

        }


        if( getIntent().getStringExtra("WEIGHT_UNIT") != null) {
            mWeightUnit = getIntent().getStringExtra("WEIGHT_UNIT");
        }

        TextView weightTextView = findViewById( R.id.weightTextView);
        weightTextView.setText("Paino (" + mWeightUnit + ")");
        TextView bmiTextView = findViewById(R.id.bmiTextView);
        bmiTextView.setText("" + mLatestBmiResult);

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        savedInstanceState.putString("WEIGHT_UNIT", mWeightUnit);
        savedInstanceState.putFloat("LATEST_BMI", mLatestBmiResult);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mLatestBmiResult = savedInstanceState.getFloat("LATEST_BMI", 0.0f);
        mWeightUnit = savedInstanceState.getString("WEIGHT_UNIT", "kg");
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void calculateBMI(View view) {
        TextView bmiTextView = findViewById(R.id.bmiTextView);
        EditText heightText = findViewById(R.id.heightText);
        EditText weightText = findViewById(R.id.weightText);
        float height = Float.parseFloat(heightText.getText().toString());
        float weight = Float.parseFloat(weightText.getText().toString());
        float bmi = weight / (height/100*height/100);
        mLatestBmiResult = bmi;
        bmiTextView.setText(""+bmi);
    }

    public void changeColor(View view) {
        Button colorButton = (Button) view;
        colorButton.setBackgroundColor(Color.YELLOW);
    }

    public void toSettings(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity( intent );
    }
}