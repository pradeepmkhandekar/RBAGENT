package com.rupeeboss.rba.mylead;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rupeeboss.rba.R;
import com.rupeeboss.rba.calling.followup.FollowUpHistoryActivity;
import com.rupeeboss.rba.core.model.LeadDetailsEntity;

import java.util.List;

/**
 * Created by Rajeev Ranjan on 03/03/2017.
 */

public class MyLeadAdapter extends RecyclerView.Adapter<MyLeadAdapter.MyViewHolder> {

    Context mContext;
    List<LeadDetailsEntity> leadDetailsEntities;

    public MyLeadAdapter(Context context, List<LeadDetailsEntity> leadDetailsEntities) {
        this.mContext = context;
        this.leadDetailsEntities = leadDetailsEntities;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtName, txtMobileNo, txtStatus, txtLoanAmnt,txtProdType ,txtAssignee,txtRemark;
        ImageView btncallImage;
        LinearLayout llsmsItem, linearLayout1;

        // LinearLayout linearLayout, linearLayout1;
        public MyViewHolder(View view) {
            super(view);
            txtName = (TextView) itemView.findViewById(R.id.txtName);
            txtMobileNo = (TextView) itemView.findViewById(R.id.txtMobileNo);
            txtStatus = (TextView) itemView.findViewById(R.id.txtStatus);
            txtAssignee = (TextView) itemView.findViewById(R.id.txtAssignee);
            txtRemark = (TextView) itemView.findViewById(R.id.txtRemark);

            txtLoanAmnt = (TextView) itemView.findViewById(R.id.txtLoanAmnt);
            txtProdType = (TextView) itemView.findViewById(R.id.txtProdType);
            llsmsItem = (LinearLayout) itemView.findViewById(R.id.llsmsItem);
            btncallImage = (ImageView) itemView.findViewById(R.id.callImage);
            llsmsItem = (LinearLayout) itemView.findViewById(R.id.llsmsItem);
            linearLayout1 = (LinearLayout) itemView.findViewById(R.id.ll_followup_name_status_remark);


        }
    }

    @Override
    public MyLeadAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_mylead_item, parent, false);

        return new MyLeadAdapter.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final MyLeadAdapter.MyViewHolder holder, int position) {
        final LeadDetailsEntity leadDetailsEntity = leadDetailsEntities.get(position);
        holder.txtName.setText(leadDetailsEntity.getCustName());
        holder.txtMobileNo.setText(leadDetailsEntity.getMobNo());
        holder.txtStatus.setText(leadDetailsEntity.getStatus());

        holder.txtProdType.setText(leadDetailsEntity.getProdType());
        holder.txtLoanAmnt.setText(leadDetailsEntity.getLoanAmnt());
        holder.txtAssignee.setText(leadDetailsEntity.getAssigneeName());
        holder.txtRemark.setText(leadDetailsEntity.getRemark());
        holder.btncallImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyLeadActivity) mContext).dialCall(leadDetailsEntity.getMobNo(), leadDetailsEntity.getCustName(), leadDetailsEntity.getLeadId());
            }
        });
        holder.linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MyLeadActivity) mContext).startActivity(new Intent(mContext, FollowUpHistoryActivity.class)
                        .putExtra("PHONE_NUMBER", leadDetailsEntity.getMobNo())
                        .putExtra("STATUS", leadDetailsEntity.getStatus())
                        .putExtra("LEAD_ID", leadDetailsEntity.getLeadId()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return leadDetailsEntities.size();
    }


}