package com.jules.ipam.service;

import com.jules.ipam.dto.SearchCriteriaDTO;
import com.jules.ipam.vo.IpAddressVO;
import com.jules.ipam.vo.NetworkVO;

import java.util.List;

public interface IpService {
    void registerNetwork(NetworkVO network);

    void allocateIp(String ipAddress, String userId, String purpose);

    void releaseIp(String ipAddress);

    List<IpAddressVO> searchIps(SearchCriteriaDTO criteria);

    int countIps(SearchCriteriaDTO criteria);
}
