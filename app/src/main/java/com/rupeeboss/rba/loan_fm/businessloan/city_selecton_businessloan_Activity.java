package com.rupeeboss.rba.loan_fm.businessloan;

import android.content.Intent;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListAdapter;

import com.rupeeboss.rba.BaseActivity;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.loan_fm.MakeCityAdapter;

import java.util.ArrayList;
import java.util.List;


import com.rupeeboss.rba.core_loan_fm.APIResponseERP;
import com.rupeeboss.rba.core_loan_fm.IResponseSubcriberERP;
import com.rupeeboss.rba.core_loan_fm.controller.erploan.ErpLoanController;
import com.rupeeboss.rba.core_loan_fm.model.LoanCityEntity;
import com.rupeeboss.rba.core_loan_fm.response.LoanCityResponse;
import com.rupeeboss.rba.loan_fm.LoanCityFacade;


public class city_selecton_businessloan_Activity extends BaseActivity implements View.OnClickListener ,IResponseSubcriberERP {


    AutoCompleteTextView acCity;
    List<String> cityList;
    Button btnNEXT;
 //   DBPersistanceController databaseController;
//    LoginResponseEntity loginEntity;
    ArrayList<String> arrayNewLoan, arrayPreferedCity;

    ArrayAdapter<String> preferedCityAdapter;
    MakeCityAdapter makeCityAdapter;
    LoanCityFacade loanCityFacade;
    boolean IsCityValid = false;
    LoanCityEntity loanCityEntity;
    CardView cvMUMBAI,cvDELHI,cvBangalore,cvhyderabad,cvAhamadabad,cvKolkata,cvCHENNAI,cvPUNE,cvJAIPUR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_selecton_personalloan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    //    databaseController = new DBPersistanceController(city_selecton_businessloan_Activity.this);
     //   cityList = databaseController.getLoanCity();
        loanCityFacade = new  LoanCityFacade(this);
        IsCityValid = false;
        acCity = (AutoCompleteTextView)findViewById(R.id.acCity);
        acCity.setOnFocusChangeListener(acCityFocusChange);
        cvMUMBAI=(CardView)findViewById(R.id.cvMUMBAI) ;
        cvDELHI=(CardView)findViewById(R.id.cvDELHI) ;
        cvBangalore=(CardView)findViewById(R.id.cvBangalore) ;
        cvhyderabad=(CardView)findViewById(R.id.cvhyderabad) ;
        cvAhamadabad=(CardView)findViewById(R.id.cvAhamadabad) ;
        cvKolkata=(CardView)findViewById(R.id.cvKolkata) ;
        cvCHENNAI=(CardView)findViewById(R.id.cvCHENNAI) ;
        cvPUNE=(CardView)findViewById(R.id.cvPUNE) ;
        cvJAIPUR=(CardView)findViewById(R.id.cvJAIPUR) ;

        btnNEXT = (Button) findViewById(R.id.btnNEXT);
        btnNEXT.setOnClickListener(this);


        cvMUMBAI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(city_selecton_businessloan_Activity.this, bank_selection_businessloanActivity.class)
                        .putExtra("city_id","677")
                );
            }
        });
        cvDELHI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(city_selecton_businessloan_Activity.this, bank_selection_businessloanActivity.class)
                        .putExtra("city_id","252")
                );
            }
        });
        cvBangalore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(city_selecton_businessloan_Activity.this, bank_selection_businessloanActivity.class)
                        .putExtra("city_id","93")
                );
            }
        });
        cvhyderabad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(city_selecton_businessloan_Activity.this, bank_selection_businessloanActivity.class)
                        .putExtra("city_id","404")
                );
            }
        });
        cvAhamadabad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(city_selecton_businessloan_Activity.this, bank_selection_businessloanActivity.class)
                        .putExtra("city_id","9")
                );
            }
        });
        cvKolkata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(city_selecton_businessloan_Activity.this, bank_selection_businessloanActivity.class)
                        .putExtra("city_id","550")
                );
            }
        });
        cvCHENNAI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(city_selecton_businessloan_Activity.this, bank_selection_businessloanActivity.class)
                        .putExtra("city_id","196")
                );
            }
        });
        cvPUNE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(city_selecton_businessloan_Activity.this, bank_selection_businessloanActivity.class)
                        .putExtra("city_id","828")
                );
            }
        });

        cvJAIPUR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(city_selecton_businessloan_Activity.this, bank_selection_businessloanActivity.class)
                        .putExtra("city_id","419")
                );
            }
        });


        acCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                IsCityValid = true;
                acCity.setError(null);
                loanCityEntity   = makeCityAdapter.getItem(position);
                acCity.setSelection(0);
            }
        });
       // loadSpinner();

        if(loanCityFacade.getLoanCity() == null) {
            showDialog();
            new ErpLoanController(this).getcityloan(this);
        }else{
            loadSpinner(loanCityFacade.getLoanCity());
        }

    }
    View.OnFocusChangeListener acCityFocusChange = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean b) {
            if (!b) {

                String str = acCity.getText().toString();

                ListAdapter listAdapter = acCity.getAdapter();
                for (int i = 0; i < listAdapter.getCount(); i++) {
                    String temp = listAdapter.getItem(i).toString();
                    if (str.compareTo(temp) == 0) {
                        return;
                    }
                }

                acCity.setText("");
                acCity.setError("Invalid city");
                acCity.setFocusable(true);
            }
        }
    };
    private void loadSpinner() {



        //region Preferred City Adapter
//        arrayPreferedCity = new ArrayList<String>();
//        preferedCityAdapter = new ArrayAdapter<String>(city_selecton_personalloan_Activity.this,
//                android.R.layout.simple_list_item_1, cityList);
//
//        acCity.setAdapter(preferedCityAdapter);
//        acCity.setThreshold(1);

        if(loanCityFacade.getLoanCity() != null) {

            makeCityAdapter = new MakeCityAdapter(this, R.layout.activity_city_selecton_personalloan, R.id.lbl_name, loanCityFacade.getLoanCity());
            acCity.setAdapter(makeCityAdapter);
            acCity.setThreshold(1);
        }

        //endregion

    }

    private void loadSpinner(List<LoanCityEntity> loanCity) {


        if(loanCityFacade.getLoanCity() != null) {

            makeCityAdapter = new MakeCityAdapter(this, R.layout.activity_city_selecton_personalloan, R.id.lbl_name, loanCity);
            acCity.setAdapter(makeCityAdapter);
            acCity.setThreshold(1);
        }

        //endregion

    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnNEXT) {
            if (acCity.getText().toString().equals("") || acCity.getText().toString().length() == 0) {
                acCity.setError("Please Enter city.");
                acCity.requestFocus();
                return;
            }
            if(IsCityValid == false)
            {
                acCity.setError("Please Enter Valid City.");
                acCity.requestFocus();
                return;
            }

       //     int city_id = databaseController.getLoanCityID(acCity.getText().toString().toUpperCase());

            if(loanCityEntity != null){

                startActivity(new Intent(city_selecton_businessloan_Activity.this, bank_selection_businessloanActivity.class)
                        .putExtra("city_id", String.valueOf(loanCityEntity.getCityId()))
                );
            }
        }


    }


    @Override
    public void OnSuccessERP(APIResponseERP response, String message) {

        cancelDialog();
        if(response instanceof  LoanCityResponse)
        {

            loadSpinner(((LoanCityResponse) response).getResult().getLstCity());
        }
    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
    }
}
