package com.rupeeboss.rba.rbaddlead;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.rupeeboss.rba.R;
import android.app.DatePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;

import android.widget.Toast;
import com.rupeeboss.rba.BaseActivity;
import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.facade.LoginFacade;
import com.rupeeboss.rba.core.model.ProductsEntity;
import com.rupeeboss.rba.core.response.LeadResponse;
import com.rupeeboss.rba.core.response.PincodeResponse;
import com.rupeeboss.rba.core_loan_fm.controller.mainloan.MainLoanController;

import com.rupeeboss.rba.utility.DateTimePicker;
import com.rupeeboss.rba.utility.Utility;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.os.AsyncTask;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import java.util.Iterator;
import javax.net.ssl.HttpsURLConnection;

import static com.rupeeboss.rba.BaseFragment.isEmpty;
import static java.lang.System.in;


public class QuickleadActivity extends BaseActivity implements View.OnClickListener, IResponseSubcriber {

    EditText etFirstName,etLastName, etEmail, etMobile_Quick, etFollowupDate,  etLoanAmount, etRemark,
            etPincode, etCity, etState,etdob,etPAN,etCompanyName,etMonthlyIncomeITR,
            etyealyIncomeITR;
    Spinner spProduct,spCompanyType,spprofile;
    ArrayList<String>  arrayProduct;
    ArrayAdapter<String> productAdapter;
    Button btnSubmit;
    // ArrayList<String> arrayStatus, arrayProduct, arrayAssignee;
    //  ArrayAdapter<String> statusAdapter;ArrayAdapter<String> assigneeAdapter;
    ArrayAdapter<String> productTypeAdapter;
    ArrayAdapter<String> CompanyTypeAdapter;
    ArrayAdapter<String> profileAdapter;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");

