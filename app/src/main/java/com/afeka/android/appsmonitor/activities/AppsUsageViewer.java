package com.afeka.android.appsmonitor.activities;

import android.app.ActionBar;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.afeka.android.appsmonitor.R;
import com.afeka.android.appsmonitor.data.AppUsage;
import com.afeka.android.appsmonitor.fragments.RegCodeFragment;
import com.afeka.android.appsmonitor.fragments.SetLimitFragment;
import com.afeka.android.appsmonitor.manager.AppUsageManager;
import com.afeka.android.appsmonitor.views.ChildrenNavItem;
import com.afeka.android.appsmonitor.views.ChildrenNavigationAdapter;
import com.afeka.android.appsmonitor.views.Recycler_View_Adapter;

import java.util.ArrayList;
import java.util.List;

public class AppsUsageViewer extends AppCompatActivity implements ActionBar.OnNavigationListener ,SetLimitFragment.OnTimePickedListener,  AdapterView.OnItemSelectedListener {
    public static final String PREFS_NAME = "appsmonitor.properties";

    private AppUsageManager appUsageManager;
    RecyclerView recyclerView;
    private Recycler_View_Adapter adapter;
    ArrayList<AppUsage> data;
    List<AppUsage> filteredData;
    int childId = 0;
    private ActionBar actionBar;
    private ArrayList<ChildrenNavItem> navSpinner;
    private ChildrenNavigationAdapter childrenAdapter;
    Spinner spinner_nav;
    boolean isParentMode;
    String regPassphrase;
    String name;
    String email;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apps_usage_viewer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.invalidateOptionsMenu();
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_APPEND);
        isParentMode = settings.getString("mode", "child").equals("parent");
        name = settings.getString("name", "");
        email = settings.getString("email", "");
        regPassphrase = settings.getString("passphrase", "");
        appUsageManager = new AppUsageManager(isParentMode, name, email);

        spinner_nav = (Spinner) findViewById(R.id.spinner_nav);
        spinner_nav.setOnItemSelectedListener(this);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);

        }

        FloatingActionButton refresh = (FloatingActionButton) findViewById(R.id.btn_refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ProgressDialog progressDialog = new ProgressDialog(AppsUsageViewer.this,
                        R.style.AppTheme_Dark_Dialog);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Refreshing...");
                progressDialog.show();

                reloadData();

                new android.os.Handler().postDelayed(
                        new Runnable() {
                            public void run() {
                                progressDialog.dismiss();
                            }
                        }, 1000);
            }
        });

        FloatingActionButton showRegCode = (FloatingActionButton) findViewById(R.id.regCodeDisplay);
        showRegCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                Fragment prev = getFragmentManager().findFragmentByTag(regPassphrase);
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);

                // Create and show the dialog.
                DialogFragment newFragment = RegCodeFragment.newInstance(regPassphrase);
                newFragment.show(ft, "dialog");
            }
        });
        loadData();
    }


    private void loadData() {
        appUsageManager.loadData();
        data = appUsageManager.getData();
        if (data.isEmpty())
            showRegCode();
        else
            refreshData(data);
    }
    private void reloadData() {
        appUsageManager.reloadData();
        data = appUsageManager.getData();
        if (data.isEmpty())
            showRegCode();
        else
            refreshData(data);
    }
    private void showRegCode() {
        spinner_nav.setVisibility(View.INVISIBLE);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        adapter = new Recycler_View_Adapter(regPassphrase, this, isParentMode);//getApplication());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void refreshData(ArrayList<AppUsage> usageData) {
        spinner_nav.setVisibility(View.VISIBLE);
        navSpinner = new ArrayList<ChildrenNavItem>();
        for (AppUsage child : usageData)
            navSpinner.add(new ChildrenNavItem(child.childName, R.drawable.child));

        childrenAdapter = new ChildrenNavigationAdapter(getApplicationContext(), navSpinner);

        spinner_nav.setAdapter(childrenAdapter);
        spinner_nav.setSelection(childId);
    }


    private List<AppUsage> filter_data(int position) {

        List<AppUsage> tempData = new ArrayList<>();

        tempData.add(data.get(position));

        return tempData;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(int itemPosition, long itemId) {
        return false;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        childId = position;
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        adapter = new Recycler_View_Adapter(filter_data(position), this, isParentMode);//getApplication());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //Toast.makeText(getBaseContext(), "Default", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onTimePicked(int hour, int minute) {
        final ProgressDialog progressDialog = new ProgressDialog(AppsUsageViewer.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Saving New Limit...");
        progressDialog.show();

        appUsageManager.setLimit(childId, hour, minute);
        appUsageManager.reloadData();
        loadData();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        progressDialog.dismiss();
                    }
                }, 1000);
        //Toast.makeText(getBaseContext(), hour+":"+minute, Toast.LENGTH_LONG).show();
    }

}
