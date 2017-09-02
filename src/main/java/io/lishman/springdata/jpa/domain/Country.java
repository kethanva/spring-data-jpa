package io.lishman.springdata.jpa.domain;

import io.lishman.springdata.jpa.domain.City;
import io.lishman.springdata.jpa.domain.Continent;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CTRY")
public class Country {

    @Id
    @SequenceGenerator(name = "CTRY_ID_GENERATOR", sequenceName = "CTRY_SEQ1")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CTRY_ID_GENERATOR")
    @Column(name = "CTRY_ID")
    private Integer id;

    @Column(name = "CTRY_AREA")
    private Integer area;

    @Column(name = "CTRY_NAME")
    private String name;

    @Column(name = "CTRY_POP")
    private Integer population;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CONT_ID")
    private Continent continent;
    
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "CTRY_ID")
    private List<City> cities;

    public Country() {
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
    public Integer getPopulation() {
        return this.population;
    }
    public void setPopulation(Integer population) {
        this.population = population;
    }
    public Continent getContinent() {
        return this.continent;
    }
    public void setContinent(Continent continent) {
        this.continent = continent;
    }
    public List<City> getCities() {
        return this.cities;
    }
    public void setCities(List<City> cities) {
        this.cities = cities;
    }
    
    public String toString() {
        return this.getName();
    }
}