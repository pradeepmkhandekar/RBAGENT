package com.rupeeboss.rba.EmiCalculator.workcapital;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rupeeboss.rba.BaseActivity;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.controller.Emicalculator.EmicalculatorController;
import com.rupeeboss.rba.core.request.requestentity.WorkCapitalEmiRequestEntity;
import com.rupeeboss.rba.core.response.WorkCapitalResponse;

import java.math.BigDecimal;

public class WorkingCapitalCalculatorActivity extends BaseActivity implements View.OnClickListener, IResponseSubcriber {

    EditText et_turnOver, et_profitBeforeTax, et_depreciation, et_FinanceCost,
            et_Inventory, et_Debtors, et_creditors, et_exhistingOd;
    Button btn_submit;
    TextView tvEligibleAmt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_working_capital_calculator);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init_widgets();
    }

    private void init_widgets() {
        et_turnOver = (EditText) findViewById(R.id.et_turnOver);
        et_profitBeforeTax = (EditText) findViewById(R.id.et_profitBeforeTax);
        et_depreciation = (EditText) findViewById(R.id.et_depreciation);
        et_FinanceCost = (EditText) findViewById(R.id.et_FinanceCost);
        et_Inventory = (EditText) findViewById(R.id.et_Inventory);
        et_Debtors = (EditText) findViewById(R.id.et_Debtors);
        et_creditors = (EditText) findViewById(R.id.et_creditors);
        et_exhistingOd = (EditText) findViewById(R.id.et_exhistingOd);
        tvEligibleAmt = (TextView) findViewById(R.id.tvEligibleAmt);
        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_submit) {
            WorkCapitalEmiRequestEntity workCapitalEmiRequestEntity = new WorkCapitalEmiRequestEntity();
            workCapitalEmiRequestEntity.setTurnover("" + et_turnOver.getText().toString().trim());
            workCapitalEmiRequestEntity.setProfitbefore("" + et_profitBeforeTax.getText().toString().trim());
            workCapitalEmiRequestEntity.setDepreciation("" + et_depreciation.getText().toString().trim());
            workCapitalEmiRequestEntity.setFinancecost("" + et_FinanceCost.getText().toString().trim());
            workCapitalEmiRequestEntity.setInventory("" + et_Inventory.getText().toString().trim());
            workCapitalEmiRequestEntity.setDebtors("" + et_Debtors.getText().toString().trim());
            workCapitalEmiRequestEntity.setCreditors("" + et_creditors.getText().toString().trim());
            workCapitalEmiRequestEntity.setExisting("" + et_exhistingOd.getText().toString().trim());
            showProgressDialog();
            new EmicalculatorController(this).getWorkingCapital(workCapitalEmiRequestEntity, this);
        }
    }

    @Override
    public void OnSuccess(APIResponse response, String message) throws InterruptedException {
        if (response instanceof WorkCapitalResponse) {
            dismissDialog();
            if(((WorkCapitalResponse) response).getData()!=null) {
                tvEligibleAmt.setVisibility(View.VISIBLE);
               // tvEligibleAmt.setText("" + "\u20B9" + BigDecimal.valueOf(((WorkCapitalResponse) response).getData().getProposedlimit()));
                tvEligibleAmt.setText("" + "\u20B9" + BigDecimal.valueOf(Math.round(((WorkCapitalResponse) response).getData().getProposedlimit())));
            }
            else {
                tvEligibleAmt.setVisibility(View.GONE);
                Toast.makeText(this, "" + ((WorkCapitalResponse) response).getErr_code(), Toast.LENGTH_SHORT).show();
            }
            //Toast.makeText(this, "" + ((WorkCapitalResponse) response).getData().getProposedlimit(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void OnFailure(Throwable t) {
        dismissDialog();
        tvEligibleAmt.setText("" + "\u20B9" + "0");
        Toast.makeText(this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
