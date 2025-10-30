package com.jules.ipam.service.impl;

import com.jules.ipam.dao.AllocationDAO;
import com.jules.ipam.dao.IpAddressDAO;
import com.jules.ipam.dao.NetworkDAO;
import com.jules.ipam.dto.SearchCriteriaDTO;
import com.jules.ipam.exception.IpAllocationException;
import com.jules.ipam.exception.IpNotFoundException;
import com.jules.ipam.service.IpService;
import com.jules.ipam.vo.AllocationVO;
import com.jules.ipam.vo.IpAddressVO;
import com.jules.ipam.vo.NetworkVO;
import org.apache.commons.net.util.SubnetUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class IpServiceImpl implements IpService {

    private final NetworkDAO networkDAO;
    private final IpAddressDAO ipAddressDAO;
    private final AllocationDAO allocationDAO;

    public IpServiceImpl(NetworkDAO networkDAO, IpAddressDAO ipAddressDAO, AllocationDAO allocationDAO) {
        this.networkDAO = networkDAO;
        this.ipAddressDAO = ipAddressDAO;
        this.allocationDAO = allocationDAO;
    }

    @Override
    @Transactional
    public void registerNetwork(NetworkVO network) {
        SubnetUtils utils = new SubnetUtils(network.getCidr());
        SubnetUtils.SubnetInfo info = utils.getInfo();

        network.setNetworkAddress(info.getNetworkAddress());
        network.setBroadcastAddress(info.getBroadcastAddress());
        network.setSubnetMask(info.getNetmask());
        networkDAO.insertNetwork(network); // network VO is updated with the generated ID

        List<IpAddressVO> ipList = new ArrayList<>();
        for (String ip : info.getAllAddresses()) {
            IpAddressVO ipVO = new IpAddressVO();
            ipVO.setIpAddress(ip);
            ipVO.setNetworkId(network.getId());
            if (ip.equals(info.getNetworkAddress()) || ip.equals(info.getBroadcastAddress())) {
                ipVO.setStatus("RESERVED");
            } else {
                ipVO.setStatus("AVAILABLE");
            }
            ipList.add(ipVO);
        }
        ipAddressDAO.insertIpAddressList(ipList);
    }

    @Override
    @Transactional
    public void allocateIp(String ipAddress, String userId, String purpose) {
        IpAddressVO ip = ipAddressDAO.findByIpAddress(ipAddress);
        if (ip == null) {
            throw new IpNotFoundException("IP address not found: " + ipAddress);
        }
        if (!"AVAILABLE".equals(ip.getStatus())) {
            throw new IpAllocationException("IP address is not available for allocation: " + ipAddress);
        }

        ipAddressDAO.updateIpStatus(ipAddress, "ALLOCATED");

        AllocationVO allocation = new AllocationVO();
        allocation.setIpId(ip.getId());
        allocation.setUserId(userId);
        allocation.setPurpose(purpose);
        allocationDAO.insertAllocation(allocation);
    }

    @Override
    @Transactional
    public void releaseIp(String ipAddress) {
        IpAddressVO ip = ipAddressDAO.findByIpAddress(ipAddress);
        if (ip == null) {
            throw new IpNotFoundException("IP address not found: " + ipAddress);
        }
        if (!"ALLOCATED".equals(ip.getStatus())) {
            throw new IpAllocationException("IP address is not allocated: " + ipAddress);
        }

        allocationDAO.deleteAllocationByIpId(ip.getId());
        ipAddressDAO.updateIpStatus(ipAddress, "AVAILABLE");
    }

    @Override
    public List<IpAddressVO> searchIps(SearchCriteriaDTO criteria) {
        return ipAddressDAO.searchIps(criteria);
    }

    @Override
    public int countIps(SearchCriteriaDTO criteria) {
        return ipAddressDAO.countIps(criteria);
    }
}
