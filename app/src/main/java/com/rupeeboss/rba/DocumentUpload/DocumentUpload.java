package com.rupeeboss.rba.DocumentUpload;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.rupeeboss.rba.BaseActivity;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.controller.document.DocumentController;
import com.rupeeboss.rba.core.facade.LoginFacade;
import com.rupeeboss.rba.core.request.requestentity.UploadDocumentRequest;
import com.rupeeboss.rba.core.response.UploadDocumentResponse;
import com.rupeeboss.rba.utility.Utility;
import com.rupeeboss.rba.utility.imagecropper.BitmapUtil;

import java.io.ByteArrayOutputStream;

public class DocumentUpload extends BaseActivity implements View.OnClickListener,IResponseSubcriber {
    private static final int CAMERA_REQUEST = 1888;
    private static final int SELECT_PICTURE = 1800;
    EditText et_doctype;
    ImageView imageView;
    private String userChoosenTask;
    String basestring="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_upload);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        et_doctype = (EditText) findViewById(R.id.et_doctype);
        imageView = (ImageView) this.findViewById(R.id.imagefromcamera);
        Button button = (Button) this.findViewById(R.id.btn_submit);

        int brokerid=  new LoginFacade(DocumentUpload.this).getUser().getBrokerId();
        if(brokerid > 0) {
            imageView.setOnClickListener(this);
            button.setOnClickListener(this);
        }else
        {
            Toast.makeText(this, "Document Upload Not Required For  Employee", Toast.LENGTH_LONG).show();
        }
    }


    public void takeImageFromCamera(View view) {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }



    public static String convertBitmapToBase64(Bitmap bitmap) {
        try {
            if (bitmap != null) {
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 70, os);
                byte[] byteArray = os.toByteArray();
                return Base64.encodeToString(byteArray, Base64.NO_WRAP);
            } else {
                return null;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(DocumentUpload.this);
        builder.setTitle("Profile Photo");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                // boolean result = Utility.checkPermission(MainActivity.this);


                    if (items[item].equals("Take Photo")) {
                        userChoosenTask = "Take Photo";
                        cameraIntent();

                    } else if (items[item].equals("Choose from Library")) {
                        userChoosenTask = "Choose from Library";
                        galleryIntent();

                    } else if (items[item].equals("Cancel")) {
                        dialog.dismiss();
                    }

            }
        });
        builder.show();
    }
    private void cameraIntent() {


        launchCamera();
    }
    private void launchCamera() {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }
    private void galleryIntent() {
        openImageChooser();
    }

    void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap mphoto = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(mphoto);


            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            mphoto.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);

            byte[] byteArray = byteArrayOutputStream .toByteArray();

            basestring = Base64.encodeToString(byteArray, Base64.DEFAULT);
            // String base64String = convertBitmapToBase64(mphoto);



        }
        if (requestCode == SELECT_PICTURE && resultCode == RESULT_OK) {
            Uri selectedImageUri = data.getData();
            if (null != selectedImageUri) {
                // Get the path from the Uri
                String path = getPathFromURI(selectedImageUri);
               // Log.i(TAG, "Image Path : " + path);
                // Set the image in ImageView
                imageView.setImageURI(selectedImageUri);

                updateProfilePic(BitmapUtil.decodeUriAsBitmap(this, selectedImageUri));
            }
        }
    }
    private void updateProfilePic(Bitmap bitmap) {
        basestring="";
        basestring = Utility.convertBitmapToBase64(bitmap);

    }
    public String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imagefromcamera) {
            selectImage();
        }
        if (v.getId() == R.id.btn_submit) {
            Utility.hideKeyBoard(v, DocumentUpload.this);
            if (et_doctype.getText().toString().matches("")) {
                Snackbar.make(et_doctype, "Enter Document Type", Snackbar.LENGTH_LONG).show();
                return;
            }
            if (basestring.toString().matches("")) {
                Snackbar.make(et_doctype, "Select Document Image", Snackbar.LENGTH_LONG).show();
                return;
            }
            UploadDocumentRequest objclss = new UploadDocumentRequest();

            objclss.setRefFBAId(new LoginFacade(DocumentUpload.this).getUser().getBrokerId());
            objclss.setDocType(et_doctype.getText().toString());
            objclss.setDocextension(".JPG");
            objclss.setBytes(basestring);
            showProgressDialog();
            new DocumentController(this).getuploadCustDocuments(objclss, DocumentUpload.this);

          //  Toast.makeText(this, basestring, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void OnSuccess(APIResponse response, String message) throws InterruptedException {
        dismissDialog();
        if (response instanceof UploadDocumentResponse) {
            if (response.getStatus_Id() == 0) {
                  et_doctype.setText("");
                  imageView.setImageResource(R.drawable.uploaddocument);
                  basestring="";
                   Snackbar.make(et_doctype, response.getMessage(), Snackbar.LENGTH_SHORT).show();
                  // Toast.makeText(this, response.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void OnFailure(Throwable t) {
        dismissDialog();
        Snackbar.make(et_doctype, t.getMessage(), Snackbar.LENGTH_SHORT).show();
    }
}
