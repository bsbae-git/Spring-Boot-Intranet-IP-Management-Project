package com.enterprise.ipmanager.model;

import com.enterprise.ipmanager.model.enums.IpStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ip_addresses")
public class IpAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subnet_id", nullable = false)
    private Subnet subnet;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IpStatus status;

    private String owner; // e.g., user's name or device name

    private LocalDateTime assignedAt;
    private LocalDateTime releasedAt;
}