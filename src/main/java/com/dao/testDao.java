package com.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.daoMain;
import com.test.makeRandomChar;
public class testDao extends daoMain{
	
	public static testDao test=new testDao();
	makeRandomChar ranMake=new makeRandomChar();
	String[] grupCode= {"groupId1","groupId2","groupid3"};
	
	public testDao() {
		log("testDao..");
		super.Init();
	}
	public void dataSelect() {
		try {
			List<Map> list=sqlSession.selectList("mybatis.testMapper.selectLowData");
			log("list111..End..");
			List<Map> list1=sqlSession.selectList("mybatis.testMapper.selectLowData");
			log("list222..End..");
			List<Map> list2=sqlSession.selectList("mybatis.testMapper.selectLowData");
			log("list333..End..");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public void insertData(Map param,int seqIdx) {
		try {
			param.put("grp_id",grupCode[ranMake.getRandomIdx(2)]);
			param.put("col1",ranMake.getRandomChars(5));
			param.put("col2",ranMake.getRandomChars(5));
			param.put("col3",ranMake.getRandomChars(5));
			param.put("etc",ranMake.getRandomChars(20));
			param.put("bigo",ranMake.getRandomChars(100));
			int insResult=sqlSession.insert("mybatis.testMapper.insertLowData",param);
			int getSeq=(Integer)param.get("seqColumn");
			log("@@ Seq["+getSeq+"]..insResult..["+insResult+"]");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public void seqInit(Map param) {
		int update=sqlSession.update("mybatis.testMapper.seqInit",param);
		
	}
	public void deleteData(Map param) {
		try {
			int delResult=sqlSession.delete("mybatis.testMapper.deleteLowData",param);
			//sqlSession.commit();
			log("delResult..["+delResult+"]");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
			
	public static void main(String[] args) {
		System.out.println("delete....Start....!");
		test.deleteData(new HashMap());
		System.out.println("inset....Start....!");
		for(int i=0; i < 300000;i++) {
			test.insertData(new HashMap(),i+1);
		}	
		test.sessionCommit();
		test.sessionClose();
	}
}
