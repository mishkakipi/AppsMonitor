package com.afeka.android.appsmonitor.views;

/**
 * Created by Michael on 18/07/2016.
 */
public class ChildrenNavItem {
    private String title;
    private int icon;

    public ChildrenNavItem(String title, int icon){
        this.title = title;
        this.icon = icon;
    }

    public String getTitle(){
        return this.title;
    }

    public int getIcon(){
        return this.icon;
    }
}
