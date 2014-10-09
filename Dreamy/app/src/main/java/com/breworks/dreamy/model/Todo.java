package com.breworks.dreamy.model;

import com.orm.SugarRecord;

/**
 * Created by Ryan on 05/10/2014.
 */
public class Todo extends SugarRecord<Todo>{

        String name;
        int status;

        //build relationship
        Milestone miles;

        // constructors
        public Todo() {
        }

        public Todo(String name, int status) {
            this.name = name;
            this.status = status;
            this.miles = null;
        }

        public Todo(String name, int status, Milestone mil) {
            this.name = name;
            this.status = status;
            this.miles = mil;
        }
}
