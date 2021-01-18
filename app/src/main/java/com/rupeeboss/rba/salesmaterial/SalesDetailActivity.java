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
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.rupeeboss.rba.BaseActivity;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.controller.dynamic.DynamicController;
import com.rupeeboss.rba.core.model.FestivalCompaignEntity;
import com.rupeeboss.rba.core.response.FestivalCampaignResponse;
import com.rupeeboss.rba.utility.Utility;
import com.rupeeboss.rba.core.model.DocsEntity;
import com.rupeeboss.rba.core.facade.LoginFacade;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;

public class SalesDetailActivity extends BaseActivity implements IResponseSubcriber {


    SharedPreferences sharedPreferences;
    String empCode;
    RecyclerView rvProduct;
    SalesDocAdapter docAdapter;
    int numberOfColumns = 2;
    String rbaNAme, rbaDesg , rbaEmail, rbaMobNo = "";
    Drawable rbaPhoto;
    LoginFacade facade;

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
        setOtherDetails();       // added jan 2021
//        docAdapter = new SalesDocAdapter(SalesDetailActivity.this,  getDocList());
//
//        rvProduct.setAdapter(docAdapter);

//        try {
//            setOtherDetails();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//
//       // new createBitmapFromURLPosp().execute();
//        Bitmap pospDetails = createBitmap(bitmap, rbaNAme,  rbaMobNo,rbaDesg,rbaEmail);
//        saveImageToStorage(pospDetails, "rbaSalesMaterialDetails");

      ///  new SendRequest().execute();

        showDialog();
        new createBitmapFromURLPosp().execute();

        new DynamicController().getSalesMaterial(this);

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
        docEntities.add(new DocsEntity(R.drawable.rbanewyear));

        docEntities.add(new DocsEntity(R.drawable.p1));
        docEntities.add(new DocsEntity(R.drawable.b1));
        docEntities.add(new DocsEntity(R.drawable.d1));
        docEntities.add(new DocsEntity(R.drawable.c1));
        docEntities.add(new DocsEntity(R.drawable.hlbt));
        docEntities.add(new DocsEntity(R.drawable.doctors));
        return docEntities;
    }

    private void init() {

        rvProduct = (RecyclerView) findViewById(R.id.rvProduct);
        rvProduct.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager1 = new GridLayoutManager(this, numberOfColumns);
        rvProduct.setLayoutManager(layoutManager1);

    }
    public void redirectToApplyMain(FestivalCompaignEntity festivalCompaignEntity) {

        Intent intent = new Intent(this, SalesShareActivity.class);
        intent.putExtra(Utility.DOC_DATA, festivalCompaignEntity);

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


     //    bitmap = ((BitmapDrawable)rbaPhoto).getBitmap();
    }

    @Override
    public void OnSuccess(APIResponse response, String message) throws InterruptedException {

        cancelDialog();

        if (response instanceof FestivalCampaignResponse) {

            List<FestivalCompaignEntity> mLoanList = ((FestivalCampaignResponse) response).getMasterData().getLoan();

            docAdapter = new SalesDocAdapter(SalesDetailActivity.this, mLoanList);

            rvProduct.setAdapter(docAdapter);
        }
    }

    @Override
    public void OnFailure(Throwable t) {

        cancelDialog();

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

    //region SendRequest commneted (Old Request)
 //   public class SendRequest extends AsyncTask<String, Void, String> {
//
//        protected void onPreExecute(){}
//
//        protected String doInBackground(String... arg0) {
//
//            try{
//
//                URL url = new URL("http://qa.mgfm.in/api/getrbcaller_salesmaterial");
//
//                JSONObject postDataParams = new JSONObject();
//
//                //   postDataParams.put("Source","Finmart");
//                //    postDataParams.put("LoanId","38054");
//                postDataParams.put("brokerid","" );//+ facade.getLoginResponse().getResult().getEmpCode());
//                // postDataParams.put("uid", ""+facade.getLoginResponse().getResult().getUID());
//                // postDataParams.put("pageno", 1);
//
//                Log.e("params",postDataParams.toString());
//
//                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//
//                //  String userCredentials = "token:1234567890";
//                // String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userCredentials.getBytes()));
//                //   String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userCredentials.getBytes()));
//
//
//                conn.setReadTimeout(15000 /* milliseconds */);
//                conn.setConnectTimeout(15000 /* milliseconds */);
//
//                conn.setRequestProperty ("token",  "1234567890");
//
//                conn.setRequestMethod("POST");
//                conn.setDoInput(true);
//                conn.setDoOutput(true);
//
//                OutputStream os = conn.getOutputStream();
//                BufferedWriter writer = new BufferedWriter(
//                        new OutputStreamWriter(os, "UTF-8"));
//                writer.write(getPostDataString(postDataParams));
//
//                writer.flush();
//                writer.close();
//                os.close();
//
//                int responseCode=conn.getResponseCode();
//
//                if (responseCode == HttpsURLConnection.HTTP_OK) {
//
//                    BufferedReader in=new BufferedReader(new InputStreamReader(conn.getInputStream()));
//                    StringBuffer sb = new StringBuffer("");
//                    String line="";
//
//                    while((line = in.readLine()) != null) {
//
//                        sb.append(line);
//                        break;
//                    }
//
//                    in.close();
//                    return sb.toString();
//
//                }
//                else {
//                    return new String("false : "+responseCode);
//                }
//            }
//            catch(Exception e){
//                return new String("Exception: " + e.getMessage());
//            }
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
////            Toast.makeText(getApplicationContext(), result,
////                    Toast.LENGTH_LONG).show();
//
//            Gson obgson = new Gson();
//            FestivalCampaignResponse cl= obgson.fromJson(result, FestivalCampaignResponse.class);
//            if(cl.getStatusNo()== 0) {
//
////                List<FestivalCompaignEntity> MasterData   = cl.getMasterData();
////                List<DocsEntity> docEntities = new ArrayList<DocsEntity>();
////
////                for(int i=0; i < cl.getMasterData().size()-1; i++)
////                {
////                    docEntities.add();
////                    docEntities.add(new DocsEntity(cl.getMasterData().get(i).getImagelink()));
////                }
////
////                docEntities.add(new DocsEntity(R.drawable.hlbt));
//                docAdapter = new SalesDocAdapter(SalesDetailActivity.this,  cl.getMasterData());
//
//                rvProduct.setAdapter(docAdapter);
//
//                //     motorurl = bean.getFourWheelerUrl();
//
//                try {
//                    setOtherDetails();
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                }
//                Bitmap pospDetails = createBitmap(null, rbaNAme,  rbaMobNo,rbaEmail,rbaDesg);
//                saveImageToStorage(pospDetails, "rbaSalesMaterialDetails");
//
//            }else
//            {
//                Toast.makeText(getApplicationContext(), cl.getMessage(),Toast.LENGTH_LONG).show();
//            }
////            JSONArray arr = null;
////                arr = new JSONArray(result);
////
////            JSONObject jObj = null;
////                jObj = arr.getJSONObject(0);
////
////               String MasterData = jObj.getString("MasterData");
////
//
//
//        }
//    }
    //endregion

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
}
