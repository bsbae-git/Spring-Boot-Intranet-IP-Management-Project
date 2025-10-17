package com.enterprise.ipmanager.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "subnets")
public class Subnet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String cidr; // e.g., "192.168.1.0/24"

    @Column(nullable = false)
    private String name;

    private Integer vlanId;

    @Lob
    private String note;
}