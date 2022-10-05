package com.example.coinapp;
import androidx.appcompat.app.AppCompatActivity;
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
    private String mUrl = "https://api.coingecko.com/api/v3/coins/";
    private RequestQueue mQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mQueue = Volley.newRequestQueue(this);
    }

    public void fetchCoinData(View view) {
        TextView textView = (TextView) findViewById(R.id.cryptoUrl);
        String cryptoUrl = textView.getText().toString();
        String mCryptoUrl = mUrl+cryptoUrl;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, mCryptoUrl, null,
                response -> {
                    System.out.println("Here");
                    Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
                    parseJsonAndUpdateUi( response );
                }, error -> error.printStackTrace()) ;
        mQueue.add(jsonObjectRequest);
        System.out.println(mQueue.toString());
    }

    private void parseJsonAndUpdateUi(JSONObject coinObject) {
        TextView coinTextView = (TextView) findViewById(R.id.coinTextView);
        TextView priceTextView = (TextView) findViewById(R.id.coinPrice);
        try {
            String coin = coinObject.getString("name");
            String price = coinObject.getJSONObject("market_data").getJSONObject("current_price").getString("usd");
            System.out.println(price);
            coinTextView.setText(coin);
            priceTextView.setText(price + " $");

            System.out.println("täällä");
        } catch (JSONException e) {
            e.printStackTrace();
            System.out.println("Virhe");

        }
    }
}
















