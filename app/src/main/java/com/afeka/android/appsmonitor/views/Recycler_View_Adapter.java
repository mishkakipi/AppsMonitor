package com.afeka.android.appsmonitor.views;

import android.animation.TimeAnimator;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
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
import com.afeka.android.appsmonitor.data.Data;
import com.afeka.android.appsmonitor.fragments.SetLimitFragment;
import com.afeka.android.appsmonitor.views.View_Holder;

import java.util.Collections;
import java.util.List;


public class Recycler_View_Adapter extends RecyclerView.Adapter<View_Holder>{

    List<AppUsage> list = Collections.emptyList();
    Context context;

    public Recycler_View_Adapter(List<AppUsage> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public View_Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        View_Holder holder = new View_Holder(v, context);
        return holder;
    }

    @Override
    public void onBindViewHolder(final View_Holder holder, int position) {
        holder.appUsageTime.setText(list.get(position).appUsageTime);
        holder.appUsageMaxTime.setText(list.get(position).appUsageMaxTime);
        holder.imageView.setImageResource(list.get(position).imageId);
        holder.appUsageMaxTime.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                SetLimitFragment SL = new SetLimitFragment();
                Bundle bundle = new Bundle();
                int hour = Integer.parseInt((String)holder.appUsageMaxTime.getText().subSequence(0,2));
                int minute = Integer.parseInt((String) holder.appUsageMaxTime.getText().subSequence(3,5));
                bundle.putInt("hour", hour);
                bundle.putInt("minute", minute);
                SL.setArguments(bundle);
                SL.show(((AppsUsageViewer)context).getFragmentManager(),"Set Limit");

                return true;
            }
        });
        //animate(holder);
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
    // Remove a RecyclerView item containing the Data object
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
