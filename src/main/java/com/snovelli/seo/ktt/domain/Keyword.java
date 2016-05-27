package com.snovelli.seo.ktt.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Keyword {
    @Id
    @GeneratedValue
    private long id;


    @NotNull
    @Pattern(regexp = "^[\\w\\s]*$")
    @Size(min = 3, max = 50)
    private String keyword;

    @OneToOne
    private KeywordTrackingProject project;


    Keyword() { }
}
