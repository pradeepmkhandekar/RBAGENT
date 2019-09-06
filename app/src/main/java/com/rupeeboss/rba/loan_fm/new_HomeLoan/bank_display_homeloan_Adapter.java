package com.rupeeboss.rba.loan_fm.new_HomeLoan;

import android.app.Activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;


import java.util.List;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core_loan_fm.model.LstCityBankdetailEntity;
import com.rupeeboss.rba.core_loan_fm.model.LstCitywiseBankLoanEntity;

/**
 * Created by IN-RB on 13-03-2019.
 */

public class bank_display_homeloan_Adapter extends RecyclerView.Adapter<bank_display_homeloan_Adapter.PLQuotesItem>{
    Activity mContext;
    List<LstCitywiseBankLoanEntity> quoteEntities;
    boolean isclick = false;
    bank_display_homeloanChild_Adapter mAdapter;

    //bank_display_personalloan.xml
    public bank_display_homeloan_Adapter(Activity mContext, List<LstCitywiseBankLoanEntity> quoteEntities) {
        this.mContext = mContext;
        this.quoteEntities = quoteEntities;
    }

    @Override
    public bank_display_homeloan_Adapter.PLQuotesItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.bank_display_homeloan,parent,false);
        return  new bank_display_homeloan_Adapter.PLQuotesItem(view);
    }

    @Override
    public void onBindViewHolder(final bank_display_homeloan_Adapter.PLQuotesItem holder, final int position) {
         final LstCitywiseBankLoanEntity quoteEntity1 = quoteEntities.get(position);
        LstCityBankdetailEntity quoteEntity;
        quoteEntity=quoteEntity1.getLstCityProdBank().get(0);

        if(quoteEntity.getPer_Lac_EMI_outside() == null ||quoteEntity.getPer_Lac_EMI_outside().equals(""))
        {
            try {
                String emi = "" + quoteEntity.getPer_Lac_EMI().split("Rs.")[1];
                String bestemi = "" + "\u20B9" + emi;
                holder.tvdet_Emi.setText(bestemi);
            }catch (Exception ex)
            {
                ex.printStackTrace();
            }


        }else {
            holder.tvdet_Emi.setText("" + quoteEntity.getPer_Lac_EMI_outside());
        }

        holder.tvdet_ROI.setText(""+quoteEntity.getBest_ROI());
        holder.tvdet_ProcessingFee.setText(""+quoteEntity.getProcessing_Fees());


        Glide.with(mContext)
                .load(quoteEntity1.getBank_URL())
                .into(holder.ivBankLogo);

        holder.rvhlknowmore.setVisibility(View.GONE);
        holder.ivArrow.setImageDrawable(mContext.getResources().getDrawable(R.drawable.downarrowread));
        holder.ivcloseArrow.setImageDrawable(mContext.getResources().getDrawable(R.drawable.down_arrow));

        holder.llbtnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((bank_selection_homeloanActivity)mContext).redirectToApplyBankHL(quoteEntity1);
            }
        });
        holder.ivArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isclick)
                {
                    isclick=false;
                    holder.ivArrow.setImageDrawable(mContext.getResources().getDrawable(R.drawable.downarrowread));
                    holder.ivcloseArrow.setImageDrawable(mContext.getResources().getDrawable(R.drawable.down_arrow));

                    holder.rvhlknowmore.setVisibility(View.GONE);
                }else {
                    isclick=true;
                    holder.ivArrow.setImageDrawable(mContext.getResources().getDrawable(R.drawable.uparrowread));
                    holder.ivcloseArrow.setImageDrawable(mContext.getResources().getDrawable(R.drawable.up_arrow));

                    holder.rvhlknowmore.setVisibility(View.VISIBLE);
                }
            }
        });
        holder.llbacklist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
               holder.ivArrow.performClick();
            }
        });
        holder.llbtnreadterms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.ivArrow.performClick();
            }
        });

        if(quoteEntity1.getLstCityProdBank().size() > 0) {
            holder.rvhomeloanQuotes.setLayoutManager(new LinearLayoutManager(mContext));
            mAdapter = new bank_display_homeloanChild_Adapter(mContext, quoteEntity1.getLstCityProdBank());
            holder.rvhomeloanQuotes.setAdapter(mAdapter);
        }
    }

    @Override
    public int getItemCount() {
        return quoteEntities.size();
    }

    public class PLQuotesItem   extends RecyclerView.ViewHolder {
        TextView   btnApply


                ,tvdet_Emi,tvdet_ROI,tvdet_ProcessingFee;
        ImageView ivBankLogo,ivArrow,ivcloseArrow,ivbtnApply;
        LinearLayout llbtnreadterms,llbtnApply,rvhlknowmore,llbacklist,lyParent;

        RecyclerView rvhomeloanQuotes;
        public PLQuotesItem(View itemView) {
            super(itemView);

            rvhomeloanQuotes = (RecyclerView) itemView.findViewById(R.id.rvhomeloanQuotes);

            tvdet_Emi = (TextView) itemView.findViewById(R.id.tvdet_Emi);
            tvdet_ProcessingFee = (TextView) itemView.findViewById(R.id.tvdet_ProcessingFee);
            tvdet_ROI = (TextView) itemView.findViewById(R.id.tvdet_ROI);


            btnApply = (TextView) itemView.findViewById(R.id.btnApply);


            ivBankLogo = (ImageView) itemView.findViewById(R.id.ivBankLogo);
            ivArrow = (ImageView) itemView.findViewById(R.id.ivArrow);
            ivcloseArrow = (ImageView) itemView.findViewById(R.id.ivcloseArrow);
            btnApply = (TextView) itemView.findViewById(R.id.btnApply);
            rvhlknowmore = (LinearLayout) itemView.findViewById(R.id.rvhlknowmore);
            llbtnreadterms = (LinearLayout) itemView.findViewById(R.id.llbtnreadterms);
            llbtnApply=(LinearLayout) itemView.findViewById(R.id.llbtnApply);
            llbacklist=(LinearLayout) itemView.findViewById(R.id.llbacklist);
            lyParent=(LinearLayout) itemView.findViewById(R.id.lyParent);
        }
    }
    public void  updateAdapter(List<LstCitywiseBankLoanEntity> tempquoteEntities){
        quoteEntities = tempquoteEntities;
        notifyDataSetChanged();
    }
}
