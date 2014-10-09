package com.breworks.dreamy.model;

import com.orm.SugarRecord;

import java.util.List;

/**
 * Created by Ryan on 05/10/2014.
 */
public class Dream extends SugarRecord<Dream>{

    String name;
    int status;
    List<Long> miles_id;

    // constructors
    public Dream() {
    }

    public Dream(String name, int status) {
        this.name = name;
        this.status = status;
    }

    public Dream(String name, int status,List<Milestone> miles) {
        this.name = name;
        this.status = status;
        for(Milestone mi : miles) {
            this.miles_id.add(mi.getId());
        }
    }

}
