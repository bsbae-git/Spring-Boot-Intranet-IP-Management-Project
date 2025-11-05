package com.example.ipam.service;

import com.example.ipam.vo.IpAddressVO;
import java.util.List;

public interface IpAddressService {
    List<IpAddressVO> getAllIpAddresses();
    void updateScanResults(String subnet);
}
