package com.rupeeboss.rba.businessloan;

import android.app.DatePickerDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import androidx.appcompat.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
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
import com.rupeeboss.rba.core.response.BLQuoteResponse;
import com.rupeeboss.rba.utility.DateTimePicker;
import com.rupeeboss.rba.utility.Utility;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BLActivity extends BaseActivity implements View.OnClickListener, IResponseSubcriber {

    EditText et_dateOfIncorp, et_turnOver, et_profitAfterTax, et_depreciation, et_interestPaidLoan, et_dirPatnersRem, et_totEmiPaid, et_LoanAmnt, et_noOfEmiPaid;
    Button btn_submit;
    BLQuoteResponse blQuoteResponse;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Spinner sbloanTentur;
    BuisnessLoanCalRequest buisCalreq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bl);
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
            Utility.hideKeyBoard(view, BLActivity.this);
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

                buisCalreq = new BuisnessLoanCalRequest();
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
                showProgressDialog();
                new EmicalculatorController(this).getBLQuotes(buisCalreq, this);
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

    @Override
    public void OnSuccess(APIResponse response, String message) throws InterruptedException {
        dismissDialog();
        if (response instanceof BLQuoteResponse) {
            if (response.getStatusId() == 1) {
                blQuoteResponse = ((BLQuoteResponse) response);
                if (((BLQuoteResponse) response).getData() != null || !((BLQuoteResponse) response).getData().equals("")) {
                    Intent intent = new Intent(BLActivity.this, BLQuoteActivity.class);
                    intent.putExtra("BL_RESPONSE", blQuoteResponse);
                    intent.putExtra("BL_REQUEST", buisCalreq);
                    startActivity(intent);
                } else {
                    Toast.makeText(BLActivity.this, message, Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(BLActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void OnFailure(Throwable t) {
        dismissDialog();
        Toast.makeText(BLActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.btnMenuShare:
                boolean result = Utility.checkPermission(BLActivity.this);
                if (result) {
                    shareData();

                }
                return true;
            case R.id.btnAddLead:
                /*startActivity(new Intent(BLActivity.this, RbAddLeadActivity.class)
                        .putExtra(Constants.HL_REQUEST, homeLoanRequest));*/
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void shareData() {
        View rootView = getWindow().getDecorView().findViewById(R.id.rvQuotes);

        datashareList(getScreenShot(rootView), "Quotes Details", "");

    }


    public Bitmap getScreenShot(View view) {
        View screenView = view.getRootView();
        screenView.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(screenView.getDrawingCache());
        screenView.setDrawingCacheEnabled(false);
        return bitmap;
    }


    private void datashareList(Bitmap bitmap, String strSubject, String strDetail) {

        String prdSubject = strSubject;
        String prdDetail = strDetail;
        try {

            //  File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "Screenshots" + System.currentTimeMillis() + ".png");

            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Screenshots", Utility.getCurrentMobileDateTime() + ".png");
            // Utility.getCurrentMobileDateTime()
            file.getParentFile().mkdirs();
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.close();

            Uri screenshotUri = Uri.fromFile(file);

            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_TEXT, prdDetail);

            shareIntent.setType("text/plain");

            PackageManager pm = BLActivity.this.getPackageManager();


            List<ResolveInfo> resInfo = pm.queryIntentActivities(shareIntent, 0);
            List<LabeledIntent> intentList = new ArrayList<LabeledIntent>();
            ///////////
            for (int i = 0; i < resInfo.size(); i++) {
                // Extract the label, append it, and repackage it in a LabeledIntent
                ResolveInfo ri = resInfo.get(i);
                String packageName = ri.activityInfo.packageName;
                String processName = ri.activityInfo.processName;
                String AppName = ri.activityInfo.name;

                if ((packageName.contains("android.email") || packageName.contains("twitter") || (packageName.contains("whatsapp")) || packageName.contains("android.gm") || packageName.contains("com.google.android.apps.plus")) || (packageName.contains("apps.docs")) && processName.contains("android.apps.docs:Clipboard") || (packageName.contains("android.talk")) && AppName.contains("hangouts")) {

                    shareIntent.setComponent(new ComponentName(packageName, ri.activityInfo.name));

                    if (packageName.contains("android.email")) {
                        shareIntent.setType("image/*");
                        shareIntent.putExtra(Intent.EXTRA_SUBJECT, prdSubject);
                        shareIntent.putExtra(Intent.EXTRA_TEXT, prdDetail);
                        shareIntent.setPackage(packageName);

                    } else if (packageName.contains("twitter")) {

                        shareIntent.setType("image/*");
                        shareIntent.putExtra(Intent.EXTRA_SUBJECT, prdSubject);
                        shareIntent.putExtra(Intent.EXTRA_TEXT, prdDetail);
                        shareIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
                        shareIntent.setPackage(packageName);

                    }
//                    else if (packageName.contains("facebook.katana")) {
//                        shareIntent.setType("text/plain");
//                        shareIntent.putExtra(Intent.EXTRA_TEXT, product.getImageUrl());
//                        shareIntent.setPackage("com.facebook.katana");
//                        //shareIntent.putExtra(Intent.EXTRA_STREAM, Deeplink);
//                    }
//                    else if (packageName.contains("facebook.orca")) {
//                        shareIntent.setType("image/*");
//                        shareIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
//                        shareIntent.putExtra(Intent.EXTRA_TEXT, prdDetail);
//                        shareIntent.setPackage("com.facebook.orca");
//
//                    }

                    else if (packageName.contains("whatsapp")) {
                        shareIntent.setType("image/*");

                        shareIntent.putExtra(Intent.EXTRA_TEXT, prdDetail);
                        shareIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
                        shareIntent.setPackage(packageName);


                    } else if (packageName.contains("com.google.android.apps.plus")) {
                        shareIntent.setType("image/*");
                        shareIntent.putExtra(Intent.EXTRA_TEXT, prdDetail);
                        shareIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
                        shareIntent.setPackage(packageName);

                    } else if (packageName.contains("android.talk")) {
                        if (AppName.contains("hangouts")) {
                            shareIntent.setType("image/*");
                            shareIntent.putExtra(Intent.EXTRA_TEXT, prdDetail);
                            shareIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
                            shareIntent.setPackage(packageName);
                        }

                    } else if (packageName.contains("apps.docs")) {
                        if (processName.contains("android.apps.docs:Clipboard")) {
                            shareIntent.setType("text/plain");
                            shareIntent.putExtra(Intent.EXTRA_TEXT, prdDetail);
                            shareIntent.setPackage(packageName);
                        }

                    } else if (packageName.contains("android.gm")) {
                        shareIntent.setType("image/*");
                        shareIntent.putExtra(Intent.EXTRA_SUBJECT, prdSubject);
                        shareIntent.putExtra(Intent.EXTRA_TEXT, prdDetail);
                        shareIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
                        shareIntent.setPackage(packageName);

                    } else {
                        shareIntent.setType("text/plain");
                        shareIntent.putExtra(Intent.EXTRA_TEXT, prdDetail);

                    }

                    intentList.add(new LabeledIntent(shareIntent, packageName, ri.loadLabel(pm), ri.icon));

                }
            }


            if (intentList.size() > 1) {
                intentList.remove(intentList.size() - 1);
            }

            Intent openInChooser = Intent.createChooser(shareIntent, "Share Via");

            // convert intentList to array
            LabeledIntent[] extraIntents = intentList.toArray(new LabeledIntent[intentList.size()]);
            openInChooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, extraIntents);

            startActivity(openInChooser);
        } catch (Exception e) {

            // Toast.makeText(getActivity(), "Please check your permissions settings.Permission issue.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    shareData();
                } else {
                    //code for deny
                }
                break;
        }
    }
}
