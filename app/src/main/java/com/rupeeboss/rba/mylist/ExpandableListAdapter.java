package com.rupeeboss.rba.mylist;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.model.ChildRBAEntity;
import com.rupeeboss.rba.core.model.SuperRBAEntity;

import java.util.List;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<SuperRBAEntity> _listDataHeader; // header titles
    // child data in format of header title, child title
    //private HashMap<String, List<String>> _listDataChild;

    public ExpandableListAdapter(Context context, List<SuperRBAEntity> listDataHeader) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        //this._listDataChild = listChildData;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return _listDataHeader.get(groupPosition).getChildlst().get(childPosititon);
        /*return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);*/
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        ChildRBAEntity childRBAEntity = (ChildRBAEntity) getChild(groupPosition, childPosition);
        final String childText = childRBAEntity.getBrokerName();

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.mylist_child_item, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.tvChildName);
        txtListChild.setPaintFlags(txtListChild.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        txtListChild.setText(childText + " - RBA");

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (_listDataHeader.get(groupPosition).getChildlst() != null)
            return _listDataHeader.get(groupPosition).getChildlst().size();
        else
            return 0;
        /*return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();*/
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        final SuperRBAEntity superRBAEntity = (SuperRBAEntity) getGroup(groupPosition);
        final String headerTitle = superRBAEntity.getBrokerName();
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.mylist_item, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.tvSuperRbaNAme);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setPaintFlags(lblListHeader.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        lblListHeader.setText(headerTitle + "- Super RBA");
        lblListHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((GroupListActivity) _context).redirectTomyLead(superRBAEntity.getBrokerId());
            }
        });
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}