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
import com.rupeeboss.rba.utility.TouchImageView;
import com.rupeeboss.rba.utility.Utility;
import com.rupeeboss.rba.core.model.DocsEntity;
import com.rupeeboss.rba.core.facade.LoginFacade;

import java.io.FileInputStream;

public class SalesShareActivity extends BaseActivity {

    DocsEntity docsEntity;
    TouchImageView ivProduct;
    Bitmap combinedImage;
    Bitmap salesPhoto;
    LoginFacade facade;
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
            docsEntity = getIntent().getExtras().getParcelable(Utility.DOC_DATA);
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
            new  datashareListBitmap(SalesShareActivity.this, combinedImage, "RupeeBoss", "").execute();
//        //new shareImageNormal(docsEntity.getImage_path(), "Finmart", "Look what I found on Finmart!").execute();


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

                        salesPhoto =   BitmapFactory.decodeResource(getResources(), docsEntity.getImage_path());

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
                        .load(docsEntity.getImage_path())
                        .asBitmap()
                        .placeholder(getResources().getDrawable(R.drawable.rupeeboss_logo))
                        .into(ivProduct);
            } else {
                ivProduct.setImageBitmap(result);
            }


        }
    }


}
