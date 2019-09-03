package com.rupeeboss.rba.loan_fm.new_personalloan;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.datacomp.magicfinmart.BaseActivity;
import com.datacomp.magicfinmart.MyApplication;
import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.loan_fm.popup.LeadInfoPopupActivity;
import com.datacomp.magicfinmart.utility.Constants;
import com.datacomp.magicfinmart.webviews.CommonWebViewActivity;

import magicfinmart.datacomp.com.finmartserviceapi.Utility;
import magicfinmart.datacomp.com.finmartserviceapi.database.DBPersistanceController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.tracking.TrackingController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.LoginResponseEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.TrackingData;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.requestentity.TrackingRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.APIResponseFM;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.IResponseSubcriberFM;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.controller.mainloan.MainLoanController;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.model.NewLoanApplicationEnity;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.response.NewLoanApplicationResponse;

public class NewPersonalApplicaionActivity extends BaseActivity implements View.OnClickListener, IResponseSubcriberFM {

    RecyclerView rvApplicationList;
    NewPersonalLoanApplicationAdapter mAdapter;

    Toolbar toolbar;
    DBPersistanceController dbPersistanceController;
    LoginResponseEntity loginResponseEntity;
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
        rvApplicationList.setLayoutManager(new LinearLayoutManager(NewPersonalApplicaionActivity.this));

        dbPersistanceController = new DBPersistanceController(NewPersonalApplicaionActivity.this);
        loginResponseEntity = dbPersistanceController.getUserData();
        showDialog();
        new MainLoanController(NewPersonalApplicaionActivity.this).getLoanApplication(0,"PSL",String.valueOf(loginResponseEntity.getFBAId()),NewPersonalApplicaionActivity.this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loanAddlist:

                new TrackingController(NewPersonalApplicaionActivity.this).sendData(new TrackingRequestEntity(new TrackingData("PERSONAL LOAN : PERSONAL LOAN QUOTES ADD WITH FLAOTING BUTTON"), Constants.PERSONA_LOAN), null);

                MyApplication.getInstance().trackEvent(Constants.PERSONA_LOAN, "Clicked", "PERSONAL LOAN QUOTES ADD WITH FLAOTING BUTTON");

                startActivity(new Intent(NewPersonalApplicaionActivity.this, city_selecton_personalloan_Activity.class));
                break;
            case R.id.tvAdd:

                new TrackingController(NewPersonalApplicaionActivity.this).sendData(new TrackingRequestEntity(new TrackingData("PERSONAL LOAN : PERSONAL LOAN QUOTES ADD WITH FLAOTING BUTTON"), Constants.PERSONA_LOAN), null);

                MyApplication.getInstance().trackEvent(Constants.PERSONA_LOAN, "Clicked", "PERSONAL LOAN QUOTES ADD WITH FLAOTING BUTTON");

                startActivity(new Intent(NewPersonalApplicaionActivity.this, city_selecton_personalloan_Activity.class));

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
                    mAdapter = new NewPersonalLoanApplicationAdapter(NewPersonalApplicaionActivity.this, getpersonal_bank_list_response.getMasterData());
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
        url = entity.getBank_URL() + "?BrokerId=" + loginResponseEntity.getLoanId()+"&FBAId=" + loginResponseEntity.getFBAId() + "&client_source=finmart&lead_id="+entity.getLeadId()+"";


//        if(String.valueOf(entity.getBankId()).equals("33")){
//            Bankname="KOTAK MAHINDRA BANK";
//            url="https://www.rupeeboss.com/kotakmahindra-pl?BrokerId=" + loginResponseEntity.getLoanId()+"&FBAId=" + loginResponseEntity.getFBAId() + "&client_source=finmart&lead_id="+entity.getLeadId()+"";
//
//        }else   if(String.valueOf(entity.getBankId()).equals("43")){
//            Bankname="RBL BANK";
//            url="https://www.rupeeboss.com/rbl-pl?BrokerId=" + loginResponseEntity.getLoanId()+"&FBAId=" + loginResponseEntity.getFBAId() + "&client_source=finmart&lead_id="+entity.getLeadId()+"";
//
//        }else  if(String.valueOf(entity.getBankId()).equals("51")){
//            Bankname="TATA CAPITAL";
//            url="https://www.rupeeboss.com/tatacapital-pl?BrokerId=" + loginResponseEntity.getLoanId()+"&FBAId=" + loginResponseEntity.getFBAId() + "&client_source=finmart&lead_id="+entity.getLeadId()+"";
//
//        }else   if(String.valueOf(entity.getBankId()).equals("53")){
//            Bankname="YES BANK";
//            String url1 = "https://yesbankbot.buildquickbots.com/chat/rupeeboss/staff/?userid=" + loginResponseEntity.getFBAId()+ "&usertype=finmart&vkey=b34f02e9-8f1c";
//
//            Utility.loadWebViewUrlInBrowser(NewPersonalApplicaionActivity.this,url1);
//        }else   if(String.valueOf(entity.getBankId()).equals("20")){
//            Bankname="HDFC BANK";
//            url="https://www.rupeeboss.com/hdfc-pl?BrokerId=" + loginResponseEntity.getLoanId()+"&FBAId=" + loginResponseEntity.getFBAId() + "&client_source=finmart&lead_id="+entity.getLeadId()+"";
//        }else  if(String.valueOf(entity.getBankId()).equals("2152")){
//            Bankname="CASHE";
//            url="https://www.rupeeboss.com/cashe-new?BrokerId=" + loginResponseEntity.getLoanId()+"&FBAId=" + loginResponseEntity.getFBAId() + "&client_source=finmart&lead_id="+entity.getLeadId()+"";
//        }

        if(!String.valueOf(entity.getBankId()).equals("53")) {
            startActivity(new Intent(this, CommonWebViewActivity.class)
                    .putExtra("URL", url)
                    .putExtra("NAME", "" + Bankname)
                    .putExtra("TITLE", "" + Bankname));
        }
        else
        {
            Bankname="YES BANK";

            String url1 = "https://yesbankbot.buildquickbots.com/chat/rupeeboss/staff/?userid=" + loginResponseEntity.getFBAId()+ "&usertype=finmart&vkey=b34f02e9-8f1c";

            Utility.loadWebViewUrlInBrowser(NewPersonalApplicaionActivity.this,url1);

        }
    }

    public void openLeadDetailPopUp_personal(String AppNumb)
    {
        Intent intent = new Intent(NewPersonalApplicaionActivity.this, LeadInfoPopupActivity.class);
        intent.putExtra("APPLICATION_NUMBER",AppNumb);
        startActivityForResult(intent,Utility.LEAD_REQUEST_CODE);
    }

}
