package com.ibm.dip.samplemicrosvc.dao.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "AuditEntry")
public class AuditEntryEntity {


    @Id
    private Long id;

    @Column(name = "createdat")
    private Timestamp createdAt;

    @Column(name = "entityname")
    private String entityName;

    @Column(name = "userid")
    private String userId;

    @Column(name = "action")
    private String action;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuditEntryEntity that = (AuditEntryEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "AuditEntryEntity{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", entityName='" + entityName + '\'' +
                ", userId='" + userId + '\'' +
                ", action='" + action + '\'' +
                '}';
    }
}
