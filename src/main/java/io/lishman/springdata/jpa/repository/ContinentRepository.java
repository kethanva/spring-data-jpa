package io.lishman.springdata.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.lishman.springdata.jpa.domain.Continent;

public interface ContinentRepository extends JpaRepository<Continent, Integer> {

}
