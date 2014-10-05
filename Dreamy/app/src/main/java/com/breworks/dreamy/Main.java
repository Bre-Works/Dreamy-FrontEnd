package com.breworks.dreamy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.breworks.dreamy.model.Dream;
import com.breworks.dreamy.tabpanel.MyTabHostProvider;
import com.breworks.dreamy.tabpanel.TabHostProvider;
import com.breworks.dreamy.tabpanel.TabView;

import java.util.List;

/**
 * Created by aidifauzan on 24-Sep-14.
 */

public class Main extends Activity {

    LinearLayout linearView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TabHostProvider tabProvider = new MyTabHostProvider(Main.this);
        TabView tabView = tabProvider.getTabHost("Home");
        tabView.setCurrentView(R.layout.activity_main);
        setContentView(tabView.render(0));
        linearView = (LinearLayout) findViewById(R.id.linearView);

        DBHelper dbh = new DBHelper(this);
        // Inserting Contacts
        if(dbh.getAllDreams() == null) {
            Log.d("Insert: ", "Inserting ..");
            dbh.createDream(new Dream(1, "Conquer The World", 0));
            dbh.createDream(new Dream(2, "Make a Homunculus", 0));
        }

        // Reading all contacts

        Log.d("Reading: ", "Reading all contacts..");
        List<Dream> dreams = dbh.getAllDreams();


        for (Dream dr : dreams) {

            String log = "Id: " + dr.getId() + " ,Name: " + dr.getName() + " ,Status: " + dr.getStatus();
            // Writing Contacts to log
            TextView Dc = new TextView(this);
            Dc.setText(dr.getName());
            linearView.addView(Dc);

            Log.d("Name: ", log);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.to_do_list) {
            Intent intent = new Intent(Main.this, ToDoList.class);
            startActivity(intent);
            //setContentView(R.layout.todolist);
        }
        if (id == R.id.Dreamy_form) {
            Intent intent = new Intent(Main.this, DreamyForm.class);
            startActivity(intent);
        }
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void gotoDreamyForm(View v){
        Intent intent = new Intent(this, DreamyForm.class);
        startActivity(intent);
    }
}
