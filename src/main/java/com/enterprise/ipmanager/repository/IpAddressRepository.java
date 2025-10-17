package com.enterprise.ipmanager.repository;

import com.enterprise.ipmanager.model.IpAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IpAddressRepository extends JpaRepository<IpAddress, Long>, JpaSpecificationExecutor<IpAddress> {
    Optional<IpAddress> findByAddress(String address);
}