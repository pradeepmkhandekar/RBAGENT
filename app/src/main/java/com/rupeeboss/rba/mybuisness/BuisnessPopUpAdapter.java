package com.rupeeboss.rba.mybuisness;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.model.BuisnessEnity;

import java.util.List;

/**
 * Created by IN-RB on 29-06-2017.
 */

public class BuisnessPopUpAdapter  extends RecyclerView.Adapter<BuisnessPopUpAdapter.BuisnessPopUPItem> {

  Activity mContext;
    List<BuisnessEnity> buisnessEnityList;

    public BuisnessPopUpAdapter(Activity mContext, List<BuisnessEnity> buisnessList) {
        this.mContext = mContext;
        this.buisnessEnityList = buisnessList;
    }

    public class BuisnessPopUPItem extends RecyclerView.ViewHolder{
        public TextView txtCustName,  txtAmount,  txtProduct,txtbank;

        public BuisnessPopUPItem(View itemView) {
            super(itemView);
            txtCustName = (TextView)itemView.findViewById(R.id.txtCustName);
            txtAmount = (TextView)itemView.findViewById(R.id.txtAmount);
            txtProduct = (TextView)itemView.findViewById(R.id.txtProduct);
            txtbank= (TextView)itemView.findViewById(R.id.txtbank);
        }
    }

    @Override
    public BuisnessPopUPItem onCreateViewHolder(ViewGroup parent, int viewType) {
      View itemView = LayoutInflater.from(parent.getContext())
              .inflate(R.layout.layout_business_item,parent,false);
        return new BuisnessPopUpAdapter.BuisnessPopUPItem(itemView);
    }

    @Override
    public void onBindViewHolder(BuisnessPopUPItem holder, int position) {

        final  BuisnessEnity buisnessEnity = buisnessEnityList.get(position);
        holder.txtCustName.setText(buisnessEnity.getCustName());
        holder.txtAmount.setText(""+ buisnessEnity.getLoanAmount());
        holder.txtProduct.setText(buisnessEnity.getProduct());
        holder.txtbank.setText(buisnessEnity.getBank());
    }


    @Override
    public int getItemCount() {
        return buisnessEnityList.size();
    }
}
