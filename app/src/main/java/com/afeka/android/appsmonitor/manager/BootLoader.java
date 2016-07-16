package com.afeka.android.appsmonitor.manager;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

/**
 * Created by Michael on 16/07/2016.
 */
public class BootLoader extends Application {
    public static final String PREFS_NAME = "appsmonitor.properties";
    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        String mode = settings.getString("mode","");
        switch (mode) {
            case "child":
                Toast.makeText(getBaseContext(), "Child mode", Toast.LENGTH_LONG).show();
                break;
            case "parent":
                Toast.makeText(getBaseContext(), "Parent mode", Toast.LENGTH_LONG).show();
                break;
            default:
                Toast.makeText(getBaseContext(), "Registration mode", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
