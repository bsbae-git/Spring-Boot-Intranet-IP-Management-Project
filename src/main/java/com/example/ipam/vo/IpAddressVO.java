package com.example.ipam.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

public class IpAddressVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String ipAddress;
    private String macAddress;
    private String hostname;
    private String status;
    private LocalDateTime lastSeen;

    public IpAddressVO() {
    }

    public IpAddressVO(Long id, String ipAddress, String macAddress, String hostname, String status, LocalDateTime lastSeen) {
        this.id = id;
        this.ipAddress = ipAddress;
        this.macAddress = macAddress;
        this.hostname = hostname;
        this.status = status;
        this.lastSeen = lastSeen;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(LocalDateTime lastSeen) {
        this.lastSeen = lastSeen;
    }

    @Override
    public String toString() {
        return "IpAddressVO{" +
                "id=" + id +
                ", ipAddress='" + ipAddress + '\'' +
                ", macAddress='" + macAddress + '\'' +
                ", hostname='" + hostname + '\'' +
                ", status='" + status + '\'' +
                ", lastSeen=" + lastSeen +
                '}';
    }
}
