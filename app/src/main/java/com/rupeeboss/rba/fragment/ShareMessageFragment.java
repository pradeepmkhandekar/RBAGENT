package com.rupeeboss.rba.fragment;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.rupeeboss.rba.BaseFragment;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.adapter.ShareMessageAdapter;
import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.controller.sharemsg.ShareMessageController;
import com.rupeeboss.rba.core.facade.LoginFacade;
import com.rupeeboss.rba.core.model.LstShareMessageEntity;
import com.rupeeboss.rba.core.response.ShareMessageResponse;
import com.rupeeboss.rba.core.response.SmsLeadResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IN-RB on 02-02-2017.
 */

public class ShareMessageFragment extends BaseFragment implements IResponseSubcriber, View.OnClickListener {

    RecyclerView recyclerShareMessage;
    ShareMessageAdapter mAdapter;
    List<LstShareMessageEntity> listShareMessag;
    private FragmentActivity myContext;
    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;

    public ShareMessageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_share_message, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Share Links");
        ((AppCompatActivity) getActivity()).getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.color.transparent_white));
        initilize(view);
        //   setHasOptionsMenu(true);
        return view;
    }

    private void initilize(View view) {


        recyclerShareMessage = (RecyclerView) view.findViewById(R.id.recyclerShareMSG);
        recyclerShareMessage.setHasFixedSize(true);

        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerShareMessage.setLayoutManager(mLayoutManager);
        //  listShareMessag = new ArrayList<DisplayEmployeePlanEntity>();
    }

    @Override
    public void onResume() {
        super.onResume();
        //server hit to fetch latest data
        getActivity().setTitle(R.string.title_activity_share_message);

        showDialog();
        new ShareMessageController().getShareMessage(new LoginFacade(myContext).getUser().getEmpCode(), "" + new LoginFacade(myContext).getUser().getBrokerId(), this);
        //   new ShareMessageController().getShareMessage("rb40000401",this);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void OnSuccess(APIResponse response, String message) throws InterruptedException {
        cancelDialog();
        if (response instanceof ShareMessageResponse) {
            if (response.getStatus_Id() == 0) {
                if (((ShareMessageResponse) response).getResult().getLstMsgLnkDtls() != null) {
                    listShareMessag = ((ShareMessageResponse) response).getResult().getLstMsgLnkDtls();


                    List<LstShareMessageEntity> list = new ArrayList<>();

                    if ((new LoginFacade(getActivity()).getUser().isCanShareLnk() == false)) {

                        for (LstShareMessageEntity item : listShareMessag)

                        {
                            if (item.getTitle().toUpperCase().equals("RBA LINK")) {
                                list.add(item);

                            }

                        }

                        listShareMessag.removeAll(list);
                    }

                    mAdapter = new ShareMessageAdapter(ShareMessageFragment.this, listShareMessag);
                    recyclerShareMessage.setAdapter(mAdapter);

                }
            } else {
                recyclerShareMessage.setAdapter(null);
                //Snackbar.make(et_date, "No data available", Snackbar.LENGTH_SHORT).show();
            }
        } else if (response instanceof SmsLeadResponse) {

            listShareMessag.add(((ShareMessageResponse) response).getResult().getLstMsgLnkDtls().get(0));
            mAdapter.notifyDataSetChanged();
            loading = true;

        }
    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
        recyclerShareMessage.setAdapter(null);
    }

    @Override
    public void onAttach(Context context) {
        myContext = (FragmentActivity) context;
        super.onAttach(context);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.share_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                FragmentManager fm = myContext.getSupportFragmentManager();
                if (fm.getBackStackEntryCount() > 0) {
                    fm.popBackStack();
                } else {
                    getActivity().onBackPressed();
                }
                return true;

        }
        return super.onOptionsItemSelected(item);

    }
}
