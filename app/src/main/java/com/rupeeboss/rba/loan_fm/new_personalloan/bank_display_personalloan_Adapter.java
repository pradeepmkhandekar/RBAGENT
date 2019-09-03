package com.rupeeboss.rba.loan_fm.new_personalloan;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.datacomp.magicfinmart.R;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.model.LstCityBankdetailEntity;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.model.LstCitywiseBankLoanEntity;

/**
 * Created by IN-RB on 13-03-2019.
 */

public class bank_display_personalloan_Adapter  extends RecyclerView.Adapter<bank_display_personalloan_Adapter.PLQuotesItem>{
    Activity mContext;
    List<LstCitywiseBankLoanEntity> quoteEntities;
    boolean isclick = false;

    //bank_display_personalloan.xml
    public bank_display_personalloan_Adapter(Activity mContext, List<LstCitywiseBankLoanEntity> quoteEntities) {
        this.mContext = mContext;
        this.quoteEntities = quoteEntities;
    }

    @Override
    public bank_display_personalloan_Adapter.PLQuotesItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.bank_display_personalloan,parent,false);
        return  new bank_display_personalloan_Adapter.PLQuotesItem(view);
    }

    @Override
    public void onBindViewHolder(final bank_display_personalloan_Adapter.PLQuotesItem holder,final int position) {
        final LstCitywiseBankLoanEntity quoteEntity1 = quoteEntities.get(position);
        LstCityBankdetailEntity quoteEntity;
        quoteEntity=quoteEntity1.getLstCityProdBank().get(0);
           //     = quoteEntity1.getLstCityProdBank();

        if(quoteEntity1.getIs_Cash())
        {
            if (quoteEntity1.getBank_Id().equals("2152")) {
                holder.lyParent.setVisibility(View.VISIBLE);
                holder.lyParent.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            } else {
                holder.lyParent.setVisibility(View.GONE);
                holder.lyParent.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
            }

        }else
        {
            holder.lyParent.setVisibility(View.VISIBLE);
            holder.lyParent.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
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
       // holder.tvBankName.setText(""+quoteEntity1.getBank_Name());
        holder.tvdet_ROI.setText(""+quoteEntity.getBest_ROI());
        holder.tvdet_ProcessingFee.setText(""+quoteEntity.getProcessing_Fees());

        holder.tvsegment.setText(""+quoteEntity.getSeqment());
        holder.tvroi.setText(""+quoteEntity.getBest_ROI());
        holder.tvPer_Lac_EMI.setText(""+quoteEntity.getPer_Lac_EMI());
        holder.tvLoanAmount.setText(""+quoteEntity.getLoan_Amt());
        holder.tvTenure.setText(""+quoteEntity.getTenure());
        holder.tvAge.setText(""+quoteEntity.getAge());

        holder.tvminSalary.setText(""+quoteEntity.getMin_Salary());
        holder.tvWorkExperence.setText(""+quoteEntity.getMin_Work_Exp());
        holder.tvProcessingFees.setText(""+quoteEntity.getProcessing_Fees());
        holder.tvPrepayment.setText(""+quoteEntity.getPrepayment_charges());

        holder.tvCibilscore.setText(""+quoteEntity.getMin_Cibil_Score());
        holder.tvForeclosure.setText(""+quoteEntity.getForeclosure_charges());

        Glide.with(mContext)
                .load(quoteEntity1.getBank_URL())
                .into(holder.ivBankLogo);

        holder.rvhlknowmore.setVisibility(View.GONE);
        holder.ivArrow.setImageDrawable(mContext.getResources().getDrawable(R.drawable.downarrowread));
        holder.ivcloseArrow.setImageDrawable(mContext.getResources().getDrawable(R.drawable.down_arrow));

        holder.llbtnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((bank_selection_personalloanActivity)mContext).redirectToApplyBank(quoteEntity1);
            }
        });
        holder.ivArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isclick)
                {
                    isclick=false;
                   // holder.ivArrow.setImageDrawable(mContext.getResources().getDrawable(R.drawable.down_arrow));
                   // holder.ivcloseArrow.setImageDrawable(mContext.getResources().getDrawable(R.drawable.down_arrow));

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


    }

    @Override
    public int getItemCount() {
        return quoteEntities.size();
    }

    public class PLQuotesItem   extends RecyclerView.ViewHolder {
        TextView   btnApply,
                tvsegment, tvroi, tvPer_Lac_EMI,tvLoanAmount,
                tvTenure,tvAge,tvminSalary,tvWorkExperence,
                tvProcessingFees,tvPrepayment,tvCibilscore,tvForeclosure
                ,tvdet_Emi,tvdet_ROI,tvdet_ProcessingFee;
        ImageView ivBankLogo,ivArrow,ivcloseArrow,ivbtnApply;
        LinearLayout llbtnreadterms,llbtnApply,rvhlknowmore,llbacklist,lyParent;

        public PLQuotesItem(View itemView) {
            super(itemView);

            tvdet_Emi = (TextView) itemView.findViewById(R.id.tvdet_Emi);
            tvdet_ProcessingFee = (TextView) itemView.findViewById(R.id.tvdet_ProcessingFee);
            tvdet_ROI = (TextView) itemView.findViewById(R.id.tvdet_ROI);

            tvCibilscore = (TextView) itemView.findViewById(R.id.tvCibilscore);
            tvForeclosure = (TextView) itemView.findViewById(R.id.tvForeclosure);

           // btnreadterm = (TextView) itemView.findViewById(R.id.btnreadterm);

            btnApply = (TextView) itemView.findViewById(R.id.btnApply);
            tvsegment = (TextView) itemView.findViewById(R.id.tvsegment);
            tvroi = (TextView) itemView.findViewById(R.id.tvroi);
            tvPer_Lac_EMI = (TextView) itemView.findViewById(R.id.tvPer_Lac_EMI);

            tvLoanAmount =(TextView)itemView.findViewById(R.id.tvLoanAmount);
            tvTenure = (TextView) itemView.findViewById(R.id.tvTenure);
            tvAge = (TextView) itemView.findViewById(R.id.tvAge);
            tvminSalary = (TextView) itemView.findViewById(R.id.tvminSalary);
            tvWorkExperence = (TextView) itemView.findViewById(R.id.tvWorkExperence);
            tvProcessingFees = (TextView) itemView.findViewById(R.id.tvProcessingFees);
            tvPrepayment = (TextView) itemView.findViewById(R.id.tvPrepayment);

            ivBankLogo = (ImageView) itemView.findViewById(R.id.ivBankLogo);
            ivArrow = (ImageView) itemView.findViewById(R.id.ivArrow);
            ivcloseArrow = (ImageView) itemView.findViewById(R.id.ivcloseArrow);
            ivbtnApply= (ImageView) itemView.findViewById(R.id.ivbtnApply);

            rvhlknowmore = (LinearLayout) itemView.findViewById(R.id.rvhlknowmore);

            lyParent=(LinearLayout) itemView.findViewById(R.id.lyParent);
            llbtnreadterms = (LinearLayout) itemView.findViewById(R.id.llbtnreadterms);
            llbtnApply=(LinearLayout) itemView.findViewById(R.id.llbtnApply);
            llbacklist=(LinearLayout) itemView.findViewById(R.id.llbacklist);
        }
    }

    public void  updateAdapter(List<LstCitywiseBankLoanEntity> tempquoteEntities){
        quoteEntities = tempquoteEntities;
        notifyDataSetChanged();
    }
}
