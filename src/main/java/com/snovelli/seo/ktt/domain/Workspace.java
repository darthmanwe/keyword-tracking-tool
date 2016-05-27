package com.snovelli.seo.ktt.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Workspace {

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @OneToOne
    private Website rootWebsite;


    Workspace() { }
}
