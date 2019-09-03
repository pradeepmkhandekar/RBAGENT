package com.rupeeboss.rba.mylist;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import com.rupeeboss.rba.BaseActivity;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.controller.mylist.MyListController;
import com.rupeeboss.rba.core.facade.LoginFacade;
import com.rupeeboss.rba.core.model.SuperRBAEntity;
import com.rupeeboss.rba.core.response.MyListResponse;
import com.rupeeboss.rba.utility.Utility;

public class MyListActivity extends BaseActivity implements IResponseSubcriber {

    RecyclerView rvSuperRba;
    MyListAdapter myListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init_widgets();
        showProgressDialog();
        new MyListController(this).getParentList(new LoginFacade(MyListActivity.this).getPanNumber(), 0, this);
    }

    private void init_widgets() {
        rvSuperRba = (RecyclerView) findViewById(R.id.rvSuperRba);
        rvSuperRba.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void OnSuccess(APIResponse response, String message) throws InterruptedException {
        if (response instanceof MyListResponse) {
            dismissDialog();
            //myListAdapter = new MyListAdapter(this, ((MyListResponse) response).getResult().getLst());
            rvSuperRba.setAdapter(myListAdapter);
        }
    }

    @Override
    public void OnFailure(Throwable t) {
        dismissDialog();
    }


    public void redirectToList(SuperRBAEntity superRBAEntity) {

        if (superRBAEntity.getChildlst() != null) {
            startActivity(new Intent(this, MyListPopUpActivity.class)
                    .putExtra(Utility.MY_LIST, superRBAEntity));
        } else {
            Snackbar.make(rvSuperRba, "No Data Found ..", Snackbar.LENGTH_SHORT).show();
        }
    }


}
