package com.members.api.data.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EntityScan(basePackages = {"com.members.api.data.entity"})
@EnableJpaRepositories("com.members.api.data.repository")
@EnableTransactionManagement
public class JpaConfig {

}
