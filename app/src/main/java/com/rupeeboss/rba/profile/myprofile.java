package com.rupeeboss.rba.profile;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.rupeeboss.rba.BaseActivity;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.controller.login.LoginController;
import com.rupeeboss.rba.core.facade.LoginFacade;
import com.rupeeboss.rba.core.response.ProfileResponse;
import com.rupeeboss.rba.home.MainActivity;
import com.rupeeboss.rba.utility.CircleTransform;
import com.rupeeboss.rba.utility.Constants;
import com.rupeeboss.rba.utility.Utility;
import com.rupeeboss.rba.utility.imagecropper.CropHelper;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;


public class myprofile extends BaseActivity implements View.OnClickListener, IResponseSubcriber, BaseActivity.PopUpListener {

    private static final int CAMERA_REQUEST = 1888;
    private static final int SELECT_PICTURE = 1800;


    LoginFacade loginFacade;
    String brokerId, empCode;

    TextView txtProfileName;
    ImageView ivUser;
    Bitmap bitmapPhoto;
    LinearLayout ll_Login, ll_Notification, ll_Password, ll_Documents, ll_Club, ll_RewardPoint;

    private String PHOTO_File = "Photograph";
    File Docfile;
    File file;
    Uri imageUri;
    private Uri cropImageUri;
    InputStream inputStream;
    ExifInterface ei;



    String[] perms = {
            "android.permission.CAMERA",
            "android.permission.WRITE_EXTERNAL_STORAGE",
            "android.permission.READ_EXTERNAL_STORAGE"

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        loginFacade = new LoginFacade(this);
        empCode = loginFacade.getUser().getEmpCode();



        initialize();

        setListener();

        txtProfileName.setText(""+ loginFacade.getUser().getBrokerName());
        getProfilePic();
    }

    private void setListener() {

        ivUser.setOnClickListener(this);
        ll_Login.setOnClickListener(this);
        ll_Notification.setOnClickListener(this);

        ll_Password.setOnClickListener(this);
        ll_Documents.setOnClickListener(this);
        ll_Club.setOnClickListener(this);
        ll_RewardPoint.setOnClickListener(this);

    }

    private void initialize() {


        txtProfileName = findViewById(R.id.txtProfileName);
        ivUser = findViewById(R.id.ivUser);

        ll_Login = findViewById(R.id.ll_Login);
        ll_Notification = findViewById(R.id.ll_Notification);
        ll_Password = findViewById(R.id.ll_Password);
        ll_Documents = findViewById(R.id.ll_Documents);

        ll_Club = findViewById(R.id.ll_Club);
        ll_RewardPoint = findViewById(R.id.ll_RewardPoint);
    }

    // region permission
    private boolean checkPermission() {

        int camera = ActivityCompat.checkSelfPermission(getApplicationContext(), perms[0]);

        int WRITE_EXTERNAL = ActivityCompat.checkSelfPermission(getApplicationContext(), perms[1]);
        int READ_EXTERNAL = ActivityCompat.checkSelfPermission(getApplicationContext(), perms[2]);

        return camera == PackageManager.PERMISSION_GRANTED
                && WRITE_EXTERNAL == PackageManager.PERMISSION_GRANTED
                && READ_EXTERNAL == PackageManager.PERMISSION_GRANTED;
    }

