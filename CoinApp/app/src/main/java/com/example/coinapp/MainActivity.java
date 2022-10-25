package com.example.coinapp;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    String mCurrency = "usd";
    String mLatestPrice = "";
    String mLatestName = "TBD";
    private String mUrl = "https://api.coingecko.com/api/v3/coins/";
    private RequestQueue mQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if( getIntent().getStringExtra("CURRENCY") != null) {
            mCurrency = getIntent().getStringExtra("CURRENCY");
            System.out.println("Hello from intent");
        }
        mQueue = Volley.newRequestQueue(this);
        if(savedInstanceState != null) {
            mLatestPrice = savedInstanceState.getString("LATEST_PRICE");
            mLatestName = savedInstanceState.getString("LATEST_NAME");
            System.out.println("Hello from saved");
        }
        TextView coinTextView = findViewById( R.id.coinTextView);
        coinTextView.setText(mLatestName);
        TextView coinPrice = findViewById(R.id.coinPrice);
        coinPrice.setText(mLatestPrice + " " + mCurrency);
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString("LATEST_PRICE", mLatestPrice);
        savedInstanceState.putString("LATEST_NAME", mLatestName);
        super.onSaveInstanceState(savedInstanceState);
    }

    public void fetchCoinData(View view) {
        TextView textView = findViewById(R.id.cryptoUrl);
        String cryptoUrl = textView.getText().toString();
        String mCryptoUrl = mUrl+cryptoUrl;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                mCryptoUrl,
                null,
                response -> {
                    // Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
                    parseJsonAndUpdateUi( response );
                },
                error -> {
                    error.printStackTrace();
                    TextView errorTextView = findViewById(R.id.errorTextView);
                    errorTextView.setText(getResources().getString(R.string.error));
                });
        mQueue.add(jsonObjectRequest);
        System.out.println(mQueue.toString());
    }

    private void parseJsonAndUpdateUi(JSONObject coinObject) {
        TextView coinTextView = findViewById(R.id.coinTextView);
        TextView priceTextView = findViewById(R.id.coinPrice);
        try {
            String coin = coinObject.getString("name");
            String price = coinObject.getJSONObject("market_data").
                    getJSONObject("current_price").getString(mCurrency);
            System.out.println(price);
            coinTextView.setText(coin);
            priceTextView.setText(price + " " + mCurrency);
            mLatestPrice = price;
            mLatestName = coin;
            TextView errorTextView = findViewById(R.id.errorTextView);
            errorTextView.setText("");
        } catch (JSONException e) {
            e.printStackTrace();
            System.out.println("Virhe");
            TextView errorTextView = (TextView) findViewById(R.id.errorTextView);
            errorTextView.setText(getResources().getString(R.string.error));
        }
    }

    public void toSettings(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity( intent );
    }
}
















