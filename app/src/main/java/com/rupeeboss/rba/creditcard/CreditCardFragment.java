package com.rupeeboss.rba.creditcard;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rupeeboss.rba.BaseFragment;
import com.rupeeboss.rba.R;

/**
 * Created by Rajeev Ranjan on 18/01/2017.
 */

public class CreditCardFragment extends BaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_creditcard, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.color.transparent_white));
        return view;
    }
}
