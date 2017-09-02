package io.lishman.springdata.jpa.repository;

import io.lishman.springdata.jpa.domain.State;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.math.BigInteger;
import java.util.List;

public interface StateRepository extends PagingAndSortingRepository<State, BigInteger> {

    public List<State> findByCapitalSinceGreaterThan(int since, Pageable pageable);

    public List<State> findByNameStartingWith(String startsWith, Sort sort);
    
}
