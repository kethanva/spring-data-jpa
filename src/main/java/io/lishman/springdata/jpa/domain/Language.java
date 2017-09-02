package io.lishman.springdata.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="lang")
public class Language {

    @Id
    @SequenceGenerator(name = "LANGUAGE_ID_GENERATOR", sequenceName = "LANG_SEQ1")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LANGUAGE_ID_GENERATOR")
    @Column(name = "LANG_ID")
    private Integer id;

    @Column(name = "LANG_NAME")
    private String name;

    @Column(name = "PERCENTAGE")
    private double percentageSpoken;

    public Language() {
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
    public double getPercentageSpoken() {
        return percentageSpoken;
    }

    public void setPercentageSpoken(double percentageSpoken) {
        this.percentageSpoken = percentageSpoken;
    }
    
    public String toString() {
        return this.getName();
    }
}