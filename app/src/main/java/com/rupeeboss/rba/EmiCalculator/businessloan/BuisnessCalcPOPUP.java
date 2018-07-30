package com.rupeeboss.rba.EmiCalculator.businessloan;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.rupeeboss.rba.BaseActivity;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.model.BuisnessLoanCalEntity;
import com.rupeeboss.rba.utility.Utility;

import java.math.BigDecimal;

public class BuisnessCalcPOPUP extends BaseActivity implements View.OnClickListener {

    BuisnessLoanCalEntity busEntity;
    TextView textViewloanamount, textViewfee,tvtotalemi,tvtotalroi,txtHdr;
    Button btnClose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buisness_calc_popup);

        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setFinishOnTouchOutside(false);
        initialize();
        if(getIntent().hasExtra(Utility.BUISNESS_EMI_CAL)){
            busEntity = getIntent().getParcelableExtra(Utility.BUISNESS_EMI_CAL);
            String strHdr = getIntent().getStringExtra(Utility.MY_BUSISNESS_HDR);
            txtHdr.setText(strHdr);
            bindBuisnessCalData();
        }
    }

    private void initialize() {
        textViewloanamount = (TextView) findViewById(R.id.textViewloanamount);
        textViewfee = (TextView) findViewById(R.id.textViewfee);

        tvtotalemi = (TextView) findViewById(R.id.tvtotalemi);
        tvtotalroi = (TextView) findViewById(R.id.tvtotalroi);
        txtHdr = (TextView) findViewById(R.id.txtHdr);
        btnClose = (Button) findViewById(R.id.btnClose);
        btnClose.setOnClickListener(this);
    }


    private void bindBuisnessCalData()
    {
        if( busEntity.getLoan_eligible()==null)
        {
            textViewloanamount.setText("0");
        }else if(busEntity.getLoan_eligible().trim().equals(""))
        {
            textViewloanamount.setText("0");
        }else {
            textViewloanamount.setText("" +   Math.round(Double.parseDouble(busEntity.getLoan_eligible())));
        }

      //  textViewloanamount.setText("" + busEntity.getLoan_eligible());
        textViewfee.setText("" +  BigDecimal.valueOf(busEntity.getProcessingfee()).toPlainString());
        tvtotalemi.setText("" +  BigDecimal.valueOf(busEntity.getEmi()).toPlainString());
        tvtotalroi.setText("" + busEntity.getRoi());
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.btnClose)
        {
            this.finish();
        }
    }
}
