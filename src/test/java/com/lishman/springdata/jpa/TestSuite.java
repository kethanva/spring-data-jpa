package com.lishman.springdata.jpa;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.lishman.springdata.jpa.repository.CountryRepositoryTest;
import com.lishman.springdata.jpa.repository.LanguageRepositoryTest;
import com.lishman.springdata.jpa.repository.MayorRepositoryTest;
import com.lishman.springdata.jpa.repository.OceanRepositoryTest;
import com.lishman.springdata.jpa.repository.StateRepositoryTest;

// TODO keep this?
@RunWith(Suite.class)
@Suite.SuiteClasses( {
    
    LanguageRepositoryTest.class,
    CountryRepositoryTest.class,
    LanguageRepositoryTest.class,
    MayorRepositoryTest.class,
    OceanRepositoryTest.class,
    StateRepositoryTest.class,

})

public class TestSuite {}