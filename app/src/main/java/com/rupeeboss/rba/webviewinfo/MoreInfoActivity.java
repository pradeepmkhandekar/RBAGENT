package com.rupeeboss.rba.webviewinfo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.rupeeboss.rba.BaseActivity;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.utility.Constants;

public class MoreInfoActivity extends BaseActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        webView = (WebView) findViewById(R.id.webView);

        if (getIntent().getStringExtra(Constants.WEB_URL) != null) {
            String strWebView = getIntent().getStringExtra(Constants.WEB_URL);

            MessageViaWebView(strWebView);
        }

        webView.setWebViewClient(new WebViewClient() {

                                     @Override
                                     public void onPageStarted(WebView view, String url, Bitmap favicon) {
                                         super.onPageStarted(view, url, favicon);
                                         showProgressDialog();
                                     }

                                     @Override
                                     public void onPageFinished(WebView view, String url) {
                                         super.onPageFinished(view, url);
                                         dismissDialog();
                                     }

                                     @Override
                                     public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                                         super.onReceivedError(view, errorCode, description, failingUrl);
                                         dismissDialog();
                                     }
                                 }


        );

    }


    private void MessageViaWebView(String strURL) {

        try {

            WebSettings settings = webView.getSettings();

            settings.setBuiltInZoomControls(true);
            settings.setUseWideViewPort(false);
            settings.setJavaScriptEnabled(true);
            settings.setSupportMultipleWindows(false);

            settings.setLoadsImagesAutomatically(true);
            settings.setLightTouchEnabled(true);
            settings.setDomStorageEnabled(true);
            settings.setLoadWithOverviewMode(true);
            settings.setJavaScriptEnabled(true);

            settings.setBuiltInZoomControls(true);   //For Zoom ON-OUT

            webView.loadUrl(strURL);


        } catch (Exception ex) {
            ex.printStackTrace();

        }

    }
}
