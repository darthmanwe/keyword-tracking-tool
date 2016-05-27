package com.snovelli.seo.ktt.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Website {

    @Id
    @GeneratedValue
    private long id;


    Website(){}


}
