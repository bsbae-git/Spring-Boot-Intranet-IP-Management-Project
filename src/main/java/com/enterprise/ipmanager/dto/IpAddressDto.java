package com.enterprise.ipmanager.dto;

import com.enterprise.ipmanager.model.enums.IpStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class IpAddressDto {
    private Long id;
    private String address;
    private Long subnetId;
    private IpStatus status;
    private String owner;
    private LocalDateTime assignedAt;
    private LocalDateTime releasedAt;
}