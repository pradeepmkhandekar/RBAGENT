package com.rupeeboss.rba.personalloan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.rupeeboss.rba.R;
import com.rupeeboss.rba.webviews.homeloan.MyWebViewClient;

public class IIFLWebviewActivity extends AppCompatActivity {

    WebView webView;
    String url,values,title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iiflwebview);


        if (getIntent().getStringExtra("values") != null) {
            values = getIntent().getStringExtra("values");
            title = getIntent().getStringExtra("TITLE");
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(title);
        init_widgets(values);
    }

    private void init_widgets(String values) {
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
        if(values.equals("iifl")) {
            url = "http://www.rupeeboss.com/apply-iifl-loan";
        }else  if(values.equals("kotak"))
        {
            url = "http://www.rupeeboss.com/kotak-personal-loan";
        }else  if(values.equals("rbl"))
        {
            url = "http://www.rupeeboss.com/rbl-personal-loan";
        }else  if(values.equals("early"))
        {
            url = "http://www.rupeeboss.com/early-salary";
        }else  if(values.equals("PaySense"))
        {
            url = "http://www.rupeeboss.com/contact-us";
        }
        else  if(values.equals("iiflpos"))
        {
            url = "http://www.rupeeboss.com/contact-us";
        }
        else  if(values.equals("kotakhl"))
        {
            url = "http://www.rupeeboss.com/kotak-home-loan";
        }
        else  if(values.equals("yeshlLoan"))
        {
            url = "http://www.rupeeboss.com/yesbank-home-loan";
        }
        else
        {
            url = "http://www.rupeeboss.com/contact-us";
        }
        Log.d("URL", url);
        webView.loadUrl(url);
    }

}
