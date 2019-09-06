package com.rupeeboss.rba.loan_fm.new_personalloan;

import android.app.Activity;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core_loan_fm.model.NewLoanApplicationEnity;

import java.util.List;

/**
 * Created by IN-RB on 12-01-2018.
 */

public class NewPersonalLoanApplicationAdapter extends RecyclerView.Adapter<NewPersonalLoanApplicationAdapter.ApplicationItem>implements Filterable {
    Activity mContext;
    List<NewLoanApplicationEnity> mApplicationList;


    public NewPersonalLoanApplicationAdapter(Activity context, List<NewLoanApplicationEnity> mApplicationList) {
        this.mContext = context;
        this.mApplicationList = mApplicationList;
    }



    public ApplicationItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_item_application_newloan, parent, false);
        return new ApplicationItem(itemView);
    }

    @Override
    public void onBindViewHolder(ApplicationItem holder, int position) {
        if (holder instanceof ApplicationItem) {

            final NewLoanApplicationEnity entity = mApplicationList.get(position);
            holder.txtApplicationNumber.setVisibility(View.VISIBLE);
            if (entity.getLeadId() != null) {

                holder.txtApplicationNumber.setText(""+String.valueOf(entity.getLeadId()));
            }else
            {
                holder.txtApplicationNumber.setText("");
            }
            holder.txtPersonName.setText(entity.getName());

            if (entity.getApplDate() != null) {
                holder.txtApplicationDate.setText("" + entity.getApplDate());
            }else
            {
                holder.txtApplicationDate.setText("");
            }

            holder.txtloanamount.setText(""+String.valueOf(entity.getLoanAmount()));


            if (entity.getRBStatus() != null) {

                holder.ivLeadInfo.setVisibility(View.VISIBLE);
                holder.txtHistory.setVisibility(View.VISIBLE);

            } else {

                holder.ivLeadInfo.setVisibility(View.GONE);
                holder.txtHistory.setVisibility(View.GONE);
            }

            holder.lyParent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if (entity.getRBStatus() != null) {
                            Toast.makeText(mContext,"Application Number Already Generated",Toast.LENGTH_SHORT).show();

                    }else{

                        ((NewPersonalApplicaionActivity) mContext).redirectPersonalLoanApply(entity);

                    }

                }
            });

//            holder.txtOverflowMenu.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    openPopUp(view, entity);
//                }
//            });



            holder.ivLeadInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        ((NewPersonalApplicaionActivity) mContext).openLeadDetailPopUp_personal(entity.getLeadId());
                    }catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });

            try {
                Glide.with(mContext)
                        .load(entity.getBank_image())
                        .into(holder.imgbankLogo);

                Glide.with(mContext)
                        .load(entity.getProgress_image())
                        .into(holder.imgStatus);
                //change Fresco

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public int getItemCount() {

            return mApplicationList.size();

    }

    public class ApplicationItem extends RecyclerView.ViewHolder {

        TextView txtOverflowMenu, txtApplicationDate, txtApplicationNumber, txtloanamount, txtPersonName,txtHistory;
        ImageView imgbankLogo,imgStatus,ivLeadInfo;
        LinearLayout lyParent;

        public ApplicationItem(View itemView) {
            super(itemView);
            txtHistory = (TextView) itemView.findViewById(R.id.txtHistory);
            txtOverflowMenu = (TextView) itemView.findViewById(R.id.txtOverflowMenu);
            txtApplicationDate = (TextView) itemView.findViewById(R.id.txtApplicationDate);
            txtApplicationNumber = (TextView) itemView.findViewById(R.id.txtApplicationNumber);
            txtloanamount = (TextView) itemView.findViewById(R.id.txtloanamount);
            txtPersonName = (TextView) itemView.findViewById(R.id.txtPersonName);
            imgbankLogo = (ImageView) itemView.findViewById(R.id.imgbankLogo);
            imgStatus = (ImageView) itemView.findViewById(R.id.imgStatus);
            lyParent = (LinearLayout) itemView.findViewById(R.id.lyParent);
            ivLeadInfo  = (ImageView) itemView.findViewById(R.id.ivLeadInfo);
        }
    }

//    public void refreshAdapter(List<FmPersonalLoanRequest> list) {
//        mAppListFiltered = list;
//    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
//                if (charString.isEmpty()) {
//                    mAppListFiltered = mAppList;
//                } else {
//                    try {
//                        List<FmPersonalLoanRequest> filteredList = new ArrayList<>();
//                        for (FmPersonalLoanRequest row : mAppList) {
//                            if (row.getPersonalLoanRequest().getApplicantNme().toLowerCase().contains(charString.toLowerCase())) {
//                                filteredList.add(row);
//                            }
//                        }
//                        mAppListFiltered = filteredList;
//                    }
//                    catch (Exception ex)
//                    {
//                        ex.printStackTrace();
//                    }
//
//                }
                FilterResults filterResults = new FilterResults();
             //   filterResults.values = mAppListFiltered;
                return filterResults;
        }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
               // mAppListFiltered = (ArrayList<FmPersonalLoanRequest>) filterResults.values;
              //  notifyDataSetChanged();
            }
        };
    }

}
