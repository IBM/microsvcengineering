package com.ibm.dip.microsvcengineering.framework.monitoring;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.MetricName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KafkaMetric {
    private static final Logger logger = LoggerFactory.getLogger(KafkaMetric.class);

    MetricName metricName;
    KafkaProducer<?,?> kafkaProducer;

    public KafkaMetric(MetricName metricName, KafkaProducer<?, ?> kafkaProducer) {
        this.metricName = metricName;
        this.kafkaProducer = kafkaProducer;
    }

    public double getMetricValue()
    {
        Object value = kafkaProducer.metrics().get(metricName).metricValue();
//        logger.debug("metric {} has value = {}", metricName, value);
        double toReturn = 0;
        if (value instanceof Double)
        {
            toReturn = (Double)value;
        }
        else
        {
            try
            {
               toReturn = Double.parseDouble(value.toString());
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return toReturn;
    }
}
