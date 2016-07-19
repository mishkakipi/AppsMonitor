package com.afeka.android.appsmonitor.views;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.afeka.android.appsmonitor.R;
import com.afeka.android.appsmonitor.activities.AppsUsageViewer;
import com.afeka.android.appsmonitor.fragments.SetLimitFragment;


//The adapters View Holder
public class View_Holder extends RecyclerView.ViewHolder implements View.OnLongClickListener {

    CardView cv;
    TextView appUsageTime;
    TextView appUsageMaxTime;
    ImageView imageView;
    Context context;

    View_Holder(View itemView, Context ctx) {
        super(itemView);
        context = ctx;
        cv = (CardView) itemView.findViewById(R.id.cardView);
        appUsageMaxTime = (TextView) itemView.findViewById(R.id.limit);
        appUsageTime = (TextView) itemView.findViewById(R.id.currentUse);
        imageView = (ImageView) itemView.findViewById(R.id.imageView);
    }

    @Override
    public boolean onLongClick(View v) {
        SetLimitFragment SL = new SetLimitFragment();

        SL.show(((AppsUsageViewer)context).getFragmentManager(),"Set Limit");

        return true;
    }
}
