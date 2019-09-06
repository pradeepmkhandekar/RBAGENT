
package com.rupeeboss.rba.loan_fm.businessloan;

import android.content.Intent;
import android.os.Bundle;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


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


public class bank_selection_businessloanActivity extends BaseActivity implements View.OnClickListener, TextWatcher, IResponseSubcriberERP {
    RecyclerView rvQuotes;
    bank_display_businessloan_Adapter mAdapter;
		    List<LstCitywiseBankLoanEntity> quoteEntities;

    citywisebankloanResponse getpersonal_bank_list_response;
    Toolbar toolbar;
    String Cityid = "";
   // DBPersistanceController dbPersistanceController;
  // LoginResponseEntity loginResponseEntity;
    LinearLayout llmessage;
    Button btnBack;
    EditText etMonthlyInc;

    LinearLayout llSalaried;
    int seekBarApplIncomeProgress = 1;
    int tenureyears=3;
    int loanamount= 500000;
    TextView txtPendingDays1,txtPendingDays2,txtPendingDays3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_selection_businessloan);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        quoteEntities = new ArrayList<>();

        llmessage = (LinearLayout) findViewById(R.id.llmessage);
        llmessage.setVisibility(View.GONE);
        etMonthlyInc = (EditText) findViewById(R.id.etMonthlyInc);
        etMonthlyInc.addTextChangedListener(this);
        etMonthlyInc.setText("500000");

        txtPendingDays1= (TextView) findViewById(R.id.txtPendingDays1) ;
        txtPendingDays2= (TextView) findViewById(R.id.txtPendingDays2) ;
        txtPendingDays3= (TextView) findViewById(R.id.txtPendingDays3) ;


        txtPendingDays3.setBackgroundResource(R.drawable.circular_select);
        txtPendingDays3.setTextColor(ContextCompat.getColor(bank_selection_businessloanActivity.this, R.color.white));
        btnBack = (Button) findViewById(R.id.btnBack);



        rvQuotes = (RecyclerView) findViewById(R.id.rvQuotes);
        rvQuotes.setLayoutManager(new LinearLayoutManager(bank_selection_businessloanActivity.this));
        //getBankdetail_personalloan
        Cityid = getIntent().getStringExtra("city_id");
     //   dbPersistanceController = new DBPersistanceController(bank_selection_businessloanActivity.this);
    //    loginResponseEntity = dbPersistanceController.getUserData();

        setListener();
        List<LstCitywiseBankLoanEntity> lst = new ArrayList<>();
        mAdapter = new bank_display_businessloan_Adapter(bank_selection_businessloanActivity.this,lst);
        rvQuotes.setAdapter(mAdapter);

        showDialog();
        new ErpLoanController(bank_selection_businessloanActivity.this).getCitywiseBankListloan(Cityid, "13", bank_selection_businessloanActivity.this);
    }

    private void setListener() {
        btnBack.setOnClickListener(this);

        txtPendingDays1.setOnClickListener(this);
        txtPendingDays2.setOnClickListener(this);
        txtPendingDays3.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
       // Constants.hideKeyBoard(view, this);
        switch (view.getId()) {
            case R.id.btnBack:
                startActivity(new Intent(bank_selection_businessloanActivity.this, city_selecton_businessloan_Activity.class));
                break;
//            case R.id.txtPendingDayshalf:
//                managePL_Common("half",  txtPendingDays1, txtPendingDays2, txtPendingDays3);
//                updateCalculatedList_cash(loanamount);
//                break;
            case R.id.txtPendingDays1:
                managePL_Common("one", txtPendingDays1,  txtPendingDays2, txtPendingDays3);
                tenureyears=1;
                updateCalculatedList(tenureyears,loanamount);
                break;
            case R.id.txtPendingDays2:
                managePL_Common("two", txtPendingDays2,  txtPendingDays1, txtPendingDays3);
                tenureyears=2;
                updateCalculatedList(tenureyears,loanamount);
                break;
            case R.id.txtPendingDays3:
                managePL_Common("three",  txtPendingDays3,txtPendingDays1, txtPendingDays2);
                tenureyears=3;
                updateCalculatedList(tenureyears,loanamount);
                break;
//            case R.id.txtPendingDays4:
//                managePL_Common("four", txtPendingDays4,txtPendingDayshalf, txtPendingDays1, txtPendingDays2, txtPendingDays3, txtPendingDays5);
//                tenureyears=4;
//                updateCalculatedList(tenureyears,loanamount);
//                break;
//            case R.id.txtPendingDays5:
//                managePL_Common("five", txtPendingDays5,txtPendingDayshalf, txtPendingDays1, txtPendingDays2, txtPendingDays3, txtPendingDays4);
//                tenureyears=5;
//                updateCalculatedList(tenureyears,loanamount);
//                break;
            //endregion

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

    private void managePL_Common(String Value, TextView clickedText, TextView textView1, TextView textView2) {

//
//        if (Type == 1) {
//            PL_STATUS = Value;
//        } else if (Type == 2) {
//            PL_CATEGORY = Value;
//        }
//        if (Type == 3) {
//            PL_EDUCATION = Value;
//        }
        clickedText.setBackgroundResource(R.drawable.circular_select);
        clickedText.setTextColor(ContextCompat.getColor(bank_selection_businessloanActivity.this, R.color.white));

        textView1.setBackgroundResource(R.drawable.circular_shape);
        textView1.setTextColor(ContextCompat.getColor(bank_selection_businessloanActivity.this, R.color.black));

        textView2.setBackgroundResource(R.drawable.circular_shape);
        textView2.setTextColor(ContextCompat.getColor(bank_selection_businessloanActivity.this, R.color.black));

        //textView3.setBackgroundResource(R.drawable.circular_shape);
        //textView3.setTextColor(ContextCompat.getColor(bank_selection_businessloanActivity.this, R.color.black));

//        textView4.setBackgroundResource(R.drawable.circular_shape);
//        textView4.setTextColor(ContextCompat.getColor(bank_selection_businessloanActivity.this, R.color.black));


//        textView5.setBackgroundResource(R.drawable.circular_shape);
//        textView5.setTextColor(ContextCompat.getColor(bank_selection_businessloanActivity.this, R.color.black));

    }


    public void redirectToApplyBankBL(LstCitywiseBankLoanEntity entity) {
        String url = "";
        String Bankname = "";
        Bankname = entity.getBank_Name();
        url= entity.getBank_Form_URL() + "?BrokerId=" + "1978"+"&FBAId=" + "1978" + "&client_source=finmart&lead_id=";


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


        startActivity(new Intent(this, CommonWebviewActivity .class)
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
                if (getpersonal_bank_list_response.getResult().size() > 0) {
                    llmessage.setVisibility(View.GONE);
                    quoteEntities = getpersonal_bank_list_response.getResult();
                    getupdatelist(quoteEntities,false);
                    updateCalculatedList(tenureyears,loanamount);
                  //  mAdapter = new bank_display_businessloan_Adapter(bank_selection_businessloanActivity.this, getpersonal_bank_list_response.getResult());
                  //  rvQuotes.setAdapter(mAdapter);
                } else {
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
                //int monthlyInc = Integer.parseInt(etMonthlyInc.getText().toString());

                    etMonthlyInc.setSelection(etMonthlyInc.getText().length());


                loanamount = Integer.parseInt(etMonthlyInc.getText().toString());
                updateCalculatedList(tenureyears,loanamount);
            }

        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

}
