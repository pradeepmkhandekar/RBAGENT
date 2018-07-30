package com.rupeeboss.rba.mylist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.model.ChildRBAEntity;

import java.util.List;

/**
 * Created by Rajeev Ranjan on 24/08/2017.
 */

public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.MyViewHolder> {

    Context mContext;
    List<ChildRBAEntity> childRBAEntities;

    public ChildAdapter(Context context, List<ChildRBAEntity> childRBAEntities) {
        this.mContext = context;
        this.childRBAEntities = childRBAEntities;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;
        RecyclerView rvChild;
        ImageView ivPlus;

        public MyViewHolder(View view) {
            super(view);
            //tvName = (TextView) itemView.findViewById(R.id.tvName);
            //rvChild = (RecyclerView) itemView.findViewById(R.id.rvChild);
            ivPlus = (ImageView) itemView.findViewById(R.id.ivPlus);
        }
    }

    @Override
    public ChildAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mylist_item, parent, false);

        return new ChildAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ChildAdapter.MyViewHolder holder, int position) {
        final ChildRBAEntity childRBAEntity = childRBAEntities.get(position);
        //holder.tvName.setText(childRBAEntity.getBrokerName());

        holder.ivPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              /*  if (childRBAEntity.getChildlst() == null || childRBAEntity.getChildlst().equals("")) {
                    holder.rvChild.setVisibility(View.GONE);
                } else {
                    holder.rvChild.setVisibility(View.VISIBLE);
                }*/

                //((MyListActivity) mContext).dialCall(leadDetailsEntity.getMobNo(), leadDetailsEntity.getCustName(), leadDetailsEntity.getLeadId());
            }
        });
        /*holder.linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MyListActivity) mContext).startActivity(new Intent(mContext, FollowUpHistoryActivity.class)
                        .putExtra("PHONE_NUMBER", leadDetailsEntity.getMobNo())
                        .putExtra("STATUS", leadDetailsEntity.getStatus())
                        .putExtra("LEAD_ID", leadDetailsEntity.getLeadId()));
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return childRBAEntities.size();
    }
}
