package com.example.systemapplotto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private MyChargerBroadcastReceiver myChargerBroadcastReceiver;
    private myLottoBroadcastReceiver myLottoBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myChargerBroadcastReceiver = new MyChargerBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        registerReceiver(myChargerBroadcastReceiver, intentFilter);

        myLottoBroadcastReceiver = new myLottoBroadcastReceiver();
        IntentFilter lottoIntentFilter = new IntentFilter("com.example.mylottoservice.LOTTONUMBER");

        registerReceiver( myLottoBroadcastReceiver, lottoIntentFilter);

    }

    public void startLottoService( View v ) {
        Intent intent = new Intent(this, MyLottoService.class);
        intent.putExtra("LOTTO_NUMBER_AMOUNT", 7);

        startService( intent );
    }

    private class MyChargerBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            TextView laturiTextView = findViewById(R.id.laturiTextView);
            if ( intent.getAction() == Intent.ACTION_POWER_CONNECTED) {
                laturiTextView.setText("Power kytketty");
            } else if ( intent.getAction() == Intent.ACTION_POWER_DISCONNECTED) {
                laturiTextView.setText("Power irroitettu");
            }
        }
    }

    private class myLottoBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction()=="com.example.mylottoservice.LOTTONUMBER") {
                int lottonumero = intent.getIntExtra("LOTTO_NUMBER", 0);
                TextView lottoTextview = findViewById(R.id.lottoTextView);
                lottoTextview.setText(lottoTextview.getText().toString() + lottonumero + " ");
            }
        }
    }
}