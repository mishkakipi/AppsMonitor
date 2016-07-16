package com.afeka.android.appsmonitor.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.afeka.android.appsmonitor.R;

public class MainActivity extends AppCompatActivity {
    public static final String PREFS_NAME = "appsmonitor.properties";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        /*
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_APPEND);
        SharedPreferences.Editor settingsEditor = settings.edit();
        String mode = "";
        if (settings.contains("mode"))
            mode = settings.getString("mode", "");
        else {
            settingsEditor.putString("mode", "registration");
            settingsEditor.commit();
        }
        if (mode.toString().equals("child")) {
            Toast.makeText(getBaseContext(), "Child", Toast.LENGTH_LONG).show();
        } else if(mode.toString().equals("parent")) {
            Intent parentIntent = new Intent(getApplicationContext(), AppsUsageViewer.class);
            parentIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

            startActivity(parentIntent);
            Toast.makeText(getBaseContext(), "Parent", Toast.LENGTH_LONG).show();

        } else if(mode.toString().equals("registration")) {
            Intent registrationIntent = new Intent(getApplicationContext(), Registration.class);
            registrationIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

            startActivity(registrationIntent);

            Toast.makeText(getBaseContext(), "Registration", Toast.LENGTH_LONG).show();

        } else {
            Intent defaultIntent = new Intent(getApplicationContext(), Registration.class);
            defaultIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

            startActivity(defaultIntent);

            Toast.makeText(getBaseContext(), "Default", Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
