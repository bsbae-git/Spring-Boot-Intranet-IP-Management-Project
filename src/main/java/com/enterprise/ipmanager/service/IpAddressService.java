package com.enterprise.ipmanager.service;

import com.enterprise.ipmanager.dto.IpAddressDto;
import com.enterprise.ipmanager.model.IpAddress;
import com.enterprise.ipmanager.model.Subnet;
import com.enterprise.ipmanager.repository.IpAddressRepository;
import com.enterprise.ipmanager.repository.SubnetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IpAddressService {

    private final IpAddressRepository ipAddressRepository;
    private final SubnetRepository subnetRepository;

    @Transactional(readOnly = true)
    public List<IpAddressDto> getAllIpAddresses() {
        return ipAddressRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public IpAddressDto createIpAddress(IpAddressDto ipAddressDto) {
        Subnet subnet = subnetRepository.findById(ipAddressDto.getSubnetId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Subnet ID: " + ipAddressDto.getSubnetId()));

        // Basic IP format and duplication validation can be added here
        ipAddressRepository.findByAddress(ipAddressDto.getAddress()).ifPresent(ip -> {
            throw new IllegalArgumentException("IP address " + ip.getAddress() + " already exists.");
        });

        IpAddress ipAddress = new IpAddress();
        ipAddress.setAddress(ipAddressDto.getAddress());
        ipAddress.setSubnet(subnet);
        ipAddress.setStatus(ipAddressDto.getStatus());
        ipAddress.setOwner(ipAddressDto.getOwner());
        IpAddress savedIpAddress = ipAddressRepository.save(ipAddress);
        return convertToDto(savedIpAddress);
    }

    private IpAddressDto convertToDto(IpAddress ipAddress) {
        IpAddressDto dto = new IpAddressDto();
        dto.setId(ipAddress.getId());
        dto.setAddress(ipAddress.getAddress());
        dto.setSubnetId(ipAddress.getSubnet().getId());
        dto.setStatus(ipAddress.getStatus());
        dto.setOwner(ipAddress.getOwner());
        dto.setAssignedAt(ipAddress.getAssignedAt());
        dto.setReleasedAt(ipAddress.getReleasedAt());
        return dto;
    }

    @Transactional(readOnly = true)
    public org.springframework.data.domain.Page<IpAddressDto> searchIpAddresses(com.enterprise.ipmanager.model.enums.IpStatus status, String keyword, org.springframework.data.domain.Pageable pageable) {
        org.springframework.data.jpa.domain.Specification<IpAddress> spec = org.springframework.data.jpa.domain.Specification
                .where(com.enterprise.ipmanager.repository.IpAddressSpecification.hasStatus(status))
                .and(com.enterprise.ipmanager.repository.IpAddressSpecification.hasKeyword(keyword));

        return ipAddressRepository.findAll(spec, pageable).map(this::convertToDto);
    }
}