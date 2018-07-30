package com.rupeeboss.rba.webviews.creditcard;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.rupeeboss.rba.BaseActivity;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.facade.LoginFacade;
import com.rupeeboss.rba.core.model.LoginEntity;
import com.rupeeboss.rba.core.model.PersonalQuoteEntity;
import com.rupeeboss.rba.webviews.homeloan.MyWebViewClient;

public class CreditCardApplyActivity extends BaseActivity {
    WebView webView;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_card_apply);
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
        url = "http://www.rupeeboss.com/credit-card";
        /*try {

            url = "http://www.rupeeboss.com/credit-card?referrer="
                    + Base64.encodeToString(new LoginFacade(this).getUser().getEmpCode().getBytes("UTF-8"), Base64.DEFAULT)
                    + "@" + Base64.encodeToString(String.valueOf(new LoginFacade(this).getUser().getBrokerId()).getBytes("UTF-8"), Base64.DEFAULT)
                    + "@" + Base64.encodeToString(String.valueOf("app").getBytes("UTF-8"), Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        Log.d("CREDIT_CARD_URL", url);
        webView.loadUrl(url);
    }

}
