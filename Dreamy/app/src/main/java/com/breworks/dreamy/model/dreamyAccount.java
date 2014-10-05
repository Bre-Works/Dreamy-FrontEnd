package com.breworks.dreamy.model;

/**
 * Created by Luck Eater on 10/4/2014.
 */

public class dreamyAccount {
    //private variable
    String _accountName;
    String _email;
    String _password;

    //empty constructor
    public dreamyAccount(){

    }

    //constructor
    public dreamyAccount(String accountName, String email, String password){
        this._accountName = accountName;
        this._email = email;
        this._password = password;
    }

    //getting accountName
    public String getAccount(){
        return this._accountName;
    }

    //setting accountName
    public void setAccount(String accountName){
        this._accountName = accountName;
    }

    //getting email
    public String getEmail(){
        return this._email;
    }

    //setting email
    public void setEmail(String email){
        this._email = email;
    }

    //getting password
    public String getPassword(){
        return this._password;
    }

    //setting password
    public void setPassword(String password){
        this._password = password;
    }
}
