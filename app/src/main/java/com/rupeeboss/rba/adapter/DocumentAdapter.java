package com.rupeeboss.rba.adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.model.DocumentEntity;

import java.util.List;

/**
 * Created by Rajeev Ranjan on 16/05/2017.
 */

public class DocumentAdapter extends RecyclerView.Adapter<DocumentAdapter.MyViewHolder> {
    Context context;
    List<DocumentEntity> documentEntities;

    public DocumentAdapter(Context context, List<DocumentEntity> documentEntities) {
        this.context = context;
        this.documentEntities = documentEntities;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle, tvDocReq;

        public MyViewHolder(View view) {
            super(view);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvDocReq = (TextView) itemView.findViewById(R.id.tvDocReq);
        }
    }

    @Override
    public DocumentAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.document_item, parent, false);

        return new DocumentAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DocumentAdapter.MyViewHolder holder, int position) {
        final DocumentEntity documentEntity = documentEntities.get(position);

        StringBuilder reqDocument = new StringBuilder();
        for (int i = 0; i < documentEntity.getDocDtls().size(); i++) {
            if (i < documentEntity.getDocDtls().size() - 1)
                reqDocument.append(i + 1 + ") " + documentEntity.getDocDtls().get(i) + "\n");
            else
                reqDocument.append(i + 1 + ") " + documentEntity.getDocDtls().get(i));
        }

        holder.tvDocReq.setText(reqDocument);
        holder.tvTitle.setText(documentEntity.getProdName());

    }

    @Override
    public int getItemCount() {
        return documentEntities.size();
    }
}
