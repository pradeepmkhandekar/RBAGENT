package com.rupeeboss.rba.mylist;

import android.content.Intent;
import android.os.Bundle;
import androidx.recyclerview.widget.DefaultItemAnimator;
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
import com.rupeeboss.rba.core.model.SuperRBAEntityNew;
import com.rupeeboss.rba.core.response.MyListResponse;
import com.rupeeboss.rba.mylead.MyLeadActivity;

import java.util.ArrayList;
import java.util.List;


public class ExpandActivity extends BaseActivity implements IResponseSubcriber {

    public GenreAdapter adapter;
    RecyclerView recyclerView;
    List<SuperRBAEntityNew> superRBAEntityNews;
    LoginFacade loginFacade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        loginFacade = new LoginFacade(this);
        recyclerView = (RecyclerView) findViewById(R.id.rvSuperRba);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // RecyclerView has some built in animations to it, using the DefaultItemAnimator.
        // Specifically when you call notifyItemChanged() it does a fade animation for the changing
        // of the data in the ViewHolder. If you would like to disable this you can use the following:
        RecyclerView.ItemAnimator animator = recyclerView.getItemAnimator();
        if (animator instanceof DefaultItemAnimator) {
            ((DefaultItemAnimator) animator).setSupportsChangeAnimations(false);
        }

        showProgressDialog();
        new MyListController(this).getParentList(loginFacade.getUser().getEmpCode(), loginFacade.getUser().getBrokerId(), this);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        adapter.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        adapter.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void OnSuccess(APIResponse response, String message) throws InterruptedException {
        if (response instanceof MyListResponse) {
            dismissDialog();
            //myListAdapter = new MyListAdapter(this, ((MyListResponse) response).getResult().getLst());
            //rvSuperRba.setAdapter(myListAdapter);
          //  superRBAEntityNews = ((MyListResponse) response).getResult().getLst();
           // adapter = new GenreAdapter(this, getSuperRbaList(((MyListResponse) response).getResult().getLst()));
            recyclerView.setAdapter(adapter);
        }
    }

    public List<SuperRBAEntity> getSuperRbaList(List<SuperRBAEntityNew> superRBAEntityNews) {
        List<SuperRBAEntity> superRBAEntities = new ArrayList<>();
        for (SuperRBAEntityNew superRBAEntityNew : superRBAEntityNews) {
            SuperRBAEntity superRBAEntity = new SuperRBAEntity(superRBAEntityNew.getBrokerName(), superRBAEntityNew.getChildlst());
            superRBAEntities.add(superRBAEntity);
        }
        return superRBAEntities;
    }

    public int getBrokerId(String title) {
        int brokerId = 0;
        for (SuperRBAEntityNew superRBAEntityNew : superRBAEntityNews) {
            if (superRBAEntityNew.getBrokerName().equals(title))
                brokerId = superRBAEntityNew.getBrokerId();
        }
        return brokerId;
    }

    public void redirectTomyLead(int brokerId) {
        startActivity(new Intent(this, MyLeadActivity.class).putExtra("BROKER_ID", brokerId));
    }

    @Override
    public void OnFailure(Throwable t) {
        dismissDialog();
    }
}
