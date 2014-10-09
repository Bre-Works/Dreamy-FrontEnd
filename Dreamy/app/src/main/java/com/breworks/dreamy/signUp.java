package com.breworks.dreamy;

/**
 * Created by Luck Eater on 10/2/2014.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
        username = usernameInput.getText().toString();
        email = emailInput.getText().toString();
        password = passwordInput.getText().toString();
        passwordConf = passwordConfInput.getText().toString();

        if(!password.equals(passwordConf))
            Toast.makeText(getApplicationContext(), "Password and password confirmation did not match!", Toast.LENGTH_SHORT).show();
        else {
            dreamyAccount account = new dreamyAccount();
            account.createAccount(email, username, password);
            Toast.makeText(getApplicationContext(), "Your account is now ready. Please login.", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, logIn.class);
            startActivity(intent);
        }
    }
}
