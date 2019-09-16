
package com.rupeeboss.rba.loan_fm.new_personalloan;

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
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


import com.rupeeboss.rba.BaseActivity;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.facade.LoginFacade;
import com.rupeeboss.rba.core_loan_fm.APIResponseERP;
import com.rupeeboss.rba.core_loan_fm.IResponseSubcriberERP;
import com.rupeeboss.rba.core_loan_fm.controller.erploan.ErpLoanController;
import com.rupeeboss.rba.core_loan_fm.model.LstCitywiseBankLoanEntity;
import com.rupeeboss.rba.core_loan_fm.response.citywisebankloanResponse;
import com.rupeeboss.rba.loan_fm.Utility;
import com.rupeeboss.rba.webviews.commonwebview.CommonWebviewActivity;

import java.util.ArrayList;
import java.util.List;




public class bank_selection_personalloanActivity extends BaseActivity implements View.OnClickListener, TextWatcher, SeekBar.OnSeekBarChangeListener, IResponseSubcriberERP {
    RecyclerView rvQuotes;
    bank_display_personalloan_Adapter  mAdapter;
    List<LstCitywiseBankLoanEntity> quoteEntities;

    citywisebankloanResponse getpersonal_bank_list_response;
    Toolbar toolbar;
    String Cityid="";
   // DBPersistanceController dbPersistanceController;
   // LoginResponseEntity loginResponseEntity;
    LinearLayout llmessage;
    Button btnBack;
    EditText etMonthlyInc;
    SeekBar  sbMonthlyInc;
    LinearLayout llSalaried;
    int seekBarApplIncomeProgress = 50;
    int tenureyears=5;
    int loanamount= 100000;
    LoginFacade loginFacade;
    String brokerId;
    TextView txtPendingDayshalf,txtPendingDays1,txtPendingDays2,txtPendingDays3,txtPendingDays4,txtPendingDays5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_selection_personalloan);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        quoteEntities = new ArrayList<>();

        llmessage = (LinearLayout)findViewById(R.id.llmessage) ;
        llmessage.setVisibility(View.GONE);
        etMonthlyInc = (EditText) findViewById(R.id.etMonthlyInc);
        etMonthlyInc.addTextChangedListener(this);
               sbMonthlyInc = (SeekBar) findViewById(R.id.sbMonthlyInc);
        sbMonthlyInc.setMax(3000);
        sbMonthlyInc.setProgress(2);
        etMonthlyInc.setText("100000");


        sbMonthlyInc.setOnSeekBarChangeListener(this);
        txtPendingDayshalf= (TextView) findViewById(R.id.txtPendingDayshalf) ;
        txtPendingDays1= (TextView) findViewById(R.id.txtPendingDays1) ;
        txtPendingDays2= (TextView) findViewById(R.id.txtPendingDays2) ;
        txtPendingDays3= (TextView) findViewById(R.id.txtPendingDays3) ;
        txtPendingDays4= (TextView) findViewById(R.id.txtPendingDays4) ;
        txtPendingDays5= (TextView) findViewById(R.id.txtPendingDays5) ;

        txtPendingDays5.setBackgroundResource(R.drawable.circular_select);
        txtPendingDays5.setTextColor(ContextCompat.getColor(bank_selection_personalloanActivity.this, R.color.white));
        btnBack= (Button) findViewById(R.id.btnBack) ;



        rvQuotes = (RecyclerView)findViewById(R.id.rvQuotes);
        rvQuotes.setLayoutManager(new LinearLayoutManager(bank_selection_personalloanActivity.this));
    //getBankdetail_personalloan
        Cityid= getIntent().getStringExtra("city_id");
      //  dbPersistanceController = new DBPersistanceController(bank_selection_personalloanActivity.this);
     //   loginResponseEntity = dbPersistanceController.getUserData();
        loginFacade = new LoginFacade(this);
        brokerId = "" + loginFacade.getUser().getBrokerId();
        setListener();
        List<LstCitywiseBankLoanEntity> lst = new ArrayList<>();
        mAdapter = new bank_display_personalloan_Adapter(bank_selection_personalloanActivity.this, lst);
        rvQuotes.setAdapter(mAdapter);
        showDialog();
       new ErpLoanController(bank_selection_personalloanActivity.this).getCitywiseBankListloan(Cityid,"9",bank_selection_personalloanActivity.this);
    }

    private void setListener() {
        btnBack.setOnClickListener(this);
        txtPendingDayshalf.setOnClickListener(this);
        txtPendingDays1.setOnClickListener(this);
        txtPendingDays2.setOnClickListener(this);
        txtPendingDays3.setOnClickListener(this);
        txtPendingDays4.setOnClickListener(this);
        txtPendingDays5.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
       // Constants.hideKeyBoard(view, this);
        switch (view.getId()) {
            case R.id.btnBack:
                startActivity(new Intent(bank_selection_personalloanActivity.this, city_selecton_personalloan_Activity.class));
                break;
            case R.id.txtPendingDayshalf:
                managePL_Common("half", txtPendingDayshalf, txtPendingDays1, txtPendingDays2, txtPendingDays3, txtPendingDays4,txtPendingDays5);
                updateCalculatedList_cash(loanamount);
                break;
            case R.id.txtPendingDays1:
                managePL_Common("one", txtPendingDays1,txtPendingDayshalf,  txtPendingDays2, txtPendingDays3, txtPendingDays4,txtPendingDays5);
                tenureyears=1;
                updateCalculatedList(tenureyears,loanamount);
                break;
            case R.id.txtPendingDays2:
                managePL_Common("two", txtPendingDays2, txtPendingDayshalf, txtPendingDays1, txtPendingDays3, txtPendingDays4,txtPendingDays5);
                tenureyears=2;
                updateCalculatedList(tenureyears,loanamount);
                break;
            case R.id.txtPendingDays3:
                managePL_Common("three",  txtPendingDays3,txtPendingDayshalf, txtPendingDays1, txtPendingDays2, txtPendingDays4,txtPendingDays5);
                tenureyears=3;
                updateCalculatedList(tenureyears,loanamount);
                break;
            case R.id.txtPendingDays4:
                managePL_Common("four", txtPendingDays4,txtPendingDayshalf, txtPendingDays1, txtPendingDays2, txtPendingDays3, txtPendingDays5);
                tenureyears=4;
                updateCalculatedList(tenureyears,loanamount);
                break;
            case R.id.txtPendingDays5:
                managePL_Common("five", txtPendingDays5,txtPendingDayshalf, txtPendingDays1, txtPendingDays2, txtPendingDays3, txtPendingDays4);
                tenureyears=5;
                updateCalculatedList(tenureyears,loanamount);
                break;
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

    private void managePL_Common(String Value, TextView clickedText, TextView textView1, TextView textView2, TextView textView3, TextView textView4,TextView textView5) {

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
        clickedText.setTextColor(ContextCompat.getColor(bank_selection_personalloanActivity.this, R.color.white));

        textView1.setBackgroundResource(R.drawable.circular_shape);
        textView1.setTextColor(ContextCompat.getColor(bank_selection_personalloanActivity.this, R.color.black));

        textView2.setBackgroundResource(R.drawable.circular_shape);
        textView2.setTextColor(ContextCompat.getColor(bank_selection_personalloanActivity.this, R.color.black));

        textView3.setBackgroundResource(R.drawable.circular_shape);
        textView3.setTextColor(ContextCompat.getColor(bank_selection_personalloanActivity.this, R.color.black));

        textView4.setBackgroundResource(R.drawable.circular_shape);
        textView4.setTextColor(ContextCompat.getColor(bank_selection_personalloanActivity.this, R.color.black));


        textView5.setBackgroundResource(R.drawable.circular_shape);
        textView5.setTextColor(ContextCompat.getColor(bank_selection_personalloanActivity.this, R.color.black));

    }


    public void redirectToApplyBank(LstCitywiseBankLoanEntity entity) {
        String url="";
        String Bankname="";

        Bankname = entity.getBank_Name();
       url= entity.getBank_Form_URL() + "?BrokerId=" + brokerId+"&FBAId=" + "0" + "&client_source=RBA&lead_id=";



        if(!entity.getBank_Id().equals("53")) {
            startActivity(new Intent(this, CommonWebviewActivity.class)
                    .putExtra("URL", url)
                    .putExtra("NAME", "" + Bankname)
                    .putExtra("TITLE", "" + Bankname));
        }else
        {
            Bankname="YES BANK";
            String url1 = "https://yesbankbot.buildquickbots.com/chat/rupeeboss/staff/?userid=" + brokerId+ "&usertype=RBA&vkey=b34f02e9-8f1c";

            Utility.loadWebViewUrlInBrowser(bank_selection_personalloanActivity.this,url1);
        }
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
                    //mAdapter = new bank_display_personalloan_Adapter(bank_selection_personalloanActivity.this, getpersonal_bank_list_response.getResult());
                    //rvQuotes.setAdapter(mAdapter);
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
                int monthlyInc = Integer.parseInt(etMonthlyInc.getText().toString());
//                if (monthlyInc == 50000)
//                {
//
//                }
//                else
                    if (monthlyInc > 50000) {
                    sbMonthlyInc.setProgress(monthlyInc / 1000);
                    //etMonthlyInc.setText("500000");
                }
                else {
                    sbMonthlyInc.setProgress(1);
                    etMonthlyInc.setSelection(etMonthlyInc.getText().length());
                }

                loanamount = Integer.parseInt(etMonthlyInc.getText().toString());
                updateCalculatedList(tenureyears,loanamount);
            }

        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        if(fromUser) {
            switch (seekBar.getId()) {

                case R.id.sbMonthlyInc:
                    if (progress >= seekBarApplIncomeProgress) {
                        if (fromUser) {
                            //   progress = ((int) Math.round(progress / seekBarApplIncomeProgress)) * seekBarApplIncomeProgress;
                            etMonthlyInc.setText(String.valueOf(((long) progress) * 1000));
                        }
                    } else {
                        etMonthlyInc.setText(String.valueOf(((long) seekBarApplIncomeProgress) * 1000));
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
