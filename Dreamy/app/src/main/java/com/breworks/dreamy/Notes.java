package com.breworks.dreamy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.breworks.dreamy.tabpanel.MyTabHostProvider;
import com.breworks.dreamy.tabpanel.TabHostProvider;
import com.breworks.dreamy.tabpanel.TabView;

/**
 * Created by aidifauzan on 29/09/2014.
 */
public class Notes extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TabHostProvider tabProvider = new MyTabHostProvider(Notes.this);
        TabView tabView = tabProvider.getTabHost("Notes");
        tabView.setCurrentView(R.layout.notes);
        setContentView(tabView.render(2));
    }

}
