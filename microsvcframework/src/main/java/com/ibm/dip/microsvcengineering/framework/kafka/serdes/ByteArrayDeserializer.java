package com.ibm.dip.microsvcengineering.framework.kafka.serdes;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class ByteArrayDeserializer<T> implements Deserializer<T> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public T deserialize(String topic, byte[] data) {
        return SerializationUtils.deserialize(data);
    }

    @Override
    public void close() {

    }
}