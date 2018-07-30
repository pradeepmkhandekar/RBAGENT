package com.rupeeboss.rba.contact;

import android.app.Activity;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rupeeboss.rba.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by IN-RB on 09-02-2017.
 */
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.CallingItem> {

    Activity mContext;
    private ArrayList<SelectUser> arrayList;
    public List<SelectUser> _data;

    public ContactAdapter(Activity context, List<SelectUser> selectUsers){
        _data = selectUsers;
        mContext = context;
        this.arrayList = new ArrayList<SelectUser>();
        this.arrayList.addAll(_data);

    }

    public class CallingItem extends  RecyclerView.ViewHolder{
        CircleImageView imageView;
        TextView title, phone;
        LinearLayout layout_call;

        public CallingItem(View itemView) {
            super(itemView);

           title = (TextView)itemView.findViewById(R.id.name);
           phone = (TextView) itemView.findViewById(R.id.no);
           imageView = (CircleImageView) itemView.findViewById(R.id.pic);
            layout_call = (LinearLayout)itemView.findViewById(R.id.layout_call);
        }
    }

    @Override
    public CallingItem onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = null;
        view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.layout_contact_item,parent,false);
        return  new CallingItem(view);

    }

    @Override
    public void onBindViewHolder(CallingItem holder, int position) {

        final SelectUser data = _data.get(position);
        holder.title.setText(data.getName());
        holder.phone.setText(data.getPhone());

        // Set image if exists
        try {
            if(data.getThumb() != null){
                holder.imageView.setImageBitmap(data.getThumb());
            }else {
                holder.imageView.setImageResource(R.drawable.contact);
            }

        }catch (OutOfMemoryError e){
            // Add default picture
            holder.imageView.setImageResource(R.drawable.contact);
            e.printStackTrace();
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(data.getPhone().length() >0) {
                    ((ContactActivity) mContext).dialCall(data.getPhone().toString());
                }

            }
        });
    }


    @Override
    public int getItemCount() {
        return _data.size();
    }


    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        _data.clear();
        if (charText.length() == 0) {
            _data.addAll(arrayList);
        } else {
            for (SelectUser wp : arrayList) {
                if (wp.getName().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    _data.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
}
