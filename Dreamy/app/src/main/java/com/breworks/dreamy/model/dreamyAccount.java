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




    }

