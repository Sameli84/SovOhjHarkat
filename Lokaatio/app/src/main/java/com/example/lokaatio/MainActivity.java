package com.example.lokaatio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.provider.Telephony;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements LocationListener {

    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openMaps(View view) {
        Uri intentUri = Uri.parse("google.streetview:cbll=60.1725833,24.9329387");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, intentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    public void setAlarm(View view) {
        Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
        i.putExtra(AlarmClock.EXTRA_MESSAGE, "HALOOOOOOO");
        i.putExtra(AlarmClock.EXTRA_HOUR, 10);
        i.putExtra(AlarmClock.EXTRA_MINUTES, 30);
        startActivity(i);
    }

    public void sendMessage(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        {
            String defaultSmsPackageName = Telephony.Sms.getDefaultSmsPackage(this);

            Uri sms_uri = Uri.parse("smsto:+35840000000");
            Intent sms_intent = new Intent(Intent.ACTION_SENDTO, sms_uri);
            sms_intent.putExtra("sms_body", "Olen Sampsa, kuka sinä olet?");
            startActivity(sms_intent);

            if (defaultSmsPackageName != null)
            { sms_intent.setPackage(defaultSmsPackageName); }
            startActivity(sms_intent);
        }
        else
        {
            Intent smsIntent = new Intent(android.content.Intent.ACTION_VIEW);
            smsIntent.setType("vnd.android-dir/mms-sms");
            smsIntent.putExtra("address","+35840000000");
            smsIntent.putExtra("sms_body","Olen Sampsa, kuka sinä olet?");
            startActivity(smsIntent);
        }

    }

    public void callPhone(View view) {
        if( ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CALL_PHONE}, 0);
            return;
        }

        TextView phoneText = findViewById(R.id.editTextPhone);
        String number = phoneText.getText().toString();

        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + number));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void startPositioning(View view) {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        try {
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                String permissions [] = new String[] {
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION
                };
                ActivityCompat.requestPermissions(this, permissions, 0);
                return;
            }
            // Location currentLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            locationManager.requestLocationUpdates( LocationManager.NETWORK_PROVIDER, 0, 0, this);
            TextView locationTextView = findViewById(R.id.locationText);

            // locationTextView.setText("Lat: " + currentLocation.getLatitude() + "\nLng: " + currentLocation.getLongitude());

            // Log.d( "LAITE_APISOVELLUS", "Lat: " + currentLocation.getLatitude());
        } catch ( SecurityException e) {
            TextView locationTextView = findViewById(R.id.locationText);
            locationTextView.setText("Ei oikeuksia");

            Log.d("LAITE_APISOVELLUS", "Security exception. Ei oikeutta paikkatietoihin");
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        TextView locationTextView = findViewById(R.id.locationText);
        locationTextView.setText("Lat: " + location.getLatitude() + "\nLng: " + location.getLongitude());
    }
}