package com.example.young_jin.supportproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.young_jin.supportproject.R;
import com.example.young_jin.supportproject.models.CardDetail;

import java.util.ArrayList;

/**
 * Created by Young-Jin on 2016-03-07.
 */
public class DetailExpandableAdapter extends BaseExpandableListAdapter {

    private final Context context;
    private ArrayList<CardDetail> groupList = null;
    private ArrayList<ArrayList<String>> childList = null;
    private LayoutInflater inflater = null;
    private ViewHolder viewHolder = null;

    public DetailExpandableAdapter(Context context, ArrayList<CardDetail> groupList, ArrayList<ArrayList<String>> childList) {

        super();
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.groupList = groupList;
        this.childList = childList;
    }

    @Override
    public CardDetail getGroup(int groupPosition) {
        return groupList.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {

            if (groupPosition == 0) {
                viewHolder = new ViewHolder();
                v = inflater.inflate(R.layout.card_detail_top, parent, false);
                v.setTag(viewHolder);
            } else {
                viewHolder = new ViewHolder();
                v = inflater.inflate(R.layout.list_item_card_detail_parent, parent, false);
                viewHolder.tv_groupName = (TextView) v.findViewById(R.id.parent_list_item_crime_title_text_view);
                viewHolder.imageview = (ImageView) v.findViewById(R.id.imageView);
//            viewHolder.iv_image = (ImageView)v.findViewById(R.id.iv_image);
                viewHolder.tv_groupName.setText(getGroup(groupPosition).getTitle());
                viewHolder.imageview.setImageDrawable(context.getResources().getDrawable(getGroup(groupPosition).getImageSrc()));
                v.setTag(viewHolder);
            }

        } else {
            viewHolder = (ViewHolder) v.getTag();
        }


//        if(isExpanded){
//            viewHolder.iv_image.setBackgroundColor(Color.GREEN);
//        }else{
//            viewHolder.iv_image.setBackgroundColor(Color.WHITE);
//        }


        return v;
    }

    @Override
    public String getChild(int groupPosition, int childPosition) {
        return childList.get(groupPosition).get(childPosition);
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childList.get(groupPosition).size();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            viewHolder = new ViewHolder();
            v = inflater.inflate(R.layout.list_item_card_detail_child, null);
            viewHolder.tv_childName = (TextView) v.findViewById(R.id.child_list_item_crime_date_text_view);
            v.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) v.getTag();
        }

        viewHolder.tv_childName.setText(getChild(groupPosition, childPosition));

        return v;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPostion, int childPosition) {
        return true;
    }


    class ViewHolder {
        //        public ImageView iv_image;
        public TextView tv_groupName;
        public ImageView imageview;
        public TextView tv_childName;
    }
}
