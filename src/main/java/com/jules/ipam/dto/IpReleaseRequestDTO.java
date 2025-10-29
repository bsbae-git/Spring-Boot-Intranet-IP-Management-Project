package com.jules.ipam.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class IpReleaseRequestDTO {

    @NotBlank(message = "IP address cannot be blank.")
    @Pattern(regexp = "^([0-9]{1,3}\\.){3}[0-9]{1,3}$", message = "Invalid IP address format.")
    private String ipAddress;

    // Manual Getter
    public String getIpAddress() {
        return ipAddress;
    }

    // Manual Setter
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
