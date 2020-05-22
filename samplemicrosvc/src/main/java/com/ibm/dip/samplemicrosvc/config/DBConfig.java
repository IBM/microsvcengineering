package com.ibm.dip.samplemicrosvc.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"com.ibm.dip.samplemicrosvc"})
@EntityScan(basePackages = {"com.ibm.dip.samplemicrosvc"})
public class DBConfig
{
}
