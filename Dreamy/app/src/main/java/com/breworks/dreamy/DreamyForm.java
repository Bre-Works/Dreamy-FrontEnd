package com.breworks.dreamy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.breworks.dreamy.model.Dream;
import com.breworks.dreamy.model.Milestone;

import java.util.List;

/**
 * Created by arsianindita on 28-Sep-14.
 */

public class DreamyForm extends Activity{

    LinearLayout container;
    ImageButton addMilestone;
    EditText milestoneInput;
    ImageButton removeMilestone;
    EditText dreamInput;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.dreamy_form);
        milestoneInput = (EditText) findViewById(R.id.milestoneInput);
        container = (LinearLayout) findViewById(R.id.container);
        dreamInput = (EditText) findViewById(R.id.dreamInput);

        Intent intent;
        if(getIntent() != null) {
            intent = getIntent();
            long dream = intent.getLongExtra("key",0);
            Dream dr = dbh.getDreamwithID(dream);
            dreamInput.setText(dr.getName());
            if(dbh.getAllMilestonesByDreams(dream) != null) {
                List<Milestone> miles = dbh.getAllMilestonesByDreams(dream);

                for (Milestone mil : miles) {
                    LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService
                            (Context.LAYOUT_INFLATER_SERVICE);

                    final View addView = inflater.inflate(R.layout.dreamy_form_row, null);

                    removeMilestone = (ImageButton) addView.findViewById(R.id.delMilestone);

                    EditText milestoneOut = (EditText) addView.findViewById(R.id.milestoneOut);

                    milestoneOut.setText(mil.getName());

                    removeMilestone.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v1) {
                            ((LinearLayout) addView.getParent()).removeView(addView);
                        }
                    });

                    container.addView(addView);
                }
            }
        }

        milestoneInput.setOnKeyListener(new View.OnKeyListener() {

                public boolean onKey(View v, int keyCode, KeyEvent event) {

                    if (event.getAction() == KeyEvent.ACTION_DOWN) {
                        if (keyCode == KeyEvent.KEYCODE_ENTER) {
                            LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService
                                    (Context.LAYOUT_INFLATER_SERVICE);

                            final View addView = inflater.inflate(R.layout.dreamy_form_row, null);

                            removeMilestone = (ImageButton) addView.findViewById(R.id.delMilestone);

                            EditText milestoneOut = (EditText) addView.findViewById(R.id.milestoneOut);

                            milestoneOut.setText(milestoneInput.getText().toString());

                            removeMilestone.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v1) {
                                    ((LinearLayout) addView.getParent()).removeView(addView);
                                }
                            });

                            container.addView(addView);
                            milestoneInput.setText("");

                            return true;
                        }
                    }
                    return false;
                }
            });
    }

    public void saveBackToHome(View v){
        finish();
    }



}

