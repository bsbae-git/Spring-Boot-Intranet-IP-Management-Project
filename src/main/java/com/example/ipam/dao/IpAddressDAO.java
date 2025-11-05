package com.example.ipam.dao;

import com.example.ipam.vo.IpAddressVO;
import java.util.List;

public interface IpAddressDAO {
    void insert(IpAddressVO vo);
    void update(IpAddressVO vo);
    IpAddressVO findByIpAddress(String ipAddress);
    List<IpAddressVO> findAll();
}
