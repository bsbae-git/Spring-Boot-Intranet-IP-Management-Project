package com.enterprise.ipmanager.controller;

import com.enterprise.ipmanager.dto.IpAddressDto;
import com.enterprise.ipmanager.service.IpAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/ips")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class IpAddressController {

    private final IpAddressService ipAddressService;

    @GetMapping
    public ResponseEntity<List<IpAddressDto>> getAllIpAddresses() {
        return ResponseEntity.ok(ipAddressService.getAllIpAddresses());
    }

    @PostMapping
    public ResponseEntity<IpAddressDto> createIpAddress(@RequestBody IpAddressDto ipAddressDto) {
        return ResponseEntity.ok(ipAddressService.createIpAddress(ipAddressDto));
    }

    @GetMapping("/search")
    public ResponseEntity<org.springframework.data.domain.Page<IpAddressDto>> searchIpAddresses(
            @RequestParam(required = false) com.enterprise.ipmanager.model.enums.IpStatus status,
            @RequestParam(required = false) String keyword,
            @org.springframework.data.web.PageableDefault(size = 20, sort = "address") org.springframework.data.domain.Pageable pageable) {
        return ResponseEntity.ok(ipAddressService.searchIpAddresses(status, keyword, pageable));
    }
}