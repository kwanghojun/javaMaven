package com.thread.linkedBloking;

import java.util.ArrayList;
import java.util.List;

abstract class absObject implements Runnable {
	protected List comList=new ArrayList();
	
	public absObject(String msg) {
		System.out.println(msg);
	}
	
	 @Override
	 public void run() {
		 System.out.println("absObject run.. start..");
	 }
}
