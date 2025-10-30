package com.jules.ipam.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class IpAllocationRequestDTO {

    @NotBlank(message = "IP address cannot be blank.")
    @Pattern(regexp = "^([0-9]{1,3}\\.){3}[0-9]{1,3}$", message = "Invalid IP address format.")
    private String ipAddress;

    @NotBlank(message = "User ID cannot be blank.")
    private String userId;

    private String purpose;

    // Manual Getters
    public String getIpAddress() {
        return ipAddress;
    }

    public String getUserId() {
        return userId;
    }

    public String getPurpose() {
        return purpose;
    }

    // Manual Setters
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
}
