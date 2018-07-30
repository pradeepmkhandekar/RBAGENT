package com.rupeeboss.rba.sharemessage;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.model.SmsLeadEntity;
import com.rupeeboss.rba.core.request.requestentity.LeadMobEntity;

import java.util.List;

/**
 * Created by Rajeev Ranjan on 27/02/2017.
 */

public class LeadSmsAdapter extends RecyclerView.Adapter<LeadSmsAdapter.MyViewHolder> {

    Activity mContext;
    List<SmsLeadEntity> smsLeadEntities;

    public LeadSmsAdapter(Activity context, List<SmsLeadEntity> smsLeadEntities) {
        this.mContext = context;
        this.smsLeadEntities = smsLeadEntities;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtName, txtMobileNo;
        CheckBox chkLead;
        LinearLayout llsmsItem;

        // LinearLayout linearLayout, linearLayout1;
        public MyViewHolder(View view) {
            super(view);
            txtName = (TextView) itemView.findViewById(R.id.txtName);
            txtMobileNo = (TextView) itemView.findViewById(R.id.txtMobileNo);
            chkLead = (CheckBox) itemView.findViewById(R.id.chkLead);
            llsmsItem = (LinearLayout) itemView.findViewById(R.id.llsmsItem);
        }
    }

    @Override
    public LeadSmsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_sms_item, parent, false);

        return new LeadSmsAdapter.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final LeadSmsAdapter.MyViewHolder holder, int position) {
        final SmsLeadEntity smsLeadEntity = smsLeadEntities.get(position);
        holder.txtName.setText(smsLeadEntity.getCustName());
        holder.txtMobileNo.setText(smsLeadEntity.getMobNo());

        holder.chkLead.setOnCheckedChangeListener(null);
        holder.chkLead.setChecked(smsLeadEntity.isChk());
        if (smsLeadEntity.isSmsflag()) {
            holder.llsmsItem.setBackgroundColor(Color.GREEN);
            holder.llsmsItem.setAlpha(.8f);
        } else {
            holder.llsmsItem.setBackgroundColor(Color.WHITE);
            holder.llsmsItem.setAlpha(.8f);
        }
        holder.chkLead.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                smsLeadEntity.setChk(isChecked);
                LeadMobEntity leadMobEntity = new LeadMobEntity();
                leadMobEntity.setLeadId("" + smsLeadEntity.getLeadId());
                leadMobEntity.setMobNo(smsLeadEntity.getMobNo());
                ((LeadSmsActivity) mContext).checkedContact(leadMobEntity, isChecked);
            }
        });


    }

    @Override
    public int getItemCount() {
        return smsLeadEntities.size();
    }
}
