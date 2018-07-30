package com.rupeeboss.rba.webviews.agreement;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.rupeeboss.rba.R;
import com.rupeeboss.rba.webviews.homeloan.MyWebViewClient;

/**
 * A simple {@link Fragment} subclass.
 */
public class AgreementWebViewFragment extends Fragment {

    WebView webView;
    String url;
    public AgreementWebViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       // return inflater.inflate(R.layout.fragment_agreement_web_view, container, false);
        View view = inflater.inflate(R.layout.fragment_agreement_web_view, container, false);
        webView = (WebView) view.findViewById(R.id.webView);
        settingWebview();
        //   setHasOptionsMenu(true);
        return view;
    }

    private void settingWebview() {
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
        //  url = url + "?qoutid=" + quoteId + "&bankid=" + bankId + "&productid=9" + "&brokerid=" + loginEntity.getBrokerId() + "&empcode=" + loginEntity.getEmpCode();
        url = "http://49.50.95.141:97/hTMLPAGES/Franchise_Agreement.pdf";

        Log.d("DOCUMENT_URL", url);
        webView.loadUrl("http://drive.google.com/viewerng/viewer?embedded=true&url=" + url);
    }
}
