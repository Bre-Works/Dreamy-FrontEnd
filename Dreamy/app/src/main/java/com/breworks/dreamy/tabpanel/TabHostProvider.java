package com.breworks.dreamy.tabpanel;

import android.app.Activity;

/**
 * Created by aidifauzan on 04/10/2014.
 */

public abstract class TabHostProvider {
	public Activity context;

	public TabHostProvider(Activity context) {
		this.context = context;
	}

	public abstract TabView getTabHost(String category);
}