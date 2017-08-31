package com.lishman.springdata.jpa.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Ocean {

    @Id
    @SequenceGenerator(name = "OCEAN_ID_GENERATOR", sequenceName = "OCEAN_SEQ1")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OCEAN_ID_GENERATOR")
    @Column(name = "OCEAN_ID")
    private Integer id;

    @Column(name = "OCEAN_AREA")
    private Integer area;

    @Column(name = "OCEAN_NAME")
    private String name;

    @ManyToMany
    @JoinTable(name = "BORD", joinColumns = { @JoinColumn(name = "OCEAN_ID") }, 
                              inverseJoinColumns = { @JoinColumn(name = "CONT_ID") })
    private Set<Continent> continents;

    public Ocean() {
    }

    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getArea() {
        return this.area;
    }
    public void setArea(Integer area) {
        this.area = area;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Set<Continent> getContinents() {
        return this.continents;
    }
    public void setContinents(Set<Continent> continents) {
        this.continents = continents;
    }
    
    public String toString() {
        return this.getName();
    }
}