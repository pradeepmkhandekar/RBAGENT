package com.rupeeboss.rba.home;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.material.navigation.NavigationView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.rupeeboss.rba.BaseActivity;
import com.rupeeboss.rba.DocumentUpload.DocumentUpload;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.calling.CallingFragment;
import com.rupeeboss.rba.changepassword.ChangePasswordActivity;
import com.rupeeboss.rba.contactToManager.ContactToManager;
import com.rupeeboss.rba.contactus.ContactUsActivity;
import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.controller.authenticate.Authentication;
import com.rupeeboss.rba.core.controller.login.LoginController;
import com.rupeeboss.rba.core.controller.sync.SyncController;
import com.rupeeboss.rba.core.facade.LoginFacade;
import com.rupeeboss.rba.core.response.AppVersionResponse;
import com.rupeeboss.rba.core.response.ProfileResponse;
import com.rupeeboss.rba.creditcard.CreditCardFragment;
import com.rupeeboss.rba.dashboard.DashboardFragment;
import com.rupeeboss.rba.fragment.AddLeadFragment;
import com.rupeeboss.rba.fragment.MyLeadFragment;
import com.rupeeboss.rba.fragment.PolicyBossWebviewFragment;
import com.rupeeboss.rba.fragment.ShareMessageFragment;
import com.rupeeboss.rba.loan.LoanMenuFragment;
import com.rupeeboss.rba.login.LoginActivity;
import com.rupeeboss.rba.mylead.MyLeadActivity;
import com.rupeeboss.rba.mylist.GroupListActivity;
import com.rupeeboss.rba.repository.RepositoryHomeFragment;
import com.rupeeboss.rba.utility.Constants;
import com.rupeeboss.rba.utility.Utility;
import com.rupeeboss.rba.utility.imagecropper.BitmapUtil;
import com.rupeeboss.rba.utility.imagecropper.CropHandler;
import com.rupeeboss.rba.utility.imagecropper.CropHelper;
import com.rupeeboss.rba.utility.imagecropper.CropParams;
import com.rupeeboss.rba.webviews.agreement.AgreementWebViewFragment;
import com.rupeeboss.rba.webviews.commonwebview.CommonWebviewActivity;
import com.rupeeboss.rba.webviews.document.DocumentWebViewActivity;
import com.rupeeboss.rba.webviews.generalInsurance.GeneralInsuranceType;
import com.rupeeboss.rba.webviews.usermanual.UserManualWebviewFragment;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;


public class MainActivity extends BaseActivity implements IResponseSubcriber, View.OnClickListener, CropHandler {
    int localAppVersionCode, serverAppVersion;
    private NavigationView navigationView;
    private DrawerLayout drawer;
    private View navHeader;
    boolean doubleBackToExitPressedOnce = false;
    // index to identify current nav menu item
    public static int navItemIndex = 0;

    String[] perms = {"android.permission.WRITE_EXTERNAL_STORAGE",
            "android.permission.READ_EXTERNAL_STORAGE",
    };


    // tags used to attach the fragments
    private static final String TAG_HOME = "Home";
    private static final String TAG_YESBANK_BOT = "Yes Bank Bot";
    private static final String TAG_LOAN = "Loan";
    private static final String TAG_CREDIT_CARD = "Credit Card";
    private static final String TAG_CALLING = "Calling";
    private static final String SHARETEXT = "Share Links";
    private static final String TAG_ADDLEAD = "Add Lead";
    private static final String TAG_MYLEAD = "My Lead";
    private static final String TAG_DOCUMENT = "Document Checklist";
    private static final String TAG_POLICYBOSS = "policyboss_webview";
    private static final String USER_MANUAL = "User Manual";
    private static final String TAG_AGRREMENT = "Agreement";
    private static final String TAG_REPOSITORY = "Repositories";
    public static String CURRENT_TAG = TAG_HOME;

    final private int REQUEST_CODE_ASK_PERMISSIONS = 1111;

    CropParams mCropParams;
    private String userChoosenTask;
    public static final String TAG = "Pic";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    // toolbar titles respected to selected nav menu item
    //private String[] activityTitles;

