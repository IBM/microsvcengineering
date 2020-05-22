package com.ibm.dip.microsvcengineering.framework.monitoring;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.apache.commons.collections4.MapUtils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.Metric;
import org.apache.kafka.common.MetricName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class KafkaProducerMonitor implements MeterBinder {
    private static final Logger logger = LoggerFactory.getLogger(KafkaProducerMonitor.class);

    private Set<String> filterOutMetrics;
    private Set<KafkaMetric> bindedMetrics;
    private KafkaProducer<?,?> kafkaProducer;
    private Iterable<Tag> tags;

    public KafkaProducerMonitor(KafkaProducer kafkaProducer, MeterRegistry registry, Iterable<Tag> tags)
    {
        this.kafkaProducer = kafkaProducer;
        this.filterOutMetrics = new HashSet<>();

        //Filter out those metrics that dont produce a double
        this.filterOutMetrics.addAll(Arrays.asList("commit-id", "version"));

        //TODO: Currently metrics all metrics are registered. make the metrics to monitor configurable
        this.bindedMetrics = new HashSet<>();
        this.tags = tags;
    }

    @Override
    public void bindTo(MeterRegistry registry) {
        Map<MetricName, ? extends Metric> metrics = kafkaProducer.metrics();
        if (MapUtils.isNotEmpty(metrics))
        {
            metrics.keySet().stream().filter(metricName -> !filterOutMetrics.contains(metricName.name()))
                    .forEach(metricName -> {
                        logger.debug("Registering Kafka Producer Metric: {}", metricName);
                        KafkaMetric metric = new KafkaMetric(metricName, kafkaProducer);
                        bindedMetrics.add(metric);
                        Gauge.builder("kafka-producer-" + metricName.name(), metric, KafkaMetric::getMetricValue)
                                .tags(tags)
                                .register(registry);
                    });
        }
    }
}
