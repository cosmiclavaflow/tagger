package com.music.tagger.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EntityScan("com.music.tagger.persistence.entity")
@EnableJpaRepositories(basePackages = {"com.music.tagger.persistence.repository"})
public class PersistenceJpaConfig {

}
