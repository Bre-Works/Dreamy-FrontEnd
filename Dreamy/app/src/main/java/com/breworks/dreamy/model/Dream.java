package com.breworks.dreamy.model;

import com.orm.SugarRecord;

/**
 * Created by Ryan on 05/10/2014.
 */
public class Dream extends SugarRecord<Dream>{

    String name;
    int status;


    //build the relationship
    dreamyAccount account;

    List<Integer> miles_id;


    // constructors
    public Dream() {
    }

    public Dream(String name, int status) {
        this.name = name;
        this.status = status;
        this.account = null;
    }


    public Dream(String name, int status,dreamyAccount acc) {
        this.name = name;
        this.status = status;
        this.account = acc;

    public Dream(String name, int status,List<Integer> miles_id) {
        this.name = name;
        this.status = status;
        this.miles_id = miles_id;

    }
}
