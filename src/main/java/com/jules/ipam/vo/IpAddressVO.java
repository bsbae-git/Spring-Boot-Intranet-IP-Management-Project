package com.jules.ipam.vo;

import java.io.Serializable;

public class IpAddressVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String ipAddress;
    private String status; // AVAILABLE, RESERVED, ALLOCATED
    private Integer networkId;

    // Manual Getters
    public Integer getId() {
        return id;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getStatus() {
        return status;
    }

    public Integer getNetworkId() {
        return networkId;
    }

    // Manual Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setNetworkId(Integer networkId) {
        this.networkId = networkId;
    }
}
