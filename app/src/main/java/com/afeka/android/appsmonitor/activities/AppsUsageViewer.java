package com.afeka.android.appsmonitor.activities;

import android.app.ActionBar;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.afeka.android.appsmonitor.R;
import com.afeka.android.appsmonitor.data.App;
import com.afeka.android.appsmonitor.data.AppUsage;
import com.afeka.android.appsmonitor.data.Data;
import com.afeka.android.appsmonitor.fragments.SetLimitFragment;
import com.afeka.android.appsmonitor.manager.ParentManager;
import com.afeka.android.appsmonitor.views.ChildrenNavItem;
import com.afeka.android.appsmonitor.views.ChildrenNavigationAdapter;
import com.afeka.android.appsmonitor.views.Recycler_View_Adapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AppsUsageViewer extends AppCompatActivity implements ActionBar.OnNavigationListener ,SetLimitFragment.OnTimePickedListener,  AdapterView.OnItemSelectedListener {
    private ParentManager _parentManager;
    RecyclerView recyclerView;
    private Recycler_View_Adapter adapter;
    ArrayList<AppUsage> data;
    List<AppUsage> filteredData;
    int childId = 0;
    private ActionBar actionBar;
    private ArrayList<ChildrenNavItem> navSpinner;
    private ChildrenNavigationAdapter childrenAdapter;
    Spinner spinner_nav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apps_usage_viewer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        _parentManager = new ParentManager();
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
                loadData();
            }
        });
        loadData();
    }

    private void loadData() {
        data = _parentManager.getData();
        refreshData(data);
    }

    private void refreshData(ArrayList<AppUsage> usageData) {
        navSpinner = new ArrayList<ChildrenNavItem>();
        for (AppUsage child : usageData)
            navSpinner.add(new ChildrenNavItem(child.childName, R.drawable.child));

        childrenAdapter = new ChildrenNavigationAdapter(getApplicationContext(), navSpinner);

        spinner_nav.setAdapter(childrenAdapter);
    }


    private List<AppUsage> filter_data(int position) {

        List<AppUsage> tempData = new ArrayList<>();

        tempData.add(data.get(position));

        return tempData;
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

    @Override
    public boolean onNavigationItemSelected(int itemPosition, long itemId) {
        return false;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        childId = position;
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        adapter = new Recycler_View_Adapter(filter_data(position), this);//getApplication());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(getBaseContext(), "Default", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onTimePicked(int hour, int minute) {
        _parentManager.setLimit(childId, hour, minute);
        loadData();
        Toast.makeText(getBaseContext(), hour+":"+minute, Toast.LENGTH_LONG).show();
    }
}
