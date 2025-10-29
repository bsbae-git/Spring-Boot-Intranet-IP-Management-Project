package com.jules.ipam.vo;

import java.io.Serializable;

public class NetworkVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String cidr;
    private String networkAddress;
    private String broadcastAddress;
    private String subnetMask;

    // Manual Getters
    public Integer getId() {
        return id;
    }

    public String getCidr() {
        return cidr;
    }

    public String getNetworkAddress() {
        return networkAddress;
    }

    public String getBroadcastAddress() {
        return broadcastAddress;
    }

    public String getSubnetMask() {
        return subnetMask;
    }

    // Manual Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setCidr(String cidr) {
        this.cidr = cidr;
    }

    public void setNetworkAddress(String networkAddress) {
        this.networkAddress = networkAddress;
    }

    public void setBroadcastAddress(String broadcastAddress) {
        this.broadcastAddress = broadcastAddress;
    }

    public void setSubnetMask(String subnetMask) {
        this.subnetMask = subnetMask;
    }
}
