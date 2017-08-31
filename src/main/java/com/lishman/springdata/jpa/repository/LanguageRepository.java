package com.lishman.springdata.jpa.repository;

import com.lishman.springdata.jpa.domain.Language;
import com.lishman.springdata.jpa.domain.Ocean;

public interface LanguageRepository 
            extends SingleItemOnlyRepository<Language, Integer> {

    public Ocean findByName(String name);
    
}
