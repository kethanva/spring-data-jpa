package io.lishman.springdata.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import io.lishman.springdata.jpa.domain.Country;

public interface CountryRepository extends Repository<Country, Integer> {
    
    //------------------------------------------- equality
    
    public Country findByName(String countryName);
    
    @Query("select c from Country c where c.name = ?1")
    public Country findByNameQueryPositionalParam(String countryName);
    
    @Query("select c from Country c where c.name = :countryName")
    public Country findByNameQueryNamedParam(@Param("countryName") String countryName);
    
    //------------------------------------------- ignore case

    public Country findByNameIgnoreCase(String countryName);

    @Query("select c from Country c where lower(c.name) = lower(:countryName)")
    public Country findByNameIgnoreCaseQuery(@Param("countryName") String countryName);

    //------------------------------------------- not equal
    
    public List<Country> findByNameNot(String countryName);

    @Query("select c from Country c where c.name != :name")
    public List<Country> findByNameNotQuery(@Param("name") String countryName);

    //------------------------------------------- like / containing / startingWith

    public List<Country> findByNameLike(String searchTerm);

    public List<Country> getByNameContaining(String searchTerm);
    
    public List<Country> readByNameStartingWith(String searchTerm);

    @Query("select c from Country c where c.name like ?1")
    public List<Country> findByNameLikeQuery(String searchTerm);
    
    //------------------------------------------- nested
    
    public List<Country> findByContinentName(String continentName);
    
    @Query("select ctry from Country ctry where ctry.continent.name = :name")
    public List<Country> findByContinentNameQuery(@Param("name") String continentName);
    
    //------------------------------------------- less than / greater than

    public List<Country> findByAreaLessThan(int area);

    @Query("select c from Country c where c.area < ?1")
    public List<Country> findByAreaLessThanQuery(int area);

    public List<Country> findByPopulationGreaterThan(int population);

    @Query("select c from Country c where c.population > ?1")
    public List<Country> findByPopulationGreaterThanQuery(int population);
    
    //------------------------------------------- between
    
    public List<Country> findByPopulationBetween(int start, int end);
    
    @Query("select c from Country c where c.population > ?1 and c.population < ?2")
    public List<Country> findByPopulationBetweenQuery(int start, int end);
    
    //------------------------------------------- and

    public List<Country> findByContinentNameAndPopulationLessThan(String continentName, int pop);

    @Query("select c from Country c where c.continent.name = ?1 and c.population < ?2")
    public List<Country> findByContinentNameAndPopulationLessThanQuery(String continentName, int pop);
    
    //------------------------------------------- or
    
    public List<Country> findByPopulationLessThanOrAreaLessThan(int pop, int area);
    
    @Query("select c from Country c where c.population < ?1 or c.area < ?2")
    public List<Country> findByPopulationLessThanOrAreaLessThanQuery(int pop, int area);

    //------------------------------------------- order by
    
    public List<Country> findByContinentNameOrderByPopulationDesc(String continentName);
}
