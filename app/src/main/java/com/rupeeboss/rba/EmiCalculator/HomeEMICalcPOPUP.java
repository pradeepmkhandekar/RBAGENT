package com.rupeeboss.rba.EmiCalculator;

import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rupeeboss.rba.BaseActivity;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.controller.Emicalculator.EmiRecalculationController;

import com.rupeeboss.rba.core.model.EmiHomeCalcuatorEntity;

import com.rupeeboss.rba.core.response.EmiRecalculationResponse;
import com.rupeeboss.rba.utility.Constants;
import com.rupeeboss.rba.utility.Utility;

import java.math.BigDecimal;

public class HomeEMICalcPOPUP extends BaseActivity implements View.OnClickListener,IResponseSubcriber {

    EmiRecalculationResponse emiRecalculationResponse;
    EmiHomeCalcuatorEntity emiEntity;

    TextView textViewdrop_in_int_new, textViewdrop_emi_new,textViewloaninterest,textViewafter_savings,textemi;

    TextView textViewloanamount, textViewfee,tvtotalemi,txtHdr;
    Button btnClose;
    ImageButton buttonrecalc;
    EditText ettotalroi;
    LinearLayout layoutresuledit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_emicalc_popup);

        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setFinishOnTouchOutside(false);
        initialize();

        if (getIntent().hasExtra(Utility.HOME_EMI_CAL)) {

            emiEntity = getIntent().getParcelableExtra(Utility.HOME_EMI_CAL);
            String strHdr = getIntent().getStringExtra(Utility.MY_BUSISNESS_HDR);
            txtHdr.setText(strHdr);
            bindData();
        }


    }

    private void initialize() {
        textViewloanamount = (TextView) findViewById(R.id.textViewloanamount);
        textViewfee = (TextView) findViewById(R.id.textViewfee);
        tvtotalemi = (TextView) findViewById(R.id.tvtotalemi);
        txtHdr = (TextView) findViewById(R.id.txtHdr);
        ettotalroi= (EditText) findViewById(R.id.ettotalroi);
        buttonrecalc=(ImageButton) findViewById(R.id.buttonrecalc);

        textViewafter_savings = (TextView) findViewById(R.id.textViewafter_savings);
        textViewloaninterest = (TextView) findViewById(R.id.textViewloaninterest);
        textViewdrop_emi_new = (TextView) findViewById(R.id.textViewdrop_emi_new);
        textViewdrop_in_int_new = (TextView) findViewById(R.id.textViewdrop_in_int_new);
        textemi = (TextView) findViewById(R.id.textemi);

        layoutresuledit= (LinearLayout) findViewById(R.id.layoutresuledit);
        btnClose = (Button) findViewById(R.id.btnClose);
        btnClose.setOnClickListener(this);
        buttonrecalc.setOnClickListener(this);
    }

    private void bindData() {
        if( emiEntity.getLoan_eligible()==null)
        {
            textViewloanamount.setText("0");
        }else if(emiEntity.getLoan_eligible().trim().equals(""))
        {
            textViewloanamount.setText("0");
        }else {
            textViewloanamount.setText("" +   Math.round(Double.parseDouble(emiEntity.getLoan_eligible())));
        }
        textViewfee.setText("" +  BigDecimal.valueOf(emiEntity.getProcessingfee()).toPlainString());
        tvtotalemi.setText("" +  BigDecimal.valueOf(emiEntity.getEmi()).toPlainString());

        ettotalroi.setText("" +  Double.parseDouble(emiEntity.getRoi()));
//        if(emiEntity.getRoi()==null){
//            ettotalroi.setText("0" );
//        }else if(emiEntity.getLoan_eligible().trim().equals("")) {
//            ettotalroi.setText("0" );
//        }else {
//
//
//        }

    }

    private void bindBuisnessCalData()
    {
//        textViewloanamount.setText("" + busEntity.getLoan_eligible());
//        textViewfee.setText("" +  BigDecimal.valueOf(busEntity.getProcessingfee()).toPlainString());
//        tvtotalemi.setText("" +  BigDecimal.valueOf(busEntity.getEmi()).toPlainString());
        //tvtotalroi.setText("" + busEntity.getRoi());
    }

    @Override
    public void onClick(View v) {

        Constants.hideKeyBoard(textViewloanamount,HomeEMICalcPOPUP.this);
        if(v.getId() == R.id.btnClose)
        {
            this.finish();
        }
        if(v.getId() == R.id.buttonrecalc)
        {
            layoutresuledit.setVisibility(v.VISIBLE);
            String ettotalroi1= ettotalroi.getText().toString();
            String roiold= emiEntity.getRoi();
            String loanamount= emiEntity.getLoanRequired().toString();
            String loantensure= ""+ (emiEntity.getLoanTenure());
            layoutresuledit.setVisibility(v.VISIBLE);
            if (TextUtils.isEmpty(ettotalroi1)) {

                ettotalroi.setError("Please Enter ROI.");
                ettotalroi.requestFocus();
                return;

            }

            showProgressDialog();
            new EmiRecalculationController(HomeEMICalcPOPUP.this).getEmiRecalculationdata(loanamount,ettotalroi.getText().toString(),loantensure,roiold,this);
        }
    }


    @Override
    public void OnSuccess(APIResponse response, String message) throws InterruptedException {
        dismissDialog();
        if (response instanceof EmiRecalculationResponse) {
            if (((EmiRecalculationResponse) response).getSuccess() == 1) {
                emiRecalculationResponse = ((EmiRecalculationResponse) response);
                if (((EmiRecalculationResponse) response).getSuccess() != 1) {
                    Toast.makeText(HomeEMICalcPOPUP.this, message, Toast.LENGTH_SHORT).show();
                }else {

                    textViewafter_savings.setText("" +  BigDecimal.valueOf(emiRecalculationResponse.getData().getAfter_savings()).toPlainString());
                    textViewloaninterest.setText("" +  BigDecimal.valueOf(emiRecalculationResponse.getData().getLoaninterest()).toPlainString());
                    textViewdrop_emi_new.setText("" +  BigDecimal.valueOf(emiRecalculationResponse.getData().getDrop_emi_new()).toPlainString());
                    textViewdrop_in_int_new.setText("" +  BigDecimal.valueOf(emiRecalculationResponse.getData().getDrop_in_int_new()).toPlainString());
                    textemi.setText("" +  BigDecimal.valueOf(emiRecalculationResponse.getData().getEmi()).toPlainString());
                }
            } else {
                Toast.makeText(HomeEMICalcPOPUP.this,message, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void OnFailure(Throwable t) {
        dismissDialog();
        // startActivity(new Intent(HomeLoanActivity.this, QuoteActivity.class).putParcelableArrayListExtra(Constants.QUOTES, (ArrayList<QuoteEntity>) quoteEntities));
        Toast.makeText(HomeEMICalcPOPUP.this, t.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
