package io.lishman.springdata.jpa.config;

import io.lishman.springdata.jpa.repository.RepositoryPackage;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackageClasses= RepositoryPackage.class)
public class RepositoryConfig {
    
}