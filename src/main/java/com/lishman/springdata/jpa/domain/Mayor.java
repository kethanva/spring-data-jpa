package com.lishman.springdata.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Mayor {

    @Id
    @SequenceGenerator(name = "MAYOR_ID_GENERATOR", sequenceName = "MAYOR_SEQ1")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MAYOR_ID_GENERATOR")
    @Column(name = "MAYOR_ID")
    private Integer id;

    @Column(name = "MAYOR_NAME")
    private String name;

    @OneToOne
    @JoinColumn(name = "CITY_ID")
    private City city;

    public Mayor() {
    }

    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public City getCity() {
        return this.city;
    }
    public void setCity(City city) {
        this.city = city;
    }
}