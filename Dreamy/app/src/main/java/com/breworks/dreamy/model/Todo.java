package com.breworks.dreamy.model;

/**
 * Created by Ryan on 05/10/2014.
 */
public class Todo {

        int id;
        String name;
        int status;
        String created_at;

        // constructors
        public Todo() {
        }

        public Todo(String name, int status) {
            this.name = name;
            this.status = status;
        }

        public Todo(int id, String name, int status) {
            this.id = id;
            this.name = name;
            this.status = status;
        }

        // setters
        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public void setCreatedAt(String created_at){
            this.created_at = created_at;
        }

        // getters
        public long getId() {
            return this.id;
        }

        public String getName() {
            return this.name;
        }

        public int getStatus() {
            return this.status;
        }
    }
