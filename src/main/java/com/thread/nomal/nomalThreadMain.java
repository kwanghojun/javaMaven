package com.thread.nomal;

public class nomalThreadMain {
	abstract class job1 implements Runnable {
		 public job1() {}
		 public void run() {
			 JobInterFace int1 =new jobImple1();
         }
	}
	abstract class job2 implements Runnable {
		public job2() {}
		public void run() { 
			JobInterFace int2 =new jobImple2();
		}
	}
	abstract class job3 implements Runnable {
		public job3() {}
		public void run() {
			JobInterFace int3 =new jobImple3();
		}
	}
	
	
}
