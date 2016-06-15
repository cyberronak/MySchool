package com.example.school.model;

import android.graphics.drawable.Drawable;

public class NavDrawerItem {
    private boolean showNotify;
    private String title;
    private Drawable icon;


    public NavDrawerItem() {

    }

    public NavDrawerItem(boolean showNotify, String title, Drawable icon) {
        this.showNotify = showNotify;
        this.title = title;
        this.icon=icon;
    }

    public boolean isShowNotify() {
        return showNotify;
    }

    public void setShowNotify(boolean showNotify) {
        this.showNotify = showNotify;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

	public Drawable getIcon() {
		return icon;
	}

	public void setIcon(Drawable icon) {
		this.icon = icon;
	}
}
