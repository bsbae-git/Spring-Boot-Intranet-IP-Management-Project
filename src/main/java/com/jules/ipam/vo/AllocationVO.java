package com.jules.ipam.vo;

import java.io.Serializable;
import java.sql.Timestamp;

public class AllocationVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer ipId;
    private String userId;
    private String purpose;
    private Timestamp allocatedAt;

    // Manual Getters
    public Integer getId() {
        return id;
    }

    public Integer getIpId() {
        return ipId;
    }

    public String getUserId() {
        return userId;
    }

    public String getPurpose() {
        return purpose;
    }

    public Timestamp getAllocatedAt() {
        return allocatedAt;
    }

    // Manual Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setIpId(Integer ipId) {
        this.ipId = ipId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public void setAllocatedAt(Timestamp allocatedAt) {
        this.allocatedAt = allocatedAt;
    }
}
