package com.rupeeboss.rba.mylist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.model.ChildRBAEntity;
import com.rupeeboss.rba.core.model.SuperRBAEntity;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class GenreAdapter extends ExpandableRecyclerViewAdapter<GenreViewHolder, ArtistViewHolder> {

    Context context;

    public GenreAdapter(Context context, List<? extends ExpandableGroup> groups) {
        super(groups);
        this.context = context;
    }


    // Group header
    @Override
    public GenreViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mylist_item, parent, false);
        return new GenreViewHolder(view);
    }

    // Group header child list
    @Override
    public ArtistViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mylist_child_item, parent, false);
        return new ArtistViewHolder(view);
    }


    @Override
    public void onBindChildViewHolder(ArtistViewHolder holder, int flatPosition,
                                      ExpandableGroup group, int childIndex) {

        final ChildRBAEntity childRBAEntity = ((SuperRBAEntity) group).getItems().get(childIndex);
        holder.setArtistName(childRBAEntity.getBrokerName());
        holder.setOnClickListener(context, childRBAEntity);
    }

    @Override
    public void onBindGroupViewHolder(GenreViewHolder holder, int flatPosition,
                                      ExpandableGroup group) {

        holder.setGenreTitle(group);
        holder.setClickListener(context, group);
    }
}
