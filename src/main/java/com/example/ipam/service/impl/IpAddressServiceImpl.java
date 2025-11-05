package com.example.ipam.service.impl;

import com.example.ipam.dao.IpAddressDAO;
import com.example.ipam.service.IpAddressService;
import com.example.ipam.service.NetworkScanService;
import com.example.ipam.vo.IpAddressVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class IpAddressServiceImpl implements IpAddressService {

    private final IpAddressDAO ipAddressDAO;
    private final NetworkScanService networkScanService;

    @Autowired
    public IpAddressServiceImpl(IpAddressDAO ipAddressDAO, NetworkScanService networkScanService) {
        this.ipAddressDAO = ipAddressDAO;
        this.networkScanService = networkScanService;
    }

    @Override
    public List<IpAddressVO> getAllIpAddresses() {
        return ipAddressDAO.findAll();
    }

    @Override
    public void updateScanResults(String subnet) {
        List<String> activeIps = networkScanService.scanNetwork(subnet);
        Map<String, IpAddressVO> dbIpMap = ipAddressDAO.findAll().stream()
                .collect(Collectors.toMap(IpAddressVO::getIpAddress, Function.identity()));

        for (String ip : activeIps) {
            IpAddressVO vo = dbIpMap.get(ip);
            if (vo == null) {
                // New active IP, insert it
                IpAddressVO newVo = new IpAddressVO();
                newVo.setIpAddress(ip);
                newVo.setStatus("active");
                newVo.setLastSeen(LocalDateTime.now());
                ipAddressDAO.insert(newVo);
            } else {
                // Existing IP, update its status and last_seen time
                vo.setStatus("active");
                vo.setLastSeen(LocalDateTime.now());
                ipAddressDAO.update(vo);
            }
        }
    }
}
