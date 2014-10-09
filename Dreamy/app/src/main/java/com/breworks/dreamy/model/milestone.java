package com.breworks.dreamy.model;

import com.orm.SugarRecord;

import java.util.List;

/**
 * Created by Ryan on 05/10/2014.
 */
public class Milestone extends SugarRecord<Milestone> {

    String name;
    int status;
    List<Integer> todo_id;

    // constructors
    public Milestone() {
    }

    public Milestone(String name, int status) {
        this.name = name;
        this.status = status;
    }

    public Milestone(String name, int status,List<Integer> todo_id) {
        this.name = name;
        this.status = status;
        this.todo_id = todo_id;
    }

}

