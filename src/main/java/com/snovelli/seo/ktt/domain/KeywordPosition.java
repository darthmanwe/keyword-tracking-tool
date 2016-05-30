package com.snovelli.seo.ktt.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data @NoArgsConstructor
public class KeywordPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private double clicks;
    private double ctr;
    private double impressions;
    private double position;

    private String date;
    private String url;
    private String country;
    private String device;


    public KeywordPosition(double clicks, double ctr, double impressions, double position, String date, String url, String country, String device) {
        this.clicks = clicks;
        this.ctr = ctr;
        this.impressions = impressions;
        this.position = position;
        this.date = date;
        this.url = url;
        this.country = country;
        this.device = device;
    }
}
