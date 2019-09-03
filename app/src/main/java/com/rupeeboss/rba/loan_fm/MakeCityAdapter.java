package com.rupeeboss.rba.loan_fm;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.datacomp.magicfinmart.R;

import java.util.ArrayList;
import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.model.LoanCityEntity;

public class MakeCityAdapter extends ArrayAdapter<LoanCityEntity> {

    Context context;
    int resource, textViewResourceId;
    List<LoanCityEntity> items, tempItems, suggestions;

    public MakeCityAdapter(Context context, int resource, int textViewResourceId, List<LoanCityEntity> items) {
        super(context, resource, items);
        this.context = context;
        this.resource = resource;
        this.textViewResourceId = textViewResourceId;
        this.items = items;
        tempItems = new ArrayList<LoanCityEntity>(items); // this makes the difference.
        suggestions = new ArrayList<LoanCityEntity>();
    }

    @Nullable
    @Override
    public LoanCityEntity getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_people, parent, false);
        }
        LoanCityEntity carMasterEntity = items.get(position);
        if (carMasterEntity != null) {
            TextView lblName = (TextView) view.findViewById(R.id.lbl_name);
            if (lblName != null)
                lblName.setText(carMasterEntity.getCityName());
        }
        return view;
    }

    @Override
    public Filter getFilter() {
        return nameFilter;
    }

    /**
     * Custom Filter implementation for custom suggestions we provide.
     */
    Filter nameFilter = new Filter() {
        @Override
        public CharSequence convertResultToString(Object resultValue) {
            String str = ((LoanCityEntity) resultValue).getCityName();
            return str;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if (constraint != null) {
                suggestions.clear();
                for (LoanCityEntity people : tempItems) {
                    if (people.getCityName().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        suggestions.add(people);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            List<LoanCityEntity> filterList = (ArrayList<LoanCityEntity>) results.values;
            if (results != null && results.count > 0) {
                clear();
                for (LoanCityEntity people : filterList) {
                    add(people);
                    notifyDataSetChanged();
                }
            }
        }
    };
}