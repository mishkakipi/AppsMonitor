package com.afeka.android.appsmonitor.manager;

import com.afeka.android.appsmonitor.data.ParentData;
import com.afeka.android.appsmonitor.db.ParentRepository;

/**
 * Created by Michael on 16/07/2016.
 */
public class ParentManager {
    private ParentData data;
    private ParentRepository repository;

    public ParentManager() {
        // TODO: Initialize repository and fill data
        data = new ParentData();

    }


    public boolean refreshData() {
        boolean hasBeenRefreshed = false;
        // TODO: refreshdata from repository
        //return hasBeenRefreshed;
        return true;
    }

}
