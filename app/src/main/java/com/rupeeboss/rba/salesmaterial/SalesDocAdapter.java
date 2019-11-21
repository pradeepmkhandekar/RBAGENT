package com.rupeeboss.rba.salesmaterial;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.rupeeboss.rba.R;

import com.rupeeboss.rba.core.model.DocsEntity;
import com.rupeeboss.rba.utility.CustomImageView;

import java.util.List;


/**
 * Created by IN-RB on 23-02-2018.
 */

public class SalesDocAdapter extends RecyclerView.Adapter<SalesDocAdapter.SalesDocItem> implements View.OnClickListener {


    Context mContex;
    List<DocsEntity> docLst;

    public SalesDocAdapter(Context mContex, List<DocsEntity> docLst) {
        this.mContex = mContex;
        this.docLst = docLst;
    }


    public class SalesDocItem extends RecyclerView.ViewHolder {

        CustomImageView ivProduct;
        LinearLayout lyParent;


        public SalesDocItem(View itemView) {
            super(itemView);
            ivProduct = (CustomImageView) itemView.findViewById(R.id.ivProduct);
            lyParent = (LinearLayout) itemView.findViewById(R.id.lyParent);

        }


    }


    @Override
    public SalesDocAdapter.SalesDocItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_doc_item, parent, false);
        return new SalesDocAdapter.SalesDocItem(itemView);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.lyParent) {
            ((SalesDetailActivity) mContex).redirectToApplyMain((DocsEntity) v.getTag(R.id.lyParent));
        }
    }

    @Override
    public void onBindViewHolder(final SalesDocAdapter.SalesDocItem holder, int position) {

        final DocsEntity docsEntity = docLst.get(position);
        holder.lyParent.setTag(R.id.lyParent, docsEntity);
        holder.lyParent.setOnClickListener(SalesDocAdapter.this);

        SimpleTarget target = new SimpleTarget<Bitmap>(300,300) {
            @Override
            public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {
                if (bitmap != null) {
                    holder.lyParent.setOnClickListener(SalesDocAdapter.this);
                    holder.ivProduct.setImageBitmap(bitmap);
                }
            }

            @Override
            public void onLoadFailed(Exception e, Drawable errorDrawable) {

                holder.lyParent.setOnClickListener(null);
            }
        };

        Glide.with(mContex)
                .load(docsEntity.getImage_path())
                .asBitmap()
                .into(holder.ivProduct);


    }

    @Override
    public int getItemCount() {
        return docLst.size();
    }
}