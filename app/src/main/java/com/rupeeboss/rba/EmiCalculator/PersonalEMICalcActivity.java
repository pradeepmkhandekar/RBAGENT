package com.rupeeboss.rba.EmiCalculator;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.rupeeboss.rba.BaseActivity;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.controller.Emicalculator.EmiPersonalCalculatorController;
import com.rupeeboss.rba.core.facade.HomeLoanRequestfacade;
import com.rupeeboss.rba.core.facade.PropertyFacade;
import com.rupeeboss.rba.core.model.PropertyEntity;
import com.rupeeboss.rba.core.request.requestentity.HomeEmiCalRequest;
import com.rupeeboss.rba.core.request.requestentity.HomeLoanRequest;
import com.rupeeboss.rba.core.response.EmiHomeCalcResponse;
import com.rupeeboss.rba.utility.Constants;
import com.rupeeboss.rba.utility.Utility;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PersonalEMICalcActivity extends BaseActivity   implements View.OnClickListener,IResponseSubcriber,SeekBar.OnSeekBarChangeListener,TextWatcher {

    EmiHomeCalcResponse getemiHomeCalcResponse;
    Toolbar toolbar;
    HomeEmiCalRequest homeLoanRequest;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    Button btnGetQuote;
    // region Applicant Details
    EditText etMonthlyInc, etEMI, etTurnOver,etTenureInYear;
    Spinner sbSalary;
    ArrayAdapter<String> salaryTypeAdapter;
    LinearLayout llSalaried, llSelfEmployeed;


   // EditText etCostOfProp;
    TextView ettxtMaxLoanAmntAllow, txtDispalayMinCostProp, txtDispalayMaxCostProp, txtDispalayMinTenureYear, txtDispalayMaxTenureYear;

    ArrayList<String> arrayNewLoan;
    ArrayAdapter<String> newLoanAdapter;

    SeekBar sbTenure;

    int seekBarCostPropProgress = 5;
    int seekBarTenureProgress = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_emicalc);
        init_widgets();
        setListener();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        loadSpinner();
    }

    private void init_widgets() {

        //region Main Initialize
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        btnGetQuote = (Button) findViewById(R.id.btn_submit);
        //endregion

        //region Property Initialize


        etTenureInYear = (EditText) findViewById(R.id.etTenureInYear);
      //  etCostOfProp = (EditText) findViewById(R.id.etCostOfProp);
        ettxtMaxLoanAmntAllow = (EditText) findViewById(R.id.ettxtMaxLoanAmntAllow);
//        txtDispalayMinCostProp = (TextView) findViewById(R.id.txtDispalayMinCostProp);
//        txtDispalayMaxCostProp = (TextView) findViewById(R.id.txtDispalayMaxCostProp);
        txtDispalayMinTenureYear = (TextView) findViewById(R.id.txtDispalayMinTenureYear);
        txtDispalayMaxTenureYear = (TextView) findViewById(R.id.txtDispalayMaxTenureYear);
//        etTenureInYear = (EditText) findViewById(R.id.etTenureInYear);

        sbTenure = (SeekBar) findViewById(R.id.sbTenure);


        //txtMaxLoanAmntAllow.setText(String.format("%.2f", getPercent(500000)));
        sbTenure.setMax(5);
        sbTenure.setProgress(1);
        etTenureInYear.setText("1");


        //endregion

        //region Applicant Initialize
        llSelfEmployeed = (LinearLayout) findViewById(R.id.llSelfEmployeed);
        llSalaried = (LinearLayout) findViewById(R.id.llSalaried);

        etTurnOver = (EditText) findViewById(R.id.etTurnOver);
//        etProfitAtTax = (EditText) findViewById(R.id.etProfitAtTax);
//        etDepreciation = (EditText) findViewById(R.id.etDepreciation);
//        etDirecPartRemuntion = (EditText) findViewById(R.id.etDirecPartRemuntion);

        sbSalary = (Spinner) findViewById(R.id.sbSalary);

        etMonthlyInc = (EditText) findViewById(R.id.etMonthlyInc);
        etEMI = (EditText) findViewById(R.id.etEMI);



//        sbMonthlyInc = (SeekBar) findViewById(R.id.sbMonthlyInc);
//        sbMonthlyInc.setMax(2500);    //2500
//        sbMonthlyInc.setProgress(1);
        etMonthlyInc.setText("25000");
        //endregion


    }


    private void setListener() {


        sbTenure.setOnSeekBarChangeListener(this);
        // sbMonthlyInc.setOnSeekBarChangeListener(this);
        btnGetQuote.setOnClickListener(this);

        //region CheckBox  Co-Applicant  Listener

      //  etCostOfProp.addTextChangedListener(this);
        etMonthlyInc.addTextChangedListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btn_submit) {

          //  String CostOfProp = etCostOfProp.getText().toString();
            String TenureInYear = etTenureInYear.getText().toString();
            String MaxloanRequired= ettxtMaxLoanAmntAllow.getText().toString();

//            if (TextUtils.isEmpty(CostOfProp)) {
//
//                etCostOfProp.setError("Please Enter Cost Of Property.");
//                etCostOfProp.requestFocus();
//                return;
//
//            }
            if (TextUtils.isEmpty(MaxloanRequired)) {

                ettxtMaxLoanAmntAllow.setError("Please Enter Loan Amount.");
                ettxtMaxLoanAmntAllow.requestFocus();
                return;

            }

//            if(Integer.parseInt(CostOfProp) < 625000 ) {
//                etCostOfProp.setError("Please Enter Cost Of Property Amount Greater Than 80% of Required Amount.");
//                etCostOfProp.requestFocus();
//                return;
//            }
            if(Integer.parseInt(MaxloanRequired) < 50000 ) {
                ettxtMaxLoanAmntAllow.setError("Please Enter Loan Amount Greater Than Or Equal To 50000.");
                ettxtMaxLoanAmntAllow.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(TenureInYear)) {

                etTenureInYear.setError("Please Enter Tenure.");
                etTenureInYear.requestFocus();
                return;

            }

            //endregion

            //region Applicant Validation


            String MonthlyInc = etMonthlyInc.getText().toString();
            String TurnOver = etTurnOver.getText().toString();
          //  String ProfitAtTax = etProfitAtTax.getText().toString();
          //  String Depreciation = etDepreciation.getText().toString();
         //   String DirecPartRemuntion = etDirecPartRemuntion.getText().toString();


            if (sbSalary.getSelectedItem().equals("Salaried")) {
                if (TextUtils.isEmpty(MonthlyInc)) {

                    etMonthlyInc.setError("Please Enter Monthly Income.");
                    etMonthlyInc.requestFocus();
                    return;

                }
                if(Integer.parseInt(MonthlyInc) < 25000 ) {
                    etMonthlyInc.setError("Please Enter Monthly Income Greater Than Or Equal To 25000.");
                    etMonthlyInc.requestFocus();
                    return;
                }
            } else {


                if (TextUtils.isEmpty(TurnOver)) {

                    etTurnOver.setError("Please Enter Turnover.");
                    etTurnOver.requestFocus();
                    return;

                }




                // End Applicant


            }
//////

            //endregion
            setApplicantDetails();
            showProgressDialog();
            new EmiPersonalCalculatorController(PersonalEMICalcActivity.this).getEmiPersonalcalculatordata(homeLoanRequest,this);

        }


    }
    private void loadSpinner() {

        //region Applicant Income Source Adapter

        salaryTypeAdapter = new ArrayAdapter<String>(PersonalEMICalcActivity.this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.IncomeSource));
        salaryTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sbSalary.setAdapter(salaryTypeAdapter);
        sbSalary.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if (selectedItem.equals("Salaried")) {
                    // do your stuff
                    llSalaried.setVisibility(View.VISIBLE);
                    llSelfEmployeed.setVisibility(View.GONE);
                } else if (selectedItem.equals("Self-Employed")) {
                    llSalaried.setVisibility(View.GONE);
                    llSelfEmployeed.setVisibility(View.VISIBLE);
                }
            } // to close the onItemSelected

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


