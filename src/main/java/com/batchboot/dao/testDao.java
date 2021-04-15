package com.batchboot.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class testDao {
	
    @Resource(name = "sqlSession")
    private SqlSessionTemplate sqlSession;

    public List<Map> selectTest(Map<String, String> data) {
        return this.sqlSession.selectList("selectTest", data);
    }
}
