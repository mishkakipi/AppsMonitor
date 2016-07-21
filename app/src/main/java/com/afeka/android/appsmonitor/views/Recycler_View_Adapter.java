package com.afeka.android.appsmonitor.views;

import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TimePicker;
import android.widget.Toast;

import com.afeka.android.appsmonitor.R;
import com.afeka.android.appsmonitor.activities.AppsUsageViewer;
import com.afeka.android.appsmonitor.data.AppUsage;
import com.afeka.android.appsmonitor.fragments.SetLimitFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Recycler_View_Adapter extends RecyclerView.Adapter<View_Holder>{

    private final boolean hasChildrenRegistered;
    List<AppUsage> list = Collections.emptyList();
    Context context;
    boolean isParentMode = false;

    public Recycler_View_Adapter(List<AppUsage> list, Context context, boolean isParentMode) {
        this.list = list;
        this.context = context;
        this.isParentMode = isParentMode;
        this.hasChildrenRegistered = true;
    }

    public Recycler_View_Adapter(String regPassphrase, Context context, boolean isParentMode) {
        ArrayList<AppUsage> list = new ArrayList<AppUsage>();
        list.add(new AppUsage("","",regPassphrase,0));
        this.list = list;
        this.context = context;
        this.isParentMode = isParentMode;
        this.hasChildrenRegistered = false;
    }

    @Override
    public View_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        int holderLayout;
        if (!isParentMode)
            holderLayout = R.layout.row_layout_child;
        else if (hasChildrenRegistered)
            holderLayout = R.layout.row_layout_parent_with_children;
        else
            holderLayout = R.layout.row_layout_parent_no_children;
        View v = LayoutInflater.from(parent.getContext()).inflate(holderLayout, parent, false);
        View_Holder holder = new View_Holder(v, context);
        return holder;
    }

    @Override
    public void onBindViewHolder(final View_Holder holder, int position) {
        holder.appUsageTime.setText(list.get(position).appUsageTime);
        if (isOverLimit(list.get(position).appUsageTime, list.get(position).appUsageMaxTime))
            holder.appUsageTime.setTextColor(Color.RED);
        else
            holder.appUsageTime.setTextColor(Color.GRAY);
        holder.appUsageMaxTime.setText(list.get(position).appUsageMaxTime);
        holder.appUsageMaxTime.setTextColor(Color.GRAY);
        holder.imageView.setImageResource(list.get(position).imageId);
        holder.appUsageMaxTime.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (isParentMode && hasChildrenRegistered) {
                    SetLimitFragment SL = new SetLimitFragment();
                    Bundle bundle = new Bundle();
                    int hour = Integer.parseInt((String) holder.appUsageMaxTime.getText().subSequence(0, 2));
                    int minute = Integer.parseInt((String) holder.appUsageMaxTime.getText().subSequence(3, 5));
                    bundle.putInt("hour", hour);
                    bundle.putInt("minute", minute);
                    SL.setArguments(bundle);
                    SL.show(((AppsUsageViewer) context).getFragmentManager(), "Set Limit");
                }

                return true;
            }
        });
        //animate(holder);
    }

    private boolean isOverLimit(String appUsageTime, String appUsageMaxTime) {
        if (appUsageMaxTime == null || appUsageMaxTime.isEmpty() ||appUsageTime == null || appUsageTime.isEmpty())
            return false;
        int nAppUsageTime = Integer.parseInt(appUsageTime.substring(0,2))*60+Integer.parseInt(appUsageTime.substring(3,4));
        int nAppUsageMaxTime = Integer.parseInt(appUsageMaxTime.substring(0,2))*60+Integer.parseInt(appUsageMaxTime.substring(3,4));
        return nAppUsageTime>=nAppUsageMaxTime;
    }

    private TimePickerDialog.OnTimeSetListener timePickerListener =
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int selectedHour,
                                      int selectedMinute) {
                    Toast.makeText(context, selectedHour+":"+selectedMinute, Toast.LENGTH_LONG).show();
                }
            };
    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    // Insert a new item to the RecyclerView
    public void insert(int position, AppUsage data) {
        list.add(position, data);
        notifyItemInserted(position);
    }
    // Remove a RecyclerView item containing the data object
    public void remove(AppUsage data) {
        int position = list.indexOf(data);
        list.remove(position);
        notifyItemRemoved(position);
    }

    public void animate(RecyclerView.ViewHolder viewHolder) {
        final Animation animAnticipateOvershoot = AnimationUtils.loadAnimation(context, R.anim.anticipate_overshoot_interpolator);
        viewHolder.itemView.setAnimation(animAnticipateOvershoot);
    }


}
