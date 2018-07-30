package com.rupeeboss.rba.mylist;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.model.SuperRBAEntity;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import static android.view.animation.Animation.RELATIVE_TO_SELF;

public class GenreViewHolder extends GroupViewHolder {

    private TextView genreName;
    private ImageView arrow;
    private ImageView icon;
    Context context;

    public GenreViewHolder(View itemView) {
        super(itemView);
        genreName = (TextView) itemView.findViewById(R.id.tvSuperRbaNAme);
        arrow = (ImageView) itemView.findViewById(R.id.ivPlus);
        // icon = (ImageView) itemView.findViewById(R.id.list_item_genre_icon);
    }

    public void setGenreTitle(ExpandableGroup genre) {
        if (genre instanceof SuperRBAEntity) {
            genreName.setText(genre.getTitle() + " - Super RBA");
            genreName.setPaintFlags(genreName.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            //icon.setBackgroundResource(((SuperRBAEntity) genre).getIconResId());
        }
    }

    public void setClickListener(final Context context, final ExpandableGroup genre) {
        if (genre instanceof SuperRBAEntity) {
            genreName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((ExpandActivity) context).redirectTomyLead(((ExpandActivity) context).getBrokerId(genre.getTitle()));
                    /*Toast.makeText(context, "" + ((ExpandActivity) context).getBrokerId(genre.getTitle())
                            , Toast.LENGTH_SHORT).show();*/
                }
            });
            //icon.setBackgroundResource(((SuperRBAEntity) genre).getIconResId());
        }
    }

    @Override
    public void expand() {
        animateExpand();
    }

    @Override
    public void collapse() {
        animateCollapse();
    }

    private void animateExpand() {
        RotateAnimation rotate =
                new RotateAnimation(360, 180, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        arrow.setAnimation(rotate);
    }

    private void animateCollapse() {
        RotateAnimation rotate =
                new RotateAnimation(180, 360, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        arrow.setAnimation(rotate);
    }
}
