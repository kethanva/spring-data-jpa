package com.lishman.springdata.jpa.repository;

import com.lishman.springdata.jpa.config.Config;
import com.lishman.springdata.jpa.config.TestConfig;
import com.lishman.springdata.jpa.domain.Language;
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

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@ContextConfiguration(classes={Config.class, TestConfig.class})
@ActiveProfiles("test")
@Transactional
@Rollback
@RunWith(SpringJUnit4ClassRunner.class)
public class LanguageRepositoryTest {

    @Autowired private LanguageRepository languageRepo;

    @PersistenceContext private EntityManager em;
    
    //------------------------------------------------- delete by id
    
    @Test
    public void testDeleteUsingId() {
        
        languageRepo.delete(3);
        
        assertThat(em.find(Language.class, 3), equalTo(null));
    }
    
    //------------------------------------------------- delete by entity
    
    @Test
    public void testDeleteUsingEntity() {
        Language hindi = em.find(Language.class, 5);
        
        languageRepo.delete(hindi);
        
        assertThat(em.find(Language.class, 5), equalTo(null));
    }
    
   //------------------------------------------------- exists
    
    @Test
    public void testExists() {
        assertThat(languageRepo.exists(2), equalTo(true));
        assertThat(languageRepo.exists(22), equalTo(false));
    }

    //------------------------------------------------- findOne
    
    @Test
    public void testFindOne() {
        Language russian = languageRepo.findOne(8);
        assertThat(russian.getName(), equalTo("Russian"));
    }
    
    //------------------------------------------------- save
    
    @Test
    public void testInsert() {
        Language other = new Language();
        other.setName("Others");
        other.setPercentageSpoken(61.17);
        
        Language inserted = languageRepo.save(other);
        
        assertThat(inserted.getName(), equalTo("Others"));
        assertThat(em.find(Language.class, inserted.getId()).getName(), equalTo("Others"));
    }


}
