package com.rupeeboss.rba.loan_fm.new_HomeLoan;

import android.content.Intent;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;


import com.rupeeboss.rba.BaseActivity;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core_loan_fm.APIResponseERP;
import com.rupeeboss.rba.core_loan_fm.IResponseSubcriberERP;
import com.rupeeboss.rba.core_loan_fm.controller.erploan.ErpLoanController;
import com.rupeeboss.rba.core_loan_fm.model.LstCitywiseBankLoanEntity;
import com.rupeeboss.rba.core_loan_fm.response.citywisebankloanResponse;
import com.rupeeboss.rba.loan_fm.Utility;
import com.rupeeboss.rba.webviews.commonwebview.CommonWebviewActivity;


import java.util.ArrayList;
import java.util.List;


public class bank_selection_homeloanActivity extends BaseActivity implements View.OnClickListener, TextWatcher, SeekBar.OnSeekBarChangeListener, IResponseSubcriberERP {
    RecyclerView rvQuotes;
    bank_display_homeloan_Adapter mAdapter;
		    List<LstCitywiseBankLoanEntity> quoteEntities;

    citywisebankloanResponse getpersonal_bank_list_response;
    Toolbar toolbar;
    String Cityid="";
    //DBPersistanceController dbPersistanceController;
   // LoginResponseEntity loginResponseEntity;
    LinearLayout llmessage;
    Button btnBack;
    EditText etMonthlyInc,etTenureInYear;
    SeekBar  sbMonthlyInc;

    LinearLayout llSalaried;
    int seekBarApplIncomeProgress = 1;
    int tenureyears=20;
    int loanamount= 2500000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_selection_homeloan);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        quoteEntities = new ArrayList<>();

        llmessage = (LinearLayout)findViewById(R.id.llmessage) ;
        llmessage.setVisibility(View.GONE);
        etMonthlyInc = (EditText) findViewById(R.id.etMonthlyInc);
        etMonthlyInc.addTextChangedListener(this);
        btnBack= (Button) findViewById(R.id.btnBack) ;
        etTenureInYear= (EditText) findViewById(R.id.etTenureInYear) ;
        etTenureInYear.addTextChangedListener(this);
        sbMonthlyInc = (SeekBar) findViewById(R.id.sbMonthlyInc);
        sbMonthlyInc.setMax(30);
        sbMonthlyInc.setProgress(20);
        etMonthlyInc.setText("2500000");
        sbMonthlyInc.setOnSeekBarChangeListener(this);

        etTenureInYear.setText("20");
        rvQuotes = (RecyclerView)findViewById(R.id.rvQuotes);
        rvQuotes.setLayoutManager(new LinearLayoutManager(bank_selection_homeloanActivity.this));
