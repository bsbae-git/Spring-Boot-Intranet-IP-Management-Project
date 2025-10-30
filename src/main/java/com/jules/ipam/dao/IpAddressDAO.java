package com.jules.ipam.dao;

import com.jules.ipam.dto.SearchCriteriaDTO;
import com.jules.ipam.vo.IpAddressVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IpAddressDAO {
    void insertIpAddressList(@Param("list") List<IpAddressVO> list);

    IpAddressVO findByIpAddress(String ipAddress);

    void updateIpStatus(@Param("ipAddress") String ipAddress, @Param("status") String status);

    List<IpAddressVO> searchIps(SearchCriteriaDTO criteria);

    int countIps(SearchCriteriaDTO criteria);
}
