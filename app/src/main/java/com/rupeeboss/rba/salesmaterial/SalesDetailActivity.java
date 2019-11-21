package com.rupeeboss.rba.salesmaterial;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Log;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rupeeboss.rba.BaseActivity;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.utility.Utility;
import com.rupeeboss.rba.core.model.DocsEntity;
import com.rupeeboss.rba.core.facade.LoginFacade;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

public class SalesDetailActivity extends BaseActivity {

    SharedPreferences sharedPreferences;
    String empCode;
    RecyclerView rvProduct;
    SalesDocAdapter docAdapter;
    int numberOfColumns = 2;
    String rbaNAme, rbaDesg , rbaEmail, rbaMobNo = "";
    Drawable rbaPhoto;
    LoginFacade facade;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        init();
        facade = new LoginFacade(this);
        sharedPreferences = this.getSharedPreferences("CALLER_AGENT", MODE_PRIVATE);
        empCode = sharedPreferences.getString(Utility.EMPLOYEE_ID, "0");
        docAdapter = new SalesDocAdapter(SalesDetailActivity.this,  getDocList());

        rvProduct.setAdapter(docAdapter);

        try {
            setOtherDetails();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

       // new createBitmapFromURLPosp().execute();
        Bitmap pospDetails = createBitmap(bitmap, rbaNAme,  rbaMobNo,rbaDesg,rbaEmail);
        saveImageToStorage(pospDetails, "rbaSalesMaterialDetails");
    }



    public List<DocsEntity> getDocList() {
        List<DocsEntity> docEntities = new ArrayList<DocsEntity>();

        docEntities.add(new DocsEntity(R.drawable.credit_card_brochure));
        docEntities.add(new DocsEntity(R.drawable.hl));
        docEntities.add(new DocsEntity(R.drawable.hl_bt));

        docEntities.add(new DocsEntity(R.drawable.lap_brochure));
        docEntities.add(new DocsEntity(R.drawable.lap_bt));
        docEntities.add(new DocsEntity(R.drawable.pl_brochure));

        docEntities.add(new DocsEntity(R.drawable.pl_bt));
        docEntities.add(new DocsEntity(R.drawable.rectify_credit));
        docEntities.add(new DocsEntity(R.drawable.unsecured_business_loan));

        return docEntities;
    }

    private void init() {

        rvProduct = (RecyclerView) findViewById(R.id.rvProduct);
        rvProduct.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager1 = new GridLayoutManager(this, numberOfColumns);
        rvProduct.setLayoutManager(layoutManager1);

    }
    public void redirectToApplyMain(DocsEntity docsEntity) {

        Intent intent = new Intent(this, SalesShareActivity.class);
        intent.putExtra(Utility.DOC_DATA, docsEntity);

        startActivity(intent);
    }


    private void setOtherDetails() {
        if (facade.getUser().getUName().equals("")) {
            rbaNAme =(facade.getUser().getBrokerName());
        } else {
            rbaNAme =(facade.getUser().getUName());
        }


        rbaEmail = facade.getUser().getEmail_Id(); //getEmpEmailID();
        rbaDesg = facade.getUser().getDesignation();//getEmpDesignation();
        rbaMobNo = facade.getUser().getContact_No();//getEmpContact();

        rbaPhoto = getResources().getDrawable(R.drawable.rupeeboss_logo);


         bitmap = ((BitmapDrawable)rbaPhoto).getBitmap();
    }


    public class createBitmapFromURLPosp extends AsyncTask<Void, Void, Bitmap> {
        URL url;

        public createBitmapFromURLPosp() {

        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(Void... voids) {
            Bitmap networkBitmap = null;
            try {
                if (url == null) {
                    Drawable d = getResources().getDrawable(R.drawable.rupeeboss_logo);
                    networkBitmap = ((BitmapDrawable) d).getBitmap();
                } else {
                    networkBitmap = BitmapFactory.decodeStream(
                            url.openConnection().getInputStream());
                }


            } catch (IOException e) {
                e.printStackTrace();
                Log.e("TAG", "Could not load Bitmap from: " + url);
            } finally {
                if (networkBitmap == null) {
                    Drawable d = getResources().getDrawable(R.drawable.rupeeboss_logo);
                    networkBitmap = ((BitmapDrawable) d).getBitmap();
                }
            }

            return networkBitmap;
        }

        protected void onPostExecute(Bitmap result) {
            if (result != null) {
                Bitmap pospDetails = createBitmap(result, rbaNAme,  rbaMobNo,rbaEmail, rbaDesg);
                saveImageToStorage(pospDetails, "rbaSalesMaterialDetails");
            }
            // bitmap_image = result;
        }
    }

}
