package com.rupeeboss.rba.incomeSimulator.fragment;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.rupeeboss.rba.BaseFragment;
import com.rupeeboss.rba.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class InvitingFragment extends BaseFragment implements View.OnClickListener {

    ImageView imgHomeLoan , imgBtHomeLoan, imgBtPersonalLoan, imgBtLAP, imgLoanAgnProp,imgBussLoan ,imgWorkCapital,
              imgCreditCard,imgSalAccount,imgCreditRepair;

    String strTitle , strBody , strLink;
    private FragmentActivity myContext;
    public InvitingFragment() {
        // Required empty public constructor
    }
    @Override
    public void onAttach(Context context) {
        myContext=(FragmentActivity) context;
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inviting, container, false);
        initilize(view);
        return view;
    }

    private void initilize(View view) {

        imgHomeLoan = (ImageView) view.findViewById(R.id.imgHomeLoan);
        imgBtHomeLoan = (ImageView) view.findViewById(R.id.imgBtHomeLoan);
        imgBtPersonalLoan = (ImageView) view.findViewById(R.id.imgBtPersonalLoan);
        imgBtLAP = (ImageView) view.findViewById(R.id.imgBtLAP);

        imgLoanAgnProp = (ImageView) view.findViewById(R.id.imgLoanAgnProp);
        imgBussLoan = (ImageView) view.findViewById(R.id.imgBussLoan);
        imgWorkCapital = (ImageView) view.findViewById(R.id.imgWorkCapital);
        imgCreditCard = (ImageView) view.findViewById(R.id.imgCreditCard);

        imgSalAccount = (ImageView) view.findViewById(R.id.imgSalAccount);
        imgCreditRepair = (ImageView) view.findViewById(R.id.imgCreditRepair);

        imgHomeLoan.setOnClickListener(this);
        imgBtHomeLoan.setOnClickListener(this);
        imgBtPersonalLoan.setOnClickListener(this);
        imgBtLAP.setOnClickListener(this);

        imgLoanAgnProp.setOnClickListener(this);
        imgBussLoan.setOnClickListener(this);
        imgWorkCapital.setOnClickListener(this);
        imgCreditCard.setOnClickListener(this);

        imgSalAccount.setOnClickListener(this);
        imgCreditRepair.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.imgHomeLoan) {

            strTitle = "Home Loan";
            strBody ="Home Loan Saving";
            strLink = "http://www.rupeeboss.com/home-loan";
            datashareList(strTitle, strBody, strLink);

        } else if (view.getId() == R.id.imgBtHomeLoan) {

            strTitle = "Home Loan Balance Transfer";
            strBody ="Home Loan Balance Transfer Saving";
            strLink = "http://www.rupeeboss.com/balance-transfer/home-loan?referrer=UgBCADQAMAAwADAAMAA0ADIAOAA=@MAA=@dwBlAGIA";
            datashareList(strTitle, strBody, strLink);

        } else if (view.getId() == R.id.imgBtPersonalLoan) {

            strTitle = "Personal Loan Balance Transfer";
            strBody ="Personal Loan Balance Transfer Saving";
            strLink = "http://www.rupeeboss.com/balance-transfer/personal-loan?referrer=UgBCADQAMAAwADAAMAA0ADIAOAA=@MAA=@dwBlAGIA";
            datashareList(strTitle, strBody, strLink);

        } else if (view.getId() == R.id.imgBtLAP) {

            strTitle = "LAP Balance Transfer";
            strBody ="LAP Balance Transfer  Saving";
            strLink = "http://www.rupeeboss.com/balance-transfer/loan-against-property-loan?referrer=UgBCADQAMAAwADAAMAA0ADIAOAA=@MAA=@dwBlAGIA";
            datashareList(strTitle, strBody, strLink);


        } else if (view.getId() == R.id.imgLoanAgnProp) {

            strTitle = "Loan Against Property";
            strBody ="Share Loan Against Property";
            strLink = "http://www.rupeeboss.com/loan-against-property";
            datashareList(strTitle, strBody, strLink);

        } else if (view.getId() == R.id.imgWorkCapital) {

            strTitle = "Working Capital";
            strBody ="Share Working Capital";
            strLink = "http://www.rupeeboss.com/sme-working-capital";
            datashareList(strTitle, strBody, strLink);

        } else if (view.getId() == R.id.imgBussLoan) {

            strTitle = "Business Loan";
            strBody ="Share Business Loan";
            strLink = "http://www.rupeeboss.com/business-loan";
            datashareList(strTitle, strBody, strLink);

        } else if (view.getId() == R.id.imgCreditCard) {

            strTitle = "Credit Card";
            strBody ="Share Credit Card";
            strLink = "http://www.rupeeboss.com/credit-card";
            datashareList(strTitle, strBody, strLink);

        } else if (view.getId() == R.id.imgSalAccount) {

            strTitle = "Salary Accounts";
            strBody ="Share Salary Accounts";
            strLink = "http://www.rupeeboss.com/idfc";
            datashareList(strTitle, strBody, strLink);

        } else if (view.getId() == R.id.imgCreditRepair) {

            strTitle = "Credit Repair";
            strBody ="Share Credit Repair";
            strLink = "";
            datashareList(strTitle, strBody, strLink);
        }
    }

    private void datashareList(String Title, String Bodymsg, String link) {


        String Deeplink;
        //"Look! This can make you look gorgeous from Nykaa";
        Deeplink = Bodymsg + "\n" + link;
        String prdSubject = Title;

        String prdDetail = Deeplink;


        try {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_TEXT, prdDetail);

            shareIntent.setType("text/plain");

            PackageManager pm = getActivity().getPackageManager();


            List<ResolveInfo> resInfo = pm.queryIntentActivities(shareIntent, 0);
            List<LabeledIntent> intentList = new ArrayList<LabeledIntent>();
            ///////////
            for (int i = 0; i < resInfo.size(); i++) {
                // Extract the label, append it, and repackage it in a LabeledIntent
                ResolveInfo ri = resInfo.get(i);
                String packageName = ri.activityInfo.packageName;
                String processName = ri.activityInfo.processName;
                String AppName = ri.activityInfo.name;

                if ((packageName.contains("android.email") || packageName.contains("mms") || packageName.contains("twitter") || (packageName.contains("whatsapp")) || (packageName.contains("facebook.katana")) || (packageName.contains("facebook.orca")) || packageName.contains("messaging") || packageName.contains("android.gm") || packageName.contains("com.google.android.apps.plus")) || (packageName.contains("apps.docs")) && processName.contains("android.apps.docs:Clipboard") || (packageName.contains("android.talk")) && AppName.contains("hangouts")) {

                    shareIntent.setComponent(new ComponentName(packageName, ri.activityInfo.name));

                    if (packageName.contains("android.email")) {

                        shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, prdSubject);
                        shareIntent.setPackage(packageName);

                    } else if (packageName.contains("twitter")) {

                        shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, prdSubject);
                        shareIntent.setPackage(packageName);

                    } else if (packageName.contains("facebook.katana")) {

                        shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, prdSubject);
                        shareIntent.setType("text/plain");
                        shareIntent.putExtra(Intent.EXTRA_TEXT, prdDetail);
                        shareIntent.setPackage("com.facebook.katana");

                    } else if (packageName.contains("facebook.orca")) {

                        shareIntent.setType("text/plain");
                        shareIntent.putExtra(Intent.EXTRA_TEXT, prdDetail);
                        shareIntent.setPackage("com.facebook.orca");

                    } else if (packageName.contains("mms")) {

                        shareIntent.setPackage(packageName);

                    } else if (packageName.contains("whatsapp")) {

                        shareIntent.setType("text/plain");
                        shareIntent.putExtra(Intent.EXTRA_TEXT, prdDetail);
                        shareIntent.setPackage(packageName);


                    } else if (packageName.contains("messaging")) {
                        shareIntent.setPackage(packageName);
                    } else if (packageName.contains("com.google.android.apps.plus")) {
                        shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, prdSubject);
                        shareIntent.setPackage(packageName);

                    }
//                    else if (packageName.contains("android.talk")) {
//                        if (AppName.contains("hangouts")) {
//
//                            shareIntent.setPackage(packageName);
//                        }
//
//                    }
                    else if (packageName.contains("apps.docs")) {
                        if (processName.contains("android.apps.docs:Clipboard")) {

                            shareIntent.setPackage(packageName);
                        }

                    } else if (packageName.contains("android.gm")) {
                        shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, prdSubject);
                        shareIntent.setPackage(packageName);

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
}
