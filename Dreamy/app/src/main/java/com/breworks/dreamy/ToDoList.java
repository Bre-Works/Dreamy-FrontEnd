package com.breworks.dreamy;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.view.inputmethod.EditorInfo;

import com.breworks.dreamy.model.Todo;
import com.breworks.dreamy.tabpanel.MyTabHostProvider;
import com.breworks.dreamy.tabpanel.TabHostProvider;
import com.breworks.dreamy.tabpanel.TabView;

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
    OnEditorActionListener taskEnter;
    DBHelper db;
    Display display;
    Point screenSize;
    int screenWidth;
    int fieldWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TabHostProvider tabProvider = new MyTabHostProvider(ToDoList.this);
        TabView tabView = tabProvider.getTabHost("Todo");
        tabView.setCurrentView(R.layout.todolist);
        setContentView(tabView.render(1));

        btnClear = (Button) findViewById(R.id.btnClear);
        table = (TableLayout) findViewById(R.id.tableTaskList);
        testText = (TextView) findViewById(R.id.textTitle);
        textField1 = (EditText) findViewById(R.id.textField1);
        checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
        checkBoxes.add(checkBox1);

        // Scaling
        display = getWindowManager().getDefaultDisplay();
        screenSize = new Point();
        display.getSize(screenSize);
        screenWidth = screenSize.x;
        fieldWidth = (int) (screenWidth * 0.7);
        textField1.getLayoutParams().width = fieldWidth;

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

        taskEnter = new OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (v.getText().toString().trim().length() > 0 && actionId == EditorInfo.IME_NULL && event.getAction() == KeyEvent.ACTION_DOWN) {
                    createTaskRow();
                    return true;
                } else if (actionId == EditorInfo.IME_NULL && event.getAction() == KeyEvent.ACTION_DOWN) {
                    return true;
                }
                return false;
            }
        };

        btnClear.setOnClickListener(oclBtnClear);
        textField1.setOnEditorActionListener(taskEnter);
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
        et.setOnEditorActionListener(taskEnter);
        et.requestFocus();
        et.setLayoutParams(new TableRow.LayoutParams(2));
        et.getLayoutParams().width = fieldWidth;
    }

}