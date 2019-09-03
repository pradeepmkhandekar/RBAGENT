package com.rupeeboss.rba.mylist;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.rupeeboss.rba.BaseActivity;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.model.SuperRBAEntity;
import com.rupeeboss.rba.utility.Utility;

public class MyListPopUpActivity extends BaseActivity implements View.OnClickListener {

    SuperRBAEntity superRBAEntity;
    RecyclerView myListRecycler;
    MyListPopUpAdapter mAdapter;
    Button btnClose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list_pop_up);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setFinishOnTouchOutside(false);

        inialize();
        if (getIntent().hasExtra(Utility.MY_LIST)) {
            superRBAEntity = getIntent().getParcelableExtra(Utility.MY_LIST);


            if(superRBAEntity.getChildlst() != null) {
                mAdapter = new MyListPopUpAdapter(MyListPopUpActivity.this, superRBAEntity.getChildlst());
                myListRecycler.setAdapter(mAdapter);
            }

        }
    }

    private void inialize() {
        myListRecycler = (RecyclerView) findViewById(R.id.MyListRecycler);
        myListRecycler.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        myListRecycler.setLayoutManager(mLayoutManager);

        btnClose = (Button) findViewById(R.id.btnClose);
        btnClose.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btnClose) {
            this.finish();
        }
    }
}
