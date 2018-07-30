package com.rupeeboss.rba.mylist;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rupeeboss.rba.R;

import com.rupeeboss.rba.core.model.ChildRBAEntity;
import com.rupeeboss.rba.mybuisness.BuisnessPopUpAdapter;

import java.util.List;

/**
 * Created by IN-RB on 24-08-2017.
 */

public class MyListPopUpAdapter  extends RecyclerView.Adapter<MyListPopUpAdapter.MyListPopUpItem>{

    Activity mContext;
    List<ChildRBAEntity> myListEnityList;

    public MyListPopUpAdapter(Activity mContext, List<ChildRBAEntity> myListEnityList) {
        this.mContext = mContext;
        this.myListEnityList = myListEnityList;
    }

    public class MyListPopUpItem extends RecyclerView.ViewHolder{

        public TextView txtBroker,  txtAssigneeBroker ;

        public MyListPopUpItem(View itemView) {
            super(itemView);
            txtBroker = (TextView)itemView.findViewById(R.id.txtBroker);
            txtAssigneeBroker = (TextView)itemView.findViewById(R.id.txtAssigneeBroker);

        }
    }

    @Override
    public MyListPopUpItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_mylist_item,parent,false);
        return new MyListPopUpAdapter.MyListPopUpItem(itemView);
    }

    @Override
    public void onBindViewHolder(MyListPopUpItem holder, int position) {
        final  ChildRBAEntity childRBAEntity = myListEnityList.get(position);

        if(childRBAEntity.getBrokerName() != null) {
            holder.txtBroker.setText(childRBAEntity.getBrokerName());
        }
        if( childRBAEntity.getParentBrokerName() !=null) {
            holder.txtAssigneeBroker.setText("" + childRBAEntity.getParentBrokerName());
        }


    }



    @Override
    public int getItemCount() {
        return myListEnityList.size();
    }
}
