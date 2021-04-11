package com.thread.nomal;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParallelExecutorService {

	private final int maxCore=Runtime.getRuntime().availableProcessors();
	private final ExecutorService executor = Executors.newFixedThreadPool(maxCore);
	private final BlockingQueue<String> queue = new ArrayBlockingQueue<>(20);
	private Long seq;
	public ParallelExecutorService(Long seq) {
		this.seq=seq;
	}
	
	public void submit(String jobName) {
		log("jobName..["+jobName+"]");
		log("Que Size..["+queue.size()+"]");
		executor.submit(() -> {
			String threadName=Thread.currentThread().getName();
			if(jobName.equals("job1")) {
				try {
					log("--job1..Start..["+threadName+"]");
					JobInterFace int1 =new jobImple1();
					int1.daoInsert(seq);
					log("--job1..End..["+threadName+"]");
					queue.put(jobName+" : Queue");
				} catch(Exception e) {
					e.printStackTrace();
				}
			} else if(jobName.equals("job2")) {
				try {
					log("--job2..Start..["+threadName+"]");
					JobInterFace int2 =new jobImple2();
					int2.daoInsert(seq);
					log("job2..End..["+threadName+"]");
					queue.put(jobName+" : Queue");
				} catch(Exception e) {
					e.printStackTrace();
				}
			} else if(jobName.equals("job3")) {
				try {
					log("--job3..Start..["+threadName+"]");
					JobInterFace int3 =new jobImple3();
					int3.daoInsert(seq);
					log("--job3..End..["+threadName+"]");
					queue.put(jobName+" : Queue");
				} catch(Exception e) {
					e.printStackTrace();
				}
			} 
		});
	}
	
	
	public void nomalProecss(String jobName) {
		if(jobName.equals("job1")) {
			try {
				log("--job1..Start..");
				JobInterFace int1 =new jobImple1();
				int1.daoInsert(seq);
				log("--job1..End..");
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(jobName.equals("job2")) {
			try {
				log("--job2..Start..");
				JobInterFace int2 =new jobImple2();
				int2.daoInsert(seq);
				log("job2..End.");
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(jobName.equals("job3")) {
			try {
				log("--job3..Start..");
				JobInterFace int3 =new jobImple3();
				int3.daoInsert(seq);
				log("--job3..End..");
			} catch(Exception e) {
				e.printStackTrace();
			}
		} 
	}
	
	public String take() {
		try {
			return queue.take();
		} catch(Exception e) {
			e.printStackTrace();
			throw new IllegalStateException(e);
		}
	}
	
	public void close() {
		List<Runnable> unfinishedTasks = executor.shutdownNow();
		if(!unfinishedTasks.isEmpty()) {
			System.out.println("��� ������� ����.");
		}
	}
	
	public static void log(String getMsg) {
		System.out.println(getMsg);
	}
}
