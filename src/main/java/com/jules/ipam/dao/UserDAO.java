package com.jules.ipam.dao;

import com.jules.ipam.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDAO {
    UserVO findByUsername(String username);
}
