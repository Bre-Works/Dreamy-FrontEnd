package com.breworks.dreamy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * Created by Maha on 9/28/14.
 */

public class ToDoList extends Activity {

    Button btnAdd;
    TextView testText;
    TableLayout table;
    TableRow row;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todolist);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        table = (TableLayout) findViewById(R.id.tableTaskList);
        testText = (TextView) findViewById(R.id.textTitle);


        View.OnClickListener oclBtnAdd = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createTaskRow();
            }
        };

        btnAdd.setOnClickListener(oclBtnAdd);
    }

    protected void createTaskRow() {
        TableRow row = new TableRow(this);
        EditText textField = new EditText(this);
        CheckBox checkbox = new CheckBox(this);
        checkbox.setLayoutParams(new TableRow.LayoutParams(1));
        textField.setLayoutParams(new TableRow.LayoutParams(2));
        row.addView(textField);
        row.addView(checkbox);
        table.addView(row);
    }

    public void gotoTodo(View v){
        Intent intent = new Intent(this, ToDoList.class);
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