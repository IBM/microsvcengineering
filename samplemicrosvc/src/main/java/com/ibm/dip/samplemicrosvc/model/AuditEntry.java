package com.ibm.dip.samplemicrosvc.model;

import java.io.Serializable;
import java.util.Objects;

public class AuditEntry implements Serializable {
    public static final String ACTION_CREATE = "CREATE";
    public static final String ACTION_READ = "READ";
    public static final String ACTION_UPDATE = "UPDATE";
    public static final String ACTION_DELETE = "DELETE";

    private long id;
    private long createdAt;
    private String entityName;
    private String userId;
    private String action;

    public AuditEntry() {
    }

    public AuditEntry(long id, long createdAt, String entityName, String userId, String action) {
        this.id = id;
        this.createdAt = createdAt;
        this.entityName = entityName;
        this.userId = userId;
        this.action = action;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
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
        AuditEntry that = (AuditEntry) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "AuditEntry{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", entityName='" + entityName + '\'' +
                ", userId='" + userId + '\'' +
                ", action='" + action + '\'' +
                '}';
    }
}
