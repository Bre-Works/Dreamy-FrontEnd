package com.breworks.dreamy;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Maha on 9/28/14.
 */

public class ToDoList extends Activity {

    Button btnAdd;
    EditText textField;
    CheckBox checkBox;
    TextView testText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todolist);

        btnAdd = (Button) findViewById(R.id.btnAdd);

        View.OnClickListener oclBtnAdd = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        };

        btnAdd.setOnClickListener(oclBtnAdd);
    }
}