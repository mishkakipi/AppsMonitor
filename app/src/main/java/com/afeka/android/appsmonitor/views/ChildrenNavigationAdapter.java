package com.afeka.android.appsmonitor.views;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.afeka.android.appsmonitor.R;

import java.util.ArrayList;

/**
 * Created by Michael on 18/07/2016.
 */
public class ChildrenNavigationAdapter  extends BaseAdapter {
    private ImageView imgIcon;
    private TextView txtTitle;
    private ArrayList<ChildrenNavItem> childrenNavItems;
    private Context context;

    public ChildrenNavigationAdapter(Context context,
                                  ArrayList<ChildrenNavItem> childrenNavItem) {
        this.childrenNavItems = childrenNavItem;
        this.context = context;
    }

    @Override
    public int getCount() {
        return childrenNavItems.size();
    }

    @Override
    public Object getItem(int index) {
        return childrenNavItems.get(index);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.content_children_nav_item_layout, null);
        }

        imgIcon = (ImageView) convertView.findViewById(R.id.imgIcon);
        txtTitle = (TextView) convertView.findViewById(R.id.txtTitle);

        imgIcon.setImageResource(childrenNavItems.get(position).getIcon());
        imgIcon.setVisibility(View.GONE);
        txtTitle.setText(childrenNavItems.get(position).getTitle());
        return convertView;
    }


    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.content_children_nav_item_layout, null);
        }

        imgIcon = (ImageView) convertView.findViewById(R.id.imgIcon);
        txtTitle = (TextView) convertView.findViewById(R.id.txtTitle);

        imgIcon.setImageResource(childrenNavItems.get(position).getIcon());
        txtTitle.setText(childrenNavItems.get(position).getTitle());
        return convertView;
    }
}
