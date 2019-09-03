package com.rupeeboss.rba.homeloan;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
import com.rupeeboss.rba.core.controller.homeloan.HomeLoanController;
import com.rupeeboss.rba.core.facade.CityFacade;
import com.rupeeboss.rba.core.facade.HomeLoanRequestfacade;
import com.rupeeboss.rba.core.facade.LoginFacade;
import com.rupeeboss.rba.core.facade.ProductFacade;
import com.rupeeboss.rba.core.facade.PropertyFacade;
import com.rupeeboss.rba.core.model.CityEntity;
import com.rupeeboss.rba.core.model.CustomerApplicationEntity;
import com.rupeeboss.rba.core.model.CustomerEntity;
import com.rupeeboss.rba.core.model.PropertyEntity;
import com.rupeeboss.rba.core.model.QuoteEntity;
import com.rupeeboss.rba.core.request.requestentity.HomeLoanRequest;
import com.rupeeboss.rba.core.response.GetQuoteResponse;
import com.rupeeboss.rba.quotes.QuoteActivity;
import com.rupeeboss.rba.utility.Constants;
import com.rupeeboss.rba.utility.DateTimePicker;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class HomeLoanActivity extends BaseActivity implements View.OnClickListener, IResponseSubcriber, SeekBar.OnSeekBarChangeListener, TextWatcher {

    //region Declaration

    GetQuoteResponse getQuoteResponse;

    CustomerEntity customerEntity;
    CustomerApplicationEntity customerApplicationEntity;

    TextView txtPropertyInfo, txtCoApplicantDetail, txtApplicantDetail;
    LinearLayout llPropertyInfo, llApplicantDetail, llCoApplicantDetail;
    Toolbar toolbar;
    HomeLoanRequest homeLoanRequest;


    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    boolean isPropertyInfoVisible = false;
    boolean isApplicantVisible = true;
    boolean isCoApplicantVisible = true;

    Button btnGetQuote;
    // region Applicant Details
    EditText etNameOfApplicant, et_DOB, etMonthlyInc, etEMI, etTurnOver, etProfitAtTax, etDepreciation, etDirecPartRemuntion;
    Spinner sbSalary;
    ArrayAdapter<String> salaryTypeAdapter;
    LinearLayout llSalaried, llSelfEmployeed;
    SeekBar sbMonthlyInc, sbTurnOver, sbProfitAfTax, sbDepreciation, sbDirecPartRemuntion;
    CheckBox chkCoApplicant;
    RadioGroup rgGender;
    RadioButton rbimgMale, rbimgFemale;
    //endregion

    //region PropertyIndo
    EditText etCostOfProp, etTenureInYear;
    TextView txtMaxLoanAmntAllow, txtDispalayMinCostProp, txtDispalayMaxCostProp, txtDispalayMinTenureYear, txtDispalayMaxTenureYear;
    Spinner spNewLoan;
    ArrayList<String> arrayNewLoan, arrayPreferedCity;
    ArrayAdapter<String> newLoanAdapter;
    ArrayAdapter<String> preferedCityAdapter;
    SeekBar sbCostOfProp, sbTenure;

    int seekBarCostPropProgress = 5;
    int seekBarTenureProgress = 1;
    int seekBarApplTurnOverProgress = 1;
    int seekBarApplProfitProgress = 1;
    int seekBarApplDepricProgress = 1;
    int seekBarApplIncomeProgress = 25;

    //endregion

    //region Co-Applicant Details
    EditText coApp_etNameOfApplicant, coApp_et_DOB, coApp_etMonthlyInc, coApp_etEMI, coApp_etTurnOver, coApp_etProfitAtTax, coApp_etDepreciation, coApp_etDirecPartRemuntion;
    Spinner coApp_sbSalary, coApp_sbRelation;
    ArrayAdapter<String> coApp_salaryTypeAdapter, coApp_relationTypeAdapter;
    LinearLayout coApp_llSalaried, coApp_llSelfEmployeed;
    SeekBar coApp_sbMonthlyInc, coApp_sbTurnOver, coApp_sbProfitAfTax, coApp_sbDepreciation, coApp_sbDirecPartRemuntion;
    RadioGroup coApp_rgGender;
    RadioButton coApp_rbimgMale, coApp_rbimgFemale;
    AutoCompleteTextView acCity;
    boolean isCitySelected;
    //endregion

    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeloan);
        init_widgets();
        setListener();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        visibleApplicant(View.GONE);
        visibleCoApplicant(View.GONE);
        visiblePropertyInfo(View.GONE);
        txtCoApplicantDetail.setVisibility(View.GONE);


        loadSpinner();
        if (getIntent().getBooleanExtra("IS_EDIT", false)) {

            customerEntity = getIntent().getParcelableExtra("CUST_DETAILS");
            fillCustomerDetails(customerEntity);
            isCitySelected=true;
            visiblePropertyInfo(View.VISIBLE);
        }
        if (getIntent().getBooleanExtra("IS_APP_EDIT", false)) {

            customerApplicationEntity = getIntent().getParcelableExtra("CUST_APP_DETAILS");
            fillCustomerApplicationDetails(customerApplicationEntity);
            isCitySelected=true;
            visiblePropertyInfo(View.VISIBLE);
        }
    }

    private void fillCustomerDetails(CustomerEntity customerEntity) {
        Log.d("DETAILS", customerEntity.toString());

        if (customerEntity.getPropertyID() != null)
            spNewLoan.setSelection(newLoanAdapter.getPosition(customerEntity.getPropertyID()));
        if (customerEntity.getLoanRequired() != null)
            etCostOfProp.setText(customerEntity.getLoanRequired());
        if (customerEntity.getLoanTenure() != null)
            etTenureInYear.setText(customerEntity.getLoanTenure());
        if (customerEntity.getCity() != null)
            acCity.setText(customerEntity.getCity());

        if (customerEntity.getApplicantNme() != null)
            etNameOfApplicant.setText(customerEntity.getApplicantNme());

        if (customerEntity.getApplicantGender().matches("male")) {
            rbimgMale.setSelected(true);
        } else {
            rbimgFemale.setSelected(true);
        }
        if (customerEntity.getApplicantDOB() != null)
            et_DOB.setText(customerEntity.getApplicantDOB());

        if (customerEntity.getApplicantSource() != null) {
            sbSalary.setSelection(Integer.parseInt(customerEntity.getApplicantSource()) - 1);
        }
        if (customerEntity.getApplicantIncome() != null)
            etMonthlyInc.setText(customerEntity.getApplicantIncome());

        if (customerEntity.getApplicantObligations() != null)
            etEMI.setText(customerEntity.getApplicantObligations());

        if (customerEntity.getTurnover() != null)
            etTurnOver.setText(customerEntity.getTurnover());

        if (customerEntity.getProfitAfterTax() != null)
            etProfitAtTax.setText(customerEntity.getProfitAfterTax());

        if (customerEntity.getDepreciation() != null)
            etDepreciation.setText(customerEntity.getDepreciation());

        if (customerEntity.getDirectorRemuneration() != null)
            etDirecPartRemuntion.setText(customerEntity.getDirectorRemuneration());

        if (customerEntity.getCoApplicantYes() != null) {
            if (customerEntity.getCoApplicantYes().matches("Y")) {

                if (customerEntity.getCoApplicantDOB() != null)
                    coApp_et_DOB.setText(customerEntity.getCoApplicantDOB());

                if (customerEntity.getCoApplicantSource() != null)
                    coApp_sbSalary.setSelection(Integer.parseInt(customerEntity.getCoApplicantSource()) - 1);

                if (customerEntity.getCoApplicantObligations() != null)
                    coApp_etEMI.setText(customerEntity.getCoApplicantObligations());

                if (customerEntity.getCoApplicantTurnover() != null)
                    coApp_etTurnOver.setText(customerEntity.getCoApplicantTurnover());

                if (customerEntity.getCoApplicantProfitAfterTax() != null)
                    coApp_etProfitAtTax.setText(customerEntity.getCoApplicantProfitAfterTax());

                if (customerEntity.getCoApplicantDepreciation() != null)
                    coApp_etDepreciation.setText(customerEntity.getCoApplicantDepreciation());

                if (customerEntity.getCoApplicantDirectorRemuneration() != null)
                    coApp_etDirecPartRemuntion.setText(customerEntity.getCoApplicantDirectorRemuneration());

            }
        }


    }

    private void fillCustomerApplicationDetails(CustomerApplicationEntity customerEntity) {
        Log.d("DETAILS", customerApplicationEntity.toString());

        /*if (customerEntity.getPropertyID() != null)
            spNewLoan.setSelection(newLoanAdapter.getPosition(customerEntity.getPropertyID()));*/
        if (customerEntity.getLoanRequired() != null)
            etCostOfProp.setText(customerEntity.getLoanRequired());
        if (customerEntity.getLoanTenure() != null)
            etTenureInYear.setText(customerEntity.getLoanTenure());
        if (customerEntity.getCity() != null)
            acCity.setText(customerEntity.getCity());

        if (customerEntity.getApplicantNme() != null)
            etNameOfApplicant.setText(customerEntity.getApplicantNme());

        if (customerEntity.getApplicantGender().matches("male")) {
            rbimgMale.setSelected(true);
        } else {
            rbimgFemale.setSelected(true);
        }
        if (customerEntity.getApplicantDOB() != null)
            et_DOB.setText(customerEntity.getApplicantDOB());

       /* if (customerEntity.getApplicantSource() != null) {
            sbSalary.setSelection(Integer.parseInt(customerEntity.getApplicantSource()) - 1);
        }*/
        if (customerEntity.getApplicantIncome() != null)
            etMonthlyInc.setText(customerEntity.getApplicantIncome());

        if (customerEntity.getApplicantObligations() != null)
            etEMI.setText(customerEntity.getApplicantObligations());

        if (customerEntity.getTurnover() != null)
            etTurnOver.setText(customerEntity.getTurnover());

        if (customerEntity.getProfitAfterTax() != null)
            etProfitAtTax.setText(customerEntity.getProfitAfterTax());

        if (customerEntity.getDepreciation() != null)
            etDepreciation.setText(customerEntity.getDepreciation());

        if (customerEntity.getDirectorRemuneration() != null)
            etDirecPartRemuntion.setText(customerEntity.getDirectorRemuneration());

        if (customerEntity.getCoApplicantYes() != null) {
            if (customerEntity.getCoApplicantYes().matches("Y")) {
                chkCoApplicant.setSelected(true);
            }
        }
        if (customerEntity.getCoApplicantDOB() != null)
            coApp_et_DOB.setText(customerEntity.getCoApplicantDOB());

        if (customerEntity.getCoApplicantSource() != null)
            coApp_sbSalary.setSelection(Integer.parseInt(customerEntity.getCoApplicantSource()) - 1);

        if (customerEntity.getCoApplicantObligations() != null)
            coApp_etEMI.setText(customerEntity.getCoApplicantObligations());

        if (customerEntity.getCoApplicantTurnover() != null)
            coApp_etTurnOver.setText(customerEntity.getCoApplicantTurnover());

        if (customerEntity.getCoApplicantProfitAfterTax() != null)
            coApp_etProfitAtTax.setText(customerEntity.getCoApplicantProfitAfterTax());

        if (customerEntity.getCoApplicantDepreciation() != null)
            coApp_etDepreciation.setText(customerEntity.getCoApplicantDepreciation());

        if (customerEntity.getCoApplicantDirectorRemuneration() != null)
            coApp_etDirecPartRemuntion.setText(customerEntity.getCoApplicantDirectorRemuneration());
    }

    private void init_widgets() {

        //region Main Initialize
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        txtPropertyInfo = (TextView) findViewById(R.id.txtPropertyInfo);
        txtCoApplicantDetail = (TextView) findViewById(R.id.txtCoApplicantDetail);
        txtApplicantDetail = (TextView) findViewById(R.id.txtApplicantDetail);
        llPropertyInfo = (LinearLayout) findViewById(R.id.llPropertyInfo);
        llApplicantDetail = (LinearLayout) findViewById(R.id.llApplicantDetail);
        llCoApplicantDetail = (LinearLayout) findViewById(R.id.llCoApplicantDetail);
        btnGetQuote = (Button) findViewById(R.id.btnGetQuote);
        //endregion

        //region Property Initialize

        spNewLoan = (Spinner) findViewById(R.id.spNewLoan);
        //spPreferedCity = (Spinner) findViewById(R.id.spPreferedCity);
        etCostOfProp = (EditText) findViewById(R.id.etCostOfProp);
        txtMaxLoanAmntAllow = (TextView) findViewById(R.id.txtMaxLoanAmntAllow);
        txtDispalayMinCostProp = (TextView) findViewById(R.id.txtDispalayMinCostProp);
        txtDispalayMaxCostProp = (TextView) findViewById(R.id.txtDispalayMaxCostProp);
        txtDispalayMinTenureYear = (TextView) findViewById(R.id.txtDispalayMinTenureYear);
        txtDispalayMaxTenureYear = (TextView) findViewById(R.id.txtDispalayMaxTenureYear);
        etTenureInYear = (EditText) findViewById(R.id.etTenureInYear);
        sbCostOfProp = (SeekBar) findViewById(R.id.sbCostOfProp);
        sbTenure = (SeekBar) findViewById(R.id.sbTenure);

        sbCostOfProp.setMax(5000);    // 50 cr
        sbCostOfProp.setProgress(5);  // 5 lac
        etCostOfProp.setText("500000");
        //txtMaxLoanAmntAllow.setText(String.format("%.2f", getPercent(500000)));
        sbTenure.setMax(30);
        sbTenure.setProgress(1);
        etTenureInYear.setText("1");
        acCity = (AutoCompleteTextView) findViewById(R.id.acCity);

        //endregion

        //region Applicant Initialize
        llSelfEmployeed = (LinearLayout) findViewById(R.id.llSelfEmployeed);
        llSalaried = (LinearLayout) findViewById(R.id.llSalaried);
        etNameOfApplicant = (EditText) findViewById(R.id.etNameOfApplicant);
        etTurnOver = (EditText) findViewById(R.id.etTurnOver);
        etProfitAtTax = (EditText) findViewById(R.id.etProfitAtTax);
        etDepreciation = (EditText) findViewById(R.id.etDepreciation);
        etDirecPartRemuntion = (EditText) findViewById(R.id.etDirecPartRemuntion);
        et_DOB = (EditText) findViewById(R.id.et_DOB);
        sbSalary = (Spinner) findViewById(R.id.sbSalary);
        sbMonthlyInc = (SeekBar) findViewById(R.id.sbMonthlyInc);
        sbTurnOver = (SeekBar) findViewById(R.id.sbTurnOver);
        sbProfitAfTax = (SeekBar) findViewById(R.id.sbProfitAfTax);
        sbDepreciation = (SeekBar) findViewById(R.id.sbDepreciation);
        sbDirecPartRemuntion = (SeekBar) findViewById(R.id.sbDirecPartRemuntion);
        etMonthlyInc = (EditText) findViewById(R.id.etMonthlyInc);
        etEMI = (EditText) findViewById(R.id.etEMI);
        chkCoApplicant = (CheckBox) findViewById(R.id.chkCoApplicant);
        rgGender = (RadioGroup) findViewById(R.id.rgGender);
        rbimgMale = (RadioButton) findViewById(R.id.rbimgMale);
        rbimgFemale = (RadioButton) findViewById(R.id.rbimgFemale);

        sbTurnOver.setMax(1000);    // 100 cr
        sbTurnOver.setProgress(10);  // 10 lac
        etTurnOver.setText("1000000");

        sbProfitAfTax.setMax(100);
        sbProfitAfTax.setProgress(10);
        etProfitAtTax.setText("1000000");

        sbDepreciation.setMax(100);
        sbDepreciation.setProgress(1);
        etDepreciation.setText("100000");

        sbMonthlyInc.setMax(2500);    //2500
        sbMonthlyInc.setProgress(1);
        etMonthlyInc.setText("25000");


        sbDirecPartRemuntion.setMax(100);
        sbDirecPartRemuntion.setProgress(1);
        etDirecPartRemuntion.setText("100000");


        //endregion

        //region Co-Applicant Initialize
        coApp_llSelfEmployeed = (LinearLayout) findViewById(R.id.coApp_llSelfEmployeed);
        coApp_llSalaried = (LinearLayout) findViewById(R.id.coApp_llSalaried);
        coApp_etNameOfApplicant = (EditText) findViewById(R.id.coApp_etNameOfApplicant);
        coApp_etTurnOver = (EditText) findViewById(R.id.coApp_etTurnOver);
        coApp_etProfitAtTax = (EditText) findViewById(R.id.coApp_etProfitAtTax);
        coApp_etDepreciation = (EditText) findViewById(R.id.coApp_etDepreciation);
        coApp_etDirecPartRemuntion = (EditText) findViewById(R.id.coApp_etDirecPartRemuntion);
        coApp_et_DOB = (EditText) findViewById(R.id.coApp_et_DOB);
        coApp_sbSalary = (Spinner) findViewById(R.id.coApp_sbSalary);
        coApp_sbRelation = (Spinner) findViewById(R.id.coApp_sbRelation);
        coApp_sbMonthlyInc = (SeekBar) findViewById(R.id.coApp_sbMonthlyInc);
        coApp_sbTurnOver = (SeekBar) findViewById(R.id.coApp_sbTurnOver);
        coApp_sbProfitAfTax = (SeekBar) findViewById(R.id.coApp_sbProfitAfTax);
        coApp_sbDepreciation = (SeekBar) findViewById(R.id.coApp_sbDepreciation);
        coApp_sbDirecPartRemuntion = (SeekBar) findViewById(R.id.coApp_sbDirecPartRemuntion);
        coApp_etMonthlyInc = (EditText) findViewById(R.id.coApp_etMonthlyInc);
        coApp_etEMI = (EditText) findViewById(R.id.coApp_etEMI);
        coApp_rgGender = (RadioGroup) findViewById(R.id.coApp_rgGender);
        coApp_rbimgMale = (RadioButton) findViewById(R.id.coApp_rbimgMale);
        coApp_rbimgFemale = (RadioButton) findViewById(R.id.coApp_rbimgFemale);

        //co-appdetail
        coApp_sbMonthlyInc.setMax(2500);
        coApp_sbMonthlyInc.setProgress(1);
        coApp_etMonthlyInc.setText("2500");

        coApp_sbTurnOver.setMax(1000);    // 100 cr
        coApp_sbTurnOver.setProgress(10);  // 10 lac
        coApp_etTurnOver.setText("1000000");

        coApp_sbProfitAfTax.setMax(100);
        coApp_sbProfitAfTax.setProgress(10);
        coApp_etProfitAtTax.setText("1000000");

        coApp_sbDepreciation.setMax(100);
        coApp_sbDepreciation.setProgress(1);
        coApp_etDepreciation.setText("100000");

        coApp_sbDirecPartRemuntion.setMax(100);
        coApp_sbDirecPartRemuntion.setProgress(1);
        coApp_etDirecPartRemuntion.setText("100000");


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

    //region datePickerDialog CoApplicant
    protected View.OnClickListener datePickerDialogCoApplicant = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Constants.hideKeyBoard(view, getApplicationContext());
            DateTimePicker.showDataPickerDialogBeforeTwentyOne(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                    Calendar calendar = Calendar.getInstance();
                    calendar.set(year, monthOfYear, dayOfMonth);
                    String currentDay = simpleDateFormat.format(calendar.getTime());
                    coApp_et_DOB.setText(currentDay);
                    //etDate.setTag(R.id.et_date, calendar.getTime());
                }
            });
        }
    };
    //endregion

    private void setListener() {
        // region auto complete city listener
        acCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                isCitySelected = true;
            }
        });
        acCity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isCitySelected = false;
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        // endregion
        sbCostOfProp.setOnSeekBarChangeListener(this);
        sbTenure.setOnSeekBarChangeListener(this);
        sbTurnOver.setOnSeekBarChangeListener(this);
        sbProfitAfTax.setOnSeekBarChangeListener(this);
        sbDepreciation.setOnSeekBarChangeListener(this);
        sbMonthlyInc.setOnSeekBarChangeListener(this);
        sbDirecPartRemuntion.setOnSeekBarChangeListener(this);

        coApp_sbMonthlyInc.setOnSeekBarChangeListener(this);
        coApp_sbTurnOver.setOnSeekBarChangeListener(this);
        coApp_sbProfitAfTax.setOnSeekBarChangeListener(this);
        coApp_sbDepreciation.setOnSeekBarChangeListener(this);
        coApp_sbDirecPartRemuntion.setOnSeekBarChangeListener(this);


        txtPropertyInfo.setOnClickListener(this);
        txtApplicantDetail.setOnClickListener(this);
        txtCoApplicantDetail.setOnClickListener(this);

        btnGetQuote.setOnClickListener(this);
        et_DOB.setOnClickListener(datePickerDialogApplicant);
        coApp_et_DOB.setOnClickListener(datePickerDialogCoApplicant);

        //region CheckBox  Co-Applicant  Listener

        chkCoApplicant.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    txtCoApplicantDetail.setVisibility(View.VISIBLE);
                    visiblePropertyInfo(View.GONE);
                    visibleApplicant(View.GONE);
                    visibleCoApplicant(View.VISIBLE);
                } else {
                    visibleCoApplicant(View.GONE);
                    visiblePropertyInfo(View.GONE);
                    visibleApplicant(View.VISIBLE);
                    txtCoApplicantDetail.setVisibility(View.GONE);
                }
            }
        });

        etCostOfProp.addTextChangedListener(this);
        etTenureInYear.addTextChangedListener(this);
        etMonthlyInc.addTextChangedListener(this);
        etTurnOver.addTextChangedListener(this);
        etProfitAtTax.addTextChangedListener(this);
        etDepreciation.addTextChangedListener(this);
        etDirecPartRemuntion.addTextChangedListener(this);
        coApp_etMonthlyInc.addTextChangedListener(this);
        coApp_etTurnOver.addTextChangedListener(this);
        coApp_etProfitAtTax.addTextChangedListener(this);
        coApp_etDepreciation.addTextChangedListener(this);
        coApp_etDirecPartRemuntion.addTextChangedListener(this);


    }

    private void visiblePropertyInfo(int visibility) {
        if (visibility == View.VISIBLE) {
            isPropertyInfoVisible = true;
        } else {
            isPropertyInfoVisible = false;
        }
        if (visibility == View.GONE) {
            txtPropertyInfo.setText(" Property Information");
            txtPropertyInfo.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.down_arrow_bas_screen, 0);
            txtPropertyInfo.setBackgroundResource(R.color.grey_form_header);
            llPropertyInfo.setVisibility(visibility);
            //isPropertyInfoVisible = false;
        } else {
            txtPropertyInfo.setText(" Property Information");
            txtPropertyInfo.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.right_arrow_bas_screen, 0);
            txtPropertyInfo.setBackground(getResources().getDrawable(R.drawable.bg3));
            llPropertyInfo.setVisibility(visibility);
            //isPropertyInfoVisible = true;
        }
    }

    private void visibleApplicant(int visibility) {
        if (visibility == View.VISIBLE) {
            isApplicantVisible = true;
        } else {
            isApplicantVisible = false;
        }
        if (visibility == View.GONE) {
            txtApplicantDetail.setText(" Application Details");
            txtApplicantDetail.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.down_arrow_bas_screen, 0);
            txtApplicantDetail.setBackgroundResource(R.color.grey_form_header);
            llApplicantDetail.setVisibility(visibility);
            //isApplicantVisible = false;
        } else {
            txtApplicantDetail.setText(" Application Details");
            txtApplicantDetail.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.right_arrow_bas_screen, 0);
            txtApplicantDetail.setBackground(getResources().getDrawable(R.drawable.bg3));
            llApplicantDetail.setVisibility(visibility);
            //isApplicantVisible = true;
        }
    }

    private void visibleCoApplicant(int visibility) {
        if (visibility == View.VISIBLE) {
            isCoApplicantVisible = true;
        } else {
            isCoApplicantVisible = false;
        }
        if (visibility == View.GONE) {
            txtCoApplicantDetail.setText(" Co-Application Details");
            txtCoApplicantDetail.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.down_arrow_bas_screen, 0);
            txtCoApplicantDetail.setBackgroundResource(R.color.grey_form_header);
            llCoApplicantDetail.setVisibility(visibility);
            //isCoApplicantVisible = false;
        } else {
            txtCoApplicantDetail.setText(" Co-Application Details");
            txtCoApplicantDetail.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.right_arrow_bas_screen, 0);
            txtCoApplicantDetail.setBackground(getResources().getDrawable(R.drawable.bg3));
            llCoApplicantDetail.setVisibility(visibility);
            //isCoApplicantVisible = true;
        }
    }

    private void altervisiblePropertyInfo() {
        if (isPropertyInfoVisible) {
            txtPropertyInfo.setText(" Property Information");
            txtPropertyInfo.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.down_arrow_bas_screen, 0);
            txtPropertyInfo.setBackgroundResource(R.color.grey_form_header);
            llPropertyInfo.setVisibility(View.GONE);
            isPropertyInfoVisible = false;
        } else {
            txtPropertyInfo.setText(" Property Information");
            txtPropertyInfo.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.right_arrow_bas_screen, 0);
            txtPropertyInfo.setBackground(getResources().getDrawable(R.drawable.bg3));
            llPropertyInfo.setVisibility(View.VISIBLE);
            isPropertyInfoVisible = true;
        }
    }

    private void altervisibleApplicant() {
        if (isApplicantVisible) {
            txtApplicantDetail.setText(" Application Details");
            txtApplicantDetail.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.down_arrow_bas_screen, 0);
            txtApplicantDetail.setBackgroundResource(R.color.grey_form_header);
            llApplicantDetail.setVisibility(View.GONE);
            isApplicantVisible = false;
        } else {
            txtApplicantDetail.setText(" Application Details");
            txtApplicantDetail.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.right_arrow_bas_screen, 0);
            txtApplicantDetail.setBackground(getResources().getDrawable(R.drawable.bg3));
            llApplicantDetail.setVisibility(View.VISIBLE);
            isApplicantVisible = true;
        }
    }

    private void altervisibleCoApplicant() {
        if (isCoApplicantVisible) {
            txtCoApplicantDetail.setText(" Co-Application Details");
            txtCoApplicantDetail.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.down_arrow_bas_screen, 0);
            txtCoApplicantDetail.setBackgroundResource(R.color.grey_form_header);
            llCoApplicantDetail.setVisibility(View.GONE);
            isCoApplicantVisible = false;
        } else {
            txtCoApplicantDetail.setText(" Co-Application Details");
            txtCoApplicantDetail.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.right_arrow_bas_screen, 0);
            txtCoApplicantDetail.setBackground(getResources().getDrawable(R.drawable.bg3));
            llCoApplicantDetail.setVisibility(View.VISIBLE);
            isCoApplicantVisible = true;
        }
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.txtPropertyInfo) {
            visibleApplicant(View.GONE);
            visibleCoApplicant(View.GONE);
            altervisiblePropertyInfo();

            // setPropertyDetails();

        } else if (v.getId() == R.id.txtApplicantDetail) {
            visiblePropertyInfo(View.GONE);
            visibleCoApplicant(View.GONE);
            altervisibleApplicant();
        } else if (v.getId() == R.id.txtCoApplicantDetail) {
            visiblePropertyInfo(View.GONE);
            visibleApplicant(View.GONE);
            altervisibleCoApplicant();
        } else if (v.getId() == R.id.btnGetQuote) {
            //region Validation

            //region Property Validation
            String CostOfProp = etCostOfProp.getText().toString();
            String TenureInYear = etTenureInYear.getText().toString();

            if (!isCitySelected) {
                acCity.setError("Please Enter city.");
                acCity.requestFocus();
                return;
            }
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
            String ProfitAtTax = etProfitAtTax.getText().toString();
            String Depreciation = etDepreciation.getText().toString();
            String DirecPartRemuntion = etDirecPartRemuntion.getText().toString();


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

                if (TextUtils.isEmpty(ProfitAtTax)) {

                    etProfitAtTax.setError("Please Enter Profit After Tax.");
                    etProfitAtTax.requestFocus();
                    return;

                }

                if (TextUtils.isEmpty(Depreciation)) {

                    etDepreciation.setError("Please Enter Depreciation.");
                    etDepreciation.requestFocus();
                    return;

                }

                if (TextUtils.isEmpty(DirecPartRemuntion)) {

                    etDirecPartRemuntion.setError("Please Enter Director/Partner Remuneration.");
                    etDirecPartRemuntion.requestFocus();
                    return;

                }

                // End Applicant


            }
