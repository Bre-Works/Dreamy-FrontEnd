package com.breworks.dreamy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

/**
 * Created by aidifauzan on 29/09/2014.
 */
public class FreeTime extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        getActionBar().hide();
        setContentView(R.layout.freetime);
    }

    public void gotoTodo(View v){
        Intent intent = new Intent(this, TodoList.class);
        startActivity(intent);
    }

    public void gotoHome(View v){
        Intent intent = new Intent(this, Main.class);
        startActivity(intent);
    }

    public void gotoNote(View v){
        Intent intent = new Intent(this, Notes.class);
        startActivity(intent);
    }

    public void gotoFreeTime(View v){
        Intent intent = new Intent(this, FreeTime.class);
        startActivity(intent);
    }

}
