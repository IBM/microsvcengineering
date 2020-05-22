package com.ibm.dip.samplemicrosvc.streamlisteners;

import com.ibm.dip.samplemicrosvc.dao.AuditEntryDAO;
import com.ibm.dip.samplemicrosvc.model.AuditEntry;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@EnableBinding(AuditEntryStreamBindings.class)
public class AuditEntryStreamListener {

    @Autowired
    AuditEntryDAO auditEntryDAO;

    @StreamListener(AuditEntryStreamBindings.INPUT)
    public void auditEntryListener(KStream<String, AuditEntry> auditEntryKStream)
    {
        auditEntryKStream.foreach((key, auditEntry) -> {
            auditEntryDAO.save(auditEntry);
        });
    }
}
