package com.lishman.springdata.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lishman.springdata.jpa.domain.Continent;

public interface ContinentRepository extends JpaRepository<Continent, Integer> {

}
