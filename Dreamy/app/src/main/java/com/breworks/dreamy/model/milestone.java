package com.breworks.dreamy.model;

import com.orm.SugarRecord;

import java.util.List;

/**
 * Created by Ryan on 05/10/2014.
 */
public class Milestone extends SugarRecord<Milestone> {

    String name;
    int status;
    List<Long> todo_id;

    // constructors
    public Milestone() {
    }

    public Milestone(String name, int status) {
        this.name = name;
        this.status = status;
    }

    public Milestone(String name, int status,List<Todo> todos) {
        this.name = name;
        this.status = status;
        for(Todo todo : todos) {
            this.todo_id.add(todo.getId());
        }
    }

}

