package com.example.ipam.dao;

import com.example.ipam.vo.UserVO;

public interface UserDAO {
    UserVO findByUsername(String username);
}
