package com.ibm.dip.microsvcengineering.framework.kafka.streams;

public interface StreamProcessor<T,U,V> {

    public T process(U input, V... optionalParams);
}
