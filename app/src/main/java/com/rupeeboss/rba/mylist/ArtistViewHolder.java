package com.rupeeboss.rba.mylist;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.widget.TextView;

import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.model.ChildRBAEntity;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

public class ArtistViewHolder extends ChildViewHolder {

    private TextView childTextView;
    Context context;

    public ArtistViewHolder(View itemView) {
        super(itemView);
        childTextView = (TextView) itemView.findViewById(R.id.tvChildName);
    }

    public void setArtistName(String name) {
        childTextView.setText(name + " - RBA");
        childTextView.setPaintFlags(childTextView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    public void setOnClickListener(final Context context, final ChildRBAEntity childRBAEntity) {
        childTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(context, "" + childRBAEntity.getBrokerId(), Toast.LENGTH_SHORT).show();
                ((ExpandActivity) context).redirectTomyLead(childRBAEntity.getBrokerId());

            }
        });
    }
}
