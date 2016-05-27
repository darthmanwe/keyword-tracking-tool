package com.snovelli.seo.ktt.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class KeywordTrackingProject {

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @OneToOne
    private Workspace workspace;


    @Column(name = "execution_frequency_days")

    @Min(1)
    @Max(365)
    private int executionFrequencyDays;


    KeywordTrackingProject() {}


}
