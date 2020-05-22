package com.ibm.dip.samplemicrosvc.integration;

import com.ibm.dip.microsvcengineering.framework.monitoring.MonitoredIntegrationComponent;
import com.ibm.dip.samplemicrosvc.model.AuditEntry;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@MonitoredIntegrationComponent
public class AuditHelper {
    private static final Logger logger = LoggerFactory.getLogger(AuditHelper.class);

    @Autowired
    KafkaProducer<String, AuditEntry> producer;

    @Value("${application.audit.topic}")
    private String topic;


    private long generateUUID()
    {
        return UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
    }

    public void logAuditEntry(String userId, String entityName, String action)
    {
        AuditEntry ae = new AuditEntry(generateUUID(), System.currentTimeMillis(), entityName, userId, action);
        ProducerRecord<String, AuditEntry> pr = new ProducerRecord<>(topic, ae.getEntityName(), ae);
        logger.debug("Publishing Audit entry: {}", ae);
        producer.send(pr);
    }
}
