package com.rupeeboss.rba.LAP;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rupeeboss.rba.BaseFragment;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.controller.quote.QuoteController;
import com.rupeeboss.rba.core.facade.LoginFacade;
import com.rupeeboss.rba.core.model.ApplicationDisplayEntity;
import com.rupeeboss.rba.core.response.QuoteDisplayResponse;
import com.rupeeboss.rba.homeloan.HomeLoanActivity;
import com.rupeeboss.rba.loan.ApplicationAdapter;
import com.rupeeboss.rba.loan.ApplicationFragment;
import com.rupeeboss.rba.loan.LoanTabsPagerAdapter;
import com.rupeeboss.rba.utility.DividerItemDecoration;

import java.util.List;


public class LAP_Loanfragment extends Fragment{

    public static final String APPLICANTION = "Application";
    RecyclerView recyclerApplication;
    ApplicationAdapter mAdapter;
    List<ApplicationDisplayEntity> listApplicationDisp;

    public LAP_Loanfragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.lap__loan_fragment, container, false);
        initialise_widget(view);
//        mAdapter = new ApplicationAdapter(ApplicationFragment.this,listApplicationDisp);
//        recyclerApplication.setAdapter(mAdapter);

        if (getArguments() != null) {
            listApplicationDisp = getArguments().getParcelableArrayList(APPLICANTION);
            if(listApplicationDisp!=null){
                mAdapter = new ApplicationAdapter(getActivity(), listApplicationDisp);
                recyclerApplication.setAdapter(mAdapter);
            }else{
                recyclerApplication.setAdapter(null);
            }
        }
        return view;
    }

    private void initialise_widget(View view) {
        recyclerApplication = (RecyclerView) view.findViewById(R.id.recyclerApplication);
        recyclerApplication.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerApplication.addItemDecoration(
                new DividerItemDecoration(ContextCompat.getDrawable(getActivity(),
                        R.drawable.item_decor)));
    }


}
