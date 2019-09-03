package com.rupeeboss.rba.webviews.CarLoan;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.rupeeboss.rba.R;
import com.rupeeboss.rba.webviews.homeloan.MyWebViewClient;

public class CarLoanActivity extends AppCompatActivity {
    WebView webView;
    String url;
    int product,brokerid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_loan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        webView = (WebView) findViewById(R.id.webView);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);

        settings.setBuiltInZoomControls(true);
        settings.setUseWideViewPort(false);
        settings.setJavaScriptEnabled(true);
        settings.setSupportMultipleWindows(false);

        settings.setLoadsImagesAutomatically(true);
        settings.setLightTouchEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setJavaScriptEnabled(true);

        MyWebViewClient webViewClient = new MyWebViewClient();
        webView.setWebViewClient(webViewClient);
        webView.getSettings().setBuiltInZoomControls(true);
        product = getIntent().getIntExtra("product",0);
        brokerid =getIntent().getIntExtra("brokerid",0);
        url = "http://www.rupeeboss.com/car-loan";
        Log.d("CAR_LOAN_URL", url);
        webView.loadUrl(url);
    }

}
