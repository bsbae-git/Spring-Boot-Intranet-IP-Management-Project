package com.example.ipam.dao.impl;

import com.example.ipam.dao.UserDAO;
import com.example.ipam.vo.UserVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {

    private final SqlSessionTemplate sqlSession;

    @Autowired
    public UserDAOImpl(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public UserVO findByUsername(String username) {
        return sqlSession.selectOne("user.findByUsername", username);
    }
}
