package com.rupeeboss.rba.EmiCalculator.businessloan;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.rupeeboss.rba.BaseActivity;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.controller.Emicalculator.EmicalculatorController;
import com.rupeeboss.rba.core.request.requestentity.BuisnessLoanCalRequest;
import com.rupeeboss.rba.core.response.BuisnessLoanCalResponse;
import com.rupeeboss.rba.utility.DateTimePicker;
import com.rupeeboss.rba.utility.Utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BusinessLoanCalActivity extends BaseActivity implements View.OnClickListener, IResponseSubcriber {

    EditText et_dateOfIncorp, et_turnOver, et_profitAfterTax, et_depreciation, et_interestPaidLoan, et_dirPatnersRem, et_totEmiPaid, et_LoanAmnt, et_noOfEmiPaid;
    Button btn_submit;
    BuisnessLoanCalResponse buisnessLoanCalResponse;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm");
    Spinner sbloanTentur;
    ArrayAdapter<String> salaryTypeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buisness_loan_cal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initialize();
        Calendar calendar = Calendar.getInstance();
        // et_dateOfIncorp.setText(simpleDateFormat.format(calendar.getTime()));
        btn_submit.setOnClickListener(this);
        et_dateOfIncorp.setOnClickListener(datePickerDialog);
        loadSpinner();

    }

    private void initialize() {

        // et_loanTenture = (EditText) findViewById(R.id.et_loanTenture);
        et_dateOfIncorp = (EditText) findViewById(R.id.et_dateOfIncorp);
        et_turnOver = (EditText) findViewById(R.id.et_turnOver);

        et_profitAfterTax = (EditText) findViewById(R.id.et_profitAfterTax);
        et_depreciation = (EditText) findViewById(R.id.et_depreciation);
        et_interestPaidLoan = (EditText) findViewById(R.id.et_interestPaidLoan);

        et_dirPatnersRem = (EditText) findViewById(R.id.et_dirPatnersRem);
        et_totEmiPaid = (EditText) findViewById(R.id.et_totEmiPaid);
        et_LoanAmnt = (EditText) findViewById(R.id.et_LoanAmnt);
        et_noOfEmiPaid = (EditText) findViewById(R.id.et_noOfEmiPaid);

        btn_submit = (Button) findViewById(R.id.btn_submit);
        sbloanTentur = (Spinner) findViewById(R.id.sbloanTentur);

    }

    protected View.OnClickListener datePickerDialog = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Utility.hideKeyBoard(view, BusinessLoanCalActivity.this);
            DateTimePicker.showDataPickerBuisnessLoan(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                    Calendar calendar = Calendar.getInstance();
                    calendar.set(year, monthOfYear, dayOfMonth);
                    String currentDay = simpleDateFormat.format(calendar.getTime());
                    et_dateOfIncorp.setText(currentDay);
                }
            });
        }

    };

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btn_submit) {

            if (validate()) {
                BuisnessLoanCalRequest buisCalreq = new BuisnessLoanCalRequest();
                buisCalreq.setApplicant_dob("1980-12-12");
                buisCalreq.setEmp_detail("2");
                buisCalreq.setTurnover("" + et_turnOver.getText().toString().trim());
                buisCalreq.setProfit_after_tax("" + et_profitAfterTax.getText().toString().trim());

                buisCalreq.setDepreciation("" + et_depreciation.getText().toString().trim());
                buisCalreq.setPartner_remuneration("" + et_dirPatnersRem.getText().toString().trim());
                buisCalreq.setInterest_paid("" + et_interestPaidLoan.getText().toString().trim());
                buisCalreq.setEmi("" + et_totEmiPaid.getText().toString().trim());

                buisCalreq.setNo_of_emi_paid("" + et_noOfEmiPaid.getText().toString().trim());
                buisCalreq.setLoan_tenure("" + sbloanTentur.getSelectedItem());
                buisCalreq.setLoan_amount("" + et_LoanAmnt.getText().toString().trim());
                buisCalreq.setDate("" + et_dateOfIncorp.getText().toString().trim());

                buisCalreq.setBank_Id("113");
                buisCalreq.setProductId("13");

//            Gson gson = new Gson();
//            String strObj = gson.toJson(buisCalreq);
//
//            Log.d("MYJSON", strObj);

                showProgressDialog();
                new EmicalculatorController(this).getBuisnessLoan(buisCalreq, this);
            }

        }
    }

    private boolean validate() {
        if (TextUtils.isEmpty(et_LoanAmnt.getText().toString().trim())) {

            et_LoanAmnt.setError("Please Enter Loan Amount.");
            et_LoanAmnt.requestFocus();
            return false;

        } else if (TextUtils.isEmpty(et_dateOfIncorp.getText().toString().trim())) {

            et_dateOfIncorp.setError("Please Enter Date Of Incorp.");
            et_dateOfIncorp.requestFocus();
            return false;

        }
//        else  if (TextUtils.isEmpty(et_loanTenture.getText().toString().trim())) {
//
//            et_loanTenture.setError("Please Enter Loan Tenure.");

//            et_loanTenture.requestFocus();
//            return false;
//
//        }else  if ((et_loanTenture.getText().toString().trim()).equals("0")) {
//
//            et_loanTenture.setError("Please Enter Required Tenure.");
//            et_loanTenture.requestFocus();
//            return false;
//
//        }
        else if (TextUtils.isEmpty(et_dateOfIncorp.getText().toString().trim())) {

            et_dateOfIncorp.setError("Please Enter Date Of Incorporation.");
            et_dateOfIncorp.requestFocus();
            return false;

        } else if (TextUtils.isEmpty(et_turnOver.getText().toString().trim())) {

            et_turnOver.setError("Please Enter Turnover.");
            et_turnOver.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(et_profitAfterTax.getText().toString().trim())) {

            et_profitAfterTax.setError("Please Enter Profit After Tax.");
            et_profitAfterTax.requestFocus();
            return false;

        } else if (TextUtils.isEmpty(et_depreciation.getText().toString().trim())) {

            et_depreciation.setError("Please Enter Depreciation.");
            et_depreciation.requestFocus();
            return false;

        } else if (TextUtils.isEmpty(et_interestPaidLoan.getText().toString().trim())) {

            et_interestPaidLoan.setError("Please Enter Interest Paid On Loans.");
            et_interestPaidLoan.requestFocus();
            return false;

        } else if (TextUtils.isEmpty(et_dirPatnersRem.getText().toString().trim())) {

            et_dirPatnersRem.setError("Please Enter Dir/Partners Remuneration.");
            et_dirPatnersRem.requestFocus();
            return false;

        } else if (TextUtils.isEmpty(et_totEmiPaid.getText().toString().trim())) {

            et_totEmiPaid.setError("Please Enter Total EMI Paid Currently.");
            et_totEmiPaid.requestFocus();
            return false;

        } else if (TextUtils.isEmpty(et_noOfEmiPaid.getText().toString().trim())) {

            et_noOfEmiPaid.setError("Please Enter Number Of EMI Paid.");
            et_noOfEmiPaid.requestFocus();
            return false;

        } else {
            return true;
        }
    }

    private void showDialogLoan(String amnt) {

        try {
            androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(BusinessLoanCalActivity.this, R.style.MyAlertDialogStyle);
            builder.setTitle("Business Loan Calculator");

            builder.setMessage("Loan Eligibility Is : " + amnt + "");

            String positiveText = getResources().getString(R.string.Ok);
            builder.setPositiveButton(positiveText,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();
                        }
                    });

            final androidx.appcompat.app.AlertDialog dialog = builder.create();
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();

        } catch (Exception ex) {
            Toast.makeText(this, "Please try again..", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void OnSuccess(APIResponse response, String message) throws InterruptedException {
        dismissDialog();
        if (response instanceof BuisnessLoanCalResponse) {
            if (response.getStatus() == "1") {
                buisnessLoanCalResponse = ((BuisnessLoanCalResponse) response);

                Intent intent = new Intent(BusinessLoanCalActivity.this, BuisnessCalcPOPUP.class);
                intent.putExtra(Utility.BUISNESS_EMI_CAL, buisnessLoanCalResponse.getData());
                intent.putExtra(Utility.MY_BUSISNESS_HDR, "Business Loan EMI Calculator");
                startActivity(intent);

            } else {
                Toast.makeText(BusinessLoanCalActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void OnFailure(Throwable t) {
        dismissDialog();
        // Snackbar.make(et_LoanAmount, t.getMessage(), Snackbar.LENGTH_SHORT).show();
    }

    private void loadSpinner() {

        //region Applicant Income Source Adapter

//        salaryTypeAdapter = new ArrayAdapter<String>(BusinessLoanCalActivity.this,
//                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.RelationType));
//        salaryTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        sbloanTentur.setAdapter(salaryTypeAdapter);
//        mspin=(Spinner) findViewById(R.id.spinner1);

        Integer[] items = new Integer[]{1, 2, 3, 4, 5};
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item, items);
        sbloanTentur.setAdapter(adapter);
    }
}
