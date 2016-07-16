package com.afeka.android.appsmonitor.data;

import com.afeka.android.appsmonitor.db.ParentRepository;
import com.afeka.android.appsmonitor.manager.ParentManager;

import java.util.List;

/**
 * Created by Michael on 16/07/2016.
 */
public class ParentData {
    private List<Child> children;
    private List<App> childrenApps;
    private List<AppUsage> childrenAppsUsage;
}
