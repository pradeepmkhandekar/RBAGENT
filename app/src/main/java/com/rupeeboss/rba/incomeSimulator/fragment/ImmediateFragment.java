package com.rupeeboss.rba.incomeSimulator.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rupeeboss.rba.R;
import com.rupeeboss.rba.incomeSimulator.IncomeSimulatorActivity;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImmediateFragment extends Fragment {

    TextView txthpintrested,txthpconctact,txthptsvalue,txthpearning;

    TextView txtplintrested,txtplconctact,txtpltsvalue,txtplearning;

    TextView  txccpintrested,txtccconctact,txtcctsvalue,txtccearning;

    TextView txtblintrested,txtblconctact,txtbltsvalue,txtblearning;
    TextView txtacintrested,txtacconctact,txtactsvalue,txtacearning;
    TextView txttotalweeklyvalue;
    DecimalFormat twoDForm = new DecimalFormat("#.##");

    private FragmentActivity myContext;
    double hpconctact=0,hpintrested=0,hptsvalue=0,hpearning=0;
    double plconctact=0,plintrested=0,pltsvalue=0,plearning=0;
    double ccconctact=0,ccintrested=0,cctsvalue=0,ccearning=0;
    double blconctact=0,blintrested=0,bltsvalue=0,blearning=0;
    double acconctact=0,acintrested=0,actsvalue=0,acearning=0;

    double ImmediateIncome=0;




    public ImmediateFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        myContext=(FragmentActivity) context;
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_immediate, container, false);
        initilize(view);
        return view;
    }

    private void initilize(View view) {

        txthpintrested = (TextView) view.findViewById(R.id.txthpintrested);
        txtplintrested = (TextView) view.findViewById(R.id.txtplintrested);
        txccpintrested = (TextView) view.findViewById(R.id.txccpintrested);
        txtblintrested = (TextView) view.findViewById(R.id.txtblintrested);
        txtacintrested = (TextView) view.findViewById(R.id.txtacintrested);

        txthpconctact= (TextView) view.findViewById(R.id.txthpconctact);
        txtplconctact = (TextView) view.findViewById(R.id.txtplconctact);
        txtccconctact = (TextView) view.findViewById(R.id.txtccconctact);
        txtblconctact = (TextView) view.findViewById(R.id.txtblconctact);
        txtacconctact = (TextView) view.findViewById(R.id.txtacconctact);

        txthptsvalue= (TextView) view.findViewById(R.id.txthptsvalue);
        txtpltsvalue = (TextView) view.findViewById(R.id.txtpltsvalue);
        txtcctsvalue = (TextView) view.findViewById(R.id.txtcctsvalue);
        txtbltsvalue = (TextView) view.findViewById(R.id.txtbltsvalue);
     //   txtactsvalue = (TextView) view.findViewById(R.id.txtactsvalue);

        txthpearning= (TextView) view.findViewById(R.id.txthpearning);
        txtplearning = (TextView) view.findViewById(R.id.txtplearning);
        txtccearning = (TextView) view.findViewById(R.id.txtccearning);
        txtblearning = (TextView) view.findViewById(R.id.txtblearning);
        txtacearning = (TextView) view.findViewById(R.id.txtacearning);

        txttotalweeklyvalue= (TextView) view.findViewById(R.id.txttotalweekly);

       int tolal =  ((IncomeSimulatorActivity)getActivity()).getTotal();

        txthpconctact.setText("" + tolal);
        txtplconctact.setText("" + tolal);
        txtccconctact.setText("" + tolal);
        txtblconctact.setText("" + tolal);
        txtacconctact.setText("" + tolal);

        hpconctact= tolal;plconctact= tolal;ccconctact= tolal;blconctact= tolal;acconctact=tolal;

        //WEEKLY
        // Hp  Calculation
        hpintrested = hpconctact * 0.0025;
        hptsvalue = hpintrested * 2500000;
        hpearning = hptsvalue * 0.0025;

        //pl
        plintrested = plconctact * 0.0075;
        pltsvalue = plintrested * 300000;
        plearning = pltsvalue * 0.0025;


        //cc
        ccintrested = ccconctact * 0.01;
        cctsvalue = ccintrested * 300;
        ccearning = cctsvalue;


        //bl
        blintrested = blconctact * 0.0025;
        bltsvalue = blintrested * 300000;
        blearning = bltsvalue * 0.0025;


        //ac SALARU
        acintrested = acconctact * 0.05;
      //  actsvalue = acintrested * 2500000;
        acearning = acintrested * 100;

        txthpintrested.setText("" +BigDecimal.valueOf(Double.valueOf(twoDForm.format(hpintrested))).toPlainString());
        txtplintrested.setText("" +BigDecimal.valueOf(Double.valueOf(twoDForm.format(plintrested))).toPlainString());
        txccpintrested.setText("" + BigDecimal.valueOf(Double.valueOf(twoDForm.format(ccintrested))).toPlainString());
        txtblintrested.setText("" +BigDecimal.valueOf(Double.valueOf(twoDForm.format(blintrested))).toPlainString());
        txtacintrested.setText("" +BigDecimal.valueOf(Double.valueOf(twoDForm.format(acintrested))).toPlainString());


        txthptsvalue.setText("" + "\u20B9" + BigDecimal.valueOf(Double.valueOf(twoDForm.format(hptsvalue))).toPlainString());
        txtpltsvalue.setText("" + "\u20B9" + BigDecimal.valueOf(Double.valueOf(twoDForm.format(pltsvalue))).toPlainString());
        txtcctsvalue.setText("" + "\u20B9" + BigDecimal.valueOf(Double.valueOf(twoDForm.format(cctsvalue))).toPlainString());
        txtbltsvalue.setText("" + "\u20B9" + BigDecimal.valueOf(Double.valueOf(twoDForm.format(bltsvalue))).toPlainString());



        ImmediateIncome=hpearning + plearning + ccearning + blearning+ acearning;



        txthpearning.setText("" + "\u20B9" + BigDecimal.valueOf(Double.valueOf(twoDForm.format(hpearning))).toPlainString());
        txtplearning.setText("" + "\u20B9" + BigDecimal.valueOf(Double.valueOf(twoDForm.format(plearning))).toPlainString());
        txtccearning.setText("" + "\u20B9" + BigDecimal.valueOf(Double.valueOf(twoDForm.format(ccearning))).toPlainString());
        txtblearning.setText("" + "\u20B9" + BigDecimal.valueOf(Double.valueOf(twoDForm.format(blearning))).toPlainString());
        txtacearning.setText("" + "\u20B9" + BigDecimal.valueOf(Double.valueOf(twoDForm.format(acearning))).toPlainString());


        txttotalweeklyvalue.setText("" + "You Can look at earning "+ "\u20B9"+BigDecimal.valueOf(Double.valueOf(twoDForm.format(ImmediateIncome))).toPlainString() + " Immediately.");
    }




}
