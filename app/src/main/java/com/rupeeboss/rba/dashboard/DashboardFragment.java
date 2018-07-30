package com.rupeeboss.rba.dashboard;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rupeeboss.rba.BaseFragment;
import com.rupeeboss.rba.EmiCalculator.EmiMenuActivity;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.calling.CallingActivity;
import com.rupeeboss.rba.contact.ContactActivity;
import com.rupeeboss.rba.incomeSimulator.IncomeSimulatorActivity;
import com.rupeeboss.rba.loan.LoanMenuActivity;
import com.rupeeboss.rba.mybuisness.BuisinessActivity;
import com.rupeeboss.rba.rbdialerpad.RbDialerPadActivity;
import com.rupeeboss.rba.sharemessage.ShareMessageActivity;
import com.rupeeboss.rba.utility.Utility;
import com.rupeeboss.rba.webviews.balancetransfer.BalanceTransferType;
import com.rupeeboss.rba.webviews.commonwebview.CommonWebviewActivity;
import com.rupeeboss.rba.webviews.creditcard.CreditCardApplyActivity;

import java.util.ArrayList;
import java.util.List;

import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.DrawableBanner;
import ss.com.bannerslider.events.OnBannerClickListener;
import ss.com.bannerslider.views.BannerSlider;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends BaseFragment implements View.OnClickListener {


    /* LinearLayout ivLoan, ivCreditCard, ivCalling,ivSharetext,ivDialer;*/
    LinearLayout ivDialer, ivContact, ivRoboPlay, ivTextShare, ivLoan, ivCreditcard, ivBalanceTransfer, ivGeneralInsurance, ivMyBusiness, ivRepository;
    TextView textview;

    ImageView imgRoboPlay;
    ImageView imgCalling;
    BannerSlider bannerSlider;
    List<Banner> banners;

    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard_new, container, false);
        //((AppCompatActivity) getActivity()).getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.bg2));
        initialize(view);
        banners = new ArrayList<>();
        //add banner using image url
        // banners.add(new RemoteBanner("Put banner image url here ..."));
        //add banner using resource drawable
        banners.add(new DrawableBanner(R.drawable.rbl_banner));
        banners.add(new DrawableBanner(R.drawable.kotak_banner));
        banners.add(new DrawableBanner(R.drawable.iifl_banner));
        banners.add(new DrawableBanner(R.drawable.hdfc_personal_loan_banner));
        banners.add(new DrawableBanner(R.drawable.hdfc_business_loan));
        banners.add(new DrawableBanner(R.drawable.kotak_home_loan_banner));
        bannerSlider.setBanners(banners);
        setListener();
        /*GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(imgRoboPlay);
        Glide.with(this).load(R.raw.robo)
                .dontTransform()
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageViewTarget);*/