    // flag to load home fragment when user presses back key
    private boolean shouldLoadHomeFragOnBackPress = true;
    private Handler mHandler;

    private Toolbar toolbar;
    CircleImageView imgUser;
    private CropHelper cropHelper;
    LoginFacade loginFacade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Allowing Strict mode policy for Nougat support
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        mHandler = new Handler();
        sharedPreferences = getSharedPreferences(Constants.SHAREDPREFERENCE_TITLE, MODE_PRIVATE);
        editor = sharedPreferences.edit();
        drawer = (DrawerLayout) findViewById(R.id.drawer);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);

        cropHelper = new CropHelper(getApplicationContext());
        loginFacade = new LoginFacade(this);
        // initializing navigation menu
        setUpNavigationView();
        BindNavigationProfile();

        // new SyncController(MainActivity.this).getCity();
        new SyncController(MainActivity.this).getProducts();
        new SyncController(MainActivity.this).getProperty();

        new LoginController(this).login(loginFacade.getPanNumber(), loginFacade.getPassword(), loginFacade.getDeviceId(), "","N", this);

        localAppVersionCode = Utility.getVersionCode(MainActivity.this);
        serverAppVersion = Integer.parseInt(loginFacade.getUser().getVersionCode());
        Log.d("APP_VERSION", "Local = " + localAppVersionCode + "Market = " + serverAppVersion);
        if (localAppVersionCode < serverAppVersion) {
            showDialog("A new Version is available on Play Store !!!Please Update.");
            return;
        }
        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_HOME;
            loadHomeFragment("Home");
        }

        getProfilePic();
    }

    private boolean checkPermission() {

        int writeLogResult = ContextCompat.checkSelfPermission(getApplicationContext(), perms[0]);
        int readResult = ContextCompat.checkSelfPermission(getApplicationContext(), perms[1]);


        return writeLogResult == PackageManager.PERMISSION_GRANTED
                && readResult == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, perms, REQUEST_CODE_ASK_PERMISSIONS);
    }

    private void openAppMarketPlace() {

        final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }

    }

    private void showDialog(String msg) {
       /* android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle(msg);
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                openAppMarketPlace();
            }
        });
        alertDialog.setCancelable(false);
        alertDialog.show();*/


        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.update_dialog);
        dialog.setCancelable(false);
        // dialog.setTitle("Title...");

        // set the custom dialog components - text, image and button
        TextView txtMsg = (TextView) dialog.findViewById(R.id.txtMsg);
        txtMsg.setText(msg);
        /*ImageView image = (ImageView) dialog.findViewById(R.id.image);
        image.setImageResource(R.drawable.ic_launcher);*/
        ConstraintLayout clUpdate = (ConstraintLayout) dialog.findViewById(R.id.clUpdate);
        //TextView btnOk = (TextView) dialog.findViewById(R.id.tvOk);
        // if button is clicked, close the custom dialog
        clUpdate.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            dialog.dismiss();
                                            finish();
                                            openAppMarketPlace();
                                        }
                                    }
        );
        dialog.show();
    }

    private void getProfilePic() {

        try {
            String imageProfile = new LoginFacade(MainActivity.this).getUserProfile();

            if (!imageProfile.equals("")) {
                //Bitmap photo = Utility.convertBase64ToBitmap(imageProfile);
                Glide.with(this)
                        .load(imageProfile)
                        .crossFade()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imgUser);

                // imgUser.setImageBitmap(photo);

            } else {
                imgUser.setImageDrawable(getResources().getDrawable(R.drawable.contact));
            }


        } catch (Exception ex) {

            imgUser.setImageBitmap(null);
        }
    }


    public boolean clearProfile() {
        String imagePath = Environment.getExternalStorageDirectory()
                + "/Android/data/"
                + MainActivity.this.getApplicationContext().getPackageName()
                + "/" + CropHelper.CROP_CACHE_FOLDER;
        File fileProfile = new File(imagePath);
        if (fileProfile.exists() && fileProfile.listFiles() != null) {
            for (File file : fileProfile.listFiles()) {
                boolean result = file.delete();
            }
            return true;
        }
        return false;
    }


    private void setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.nav_home:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_HOME;
                        break;
                    case R.id.nav_manager:
                        startActivity(new Intent(MainActivity.this, ContactToManager.class));
                        break;
                    case R.id.nav_contact_us:
                        startActivity(new Intent(MainActivity.this, ContactUsActivity.class));
                        break;
                    case R.id.nav_upload_document:
                        startActivity(new Intent(MainActivity.this, DocumentUpload.class));
                        break;

                    case R.id.nav_income_simulator:
                        startActivity(new Intent(MainActivity.this, GeneralInsuranceType.class));
                        //   startActivity(new Intent(MainActivity.this, IncomeSimulatorActivity.class));
                        break;
                    case R.id.nav_change_pwd:
                        startActivity(new Intent(MainActivity.this, ChangePasswordActivity.class));
                        break;
                    case R.id.nav_mylist:
                        startActivity(new Intent(MainActivity.this, GroupListActivity.class));
                        break;
                    case R.id.nav_loan:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_LOAN;
                        break;
                    case R.id.nav_credit_card:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_CREDIT_CARD;
                        break;

                    case R.id.nav_calling:
                        navItemIndex = 3;
                        CURRENT_TAG = TAG_CALLING;
                        break;
                    case R.id.sharetext:
                        navItemIndex = 4;
                        CURRENT_TAG = SHARETEXT;
                        break;

                    case R.id.nav_addLead:
                        navItemIndex = 5;
                        CURRENT_TAG = TAG_ADDLEAD;
                        break;
                    case R.id.nav_MyLead:
                        startActivity(new Intent(MainActivity.this, MyLeadActivity.class));
                        break;
                    case R.id.nav_docRequired:
                       /* navItemIndex = 7;
                        CURRENT_TAG = TAG_DOCUMENT;*/
                        startActivity(new Intent(MainActivity.this, DocumentWebViewActivity.class));
                        break;
                    case R.id.nav_policyboss:
                        navItemIndex = 8;
                        CURRENT_TAG = TAG_POLICYBOSS;
                        break;
                    case R.id.nav_usermanual:
                        navItemIndex = 9;
                        CURRENT_TAG = USER_MANUAL;
                        break;

                    case R.id.nav_agreement:
                        navItemIndex = 10;
                        CURRENT_TAG = TAG_AGRREMENT;
                        break;
                    case R.id.nav_repository:
                        navItemIndex = 11;
                        CURRENT_TAG = TAG_REPOSITORY;
                        break;
                    case R.id.nav_yes_bank_bot:
                        String url;
                        if (loginFacade.getUser().getBrokerId() == 0) {
                            url = "https://yesbankbot.buildquickbots.com/chat/rupeeboss/staff/?userid=" + loginFacade.getUser().getEmpCode() + "&usertype=RBA&vkey=b34f02e9-8f1c";
                        } else {
                            url = "https://yesbankbot.buildquickbots.com/chat/rupeeboss/staff/?userid=" + loginFacade.getUser().getBrokerId() + "&usertype=RBA&vkey=b34f02e9-8f1c";
                        }

                        /*String url = "https://yesbankbot.buildquickbots.com/chat/rupeeboss_uat/staff/?userid=" + loginFacade.getUser().getEmpCode() +
                                "&usertype=RBA&vkey=8da76ddb-49cb-4c84-b14e-b72d951e2495";*/
                        startActivity(new Intent(MainActivity.this, CommonWebviewActivity.class)
                                .putExtra("URL", url)
                                .putExtra("NAME", "YES BANK BOT")
                                .putExtra("TITLE", "YES BANK BOT"));
                        break;

                    case R.id.nav_logout:
                        finish();

                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        new LoginFacade(MainActivity.this).clearLoginCache();
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);

                        break;


                    default:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_HOME;
                        break;
                }

                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }
                menuItem.setChecked(true);

                loadHomeFragment(CURRENT_TAG);

                return true;
            }
        });


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawer.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
            return;
        }

        // This code loads home fragment when back key is pressed
        // when user is in other fragment than home
        if (shouldLoadHomeFragOnBackPress) {
            // checking if user is on other navigation menu
            // rather than home
            if (navItemIndex != 0) {
                navItemIndex = 0;
                CURRENT_TAG = TAG_HOME;
                loadHomeFragment(CURRENT_TAG);
                return;
            } else {
                if (doubleBackToExitPressedOnce) {
                    super.onBackPressed();
                    return;
                }

                this.doubleBackToExitPressedOnce = true;

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        doubleBackToExitPressedOnce = false;
                    }
                }, 2000);
            }
        }

        // super.onBackPressed();
    }

    private void setToolbarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }


    private Fragment getHomeFragment() {
        Fragment fragment = null;
        switch (navItemIndex) {
            case 0:
                // home
                fragment = new DashboardFragment();
                return fragment;
            case 1:
                // photos
                fragment = new LoanMenuFragment();
                return fragment;
            case 2:
                //Credit Card
                fragment = new CreditCardFragment();
                return fragment;
            case 3:
                // calling
                fragment = new CallingFragment();
                return fragment;
            case 4:
                // calling
                fragment = new ShareMessageFragment();
                return fragment;

            case 5:
                // calling
                fragment = new AddLeadFragment();
                return fragment;

//               fragment = new AddLeadFragment();
//            getSupportActionBar().setTitle("Lead Capture");
//            return fragment;
            case 6:
                fragment = new MyLeadFragment();
                return fragment;
           /* case 7:
                fragment = new DocumentFragment();
                return fragment;
                startActivity(new Intent(MainActivity.this, DocumentWebViewActivity.class));
                break;*/
            case 8:
                fragment = new PolicyBossWebviewFragment();
                return fragment;
            case 9:
                fragment = new UserManualWebviewFragment();
                getSupportActionBar().setTitle("User Manual");
                return fragment;

            case 10:
                fragment = new AgreementWebViewFragment();
                getSupportActionBar().setTitle("Agreement");
                return fragment;
            case 11:
                fragment = new RepositoryHomeFragment();
                getSupportActionBar().setTitle("Repositories");
                return fragment;
            default:
                fragment = new DashboardFragment();
                return fragment;
        }
    }

    private void loadHomeFragment(String title) {
        // selecting appropriate nav menu item
        selectNavMenu();

        // set toolbar title
        setToolbarTitle(title);

        // if user select the current navigation menu again, don't do anything
        // just close the navigation drawer
        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer.closeDrawers();

            return;
        }

        // Sometimes, when fragment has huge data, screen seems hanging
        // when switching between navigation menus
        // So using runnable, the fragment is loaded with cross fade effect
        // This effect can be seen in GMail app
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }

        // show or hide the fab button
        // toggleFab();

        //Closing drawer on item click
        drawer.closeDrawers();

        // refresh toolbar menu
        invalidateOptionsMenu();
    }

    private void BindNavigationProfile() {

        View profileView = navigationView.getHeaderView(0);
        TextView txtUserName = (TextView) profileView.findViewById(R.id.txtUserName);
        TextView txtRBA = (TextView) profileView.findViewById(R.id.txtRBA);
        imgUser = (CircleImageView) profileView.findViewById(R.id.imgUser);
        if (new LoginFacade(MainActivity.this).getUser().getUName().equals("")) {
            txtUserName.setText(new LoginFacade(MainActivity.this).getUser().getBrokerName());
        } else {
            txtUserName.setText(new LoginFacade(MainActivity.this).getUser().getUName());
        }
        txtRBA.setText(new LoginFacade(MainActivity.this).getUser().getEmpCode());
//        Button nav_footer_logout = (Button) findViewById(R.id.nav_footer_logout);
//        nav_footer_logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//                new LoginFacade(MainActivity.this).clearLoginCache();
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);
//            }
//        });

        mCropParams = new CropParams(this);  // initializing CropParms
        imgUser.setOnClickListener(this);


    }

    @Override
    public void OnSuccess(APIResponse response, String message) throws InterruptedException {

        dismissDialog();
        if (response instanceof ProfileResponse) {
            if (response.getStatus_Id() == 0) {

                if (((ProfileResponse) response).getProfilePic() != null) {
                    getProfilePic();
                }
            }

        } else if (response instanceof AppVersionResponse) {
            if (response.getStatusId() == 0) {
                if ((((AppVersionResponse) response).getVersion_code() != null) || (((AppVersionResponse) response).getVersion_code().toString() != "")) {
                    if (localAppVersionCode < Integer.parseInt(((AppVersionResponse) response).getVersion_code())) {
                        showDialog("A new Version is available on Play Store !!!Please Update.");
                        return;
                    }

                }
            }
        }

    }

    @Override
    public void OnFailure(Throwable t) {

        imgUser.setImageDrawable(getResources().getDrawable(R.drawable.contact));
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (userChoosenTask.equals("Take Photo"))
                        cameraIntent();
                    else if (userChoosenTask.equals("Choose from Library"))
                        galleryIntent();
                } else {
                    //code for deny
                }
                break;
        }
    }


    // region Crop Handler
    @Override
    public void onPhotoCropped(Uri uri) {
        // Original or Cropped uri
        Log.d(TAG, "Crop Uri in path: " + uri.getPath());
        if (!mCropParams.compress) {

            updateProfilePic(BitmapUtil.decodeUriAsBitmap(this, uri));
        }
    }

    @Override
    public void onCompressed(Uri uri) {

        updateProfilePic(BitmapUtil.decodeUriAsBitmap(this, uri));
    }

    @Override
    public void onCancel() {
        Toast.makeText(this, "upload cancelled!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFailed(String message) {

        Toast.makeText(this, "upload failed: " + message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void handleIntent(Intent intent, int requestCode) {
        startActivityForResult(intent, requestCode);
    }

    @Override
    public CropParams getCropParams() {
        return mCropParams;
    }

    //endregion

    //region Camera and Gallery
    private void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Profile Photo");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                // boolean result = Utility.checkPermission(MainActivity.this);
                if (!checkPermission()) {
                    requestPermission();
                } else {
                    boolean result = checkPermission();
                    if (items[item].equals("Take Photo")) {
                        userChoosenTask = "Take Photo";
                        if (result)
                            cameraIntent();

                    } else if (items[item].equals("Choose from Library")) {
                        userChoosenTask = "Choose from Library";
                        if (result)
                            galleryIntent();

                    } else if (items[item].equals("Cancel")) {
                        dialog.dismiss();
                    }
                }
            }
        });
        builder.show();
    }

    private void cameraIntent() {
        mCropParams.refreshUri();

        launchCamera();
    }

    private void launchCamera() {
        try {
            //  use standard intent to capture an image
//            Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//            captureIntent.putExtra("android.intent.extras.CAMERA_FACING", 1);
//            startActivityForResult(captureIntent,  CropHelper.REQUEST_CAMERA);

            //we will handle the returned data in onActivityResult

            /// For Crop Enable or Disabling
            mCropParams.enable = true;
            Intent captureIntent = CropHelper.buildCameraIntent(mCropParams);
            captureIntent.putExtra("android.intent.extras.CAMERA_FACING", 1);

            captureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);    // rahul05
            startActivityForResult(captureIntent, CropHelper.REQUEST_CAMERA);

        } catch (ActivityNotFoundException anfe) {
            //display an error message
            String errorMessage = "Whoops - your device doesn't support capturing images!";
            Toast toast = Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_SHORT);
            toast.show();
        }
    }


    private void galleryIntent() {
        mCropParams.enable = true;
        Intent intent = CropHelper.buildGalleryIntent(mCropParams);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);    // rahul05
        startActivityForResult(intent, CropHelper.REQUEST_CROP);
    }
    //endregion

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imgUser) {
            selectImage();
        }

    }

    private void updateProfilePic(Bitmap bitmap) {

        String base64String = Utility.convertBitmapToBase64(bitmap);

        new LoginController(this).uploadProfilePicture(new LoginFacade(MainActivity.this).getPanNumber(), base64String, this);
        Log.d("Base64", base64String);

        CropHelper.clearCacheDir();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        CropHelper.handleResult(this, requestCode, resultCode, data);
        if (requestCode == 1) {
            Log.e(TAG, "");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        showProgressDialog();
        new Authentication(MainActivity.this).getApiVersionCode("2", this);   //    RB Caller App :  appTypeId = "2"
    }
}
