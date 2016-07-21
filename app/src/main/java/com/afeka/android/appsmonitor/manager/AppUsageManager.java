package com.afeka.android.appsmonitor.manager;

import com.afeka.android.appsmonitor.R;
import com.afeka.android.appsmonitor.data.AppUsage;

import java.util.ArrayList;

/**
 * Created by Michael on 16/07/2016.
 */
public class AppUsageManager {
    private ArrayList<AppUsage> data;
    private boolean isParentMode;
    private String name;
    private String email;

    public AppUsageManager(boolean isParentMode, String name, String email) {
        // TODO: Initialize repository and fill data
        data = new ArrayList<AppUsage>();
        this.isParentMode = isParentMode;
        this.email = email;
        this.name = name;
        //data.add(new AppUsage("Michaelk19", "00:30", "00:50", R.drawable.facebook));
        //data.add(new AppUsage("Michaelk20", "00:20", "00:40", R.drawable.facebook));
    }

    public ArrayList<AppUsage> getData() {
        return data;
    }
    public void loadData() {
        if (isParentMode) {
            // TODO load children data from db and fill data list

            //data.add(new AppUsage("Michaelk21", "00:30", "00:50", R.drawable.facebook));
            //data.add(new AppUsage("Michaelk22", "00:20", "00:40", R.drawable.facebook));
        } else {
            // TODO load child data from db and fill data list based on email and name
            data.add(new AppUsage(name, "00:30", "00:50", R.drawable.facebook));
        }
    }
    public void setLimit(int childId, int hour, int minute) {

        if (!isParentMode)
            return;

        //TODO: insert new limit to the child
        String newLimit;
        newLimit = hour<10?"0"+hour:""+hour;
        newLimit = newLimit + ":";
        newLimit = minute<10?newLimit+"0"+minute:newLimit+""+minute;
        data.get(childId).appUsageMaxTime = newLimit;
    }

    public void reloadData() {
        if (!isParentMode)
            return;
        data.add(new AppUsage("Michaelk21", "00:30", "00:50", R.drawable.facebook));
        data.add(new AppUsage("Michaelk22", "00:20", "00:40", R.drawable.facebook));
    }
}
