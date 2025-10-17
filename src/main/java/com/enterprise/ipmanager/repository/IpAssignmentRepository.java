package com.enterprise.ipmanager.repository;

import com.enterprise.ipmanager.model.IpAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IpAssignmentRepository extends JpaRepository<IpAssignment, Long> {
}