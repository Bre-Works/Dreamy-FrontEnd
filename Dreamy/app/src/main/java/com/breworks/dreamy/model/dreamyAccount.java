package com.breworks.dreamy.model;

/**
 * Created by Luck Eater on 10/4/2014.
 */

public class dreamyAccount {

        int id;
        String email;
        String userID;
        String password;
        String created_at;

        // constructors
        public dreamyAccount() {
        }

        public dreamyAccount(String email, String userID ,String password) {
            this.email = email;
            this.password = password;
            this.userID = userID;
        }

        public dreamyAccount(int id, String email, String userID ,String password) {
            this.id = id;
            this.email = email;
            this.password = password;
            this.userID = userID;
        }

        // setters
        public void setId(int id) {
            this.id = id;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setUserID(String userID){
            this.userID = userID;
        }

        public void setCreatedAt(String created_at){
        this.created_at = created_at;
    }

        // getters
        public long getId() {
            return this.id;
        }

        public String getEmail() {
            return this.email;
        }

        public String getPassword() {
        return this.password;
    }

        public String getUserID() { return this.userID; }
    }

