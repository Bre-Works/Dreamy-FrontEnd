package com.breworks.dreamy.model;

import com.orm.SugarRecord;

/**
 * Created by Ryan on 05/10/2014.
 */
public class Milestone extends SugarRecord<Milestone> {

    String name;
    int status;


    //build relationship
    Dream dream;

    List<Integer> todo_id;


    // constructors
    public Milestone() {
    }

    public Milestone(String name, int status) {
        this.name = name;
        this.status = status;
        this.dream = null;
    }

    public Milestone(String name, int status, Dream dr) {
        this.name = name;
        this.status = status;
        this.dream = dr;

    public Milestone(String name, int status,List<Integer> todo_id) {
        this.name = name;
        this.status = status;
        this.todo_id = todo_id;

    }


}

