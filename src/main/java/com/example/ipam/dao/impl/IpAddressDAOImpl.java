package com.example.ipam.dao.impl;

import com.example.ipam.dao.IpAddressDAO;
import com.example.ipam.vo.IpAddressVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IpAddressDAOImpl implements IpAddressDAO {

    private final SqlSessionTemplate sqlSession;

    @Autowired
    public IpAddressDAOImpl(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public void insert(IpAddressVO vo) {
        sqlSession.insert("ipam.insert", vo);
    }

    @Override
    public void update(IpAddressVO vo) {
        sqlSession.update("ipam.update", vo);
    }

    @Override
    public IpAddressVO findByIpAddress(String ipAddress) {
        return sqlSession.selectOne("ipam.findByIpAddress", ipAddress);
    }

    @Override
    public List<IpAddressVO> findAll() {
        return sqlSession.selectList("ipam.findAll");
    }
}
