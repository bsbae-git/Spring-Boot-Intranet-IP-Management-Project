package com.example.ipmanager.repository;

import com.example.ipmanager.model.IpAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IpAddressRepository extends JpaRepository<IpAddress, Long> {
    Optional<IpAddress> findByIpAddress(String ipAddress);
}