package com.rupeeboss.rba.personalloan;

import android.app.DatePickerDialog;
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
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.rupeeboss.rba.BaseActivity;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.controller.personalloan.PersonalLoanController;
import com.rupeeboss.rba.core.facade.LoginFacade;
import com.rupeeboss.rba.core.model.CustomerApplicationEntity;
import com.rupeeboss.rba.core.model.CustomerEntity;
import com.rupeeboss.rba.core.request.requestentity.PersonalLoanRequest;
import com.rupeeboss.rba.core.response.GetPersonalLoanResponse;
import com.rupeeboss.rba.utility.Constants;
import com.rupeeboss.rba.utility.DateTimePicker;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PersonalLoanActivity extends BaseActivity implements View.OnClickListener, IResponseSubcriber, SeekBar.OnSeekBarChangeListener, TextWatcher {
    //region Applicant Details


    PersonalLoanRequest personalLoanRequest;
    CustomerEntity customerEntity;
    CustomerApplicationEntity customerApplicationEntity;

    Toolbar toolbar;
    GetPersonalLoanResponse getPersonalLoanResponse;
    TextView txtApplicantDetail;
    LinearLayout llApplicantDetail, ll_chkCoApplicant;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    boolean isApplicantVisible = true;
    Button btnGetQuote;

    EditText etNameOfApplicant, et_DOB, etMonthlyInc, etEMI, etTurnOver, etProfitAtTax, etDepreciation, etDirecPartRemuntion;
    Spinner sbSalary;
    ArrayAdapter<String> salaryTypeAdapter;
    LinearLayout llSalaried, llSelfEmployeed;
    SeekBar sbMonthlyInc, sbTurnOver;
    CheckBox chkCoApplicant;
    RadioGroup rgGender;
    RadioButton rbimgMale, rbimgFemale;

    //region PropertyIndo
    EditText etCostOfProp, etTenureInYear;
    TextView txtDispalayMinCostProp, txtDispalayMaxCostProp, txtDispalayMinTenureYear, txtDispalayMaxTenureYear;
    SeekBar sbCostOfProp, sbTenure;

    int seekBarCostPropProgress = 1;
    int seekBarTenureProgress = 1;
    int seekBarApplTurnOverProgress = 1;
    int seekBarApplProfitProgress = 1;
    int seekBarApplDepricProgress = 1;
    int seekBarApplIncomeProgress = 25;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_loan);
        init_widgets();
        setListener();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        visibleApplicant();
        loadSpinner();

        if (getIntent().getBooleanExtra("IS_EDIT", false)) {
            customerEntity = getIntent().getParcelableExtra("CUST_DETAILS");
            fillCustomerDetails(customerEntity);
        }
        if (getIntent().getBooleanExtra("IS_APP_EDIT", false)) {
            customerApplicationEntity = getIntent().getParcelableExtra("CUST_APP_DETAILS");
            fillCustomerApplicationDetails(customerApplicationEntity);
        }


    }

    private void fillCustomerApplicationDetails(CustomerApplicationEntity customerEntity) {
        if (customerEntity.getLoanRequired() != null)
            etCostOfProp.setText(customerEntity.getLoanRequired());
        if (customerEntity.getLoanTenure() != null)
            etTenureInYear.setText(customerEntity.getLoanTenure());
        if (customerEntity.getApplicantNme() != null)
            etNameOfApplicant.setText(customerEntity.getApplicantNme());
        /*if (customerEntity.getApplicantGender().equals("M")) {
            rbimgMale.setSelected(true);
        } else {
            rbimgFemale.setSelected(true);
        }*/
        if (customerEntity.getApplicantDOB() != null)
            et_DOB.setText(customerEntity.getApplicantDOB());
        if (customerEntity.getApplicantIncome() != null)
            etMonthlyInc.setText("" + customerEntity.getApplicantIncome());


    }

    private void fillCustomerDetails(CustomerEntity customerEntity) {
        if (customerEntity.getLoanRequired() != null)
            etCostOfProp.setText(customerEntity.getLoanRequired());
        if (customerEntity.getLoanTenure() != null)
            etTenureInYear.setText(customerEntity.getLoanTenure());
        if (customerEntity.getApplicantNme() != null)
            etNameOfApplicant.setText(customerEntity.getApplicantNme());
        /*if (customerEntity.getApplicantGender().equals("M")) {
            rbimgMale.setSelected(true);
        } else {
            rbimgFemale.setSelected(true);
        }*/
        if (customerEntity.getApplicantDOB() != null)
            et_DOB.setText(customerEntity.getApplicantDOB());
        if (customerEntity.getApplicantIncome() != null)
            etMonthlyInc.setText("" + customerEntity.getApplicantIncome());


    }

    private void init_widgets() {

        //region Main Initialize
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        txtApplicantDetail = (TextView) findViewById(R.id.txtApplicantDetail);

        llApplicantDetail = (LinearLayout) findViewById(R.id.llApplicantDetail);

        btnGetQuote = (Button) findViewById(R.id.btnGetQuote);
        //endregion
        //region Property Initialize
        etCostOfProp = (EditText) findViewById(R.id.etCostOfProp);
        txtDispalayMinCostProp = (TextView) findViewById(R.id.txtDispalayMinCostProp);
        txtDispalayMaxCostProp = (TextView) findViewById(R.id.txtDispalayMaxCostProp);
        txtDispalayMinTenureYear = (TextView) findViewById(R.id.txtDispalayMinTenureYear);
        txtDispalayMaxTenureYear = (TextView) findViewById(R.id.txtDispalayMaxTenureYear);
        etTenureInYear = (EditText) findViewById(R.id.etTenureInYear);
        sbCostOfProp = (SeekBar) findViewById(R.id.sbCostOfProp);
        sbTenure = (SeekBar) findViewById(R.id.sbTenure);
        sbCostOfProp.setMax(200);    // 2 cr
        sbCostOfProp.setProgress(1);  // 5 lac
        etCostOfProp.setText("500000");
        //txtMaxLoanAmntAllow.setText(String.format("%.2f", getPercent(500000)));
        sbTenure.setMax(5);
        sbTenure.setProgress(1);
        etTenureInYear.setText("1");

        //region Applicant Initialize
        llSelfEmployeed = (LinearLayout) findViewById(R.id.llSelfEmployeed);
        llSalaried = (LinearLayout) findViewById(R.id.llSalaried);
        etNameOfApplicant = (EditText) findViewById(R.id.etNameOfApplicant);
        etTurnOver = (EditText) findViewById(R.id.etTurnOver);

        et_DOB = (EditText) findViewById(R.id.et_DOB);
        sbSalary = (Spinner) findViewById(R.id.sbSalary);
        sbMonthlyInc = (SeekBar) findViewById(R.id.sbMonthlyInc);
        sbTurnOver = (SeekBar) findViewById(R.id.sbTurnOver);

        etMonthlyInc = (EditText) findViewById(R.id.etMonthlyInc);
        etEMI = (EditText) findViewById(R.id.etEMI);

        rgGender = (RadioGroup) findViewById(R.id.rgGender);
        rbimgMale = (RadioButton) findViewById(R.id.rbimgMale);
        rbimgFemale = (RadioButton) findViewById(R.id.rbimgFemale);

        sbTurnOver.setMax(1000);    // 100 cr
        sbTurnOver.setProgress(10);  // 10 lac
        etTurnOver.setText("1000000");


        sbMonthlyInc.setMax(2500);
        sbMonthlyInc.setProgress(1);
        etMonthlyInc.setText("25000");


        //endregion
    }

    //region datePickerDialog Applicant
    protected View.OnClickListener datePickerDialogApplicant = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Constants.hideKeyBoard(view, getApplicationContext());
            DateTimePicker.showDataPickerDialogBeforeTwentyOne(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                    Calendar calendar = Calendar.getInstance();
                    calendar.set(year, monthOfYear, dayOfMonth);
                    String currentDay = simpleDateFormat.format(calendar.getTime());
                    et_DOB.setText(currentDay);
                    //etDate.setTag(R.id.et_date, calendar.getTime());
                }
            });
        }
    };
    //endregion


    private void altervisibleApplicant() {
        if (isApplicantVisible) {
            txtApplicantDetail.setText(" Application Details");
            txtApplicantDetail.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.down_arrow_bas_screen, 0);
            llApplicantDetail.setVisibility(View.GONE);
            isApplicantVisible = false;
        } else {
            txtApplicantDetail.setText(" Application Details");
            txtApplicantDetail.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.right_arrow_bas_screen, 0);
            llApplicantDetail.setVisibility(View.VISIBLE);
            isApplicantVisible = true;
        }
    }

    private void setListener() {
        sbCostOfProp.setOnSeekBarChangeListener(this);
        sbTenure.setOnSeekBarChangeListener(this);
        sbTurnOver.setOnSeekBarChangeListener(this);


        sbMonthlyInc.setOnSeekBarChangeListener(this);


        txtApplicantDetail.setOnClickListener(this);


        btnGetQuote.setOnClickListener(this);
        et_DOB.setOnClickListener(datePickerDialogApplicant);


        //region CheckBox  Co-Applicant  Listener


        etCostOfProp.addTextChangedListener(this);
        etTenureInYear.addTextChangedListener(this);
        etMonthlyInc.addTextChangedListener(this);
        etTurnOver.addTextChangedListener(this);


    }

    private void visibleApplicant() {

        txtApplicantDetail.setText(" Application Details");
        txtApplicantDetail.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.right_arrow_bas_screen, 0);
        // llApplicantDetail.setVisibility(visibility);
        //isApplicantVisible = true;

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnGetQuote) {
            //region Validation
            //region Property Validation
            String CostOfProp = etCostOfProp.getText().toString();
            String TenureInYear = etTenureInYear.getText().toString();
            if (TextUtils.isEmpty(CostOfProp)) {

                etCostOfProp.setError("Please Enter Cost Of Property.");
                etCostOfProp.requestFocus();
                return;

            }
            if (TextUtils.isEmpty(TenureInYear)) {

                etTenureInYear.setError("Please Enter Tenure.");
                etTenureInYear.requestFocus();
                return;

            }
            //endregion
            //region Applicant Validation
            String NameOfApplicant = etNameOfApplicant.getText().toString();
            String DOB = et_DOB.getText().toString();
            String MonthlyInc = etMonthlyInc.getText().toString();
            String TurnOver = etTurnOver.getText().toString();


            if (TextUtils.isEmpty(NameOfApplicant)) {

                etNameOfApplicant.setError("Please Enter Name Of Applicant.");
                etNameOfApplicant.requestFocus();
                return;

            }
            if (TextUtils.isEmpty(DOB)) {

                et_DOB.setError("Please Enter DOB.");
                et_DOB.requestFocus();
                return;

            }
            if (sbSalary.getSelectedItem().equals("Salaried")) {
                if (TextUtils.isEmpty(MonthlyInc)) {

                    etMonthlyInc.setError("Please Enter Monthly Income.");
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
            }/////
            //endregion
            // endregion
            setApplicantDetails();
            showProgressDialog();
            new PersonalLoanController(PersonalLoanActivity.this).getPersonalLoan(personalLoanRequest, this);

        }
    }

    private void loadSpinner() {

        //region Applicant Income Source Adapter

        salaryTypeAdapter = new ArrayAdapter<String>(PersonalLoanActivity.this,
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

        //endregion


    }

    private void setApplicantDetails() {
        // region  HomeLoanRequest Binding

        personalLoanRequest = new PersonalLoanRequest();
        personalLoanRequest.setLoanRequired(etCostOfProp.getText().toString());
        personalLoanRequest.setLoanTenure(etTenureInYear.getText().toString());
        personalLoanRequest.setApplicantNme(etNameOfApplicant.getText().toString());

        if (sbSalary.getSelectedItem().toString().contains("Salaried")) {
            personalLoanRequest.setApplicantSource("1");
        } else if (sbSalary.getSelectedItem().toString().contains("Self-Employed")) {
            personalLoanRequest.setApplicantSource("2");
        }
        if (personalLoanRequest.getApplicantSource() == "1") {
            personalLoanRequest.setApplicantIncome(etMonthlyInc.getText().toString());
        } else if (personalLoanRequest.getApplicantSource() == "2") {
            personalLoanRequest.setApplicantIncome(etTurnOver.getText().toString());
            //same in personal loan

        }

        if (rbimgMale.isChecked()) {
            personalLoanRequest.setApplicantGender("M");
        } else {
            personalLoanRequest.setApplicantGender("F");
        }

        personalLoanRequest.setApplicantObligations(etEMI.getText().toString());
        personalLoanRequest.setApplicantDOB(et_DOB.getText().toString());

        personalLoanRequest.setBrokerId("RBAAPP");
        personalLoanRequest.setBrokerId("" + new LoginFacade(PersonalLoanActivity.this).getUser().getBrokerId());
        personalLoanRequest.setempcode("" + new LoginFacade(PersonalLoanActivity.this).getUser().getEmpCode());
        // personalLoanRequest.setProductId("12");


        //endregion
    }

    @Override
    public void OnSuccess(APIResponse response, String message) throws InterruptedException {
        dismissDialog();
        if (response instanceof GetPersonalLoanResponse) {
            if (response.getStatus_Id() == 0) {
                getPersonalLoanResponse = ((GetPersonalLoanResponse) response);
                startActivity(new Intent(PersonalLoanActivity.this, PersonalLoanQuoteActivity.class)
                        .putExtra(Constants.PERSONAL_LOAN_QUOTES, getPersonalLoanResponse)
                        .putExtra(Constants.PL_REQUEST, personalLoanRequest));
            } else {
                Toast.makeText(PersonalLoanActivity.this, response.getMsg(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void OnFailure(Throwable t) {
        dismissDialog();
        // startActivity(new Intent(HomeLoanActivity.this, QuoteActivity.class).putParcelableArrayListExtra(Constants.QUOTES, (ArrayList<QuoteEntity>) quoteEntities));
        Toast.makeText(PersonalLoanActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.sbCostOfProp:
//                if (fromUser) {
//                    if (progress <= 10) {
//                        long value = 500000 + progress * 50000;
//                        etCostOfProp.setText("" + value);
//                    } else if (progress <= 50 && progress > 10) {
//                        long value = progress * 100000;
//                        etCostOfProp.setText("" + value);
//                    } else if (progress < 60 && progress > 50) {
//                        long value = (progress % 10) * 500000 + 5000000;
//                        etCostOfProp.setText("" + value);
//                    } else if (progress < 110 && progress >= 60) {
//                        long value = (progress % 60) * 10000000 + 10000000;
//                        etCostOfProp.setText("" + value);
//                    }
//                    //progress = ((int) Math.round(progress / seekBarCostPropProgress)) * seekBarCostPropProgress;
//                    //etCostOfProp.setText(String.valueOf(((long) progress) * 100000));
//                    //txtMaxLoanAmntAllow.setText("" + getMaxLoanAmount(etCostOfProp.getText().toString()).intValueExact());
//                }

                if (progress >= seekBarCostPropProgress) {
                    if (fromUser) {
                        //progress = ((int) Math.round(progress / seekBarCostPropProgress)) * seekBarCostPropProgress;
                        etCostOfProp.setText(String.valueOf(((long) progress) * 100000));

                    }
                } else {
                    etCostOfProp.setText(String.valueOf(((long) seekBarCostPropProgress) * 100000));

                }
                break;

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

            case R.id.sbTurnOver:
                if (progress >= seekBarApplTurnOverProgress) {
                    if (fromUser) {
                        // progress = ((int) Math.round(progress / seekBarTenureProgress)) * seekBarTenureProgress;
                        etTurnOver.setText(String.valueOf(((long) progress) * 1000000));
                    }
                } else {
                    etTurnOver.setText(String.valueOf(((long) seekBarApplTurnOverProgress) * 1000000));
                }
                break;

            case R.id.sbProfitAfTax:
                if (progress >= seekBarApplProfitProgress) {
                    if (fromUser) {
                        //    progress = ((int) Math.round(progress / seekBarApplProfitProgress)) * seekBarApplProfitProgress;
                        etProfitAtTax.setText(String.valueOf(((long) progress) * 1000000));
                    }
                } else {
                    etProfitAtTax.setText(String.valueOf(((long) seekBarApplProfitProgress) * 1000000));
                }
                break;

            case R.id.sbDepreciation:
                if (progress >= seekBarApplDepricProgress) {
                    if (fromUser) {
                        //    progress = ((int) Math.round(progress / seekBarApplDepricProgress)) * seekBarApplDepricProgress;
                        etDepreciation.setText(String.valueOf(((long) progress) * 100000));
                    }
                } else {
                    etDepreciation.setText(String.valueOf(((long) seekBarApplDepricProgress) * 100000));
                }
                break;

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

            case R.id.sbDirecPartRemuntion:
                if (progress >= seekBarApplDepricProgress) {
                    if (fromUser) {
                        //    progress = ((int) Math.round(progress / seekBarApplDepricProgress)) * seekBarApplDepricProgress;
                        etDirecPartRemuntion.setText(String.valueOf(((long) progress) * 100000));
                    }
                } else {
                    etDirecPartRemuntion.setText(String.valueOf(((long) seekBarApplDepricProgress) * 100000));
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
        if (etCostOfProp.getText().hashCode() == s.hashCode()) {

//            if (!etCostOfProp.getText().toString().equals("") && !etCostOfProp.getText().toString().equals(null)) {
//                int costOfProperty = Integer.parseInt(etCostOfProp.getText().toString());
//
//                if (costOfProperty <= 1000000 && costOfProperty > 500000) {
//                    costOfProperty = costOfProperty - 500000;
//                    sbCostOfProp.setProgress(costOfProperty / 50000);
//                } else if (costOfProperty <= 5000000 && costOfProperty > 1000000) {
//                    sbCostOfProp.setProgress(costOfProperty / 100000);
//                } else if (costOfProperty <= 10000000 && costOfProperty > 5000000) {
//                    costOfProperty = costOfProperty - 5000000;
//                    sbCostOfProp.setProgress(50 + (costOfProperty / 5000000));
//                } else if (costOfProperty > 10000000) {
//                    costOfProperty = costOfProperty - 10000000;
//                    sbCostOfProp.setProgress(60 + (costOfProperty / 10000000));
//                }
//
//                int sactionAmount = getMaxLoanAmount("" + costOfProperty).intValueExact();
//                // txtMaxLoanAmntAllow.setText("" + sactionAmount);
//                // sbCostOfProp.setProgress(costOfProperty / 100000);
//
//            } else {
//                // txtMaxLoanAmntAllow.setText("");
//            }

            if (!etCostOfProp.getText().toString().equals("") && !etCostOfProp.getText().toString().equals(null)) {

                int costOfProperty = Integer.parseInt(etCostOfProp.getText().toString());
                int sactionAmount = getMaxLoanAmount("" + costOfProperty).intValueExact();

                if (costOfProperty > 500000) {

                    sbCostOfProp.setProgress(costOfProperty / 100000);
                } else {
                    sbCostOfProp.setProgress(1);
                    //   etCostOfProp.setSelection(etCostOfProp.getText().length());
                }

            }

        } else if (etTenureInYear.getText().hashCode() == s.hashCode()) {

            if (!etTenureInYear.getText().toString().equals("") && !etTenureInYear.getText().toString().equals(null)) {
                int tenureInYear = Integer.parseInt(etTenureInYear.getText().toString());
                sbTenure.setProgress(tenureInYear);

            }

        } else if (etTurnOver.getText().hashCode() == s.hashCode()) {

            if (!etTurnOver.getText().toString().equals("") && !etTurnOver.getText().toString().equals(null)) {
                int turnOver = Integer.parseInt(etTurnOver.getText().toString());
                sbTurnOver.setProgress(turnOver / 1000000);
            }
        } else if (etMonthlyInc.getText().hashCode() == s.hashCode()) {

            if (!etMonthlyInc.getText().toString().equals("") && !etMonthlyInc.getText().toString().equals(null)) {
                int monthlyInc = Integer.parseInt(etMonthlyInc.getText().toString());

                if (monthlyInc > 25000) {
                    sbMonthlyInc.setProgress(monthlyInc / 1000);
                } else {
                    sbMonthlyInc.setProgress(1);
                    etMonthlyInc.setSelection(etMonthlyInc.getText().length());
                }


            }

        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }


}