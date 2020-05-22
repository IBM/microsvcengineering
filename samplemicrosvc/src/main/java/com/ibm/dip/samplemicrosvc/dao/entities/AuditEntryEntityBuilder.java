package com.ibm.dip.samplemicrosvc.dao.entities;

import com.ibm.dip.samplemicrosvc.model.AuditEntry;

import java.sql.Timestamp;

public class AuditEntryEntityBuilder {

    public static AuditEntryEntity from(AuditEntry auditEntry)
    {
        AuditEntryEntity aee = new AuditEntryEntity();

        aee.setId(auditEntry.getId());
        aee.setAction(auditEntry.getAction());
        aee.setCreatedAt(new Timestamp(auditEntry.getCreatedAt()));
        aee.setEntityName(auditEntry.getEntityName());
        aee.setUserId(auditEntry.getUserId());
        return aee;
    }
}
