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
        List<Long> dream_id;

        // constructors
        public dreamyAccount() {
        }

        public dreamyAccount(String email, String username ,String password) {
            this.email = email;
            this.password = password;
            this.username = username;
        }

        public dreamyAccount(String email, String username ,String password, List<Dream> dreams) {
            this.email = email;
            this.password = password;
            this.username = username;
            for(Dream dr : dreams) {
                this.dream_id.add(dr.getId());
            }
        }


    }

