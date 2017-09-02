package io.lishman.springdata.jpa.repository;

import org.springframework.data.repository.CrudRepository;

import io.lishman.springdata.jpa.domain.Ocean;

public interface OceanRepository extends CrudRepository<Ocean, Integer> {

    public Ocean findByName(String name);
    
}
