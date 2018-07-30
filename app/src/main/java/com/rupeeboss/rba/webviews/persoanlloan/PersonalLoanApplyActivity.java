package com.rupeeboss.rba.webviews.persoanlloan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.facade.LoginFacade;
import com.rupeeboss.rba.core.model.LoginEntity;
import com.rupeeboss.rba.core.model.PersonalQuoteEntity;
import com.rupeeboss.rba.webviews.homeloan.MyWebViewClient;


public class PersonalLoanApplyActivity extends AppCompatActivity {

    WebView webView;
    int quoteId;
    PersonalQuoteEntity entity;
    String url;
    LoginEntity loginEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_loan_apply);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        webView = (WebView) findViewById(R.id.webView);

        if (getIntent().getStringExtra("PL_URL") != null) {
            entity = getIntent().getParcelableExtra("PL");
            quoteId = getIntent().getIntExtra("PL_QUOTE_ID", 0);
            url = getIntent().getStringExtra("PL_URL");
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
        //  url = url + "?qoutid=" + quoteId + "&bankid=" + bankId + "&productid=9" + "&brokerid=" + loginEntity.getBrokerId() + "&empcode=" + loginEntity.getEmpCode();

        url = url + "?qoutid=" + quoteId + "&bankid=" + entity.getBank_Id()
                + "&productid=9"
                + "&refapp=0"
                + "&brokerid=" + loginEntity.getBrokerId()
                + "&empcode=" + loginEntity.getEmpCode()
                + "&loanamout=" + entity.getLoan_eligible()
                + "&idtype=" + entity.getRoi_type()
                + "&processingfee=" + entity.getProcessingfee();

        Log.d("PERSONAL_LOAN_URL", url);
        webView.loadUrl(url);
    }

}
