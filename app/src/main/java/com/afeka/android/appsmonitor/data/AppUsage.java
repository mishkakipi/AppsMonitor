package com.afeka.android.appsmonitor.data;

import android.media.Image;
import android.widget.ImageView;

/**
 * Created by Michael on 16/07/2016.
 */
public class AppUsage {
    public String childName;
    public String appUsageTime;
    public String appUsageMaxTime;
    public int imageId;


    public AppUsage(String childName, String appUsageTime, String appUsageMaxTime, int imageId) {
        this.childName = childName;
        this.appUsageTime = appUsageTime;
        this.appUsageMaxTime = appUsageMaxTime;
        this.imageId = imageId;
    }
}
