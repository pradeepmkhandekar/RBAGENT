package com.rupeeboss.rba;

import android.app.Dialog;
import android.app.ProgressDialog;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.os.Environment;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.rupeeboss.rba.utility.Utility;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Nilesh Birhade on 16-01-2017.
 */

public class BaseActivity extends AppCompatActivity {

    private ProgressDialog dialog;
    private static final CharSequence LOADING = "Loading...";
    PopUpListener popUpListener;

    int height = 400;
    int textSize = 70;
    int textMargin = 15;
    int startHeight = (height - (4 * textSize) - (3 * textMargin)) / 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void showProgressDialog() {
        dialog = ProgressDialog.show(this, "", LOADING, false);
    }

    public void dismissDialog() {
        if (dialog != null) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }
    public static boolean validateEmailAddress(EditText editText) {
        String emailEntered = editText.getText().toString().trim();
        if (emailEntered.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(emailEntered).matches()) {
            return false;
        }
        return true;
    }

    public static boolean validatePhoneNumber(EditText editText) {
        String phoneNumberPattern = "^(?:(?:\\+|0{0,2})91(\\s*[\\-]\\s*)?|[0]?)?[789]\\d{9}$";
        String phoneNumberEntered = editText.getText().toString().trim();
        if (phoneNumberEntered.isEmpty() || !phoneNumberEntered.matches(phoneNumberPattern)) {
            return false;
        }
        return true;
    }
    public static boolean isValidPan(String Pan) {
//        String rx = "/[A-Z]{5}[0-9]{4}[A-Z]{1}$/";
        Pattern pattern = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}");
        Matcher matcher = pattern.matcher(Pan);
        if (matcher.matches()) {
            return true;

        } else {
            return false;
        }
    }
    protected void cancelDialog() {
        if (dialog != null) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }



    public void showDialog() {
        dialog = ProgressDialog.show(this, "Loading...", LOADING, false);
    }

    public void openPopUp(final View view, String title, String desc, String positiveButtonName, boolean isCancelable) {
        try {
            final Dialog dialog;
            dialog = new Dialog(BaseActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.layout_common_popup);

            TextView tvTitle = (TextView) dialog.findViewById(R.id.tvTitle);
            tvTitle.setText(title);
            TextView tvOk = (TextView) dialog.findViewById(R.id.tvOk);
            tvOk.setText(positiveButtonName);
            TextView txtMessage = (TextView) dialog.findViewById(R.id.txtMessage);
            txtMessage.setText(desc);
            ImageView ivCross = (ImageView) dialog.findViewById(R.id.ivCross);

            dialog.setCancelable(isCancelable);
            dialog.setCanceledOnTouchOutside(isCancelable);

            Window dialogWindow = dialog.getWindow();
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.width = lp.MATCH_PARENT;  // Width
            lp.height = lp.WRAP_CONTENT; // Height
            dialogWindow.setAttributes(lp);

            dialog.show();
            tvOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Close dialog
                    if (popUpListener != null)
                        popUpListener.onPositiveButtonClick(dialog, view);
                }
            });

            ivCross.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Close dialog
                    if (popUpListener != null)
                        popUpListener.onCancelButtonClick(dialog, view);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registerPopUp(PopUpListener popUpListener) {
        if (popUpListener != null)
            this.popUpListener = popUpListener;
    }

    public interface PopUpListener {

        void onPositiveButtonClick(Dialog dialog, View view);

        void onCancelButtonClick(Dialog dialog, View view);
    }



    public Bitmap createBitmap(Bitmap pospPhoto, String pospName, String pospMob, String pospDesg, String pospEmail) {

       // pospPhoto = Bitmap.createScaledBitmap(pospPhoto, height + -20, height - 20, false);

        Bitmap textBitmap = Bitmap.createBitmap(2000, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(textBitmap);
        canvas.drawColor(Color.WHITE);

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(textSize);

        Paint paintBold = new Paint();
        paintBold.setColor(Color.BLACK);
        paintBold.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        paintBold.setTextSize(textSize + 1);
        startHeight += 20;
        canvas.drawText("" + pospName, height + 250, startHeight + textMargin, paintBold);
        canvas.drawText("" + pospMob, height + 250, startHeight + 1 * textSize + 2 * textMargin, paint);
        canvas.drawText("" + pospDesg, height + 250, startHeight + 2 * textSize + 3 * textMargin, paint);
        canvas.drawText("" + pospEmail, height + 250, startHeight + 3 * textSize + 4 * textMargin, paint);
        //canvas.drawText("" + pospName + "\n" + pospDesg + "\n" + pospMob + "\n" + pospEmail, 10, 10, paint);


        Bitmap cs = null;

        cs = Bitmap.createBitmap(2000, height, Bitmap.Config.ARGB_8888);

        Canvas comboImage = new Canvas(cs);

        comboImage.drawBitmap(textBitmap, 0f, 0f, null);
        comboImage.drawBitmap(pospPhoto, 0f, 15f, null);


        return cs;
    }

    public File saveImageToStorage(Bitmap bitmap, String name) {
        FileOutputStream outStream = null;

        File dir = Utility.createDirIfNotExists();
        String fileName = name + ".jpg";
        fileName = fileName.replaceAll("\\s+", "");
        File outFile = new File(dir, fileName);
        try {
            outStream = new FileOutputStream(outFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 70, outStream);
            outStream.flush();
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outFile;
    }
    public class datashareListBitmap extends AsyncTask<Void, Void, Void> {

        Context context;
        Bitmap bitmap; String prdSubject;
        String prdDetail;
        File file;

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            showDialog();
        }


        public datashareListBitmap(Context context, Bitmap bitmap, String prdSubject, String prdDetail) {

            this.context = context;
            this.bitmap = bitmap;
            this.prdSubject = prdSubject;
            this.prdDetail =  prdDetail ;


        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "RupeeBoss_product.jpg");

                file.getParentFile().mkdirs();
                FileOutputStream out = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                out.close();

            }catch (Exception ex)
            {
                cancelDialog();
            }

            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            cancelDialog();
            datashareList(context, file, "RupeeBoss", "");
        }


    }

    public void datashareList(Context context, File file, String prdSubject, String prdDetail) {


        try {


//            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "RupeeBoss_product.jpg");
//
//            file.getParentFile().mkdirs();
//            FileOutputStream out = new FileOutputStream(file);
//            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
//            out.close();

            //  Uri screenshotUri = Uri.fromFile(file);
            Uri screenshotUri = FileProvider.getUriForFile(BaseActivity.this,
                    getString(R.string.file_provider_authority),
                    file);

            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_TEXT, prdDetail);

            shareIntent.setType("text/plain");

            PackageManager pm = context.getPackageManager();


            List<ResolveInfo> resInfo = pm.queryIntentActivities(shareIntent, 0);
            List<LabeledIntent> intentList = new ArrayList<LabeledIntent>();

            for (int i = 0; i < resInfo.size(); i++) {
                // Extract the label, append it, and repackage it in a LabeledIntent
                ResolveInfo ri = resInfo.get(i);
                String packageName = ri.activityInfo.packageName;
                String processName = ri.activityInfo.processName;
                String AppName = ri.activityInfo.name;

                if ((packageName.contains("android.email") || packageName.contains("mms") || packageName.contains("twitter") || (packageName.contains("whatsapp")) || packageName.contains("messaging") || packageName.contains("android.gm") || packageName.contains("com.google.android.apps.plus")) || (packageName.contains("apps.docs")) && processName.contains("android.apps.docs:Clipboard") || (packageName.contains("android.talk")) && AppName.contains("hangouts")) {

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
                    else if (packageName.contains("mms")) {
                        shareIntent.setType("text/plain");
                        shareIntent.putExtra(Intent.EXTRA_TEXT, prdDetail);
                        shareIntent.setPackage(packageName);

                    } else if (packageName.contains("whatsapp")) {
                        shareIntent.setType("image/*");

                        shareIntent.putExtra(Intent.EXTRA_TEXT, prdDetail);
                        shareIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
                        shareIntent.setPackage(packageName);


                    } else if (packageName.contains("messaging")) {
                        shareIntent.setType("text/plain");
                        shareIntent.putExtra(Intent.EXTRA_TEXT, prdDetail);
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
            e.printStackTrace();
        }

    }


    public Bitmap combineImages(Bitmap first, Bitmap second) { // can add a 3rd parameter 'String loc' if you want to save the new image - left some code to do that at the bottom
        Bitmap cs = null;

        int width, height = 0;
        width = first.getWidth();
        height = first.getHeight() + second.getHeight();

        cs = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        Canvas comboImage = new Canvas(cs);

        comboImage.drawBitmap(first, 0f, 0f, null);
        comboImage.drawBitmap(second, 0f, first.getHeight(), null);

        // this is an extra bit I added, just incase you want to save the new image somewhere and then return the location
    /*String tmpImg = String.valueOf(System.currentTimeMillis()) + ".png";

    OutputStream os = null;
    try {
      os = new FileOutputStream(loc + tmpImg);
      cs.compress(CompressFormat.PNG, 100, os);
    } catch(IOException e) {
      Log.e("combineImages", "problem combining images", e);
    }*/

        return cs;
    }

    public File getImageFromStorage(String name) {
        try {
            File dir = Utility.createDirIfNotExists();
            String fileName = name + ".jpg";
            File outFile = new File(dir, fileName);
            return outFile;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
