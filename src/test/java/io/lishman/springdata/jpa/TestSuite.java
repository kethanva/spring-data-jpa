package io.lishman.springdata.jpa;

import io.lishman.springdata.jpa.repository.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

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