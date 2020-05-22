package com.ibm.dip.samplemicrosvc.dao.repositories;

import com.ibm.dip.samplemicrosvc.dao.entities.AuditEntryEntity;
import org.springframework.data.repository.CrudRepository;

public interface AuditEntryEntityRepository extends CrudRepository<AuditEntryEntity, String> {
}
