package io.lishman.springdata.jpa.repository;

import io.lishman.springdata.jpa.domain.Language;
import io.lishman.springdata.jpa.domain.Ocean;

public interface LanguageRepository 
            extends SingleItemOnlyRepository<Language, Integer> {

    public Ocean findByName(String name);
    
}
