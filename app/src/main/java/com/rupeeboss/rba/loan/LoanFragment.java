package com.rupeeboss.rba.loan;


import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rupeeboss.rba.BaseFragment;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.controller.quote.QuoteController;
import com.rupeeboss.rba.core.facade.LoginFacade;
import com.rupeeboss.rba.core.response.QuoteDisplayResponse;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoanFragment extends BaseFragment implements  IResponseSubcriber {

    private ViewPager viewPager;
    LoanTabsPagerAdapter mAdapter;
    TabLayout tabLayout;


    public LoanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_loan, container, false);
        //((AppCompatActivity) getActivity()).getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.color.transparent_white));
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

    private void initialise_widget(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.pager);
        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);

    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//
//        inflater.inflate(R.menu.loan_frament_menu, menu);
//        super.onCreateOptionsMenu(menu, inflater);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (item.getItemId() == R.id.loan_add) {
//            // startActivity(new Intent(getActivity(), LoanMenuActivity.class));
//            startActivity(new Intent(getActivity(), HomeLoanActivity.class));
//        } else if (item.getItemId() == android.R.id.home) {
//            getActivity().onBackPressed();
//        }
//
//        return true;
//    }

    @Override
    public void onResume() {
        super.onResume();
        showDialog();

        new QuoteController(getContext()).getQuote(12,"" + new LoginFacade(getActivity()).getUser().getBrokerId(), this);
    }

    @Override
    public void OnSuccess(APIResponse response, String message) throws InterruptedException {

//        List<ApplicationDisplayEntity> listApplicationDisp;
//        List<QuoteDisplayEntity> listQuoteDisp;
        cancelDialog();
        if (response instanceof QuoteDisplayResponse) {

            if (response.getStatus_Id() == 0) {
                if ((((QuoteDisplayResponse) response).getApplicationList() != null) || (((QuoteDisplayResponse) response).getQuoteList() != null)) {
                    mAdapter = new LoanTabsPagerAdapter(getChildFragmentManager(), ((QuoteDisplayResponse) response));
                    viewPager.setAdapter(mAdapter);
                }
            } else {
                Toast.makeText(getActivity(), ""+response.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
        Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
    }


}
