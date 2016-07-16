package com.afeka.android.appsmonitor.data;

/**
 * Created by Michael on 16/07/2016.
 */
public class AppUsage {
    private String id;
    private String childId;
    private String appId;
    private String appUsageTime;

    public AppUsage() {

    }

    private AppUsage(AppUsageBuilder builder) {
        this.id = builder.id;
        this.appUsageTime = builder.appUsageTime;
        this.appId = builder.appId;
        this.childId = builder.childId;
    }

    public String getId() {
        return id;
    }

    public String getChildId() {
        return childId;
    }

    public String getAppId() {
        return appId;
    }

    public String getAppUsageTime() {
        return appUsageTime;
    }

    public void update(String childId, String appId, String appUsageTime) {
        this.appId = appId;
        this.childId = childId;
        this.appUsageTime = appUsageTime;
    }
    public static AppUsageBuilder getBuilder() {
        return new AppUsageBuilder();
    }

    public static class AppUsageBuilder {

        private String appUsageTime;
        private String appId;
        private String childId;
        private String id;

        public AppUsageBuilder id(String id) {
            this.id = id;
            return this;
        }
        public AppUsageBuilder appUsageTime(String appUsageTime) {
            this.appUsageTime = appUsageTime;
            return this;
        }
        public AppUsageBuilder appId(String appId) {
            this.appId = appId;
            return this;
        }
        public AppUsageBuilder childId(String childId) {
            this.childId = childId;
            return this;
        }

        public AppUsage build() {
            AppUsage newAppUsage = new AppUsage(this);
            return newAppUsage;
        }
    }
}
