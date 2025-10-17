package com.enterprise.ipmanager.dto;

import lombok.Data;

@Data
public class SubnetDto {
    private Long id;
    private String cidr;
    private String name;
    private Integer vlanId;
    private String note;
}