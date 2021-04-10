package com.thread.nomal;

import java.util.HashMap;

import com.dao.testDao;

public class jobImple3 implements JobInterFace{
	public void method1() {
		System.out.println("Job3...Method1..");
	}
	public void method2() {
		System.out.println("Job3...Method2..");
	}
	
	public void daoInsert(Long idx) {
		System.out.println("Job1...Method1..");
		testDao dao=new testDao();
		for(int i=0; i < 700;i++) {
			idx.valueOf(idx.longValue()+1);
			dao.insertData(new HashMap(), idx.intValue());
		}
	}
}
