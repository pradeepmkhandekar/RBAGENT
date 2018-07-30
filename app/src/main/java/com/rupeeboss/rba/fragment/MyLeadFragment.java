package com.rupeeboss.rba.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rupeeboss.rba.BaseFragment;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.mylead.MyLeadAdapter;
import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.controller.smslead.SmsLead;
import com.rupeeboss.rba.core.facade.LoginFacade;
import com.rupeeboss.rba.core.model.LeadDetailsEntity;
import com.rupeeboss.rba.core.request.requestentity.MyLeadRequestEntity;
import com.rupeeboss.rba.core.response.MyLeadResponse;

import java.util.List;

/**
 * Created by Rajeev Ranjan on 03/03/2017.
 */

public class MyLeadFragment extends BaseFragment implements IResponseSubcriber {

    RecyclerView myLeadRecycler;
    MyLeadAdapter mAdapter;
    List<LeadDetailsEntity> leadDetailsEntityList;
    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    int page = 1, totalItems;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_mylead, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("MY LEADS");
        ((AppCompatActivity) getActivity()).getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.color.transparent_white));
        initilize(view);
        addListener();
        //   setHasOptionsMenu(true);
        return view;
    }

    private void initilize(View view) {
        myLeadRecycler = (RecyclerView) view.findViewById(R.id.myLeadRecycler);
    }

    private void addListener() {
        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        myLeadRecycler.setLayoutManager(mLayoutManager);

        myLeadRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) //check for scroll down
                {
                    visibleItemCount = mLayoutManager.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    pastVisiblesItems = mLayoutManager.findLastVisibleItemPosition();
                    Log.v("onScrolled", "visibleItemCount = " + visibleItemCount + " pastVisiblesItems = " + pastVisiblesItems + " totalItemCount =" + totalItemCount);
                    if (loading) {
                        if (totalItemCount <= (pastVisiblesItems + visibleItemCount)) {
                            loading = false;
                            Log.v("onScrolled", "Last Item Wow !");
                            //Do pagination.. i.e. fetch new data
                            MyLeadRequestEntity myLeadRequestEntity = new MyLeadRequestEntity();
                            myLeadRequestEntity.setBrokerId("" + new LoginFacade(getActivity()).getUser().getBrokerId());
                            myLeadRequestEntity.setCode("" + new LoginFacade(getActivity()).getUser().getEmpCode());
                            myLeadRequestEntity.setPgNo(page);
                            Toast.makeText(getActivity(), pastVisiblesItems + "/" + totalItems, Toast.LENGTH_SHORT).show();
                            new SmsLead().getMyLead(myLeadRequestEntity, MyLeadFragment.this);
                        }
                    }
                }
            }
        });
    }


    @Override
    public void OnSuccess(APIResponse response, String message) throws InterruptedException {
        if (response instanceof MyLeadResponse) {
            if (response.getStatusId() == 0) {
                if (((MyLeadResponse) response).getResult().getLstLeads() != null) {
                    if (mAdapter == null) {
                        leadDetailsEntityList = ((MyLeadResponse) response).getResult().getLstLeads();
                        totalItems = ((MyLeadResponse) response).getResult().getTotalCount();
                        mAdapter = new MyLeadAdapter(getActivity(), leadDetailsEntityList);
                        myLeadRecycler.setAdapter(mAdapter);
                    } else {
                        leadDetailsEntityList.addAll(((MyLeadResponse) response).getResult().getLstLeads());
                        mAdapter.notifyDataSetChanged();
                        loading = true;
                    }
                }
            } else {
                myLeadRecycler.setAdapter(null);
                loading = false;
                //Snackbar.make(et_date, "No data available", Snackbar.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void OnFailure(Throwable t) {

    }
}
