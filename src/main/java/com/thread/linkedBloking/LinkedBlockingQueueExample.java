package com.thread.linkedBloking;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueExample {

    public static void main(String[] args) {
    	
    	try {
    		absObject absRun=new absExtendObjct("absExtendObjct Msg..!");
	    	Thread absThread=new Thread(absRun);
	    	absThread.start();
	    	absThread.join();
	        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>(15);
	        List<String> list=new ArrayList();
	        List<String> list_tmp=list;
	        String a[]=new String[10];        
	        String b[]=a;
	        a[0]="1234";
	        Producer producer = new Producer(queue);
	        ObservingConsumer obsConsumer = new ObservingConsumer(queue, producer,list);
	        RemovingConsumer remConsumer = new RemovingConsumer(queue, producer,list);
	        list.add("last add");
	        System.out.println("list.. size..["+list.size()+"]..tmp size..["+list_tmp.size()+"]..b..["+b[0]+"]");
	        Thread producerThread = new Thread(producer);
	        Thread obsConsumerThread = new Thread(obsConsumer);
	        Thread remConsumerThread = new Thread(remConsumer);
	        
	        producerThread.start();
	        obsConsumerThread.start();
	        remConsumerThread.start();
	        queue.put("StringLast");
    	} catch(Exception e) {
    		e.printStackTrace();
    	} finally {
    		System.out.println("finally...!");
    	}
    }
}