package com.lishman.springdata.jpa.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "CONT")
public class Continent {

    @Id
    @SequenceGenerator(name = "CONT_ID_GENERATOR", sequenceName = "CONT_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONT_ID_GENERATOR")
    @Column(name = "CONT_ID")
    private Integer id;

    @Column(name = "CONT_NAME")
    private String name;

    @ManyToMany(mappedBy = "continents")
    private Set<Ocean> oceans;

    @OneToMany(mappedBy = "continent", cascade={CascadeType.ALL})
    private Set<Country> countries;
    
    @Transient
    private int viewCount = 0;

    public Continent() {
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
    public Set<Ocean> getOceans() {
        return this.oceans;
    }
    public void setOceans(Set<Ocean> oceans) {
        this.oceans = oceans;
    }
    public Set<Country> getCountries() {
        return this.countries;
    }
    public void setCountries(Set<Country> countries) {
        this.countries = countries;
    }
    public int getViewCount() {
        return viewCount;
    }
    public void incrementViewCount() {
        this.viewCount++;
    }
    
    public String toString() {
        return this.getName();
    }
}