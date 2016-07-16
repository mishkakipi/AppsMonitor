package com.afeka.android.appsmonitor.manager;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.afeka.android.appsmonitor.activities.AppsUsageViewer;
import com.afeka.android.appsmonitor.activities.Registration;

/**
 * Created by Michael on 16/07/2016.
 */
public class BootLoader extends Application {
    public static final String PREFS_NAME = "appsmonitor.properties";
    @Override
    public void onCreate() {
        super.onCreate();

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        String mode = "";
        if (settings.contains("mode"))
            mode = settings.getString("mode","");
        else {
            settings.edit().putString("mode", "registration");
            settings.edit().commit();
        }
        switch (mode) {
            case "child":
                // make it work as a service
                Toast.makeText(getBaseContext(), "Child", Toast.LENGTH_LONG).show();
                break;
            case "parent":
                Intent parentIntent = new Intent(getApplicationContext(), AppsUsageViewer.class);
                parentIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(parentIntent);
                Toast.makeText(getBaseContext(), "Parent", Toast.LENGTH_LONG).show();
                break;
            case "registration":
                Intent registrationIntent = new Intent(getApplicationContext(), Registration.class);
                registrationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(registrationIntent);

                Toast.makeText(getBaseContext(), "Registration", Toast.LENGTH_LONG).show();
                break;
            default:
                Intent defaultIntent = new Intent(getApplicationContext(), Registration.class);
                defaultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(defaultIntent);

                Toast.makeText(getBaseContext(), "Default", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
