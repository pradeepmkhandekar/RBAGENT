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
public class MonthlyFragment extends Fragment {

    TextView txthpintrested,txthpconctact,txthptsvalue,txthpearning;

    TextView txtplintrested,txtplconctact,txtpltsvalue,txtplearning;

    TextView  txccpintrested,txtccconctact,txtcctsvalue,txtccearning;

    TextView txtblintrested,txtblconctact,txtbltsvalue,txtblearning;
    TextView txtacintrested,txtacconctact,txtactsvalue,txtacearning;
    TextView txttotalmonthly;
    private FragmentActivity myContext;

    double MonthlyIncome=0;
    //Monthly
    double mm_hpconctact=0, mm_hpintrested=0, mm_hptsvalue=0, mm_hpearning=0;
    double  mm_plconctact=0, mm_plintrested=0, mm_pltsvalue=0, mm_plearning=0;
    double  mm_ccconctact=0, mm_ccintrested=0, mm_cctsvalue=0, mm_ccearning=0;
    double  mm_blconctact=0, mm_blintrested=0, mm_bltsvalue=0, mm_blearning=0;
    double  mm_acconctact=0, mm_acintrested=0, mm_actsvalue=0, mm_acearning=0;

    DecimalFormat twoDForm = new DecimalFormat("#.##");


    public MonthlyFragment() {
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
        View view = inflater.inflate(R.layout.fragment_monthly, container, false);

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

        txttotalmonthly= (TextView) view.findViewById(R.id.txttotalmonthly);

        int tolal =  ((IncomeSimulatorActivity)getActivity()).getTotal();

        txthpconctact.setText("" + tolal);
        txtplconctact.setText("" + tolal);
        txtccconctact.setText("" + tolal);
        txtblconctact.setText("" + tolal);
        txtacconctact.setText("" + tolal);

        mm_hpconctact= tolal;mm_plconctact= tolal;mm_ccconctact= tolal;mm_blconctact= tolal;mm_acconctact=tolal;


        //MONTHLY
        // Hp  Calculation
        mm_hpintrested = mm_hpconctact * 0.001;
        mm_hptsvalue = mm_hpintrested * 2500000;
        mm_hpearning = mm_hptsvalue * 0.0025;

        //pl
        mm_plintrested = mm_plconctact * 0.001;
        mm_pltsvalue = mm_plintrested * 300000;
        mm_plearning = mm_pltsvalue * 0.0025;


        //cc
        mm_ccintrested = mm_ccconctact * 0.01;
        mm_cctsvalue = mm_ccintrested * 300;
        mm_ccearning = mm_cctsvalue;


        //bl
        mm_blintrested = mm_blconctact * 0.001;
        mm_bltsvalue = mm_blintrested * 300000;
        mm_blearning = mm_bltsvalue * 0.0025;


        //ac SALARU
        mm_acintrested = mm_acconctact * 0.05;
        //  mm_actsvalue = mm_acintrested * 2500000;
        mm_acearning = mm_acintrested * 100;



        txthpintrested.setText("" +BigDecimal.valueOf(Double.valueOf(twoDForm.format(mm_hpintrested))).toPlainString());
        txtplintrested.setText("" +BigDecimal.valueOf(Double.valueOf(twoDForm.format(mm_plintrested))).toPlainString());
        txccpintrested.setText("" + BigDecimal.valueOf(Double.valueOf(twoDForm.format(mm_ccintrested))).toPlainString());
        txtblintrested.setText("" +BigDecimal.valueOf(Double.valueOf(twoDForm.format(mm_blintrested))).toPlainString());
        txtacintrested.setText("" +BigDecimal.valueOf(Double.valueOf(twoDForm.format(mm_acintrested))).toPlainString());




        txthptsvalue.setText("" + "\u20B9" +  BigDecimal.valueOf(Double.valueOf(twoDForm.format(mm_hptsvalue))).toPlainString());
        txtpltsvalue.setText("" + "\u20B9" +  BigDecimal.valueOf(Double.valueOf(twoDForm.format(mm_pltsvalue))).toPlainString());
        txtcctsvalue.setText("" + "\u20B9" +  BigDecimal.valueOf(Double.valueOf(twoDForm.format(mm_cctsvalue))).toPlainString());
        txtbltsvalue.setText("" + "\u20B9" +  BigDecimal.valueOf(Double.valueOf(twoDForm.format(mm_bltsvalue))).toPlainString());


        MonthlyIncome=mm_hpearning + mm_plearning + mm_ccearning + mm_blearning+ mm_acearning;


        txthpearning.setText("" + "\u20B9" +  BigDecimal.valueOf(Double.valueOf(twoDForm.format(mm_hpearning))).toPlainString());
        txtplearning.setText("" + "\u20B9" +  BigDecimal.valueOf(Double.valueOf(twoDForm.format(mm_plearning))).toPlainString());
        txtccearning.setText("" + "\u20B9" +  BigDecimal.valueOf(Double.valueOf(twoDForm.format(mm_ccearning))).toPlainString());
        txtblearning.setText("" + "\u20B9" +  BigDecimal.valueOf(Double.valueOf(twoDForm.format(mm_blearning))).toPlainString());
        txtacearning.setText("" + "\u20B9" +  BigDecimal.valueOf(Double.valueOf(twoDForm.format(mm_acearning))).toPlainString());


      //  textViewloanamount.setText("" +   Math.round(Double.parseDouble(emiEntity.getLoan_eligible())));
        txttotalmonthly.setText("" + "You Can look at earning "+ "\u20B9"+  BigDecimal.valueOf(Double.valueOf(twoDForm.format(MonthlyIncome))).toPlainString() + " Monthly.");

       // txttotalmonthly.setText("" + "You Can look at earning "+ "\u20B9"+ Math.round((MonthlyIncome)) + " Monthly.");
    }

}
