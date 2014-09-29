package com.breworks.dreamy;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by arsianindita on 28-Sep-14.
 */
public class DreamyForm extends Activity implements View.OnClickListener {

    LinearLayout container;
    ImageButton addMilestone;
    EditText milestoneInput;
    ImageButton removeMilestone;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.dreamy_form);
        addMilestone = (ImageButton) findViewById(R.id.addMilestone);
        milestoneInput = (EditText) findViewById(R.id.milestoneInput);
        container = (LinearLayout) findViewById(R.id.container);

        addMilestone.setOnClickListener(this);
    }

    public void onClick(View v){

        LayoutInflater inflater = (LayoutInflater)getBaseContext().getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);

        final View addView = inflater.inflate(R.layout.dreamy_form_row, null);

        removeMilestone = (ImageButton)addView.findViewById(R.id.delMilestone);

        EditText milestoneOut = (EditText)addView.findViewById(R.id.milestoneOut);

        milestoneOut.setText(milestoneInput.getText().toString());

        removeMilestone.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v1) {
                ((LinearLayout) addView.getParent()).removeView(addView);
            }
        });

        container.addView(addView);
        milestoneInput.setText("");
    }
    
}
