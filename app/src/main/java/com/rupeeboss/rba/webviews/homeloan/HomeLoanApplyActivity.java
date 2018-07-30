package com.rupeeboss.rba.webviews.homeloan;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.rupeeboss.rba.BaseActivity;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.facade.LoginFacade;
import com.rupeeboss.rba.core.model.LoginEntity;
import com.rupeeboss.rba.core.model.QuoteEntity;

public class HomeLoanApplyActivity extends BaseActivity {

    WebView webView;
    int quoteId;
    String url;
    QuoteEntity quoteEntity;
    LoginEntity loginEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_loan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        webView = (WebView) findViewById(R.id.webView);
        //webView.clearCache(false);
        if (getIntent().getStringExtra("URL") != null) {
            quoteEntity = getIntent().getParcelableExtra("QUOTE_ENTITY");
            quoteId = getIntent().getIntExtra("QUOTE_ID", 0);
            url = getIntent().getStringExtra("URL");
        }
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
        loginEntity = new LoginFacade(this).getUser();
        url = url + "?qoutid=" + quoteId + "&bankid=" + quoteEntity.getBank_Id()
                + "&productid=12"
                + "&refapp=0"
                + "&brokerid=" + loginEntity.getBrokerId()
                + "&empcode=" + loginEntity.getEmpCode()
                + "&loanamout=" + quoteEntity.getLoan_eligible()
                + "&idtype=" + quoteEntity.getRoi_type()
                + "&processingfee=" + quoteEntity.getProcessingfee();
        Log.d("HOME_LOAN_URL", url);
        webView.loadUrl(url);
    }

}