//getBankdetail_personalloan
        Cityid= getIntent().getStringExtra("city_id");
  //      dbPersistanceController = new DBPersistanceController(bank_selection_homeloanActivity.this);
  //      loginResponseEntity = dbPersistanceController.getUserData();

        setListener();
        List<LstCitywiseBankLoanEntity> lst = new ArrayList<>();
        mAdapter = new bank_display_homeloan_Adapter(bank_selection_homeloanActivity.this, lst);
        rvQuotes.setAdapter(mAdapter);
        showDialog();
       new ErpLoanController(bank_selection_homeloanActivity.this).getCitywiseBankListloan(Cityid,"12",bank_selection_homeloanActivity.this);
    }

	     private void setListener() {
        btnBack.setOnClickListener(this);



    }
    @Override
    public void onClick(View view) {
       // Constants.hideKeyBoard(view, this);
        switch (view.getId()) {
            case R.id.btnBack:
                startActivity(new Intent(bank_selection_homeloanActivity.this, city_selecton_homeloan_Activity.class));
                break;


//
//            case R.id.txtPendingDays3:
//                managePL_Common("three",  txtPendingDays3,txtPendingDays1, txtPendingDays2);
//                tenureyears=3;
//                updateCalculatedList(tenureyears,loanamount);
//                break;


        }
    }

    public void updateCalculatedList(int termInYears,int loanAmount)
    {
       // int loanAmount = Integer.parseInt(etMonthlyInc.getText().toString());
      //  int termInYears=Integer.parseInt(etyear.getText().toString());
       //Double.parseDouble(etrate.getText().toString());

        for(LstCitywiseBankLoanEntity entity : quoteEntities){
            try {
                String roi = entity.getLstCityProdBank().get(0).getBest_ROI().toString().split("%")[0];
                double interestRate = Double.parseDouble(roi);


                double monthlyPayment =
                        calculateMonthlyPayment(loanAmount, termInYears, interestRate);

                String bestemi = String.valueOf("" + "\u20B9" +   String.valueOf(Math.round(monthlyPayment)));

                entity.getLstCityProdBank().get(0).setPer_Lac_EMI_outside(bestemi);
            }catch (Exception ex)
            {
                ex.printStackTrace();
            }
            }
        if(quoteEntities.size()>0) {
            getupdatelist(quoteEntities,false);
            mAdapter.updateAdapter(quoteEntities);
        }
    }


    public void updateCalculatedList_cash(int loanAmount)
    {
        // int loanAmount = Integer.parseInt(etMonthlyInc.getText().toString());
        //Double termInYears = 0.5;
        //Double.parseDouble(etrate.getText().toString());

        for(LstCitywiseBankLoanEntity entity : quoteEntities){
            try {
                String roi = entity.getLstCityProdBank().get(0).getBest_ROI().toString().split("%")[0];
                double interestRate = Double.parseDouble(roi);



                interestRate /= 100.0;


                double monthlyRate = interestRate / 12.0;


                int termInMonths = 6;



                double monthlyPayment =
                        (loanAmount*monthlyRate) /
                                (1-Math.pow(1+monthlyRate, -termInMonths));



                String bestemi = String.valueOf("" + "\u20B9" + String.format("%.2f", monthlyPayment));

                entity.getLstCityProdBank().get(0).setPer_Lac_EMI_outside(bestemi);
            }catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
        if(quoteEntities.size()>0) {
            getupdatelist(quoteEntities,true);
            mAdapter.updateAdapter(quoteEntities);
        }
    }

    public void getupdatelist( List<LstCitywiseBankLoanEntity> quoteEntities, Boolean blnChk)
    {
        // List<LstCityBankdetailEntity> lstCityProdBank;
        if(blnChk) {
            for (LstCitywiseBankLoanEntity citywiseBankLoanEntity : quoteEntities) {
                        citywiseBankLoanEntity.setIs_Cash(true);

            }
        }
        else
        {
            for(LstCitywiseBankLoanEntity citywiseBankLoanEntity : quoteEntities) {
                citywiseBankLoanEntity.setIs_Cash(false);
            }
        }

    }
    public  double calculateMonthlyPayment(
            int loanAmount, int termInYears, double interestRate) {

        // Convert interest rate into a decimal
        // eg. 6.5% = 0.065

        interestRate /= 100.0;

        // Monthly interest rate
        // is the yearly rate divided by 12

        double monthlyRate = interestRate / 12.0;

        // The length of the term in months
        // is the number of years times 12

        int termInMonths = termInYears * 12;

        // Calculate the monthly payment
        // Typically this formula is provided so
        // we won't go into the details

        // The Math.pow() method is used calculate values raised to a power

        double monthlyPayment =
                (loanAmount*monthlyRate) /
                        (1-Math.pow(1+monthlyRate, -termInMonths));

        return monthlyPayment;
    }

  public void redirectToApplyBankHL(LstCitywiseBankLoanEntity entity) {
        String url="";
        String Bankname="";
        Bankname = entity.getBank_Name();
        url= entity.getBank_Form_URL() + "?BrokerId=" + "1978"+"&FBAId=" +  "1978" + "&client_source=finmart&lead_id=";


//        if (entity.getBank_Id().equals("33")) {
//            Bankname = "KOTAK MAHINDRA BANK";
//            //   url="https://www.rupeeboss.com/kotakmahindra-pl?BrokerId=" + loginResponseEntity.getLoanId()+"&FBAId=" + loginResponseEntity.getFBAId() + "&client_source=finmart&lead_id=";
//
//        } else if (entity.getBank_Id().equals("43")) {
//            Bankname = "RBL BANK";
//            //  url="https://www.rupeeboss.com/rbl-pl?BrokerId=" + loginResponseEntity.getLoanId()+"&FBAId=" + loginResponseEntity.getFBAId() + "&client_source=finmart&lead_id=";
//
//        } else if (entity.getBank_Id().equals("51")) {
//            Bankname = "TATA CAPITAL";
//            // url="https://www.rupeeboss.com/tatacapital-pl?BrokerId=" + loginResponseEntity.getLoanId()+"&FBAId=" + loginResponseEntity.getFBAId() + "&client_source=finmart&lead_id=";
//
//        } else if (entity.getBank_Id().equals("53")) {
//            Bankname = "YES BANK";
//            //  String url1 = "https://yesbankbot.buildquickbots.com/chat/rupeeboss/staff/?userid=" + loginResponseEntity.getFBAId()+ "&usertype=finmart&vkey=b34f02e9-8f1c";
//
//            // Utility.loadWebViewUrlInBrowser(bank_selection_businessloanActivity.this,url1);
//        } else if (entity.getBank_Id().equals("20")) {
//            Bankname = "HDFC BANK";
//            url = "https://www.rupeeboss.com/hdfc-bl?BrokerId=" + loginResponseEntity.getLoanId() + "&FBAId=" + loginResponseEntity.getFBAId() + "&client_source=finmart&lead_id=";
//        } else if (entity.getBank_Id().equals("2152")) {
//            Bankname = "CASHE";
//            //   url="https://www.rupeeboss.com/cashe-new?BrokerId=" + loginResponseEntity.getLoanId()+"&FBAId=" + loginResponseEntity.getFBAId() + "&client_source=finmart&lead_id=";
//        }


            startActivity(new Intent(this, CommonWebviewActivity.class)
                    .putExtra("URL", url)
                    .putExtra("NAME", "" + Bankname)
                    .putExtra("TITLE", "" + Bankname));

        //  setFmBankRequest(entity);
    }

    @Override
    public void OnSuccessERP(APIResponseERP response, String message) {
        cancelDialog();
        if (response instanceof citywisebankloanResponse) {

            getpersonal_bank_list_response = ((citywisebankloanResponse) response);
            if (getpersonal_bank_list_response != null) {
                if(getpersonal_bank_list_response.getResult().size() > 0) {
                    llmessage.setVisibility(View.GONE);
                    quoteEntities = getpersonal_bank_list_response.getResult();
                    getupdatelist(quoteEntities,false);
                    updateCalculatedList(tenureyears,loanamount);
//                    mAdapter = new bank_display_homeloan_Adapter(bank_selection_homeloanActivity.this, getpersonal_bank_list_response.getResult());
//                    rvQuotes.setAdapter(mAdapter);
                }else {
                  //  Toast.makeText(this, "Data Not Found", Toast.LENGTH_LONG).show();
                    llmessage.setVisibility(View.VISIBLE);
                }
            }else
            {
                Toast.makeText(this, getpersonal_bank_list_response.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
        Toast.makeText(this, getpersonal_bank_list_response.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (etMonthlyInc.getText().hashCode() == s.hashCode()) {

            if (!etMonthlyInc.getText().toString().equals("") && !etMonthlyInc.getText().toString().equals(null)) {
             // int monthlyInc = Integer.parseInt(etMonthlyInc.getText().toString());

              etMonthlyInc.setSelection(etMonthlyInc.getText().length());

               // tenureyears = Integer.parseInt(etTenureInYear.getText().toString());
                loanamount = Integer.parseInt(etMonthlyInc.getText().toString());
                updateCalculatedList(tenureyears,loanamount);
            }

        }
        if (etTenureInYear.getText().hashCode() == s.hashCode()) {
            if (!etTenureInYear.getText().toString().equals("") && !etTenureInYear.getText().toString().equals(null)) {
                 int TenurelyInc = Integer.parseInt(etTenureInYear.getText().toString());
                if (TenurelyInc > 1) {
                    sbMonthlyInc.setProgress(TenurelyInc);
                }
                else
                {
                    sbMonthlyInc.setProgress(1);
                    etTenureInYear.setSelection(etTenureInYear.getText().length());
                }
              

                tenureyears = Integer.parseInt(etTenureInYear.getText().toString());
                updateCalculatedList(tenureyears,loanamount);
            }

        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        String progr="";
        if(fromUser) {
            switch (seekBar.getId()) {

                case R.id.sbMonthlyInc:
                    if (progress >= seekBarApplIncomeProgress) {
                        if (fromUser) {
                            progr =  String.valueOf(progress);
                            tenureyears = progress;
                            etTenureInYear.setText(progr);
                        }
                    } else {
                        progr= String.valueOf((long) seekBarApplIncomeProgress);
                        tenureyears= seekBarApplIncomeProgress;
                        etTenureInYear.setText(progr);
                    }

                    break;

            }
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