//////

            //endregion

            //region Co-Applicant Validation

            if (chkCoApplicant.isChecked()) {
                String coAppNameOfApplicant = coApp_etNameOfApplicant.getText().toString();
                String coAppDOB = coApp_et_DOB.getText().toString();
                String coAppMonthlyInc = coApp_etMonthlyInc.getText().toString();
                String coAppTurnOver = coApp_etTurnOver.getText().toString();
                String coAppProfitAtTax = coApp_etProfitAtTax.getText().toString();
                String coAppDepreciation = coApp_etDepreciation.getText().toString();
                String coAppDirecPartRemuntion = coApp_etDirecPartRemuntion.getText().toString();

                if (TextUtils.isEmpty(coAppNameOfApplicant)) {

                    coApp_etNameOfApplicant.setError("Enter Name Of Co-Applicant.");
                    coApp_etNameOfApplicant.requestFocus();
                    return;

                }
                if (TextUtils.isEmpty(coAppDOB)) {

                    coApp_et_DOB.setError("Enter DOB.");
                    coApp_et_DOB.requestFocus();
                    return;

                }

                if (coApp_sbSalary.getSelectedItem().equals("Salaried")) {
                    if (TextUtils.isEmpty(coAppMonthlyInc)) {

                        coApp_etMonthlyInc.setError("Enter Monthly Income.");
                        coApp_etMonthlyInc.requestFocus();
                        return;

                    }
                } else {
                    if (TextUtils.isEmpty(coAppTurnOver)) {

                        coApp_etTurnOver.setError("Enter Turnover.");
                        coApp_etTurnOver.requestFocus();
                        return;

                    }

                    if (TextUtils.isEmpty(coAppProfitAtTax)) {

                        coApp_etProfitAtTax.setError("Enter Profit After Tax.");
                        coApp_etProfitAtTax.requestFocus();
                        return;

                    }

                    if (TextUtils.isEmpty(coAppDepreciation)) {

                        coApp_etDepreciation.setError("Enter Depreciation.");
                        coApp_etDepreciation.requestFocus();
                        return;

                    }

                    if (TextUtils.isEmpty(coAppDirecPartRemuntion)) {

                        coApp_etDirecPartRemuntion.setError("Enter Director/Partner Remuneration.");
                        coApp_etDirecPartRemuntion.requestFocus();
                        return;

                    }
                }
                // End Co-Applicant
            }

            //endregion

            // endregion
            setApplicantDetails();
            showProgressDialog();
            new HomeLoanController(HomeLoanActivity.this).getHomeLoan(homeLoanRequest, this);

        }


    }

    private void loadSpinner() {

        //region Applicant Income Source Adapter

        salaryTypeAdapter = new ArrayAdapter<String>(HomeLoanActivity.this,
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

        //region Relation Type Adapter
        coApp_relationTypeAdapter = new ArrayAdapter<String>(HomeLoanActivity.this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.RelationType));
        coApp_relationTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        coApp_sbRelation.setAdapter(coApp_relationTypeAdapter);
        //endregion

        //region Co-Applicant Income Source Adapter
        coApp_salaryTypeAdapter = new ArrayAdapter<String>(HomeLoanActivity.this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.IncomeSource));
        coApp_salaryTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        coApp_sbSalary.setAdapter(coApp_salaryTypeAdapter);
        coApp_sbSalary.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if (selectedItem.equals("Salaried")) {
                    // do your stuff
                    coApp_llSalaried.setVisibility(View.VISIBLE);
                    coApp_llSelfEmployeed.setVisibility(View.GONE);
                } else if (selectedItem.equals("Self-Employed")) {
                    coApp_llSalaried.setVisibility(View.GONE);
                    coApp_llSelfEmployeed.setVisibility(View.VISIBLE);
                }
            } // to close the onItemSelected

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //endregion

        //region Loan Type Adapter
        arrayNewLoan = new ArrayList<String>();
        newLoanAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, getNewLoanList());
        newLoanAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spNewLoan.setAdapter(newLoanAdapter);
        //endregion

        //region Preferred City Adapter
        arrayPreferedCity = new ArrayList<String>();
        preferedCityAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, getCityList());
        //preferedCityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spPreferedCity.setAdapter(preferedCityAdapter);
        acCity.setAdapter(preferedCityAdapter);
        acCity.setThreshold(2);
        //endregion

    }

    private ArrayList<String> getNewLoanList() {

        if (new PropertyFacade(HomeLoanActivity.this).getPropertyList() != null) {
            for (PropertyEntity entity : new PropertyFacade(HomeLoanActivity.this).getPropertyList()) {
                arrayNewLoan.add(entity.getProperty_Type());
            }
        }

        return arrayNewLoan;
    }


    private ArrayList<String> getCityList() {

        if (new CityFacade(HomeLoanActivity.this).getCityList() != null) {
            for (CityEntity entity : new CityFacade(HomeLoanActivity.this).getCityList()) {
                arrayPreferedCity.add(entity.getCity_Name());
            }
        }

        return arrayPreferedCity;
    }

    private void setApplicantDetails() {
        // region  HomeLoanRequest Binding

        homeLoanRequest = new HomeLoanRequest();

        homeLoanRequest.setPropertyID("" + new PropertyFacade(HomeLoanActivity.this).getPropertyId(spNewLoan.getSelectedItem().toString()));
        homeLoanRequest.setPropertyCost(etCostOfProp.getText().toString());
        homeLoanRequest.setLoanTenure(etTenureInYear.getText().toString());
        homeLoanRequest.setLoanRequired(txtMaxLoanAmntAllow.getText().toString());
        homeLoanRequest.setCity("" + acCity.getText().toString());
        homeLoanRequest.setApplicantNme(etNameOfApplicant.getText().toString());
        if (rbimgFemale.isChecked()) {
            homeLoanRequest.setApplicantGender("F");
        } else if (rbimgMale.isChecked()) {
            homeLoanRequest.setApplicantGender("M");
        }
        if (sbSalary.getSelectedItem().toString().contains("Salaried")) {

            homeLoanRequest.setApplicantSource("1");
        } else if (sbSalary.getSelectedItem().toString().contains("Self-Employed")) {
            homeLoanRequest.setApplicantSource("2");
        }
        if (homeLoanRequest.getApplicantSource() == "1") {
            homeLoanRequest.setApplicantIncome(etMonthlyInc.getText().toString());
        } else if (homeLoanRequest.getApplicantSource() == "2") {
            homeLoanRequest.setTurnover(etTurnOver.getText().toString());
            homeLoanRequest.setProfitAfterTax(etProfitAtTax.getText().toString());
            homeLoanRequest.setDepreciation(etDepreciation.getText().toString());
            homeLoanRequest.setDirectorRemuneration(etDirecPartRemuntion.getText().toString());
        }

        homeLoanRequest.setApplicantObligations(etEMI.getText().toString());
        homeLoanRequest.setApplicantDOB(et_DOB.getText().toString());
        if (chkCoApplicant.isChecked()) {
            homeLoanRequest.setCoApplicantYes("Y");
            if (coApp_rbimgFemale.isChecked()) {
                homeLoanRequest.setCoApplicantGender("F");
            } else if (coApp_rbimgMale.isChecked()) {
                homeLoanRequest.setCoApplicantGender("M");
            }
            if (coApp_sbSalary.getSelectedItem().toString().contains("Salaried")) {
                homeLoanRequest.setCoApplicantSource("1");
            } else if (sbSalary.getSelectedItem().toString().contains("Self-Employed")) {
                homeLoanRequest.setCoApplicantSource("2");
            }
            if (homeLoanRequest.getCoApplicantSource() == "1") {
                homeLoanRequest.setCoApplicantIncome(coApp_etMonthlyInc.getText().toString());
            } else if (homeLoanRequest.getCoApplicantSource() == "2") {
                homeLoanRequest.setCoApplicantTurnover(coApp_etTurnOver.getText().toString());
                homeLoanRequest.setCoApplicantProfitAfterTax(coApp_etProfitAtTax.getText().toString());
                homeLoanRequest.setCoApplicantDepreciation(coApp_etDepreciation.getText().toString());
                homeLoanRequest.setCoApplicantDirectorRemuneration(coApp_etDirecPartRemuntion.getText().toString());
            }
            homeLoanRequest.setCoApplicantObligations(coApp_etEMI.getText().toString());
            homeLoanRequest.setCoApplicantDOB(coApp_et_DOB.getText().toString());


        } else {
            homeLoanRequest.setCoApplicantYes("N");
        }
        homeLoanRequest.setBrokerId("" + new LoginFacade(HomeLoanActivity.this).getUser().getBrokerId());
        homeLoanRequest.setempcode("" + new LoginFacade(HomeLoanActivity.this).getUser().getEmpCode());
        homeLoanRequest.setProductId("12");


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


    //endregion


    private void setPropertyDetails() {
        ApplicantEntity propEntity = new ApplicantEntity();
        propEntity.setProCostOfProperty(etCostOfProp.getText().toString());
        propEntity.setProMaxLoanAmntAllow(txtMaxLoanAmntAllow.getText().toString());
        propEntity.setProTenureInYears(etTenureInYear.getText().toString());

        propEntity.setProNewLoan(new ProductFacade(HomeLoanActivity.this).getProductId(spNewLoan.getSelectedItem().toString()));
        //propEntity.setProPreferedCity(new CityFacade(HomeLoanActivity.this).getCityId(spPreferedCity.getSelectedItem().toString()));


    }


    @Override
    public void OnSuccess(APIResponse response, String message) throws InterruptedException {
        dismissDialog();
        if (response instanceof GetQuoteResponse) {
            if (response.getStatus_Id() == 0) {
                getQuoteResponse = ((GetQuoteResponse) response);
                startActivity(new Intent(HomeLoanActivity.this, QuoteActivity.class).putParcelableArrayListExtra(Constants.QUOTES, (ArrayList<QuoteEntity>) getQuoteResponse.getData())
                        .putExtra(Constants.QUOTES, getQuoteResponse)
                        .putExtra(Constants.HL_REQUEST, homeLoanRequest));
            } else {
                Toast.makeText(HomeLoanActivity.this, response.getMsg(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void OnFailure(Throwable t) {
        dismissDialog();
        // startActivity(new Intent(HomeLoanActivity.this, QuoteActivity.class).putParcelableArrayListExtra(Constants.QUOTES, (ArrayList<QuoteEntity>) quoteEntities));
        Toast.makeText(HomeLoanActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
    }

    //region SeekBar ChangeListener
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.sbCostOfProp:
                if (progress >= seekBarCostPropProgress) {
                    if (fromUser) {
                        //progress = ((int) Math.round(progress / seekBarCostPropProgress)) * seekBarCostPropProgress;
                        etCostOfProp.setText(String.valueOf(((long) progress) * 100000));
                        txtMaxLoanAmntAllow.setText("" + getMaxLoanAmount(etCostOfProp.getText().toString()).intValueExact());
                    }
                } else {
                    etCostOfProp.setText(String.valueOf(((long) seekBarCostPropProgress) * 100000));
                    txtMaxLoanAmntAllow.setText("" + getMaxLoanAmount(etCostOfProp.getText().toString()).intValueExact());
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

            // Co- Applicant
            case R.id.coApp_sbMonthlyInc:
                if (progress >= seekBarApplIncomeProgress) {
                    if (fromUser) {
                        //   progress = ((int) Math.round(progress / seekBarApplIncomeProgress)) * seekBarApplIncomeProgress;
                        coApp_etMonthlyInc.setText(String.valueOf(((long) progress) * 1000));
                    }
                } else {
                    coApp_etMonthlyInc.setText(String.valueOf(((long) seekBarApplIncomeProgress) * 1000));
                }

                break;

            case R.id.coApp_sbTurnOver:
                if (progress >= seekBarApplTurnOverProgress) {
                    if (fromUser) {
                        //    progress = ((int) Math.round(progress / seekBarApplTurnOverProgress)) * seekBarApplTurnOverProgress;
                        coApp_etTurnOver.setText(String.valueOf(((long) progress) * 1000000));
                    }
                } else {
                    coApp_etTurnOver.setText(String.valueOf(((long) seekBarApplTurnOverProgress) * 1000000));
                }
                break;

            case R.id.coApp_sbProfitAfTax:
                if (progress >= seekBarApplProfitProgress) {
                    if (fromUser) {
                        //    progress = ((int) Math.round(progress / seekBarApplProfitProgress)) * seekBarApplProfitProgress;
                        coApp_etProfitAtTax.setText(String.valueOf(((long) progress) * 1000000));
                    }
                } else {
                    coApp_etProfitAtTax.setText(String.valueOf(((long) seekBarApplProfitProgress) * 1000000));
                }
                break;

            case R.id.coApp_sbDepreciation:
                if (progress >= seekBarApplDepricProgress) {
                    if (fromUser) {
                        //   progress = ((int) Math.round(progress / seekBarApplDepricProgress)) * seekBarApplDepricProgress;
                        coApp_etDepreciation.setText(String.valueOf(((long) progress) * 100000));
                    }
                } else {
                    coApp_etDepreciation.setText(String.valueOf(((long) seekBarApplDepricProgress) * 100000));
                }
                break;

            case R.id.coApp_sbDirecPartRemuntion:
                if (progress >= seekBarApplDepricProgress) {
                    if (fromUser) {
                        //     progress = ((int) Math.round(progress / seekBarApplDepricProgress)) * seekBarApplDepricProgress;
                        coApp_etDirecPartRemuntion.setText(String.valueOf(((long) progress) * 100000));
                    }
                } else {
                    coApp_etDirecPartRemuntion.setText(String.valueOf(((long) seekBarApplDepricProgress) * 100000));
                }
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        //  Log.d("progressSeek", "onStart 1");

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

            if (!etCostOfProp.getText().toString().equals("") && !etCostOfProp.getText().toString().equals(null)) {

                int costOfProperty = Integer.parseInt(etCostOfProp.getText().toString());
                int sactionAmount = getMaxLoanAmount("" + costOfProperty).intValueExact();
                txtMaxLoanAmntAllow.setText("" + sactionAmount);
                if (costOfProperty > 500000) {

                    sbCostOfProp.setProgress(costOfProperty / 100000);
                } else {
                    sbCostOfProp.setProgress(1);
                    // etCostOfProp.setSelection(etCostOfProp.getText().length());
                }

            } else {
                txtMaxLoanAmntAllow.setText("");
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

        } else if (etProfitAtTax.getText().hashCode() == s.hashCode()) {

            if (!etProfitAtTax.getText().toString().equals("") && !etProfitAtTax.getText().toString().equals(null)) {
                int profitAtTax = Integer.parseInt(etProfitAtTax.getText().toString());
                sbProfitAfTax.setProgress(profitAtTax / 1000000);
            }

        } else if (etDepreciation.getText().hashCode() == s.hashCode()) {

            if (!etDepreciation.getText().toString().equals("") && !etDepreciation.getText().toString().equals(null)) {
                int depreciation = Integer.parseInt(etDepreciation.getText().toString());
                sbDepreciation.setProgress(depreciation / 100000);
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

        } else if (etDirecPartRemuntion.getText().hashCode() == s.hashCode()) {
            if (!etDirecPartRemuntion.getText().toString().equals("") && !etDirecPartRemuntion.getText().toString().equals(null)) {
                int direcPartRemuntion = Integer.parseInt(etDirecPartRemuntion.getText().toString());
                sbDirecPartRemuntion.setProgress(direcPartRemuntion / 100000);
            }

        } else if (coApp_etMonthlyInc.getText().hashCode() == s.hashCode()) {
            if (!coApp_etMonthlyInc.getText().toString().equals("") && !coApp_etMonthlyInc.getText().toString().equals(null)) {
                int coApp_MonthlyInc = Integer.parseInt(coApp_etMonthlyInc.getText().toString());
                if (coApp_MonthlyInc > 25000) {
                    coApp_sbMonthlyInc.setProgress(coApp_MonthlyInc / 1000);
                } else {
                    coApp_sbMonthlyInc.setProgress(1);
                    coApp_etMonthlyInc.setSelection(coApp_etMonthlyInc.getText().length());
                }
            }

        } else if (coApp_etTurnOver.getText().hashCode() == s.hashCode()) {
            if (!coApp_etTurnOver.getText().toString().equals("") && !coApp_etTurnOver.getText().toString().equals(null)) {
                int coApp_TurnOver = Integer.parseInt(coApp_etTurnOver.getText().toString());
                coApp_sbTurnOver.setProgress(coApp_TurnOver / 1000000);
            }
        } else if (coApp_etProfitAtTax.getText().hashCode() == s.hashCode()) {
            if (!coApp_etProfitAtTax.getText().toString().equals("") && !coApp_etProfitAtTax.getText().toString().equals(null)) {
                int coApp_ProfitAtTax = Integer.parseInt(coApp_etProfitAtTax.getText().toString());
                coApp_sbProfitAfTax.setProgress(coApp_ProfitAtTax / 1000000);
            }

        } else if (coApp_etDepreciation.getText().hashCode() == s.hashCode()) {
            if (!coApp_etDepreciation.getText().toString().equals("") && !coApp_etDepreciation.getText().toString().equals(null)) {
                int coApp_Depreciation = Integer.parseInt(coApp_etDepreciation.getText().toString());
                coApp_sbDepreciation.setProgress(coApp_Depreciation / 100000);
            }

        } else if (coApp_etDirecPartRemuntion.getText().hashCode() == s.hashCode()) {
            if (!coApp_etDirecPartRemuntion.getText().toString().equals("") && !coApp_etDirecPartRemuntion.getText().toString().equals(null)) {
                int coApp_DirecPartRemuntion = Integer.parseInt(coApp_etDirecPartRemuntion.getText().toString());
                coApp_sbDirecPartRemuntion.setProgress(coApp_DirecPartRemuntion / 100000);
            }

        }
    }

    @Override
    public void afterTextChanged(Editable s) {
    }
    // endregion
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}
