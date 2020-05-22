package com.ibm.dip.samplemicrosvc.config;

import com.ibm.dip.microsvcengineering.framework.monitoring.KafkaProducerMonitor;
import com.ibm.dip.samplemicrosvc.model.AuditEntry;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class KafkaClientConfig {

    private static final String kafka_bootstrap_servers_key = "bootstrap.servers";
    private static final String kafka_acks_key = "acks";
    private static final String kafka_retries_key = "retries";
    private static final String kafka_batch_size_key = "batch.size";
    private static final String kafka_linger_ms_key = "linger.ms";
    // private static final String kafka_buffer_memory_key = "buffer.memory";
    private static final String kafka_key_serializer_key = "key.serializer";
    private static final String kafka_value_serializer_key = "value.serializer";

    private KafkaProducer<String, AuditEntry> producer = null;

    @Value("${application.kafkaHosts}")
    private String kafkaHosts;

    @Value("${application.audit.key.serializer}")
    private String keySerializer;

    @Value("${application.audit.value.serializer}")
    private String valueSerializer;

    @Value("${application.audit.topic}")
    private String auditTopic;

    @Bean
    public KafkaProducer<String, AuditEntry> auditEntryKafkaProducer()
    {
        Properties props = new Properties();
        props.setProperty(kafka_bootstrap_servers_key, kafkaHosts);
        props.setProperty(kafka_acks_key, "all");
        props.setProperty(kafka_retries_key, "0");
        props.setProperty(kafka_batch_size_key, "16384");
        props.setProperty(kafka_linger_ms_key, "0");
        // props.setProperty(kafka_buffer_memory_key, bufferMemory);
        props.setProperty(kafka_key_serializer_key, keySerializer);
        props.setProperty(kafka_value_serializer_key, valueSerializer);

        KafkaProducer<String, AuditEntry> kafkaProducer = new KafkaProducer<>(props);
        return new KafkaProducer<>(props);
    }

    @Bean
    public KafkaProducerMonitor kafkaProducerMonitor(KafkaProducer<String, AuditEntry> kafkaProducer, MeterRegistry registry)
    {
        return new KafkaProducerMonitor(kafkaProducer, registry, Tags.of("topic", auditTopic));
    }
}
