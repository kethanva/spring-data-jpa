package com.lishman.springdata.jpa.repository;

import com.lishman.springdata.jpa.config.Config;
import com.lishman.springdata.jpa.config.TestConfig;
import com.lishman.springdata.jpa.domain.Continent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@ContextConfiguration(classes={Config.class, TestConfig.class})
@ActiveProfiles("test")
@Transactional
@Rollback
@RunWith(SpringJUnit4ClassRunner.class)
public class ContinentRepositoryTest {

    @Autowired private ContinentRepository continentRepo;

    @Autowired private JdbcTemplate jdbcTemplate; 
    @PersistenceContext private EntityManager em;

    //------------------------------------------------- find all
    
    @Test
    public void testFindAll() {
        List<Continent> continents = continentRepo.findAll();
        assertThat(continents.toString(), equalTo("[Africa, Asia, Europe, North America, South America, Oceania, Antarctica]"));
    }
    
    //------------------------------------------------- find all with sort

    @Test
    public void testFindAllWithIds() {
        Sort ascendingName = new Sort(Direction.ASC, "name");
        List<Continent> continents = continentRepo.findAll(ascendingName);
        
        assertThat(continents.toString(), equalTo("[Africa, Antarctica, Asia, Europe, North America, Oceania, South America]"));
    }
    
    //------------------------------------------------- insert
    
    @Test
    public void testInsert() {
        Continent another = new Continent();
        another.setName("another");

        Continent continentInserted = continentRepo.save(another);
        continentRepo.flush();
        
        assertThat(continentInserted.getName(), equalTo("another"));
        String name = jdbcTemplate.queryForObject("SELECT cont_name FROM cont WHERE cont_id = ?", 
                                                  String.class,
                                                  continentInserted.getId());
        assertThat(name, equalTo("another"));
    }
    
    //------------------------------------------------- update
    
    @Test
    public void testUpdate() {
        
        Continent northAmerica = em.find(Continent.class, 4);
        northAmerica.setName("N. America");
        
        Continent updated = continentRepo.saveAndFlush(northAmerica);
        
        assertThat(updated.getName(), equalTo("N. America"));
        
        String name = jdbcTemplate.queryForObject("SELECT cont_name FROM cont WHERE cont_id = ?", 
                                                    String.class,
                                                    4);
        assertThat(name, equalTo("N. America"));    
    }

}
