package com.rupeeboss.rba.loan_fm.popup;

import android.os.Bundle;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.rupeeboss.rba.BaseActivity;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core_loan_fm.APIResponseERP;
import com.rupeeboss.rba.core_loan_fm.IResponseSubcriberERP;
import com.rupeeboss.rba.core_loan_fm.controller.erploan.ErpLoanController;
import com.rupeeboss.rba.core_loan_fm.model.LeadDataEntity;
import com.rupeeboss.rba.core_loan_fm.model.LeadEntity;
import com.rupeeboss.rba.core_loan_fm.response.LeadResponse;

import java.util.List;



public class LeadInfoPopupActivity extends BaseActivity implements View.OnClickListener , IResponseSubcriberERP {

    RecyclerView rvLeads;
    LeadInfoPopupAdapter mAdapter;
    TextView  txtCusName,  txtMobile, txtProduct,  txtBank ,txtNoData;
    LinearLayout lyParent;
    Button btnBack;
    List<LeadEntity> LeadList;
    LeadDataEntity leadDataEntity;
    String AppNumb = "0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lead_info_popup);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setFinishOnTouchOutside(true);

        initailize();
        if(getIntent().hasExtra("APPLICATION_NUMBER")){

            AppNumb = getIntent().getStringExtra("APPLICATION_NUMBER");
            showDialog();
            new ErpLoanController(LeadInfoPopupActivity.this).getLeadDetails(AppNumb, LeadInfoPopupActivity.this);

        }



    }

    private void initailize()
    {

        txtCusName = (TextView) findViewById(R.id.txtCusName);
        txtMobile = (TextView) findViewById(R.id.txtMobile);
        txtProduct = (TextView) findViewById(R.id.txtProduct);
        txtNoData = (TextView) findViewById(R.id.txtNoData);
        txtBank = (TextView) findViewById(R.id.txtBank);
        btnBack = (Button)  findViewById(R.id.btnBack);
        lyParent = (LinearLayout)findViewById(R.id.lyParent) ;

        rvLeads = (RecyclerView) findViewById(R.id.rvLeads);
        rvLeads.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(LeadInfoPopupActivity.this);
        rvLeads.setLayoutManager(layoutManager);


        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnBack:

                this.finish();
                break;

        }

    }

    private void bindData()
    {
        txtCusName.setText(leadDataEntity.getCust_Name());
        txtProduct.setText(leadDataEntity.getProduct_Name());
        txtMobile.setText(leadDataEntity.getMobileNo());
        txtBank.setText(leadDataEntity.getBank_Name());
    }

    @Override
    public void OnSuccessERP(APIResponseERP response, String message) {

        cancelDialog();

        if (response instanceof LeadResponse) {

            if (response.getStatusId() == 0) {
                if ( ((LeadResponse) response).getResult() != null) {

                    lyParent.setVisibility(View.VISIBLE);
                    txtNoData.setVisibility(View.GONE);
                    leadDataEntity = ((LeadResponse) response).getResult();
                    bindData();
                    LeadList = ((LeadResponse) response).getResult().getLstLeadDtls();

                    mAdapter = new LeadInfoPopupAdapter(LeadInfoPopupActivity.this, LeadList);
                    rvLeads.setAdapter(mAdapter);
                } else {
                    rvLeads.setAdapter(null);
                    Snackbar.make(rvLeads, "No  Data Available", Snackbar.LENGTH_SHORT).show();
                }
            }else {

                lyParent.setVisibility(View.GONE);
                txtNoData.setVisibility(View.VISIBLE);
                rvLeads.setAdapter(null);
               // Snackbar.make(rvLeads, "No  Data Available", Snackbar.LENGTH_SHORT).show();
            }
        }


    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
        Toast.makeText(this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {

        this.finish();
    }
}
