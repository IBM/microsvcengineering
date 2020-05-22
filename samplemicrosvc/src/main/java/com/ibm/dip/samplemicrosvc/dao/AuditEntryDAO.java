package com.ibm.dip.samplemicrosvc.dao;

import com.ibm.dip.microsvcengineering.framework.monitoring.MonitoredDAO;
import com.ibm.dip.samplemicrosvc.dao.entities.AuditEntryEntity;
import com.ibm.dip.samplemicrosvc.dao.entities.AuditEntryEntityBuilder;
import com.ibm.dip.samplemicrosvc.dao.repositories.AuditEntryEntityRepository;
import com.ibm.dip.samplemicrosvc.model.AuditEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@MonitoredDAO
public class AuditEntryDAO {
    private static final Logger logger = LoggerFactory.getLogger(AuditEntryDAO.class);

    @Autowired
    AuditEntryEntityRepository repo;

    public void save(AuditEntry auditEntry)
    {
        logger.debug("saving auditEntry: {}", auditEntry);
        AuditEntryEntity aee = AuditEntryEntityBuilder.from(auditEntry);
        repo.save(aee);
    }
}