    SimpleDateFormat simpleDateFormat_dob = new SimpleDateFormat("yyyy-MM-dd");
    TableRow tbl_monthly,tbl_yearly;
    WebView webView;
    String url = "";
    Boolean isDataUploaded = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quicklead);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        arrayProduct = new ArrayList<String>();


        initialize();
        loadSpinner();
    }

    protected View.OnClickListener datePickerDialog = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Utility.hideKeyBoard(view, QuickleadActivity.this);
            DateTimePicker.currentDateAndForward(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                    Calendar calendar = Calendar.getInstance();
                    calendar.set(year, monthOfYear, dayOfMonth);
                    String currentDay = simpleDateFormat.format(calendar.getTime());
                    etFollowupDate.setText(currentDay);
                    //etDate.setTag(R.id.et_date, calendar.getTime());
                }
            });
        }
    };
    protected View.OnClickListener datePickerdob = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Utility.hideKeyBoard(view, QuickleadActivity.this);
            DateTimePicker.showHealthAgeDatePicker(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                    Calendar calendar = Calendar.getInstance();
                    calendar.set(year, monthOfYear, dayOfMonth);
                    String currentDay = simpleDateFormat.format(calendar.getTime());
                    etdob.setText(currentDay);
                    //etDate.setTag(R.id.et_date, calendar.getTime());
                }
            });
        }
    };



    private void initialize() {
        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etLastName = (EditText) findViewById(R.id.etLastName);

        etdob = (EditText) findViewById(R.id.etdob);
        etPAN = (EditText) findViewById(R.id.etPAN);
        etCompanyName = (EditText) findViewById(R.id.etCompanyName);

        etMonthlyIncomeITR = (EditText) findViewById(R.id.etMonthlyIncomeITR);


        etyealyIncomeITR = (EditText) findViewById(R.id.etyealyIncomeITR);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etMobile_Quick = (EditText) findViewById(R.id.etMobile_Quick);
        etFollowupDate = (EditText) findViewById(R.id.etFollowupDate);
        etLoanAmount = (EditText) findViewById(R.id.etLoanAmount);
        etRemark = (EditText) findViewById(R.id.etRemark);

        tbl_monthly = (TableRow) findViewById(R.id.tbl_monthly);
        tbl_yearly = (TableRow) findViewById(R.id.tbl_yearly);
        tbl_monthly.setVisibility(View.GONE);
        tbl_yearly.setVisibility(View.GONE);

        spProduct = (Spinner) findViewById(R.id.spProduct);
        spCompanyType = (Spinner) findViewById(R.id.spCompanyType);
        spprofile = (Spinner) findViewById(R.id.spprofile);

        etPincode = (EditText) findViewById(R.id.etPincode);
        etPincode.addTextChangedListener(pincodeTextWatcher);

        etCity = (EditText) findViewById(R.id.etCity);
        etState = (EditText) findViewById(R.id.etState);

        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(this);

        etFollowupDate.setOnClickListener(datePickerDialog);
        etdob.setOnClickListener(datePickerdob);


        webView = findViewById(R.id.webView);
        url = "http://erp.rupeeboss.com/loansrepository/Loans-repository.html";
        settingWebview();



        etPincode= (EditText) findViewById(R.id.etPincode);
        etCity= (EditText) findViewById(R.id.etCity);
        etState= (EditText) findViewById(R.id.etState);
        etPincode.addTextChangedListener(pincodeTextWatcher);
    }

    private void loadSpinner() {
//        statusAdapter = new ArrayAdapter<String>(getActivity(),
//                android.R.layout.simple_spinner_item, getStatusList());
//        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spStatus.setAdapter(statusAdapter);

//        assigneeAdapter = new ArrayAdapter<String>(getActivity(),
//                android.R.layout.simple_spinner_item, getAssigneeList());
//
//        assigneeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spAssign.setAdapter(assigneeAdapter);

        productAdapter = new ArrayAdapter<String>(QuickleadActivity.this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.quicklead_product));//getProductList()
        productAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spProduct.setAdapter(productAdapter);

        CompanyTypeAdapter = new ArrayAdapter<String>(QuickleadActivity.this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.quicklead_Company_Type));
        CompanyTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCompanyType.setAdapter(CompanyTypeAdapter);

        profileAdapter = new ArrayAdapter<String>(QuickleadActivity.this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.quicklead_Profile));
        profileAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spprofile.setAdapter(profileAdapter);


        spprofile.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position ==0)
                {
                    tbl_monthly.setVisibility(View.GONE);
                    tbl_yearly.setVisibility(View.GONE);
                }
                else if(position ==1)
                {
                    tbl_monthly.setVisibility(View.VISIBLE);
                    tbl_yearly.setVisibility(View.GONE);
                }else if(position ==2 || position==3)
                {
                    tbl_monthly.setVisibility(View.GONE);
                    tbl_yearly.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private ArrayList<String> getProductList() {

        List<ProductsEntity> productEntities = new LoginFacade(QuickleadActivity.this).getProductList();
        for (ProductsEntity entity : productEntities) {
            arrayProduct.add(entity.getProdName());
        }

        return arrayProduct;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnSubmit) {
            Utility.hideKeyBoard(view, QuickleadActivity.this);

            if (!isEmpty(etFirstName)) {
                etFirstName.setError("Enter First Name");
                etFirstName.setFocusable(true);
                return;
            } else {
                etFirstName.setError(null);
            }
            if (!isEmpty(etLastName)) {
                etLastName.setError("Enter Last Name");
                etLastName.setFocusable(true);
                return;
            } else {
                etLastName.setError(null);
            }

            if (!isValidePhoneNumber(etMobile_Quick)) {
                etMobile_Quick.setError("Enter Mobile Number");
                etMobile_Quick.setFocusable(true);
                return;
            } else {
                etMobile_Quick.setError(null);
            }

            if (!isValideEmailID(etEmail)) {
                etEmail.setError("Invalid Email ID");
                etEmail.setFocusable(true);
                return;
            }
            if (!isEmpty(etdob)) {
                etdob.setError("Invalid Dob  date");
                etdob.setFocusable(true);
                return;
            } else {
                etdob.setError(null);
            }
            if (!isEmpty(etFollowupDate)) {
                etFollowupDate.setError("Invalid Follow up date");
                etFollowupDate.setFocusable(true);
                return;
            } else {
                etFollowupDate.setError(null);
            }
            if (!isEmpty(etPAN)) {
                etPAN.setError("Enter PAN Number");
                etPAN.setFocusable(true);
                return;
            } else {
                etPAN.setError(null);
            }
            if (!isValidPanValue(etPAN)) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                    etPAN.setError("Invalid PAN No.");
                    etPAN.setFocusable(true);
                    //   etPAN.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                    return;
                } else {
                    etPAN.setError("Invalid PAN No.");
                    etPAN.setFocusable(true);
                    return;
                }
            }
            if (spProduct.getSelectedItemPosition() == 0) {
                Toast.makeText(QuickleadActivity.this, "Select product", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!isEmpty(etLoanAmount)) {
                etLoanAmount.setError("Enter Loan Amount");
                etLoanAmount.setFocusable(true);
                return;
            } else {
                etLoanAmount.setError(null);
            }
            if (!isEmpty(etCompanyName)) {
                etCompanyName.setError("Enter Company Name");
                etCompanyName.setFocusable(true);
                return;
            } else {
                etCompanyName.setError(null);
            }
            if (spCompanyType.getSelectedItemPosition() == 0) {
                Toast.makeText(QuickleadActivity.this, "Select Company Type", Toast.LENGTH_SHORT).show();
                return;
            }
            if (spprofile.getSelectedItemPosition() == 0) {
                Toast.makeText(QuickleadActivity.this, "Select Profile", Toast.LENGTH_SHORT).show();
                return;
            }
            if (spprofile.getSelectedItem().toString().equals("Salaried"))
            {
//                if (!isEmpty(etMonthlyIncome)) {
//                    etMonthlyIncome.setError("Enter Monthly Obligation");
//                    etMonthlyIncome.setFocusable(true);
//                    return;
//                } else {
//                    etMonthlyIncome.setError(null);
//                }

                if (!isEmpty(etMonthlyIncomeITR)) {
                    etMonthlyIncomeITR.setError("Enter Monthly Income ITR");
                    etMonthlyIncomeITR.setFocusable(true);
                    return;
                } else {
                    etMonthlyIncomeITR.setError(null);
                }

            }else {
//                if (!isEmpty(etMonthlyIncomeYealy)) {
//                    etMonthlyIncomeYealy.setError("Enter Yealy Obligation");
//                    etMonthlyIncomeYealy.setFocusable(true);
//                    return;
//                } else {
//                    etMonthlyIncomeYealy.setError(null);
//                }

                if (!isEmpty(etyealyIncomeITR)) {
                    etyealyIncomeITR.setError("Enter Yealy Income ITR");
                    etyealyIncomeITR.setFocusable(true);
                    return;
                } else {
                    etyealyIncomeITR.setError(null);
                }
            }
            if (!isEmpty(etPincode)) {
                etPincode.setError("Enter Pincode");
                etPincode.setFocusable(true);
                return;
            } else {
                etPincode.setError(null);
            }
            if (etPincode.getText().length() == 6) {
            }else
            {
                etPincode.setError("Enter Six Digit Pincode");
                etPincode.setFocusable(true);
                return;
            }
            if (!isEmpty(etRemark)) {
                etRemark.setError("Enter Remark");
                etRemark.setFocusable(true);
                return;
            } else {
                etRemark.setError(null);
            }


      //    new QuickleadActivity.SendRequest().execute();
            ConnectivityManager connMgr = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                MyAsync myAsync = new MyAsync();
                myAsync.execute();
            } else {
                AlertDialog.Builder builder = new AlertDialog.
                        Builder(QuickleadActivity.this);
                builder.setTitle("Alert!");
                builder.setMessage("Please check your network connection");
                builder.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        });
                builder.create().show();
            }
        }
    }

    @Override
    public void OnSuccess(APIResponse response, String message) throws InterruptedException {
        cancelDialog();
        if (response instanceof PincodeResponse) {
            if (response.getStatus_Id() == 0) {
                try {
                    if ((((PincodeResponse) response).getMasterData().getState_name()) != null) {
                        etState.setText("" + ((PincodeResponse) response).getMasterData().getState_name());
                        etCity.setText("" + ((PincodeResponse) response).getMasterData().getCityname());
                    } else
                    {
                        etPincode.setText("");
                        etState.setText("");
                        etCity.setText("");
                    }
                }catch (Exception ex) {
                    Toast.makeText(QuickleadActivity.this, "No Data", Toast.LENGTH_SHORT).show();
                    etState.setText("");
                    etCity.setText("");
                    etPincode.setText("");

                }

            } else {
                etPincode.setText("");
                etState.setText("");
                etCity.setText("");

            }
        }else
        if (response instanceof LeadResponse) {
            if (response.getStatusId() == 0) {
                etEmail.setText("");
                etMobile_Quick.setText("");
                //  etLoanAmt.setText("");
                //  etMonthlyIncome.setText("");
                etRemark.setText("");
                //  etName.setText("");
            }
            Snackbar.make(etRemark, response.getMessage(), Snackbar.LENGTH_SHORT).show();
            dialogMessage(true, "getLead_Id()", response.getMessage());

        }
    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
        dialogMessage(false, t.getMessage(), "");

    }

    private void dialogMessage(final boolean isSuccess, String AppNo, String displayMessage) {

        AlertDialog.Builder builder = new AlertDialog.Builder(QuickleadActivity.this);
        builder.setCancelable(false);

        StringBuilder Message = new StringBuilder();
        if (isSuccess) {
            builder.setTitle("Lead Saved..!");
            String strMessage = "Lead ID:" + AppNo + "\n\n";
            String success = displayMessage;
            Message.append(strMessage + success);

        } else {
            builder.setTitle("Failed");
            String failure = AppNo;
            Message.append(failure);
        }
        builder.setMessage(Message.toString())
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        if (isSuccess) {
                            QuickleadActivity.this.finish();
                        }
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
        TextView msgTxt = (TextView) dialog.findViewById(android.R.id.message);
        msgTxt.setTextSize(14.0f);
    }
    //region textwatcher
    TextWatcher pincodeTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            if (start == 5) {
                etCity.setText("");
                etState.setText("");
            }
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if ((s.length() == 6) && (isDataUploaded)) {
                showDialogQuicklead("Fetching City...");
                Toast.makeText(QuickleadActivity.this, "Fetching City...Data", Toast.LENGTH_SHORT).show();
                new MainLoanController(QuickleadActivity.this).getCityState(etPincode.getText().toString(),QuickleadActivity.this);

            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


    private void settingWebview() {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);

        settings.setBuiltInZoomControls(false);
        settings.setUseWideViewPort(false);
        settings.setJavaScriptEnabled(true);
        settings.setSupportMultipleWindows(false);

        settings.setLoadsImagesAutomatically(true);
        settings.setLightTouchEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setJavaScriptEnabled(true);


        /*MyWebViewClient webViewClient = new MyWebViewClient(this);
        webView.setWebViewClient(webViewClient);*/
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                // TODO show you progress image
                showDialog();
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                // TODO hide your progress image
                cancelDialog();
                super.onPageFinished(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.endsWith(".pdf")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.parse(url), "application/pdf");
                    try {
                        startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        //user does not have a pdf viewer installed
                        String googleDocs = "https://docs.google.com/viewer?url=";
                        webView.loadUrl(googleDocs + url);
                    }
                }
                return false;
            }
        });
        webView.getSettings().setBuiltInZoomControls(true);
        Log.d("URL", url);
        //webView.loadUrl("http://drive.google.com/viewerng/viewer?embedded=true&url=" + url);
        webView.loadUrl(url);
    }



    //Http Call


    public class SendRequest extends AsyncTask<String, Void, String> {
        JSONObject postDataParams = new JSONObject();
        protected void onPreExecute(){
            try {
                postDataParams.put("Name", "" + etFirstName.getText()+" " + etLastName.getText());
                postDataParams.put("brokerId", ""+new LoginFacade(QuickleadActivity.this).getUser().getBrokerId());//new LoginFacade(getActivity()).getUser().getBrokerId()
                postDataParams.put("EMail", etEmail.getText().toString());
                postDataParams.put("FBA_Id","" +0);//new LoginFacade(getActivity()).getUser().getBrokerId()
                postDataParams.put("followupDate", etFollowupDate.getText().toString());
                postDataParams.put("DOB", etdob.getText().toString());
                postDataParams.put("Status", "43");
                postDataParams.put("PAN", etPAN.getText().toString());
                postDataParams.put("Loan_amt", etLoanAmount.getText().toString());
                postDataParams.put("Mobile", etMobile_Quick.getText().toString());

                if (spprofile.getSelectedItem().equals("Salaried")) {
                    postDataParams.put("Monthly_income", etMonthlyIncomeITR.getText().toString());
                } else {
                    postDataParams.put("Monthly_income", etyealyIncomeITR.getText().toString());
                }

                postDataParams.put("ProductId",""+ spProduct.getSelectedItemPosition()); //String.valueOf(spProduct.getSelectedItemPosition()));
                postDataParams.put("CompanyName", etCompanyName.getText().toString());
                postDataParams.put("CompanyType", String.valueOf(spCompanyType.getSelectedItem()));
                postDataParams.put("Profile", String.valueOf(spprofile.getSelectedItem()));
                postDataParams.put("Remark", etRemark.getText().toString());


                postDataParams.put("Pincode", "" + etPincode.getText().toString());
                postDataParams.put("City", "" + etCity.getText().toString());
                postDataParams.put("State", etState.getText().toString());
                postDataParams.put("Source", "RBA");
                postDataParams.put("Client_Source", "RBA");
                Log.e("params", postDataParams.toString());
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }

        protected String doInBackground(String... arg0) {

            try{

                URL url = new URL("http://bankapi.rupeeboss.com/BankAPIService.svc/createOtherLoanLeadReq");


                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                //  String userCredentials = "token:1234567890";
                // String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userCredentials.getBytes()));
                //   String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userCredentials.getBytes()));


                conn.setReadTimeout(15000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);

                // conn.setRequestProperty ("token",  "1234567890"); //for finmart

                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
             //   writer.write(getPostDataString(postDataParams));
                writer.write(postDataParams.toString());
                writer.flush();
                writer.close();
                os.close();

                int responseCode=conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {

                    BufferedReader in=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuffer sb = new StringBuffer("");
                    String line="";

                    while((line = in.readLine()) != null) {

                        sb.append(line);
                        break;
                    }

                    in.close();
                    return sb.toString();

                }
                else {
                    return new String("false : "+responseCode);
                }
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(QuickleadActivity.this, result,
                    Toast.LENGTH_SHORT).show();

            Gson obgson = new Gson();


                etEmail.setText("");
            etMobile_Quick.setText("");
                //  etLoanAmt.setText("");
                //  etMonthlyIncome.setText("");
                etRemark.setText("");
                //  etName.setText("");

            Snackbar.make(etRemark, "msg", Snackbar.LENGTH_SHORT).show();
          //  dialogMessage(true, "getLead_Id()", "msg");

//            insuranceuriResponse cl= obgson.fromJson(result, insuranceuriResponse.class);
//
//            insuranceuriResponse.MasterDataBean bean = cl.getMasterData();
//
//            motorurl=bean.getFourWheelerUrl();
//            twowheelerurl=bean.getTwoWheelerUrl();
//            healthurl=bean.getHealthurl();


//            JSONArray arr = null;
//                arr = new JSONArray(result);
//
//            JSONObject jObj = null;
//                jObj = arr.getJSONObject(0);
//
//               String MasterData = jObj.getString("MasterData");
//


        }
    }

    public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while(itr.hasNext()){

            String key= itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }
        return result.toString();
    }

    public class MyAsync extends AsyncTask<String, Void, String>  {
        JSONObject postDataParams = new JSONObject();
        ProgressDialog progressDialog;

        protected void onPreExecute(){
            try {
                postDataParams.put("Name", "" + etFirstName.getText()+" " + etLastName.getText());
                postDataParams.put("brokerId", ""+new LoginFacade(QuickleadActivity.this).getUser().getBrokerId());//new LoginFacade(getActivity()).getUser().getBrokerId()
                postDataParams.put("EMail", etEmail.getText().toString());
                postDataParams.put("FBA_Id","" +0);//new LoginFacade(getActivity()).getUser().getBrokerId()
                postDataParams.put("followupDate", etFollowupDate.getText().toString());
                postDataParams.put("DOB", etdob.getText().toString());
                postDataParams.put("Status", "43");
                postDataParams.put("PAN", etPAN.getText().toString());
                postDataParams.put("Loan_amt", etLoanAmount.getText().toString());
                postDataParams.put("Mobile", etMobile_Quick.getText().toString());

                if (spprofile.getSelectedItem().equals("Salaried")) {
                    postDataParams.put("Monthly_income", etMonthlyIncomeITR.getText().toString());
                } else {
                    postDataParams.put("Monthly_income", etyealyIncomeITR.getText().toString());
                }

                postDataParams.put("ProductId",""+ spProduct.getSelectedItemPosition()); //String.valueOf(spProduct.getSelectedItemPosition()));
                postDataParams.put("CompanyName", etCompanyName.getText().toString());
                postDataParams.put("CompanyType", String.valueOf(spCompanyType.getSelectedItem()));
                postDataParams.put("Profile", String.valueOf(spprofile.getSelectedItem()));
                postDataParams.put("Remark", etRemark.getText().toString());


                postDataParams.put("Pincode", "" + etPincode.getText().toString());
                postDataParams.put("City", "" + etCity.getText().toString());
                postDataParams.put("State", etState.getText().toString());
                postDataParams.put("Source", "RBA");
                postDataParams.put("Client_Source", "RBA");
                Log.e("params", postDataParams.toString());

                progressDialog = ProgressDialog.show(QuickleadActivity.this, "downloading", "please wait");
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }

        //Perform the request in background
        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection connection;
            try {
                //Open a new URL connection
                connection = (HttpURLConnection) new URL("http://bankapi.rupeeboss.com/BankAPIService.svc/createOtherLoanLeadReq")
                        .openConnection();

                //Defines a HTTP request type
                connection.setRequestMethod("POST");

                //Sets headers: Content-Type, Authorization
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("Accept", "application/json");
              //  connection.setRequestProperty("Authorization", "Token fab11c9b6bd4215a989c5bf57eb678");

                //Add POST data in JSON format
//                JSONObject jsonParam = new JSONObject();
//                try {
//                    jsonParam.put("campaign_id", "4193");
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }

                //Create a writer object and make the request
                OutputStreamWriter outputStream = new OutputStreamWriter(connection.getOutputStream());
                outputStream.write(postDataParams.toString());
                outputStream.flush();
                outputStream.close();

                //Get the Response code for the request
             //   return ""+connection.getResponseCode();
                int responseCode=connection.getResponseCode();
                if (responseCode == HttpsURLConnection.HTTP_OK) {


                    BufferedReader in=new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuffer sb = new StringBuffer("");
                    String line="";

                    while((line = in.readLine()) != null) {

                        sb.append(line);
                        break;
                    }

                    in.close();
                    return sb.toString();

                }
                else {
                    return new String("false : "+responseCode);
                }
            } catch (IOException e) {
                return new String("Exception: " + e.getMessage());

            }
           // return "-1";
        }

        //Run this once the background task returns.

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(QuickleadActivity.this, result,
                    Toast.LENGTH_LONG).show();
            progressDialog.dismiss();
        //    JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();

            String  str =result.substring(1);

                String result2 = null;
                if ((str != null) && (str.length() > 0)) {
                    result2 = str.substring(0, str.length() - 1);
                }
            try {
             String s=   result.substring(1, result.length()-1);
                    // get JSONObject from JSON file
                String g =result2.replace("\"","");


               // JSONObject obj3 = new JSONObject(g);
               // JSONObject ob4 = new JSONObject(s);

                    // fetch JSONObject named employee



                    // get employee name and salary





            } catch (Throwable t) {
                Log.e("My App", "Could not parse malformed JSON: \"" + result + "\"");
            }
          //  Gson obgson = new Gson();

          //  String json = obgson.toJson(result);

        //    etEmail.setText("");
        //    etMobile_Quick.setText("");
            //  etLoanAmt.setText("");
            //  etMonthlyIncome.setText("");
        //    etRemark.setText("");
            //  etName.setText("");

            Snackbar.make(etRemark, "msg", Snackbar.LENGTH_SHORT).show();
            dialogMessage(true,  result, "msg");

//            insuranceuriResponse cl= obgson.fromJson(result, insuranceuriResponse.class);
//
//            insuranceuriResponse.MasterDataBean bean = cl.getMasterData();
//
//            motorurl=bean.getFourWheelerUrl();
//            twowheelerurl=bean.getTwoWheelerUrl();
//            healthurl=bean.getHealthurl();


//            JSONArray arr = null;
//                arr = new JSONArray(result);
//
//            JSONObject jObj = null;
//                jObj = arr.getJSONObject(0);
//
//               String MasterData = jObj.getString("MasterData");
//


        }
    }
}
