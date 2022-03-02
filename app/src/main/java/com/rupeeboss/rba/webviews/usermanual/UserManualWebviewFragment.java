package com.rupeeboss.rba.webviews.usermanual;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.rupeeboss.rba.BaseFragment;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.webviews.homeloan.MyWebViewClient;


public class UserManualWebviewFragment extends BaseFragment {
    WebView webView;
    String url;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_policyboss, container, false);
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
        url = "http://erp.rupeeboss.com/rba.pdf";

        Log.d("DOCUMENT_URL", url);
        webView.loadUrl("http://drive.google.com/viewerng/viewer?embedded=true&url=" + url);
    }
}
