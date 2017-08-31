package com.lishman.springdata.jpa.repository;

import org.springframework.data.repository.Repository;

import com.lishman.springdata.jpa.domain.Mayor;

public interface MayorRepository extends Repository<Mayor, Integer>,
                                          MayorRepositoryCustom {

    public Mayor findByName(String name);

}
