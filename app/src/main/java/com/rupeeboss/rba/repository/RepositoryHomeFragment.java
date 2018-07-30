package com.rupeeboss.rba.repository;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.rupeeboss.rba.BaseFragment;
import com.rupeeboss.rba.R;

/**
 * Created by Rajeev Ranjan on 27/07/2017.
 */

public class RepositoryHomeFragment extends BaseFragment implements View.OnClickListener {
    LinearLayout llUserManual, llAgreement;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_repository, container, false);
        setHasOptionsMenu(true);
        init(view);
        return view;
    }

    private void init(View view) {
        llAgreement = (LinearLayout) view.findViewById(R.id.llAgreement);
        llUserManual = (LinearLayout) view.findViewById(R.id.llUserManual);
        llAgreement.setOnClickListener(this);
        llUserManual.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llUserManual:
                startActivity(new Intent(getActivity(), RepositoryWebViewActivity.class)
                        .putExtra("URL", "http://rupeeboss.org/HTMLPAGES/rba.pdf")
                        .putExtra("NAME", "RBA_MANUAL")
                        .putExtra("TITLE", "USER MANUAL"));
                break;
            case R.id.llAgreement:
                startActivity(new Intent(getActivity(), RepositoryWebViewActivity.class)
                        .putExtra("URL", "http://49.50.95.141:97/hTMLPAGES/Franchise_Agreement.pdf")
                        .putExtra("NAME", "RBA_AGREEMENT")
                        .putExtra("TITLE", "FRANCHISE AGREEMENT"));
                break;
        }
    }
}
