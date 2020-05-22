package com.ibm.dip.microsvcengineering.framework.monitoring;

import io.micrometer.prometheus.PrometheusMeterRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MicroSvcMeterRegistryConfig {

    @Value("${spring.application.name}")
    String appName;

    @Value("${env}")
    String environment;

    @Value("${instanceId}")
    String instanceId;

    @Bean
    MeterRegistryCustomizer<PrometheusMeterRegistry> configureMetricsRegistry()
    {
        return registry -> registry.config().commonTags("appName", appName, "env", environment, "instanceId", instanceId);
    }
}
