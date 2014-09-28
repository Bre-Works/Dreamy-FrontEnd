package com.breworks.dreamy;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

/**
 * Created by arsianindita on 28-Sep-14.
 */
public class DreamyForm extends Activity implements View.OnClickListener {

    LinearLayout container;
    Button addMilestone;
    EditText milestoneInput;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.dreamy_form);
        addMilestone = (Button) findViewById(R.id.addMilestone);
        milestoneInput = (EditText) findViewById(R.id.milestoneInput);
        container = (LinearLayout) findViewById(R.id.container);

        addMilestone.setOnClickListener(this);
    }

    public void onClick(View v){






        
    }
    
}
