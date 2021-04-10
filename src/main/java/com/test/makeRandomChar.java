package com.test;

import java.util.Random;

public class makeRandomChar {
	
	public makeRandomChar() {
		
	}
	
	public String getRandomChars(int charLength) {
		Random rnd =new Random();
	
		StringBuffer buf =new StringBuffer();
	
		for(int i=0;i<charLength;i++){
	
		    if(rnd.nextBoolean()){
	
		        buf.append((char)((int)(rnd.nextInt(26))+97));
	
		    }else{
	
		        buf.append((rnd.nextInt(10)));
	
		    }
		}
		return buf.toString();
	}
	
	public int getRandomIdx(int maxIdx) {
		double getRandom=Math.random();
		int retIdx=(int)(getRandom*maxIdx)+1;
		return retIdx;
	}
	public int getRandomIdx2(int maxIdx) {
		double getRandom=Math.random();
		int retIdx=(int)(getRandom*maxIdx)+1;
		if(retIdx==0) {
			retIdx=1;
		}
		return retIdx;
	}
}
