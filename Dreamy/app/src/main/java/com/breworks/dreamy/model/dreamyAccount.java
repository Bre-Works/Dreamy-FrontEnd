package com.breworks.dreamy.model;

import com.orm.SugarRecord;

import java.util.List;

/**
 * Created by Luck Eater on 10/4/2014.
 */

public class dreamyAccount extends SugarRecord<dreamyAccount> {

        String email;
        String username;
        String password;

        List<Integer> dream_id;


        // constructors
        public dreamyAccount() {
        }

        public dreamyAccount(String email, String username ,String password) {
            this.email = email;
            this.password = password;
            this.username = username;
        }

        public static void createAccount(String email, String username, String password){
            dreamyAccount account = new dreamyAccount(email, username, password);
            account.save();

        public dreamyAccount(String email, String username ,String password, List<Integer> dream_id) {
            this.email = email;
            this.password = password;
            this.username = username;
            this.dream_id = dream_id;

        }

        public List<dreamyAccount> allUser(){
            
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

