package com.breworks.dreamy;

/**
 * Created by Luck Eater on 10/2/2014.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

public class logIn extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);
    }

    public void goToMain(View vi){
        Intent intent = new Intent(this, Main.class);
        startActivity(intent);
    }

    public void goToSignUp(View vi){
        Intent intent = new Intent(this, signUp.class);
        startActivity(intent);
    }

}
