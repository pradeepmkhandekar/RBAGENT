package com.rupeeboss.rba.incomeSimulator;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.rupeeboss.rba.BaseActivity;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.home.MainActivity;
import com.rupeeboss.rba.incomeSimulator.fragment.ImmediateFragment;
import com.rupeeboss.rba.incomeSimulator.fragment.IncomeSimulatorFragment;
import com.rupeeboss.rba.incomeSimulator.fragment.InvitingFragment;
import com.rupeeboss.rba.incomeSimulator.fragment.MonthlyFragment;

import static com.rupeeboss.rba.R.id.action_Immediate;
import static com.rupeeboss.rba.R.id.action_Inviting;
import static com.rupeeboss.rba.R.id.action_Monthly;

public class IncomeSimulatorActivity extends BaseActivity implements View.OnClickListener {

    BottomNavigationView bottomNavigationView;

    int totCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_simulator);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        IncomeSimulatorFragment incomeSimulatorFragment = new IncomeSimulatorFragment();
        FragmentTransaction transactionSim = getSupportFragmentManager().beginTransaction();
        transactionSim.replace(R.id.frame_layout, incomeSimulatorFragment, "Home");
        //  transactionSim.addToBackStack("home");
        transactionSim.commit();

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.getMenu().getItem(0).setCheckable(false);  // UnCheck the First Item
      //  bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (getSupportFragmentManager().getBackStackEntryCount() == 0) {

                    if (getSupportFragmentManager().findFragmentByTag("Home") != null) {
                        IncomeSimulatorFragment fragSim = (IncomeSimulatorFragment) getSupportFragmentManager().findFragmentByTag("Home");
                        if (fragSim != null) {

                            int temCount = fragSim.getTotalContact();
                            if (temCount == 0) {

                                validateTotal(0);
                                unCheckAllBottomMenu();
                                return true;

                            } else if (temCount > 100000) {
                                validateTotal(1);
                                unCheckAllBottomMenu();
                                return true;

                            } else {

                                totCount = temCount;
                            }

                        }

                    }
                }

                //region switch
                switch (item.getItemId()) {
                    case action_Immediate:
                        ImmediateFragment immediateFragment = new ImmediateFragment();
                        FragmentTransaction transaction_imm = getSupportFragmentManager().beginTransaction();
                        transaction_imm.replace(R.id.frame_layout, immediateFragment, "Immed");
                        transaction_imm.addToBackStack("other");
                        transaction_imm.commitAllowingStateLoss();

                        item.setCheckable(true);
                        bottomNavigationView.getMenu().getItem(1).setCheckable(false);
                        bottomNavigationView.getMenu().getItem(2).setCheckable(false);
                        break;
                    case action_Monthly:
                        MonthlyFragment monthlyFragment = new MonthlyFragment();
                        FragmentTransaction transaction_mon = getSupportFragmentManager().beginTransaction();
                        transaction_mon.replace(R.id.frame_layout, monthlyFragment, "Month");
                        transaction_mon.addToBackStack("other");
                        transaction_mon.commitAllowingStateLoss();

                        item.setCheckable(true);
                        bottomNavigationView.getMenu().getItem(0).setCheckable(false);
                        bottomNavigationView.getMenu().getItem(2).setCheckable(false);
                        break;
                    case action_Inviting:

                        InvitingFragment invitingFragment = new InvitingFragment();
                        FragmentTransaction transaction_inv = getSupportFragmentManager().beginTransaction();
                        transaction_inv.replace(R.id.frame_layout, invitingFragment, "Invit");
                        transaction_inv.addToBackStack("other");
                        transaction_inv.commitAllowingStateLoss();

                        item.setCheckable(true);
                        bottomNavigationView.getMenu().getItem(0).setCheckable(false);
                        bottomNavigationView.getMenu().getItem(1).setCheckable(false);
                        break;
                }

                //endregion

                return true;
            }
        });

    }

    @Override
    public void onClick(View v) {

    }

    private void unCheckAllBottomMenu()
    {
        int size = bottomNavigationView.getMenu().size();
        for (int i = 0; i < size; i++) {
            bottomNavigationView.getMenu().getItem(i).setCheckable(false);
        }
    }

    @Override
    public void onBackPressed() {

         unCheckAllBottomMenu();
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {

            getSupportFragmentManager().popBackStack("other", FragmentManager.POP_BACK_STACK_INCLUSIVE);

//            int count = getSupportFragmentManager().getBackStackEntryCount();
//            while(count > 0){
//                getSupportFragmentManager().popBackStack();
//                count--;
//            }
        } else {
            super.onBackPressed();

        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                int count = getSupportFragmentManager().getBackStackEntryCount();

                if (count > 0) {
                    IncomeSimulatorFragment incomeSimulatorFragment = new IncomeSimulatorFragment();
                    FragmentTransaction transactionSim = getSupportFragmentManager().beginTransaction();
                    transactionSim.replace(R.id.frame_layout, incomeSimulatorFragment, "Home");
                    transactionSim.commit();
                    getSupportFragmentManager().popBackStack("other", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                    unCheckAllBottomMenu();
                } else if (count == 0) {
                    startActivity(new Intent(IncomeSimulatorActivity.this, MainActivity.class));
                    finish();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);

        // Note : Home frag Not added to addToBackStack , hence counting consider only for other fragment
        // ie why case may be count 1  Frame having both home and 1 other frag
    }

    public int getTotal() {
        return totCount;
    }

    private void validateTotal(int type) {

        try {
            androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(IncomeSimulatorActivity.this);
            builder.setTitle("Warning...");

            if (type == 0) {
                builder.setMessage("Please enter the number of contact.");
            } else {
                builder.setMessage("Contact number should not exceed than 1 lac.");
            }

            String positiveText = "OK";
            builder.setPositiveButton(positiveText,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });


            final androidx.appcompat.app.AlertDialog dialog = builder.create();
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();

        } catch (Exception ex) {
            Toast.makeText(this, "Please try again..", Toast.LENGTH_SHORT).show();
        }
    }
}



