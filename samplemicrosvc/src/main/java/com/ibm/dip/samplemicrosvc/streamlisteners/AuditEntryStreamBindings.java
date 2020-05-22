package com.ibm.dip.samplemicrosvc.streamlisteners;

import com.ibm.dip.samplemicrosvc.model.AuditEntry;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.context.annotation.Configuration;

@Configuration
public interface AuditEntryStreamBindings {
    String INPUT = "input";

    @Input
    KStream<String, AuditEntry> input();

}
