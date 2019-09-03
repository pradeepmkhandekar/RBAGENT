package com.rupeeboss.rba.calling.history;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.model.LeadHstryDataEntity;

import java.util.List;

/**
 * Created by Nilesh Birhade on 19-10-2016.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyViewHolder> {


    Context mContext;
    List<LeadHstryDataEntity> listHistory;

    public HistoryAdapter(Context context, List<LeadHstryDataEntity> list) {
        this.mContext = context;
        listHistory = list;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtDate, txtStatus, txtremark, txtAssign, txtProduct;//txtName
        LinearLayout linearLayout;


        public MyViewHolder(View view) {
            super(view);
            txtDate = (TextView) itemView.findViewById(R.id.txtDate);
            txtStatus = (TextView) itemView.findViewById(R.id.txtStatus);
            //txtName = (TextView) itemView.findViewById(R.id.txtName);
            txtremark = (TextView) itemView.findViewById(R.id.txtRemark);
            txtAssign = (TextView) itemView.findViewById(R.id.txtAssign);
            txtProduct = (TextView) itemView.findViewById(R.id.txtProduct);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.ll_history_item);

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final LeadHstryDataEntity leadHstryDataEntity = listHistory.get(position);
        holder.txtDate.setText("" + leadHstryDataEntity.getDate());
        holder.txtStatus.setText("" + leadHstryDataEntity.getStatus());
        //holder.txtName.setText(leadHstryDataEntity.getName());
        holder.txtremark.setText("" + leadHstryDataEntity.getRemark());
        holder.txtAssign.setText("" + leadHstryDataEntity.getAssignee());
        holder.txtProduct.setText("" + leadHstryDataEntity.getProduct());


    }

    @Override
    public int getItemCount() {
        return listHistory.size();
    }

}
