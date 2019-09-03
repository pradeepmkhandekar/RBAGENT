package com.rupeeboss.rba.personalloan;

import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rupeeboss.rba.BaseFragment;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.loan.LoanTabsPagerAdapter;

/**
 * Created by IN-RB on 09-02-2017.
 */

public class PersonalLoanFragment extends BaseFragment implements IResponseSubcriber {

    private ViewPager viewPager;
    LoanTabsPagerAdapter mAdapter;
    TabLayout tabLayout;
    public PersonalLoanFragment()
    {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_personalloan, container, false);
        initialise_widget(view);
        setHasOptionsMenu(true);
        tabLayout.addTab(tabLayout.newTab().setText("QUOTES"));
        tabLayout.addTab(tabLayout.newTab().setText("APPLICATION"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                Log.d("onTabSelected", "" + tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.d("onTabUnselected", "" + tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.d("onTabReselected", "" + tab.getPosition());
            }
        });
        return view;
    }

    private void initialise_widget(View view)
    {
        viewPager = (ViewPager) view.findViewById(R.id.pager);
        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
    }
//
//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//
//        inflater.inflate(R.menu.loan_frament_menu, menu);
//        super.onCreateOptionsMenu(menu, inflater);
//    }


//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (item.getItemId() == R.id.loan_add) {
//            // startActivity(new Intent(getActivity(), LoanMenuActivity.class));
//            startActivity(new Intent(getActivity(), PersonalLoanActivity.class));
//        }
//
//        else if(item.getItemId() == android.R.id.home)
//        {
//            getActivity().onBackPressed();
//        }
//
//        return true;
//    }



    @Override
    public void OnSuccess(APIResponse response, String message) throws InterruptedException {


    }

    @Override
    public void OnFailure(Throwable t) {



    }
}
