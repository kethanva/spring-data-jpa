package io.lishman.springdata.jpa.repository;

import io.lishman.springdata.jpa.config.Config;
import io.lishman.springdata.jpa.config.TestConfig;
import io.lishman.springdata.jpa.domain.Country;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@ContextConfiguration(classes={Config.class, TestConfig.class})
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class CountryRepositoryTest {

    @Autowired private CountryRepository countryRepo;

    @Autowired private JdbcOperations jdbcOps;
    
    //------------------------------------------------- equality

    @Test
    public void testFindByName() {

        Country country = countryRepo.findByName("Ghana");

        assertThat(country.getId(), equalTo(2));
        assertThat(country.getName(), equalTo("Ghana"));
    }

    @Test
    public void testFindByNameQueryPositional() {
        
        Country country = countryRepo.findByNameQueryPositionalParam("Gambia");
        
        assertThat(country.getId(), equalTo(7));
        assertThat(country.getName(), equalTo("Gambia"));
    }

    @Test
    public void testFindByNameQueryNamed() {
        
        Country country = countryRepo.findByNameQueryPositionalParam("Gabon");
        
        assertThat(country.getId(), equalTo(8));
        assertThat(country.getName(), equalTo("Gabon"));
    }
    
    //------------------------------------------------- ignore case
    
    @Test
    public void testFindByNameIgnoreCase() {
        
        Country country = countryRepo.findByNameIgnoreCase("greece");
        
        assertThat(country.getId(), equalTo(4));
        assertThat(country.getName(), equalTo("Greece"));
    }
    
    @Test
    public void testFindByNameIgnoreCaseQuery() {
        
        Country country = countryRepo.findByNameIgnoreCaseQuery("GREECE");
        
        assertThat(country.getId(), equalTo(4));
        assertThat(country.getName(), equalTo("Greece"));
    }
    
    //------------------------------------------------- not equal
    
    @Test
    public void testFindByNameNotEqual() {
        
        List<Country> countries = countryRepo.findByNameNot("Greece");
        
        assertThat(countries.toString(), equalTo("[Germany, Ghana, Australia, Georgia, New Zealand, Gambia, Gabon]"));
    }
    
    @Test
    public void testFindByNameNotEqualQuery() {
        
        List<Country> countries = countryRepo.findByNameNotQuery("Greece");
        
        assertThat(countries.toString(), equalTo("[Germany, Ghana, Australia, Georgia, New Zealand, Gambia, Gabon]"));
    }

    //------------------------------------------------- like / containing / startingWith

    @Test
    public void testReadByNameLike() {
        List<Country> startsWithGaOrGe = countryRepo.findByNameLike("Ge%");
        assertThat(startsWithGaOrGe.toString(), equalTo("[Georgia, Germany]"));
    }

    @Test
    public void testReadByNameContaining() {
        List<Country> startsWithGaOrGe = countryRepo.getByNameContaining("ia");
        assertThat(startsWithGaOrGe.toString(), equalTo("[Australia, Gambia, Georgia]"));
    }
    
    @Test
    public void testReadByNameStartingWith() {
        List<Country> startsWithGaOrGe = countryRepo.readByNameStartingWith("New");
        assertThat(startsWithGaOrGe.toString(), equalTo("[New Zealand]"));
    }
    
    @Test
    public void testReadByNameLikeQuery() {
        List<Country> startsWithGaOrGe = countryRepo.findByNameLikeQuery("Ga%");
        assertThat(startsWithGaOrGe.toString(), equalTo("[Gabon, Gambia]"));
    }

    
    //------------------------------------------------- nested
    
    @Test
    public void testFindByContinentName() {
        
        List<Country> countries = countryRepo.findByContinentName("Europe");
        
        assertThat(countries.toString(), equalTo("[Germany, Greece, Georgia]"));
    }
    
    @Test
    public void testFindByContinentNameQuery() {
        
        List<Country> countries = countryRepo.findByContinentNameQuery("Africa");
        
        assertThat(countries.toString(), equalTo("[Ghana, Gambia, Gabon]"));
    }
    
    
    //------------------------------------------------- less than / greater than
    
    @Test
    public void testFindByAreaInSquareMilesLessThan() {
        List<Country> smallCountries = countryRepo.findByAreaLessThan(30000);
        assertThat(smallCountries .toString(), equalTo("[Georgia, Gambia]"));
    }

    @Test
    public void testFindByAreaInSquareMilesLessThanQuery() {
        List<Country> smallCountries = countryRepo.findByAreaLessThanQuery(40000);
        assertThat(smallCountries.toString(), equalTo("[Georgia, Gambia]"));
    }
    
    @Test
    public void testFindByPopulationGreaterThan() {
        List<Country> largePopulation = countryRepo.findByPopulationGreaterThan(22000000);
        assertThat(largePopulation.toString(), equalTo("[Germany, Ghana]"));
    }
    
    @Test
    public void testFindByPopulationGreaterThanQuery() {
        List<Country> largePopulation = countryRepo.findByPopulationGreaterThanQuery(50000000);
        assertThat(largePopulation.toString(), equalTo("[Germany]"));
    }
    
    
    //------------------------------------------------- between

    @Test
    public void testFindByPopulationBetween() {
        List<Country> largePopulation = countryRepo.findByPopulationBetween(20000000, 50000000);
        assertThat(largePopulation.toString(), equalTo("[Ghana, Australia]"));
    }
    
    @Test
    public void testFindByPopulationBetweenQuery() {
        List<Country> largePopulation = countryRepo.findByPopulationBetweenQuery(10000000, 30000000);
        assertThat(largePopulation.toString(), equalTo("[Ghana, Australia, Greece]"));
    }
    
    //------------------------------------------------- and

    @Test
    public void testFindByContinentNameAndPopulationLessThan() {
        List<Country> smallPopEuropean = countryRepo.findByContinentNameAndPopulationLessThan("Europe", 20000000);
        assertThat(smallPopEuropean.toString(), equalTo("[Greece, Georgia]"));
    }
    
    @Test
    public void testFindByContinentNameAndPopulationLessThanQuery() {
        List<Country> smallPopAfrican = countryRepo.findByContinentNameAndPopulationLessThanQuery("Africa", 10000000);
        assertThat(smallPopAfrican.toString(), equalTo("[Gambia, Gabon]"));
    }
    //------------------------------------------------- or

    @Test
    public void testFindByPopulationLessThanOrAreaInSquareMilesLessThan() {
        List<Country> smallPopOrArea = countryRepo.findByPopulationLessThanOrAreaLessThan(4000000, 80000);
        assertThat(smallPopOrArea.toString(), equalTo("[Greece, Georgia, Gambia, Gabon]"));
    }
    
    @Test
    public void testFindByPopulationLessThanOrAreaInSquareMilesLessThanQuery() {
        List<Country> smallPopOrArea = countryRepo.findByPopulationLessThanOrAreaLessThanQuery(1000000, 70000);
        assertThat(smallPopOrArea.toString(), equalTo("[Greece, Georgia, Gambia]"));
    } 
    
    //------------------------------------------------- orderBy
    
    @Test
    public void testFindByContinentNameOrderByPopulation() {
        List<Country> europeanCountries = countryRepo.findByContinentNameOrderByPopulationDesc("Europe");
        assertThat(europeanCountries.toString(), equalTo("[Germany, Greece, Georgia]"));
    }
}
