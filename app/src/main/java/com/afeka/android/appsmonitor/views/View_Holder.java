package com.afeka.android.appsmonitor.views;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.afeka.android.appsmonitor.R;

/**
 * Created by Michael on 17/07/2016.
 */
public class View_Holder extends RecyclerView.ViewHolder {

    CardView cv;
    TextView appMaxTime;
    TextView appUsageTime;
    ImageView appIcon;

    View_Holder(View itemView) {
        super(itemView);
        cv = (CardView) itemView.findViewById(R.id.cardView);
        appMaxTime = (TextView) itemView.findViewById(R.id.title);
        appUsageTime = (TextView) itemView.findViewById(R.id.description);
        appIcon = (ImageView) itemView.findViewById(R.id.imageView);
    }
}