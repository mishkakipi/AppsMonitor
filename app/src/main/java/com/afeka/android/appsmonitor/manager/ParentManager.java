package com.afeka.android.appsmonitor.manager;

import com.afeka.android.appsmonitor.R;
import com.afeka.android.appsmonitor.data.AppUsage;
import com.afeka.android.appsmonitor.data.ParentData;
import com.afeka.android.appsmonitor.db.ParentRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 16/07/2016.
 */
public class ParentManager {
    private ArrayList<AppUsage> data;
    private ParentRepository repository;

    public ParentManager() {
        // TODO: Initialize repository and fill data
        data = new ArrayList<AppUsage>();
        //data.add(new AppUsage("Michaelk19", "00:30", "00:50", R.drawable.facebook));
        //data.add(new AppUsage("Michaelk20", "00:20", "00:40", R.drawable.facebook));

    }


    public boolean refreshData() {
        boolean hasBeenRefreshed = false;
        // TODO: refreshdata from repository
        //return hasBeenRefreshed;
        return true;
    }

    public ArrayList<AppUsage> getData() {
        return data;
    }

    public void setLimit(int childId, int hour, int minute) {
        //TODO: insert new limit to the child
        String newLimit;
        newLimit = hour<10?"0"+hour:""+hour;
        newLimit = newLimit + ":";
        newLimit = minute<10?newLimit+"0"+minute:newLimit+""+minute;
        data.get(childId).appUsageMaxTime = newLimit;
    }
}
