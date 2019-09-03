package com.rupeeboss.rba.mylist;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.rupeeboss.rba.BaseActivity;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.controller.mylist.MyListController;
import com.rupeeboss.rba.core.controller.smslead.SmsLead;
import com.rupeeboss.rba.core.facade.LoginFacade;
import com.rupeeboss.rba.core.model.SuperRBAEntity;
import com.rupeeboss.rba.core.request.requestentity.MyLeadRequestEntity;
import com.rupeeboss.rba.core.response.MyLeadResponse;
import com.rupeeboss.rba.core.response.MyListResponse;
import com.rupeeboss.rba.mylead.MyLeadActivity;

import java.util.List;

public class GroupListActivity extends BaseActivity implements IResponseSubcriber {

    ExpandableListAdapter listAdapter;
    static ExpandableListView expListView;
    LoginFacade loginFacade;
    List<SuperRBAEntity> superRBAEntityList;
    String brokerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init_widgets();
        showProgressDialog();
        new MyListController(this).getParentList(loginFacade.getUser().getEmpCode(), loginFacade.getUser().getBrokerId(), this);
        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                redirectTomyLead(superRBAEntityList.get(groupPosition).getChildlst().get(childPosition).getBrokerId());
                return false;
            }
        });
    }

    private void init_widgets() {
        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        loginFacade = new LoginFacade(this);
    }

    @Override
    public void OnSuccess(APIResponse response, String message) throws InterruptedException {
        if (response instanceof MyListResponse) {
            dismissDialog();
            superRBAEntityList = ((MyListResponse) response).getResult().getLst();
            listAdapter = new ExpandableListAdapter(this, superRBAEntityList);

            // setting list adapter
            expListView.setAdapter(listAdapter);
        } else if (response instanceof MyLeadResponse) {
            dismissDialog();
            if (response.getStatusId() == 0) {
                startActivity(new Intent(this, MyLeadActivity.class).putExtra("BROKER_ID", brokerId)
                        .putExtra("MY_LEAD_RESPONSE", ((MyLeadResponse) response).getResult()));
            } else {
                Toast.makeText(this, "" + response.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    public void OnFailure(Throwable t) {
        dismissDialog();
        Toast.makeText(this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
    }

    public void redirectTomyLead(int brokerId) {
        this.brokerId = "" + brokerId;
        MyLeadRequestEntity myLeadRequestEntity = new MyLeadRequestEntity();
        myLeadRequestEntity.setBrokerId("" + brokerId);
        myLeadRequestEntity.setCode("");
        myLeadRequestEntity.setPgNo(1);
        showProgressDialog();
        new SmsLead().getMyLead(myLeadRequestEntity, GroupListActivity.this);

    }
}
