package com.rupeeboss.rba.mylist;

import android.content.Context;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.model.SuperRBAEntity;

import java.util.List;

/**
 * Created by Rajeev Ranjan on 23/08/2017.
 */

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.MyViewHolder> {

    Context mContext;
    List<SuperRBAEntity> superRBAEntities;

    public MyListAdapter(Context context, List<SuperRBAEntity> superRBAEntities) {
        this.mContext = context;
        this.superRBAEntities = superRBAEntities;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;
        RecyclerView rvChild;
        ImageView ivPlus;
        LinearLayout lyParent;

        public MyViewHolder(View view) {
            super(view);
            //tvName = (TextView) itemView.findViewById(R.id.tvName);
            //rvChild = (RecyclerView) itemView.findViewById(R.id.rvChild);
            rvChild.setLayoutManager(new LinearLayoutManager(mContext));
            ivPlus = (ImageView) itemView.findViewById(R.id.ivPlus);
            lyParent = (LinearLayout)itemView.findViewById(R.id.lyParent);
        }
    }

    @Override
    public MyListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mylist_item, parent, false);

        return new MyListAdapter.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final MyListAdapter.MyViewHolder holder, int position) {
        final SuperRBAEntity superRBAEntity = superRBAEntities.get(position);
       // holder.tvName.setText(superRBAEntity.getBrokerName());
        if (superRBAEntity.getChildlst() == null || superRBAEntity.getChildlst().equals("")) {
            holder.ivPlus.setVisibility(View.GONE);
        } else {
            holder.ivPlus.setVisibility(View.VISIBLE);
        }
        holder.ivPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*if (superRBAEntity.getChildlst() == null || superRBAEntity.getChildlst().equals("")) {
                    holder.rvChild.setVisibility(View.GONE);
                } else {
                    if (holder.rvChild.getVisibility() == View.VISIBLE) {
                        holder.rvChild.setVisibility(View.GONE);
                    } else {
                        holder.rvChild.setVisibility(View.VISIBLE);
                        ChildAdapter childAdapter = new ChildAdapter(mContext, superRBAEntity.getChildlst());
                        holder.rvChild.setAdapter(childAdapter);
                    }

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

//        holder.lyParent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ((MyListActivity)mContext).redirectToList(superRBAEntity);
//
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return superRBAEntities.size();
    }

}
