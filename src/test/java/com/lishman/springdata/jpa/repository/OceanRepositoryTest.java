package com.lishman.springdata.jpa.repository;

import com.lishman.springdata.jpa.config.Config;
import com.lishman.springdata.jpa.config.TestConfig;
import com.lishman.springdata.jpa.domain.Ocean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@ContextConfiguration(classes={Config.class, TestConfig.class})
@ActiveProfiles("test")
@Transactional
@Rollback
@RunWith(SpringJUnit4ClassRunner.class)
public class OceanRepositoryTest {

    @Autowired private OceanRepository oceanRepo;
    
    @PersistenceContext private EntityManager em;
    
    //------------------------------------------------- findByName

    @Test
    public void testFindByName() {
        Ocean ocean= oceanRepo.findByName("Artic");
        assertThat(ocean.getName(), equalTo("Artic"));
    }

    //------------------------------------------------- count
    
    @Test
    public void testCount() {
        assertThat(oceanRepo.count(), equalTo(5L));
    }
    
    //------------------------------------------------- exists
    
    @Test
    public void testExists() {
        assertThat(oceanRepo.exists(2), equalTo(true));
        assertThat(oceanRepo.exists(222), equalTo(false));
    }
    
    //------------------------------------------------- find one
    
    @Test
    public void testFindOne() {
        Ocean ocean = oceanRepo.findOne(5);
        assertThat(ocean.getName(), equalTo("Southern"));
    }
    
    //------------------------------------------------- find all
    
    @Test
    public void testFindAll() {
        Iterable<Ocean> oceans = oceanRepo.findAll();
        assertThat(oceans.toString(), equalTo("[Artic, Atlantic, Indian, Pacific, Southern]"));
    }
    
    @Test
    public void testFindAllWithIds() {
        List<Integer> ids = new ArrayList<Integer>();
        ids.add(2);
        ids.add(4);
        
        Iterable<Ocean> oceans = oceanRepo.findAll(ids);
        assertThat(oceans.toString(), equalTo("[Atlantic, Pacific]"));
    }
    
    //------------------------------------------------- insert
    
    @Test
    public void testInsert() {
    
        Ocean another = new Ocean();
        another.setName("Another");
        another.setArea(123456);
        
        Ocean inserted = oceanRepo.save(another);
        
        assertThat(inserted.getName(), equalTo("Another"));
        assertThat(inserted.getArea(), equalTo(123456));
        
        Ocean fromPersistenceContext = em.find(Ocean.class, inserted.getId());
        assertThat(fromPersistenceContext.getName(), equalTo("Another"));
    }
    
    //------------------------------------------------- update
    
    @Test
    public void testUpdate() {
        Ocean atlantic = em.find(Ocean.class, 2);
        atlantic.setArea(29631234);
        
        Ocean updated = oceanRepo.save(atlantic);
        
        assertThat(updated.getName(), equalTo("Atlantic"));
        assertThat(updated.getArea(), equalTo(29631234));
        
        Ocean fromPersistenceContext = em.find(Ocean.class, 2);
        assertThat(fromPersistenceContext.getArea(), equalTo(29631234));
    }
    
    //------------------------------------------------- delete
    
    @Test
    public void testDelete() {
        Ocean pacific = em.find(Ocean.class, 4);
        
        oceanRepo.delete(pacific);
        
        assertThat(em.find(Ocean.class, 4), equalTo(null));
    }
}
