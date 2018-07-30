package com.rupeeboss.rba.quotes;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rupeeboss.rba.BaseActivity;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.model.BLQuoteEntity;
import com.rupeeboss.rba.core.model.QuoteEntity;


public class QuoteInfoActivity extends BaseActivity {

    QuoteEntity quoteEntity;
    BLQuoteEntity blQuoteEntity;
    ImageView imgBankLogo;
    TextView txtPartpaymentfixed, txtPrecloserFixed, txtTopup, txtBalancePeriod,
            txtLockInPeriod, txtWomenROI, txtLoanRequired, txtProcessingFees,
            txtBankName, txtROI, txtNetIncome, txtEmi, txtLoanTenure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quoteinfo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Quote information");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        initialise_widgets();

        if (getIntent().hasExtra("QUOTEINFO")) {
            quoteEntity = getIntent().getParcelableExtra("QUOTEINFO");
            // Toast.makeText(this, "Quotes " + quoteEntity.getBalance_Transfer(), Toast.LENGTH_SHORT).show();
            bindQuote();
        } else if (getIntent().hasExtra("BL_QUOTE")) {
            blQuoteEntity = getIntent().getParcelableExtra("BL_QUOTE");
            bindBLQuotes();
        }
    }

    private void bindBLQuotes() {
        Glide.with(this)
                .load(blQuoteEntity.getBank_Logo())
                .into(imgBankLogo);
        txtPartpaymentfixed.setText(blQuoteEntity.getPart_Pmt_Fixed());
        txtPrecloserFixed.setText(blQuoteEntity.getPre_Closer_Fixed());
        txtTopup.setText(blQuoteEntity.getTop_up());
        txtBalancePeriod.setText(blQuoteEntity.getBalance_Transfer());
        txtLockInPeriod.setText(blQuoteEntity.getLock_In_Period());
        txtWomenROI.setText(blQuoteEntity.getWomen_roi());
        txtLoanRequired.setText(blQuoteEntity.getLoanRequired());
        txtProcessingFees.setText("" + blQuoteEntity.getProcessingfee());
        txtBankName.setText(blQuoteEntity.getBank_Name());
        txtROI.setText(blQuoteEntity.getRoi());
        //txtNetIncome.setText(blQuoteEntity.getNetincome());
        txtEmi.setText("" + blQuoteEntity.getEmi());
        txtLoanTenure.setText("" + blQuoteEntity.getLoanTenure());
    }

    private void bindQuote() {
        Glide.with(this)
                .load(quoteEntity.getBank_Logo())
                .into(imgBankLogo);
        txtPartpaymentfixed.setText(quoteEntity.getPart_Pmt_Fixed());
        txtPrecloserFixed.setText(quoteEntity.getPre_Closer_Fixed());
        txtTopup.setText(quoteEntity.getTop_up());
        txtBalancePeriod.setText(quoteEntity.getBalance_Transfer());
        txtLockInPeriod.setText(quoteEntity.getLock_In_Period());
        txtWomenROI.setText(quoteEntity.getWomen_roi());
        txtLoanRequired.setText(quoteEntity.getLoanRequired());
        txtProcessingFees.setText("" + quoteEntity.getProcessingfee());
        txtBankName.setText(quoteEntity.getBank_Name());
        txtROI.setText(quoteEntity.getRoi());
        txtNetIncome.setText(quoteEntity.getNetincome());
        txtEmi.setText("" + quoteEntity.getEmi());
        txtLoanTenure.setText("" + quoteEntity.getLoanTenure());
    }

    private void initialise_widgets() {
        imgBankLogo = (ImageView) findViewById(R.id.imgBankLogo);
        txtPartpaymentfixed = (TextView) findViewById(R.id.txtPartpaymentfixed);
        txtPrecloserFixed = (TextView) findViewById(R.id.txtPrecloserFixed);
        txtTopup = (TextView) findViewById(R.id.txtTopup);
        txtBalancePeriod = (TextView) findViewById(R.id.txtBalancePeriod);
        txtLockInPeriod = (TextView) findViewById(R.id.txtLockInPeriod);
        txtWomenROI = (TextView) findViewById(R.id.txtWomenROI);
        txtLoanRequired = (TextView) findViewById(R.id.txtLoanRequired);
        txtProcessingFees = (TextView) findViewById(R.id.txtProcessingFees);
        txtBankName = (TextView) findViewById(R.id.txtBankName);
        txtROI = (TextView) findViewById(R.id.txtROI);
        txtNetIncome = (TextView) findViewById(R.id.txtNetIncome);
        txtEmi = (TextView) findViewById(R.id.txtEmi);
        txtLoanTenure = (TextView) findViewById(R.id.txtLoanTenure);
    }


}
