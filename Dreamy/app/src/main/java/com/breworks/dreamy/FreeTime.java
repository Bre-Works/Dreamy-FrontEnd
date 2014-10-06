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
public class FreeTime extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TabHostProvider tabProvider = new MyTabHostProvider(FreeTime.this);
        TabView tabView = tabProvider.getTabHost("FreeTime");
        tabView.setCurrentView(R.layout.freetime);
        setContentView(tabView.render(3));
    }

}
