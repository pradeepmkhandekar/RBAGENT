package com.rupeeboss.rba.businessloan;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.model.BLQuoteEntity;
import com.rupeeboss.rba.core.response.BLQuoteResponse;
import com.rupeeboss.rba.quotes.QuoteInfoActivity;

/**
 * Created by Rajeev Ranjan on 27/01/2017.
 */

public class BlQuoteAdapter extends RecyclerView.Adapter<BlQuoteAdapter.BankQuotesItem> {


    Activity mContext;
    BLQuoteResponse blQuoteResponse;


    public BlQuoteAdapter(Activity context, BLQuoteResponse blQuoteResponse) {
        mContext = context;
        this.blQuoteResponse = blQuoteResponse;
    }

    public class BankQuotesItem extends RecyclerView.ViewHolder {

        TextView tvLoanAmt, tvBestRate, tvBankName, tvBestEmi, tvLoanTenure, tvProcessingFee, btnApply, tvEligibleLoan;
        ImageView ivBankLogo, ivInfo;

        public BankQuotesItem(View view) {
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
            tvEligibleLoan = (TextView) itemView.findViewById(R.id.tvEligibleLoan);
        }
    }

    @Override
    public BankQuotesItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;

        view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.layout_quotes_bank_item, parent, false);
        return new BankQuotesItem(view);

    }

    @Override
    public void onBindViewHolder(BankQuotesItem holder, final int position) {

        final BLQuoteEntity blQuoteEntity = blQuoteResponse.getData().get(position);
        holder.tvLoanAmt.setText("" + blQuoteEntity.getLoanRequired());
        holder.tvBestRate.setText("" + blQuoteEntity.getRoi());
        holder.tvBankName.setText("" + blQuoteEntity.getBank_Name());
        holder.tvBestEmi.setText("" + blQuoteEntity.getEmi());
        holder.tvLoanTenure.setText("" + blQuoteEntity.getLoanTenure());
        holder.tvProcessingFee.setText("" + blQuoteEntity.getProcessingfee());
        holder.tvEligibleLoan.setText("" + blQuoteEntity.getLoan_eligible());
        Glide.with(mContext)
                .load(blQuoteEntity.getBank_Logo())
                .into(holder.ivBankLogo);
        //change Fresco


        holder.btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // ((QuoteActivity) mContext).redirectToApplyLoan(blQuoteEntity);
                Toast.makeText(mContext, "Thanks", Toast.LENGTH_SHORT).show();

            }
        });
        holder.ivInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, QuoteInfoActivity.class).putExtra("BL_QUOTE", blQuoteEntity));
            }
        });

    }

    @Override
    public int getItemCount() {
        return blQuoteResponse.getData().size();
    }


}