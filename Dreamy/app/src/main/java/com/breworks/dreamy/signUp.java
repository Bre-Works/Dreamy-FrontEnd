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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.breworks.dreamy.model.dreamyAccount;

public class signUp extends Activity{
    Button createAccount;
    EditText usernameInput, emailInput, passwordInput, passwordConfInput;
    String username, email, password, passwordConf;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        usernameInput = (EditText) findViewById(R.id.username);
        emailInput = (EditText) findViewById(R.id.email);
        passwordInput = (EditText) findViewById(R.id.password);
        passwordConfInput = (EditText) findViewById(R.id.passwordConf);
        //createAccount = (Button) findViewById(R.id.createAccount);
    }


    public void createAccount(View v){
        username = usernameInput.toString();
        email = emailInput.toString();
        password = passwordInput.toString();
        passwordConf = passwordConfInput.toString();

        if(!password.equals(passwordConf)){
            Toast.makeText(getApplicationContext(), "Password and password confirmation did not match!", Toast.LENGTH_SHORT).show();
            return;
        }

        dreamyAccount account = new dreamyAccount(email, username, password);
        DBHelper.createAccounts(account);
    }
}