//        arrayNewLoan = new ArrayList<String>();
//        newLoanAdapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_spinner_item, getNewLoanList());
//        newLoanAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spNewLoan.setAdapter(newLoanAdapter);


    }

    private ArrayList<String> getNewLoanList() {

        if (new PropertyFacade(PersonalEMICalcActivity.this).getPropertyList() != null) {
            for (PropertyEntity entity : new PropertyFacade(PersonalEMICalcActivity.this).getPropertyList()) {
                arrayNewLoan.add(entity.getProperty_Type());
            }
        }

        return arrayNewLoan;
    }


    private void setApplicantDetails() {
        // region  HomeLoanRequest Binding

        homeLoanRequest = new HomeEmiCalRequest();

       // homeLoanRequest.setPropertyCost(etCostOfProp.getText().toString());
        homeLoanRequest.setLoanTenure(etTenureInYear.getText().toString());
        homeLoanRequest.setLoanRequired(ettxtMaxLoanAmntAllow.getText().toString());

        homeLoanRequest.setApplicantGender("M");
//        if (rbimgFemale.isChecked()) {
//            homeLoanRequest.setApplicantGender("F");
//        } else if (rbimgMale.isChecked()) {
//            homeLoanRequest.setApplicantGender("M");
//        }
        if (sbSalary.getSelectedItem().toString().contains("Salaried")) {
            homeLoanRequest.setApplicantSource("1");
        } else if (sbSalary.getSelectedItem().toString().contains("Self-Employed")) {
            homeLoanRequest.setApplicantSource("2");
        }
        if (homeLoanRequest.getApplicantSource() == "1") {
            homeLoanRequest.setApplicantIncome(etMonthlyInc.getText().toString());
        } else if (homeLoanRequest.getApplicantSource() == "2") {
            homeLoanRequest.setApplicantIncome(etTurnOver.getText().toString());
          //  homeLoanRequest.setProfitAfterTax(etProfitAtTax.getText().toString());
         //   homeLoanRequest.setDepreciation(etDepreciation.getText().toString());
        //    homeLoanRequest.setDirectorRemuneration(etDirecPartRemuntion.getText().toString());
        }

        homeLoanRequest.setApplicantObligations(etEMI.getText().toString());




        // homeLoanRequest.setBrokerId("" + new LoginFacade(HomeEMICalcActivity.this).getUser().getBrokerId());

        homeLoanRequest.setProductId("9"); //Personal


        //endregion
    }


    //region add-edit-delete HomeloanRequest

    private void saveHomeLoanRequest(HomeLoanRequest request) {
        new HomeLoanRequestfacade(this).storeHomeLoanRequest(request);
    }

    private void clearHomeLoanRequest() {
        new HomeLoanRequestfacade(this).clearCache();
    }

    private HomeLoanRequest getHomeLoanRequest() {
        return new HomeLoanRequestfacade(this).getHomeLoanRequest();
    }


    @Override
    public void OnSuccess(APIResponse response, String message) throws InterruptedException {
        dismissDialog();
        Constants.hideKeyBoard(llSalaried,PersonalEMICalcActivity.this);
        if (response instanceof EmiHomeCalcResponse) {
            if (response.getStatus() == "1") {
                getemiHomeCalcResponse = ((EmiHomeCalcResponse) response);
                if (response.getStatus() != "1") {

                    Toast.makeText(PersonalEMICalcActivity.this, message, Toast.LENGTH_SHORT).show();
                }else {
                    //  getemiHomeCalcResponse.getData().Bank_Name
//                startActivity(new Intent(HomeEMICalcActivity.this, QuoteActivity.class).putParcelableArrayListExtra(Constants.QUOTES, (ArrayList<QuoteEntity>) getemiHomeCalcResponse.getData())
//                        .putExtra(Constants.QUOTES, getemiHomeCalcResponse));

                    Intent intent = new Intent(PersonalEMICalcActivity.this ,HomeEMICalcPOPUP.class);
                    intent.putExtra(Utility.HOME_EMI_CAL,getemiHomeCalcResponse.getData());
                    intent.putExtra(Utility.MY_BUSISNESS_HDR, "Personal Loan EMI Calculator");
                    startActivity(intent);
                }
            } else {
                Toast.makeText(PersonalEMICalcActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void OnFailure(Throwable t) {
        dismissDialog();
        // startActivity(new Intent(HomeLoanActivity.this, QuoteActivity.class).putParcelableArrayListExtra(Constants.QUOTES, (ArrayList<QuoteEntity>) quoteEntities));
        Toast.makeText(PersonalEMICalcActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
    }

    //region SeekBar ChangeListener
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.sbTenure:
                if (progress >= seekBarTenureProgress) {
                    if (fromUser) {
                        // progress = ((int) Math.round(progress / seekBarTenureProgress)) * seekBarTenureProgress;
                        etTenureInYear.setText(String.valueOf(progress));
                    }
                } else {
                    etTenureInYear.setText(String.valueOf((long) seekBarTenureProgress));
                }
                break;
        }
    }


    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }
    private BigDecimal getMaxLoanAmount(String value) {
        double loanAmount = Double.parseDouble(value);
        return BigDecimal.valueOf(Math.ceil(loanAmount * .8)).setScale(0, BigDecimal.ROUND_HALF_UP);
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

      if (etTenureInYear.getText().hashCode() == s.hashCode()) {

            if (!etTenureInYear.getText().toString().equals("") && !etTenureInYear.getText().toString().equals(null)) {
                int tenureInYear = Integer.parseInt(etTenureInYear.getText().toString());
                sbTenure.setProgress(tenureInYear);

            }

        }else if (etMonthlyInc.getText().hashCode() == s.hashCode()) {

            if (!etMonthlyInc.getText().toString().equals("") && !etMonthlyInc.getText().toString().equals(null)) {
                int monthlyInc = Integer.parseInt(etMonthlyInc.getText().toString());

//                if(monthlyInc > 25000 ) {
//                    sbMonthlyInc.setProgress(monthlyInc / 1000);
//                }else{
//                    sbMonthlyInc.setProgress(1);
//                    etMonthlyInc.setSelection(etMonthlyInc.getText().length());
//                }


            }

        }
    }


    @Override
    public void afterTextChanged(Editable s) {

    }

}
