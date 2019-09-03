package com.rupeeboss.rba.personalloan;

import android.app.Activity;
import android.content.Intent;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.model.PersonalQuoteEntity;

import java.util.List;

/**
 * Created by Rajeev Ranjan on 14/02/2017.
 */

public class PLQuoteAdapter extends RecyclerView.Adapter<PLQuoteAdapter.PLQuotesItem> {

    Activity mContext;
    List<PersonalQuoteEntity> quoteEntities;


    public PLQuoteAdapter(Activity context, List<PersonalQuoteEntity> quoteEntities) {
        mContext = context;
        this.quoteEntities = quoteEntities;
    }

    public class PLQuotesItem extends RecyclerView.ViewHolder {

        TextView tvLoanAmt, tvBestRate, tvBankName, tvBestEmi, tvLoanTenure, tvProcessingFee, btnApply,tvEligibleLoan;
        ImageView ivBankLogo, ivInfo;

        public PLQuotesItem(View view) {
            super(view);
            tvLoanAmt = (TextView) itemView.findViewById(R.id.tvLoanAmt);
            tvBestRate = (TextView) itemView.findViewById(R.id.tvBestRate);
            tvBankName = (TextView) itemView.findViewById(R.id.tvBankName);
            tvBestEmi = (TextView) itemView.findViewById(R.id.tvBestEmi);
            tvLoanTenure = (TextView) itemView.findViewById(R.id.tvLoanTenure);
            tvProcessingFee = (TextView) itemView.findViewById(R.id.tvProcessingFee);
            btnApply = (TextView) itemView.findViewById(R.id.btnApply);
            ivBankLogo = (ImageView) itemView.findViewById(R.id.ivBankLogo);
            ivInfo = (ImageView) itemView.findViewById(R.id.ivInfo);
            tvEligibleLoan =(TextView)itemView.findViewById(R.id.tvEligibleLoan);
        }
    }

    @Override
    public PLQuoteAdapter.PLQuotesItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;

        view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.layout_pl_quote_item, parent, false);
        return new PLQuoteAdapter.PLQuotesItem(view);

    }

    @Override
    public void onBindViewHolder(PLQuoteAdapter.PLQuotesItem holder, final int position) {

        final PersonalQuoteEntity quoteEntity = quoteEntities.get(position);
        holder.tvLoanAmt.setText("" + quoteEntity.getLoanRequired());
        holder.tvBestRate.setText("" + quoteEntity.getRoi());
        holder.tvBankName.setText("" + quoteEntity.getBank_Name());
        holder.tvBestEmi.setText("" + quoteEntity.getEmi());
        holder.tvLoanTenure.setText("" + quoteEntity.getLoanTenure());
        holder.tvProcessingFee.setText("" + quoteEntity.getProcessingfee());
        holder.tvEligibleLoan.setText(""+quoteEntity.getLoan_eligible());
        Glide.with(mContext)
                .load(quoteEntity.getBank_Logo())
                .into(holder.ivBankLogo);
        //change Fresco


        holder.btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((PersonalLoanQuoteActivity) mContext).redirectToApplyLoan(quoteEntity);

            }
        });
        holder.ivInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mContext.startActivity(new Intent(mContext, PLQuoteInfoActivity.class).putExtra("PL_QUOTEINFO", quoteEntity));
            }
        });

    }

    @Override
    public int getItemCount() {
        return quoteEntities.size();
    }

}
