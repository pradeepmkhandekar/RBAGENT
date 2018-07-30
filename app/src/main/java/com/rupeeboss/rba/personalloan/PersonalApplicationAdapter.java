package com.rupeeboss.rba.personalloan;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.controller.editapplication.EditApplicationController;
import com.rupeeboss.rba.core.facade.ProductFacade;
import com.rupeeboss.rba.core.model.CustomerApplicationEntity;
import com.rupeeboss.rba.core.model.PersonalApplicationDisplayEntity;
import com.rupeeboss.rba.core.response.EditApplicationResponse;
import com.rupeeboss.rba.utility.Constants;
import com.rupeeboss.rba.webviewinfo.MoreInfoActivity;

import java.util.List;

/**
 * Created by IN-RB on 14-02-2017.
 */
public class PersonalApplicationAdapter extends RecyclerView.Adapter<PersonalApplicationAdapter.ApplicationItem> implements IResponseSubcriber{

    Context mContext;
    List<PersonalApplicationDisplayEntity> listApplicationDispEntities;

    public PersonalApplicationAdapter(Context context, List<PersonalApplicationDisplayEntity> listApplicationDispEntities) {
        this.mContext = context;
        this.listApplicationDispEntities = listApplicationDispEntities;
    }

    @Override
    public void OnSuccess(APIResponse response, String message) throws InterruptedException {
        if(response instanceof EditApplicationResponse){
            if(response.getStatus_Id()==0){
                CustomerApplicationEntity customerEntity = ((EditApplicationResponse) response).getData();
                mContext.startActivity(new Intent(mContext,PersonalLoanActivity.class)
                        .putExtra("IS_APP_EDIT",true)
                        .putExtra("CUST_APP_DETAILS",customerEntity));
            }

        }
    }

    @Override
    public void OnFailure(Throwable t) {

    }

    public class ApplicationItem extends RecyclerView.ViewHolder {
        public TextView txtAppName, txtLoanType, txtLoanValue,
                txtNetIncome, txtLoanStatus, txtGrossIncome;
        Button btnMoreInfo;
        LinearLayout ll_plApplication;

        public ApplicationItem(View view) {
            super(view);
            txtAppName = (TextView) itemView.findViewById(R.id.txtAppName);
            txtLoanType = (TextView) itemView.findViewById(R.id.txtLoanType);
            txtLoanValue = (TextView) itemView.findViewById(R.id.txtLoanValue);
            txtNetIncome = (TextView) itemView.findViewById(R.id.txtNetIncome);
            txtLoanStatus = (TextView) itemView.findViewById(R.id.txtLoanStatus);
            txtGrossIncome = (TextView) itemView.findViewById(R.id.txtGrossIncome);
            btnMoreInfo = (Button) itemView.findViewById(R.id.btnMoreInfo);
            ll_plApplication = (LinearLayout) itemView.findViewById(R.id.ll_plApplication);
        }
    }

    @Override
    public ApplicationItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;

        view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.personal_application_item, parent, false);
        return new ApplicationItem(view);

    }

    @Override
    public void onBindViewHolder(PersonalApplicationAdapter.ApplicationItem holder, int position) {
        final PersonalApplicationDisplayEntity applicationDisplayEntity = listApplicationDispEntities.get(position);
        holder.txtAppName.setText("" + applicationDisplayEntity.getApplntName());
        holder.txtLoanType.setText("" +applicationDisplayEntity.getProdName());
        holder.txtLoanValue.setText("" + applicationDisplayEntity.getLoan_Cost());
        holder.txtLoanStatus.setText("" + applicationDisplayEntity.getApplnStatus());
        holder.txtNetIncome.setText("" + applicationDisplayEntity.getNet_Income());
        holder.txtGrossIncome.setText("" + applicationDisplayEntity.getGross_Income());

        holder.btnMoreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MoreInfoActivity.class);
                intent.putExtra(Constants.WEB_URL, applicationDisplayEntity.getAppLink());
                mContext.startActivity(intent);

            }
        });
        holder.ll_plApplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new EditApplicationController(mContext).getDisplayApplication(applicationDisplayEntity.getAppId(), PersonalApplicationAdapter.this);
            }
        });
    }


    @Override
    public int getItemCount() {
        return listApplicationDispEntities.size();
    }
}
