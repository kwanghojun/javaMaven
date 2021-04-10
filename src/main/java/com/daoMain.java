package com;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public abstract class daoMain {
	protected String resource = "mybatis/mybatis-config.xml";
	protected SqlSessionFactory sqlSessionFactory;
	protected SqlSession sqlSession;
	public void Init() {
		daoInit();
	}
	
	public  void daoInit() {
		log("daoMain..");
		
		try {
			log("sqlSessionFactory..["+sqlSessionFactory+"]");
			log("sqlSession..["+sqlSession+"]");
			if(sqlSession==null || (sqlSession !=null && sqlSession.getConnection() !=null && !sqlSession.getConnection().isClosed())) {
				InputStream inputStream = Resources.getResourceAsStream(resource);
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
				sqlSession= sqlSessionFactory.openSession(true);
			} else {
				log("sqlSession Connected..!");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void getReConnection(boolean autoCommitFlag) {
		sessionClose();
		daoInit();
		getSqlssion(autoCommitFlag);
	}
	
	protected void getSqlssion(boolean autoCommitFlag) {
		try {
			boolean reLoadFlag=false;
			if(sqlSession ==null || (sqlSession !=null && sqlSession.getConnection() ==null)) {
				reLoadFlag=true;
			}
			if(reLoadFlag) {
				InputStream inputStream = Resources.getResourceAsStream(resource);
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
				sqlSession= sqlSessionFactory.openSession(autoCommitFlag);
			} else {				
				log("getSqlssion..Inited");
			}
		} catch(Exception e) {
			System.out.println("getSqlssion Err..!");
			e.printStackTrace();
		}
	}
	
	protected void sessionClose() {
		try {
			if(sqlSession !=null && !sqlSession.getConnection().isClosed()) {
				sqlSession.close();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void sessionCommit() {
		if(sqlSession !=null) {
			try {
				if(!sqlSession.getConnection().getAutoCommit()) {
					sqlSession.commit();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	protected void sessionRollBack() {
		try {
			if(sqlSession !=null && !sqlSession.getConnection().getAutoCommit()) {
				sqlSession.rollback();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	protected static void log(String msg) {
		System.out.println("log..Msg..["+msg+"]");
	}

}
