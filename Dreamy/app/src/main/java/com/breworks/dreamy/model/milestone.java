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
    }


    public String getName(){
        return this.name;
    }

    public int getStatus(){
        return this.status;
    }
}

