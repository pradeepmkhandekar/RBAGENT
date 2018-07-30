package com.rupeeboss.rba.fragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.rupeeboss.rba.BaseFragment;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.facade.LoginFacade;
import com.rupeeboss.rba.core.model.LoginEntity;
import com.rupeeboss.rba.webviews.homeloan.MyWebViewClient;

/**
 * Created by Rajeev Ranjan on 18/05/2017.
 */

public class PolicyBossWebviewFragment extends BaseFragment {
    WebView webView;
    LoginEntity loginEntity;
    String url;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_policyboss, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("POLICYBOSS");
        ((AppCompatActivity) getActivity()).getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.color.transparent_white));
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
        loginEntity = new LoginFacade(getActivity()).getUser();
        //  url = url + "?qoutid=" + quoteId + "&bankid=" + bankId + "&productid=9" + "&brokerid=" + loginEntity.getBrokerId() + "&empcode=" + loginEntity.getEmpCode();
        url = "http://www.policyboss.com/?utm_source=RBA&utm_medium=CrossSell";
        url = url
                + "&utm_campaign=" + loginEntity.getEmpCode()
                + "&utm_term=" + loginEntity.getBrokerId();

        Log.d("POLICYBOSS_URL", url);
        webView.loadUrl(url);
    }
}
