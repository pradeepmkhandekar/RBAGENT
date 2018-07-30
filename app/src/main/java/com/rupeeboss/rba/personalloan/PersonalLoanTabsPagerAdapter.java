package com.rupeeboss.rba.personalloan;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.rupeeboss.rba.core.model.ApplicationDisplayEntity;
import com.rupeeboss.rba.core.model.PersonalApplicationDisplayEntity;
import com.rupeeboss.rba.core.model.PersonalQuoteDisplayEntity;
import com.rupeeboss.rba.core.model.QuoteDisplayEntity;
import com.rupeeboss.rba.core.response.PersonalQuoteAppDispalyResponse;
import com.rupeeboss.rba.core.response.QuoteDisplayResponse;
import com.rupeeboss.rba.loan.ApplicationFragment;
import com.rupeeboss.rba.loan.QuotesFragment;

import java.util.ArrayList;

/**
 * Created by IN-RB on 14-02-2017.
 */
public class PersonalLoanTabsPagerAdapter extends FragmentPagerAdapter {

    PersonalQuoteAppDispalyResponse personalQuoteAppDispalyResponse;

  public  PersonalLoanTabsPagerAdapter(FragmentManager fm ,PersonalQuoteAppDispalyResponse personalQuoteAppDispalyResponse)
  {
      super(fm);
      this.personalQuoteAppDispalyResponse = personalQuoteAppDispalyResponse;

  }

    @Override
    public Fragment getItem(int index) {
        switch (index) {
            case 0:
                // Salary fragment activity
                Bundle args = new Bundle();
                args.putParcelableArrayList(QuotesFragment.QUOTES, (ArrayList<PersonalQuoteDisplayEntity>) personalQuoteAppDispalyResponse.getQuoteList());
                PersonalQuotesFragment quotesFragment= new PersonalQuotesFragment();
                quotesFragment.setArguments(args);
                return quotesFragment;
            case 1:
                Bundle argsApp = new Bundle();
                argsApp.putParcelableArrayList(PersonalApplicationFragment.APPLICANTION, (ArrayList<PersonalApplicationDisplayEntity>) personalQuoteAppDispalyResponse.getApplicationList());
                PersonalApplicationFragment applicationFragment= new PersonalApplicationFragment();
                applicationFragment.setArguments(argsApp);
                return  applicationFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
