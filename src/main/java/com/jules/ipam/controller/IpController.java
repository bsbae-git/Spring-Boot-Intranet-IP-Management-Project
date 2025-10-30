package com.jules.ipam.controller;

import com.jules.ipam.dto.ApiResponse;
import com.jules.ipam.dto.IpAllocationRequestDTO;
import com.jules.ipam.dto.IpReleaseRequestDTO;
import com.jules.ipam.dto.SearchCriteriaDTO;
import com.jules.ipam.service.IpService;
import com.jules.ipam.vo.IpAddressVO;
import com.jules.ipam.vo.NetworkVO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class IpController {

    private final IpService ipService;

    public IpController(IpService ipService) {
        this.ipService = ipService;
    }

    @PostMapping("/networks")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> registerNetwork(@RequestBody NetworkVO network) {
        ipService.registerNetwork(network);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @PostMapping("/ips/allocate")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> allocateIp(@Valid @RequestBody IpAllocationRequestDTO request) {
        ipService.allocateIp(request.getIpAddress(), request.getUserId(), request.getPurpose());
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @PostMapping("/ips/release")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> releaseIp(@Valid @RequestBody IpReleaseRequestDTO request) {
        ipService.releaseIp(request.getIpAddress());
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @GetMapping("/ips")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<ApiResponse<Map<String, Object>>> searchIps(SearchCriteriaDTO criteria) {
        List<IpAddressVO> ips = ipService.searchIps(criteria);
        int total = ipService.countIps(criteria);

        Map<String, Object> responseData = new HashMap<>();
        responseData.put("ips", ips);
        responseData.put("total", total);
        responseData.put("page", criteria.getPage());
        responseData.put("pageSize", criteria.getPageSize());

        return ResponseEntity.ok(ApiResponse.success(responseData));
    }
}
