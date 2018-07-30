package com.rupeeboss.rba.loan;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.rupeeboss.rba.core.model.ApplicationDisplayEntity;
import com.rupeeboss.rba.core.model.QuoteDisplayEntity;
import com.rupeeboss.rba.core.response.QuoteDisplayResponse;
import com.rupeeboss.rba.dashboard.DashboardFragment;

import java.util.ArrayList;
import java.util.List;

public class LoanTabsPagerAdapter extends FragmentPagerAdapter {

    QuoteDisplayResponse quoteDisplayResponse;
    public LoanTabsPagerAdapter(FragmentManager fm,QuoteDisplayResponse quoteDisplayResponse) {
        super(fm);
        this.quoteDisplayResponse = quoteDisplayResponse;

    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                // Salary fragment activity
                Bundle args = new Bundle();
                args.putParcelableArrayList(QuotesFragment.QUOTES, (ArrayList<QuoteDisplayEntity>) quoteDisplayResponse.getQuoteList());
                QuotesFragment quotesFragment= new QuotesFragment();
                quotesFragment.setArguments(args);
            return quotesFragment;
            case 1:
                Bundle argsApp = new Bundle();
                argsApp.putParcelableArrayList(ApplicationFragment.APPLICANTION, (ArrayList<ApplicationDisplayEntity>) quoteDisplayResponse.getApplicationList());
                ApplicationFragment applicationFragment= new ApplicationFragment();
                applicationFragment.setArguments(argsApp);
                return  applicationFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 2;
    }

}