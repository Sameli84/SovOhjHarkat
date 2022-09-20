package com.example.bmiapp;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void calculateBMI(View view) {
        TextView bmiTextView = findViewById(R.id.bmiTextView);
        EditText heightText = findViewById(R.id.heightText);
        EditText weightText = findViewById(R.id.weightText);
        float height = Float.parseFloat(heightText.getText().toString());
        float weight = Float.parseFloat(weightText.getText().toString());
        float bmi = weight / (height/100*height/100);
        bmiTextView.setText(""+bmi);
    }

    public void changeColor(View view) {
        Button colorButton = (Button) view;
        colorButton.setBackgroundColor(Color.YELLOW);
    }
}