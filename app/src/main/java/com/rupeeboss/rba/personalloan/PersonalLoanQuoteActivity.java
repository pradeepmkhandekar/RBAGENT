package com.rupeeboss.rba.personalloan;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.rupeeboss.rba.BaseActivity;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.model.PersonalQuoteEntity;
import com.rupeeboss.rba.core.request.requestentity.PersonalLoanRequest;
import com.rupeeboss.rba.core.response.GetPersonalLoanResponse;
import com.rupeeboss.rba.rbaddlead.RbAddLeadActivity;
import com.rupeeboss.rba.utility.Constants;
import com.rupeeboss.rba.utility.Utility;
import com.rupeeboss.rba.webviews.persoanlloan.PersonalLoanApplyActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class PersonalLoanQuoteActivity extends BaseActivity {

    GetPersonalLoanResponse getPersonalLoanResponse;
    RecyclerView rvPLQuotes;
    PLQuoteAdapter mAdapter;
    PersonalLoanRequest personalLoanRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_loan_quote);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getPersonalLoanResponse = getIntent().getParcelableExtra(Constants.PERSONAL_LOAN_QUOTES);
        rvPLQuotes = (RecyclerView) findViewById(R.id.rvPLQuotes);
        mAdapter = new PLQuoteAdapter(PersonalLoanQuoteActivity.this, getPersonalLoanResponse.getData());
        rvPLQuotes.setAdapter(mAdapter);
        rvPLQuotes.setLayoutManager(new LinearLayoutManager(this));
        personalLoanRequest = getIntent().getParcelableExtra(Constants.PL_REQUEST);
    }

    public void redirectToApplyLoan(PersonalQuoteEntity entity) {

    /*    new QuoteController(this).sendSelectedQuoteInfo(String.valueOf(getPersonalLoanResponse.getQuote_id()),
                String.valueOf(entity.getBank_Id()),
                String.valueOf(entity.getRoi_type()),
                String.valueOf(entity.getLoan_eligible()),
                String.valueOf(entity.getProcessingfee()));*/
        startActivity(new Intent(this, PersonalLoanApplyActivity.class)
                .putExtra("PL", entity)
                .putExtra("PL_URL", getPersonalLoanResponse.getUrl())
                .putExtra("PL_QUOTE_ID", getPersonalLoanResponse.getQuote_id()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.pl_menu, menu);//Menu Resource, Menu
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.btnMenuShare:
                boolean result = Utility.checkPermission(PersonalLoanQuoteActivity.this);
                if (result) {
                    shareData();

                }
                return true;
            case R.id.btnAddLead:
                startActivity(new Intent(PersonalLoanQuoteActivity.this, RbAddLeadActivity.class)
                        .putExtra(Constants.PL_REQUEST, personalLoanRequest));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void shareData() {
        View rootView = getWindow().getDecorView().findViewById(R.id.rvPLQuotes);

        datashareList(getScreenShot(rootView), "Quotes Details", "");

    }


    public Bitmap getScreenShot(View view) {
        View screenView = view.getRootView();
        screenView.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(screenView.getDrawingCache());
        screenView.setDrawingCacheEnabled(false);
        return bitmap;
    }


    private void datashareList(Bitmap bitmap, String strSubject, String strDetail) {

        String prdSubject = strSubject;
        String prdDetail = strDetail;
        try {

            //  File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "Screenshots" + System.currentTimeMillis() + ".png");

            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Screenshots", Utility.getCurrentMobileDateTime() + ".png");
            // Utility.getCurrentMobileDateTime()
            file.getParentFile().mkdirs();
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.close();

            Uri screenshotUri = Uri.fromFile(file);

            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_TEXT, prdDetail);

            shareIntent.setType("text/plain");

            PackageManager pm = PersonalLoanQuoteActivity.this.getPackageManager();


            List<ResolveInfo> resInfo = pm.queryIntentActivities(shareIntent, 0);
            List<LabeledIntent> intentList = new ArrayList<LabeledIntent>();
            ///////////
            for (int i = 0; i < resInfo.size(); i++) {
                // Extract the label, append it, and repackage it in a LabeledIntent
                ResolveInfo ri = resInfo.get(i);
                String packageName = ri.activityInfo.packageName;
                String processName = ri.activityInfo.processName;
                String AppName = ri.activityInfo.name;

                if ((packageName.contains("android.email") || packageName.contains("twitter") || (packageName.contains("whatsapp")) || packageName.contains("android.gm") || packageName.contains("com.google.android.apps.plus")) || (packageName.contains("apps.docs")) && processName.contains("android.apps.docs:Clipboard") || (packageName.contains("android.talk")) && AppName.contains("hangouts")) {

                    shareIntent.setComponent(new ComponentName(packageName, ri.activityInfo.name));

                    if (packageName.contains("android.email")) {
                        shareIntent.setType("image/*");
                        shareIntent.putExtra(Intent.EXTRA_SUBJECT, prdSubject);
                        shareIntent.putExtra(Intent.EXTRA_TEXT, prdDetail);
                        shareIntent.setPackage(packageName);

                    } else if (packageName.contains("twitter")) {

                        shareIntent.setType("image/*");
                        shareIntent.putExtra(Intent.EXTRA_SUBJECT, prdSubject);
                        shareIntent.putExtra(Intent.EXTRA_TEXT, prdDetail);
                        shareIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
                        shareIntent.setPackage(packageName);

                    }
//                    else if (packageName.contains("facebook.katana")) {
//                        shareIntent.setType("text/plain");
//                        shareIntent.putExtra(Intent.EXTRA_TEXT, product.getImageUrl());
//                        shareIntent.setPackage("com.facebook.katana");
//                        //shareIntent.putExtra(Intent.EXTRA_STREAM, Deeplink);
//                    }
//                    else if (packageName.contains("facebook.orca")) {
//                        shareIntent.setType("image/*");
//                        shareIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
//                        shareIntent.putExtra(Intent.EXTRA_TEXT, prdDetail);
//                        shareIntent.setPackage("com.facebook.orca");
//
//                    }

                    else if (packageName.contains("whatsapp")) {
                        shareIntent.setType("image/*");

                        shareIntent.putExtra(Intent.EXTRA_TEXT, prdDetail);
                        shareIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
                        shareIntent.setPackage(packageName);


                    } else if (packageName.contains("com.google.android.apps.plus")) {
                        shareIntent.setType("image/*");
                        shareIntent.putExtra(Intent.EXTRA_TEXT, prdDetail);
                        shareIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
                        shareIntent.setPackage(packageName);

                    } else if (packageName.contains("android.talk")) {
                        if (AppName.contains("hangouts")) {
                            shareIntent.setType("image/*");
                            shareIntent.putExtra(Intent.EXTRA_TEXT, prdDetail);
                            shareIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
                            shareIntent.setPackage(packageName);
                        }

                    } else if (packageName.contains("apps.docs")) {
                        if (processName.contains("android.apps.docs:Clipboard")) {
                            shareIntent.setType("text/plain");
                            shareIntent.putExtra(Intent.EXTRA_TEXT, prdDetail);
                            shareIntent.setPackage(packageName);
                        }

                    } else if (packageName.contains("android.gm")) {
                        shareIntent.setType("image/*");
                        shareIntent.putExtra(Intent.EXTRA_SUBJECT, prdSubject);
                        shareIntent.putExtra(Intent.EXTRA_TEXT, prdDetail);
                        shareIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
                        shareIntent.setPackage(packageName);

                    } else {
                        shareIntent.setType("text/plain");
                        shareIntent.putExtra(Intent.EXTRA_TEXT, prdDetail);

                    }

                    intentList.add(new LabeledIntent(shareIntent, packageName, ri.loadLabel(pm), ri.icon));

                }
            }


            if (intentList.size() > 1) {
                intentList.remove(intentList.size() - 1);
            }

            Intent openInChooser = Intent.createChooser(shareIntent, "Share Via");

            // convert intentList to array
            LabeledIntent[] extraIntents = intentList.toArray(new LabeledIntent[intentList.size()]);
            openInChooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, extraIntents);

            startActivity(openInChooser);
        } catch (Exception e) {

            // Toast.makeText(getActivity(), "Please check your permissions settings.Permission issue.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    shareData();
                } else {
                    //code for deny
                }
                break;
        }
    }

}
