package com.jules.ipam.dao;

import com.jules.ipam.vo.NetworkVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NetworkDAO {
    void insertNetwork(NetworkVO network);
}
