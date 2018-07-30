package com.rupeeboss.rba.calling.followup;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.model.FollowUpEntity;

import java.util.List;

/**
 * Created by Rajeev Ranjan on 24/10/2016.
 */

public class FollowUpAdapter extends RecyclerView.Adapter<FollowUpAdapter.MyViewHolder> {


    Context mContext;
    List<FollowUpEntity> followUpEntities;
    //List<LeadHstryDataEntity> listHistory;

    public FollowUpAdapter(Context context, List<FollowUpEntity> followUpEntities) {
        this.mContext = context;
        this.followUpEntities = followUpEntities;
        //listHistory = list;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtName, txtStatus, txtRemark;
        ImageView btnCall;
        LinearLayout linearLayout, linearLayout1;


        public MyViewHolder(View view) {
            super(view);
            txtName = (TextView) itemView.findViewById(R.id.txtFollowupName);
            txtStatus = (TextView) itemView.findViewById(R.id.txtFollowupStatus);
            txtRemark = (TextView) itemView.findViewById(R.id.txtFollowupRemark);
            btnCall = (ImageView) itemView.findViewById(R.id.btnCall);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.ll_followup_item);
            linearLayout1 = (LinearLayout) itemView.findViewById(R.id.ll_followup_name_status_remark);

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.followup_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final FollowUpEntity followUpEntity = followUpEntities.get(position);
        holder.txtName.setText(followUpEntity.getName());
        holder.txtStatus.setText(followUpEntity.getStatus());
        holder.txtRemark.setText(followUpEntity.getRemark());
        holder.btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((FollowUpActivity) mContext).dialCall(followUpEntity.getMobileNo(), followUpEntity.getName(), followUpEntity.getLeadId());
            }
        });
        holder.linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ((FollowUpActivity) mContext).startActivity(new Intent(mContext, FollowUpHistoryActivity.class)
                        .putExtra("PHONE_NUMBER", followUpEntity.getMobileNo())
                        .putExtra("STATUS", followUpEntity.getStatus())
                        .putExtra("LEAD_ID", followUpEntity.getLeadId()));

            }
        });

    }

    @Override
    public int getItemCount() {
        return followUpEntities.size();
    }
}