    private boolean checkRationalePermission() {

        boolean camera = ActivityCompat.shouldShowRequestPermissionRationale(myprofile.this, perms[0]);

        boolean write_external = ActivityCompat.shouldShowRequestPermissionRationale(myprofile.this, perms[1]);
        boolean read_external = ActivityCompat.shouldShowRequestPermissionRationale(myprofile.this, perms[2]);

        return camera || write_external || read_external;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, perms, Constants.PERMISSION_CAMERA_STORACGE_CONSTANT);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        switch (requestCode) {
            case Constants.PERMISSION_CAMERA_STORACGE_CONSTANT:
                if (grantResults.length > 0) {

                    //boolean writeExternal = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                    boolean camera = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean writeExternal = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    boolean readExternal = grantResults[2] == PackageManager.PERMISSION_GRANTED;

                    if (camera && writeExternal && readExternal) {

                        showCamerGalleryPopUp();

                    }

                }
                break;


        }
    }

    //endregion


    private void galleryCamPopUp() {

        if (!checkPermission()) {

            if (checkRationalePermission()) {
                //Show Information about why you need the permission
                requestPermission();

            } else {
                //Previously Permission Request was cancelled with 'Dont Ask Again',
                // Redirect to Settings after showing Information about why you need the permission

                //  permissionAlert(navigationView,"Need Call Permission","This app needs Call permission.");
                openPopUp(ll_Login, "Need  Permission", "This app needs all permissions.", "GRANT", true);


            }
        } else {

            showCamerGalleryPopUp();
        }
    }


    private void showCamerGalleryPopUp() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.CustomDialog);

        LinearLayout lyCamera, lyGallery;
        LayoutInflater inflater = this.getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.layout_cam_gallery, null);

        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        // set the custom dialog components - text, image and button
        lyCamera = (LinearLayout) dialogView.findViewById(R.id.lyCamera);
        lyGallery = (LinearLayout) dialogView.findViewById(R.id.lyGallery);

        lyCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchCamera();
                alertDialog.dismiss();

            }
        });

        lyGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
                alertDialog.dismiss();

            }
        });
        alertDialog.setCancelable(true);
        alertDialog.show();
        //  alertDialog.getWindow().setLayout(900, 600);

        // for user define height and width..
    }


    private void launchCamera() {


        String FileName = "";

        FileName = PHOTO_File;
        Docfile = createFile(FileName);

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
            imageUri = Uri.fromFile(Docfile);
        } else {
            imageUri = FileProvider.getUriForFile(myprofile.this,
                    getString(R.string.file_provider_authority), Docfile);
        }


        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT,
                imageUri);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }


    private void openGallery() {

        String FileName = "";

        FileName = PHOTO_File;


        Docfile = createFile(FileName);

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }

    public File createFile(String name) {
        FileOutputStream outStream = null;

        File dir = Utility.createDirIfNotExists();
        String fileName = name + ".jpg";
        fileName = fileName.replaceAll("\\s+", "");
        File outFile = new File(dir, fileName);

        return outFile;
    }


    private byte[] bitmapToByte(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    /**
     * //TODO: Crop image activity to crop capture image.
     * Start crop image activity for the given image.
     */
    private void startCropImageActivity(Uri imageUri) {
        CropImage.activity(imageUri)
                .setGuidelines(CropImageView.Guidelines.ON)
                .setMultiTouchEnabled(true)
                .start(this);
    }

    @Override
    @SuppressLint("NewApi")
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Below For Cropping The Camera Image
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            //extractTextFromImage();
            startCropImageActivity(imageUri);
        }
        // Below For Cropping The Gallery Image
        else if (requestCode == SELECT_PICTURE && resultCode == RESULT_OK) {
            Uri selectedImageUri = data.getData();
            startCropImageActivity(selectedImageUri);
        }

        //region Below  handle result of CropImageActivity
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);

            if (resultCode == RESULT_OK) {
                try {
                    cropImageUri = result.getUri();
                    Bitmap mphoto = null;
                    try {
                        mphoto = MediaStore.Images.Media.getBitmap(this.getContentResolver(), cropImageUri);
                        bitmapPhoto = mphoto;

                        updateProfilePic(mphoto);


                    } catch (Exception e) {
                        e.printStackTrace();
                    }


//                            showDialog();
//                            file = saveImageToStorage(mphoto, PHOTO_File);
//                            setProfilePhoto(mphoto);
//                            part = Utility.getMultipartImage(file);
//                            body = Utility.getBody(this, loginEntity.getFBAId(), PROFILE, PHOTO_File);
//
//                            new RegisterController(this).uploadDocuments(part, body, this);


                } catch (Exception e) {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                }

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Toast.makeText(this, "Cropping failed: " + result.getError(), Toast.LENGTH_LONG).show();
            }
        }

        //endregion


    }

    private void setDocumentUpload(Bitmap mphoto) {

        ivUser.setPadding(0, 0, 0, 0);
        if (mphoto != null) {
            Glide.with(myprofile.this)
                    .load(bitmapToByte(mphoto))
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .placeholder(R.drawable.circle_placeholder)
                    .skipMemoryCache(true)
                    .override(100, 100)
                    .transform(new CircleTransform(myprofile.this)) // applying the image transformer
                    .into(ivUser);


        }
        else {
            ivUser.setImageDrawable(getResources().getDrawable(R.drawable.profile));
            ivUser.setBackground(getResources().getDrawable(R.drawable.circle_placeholder));
        }




    }

    private void getProfilePic() {

        try {
            String imageProfile = new LoginFacade(myprofile.this).getUserProfile();

            if (!imageProfile.equals("")) {
                //Bitmap photo = Utility.convertBase64ToBitmap(imageProfile);
                Glide.with(this)
                        .load(imageProfile)
                        .crossFade()
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .placeholder(R.drawable.circle_placeholder)
                        .skipMemoryCache(true)
                        .override(100, 100)
                        .transform(new CircleTransform(myprofile.this)) // applying the image transformer
                        .into(ivUser);


            }
//            else {
//                ivUser.setImageDrawable(getResources().getDrawable(R.drawable.profile));
//                ivUser.setBackground(getResources().getDrawable(R.drawable.circle_placeholder));
//            }


        } catch (Exception ex) {

            ivUser.setImageDrawable(getResources().getDrawable(R.drawable.profile));
            ivUser.setBackground(getResources().getDrawable(R.drawable.circle_placeholder));
        }
    }

    private void updateProfilePic(Bitmap bitmap) {

        showDialog();
        String base64String = Utility.convertBitmapToBase64(bitmap);

        new LoginController(this).uploadProfilePicture(new LoginFacade(myprofile.this).getPanNumber(), base64String, this);
        Log.d("Base64", base64String);

        CropHelper.clearCacheDir();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.ivUser:
                galleryCamPopUp();
                break;
        }

    }

    @Override
    public void OnSuccess(APIResponse response, String message) throws InterruptedException {

        dismissDialog();
        if (response instanceof ProfileResponse) {
            if (response.getStatus_Id() == 0) {

                if (((ProfileResponse) response).getProfilePic() != null) {
                    setDocumentUpload(bitmapPhoto);
                }
            }

        }
    }

    @Override
    public void OnFailure(Throwable t) {

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        supportFinishAfterTransition();
        super.onBackPressed();
    }

    @Override
    public void onPositiveButtonClick(Dialog dialog, View view) {

        dialog.cancel();
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, Constants.REQUEST_PERMISSION_SETTING);

    }

    @Override
    public void onCancelButtonClick(Dialog dialog, View view) {
        dialog.cancel();
    }
}
