package com.thread.nomal;

import java.util.HashMap;

import com.dao.testDao;

public class jobImple1 implements JobInterFace{
	public void method1() {
		
	}
	public void method2() {
		System.out.println("Job1...Method2..");
	}
	
	public void daoInsert(Long idx) {
		log("Job1","...Method1..");
		testDao dao=new testDao();
		for(int i=0; i < 500;i++) {
			idx.valueOf(idx.longValue()+1);
			dao.insertData(new HashMap(), idx.intValue());
		}
	}
	

	public static void log(String type,String msg) {
		System.out.println("log["+type+"]...Msg..["+msg+"]");
	}
}
