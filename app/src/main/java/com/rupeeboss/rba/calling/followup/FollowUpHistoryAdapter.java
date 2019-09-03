package com.rupeeboss.rba.calling.followup;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.model.FollowUpHistoryDetailsEntity;

import java.util.List;

/**
 * Created by Rajeev Ranjan on 27/10/2016.
 */

public class FollowUpHistoryAdapter extends RecyclerView.Adapter<FollowUpHistoryAdapter.MyViewHolder> {
    Context mContext;
    List<FollowUpHistoryDetailsEntity> followUpHistoryDetailsEntityList;

    public FollowUpHistoryAdapter(Context context, List<FollowUpHistoryDetailsEntity> followUpHistoryDetailsEntityList) {
        this.mContext = context;
        this.followUpHistoryDetailsEntityList = followUpHistoryDetailsEntityList;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtName, txtStatus, txtRemark, txtDate, txtTime;
        LinearLayout linearLayout;

        public MyViewHolder(View view) {
            super(view);
            txtName = (TextView) itemView.findViewById(R.id.txtFollowupHistoryName);
            txtStatus = (TextView) itemView.findViewById(R.id.txtFollowupHistoryStatus);
            txtRemark = (TextView) itemView.findViewById(R.id.txtFollowupHistoryRemark);
            txtDate = (TextView) itemView.findViewById(R.id.txtFollowupHistoryDate);
            txtTime = (TextView) itemView.findViewById(R.id.txtFollowupHistoryTime);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.ll_followup_history);

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.followup_history_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final FollowUpHistoryDetailsEntity followUpHistoryDetailsEntity = followUpHistoryDetailsEntityList.get(position);
        holder.txtName.setText(followUpHistoryDetailsEntity.getName());
        holder.txtStatus.setText(followUpHistoryDetailsEntity.getStatus());
        holder.txtRemark.setText(followUpHistoryDetailsEntity.getRemark());
        holder.txtDate.setText(followUpHistoryDetailsEntity.getDate());
        holder.txtTime.setText(followUpHistoryDetailsEntity.getTime());

    }

    @Override
    public int getItemCount() {
        return followUpHistoryDetailsEntityList.size();
    }
}
