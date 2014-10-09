package com.breworks.dreamy.model;

import com.orm.SugarRecord;

/**
 * Created by Ryan on 05/10/2014.
 */
public class Todo extends SugarRecord<Todo>{

        String name;
        int status;

        // constructors
        public Todo() {
        }

        public Todo(String name, int status) {
            this.name = name;
            this.status = status;
        }
}
