package com.rupeeboss.rba.webviews.generalInsurance;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.facade.LoginFacade;
import com.rupeeboss.rba.core.model.LoginEntity;
import com.rupeeboss.rba.webviews.homeloan.MyWebViewClient;

public class GeneralInsuranceActivity extends AppCompatActivity {
    WebView webView;
    String url;
    int product, brokerid, ssid;
    LoginEntity loginEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_insurance);
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

        loginEntity = new LoginFacade(GeneralInsuranceActivity.this).getUser();
        ssid = loginEntity.getSsid();
        product = getIntent().getIntExtra("product", 0);
        brokerid = getIntent().getIntExtra("brokerid", 0);
        url = "http://www.policyboss.com/home/AgentIndex?ClientID=4&AgentID=" + ssid + "&AgentSource=3";// http://www.policyboss.com/home/AgentIndex?ClientID=4&AgentID=0&AgentSource=0
        /*url = "http://www.policyboss.com/?utm_source=RBA&utm_medium=CrossSell";
        url = url
                + "&utm_campaign=" + loginEntity.getEmpCode()
                + "&utm_term=" + loginEntity.getBrokerId();*/
        Log.d("URL", url);
        webView.loadUrl(url);
    }
}
