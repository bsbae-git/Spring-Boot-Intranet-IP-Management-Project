package com.enterprise.ipmanager.service;

import com.enterprise.ipmanager.dto.SubnetDto;
import com.enterprise.ipmanager.model.Subnet;
import com.enterprise.ipmanager.repository.SubnetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubnetService {

    private final SubnetRepository subnetRepository;

    @Transactional(readOnly = true)
    public List<SubnetDto> getAllSubnets() {
        return subnetRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public SubnetDto createSubnet(SubnetDto subnetDto) {
        // Basic CIDR validation can be added here
        subnetRepository.findByCidr(subnetDto.getCidr()).ifPresent(s -> {
            throw new IllegalArgumentException("Subnet with CIDR " + s.getCidr() + " already exists.");
        });

        Subnet subnet = new Subnet();
        subnet.setCidr(subnetDto.getCidr());
        subnet.setName(subnetDto.getName());
        subnet.setVlanId(subnetDto.getVlanId());
        subnet.setNote(subnetDto.getNote());
        Subnet savedSubnet = subnetRepository.save(subnet);
        return convertToDto(savedSubnet);
    }

    private SubnetDto convertToDto(Subnet subnet) {
        SubnetDto dto = new SubnetDto();
        dto.setId(subnet.getId());
        dto.setCidr(subnet.getCidr());
        dto.setName(subnet.getName());
        dto.setVlanId(subnet.getVlanId());
        dto.setNote(subnet.getNote());
        return dto;
    }
}