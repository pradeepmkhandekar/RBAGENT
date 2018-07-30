package com.rupeeboss.rba.webviews.document;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

import com.rupeeboss.rba.BaseActivity;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.webviews.homeloan.MyWebViewClient;

import java.util.ArrayList;
import java.util.List;

public class DocumentWebViewActivity extends BaseActivity implements View.OnClickListener {
    WebView webView;
    String url;
    Button btnBussLoan, btnCreeditCard, btnHomeLoan, btnPersonalLoan, btnLap, btnAll;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_web_view);
        Window window = this.getWindow();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // clear FLAG_TRANSLUCENT_STATUS flag:
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            // finally change the color
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        webView = (WebView) findViewById(R.id.webView);

        init_widgets();
        setListeners();
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
        url = "http://www.rupeeboss.com/document/Required_Document.html";
        Log.d("DOCUMENT_URL", url);
        webView.loadUrl(url);
    }

    private void setListeners() {
        btnBussLoan.setOnClickListener(this);
        btnCreeditCard.setOnClickListener(this);
        btnHomeLoan.setOnClickListener(this);
        btnPersonalLoan.setOnClickListener(this);
        btnLap.setOnClickListener(this);
        btnAll.setOnClickListener(this);
    }

    private void init_widgets() {
        btnBussLoan = (Button) findViewById(R.id.btnBussLoan);
        btnCreeditCard = (Button) findViewById(R.id.btnCreeditCard);
        btnHomeLoan = (Button) findViewById(R.id.btnHomeLoan);
        btnPersonalLoan = (Button) findViewById(R.id.btnPersonalLoan);
        btnLap = (Button) findViewById(R.id.btnLap);
        btnAll = (Button) findViewById(R.id.btnAll);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.share_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btnMenuShare:
                // About option clicked.
                datashareList("Document Checklist", "Document Checklist", "http://www.rupeeboss.com/document/Required_Document.html");
                break;

            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;

        }
        return true;
    }

    private void datashareList(String Title, String Bodymsg, String link) {


        String Deeplink;
        //"Look! This can make you look gorgeous from Nykaa";
        Deeplink = Bodymsg + "\n" + link;
        String prdSubject = Title;

        String prdDetail = Deeplink;


        try {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_TEXT, prdDetail);

            shareIntent.setType("text/plain");

            PackageManager pm = getPackageManager();


            List<ResolveInfo> resInfo = pm.queryIntentActivities(shareIntent, 0);
            List<LabeledIntent> intentList = new ArrayList<LabeledIntent>();
            ///////////
            for (int i = 0; i < resInfo.size(); i++) {
                // Extract the label, append it, and repackage it in a LabeledIntent
                ResolveInfo ri = resInfo.get(i);
                String packageName = ri.activityInfo.packageName;
                String processName = ri.activityInfo.processName;
                String AppName = ri.activityInfo.name;

                if ((packageName.contains("android.email") || packageName.contains("mms") || packageName.contains("twitter") || (packageName.contains("whatsapp")) || (packageName.contains("facebook.katana")) || (packageName.contains("facebook.orca")) || packageName.contains("messaging") || packageName.contains("android.gm") || packageName.contains("com.google.android.apps.plus")) || (packageName.contains("apps.docs")) && processName.contains("android.apps.docs:Clipboard") || (packageName.contains("android.talk")) && AppName.contains("hangouts")) {

                    shareIntent.setComponent(new ComponentName(packageName, ri.activityInfo.name));

                    if (packageName.contains("android.email")) {

                        shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, prdSubject);
                        shareIntent.setPackage(packageName);

                    } else if (packageName.contains("twitter")) {

                        shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, prdSubject);
                        shareIntent.setPackage(packageName);

                    } else if (packageName.contains("facebook.katana")) {

                        shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, prdSubject);
                        shareIntent.setType("text/plain");
                        shareIntent.putExtra(Intent.EXTRA_TEXT, prdDetail);
                        shareIntent.setPackage("com.facebook.katana");

                    } else if (packageName.contains("facebook.orca")) {

                        shareIntent.setType("text/plain");
                        shareIntent.putExtra(Intent.EXTRA_TEXT, prdDetail);
                        shareIntent.setPackage("com.facebook.orca");

                    } else if (packageName.contains("mms")) {

                        shareIntent.setPackage(packageName);

                    } else if (packageName.contains("whatsapp")) {

                        shareIntent.setType("text/plain");
                        shareIntent.putExtra(Intent.EXTRA_TEXT, prdDetail);
                        shareIntent.setPackage(packageName);


                    } else if (packageName.contains("messaging")) {
                        shareIntent.setPackage(packageName);
                    } else if (packageName.contains("com.google.android.apps.plus")) {
                        shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, prdSubject);
                        shareIntent.setPackage(packageName);

                    }
//                    else if (packageName.contains("android.talk")) {
//                        if (AppName.contains("hangouts")) {
//
//                            shareIntent.setPackage(packageName);
//                        }
//
//                    }
                    else if (packageName.contains("apps.docs")) {
                        if (processName.contains("android.apps.docs:Clipboard")) {

                            shareIntent.setPackage(packageName);
                        }

                    } else if (packageName.contains("android.gm")) {
                        shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, prdSubject);
                        shareIntent.setPackage(packageName);

                    }

                    intentList.add(new LabeledIntent(shareIntent, packageName, ri.loadLabel(pm), ri.icon));

                }
            }


            if (intentList.size() > 1) {
                intentList.remove(intentList.size() - 1);
            }

            Intent openInChooser = Intent.createChooser(shareIntent, "Share Via");

            // convert intentList to array
            LabeledIntent[] extraIntents = intentList.toArray(new LabeledIntent[intentList.size()]);
            openInChooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, extraIntents);

            startActivity(openInChooser);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBussLoan:
                datashareList("Business Loan", "Required Documents- Business Loan", "http://www.rupeeboss.com/document/business-loan.html");
                webView.loadUrl("http://www.rupeeboss.com/document/business-loan.html");
                break;
            case R.id.btnCreeditCard:
                datashareList("Credit Card", "Required Documents- Credit Card", "http://www.rupeeboss.com/document/credit-card.html");
                webView.loadUrl("http://www.rupeeboss.com/document/credit-card.html");
                break;
            case R.id.btnHomeLoan:
                datashareList("Home Loan", "Required Documents- Home Loan", "http://www.rupeeboss.com/document/home-loan.html");
                webView.loadUrl("http://www.rupeeboss.com/document/home-loan.html");
                break;
            case R.id.btnPersonalLoan:
                datashareList("Personal Loan", "Required Documents- Personal Loan", "http://www.rupeeboss.com/document/personal-loan.html");
                webView.loadUrl("http://www.rupeeboss.com/document/personal-loan.html");
                break;
            case R.id.btnLap:
                datashareList("Loan Against Property", "Required Documents- Loan Against Property", "http://www.rupeeboss.com/document/lap.html");
                webView.loadUrl("http://www.rupeeboss.com/document/lap.html");
                break;
            case R.id.btnAll:
                datashareList(" Loan", "Required Documents-  Loan", "http://www.rupeeboss.com/document/Required_Document.html");
                webView.loadUrl("http://www.rupeeboss.com/document/Required_Document.html");
                break;

        }
    }
}
