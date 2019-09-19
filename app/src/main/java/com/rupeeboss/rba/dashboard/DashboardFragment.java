package com.rupeeboss.rba.dashboard;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.core.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rupeeboss.rba.BaseFragment;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.contact.ContactActivity;
import com.rupeeboss.rba.contactus.ContactUsActivity;
import com.rupeeboss.rba.core.facade.LoginFacade;
import com.rupeeboss.rba.fragment.AddLeadFragment;
import com.rupeeboss.rba.incomeSimulator.IncomeSimulatorActivity;
import com.rupeeboss.rba.loan_fm.new_HomeLoan.NewHomeApplicaionActivity;
import com.rupeeboss.rba.loan_fm.new_personalloan.NewPersonalApplicaionActivity;
import com.rupeeboss.rba.loan_fm.newlaploan.NewLAPApplicaionActivity;
import com.rupeeboss.rba.mybuisness.BuisinessActivity;
import com.rupeeboss.rba.utility.Utility;
import com.rupeeboss.rba.webviews.balancetransfer.BalanceTransferActivity;
import com.rupeeboss.rba.webviews.commonwebview.CommonWebviewActivity;
import com.rupeeboss.rba.webviews.creditcard.CreditCardApplyActivity;
import com.rupeeboss.rba.webviews.generalInsurance.GeneralInsuranceType;
import com.rupeeboss.rba.webviews.workingCapital.WorkingCapitalActivity;

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


    LinearLayout explorerba;
    TextView ivcontact_us,ivmy_business,ivinbox,ivloan_on_chat,
            ivmsme, ivcredit_card, ivpersonal_loan, ivhome_loan, ivloan_against_property,
            ivbalance_transfer, ivcar_loan,ivrectify_productss,ivworking_capital,
            ivcash_loan,ivinsurance,ivcommercial_purchase,
            ivhome,ivincome_simulator,ivScan,ivgenerate_leads;


    BannerSlider bannerSlider;
    List<Banner> banners;
    LoginFacade loginFacade;
    String brokerId;

    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard2, container, false);
        //((AppCompatActivity) getActivity()).getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.bg2));
        loginFacade = new LoginFacade(getActivity());
        brokerId = "" + loginFacade.getUser().getBrokerId();

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
                                .putExtra("URL", "http://www.rupeeboss.com/rbl-personal-loan?BrokerId="+brokerId+"&client_source=RBA")
                                .putExtra("NAME", "RBL PERSONAL LOAN")
                                .putExtra("TITLE", "RBL PERSONAL LOAN"));
                        break;
                    case 1://kotak_banner
                        startActivity(new Intent(getActivity(), CommonWebviewActivity.class)
                                .putExtra("URL", "http://www.rupeeboss.com/kotak-personal-loan?BrokerId="+brokerId+"&client_source=RBA")
                                .putExtra("NAME", "KOTAK PERSONAL LOAN")
                                .putExtra("TITLE", "KOTAK PERSONAL LOAN"));
                        break;
                    case 2://iifl_banner
                        startActivity(new Intent(getActivity(), CommonWebviewActivity.class)
                                .putExtra("URL", "http://www.rupeeboss.com/apply-iifl-loan?BrokerId="+brokerId+"&client_source=RBA")
                                .putExtra("NAME", "IIFL PERSONAL LOAN")
                                .putExtra("TITLE", "IIFL PERSONAL LOAN"));
                        break;
                    case 3://hdfc_personal_loan_banner
                        startActivity(new Intent(getActivity(), CommonWebviewActivity.class)
                                .putExtra("URL", "http://www.rupeeboss.com/hdfc-personal-loan?BrokerId="+brokerId+"&client_source=RBA")
                                .putExtra("NAME", "HDFC PERSONAL LOAN")
                                .putExtra("TITLE", "HDFC PERSONAL LOAN"));
                        break;
                    case 4://hdfc_business_loan
                        startActivity(new Intent(getActivity(), CommonWebviewActivity.class)
                                .putExtra("URL", "http://www.rupeeboss.com/hdfc-business-loan?BrokerId="+brokerId+"&client_source=RBA")
                                .putExtra("NAME", "HDFC BUSINESS LOAN")
                                .putExtra("TITLE", "HDFC BUSINESS LOAN"));
                        break;
                    case 5://kotak_home_loan_banner
                        startActivity(new Intent(getActivity(), CommonWebviewActivity.class)
                                .putExtra("URL", "http://www.rupeeboss.com/kotak-home-loan?BrokerId="+brokerId+"&client_source=RBA")
                                .putExtra("NAME", "KOTAK HOME LOAN")
                                .putExtra("TITLE", "KOTAK HOME LOAN"));
                        break;
                }
            }
        });
    }

    private void initialize(View view) {

        bannerSlider = (BannerSlider) view.findViewById(R.id.banner_slider1);

        ivcontact_us = (TextView) view.findViewById(R.id.ivcontact_us);
        ivmy_business = (TextView) view.findViewById(R.id.ivmy_business);
        ivinbox = (TextView) view.findViewById(R.id.ivinbox);
        ivloan_on_chat = (TextView) view.findViewById(R.id.ivloan_on_chat);
        ivmsme = (TextView) view.findViewById(R.id.ivmsme);
        ivcredit_card = (TextView) view.findViewById(R.id.ivcredit_card);
        ivpersonal_loan = (TextView) view.findViewById(R.id.ivpersonal_loan);
        ivhome_loan = (TextView) view.findViewById(R.id.ivhome_loan);
        ivloan_against_property = (TextView) view.findViewById(R.id.ivloan_against_property);
        ivbalance_transfer = (TextView) view.findViewById(R.id.ivbalance_transfer);
        ivcar_loan = (TextView) view.findViewById(R.id.ivcar_loan);
        ivrectify_productss = (TextView) view.findViewById(R.id.ivrectify_productss);

        ivworking_capital = (TextView) view.findViewById(R.id.ivworking_capital);
        ivcash_loan = (TextView) view.findViewById(R.id.ivcash_loan);
        ivinsurance = (TextView) view.findViewById(R.id.ivinsurance);
        ivcommercial_purchase = (TextView) view.findViewById(R.id.ivcommercial_purchase);

        ivhome = (TextView) view.findViewById(R.id.ivhome);
        ivincome_simulator = (TextView) view.findViewById(R.id.ivincome_simulator);
        ivScan = (TextView) view.findViewById(R.id.ivScan);
        ivgenerate_leads = (TextView) view.findViewById(R.id.ivgenerate_leads);

//
        ivcontact_us.setOnClickListener(this);
        ivmy_business.setOnClickListener(this);
        ivinbox.setOnClickListener(this);
        ivloan_on_chat.setOnClickListener(this);
        ivmsme.setOnClickListener(this);
        ivcredit_card.setOnClickListener(this);
        ivpersonal_loan.setOnClickListener(this);
        ivhome_loan.setOnClickListener(this);
        ivloan_against_property.setOnClickListener(this);
        ivbalance_transfer.setOnClickListener(this);
//
        ivcar_loan.setOnClickListener(this);
        ivrectify_productss.setOnClickListener(this);
        ivworking_capital.setOnClickListener(this);
        ivcash_loan.setOnClickListener(this);
        ivinsurance.setOnClickListener(this);
        ivcommercial_purchase.setOnClickListener(this);

        ivhome.setOnClickListener(this);
        ivincome_simulator.setOnClickListener(this);
        ivScan.setOnClickListener(this);
        ivgenerate_leads.setOnClickListener(this);
//        textview = (TextView) view.findViewById(R.id.brokername);
//        //   textview.setSelected(true);
//        //  textview.setEllipsize(TextUtils.TruncateAt.MARQUEE);
//        textview.setSingleLine(true);
//        ivLoan.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivcontact_us:
                startActivity(new Intent(getActivity(), ContactUsActivity.class));

                break;
            case R.id.ivmy_business:
              startActivity(new Intent(getActivity(), BuisinessActivity.class));

                break;
            case R.id.ivinbox:
                //  startActivity(new Intent(getActivity(), LoanActivity.class));
               // startActivity(new Intent(getActivity(), LoanMenuActivity.class));

                break;
            case R.id.ivloan_on_chat:
                String url;
                if (loginFacade.getUser().getBrokerId() == 0) {
                    url = "https://yesbankbot.buildquickbots.com/chat/rupeeboss/staff/?userid=" + loginFacade.getUser().getEmpCode() + "&usertype=RBA&vkey=b34f02e9-8f1c";
                } else {
                    url = "https://yesbankbot.buildquickbots.com/chat/rupeeboss/staff/?userid=" + loginFacade.getUser().getBrokerId() + "&usertype=RBA&vkey=b34f02e9-8f1c";
                }

                        /*String url = "https://yesbankbot.buildquickbots.com/chat/rupeeboss_uat/staff/?userid=" + loginFacade.getUser().getEmpCode() +
                                "&usertype=RBA&vkey=8da76ddb-49cb-4c84-b14e-b72d951e2495";*/
                startActivity(new Intent(getActivity(), CommonWebviewActivity.class)
                        .putExtra("URL", url)
                        .putExtra("NAME", "YES BANK BOT")
                        .putExtra("TITLE", "YES BANK BOT"));
                 break;
            case R.id.ivmsme:
                startActivity(new Intent(getActivity(), WorkingCapitalActivity.class));
//                if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.WRITE_CALL_LOG)
//                        + ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE)
//                        + ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_PHONE_STATE)
//                        + ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.RECORD_AUDIO)
//                        + ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                        + ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
//                        + ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_CONTACTS)
//                        != PackageManager.PERMISSION_GRANTED) {
//                    //  requestPermissions(new String[] {android.Manifest.permission.READ_CONTACTS}, Utility.MY_PERMISSIONS_REQUEST_READ_CONTACTS);
//                    requestPermissions(new String[]{android.Manifest.permission.WRITE_CALL_LOG,
//                            android.Manifest.permission.CALL_PHONE,
//                            android.Manifest.permission.READ_PHONE_STATE,
//                            android.Manifest.permission.RECORD_AUDIO,
//                            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                            android.Manifest.permission.ACCESS_FINE_LOCATION,
//                            android.Manifest.permission.READ_CONTACTS,
//                    }, Utility.REQUEST_CODE_ASK_PERMISSIONS_ALL);
//                } else {

               //     startActivity(new Intent(getActivity(), ContactActivity.class));
               // }

                break;
            case R.id.ivcredit_card:
//                startActivity(new Intent(getActivity(), CommonWebviewActivity.class)
//                        .putExtra("URL", "http://www.rupeeboss.com/rbl-personal-loan")
//                        .putExtra("NAME", "RBL PERSONAL LOAN")
//                        .putExtra("TITLE", "RBL PERSONAL LOAN"));
              startActivity(new Intent(getActivity(), CreditCardApplyActivity.class));
                //   Toast.makeText(getActivity(), "Coming soon...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ivpersonal_loan:

                startActivity(new Intent(getActivity(), NewPersonalApplicaionActivity.class));
                // Toast.makeText(getActivity(), "Coming soon...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ivhome_loan:

                // Toast.makeText(getActivity(), "Coming soon...", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), NewHomeApplicaionActivity.class));
                break;
            case R.id.ivloan_against_property:

               startActivity(new Intent(getActivity(), NewLAPApplicaionActivity.class));
                break;

            case R.id.ivbalance_transfer:
                startActivity(new Intent(getActivity(), BalanceTransferActivity.class));

             //   startActivity(new Intent(getActivity(), ShareMessageActivity.class));
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

            case R.id.ivcar_loan:
                startActivity(new Intent(getActivity(), CommonWebviewActivity.class)
                        .putExtra("URL", "https://www.rupeeboss.com/hdfc-car-top-up-loan?BrokerId="+brokerId+"&client_source=RBA")
                        .putExtra("NAME", "CAR LOAN")
                        .putExtra("TITLE", "CAR LOAN"));
              //  startActivity(new Intent(getActivity(), BalanceTransferType.class));
                break;

            case R.id.ivrectify_productss:
                startActivity(new Intent(getActivity(), CommonWebviewActivity.class)
                        .putExtra("URL", " https://www.rupeeboss.com/rectifycredit?fbaid=0&type=RBA&loan_id="+brokerId+"")
                        .putExtra("NAME", "RECTIFY PRODUCT")
                        .putExtra("TITLE", "RECTIFY PRODUCT"));
            //    startActivity(new Intent(getActivity(), BalanceTransferType.class));
                break;

            case R.id.ivworking_capital:
                startActivity(new Intent(getActivity(), CommonWebviewActivity.class)
                        .putExtra("URL", "https://www.rupeeboss.com/working-capital?BrokerId="+brokerId+"&client_source=RBA")
                        .putExtra("NAME", "WORKING CAPITAL")
                        .putExtra("TITLE", "WORKING CAPITAL"));
            //    startActivity(new Intent(getActivity(), BalanceTransferType.class));
                break;

            case R.id.ivcash_loan:
                startActivity(new Intent(getActivity(), CommonWebviewActivity.class)
                        .putExtra("URL", "https://www.rupeeboss.com/cashe?BrokerId="+brokerId+"&client_source=RBA")
                        .putExtra("NAME", "CASH LOAN")
                        .putExtra("TITLE", "CASH LOAN"));
             //   startActivity(new Intent(getActivity(), BalanceTransferType.class));
                break;

            case R.id.ivinsurance:

                startActivity(new Intent(getActivity(), GeneralInsuranceType.class));
                break;

            case R.id.ivcommercial_purchase:
                startActivity(new Intent(getActivity(), NewLAPApplicaionActivity.class));
                break;
            case R.id.ivgenerate_leads:

                Fragment fragment = null;
                        fragment = new AddLeadFragment();
                        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                                android.R.anim.fade_out);
                        fragmentTransaction.replace(R.id.frame, fragment, "Share Text");
                        fragmentTransaction.commitAllowingStateLoss();
                break;
            case R.id.ivhome:
                break;
            case R.id.ivincome_simulator:
                 startActivity(new Intent(getActivity(), IncomeSimulatorActivity.class));
                break;
            case R.id.ivScan:
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