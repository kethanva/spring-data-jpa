package com.lishman.springdata.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class State {

    @Id
    @SequenceGenerator(name = "STATE_ID_GENERATOR", sequenceName = "STATE_SEQ1")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STATE_ID_GENERATOR")
    @Column(name = "STATE_ID")
    private Integer id;

    @Column(name = "STATE_NAME")
    private String name;

    @Column(name = "STATE_ABBR")
    private String abbreviation;
    
    @Column(name = "CAPITAL")
    private String capital;
    
    @Column(name = "CAPITAL_SINCE")
    private int capitalSince;

    public State() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public int getCapitalSince() {
        return capitalSince;
    }

    public void setCapitalSince(int capitalSince) {
        this.capitalSince = capitalSince;
    }
    
    public String toString() {
        return this.getName();
    }

}