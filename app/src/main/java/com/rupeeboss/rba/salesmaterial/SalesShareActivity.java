package com.rupeeboss.rba.salesmaterial;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.bumptech.glide.Glide;
import com.rupeeboss.rba.BaseActivity;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.model.FestivalCompaignEntity;
import com.rupeeboss.rba.utility.CustomImageView;
import com.rupeeboss.rba.utility.TouchImageView;
import com.rupeeboss.rba.utility.Utility;
import com.rupeeboss.rba.core.model.DocsEntity;
import com.rupeeboss.rba.core.facade.LoginFacade;

import java.io.FileInputStream;
import java.net.URL;

public class SalesShareActivity extends BaseActivity {

    DocsEntity docsEntity;
    TouchImageView ivProduct;
    Bitmap combinedImage;
    Bitmap salesPhoto;
    LoginFacade facade;
    FestivalCompaignEntity festivalCompaignEntity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_share);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initialize();
        facade = new LoginFacade(this);
        new createBitmapFromURLNew().execute();
    }

    private void initialize() {
        ivProduct = (TouchImageView) findViewById(R.id.ivProduct);

        if (getIntent().hasExtra(Utility.DOC_DATA)) {
            festivalCompaignEntity = getIntent().getExtras().getParcelable(Utility.DOC_DATA);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.share_action_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_share:
                showShareProduct();
                break;


        }
        return super.onOptionsItemSelected(item);
    }



    public void showShareProduct() {
        if (combinedImage != null)
        {
            String desc = "";
            String QueryBaseUrl= "";
            if(festivalCompaignEntity.getBaseurl() != null) {
                if (!festivalCompaignEntity.getBaseurl().isEmpty()) {
                   String brokerid = String.valueOf(new LoginFacade(SalesShareActivity.this).getUser().getBrokerId());
                    QueryBaseUrl = festivalCompaignEntity.getBaseurl() + "empcode=&brokerid="+brokerid+"&client_source=rbcaller";
                    desc = festivalCompaignEntity.getDescription() + "\n" + QueryBaseUrl;
                } else {
                    desc = festivalCompaignEntity.getDescription();
                }

            }else {
                desc = festivalCompaignEntity.getDescription();
            }
            new datashareListBitmap(SalesShareActivity.this, combinedImage, festivalCompaignEntity.getTitle(), desc).execute();
        }

        //new shareImageNormal(docsEntity.getImage_path(), "Finmart", "Look what I found on Finmart!").execute();


    }

    public class createBitmapFromURLNew extends AsyncTask<Void, Void, Bitmap> {

        @Override
        protected void onPreExecute() {
            showDialog();
        }

        @Override
        protected Bitmap doInBackground(Void... voids) {


                    try {
                        combinedImage = BitmapFactory.decodeStream(new FileInputStream(getImageFromStorage("rbaSalesMaterialDetails")));


                    } catch (Exception e) {
                        e.printStackTrace();
                    }

            try {
                URL salePhotoUrl  = new URL(festivalCompaignEntity.getImagelink());
                salesPhoto =   BitmapFactory.decodeStream( salePhotoUrl.openConnection().getInputStream());

                if (combinedImage != null && salesPhoto != null) {
                    combinedImage = combineImages(salesPhoto, combinedImage);
                    //ivProduct.setImageBitmap(combinedImage);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


            return combinedImage;
        }

        protected void onPostExecute(Bitmap result) {

            cancelDialog();
            if (result == null) {
                Glide.with(SalesShareActivity.this)
                        .load(festivalCompaignEntity.getImagelink())
                        .asBitmap()
                        .placeholder(getResources().getDrawable(R.drawable.rupeeboss_logo))
                        .into(ivProduct);
            } else {
                ivProduct.setImageBitmap(result);
            }


        }
    }


}
