package com.rupeeboss.rba.personalloan;

import android.content.Context;
import android.content.Intent;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.controller.editquote.EditQuoteController;
import com.rupeeboss.rba.core.facade.ProductFacade;
import com.rupeeboss.rba.core.model.CustomerEntity;
import com.rupeeboss.rba.core.model.PersonalQuoteDisplayEntity;
import com.rupeeboss.rba.core.response.EditQuoteResponse;
import com.rupeeboss.rba.utility.Constants;
import com.rupeeboss.rba.webviewinfo.MoreInfoActivity;

import java.util.List;

/**
 * Created by IN-RB on 14-02-2017.
 */
public class PersonalQuotesAdapter extends RecyclerView.Adapter<PersonalQuotesAdapter.QuotesItem> implements IResponseSubcriber {

    Context mContext;
    List<PersonalQuoteDisplayEntity> listQuoteDisplayEntities;

    public PersonalQuotesAdapter(Context context, List<PersonalQuoteDisplayEntity> listQuoteDisplayEntities) {
        mContext = context;
        this.listQuoteDisplayEntities = listQuoteDisplayEntities;
    }

    @Override
    public void OnSuccess(APIResponse response, String message) throws InterruptedException {
        if (response instanceof EditQuoteResponse) {
            if (response.getStatus_Id() == 0) {
                CustomerEntity customerEntity = ((EditQuoteResponse) response).getData();
                mContext.startActivity(new Intent(mContext, PersonalLoanActivity.class)
                        .putExtra("IS_EDIT", true)
                        .putExtra("CUST_DETAILS", customerEntity));
            }

        }
    }

    @Override
    public void OnFailure(Throwable t) {

    }


    public class QuotesItem extends RecyclerView.ViewHolder {
        public TextView txtAppName, txtLoanType, txtLoanValue,
                txtNetIncome, txtLoanStatus, txtGrossIncome;
        Button btnMoreInfo;
        LinearLayout ll_quotesItem;

        public QuotesItem(View view) {
            super(view);
            txtAppName = (TextView) itemView.findViewById(R.id.txtAppName);
            txtLoanType = (TextView) itemView.findViewById(R.id.txtLoanType);
            txtLoanValue = (TextView) itemView.findViewById(R.id.txtLoanValue);
            txtNetIncome = (TextView) itemView.findViewById(R.id.txtNetIncome);
            txtLoanStatus = (TextView) itemView.findViewById(R.id.txtLoanStatus);
            txtGrossIncome = (TextView) itemView.findViewById(R.id.txtGrossIncome);
            btnMoreInfo = (Button) itemView.findViewById(R.id.btnMoreInfo);
            ll_quotesItem = (LinearLayout) itemView.findViewById(R.id.ll_quotesItem);
        }
    }

    @Override
    public QuotesItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;

        view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.personal_quotes_item, parent, false);
        return new QuotesItem(view);

    }

    @Override
    public void onBindViewHolder(QuotesItem holder, int position) {
        final PersonalQuoteDisplayEntity quoteDisplayEntity = listQuoteDisplayEntities.get(position);
        holder.txtAppName.setText("" + quoteDisplayEntity.getApplicantNme());
        holder.txtLoanType.setText("" + new ProductFacade(mContext).getProductType(quoteDisplayEntity.getProductId()));
        holder.txtLoanValue.setText("" + quoteDisplayEntity.getLoanRequired());
        holder.txtLoanStatus.setText("" + quoteDisplayEntity.getStatusX());
        holder.txtNetIncome.setText("" + quoteDisplayEntity.getApplicantIncome());
        holder.txtGrossIncome.setText("" + quoteDisplayEntity.getTurnover());

        holder.btnMoreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MoreInfoActivity.class);
                intent.putExtra(Constants.WEB_URL, quoteDisplayEntity.getUrl());
                mContext.startActivity(intent);

            }
        });
        holder.ll_quotesItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new EditQuoteController(mContext).getCustomerDetails("" + quoteDisplayEntity.getID(), PersonalQuotesAdapter.this);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listQuoteDisplayEntities.size();
    }
}