//        GlideDrawableImageViewTarget imageViewTargetCalling = new GlideDrawableImageViewTarget(imgCalling);
//        Glide.with(this).load(R.raw.calling)
//                .dontTransform()
//                .dontAnimate()
//                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                .into(imageViewTargetCalling);

        return view;
    }

    private void setListener() {
        bannerSlider.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void onClick(int position) {
                switch (position) {
                    case 0://rbl_banner
                        startActivity(new Intent(getActivity(), CommonWebviewActivity.class)
                                .putExtra("URL", "http://www.rupeeboss.com/rbl-personal-loan")
                                .putExtra("NAME", "RBL PERSONAL LOAN")
                                .putExtra("TITLE", "RBL PERSONAL LOAN"));
                        break;
                    case 1://kotak_banner
                        startActivity(new Intent(getActivity(), CommonWebviewActivity.class)
                                .putExtra("URL", "http://www.rupeeboss.com/kotak-personal-loan")
                                .putExtra("NAME", "KOTAK PERSONAL LOAN")
                                .putExtra("TITLE", "KOTAK PERSONAL LOAN"));
                        break;
                    case 2://iifl_banner
                        startActivity(new Intent(getActivity(), CommonWebviewActivity.class)
                                .putExtra("URL", "http://www.rupeeboss.com/apply-iifl-loan")
                                .putExtra("NAME", "IIFL PERSONAL LOAN")
                                .putExtra("TITLE", "IIFL PERSONAL LOAN"));
                        break;
                    case 3://hdfc_personal_loan_banner
                        startActivity(new Intent(getActivity(), CommonWebviewActivity.class)
                                .putExtra("URL", "http://www.rupeeboss.com/hdfc-personal-loan")
                                .putExtra("NAME", "HDFC PERSONAL LOAN")
                                .putExtra("TITLE", "HDFC PERSONAL LOAN"));
                        break;
                    case 4://hdfc_business_loan
                        startActivity(new Intent(getActivity(), CommonWebviewActivity.class)
                                .putExtra("URL", "http://www.rupeeboss.com/hdfc-business-loan")
                                .putExtra("NAME", "HDFC BUSINESS LOAN")
                                .putExtra("TITLE", "HDFC BUSINESS LOAN"));
                        break;
                    case 5://kotak_home_loan_banner
                        startActivity(new Intent(getActivity(), CommonWebviewActivity.class)
                                .putExtra("URL", "http://www.rupeeboss.com/kotak-home-loan")
                                .putExtra("NAME", "KOTAK HOME LOAN")
                                .putExtra("TITLE", "KOTAK HOME LOAN"));
                        break;
                }
            }
        });
    }

    private void initialize(View view) {

        bannerSlider = (BannerSlider) view.findViewById(R.id.banner_slider1);
        ivContact = (LinearLayout) view.findViewById(R.id.ivContact);
        ivRoboPlay = (LinearLayout) view.findViewById(R.id.ivRoboPlay);
        ivTextShare = (LinearLayout) view.findViewById(R.id.ivTextShare);
        ivLoan = (LinearLayout) view.findViewById(R.id.ivLoan);
        ivCreditcard = (LinearLayout) view.findViewById(R.id.ivCreditcard);
        ivBalanceTransfer = (LinearLayout) view.findViewById(R.id.ivBalanceTransfer);
        ivGeneralInsurance = (LinearLayout) view.findViewById(R.id.ivGeneralInsurance);
        ivMyBusiness = (LinearLayout) view.findViewById(R.id.ivMyBusiness);
        ivRepository = (LinearLayout) view.findViewById(R.id.ivRepository);
        ivDialer = (LinearLayout) view.findViewById(R.id.ivDialer);
        imgRoboPlay = (ImageView) view.findViewById(R.id.imgRoboPlay);
        imgCalling = (ImageView) view.findViewById(R.id.imgCalling);

        ivDialer.setOnClickListener(this);
        ivContact.setOnClickListener(this);
        ivRoboPlay.setOnClickListener(this);
        ivTextShare.setOnClickListener(this);
        ivLoan.setOnClickListener(this);
        ivCreditcard.setOnClickListener(this);
        ivBalanceTransfer.setOnClickListener(this);
        ivGeneralInsurance.setOnClickListener(this);
        ivMyBusiness.setOnClickListener(this);
        ivRepository.setOnClickListener(this);

        textview = (TextView) view.findViewById(R.id.brokername);
        //   textview.setSelected(true);
        //  textview.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        textview.setSingleLine(true);
        ivLoan.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivDialer:
                startActivity(new Intent(getActivity(), RbDialerPadActivity.class));
                break;
            case R.id.ivCreditcard:
                startActivity(new Intent(getActivity(), CreditCardApplyActivity.class));
                break;
            case R.id.ivLoan:
                //  startActivity(new Intent(getActivity(), LoanActivity.class));
                startActivity(new Intent(getActivity(), LoanMenuActivity.class));
                break;
            case R.id.ivRoboPlay:
                startActivity(new Intent(getActivity(), CallingActivity.class));
                break;
            case R.id.ivContact:
                if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.WRITE_CALL_LOG)
                        + ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE)
                        + ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_PHONE_STATE)
                        + ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.RECORD_AUDIO)
                        + ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        + ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                        + ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_CONTACTS)
                        != PackageManager.PERMISSION_GRANTED) {
                    //  requestPermissions(new String[] {android.Manifest.permission.READ_CONTACTS}, Utility.MY_PERMISSIONS_REQUEST_READ_CONTACTS);
                    requestPermissions(new String[]{android.Manifest.permission.WRITE_CALL_LOG,
                            android.Manifest.permission.CALL_PHONE,
                            android.Manifest.permission.READ_PHONE_STATE,
                            android.Manifest.permission.RECORD_AUDIO,
                            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            android.Manifest.permission.ACCESS_FINE_LOCATION,
                            android.Manifest.permission.READ_CONTACTS,
                    }, Utility.REQUEST_CODE_ASK_PERMISSIONS_ALL);
                } else {

                    startActivity(new Intent(getActivity(), ContactActivity.class));
                }

                break;
            case R.id.ivRepository:
                startActivity(new Intent(getActivity(), EmiMenuActivity.class));
                //   Toast.makeText(getActivity(), "Coming soon...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ivGeneralInsurance:
                startActivity(new Intent(getActivity(), IncomeSimulatorActivity.class));

                //  startActivity(new Intent(getActivity(), GeneralInsuranceType.class));
                // Toast.makeText(getActivity(), "Coming soon...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ivMyBusiness:
                // Toast.makeText(getActivity(), "Coming soon...", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), BuisinessActivity.class));
                break;
            case R.id.ivBalanceTransfer:
                startActivity(new Intent(getActivity(), BalanceTransferType.class));
                break;

            case R.id.ivTextShare:

                startActivity(new Intent(getActivity(), ShareMessageActivity.class));
                /*Fragment fragment = null;
                fragment = new ShareMessageFragment();
                // getActivity().getActionBar().setTitle("Share Text");
                //(MainActivity.class)getActivity().getActionBar().setTitle("Share Text");
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, "Share Text");
                fragmentTransaction.commitAllowingStateLoss();
*/
//                Runnable mPendingRunnable = new Runnable() {
//                    @Override
//                    public void run() {
//                        // update the main content by replacing fragments
//                        Fragment fragment = null;
//                        fragment = new ShareMessageFragment();
//                        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//                        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
//                                android.R.anim.fade_out);
//                        fragmentTransaction.replace(R.id.frame, fragment, "Share Text");
//                        fragmentTransaction.commitAllowingStateLoss();
//                    }
//                };
//                if (mPendingRunnable != null) {
//                    mHandler.post(mPendingRunnable);
//                }
                break;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case Utility.REQUEST_CODE_ASK_PERMISSIONS_ALL:
                if (grantResults.length > 0) {

                    boolean writeLog = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean callPhone = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    boolean phoneState = grantResults[2] == PackageManager.PERMISSION_GRANTED;
                    boolean recordAudio = grantResults[3] == PackageManager.PERMISSION_GRANTED;
                    boolean writeExternal = grantResults[4] == PackageManager.PERMISSION_GRANTED;
                    boolean accessFine = grantResults[5] == PackageManager.PERMISSION_GRANTED;
                    boolean readContact = grantResults[6] == PackageManager.PERMISSION_GRANTED;
//                    boolean sendSms = grantResults[7] == PackageManager.PERMISSION_GRANTED;
//                    boolean readSms = grantResults[8] == PackageManager.PERMISSION_GRANTED;

//                    if (writeLog && callPhone && phoneState && recordAudio && writeExternal && accessFine && readContact && sendSms && readSms) {
//                        startActivity(new Intent(getActivity(), ContactActivity.class));
                    if (writeLog && callPhone && phoneState && recordAudio && writeExternal && accessFine && readContact) {
                        startActivity(new Intent(getActivity(), ContactActivity.class));

                    } else {

                    }
                }
                break;
        }
    }

}
