package com.example.vatupassi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void startSensors(View view) {
        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
        for( Sensor s : sensorList ) {
            Toast.makeText(this, s.getName(), Toast.LENGTH_SHORT).show();
        }
        Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if(accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI);
        }
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;
        if (sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float xSensor = event.values[0];
            float ySensor = event.values[1];
            float zSensor = event.values[2];

            TextView sensorTextview = (TextView) findViewById(R.id.textView);
            sensorTextview.setText("X: " + xSensor + "\nY: " + ySensor + "\nZ: " + zSensor);

            TextView sensorTextview3 = (TextView) findViewById(R.id.textView3);
            if (xSensor < 0.1 && xSensor > -0.1) {
                sensorTextview3.setText("Vaaterissa x:n suhteen: Kyllä");
            } else {
                sensorTextview3.setText("Vaaterissa x:n suhteen: Ei");
            }
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}