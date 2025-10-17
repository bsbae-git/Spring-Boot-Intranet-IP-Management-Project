package com.enterprise.ipmanager.controller;

import com.enterprise.ipmanager.dto.SubnetDto;
import com.enterprise.ipmanager.service.SubnetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/subnets")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class SubnetController {

    private final SubnetService subnetService;

    @GetMapping
    public ResponseEntity<List<SubnetDto>> getAllSubnets() {
        return ResponseEntity.ok(subnetService.getAllSubnets());
    }

    @PostMapping
    public ResponseEntity<SubnetDto> createSubnet(@RequestBody SubnetDto subnetDto) {
        return ResponseEntity.ok(subnetService.createSubnet(subnetDto));
    }
}