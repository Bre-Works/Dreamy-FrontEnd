package com.breworks.dreamy.model;

import com.orm.SugarRecord;

/**
 * Created by Luck Eater on 10/4/2014.
 */

public class dreamyAccount extends SugarRecord<dreamyAccount> {

        String email;
        String username;
        String password;

        // constructors
        public dreamyAccount() {
        }

        public dreamyAccount(String email, String username ,String password) {
            this.email = email;
            this.password = password;
            this.username = username;
        }

        public void createAccount(String email, String username, String password){
            dreamyAccount account = new dreamyAccount(email, username, password);
            account.save();
        }

        public String getEmail(){
            return this.email;
        }

        public String getPassword(){
            return this.password;
        }

        public String getUsername(){
            return this.username;
        }
}

