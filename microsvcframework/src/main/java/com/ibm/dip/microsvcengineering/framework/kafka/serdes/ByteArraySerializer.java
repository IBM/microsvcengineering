package com.ibm.dip.microsvcengineering.framework.kafka.serdes;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.kafka.common.serialization.Serializer;

import java.io.Serializable;
import java.util.Map;

public class ByteArraySerializer<T> implements Serializer<T> {

    @Override
    public void configure(Map configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String topic, T data) {

        if (data instanceof Serializable)
        {
            return SerializationUtils.serialize((Serializable) data);
        }
        else
        {
            throw new IllegalArgumentException("input object does not implement Serializable");
        }
    }

    @Override
    public void close() {

    }
}
