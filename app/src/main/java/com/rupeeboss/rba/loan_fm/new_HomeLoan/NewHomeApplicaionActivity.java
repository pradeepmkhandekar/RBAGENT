package com.rupeeboss.rba.loan_fm.new_HomeLoan;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rupeeboss.rba.BaseActivity;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core_loan_fm.APIResponseFM;
import com.rupeeboss.rba.core_loan_fm.IResponseSubcriberFM;
import com.rupeeboss.rba.core_loan_fm.controller.mainloan.MainLoanController;
import com.rupeeboss.rba.core_loan_fm.model.NewLoanApplicationEnity;
import com.rupeeboss.rba.core_loan_fm.response.NewLoanApplicationResponse;
import com.rupeeboss.rba.loan_fm.Utility;
import com.rupeeboss.rba.loan_fm.popup.LeadInfoPopupActivity;
import com.rupeeboss.rba.webviews.commonwebview.CommonWebviewActivity;


public class NewHomeApplicaionActivity extends BaseActivity implements View.OnClickListener, IResponseSubcriberFM {

    RecyclerView rvApplicationList;
    NewHomeLoanApplicationAdapter mAdapter;

    Toolbar toolbar;
  //  DBPersistanceController dbPersistanceController;
  //  LoginResponseEntity loginResponseEntity;
    NewLoanApplicationResponse getpersonal_bank_list_response;
    FloatingActionButton loanAddlist;
    TextView tvAdd;
    boolean isHit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_personal_applicaion);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        tvAdd = (TextView) findViewById(R.id.tvAdd);
        tvAdd.setOnClickListener(this);
        loanAddlist = (FloatingActionButton) findViewById(R.id.loanAddlist);
        loanAddlist.setOnClickListener(this);

        rvApplicationList = (RecyclerView)findViewById(R.id.rvApplicationList);
        rvApplicationList.setLayoutManager(new LinearLayoutManager(NewHomeApplicaionActivity.this));

      //  dbPersistanceController = new DBPersistanceController(NewHomeApplicaionActivity.this);
     //   loginResponseEntity = dbPersistanceController.getUserData();
        showDialog();
        new MainLoanController(NewHomeApplicaionActivity.this).getLoanApplication(0,"HML",String.valueOf("1978"), NewHomeApplicaionActivity.this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loanAddlist:


                startActivity(new Intent(NewHomeApplicaionActivity.this, city_selecton_homeloan_Activity.class));
			             break;
            case R.id.tvAdd:

                startActivity(new Intent(NewHomeApplicaionActivity.this, city_selecton_homeloan_Activity.class));
		
                break;
        }
    }


    @Override
    public void OnSuccessFM(APIResponseFM response, String message) {
        cancelDialog();
        if (response instanceof NewLoanApplicationResponse) {

            getpersonal_bank_list_response = ((NewLoanApplicationResponse) response);
            if (getpersonal_bank_list_response != null) {
                if(getpersonal_bank_list_response.getMasterData().size() > 0) {
                    mAdapter = new NewHomeLoanApplicationAdapter(NewHomeApplicaionActivity.this, getpersonal_bank_list_response.getMasterData());
                    rvApplicationList.setAdapter(mAdapter);
                }else {
                    Toast.makeText(this, "Data Not Found", Toast.LENGTH_LONG).show();
                }
            }else
            {
                Toast.makeText(this, getpersonal_bank_list_response.getMessage(), Toast.LENGTH_LONG).show();
            }


        }
    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
        Toast.makeText(this, getpersonal_bank_list_response.getMessage(), Toast.LENGTH_LONG).show();

    }

    public void redirectPersonalLoanApply(NewLoanApplicationEnity entity) {

        String url="";
        String Bankname="";
        Bankname = entity.getBankName();
        url = entity.getBank_URL() + "?BrokerId=" + "1978"+"&FBAId=" + "1978" + "&client_source=finmart&lead_id="+entity.getLeadId()+"";

//        if(String.valueOf(entity.getBankId()).equals("33")){
//            Bankname="KOTAK MAHINDRA BANK";
//            url="https://www.rupeeboss.com/kotakmahindra-home-loan?BrokerId=" + loginResponseEntity.getLoanId()+"&FBAId=" + loginResponseEntity.getFBAId() + "&client_source=finmart&lead_id="+entity.getLeadId()+"";
//
//        }else   if(String.valueOf(entity.getBankId()).equals("43")){
//            Bankname="RBL BANK";
//           // url="https://www.rupeeboss.com/rbl-pl?BrokerId=" + loginResponseEntity.getLoanId()+"&FBAId=" + loginResponseEntity.getFBAId() + "&client_source=finmart&lead_id="+entity.getLeadId()+"";
//
//        }else  if(String.valueOf(entity.getBankId()).equals("51")){
//            Bankname="TATA CAPITAL";
//          //  url="https://www.rupeeboss.com/tatacapital-pl?BrokerId=" + loginResponseEntity.getLoanId()+"&FBAId=" + loginResponseEntity.getFBAId() + "&client_source=finmart&lead_id="+entity.getLeadId()+"";
//
//        }else   if(String.valueOf(entity.getBankId()).equals("53")){
//            Bankname="YES BANK";
//         //   String url1 = "https://yesbankbot.buildquickbots.com/chat/rupeeboss/staff/?userid=" + loginResponseEntity.getFBAId()+ "&usertype=finmart&vkey=b34f02e9-8f1c";
//            url="https://www.rupeeboss.com/yes-bank-home-loan?BrokerId=" + loginResponseEntity.getLoanId()+"&FBAId=" + loginResponseEntity.getFBAId() + "&client_source=finmart&lead_id="+entity.getLeadId()+"";
//
//            //Utility.loadWebViewUrlInBrowser(NewHomeApplicaionActivity.this,url1);
//        }else   if(String.valueOf(entity.getBankId()).equals("20")){
//            Bankname="HDFC BANK";
//        //    url="https://www.rupeeboss.com/hdfc-pl?BrokerId=" + loginResponseEntity.getLoanId()+"&FBAId=" + loginResponseEntity.getFBAId() + "&client_source=finmart&lead_id="+entity.getLeadId()+"";
//        }else  if(String.valueOf(entity.getBankId()).equals("2152")){
//            Bankname="CASHE";
//         //   url="https://www.rupeeboss.com/cashe-new?BrokerId=" + loginResponseEntity.getLoanId()+"&FBAId=" + loginResponseEntity.getFBAId() + "&client_source=finmart&lead_id="+entity.getLeadId()+"";
//        }


            startActivity(new Intent(this, CommonWebviewActivity.class)
                    .putExtra("URL", url)
                    .putExtra("NAME", "" + Bankname)
                    .putExtra("TITLE", "" + Bankname));

    }

    public void openLeadDetailPopUp_home(String AppNumb)
    {
        Intent intent = new Intent(NewHomeApplicaionActivity.this, LeadInfoPopupActivity.class);
        intent.putExtra("APPLICATION_NUMBER",AppNumb);
        startActivityForResult(intent, Utility.LEAD_REQUEST_CODE);
    }

}
