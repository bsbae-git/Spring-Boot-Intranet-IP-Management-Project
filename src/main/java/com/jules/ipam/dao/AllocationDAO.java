package com.jules.ipam.dao;

import com.jules.ipam.vo.AllocationVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AllocationDAO {
    void insertAllocation(AllocationVO allocation);

    void deleteAllocationByIpId(Integer ipId);
}
