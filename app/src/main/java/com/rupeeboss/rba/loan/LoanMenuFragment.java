package com.rupeeboss.rba.loan;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.rupeeboss.rba.BaseFragment;
import com.rupeeboss.rba.LAP.Loan_LAPActivity;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.personalloan.LoanPersonalActivity;
import com.rupeeboss.rba.webviews.CarLoan.CarLoanActivity;
import com.rupeeboss.rba.webviews.balancetransfer.BalanceTransferType;
import com.rupeeboss.rba.webviews.businessLoan.businessLoanActivity;
import com.rupeeboss.rba.webviews.commPurchase.CommPurchaseActivity;
import com.rupeeboss.rba.webviews.workingCapital.WorkingCapitalActivity;

/**
 * Created by IN-RB on 08-02-2017.
 */

public class LoanMenuFragment extends BaseFragment implements View.OnClickListener {
    LinearLayout homeLoan, loanAgainstProperty, carLoan, personalLoan, businessLoan, balanceTransfer, workingCapital, commPurchase;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.content_loan_menu, container, false);
        initialize(view);
        return view;
    }

    private void initialize(View view) {
        homeLoan = (LinearLayout) view.findViewById(R.id.homeLoan);
        loanAgainstProperty = (LinearLayout) view.findViewById(R.id.loanAgainstProperty);
        carLoan = (LinearLayout) view.findViewById(R.id.carLoan);
        personalLoan = (LinearLayout) view.findViewById(R.id.personalLoan);
        businessLoan = (LinearLayout) view.findViewById(R.id.businessLoan);
        balanceTransfer = (LinearLayout) view.findViewById(R.id.balanceTransfer);
        workingCapital = (LinearLayout) view.findViewById(R.id.workingCapital);
        commPurchase = (LinearLayout) view.findViewById(R.id.commPurchase);
        workingCapital.setOnClickListener(this);
        commPurchase.setOnClickListener(this);
        homeLoan.setOnClickListener(this);
        carLoan.setOnClickListener(this);
        personalLoan.setOnClickListener(this);
        businessLoan.setOnClickListener(this);
        loanAgainstProperty.setOnClickListener(this);
        balanceTransfer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.homeLoan:
                // startActivity(new Intent(LoanMenuActivity.this, HomeLoanActivity.class));
                startActivity(new Intent(getActivity(), LoanActivity.class));
                break;
            case R.id.loanAgainstProperty:
                startActivity(new Intent(getActivity(), Loan_LAPActivity.class));
                break;
            case R.id.carLoan:
                startActivity(new Intent(getActivity(), CarLoanActivity.class));
                break;
            case R.id.businessLoan:
                startActivity(new Intent(getActivity(), businessLoanActivity.class));
                //Toast.makeText(getActivity(), "Coming soon...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.personalLoan:
                startActivity(new Intent(getActivity(), LoanPersonalActivity.class));
                break;
            case R.id.balanceTransfer:
                startActivity(new Intent(getActivity(), BalanceTransferType.class));
                break;
            case R.id.workingCapital:
                startActivity(new Intent(getActivity(), WorkingCapitalActivity.class));
                break;
            case R.id.commPurchase:
                startActivity(new Intent(getActivity(), CommPurchaseActivity.class));
              //  Toast.makeText(getActivity(), "Coming soon...", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
