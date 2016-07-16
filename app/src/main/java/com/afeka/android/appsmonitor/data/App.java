package com.afeka.android.appsmonitor.data;

/**
 * Created by Michael on 16/07/2016.
 */
public class App {
    private String appName;
    private String appUseTimeLimit;

    public String getAppName() {
        return appName;
    }

    public String getAppUseTimeLimit() {
        return appUseTimeLimit;
    }

    public App() {

    }

    private App(AppBuilder builder) {
        this.appName = builder.appName;
        this.appUseTimeLimit = builder.appUseTimeLimit;
    }

    public void update(String appName, String appUseTime, String appUseTimeLimit) {
        this.appName = appName;
        this.appUseTimeLimit = appUseTimeLimit;
    }

    public static class AppBuilder {
        private String appName;
        private String appUseTimeLimit;

        public AppBuilder appName(String appName) {
            this.appName = appName;
            return this;
        }

        public AppBuilder appUseTimeLimit(String appUseTimeLimit) {
            this.appUseTimeLimit = appUseTimeLimit;
            return this;
        }

        public App build() {
            App newApp = new App(this);
            return newApp;
        }

    }
}
