package com.lishman.springdata.jpa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
// TODO use basePackageClasses
@EnableJpaRepositories(basePackages="com.lishman.springdata.jpa.repository")
public class RepositoryConfig {
    
}