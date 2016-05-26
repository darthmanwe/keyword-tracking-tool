package com.snovelli.seo.ktt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class KeywordPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long clicks;
    private double ctr;
    private long impressions;
    private double position;

    private String date;
    private String url;
    private String country;
    private String device;


    public long getId() {
        return id;
    }
}
