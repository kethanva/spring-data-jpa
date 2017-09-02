package io.lishman.springdata.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class City {

    @Id
    @SequenceGenerator(name = "CITY_ID_GENERATOR", sequenceName = "CITY_SEQ1")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CITY_ID_GENERATOR")
    @Column(name = "CITY_ID")
    private Integer id;

    @Column(name = "CITY_NAME")
    private String name;

    @OneToOne(mappedBy = "city")
    private Mayor mayor;

    public City() {
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
    public Mayor getMayor() {
        return this.mayor;
    }
    public void setMayor(Mayor mayor) {
        this.mayor = mayor;
    }
    
    public String toString() {
        return this.getName();
    }
}