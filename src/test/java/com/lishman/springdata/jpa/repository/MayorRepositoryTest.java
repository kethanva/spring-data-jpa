package com.lishman.springdata.jpa.repository;

import com.lishman.springdata.jpa.config.Config;
import com.lishman.springdata.jpa.config.TestConfig;
import com.lishman.springdata.jpa.domain.Mayor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@ContextConfiguration(classes={Config.class, TestConfig.class})
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class MayorRepositoryTest {

    @Autowired private MayorRepository mayorRepo;

    //------------------------------------------------- find by name
    
    @Test
    public void testFindByName() {
        Mayor mayor = mayorRepo.findByName("Klaus Wowereit");
        assertThat(mayor.getId(), equalTo(1));
        assertThat(mayor.getName(), equalTo("Klaus Wowereit"));
    }
    
    //------------------------------------------------- get name length 

    @Test
    public void testGetNameLength() {
        int length = mayorRepo.getNameLength(1);
        assertThat(length, equalTo(14));
    }
}
