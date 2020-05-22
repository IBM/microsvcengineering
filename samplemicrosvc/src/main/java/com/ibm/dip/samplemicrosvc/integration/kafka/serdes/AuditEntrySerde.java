package com.ibm.dip.samplemicrosvc.integration.kafka.serdes;

import com.ibm.dip.microsvcengineering.framework.kafka.serdes.ByteArrayDeserializer;
import com.ibm.dip.microsvcengineering.framework.kafka.serdes.ByteArraySerializer;
import com.ibm.dip.samplemicrosvc.model.AuditEntry;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class AuditEntrySerde implements Serde<AuditEntry> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public void close() {

    }

    @Override
    public Serializer<AuditEntry> serializer() {
        return new ByteArraySerializer<>();

    }

    @Override
    public Deserializer<AuditEntry> deserializer() {
        return new ByteArrayDeserializer<>();
    }

}
