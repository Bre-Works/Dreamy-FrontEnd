package com.breworks.dreamy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.view.inputmethod.EditorInfo;

import java.util.ArrayList;

/**
 * Created by Maha on 9/28/14.
 */

public class ToDoList extends Activity {

    ArrayList<CheckBox> checkBoxes = new ArrayList<CheckBox>();
    Button btnClear;
    TextView testText;
    TableLayout table;
    CheckBox checkBox1;
    EditText textField1;
    OnEditorActionListener newTextField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todolist);

        btnClear = (Button) findViewById(R.id.btnClear);
        table = (TableLayout) findViewById(R.id.tableTaskList);
        testText = (TextView) findViewById(R.id.textTitle);
        textField1 = (EditText) findViewById(R.id.textField1);
        checkBox1 = (CheckBox) findViewById(R.id.checkBox1);

        checkBoxes.add(checkBox1);


        View.OnClickListener oclBtnClear = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (CheckBox i : checkBoxes) {
                    if (i.isChecked()) {
                        View row = (View) i.getParent();
                        table.removeView(row);
                    }
                }
                if (table.getChildCount() == 0) {
                    createTaskRow();
                }
            }
        };

        newTextField = new OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_NULL && event.getAction() == KeyEvent.ACTION_DOWN) {
                    createTaskRow();
                    return true;
                }
                return false;
            }
        };

        btnClear.setOnClickListener(oclBtnClear);
        textField1.setOnEditorActionListener(newTextField);
    }

    protected void createTaskRow() {
        TableRow row = new TableRow(this);
        EditText textField = new EditText(this);
        CheckBox checkbox = new CheckBox(this);
        checkbox.setLayoutParams(new TableRow.LayoutParams(1));
        setEditTextAttributes(textField);
        checkBoxes.add(checkbox);
        row.addView(checkbox);
        row.addView(textField);
        table.addView(row);
    }

    protected void setEditTextAttributes(EditText et) {
        et.setOnEditorActionListener(newTextField);
        et.requestFocus();
        et.setMaxLines(1);
        et.setLayoutParams(new TableRow.LayoutParams(2));
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