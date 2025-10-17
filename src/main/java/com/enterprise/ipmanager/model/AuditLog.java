package com.enterprise.ipmanager.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "audit_logs")
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String action; // e.g., "ASSIGN_IP", "RELEASE_IP", "CREATE_SUBNET"

    @Column(nullable = false)
    private String username; // User who performed the action

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Lob
    private String details; // e.g., JSON representation of the change
}