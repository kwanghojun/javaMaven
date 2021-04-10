package com.thread.linkedBloking;

public class absExtendObjct extends absObject{
	
	
	public absExtendObjct(String msg) {
		super(msg);
	}
	
	 @Override
	 public void run() {
		 System.out.println("absExtendObjct run.. start..");
	 }
}
