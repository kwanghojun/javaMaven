package com.thread.nomal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.dao.testDao;
import com.test.makeRandomChar;

import java.util.concurrent.BlockingQueue;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
public class ExecutorTest {
	
	public static void main(String[] args) {
		int maxLoop=1;
		Long seq=Long.valueOf(0);

		LocalDateTime startTime=LocalDateTime.now();
		try {
			testDao tdao=new testDao();
			tdao.seqInit(new HashMap());
			Map param=new HashMap();
			param.put("regdt","20210410");
			tdao.deleteData(param);
			makeRandomChar random=new makeRandomChar();
			while(maxLoop > 0) {
				log("maxLoop==["+maxLoop+"]");
				ParallelExecutorService paralExecutor=new ParallelExecutorService(seq);
				List<String> jobList = List.of("job1","job2","job3");
				int loopThread=3;
				for(int i=0; i < loopThread;i++) {
					int setIdx=i+1;
					paralExecutor.submit("job"+random.getRandomIdx2(3)); 
				}
				for (int i = 0 ; i < loopThread; i++) {
			            String result = paralExecutor.take();
			            System.out.println("Thread Take..["+result+"]");
			    }
				paralExecutor.close();
				maxLoop--;
			}
		} catch(Exception e) {
			
		}
		
		LocalDateTime endTime=LocalDateTime.now();
		System.out.println("Thread...End..!..["+getDateTime()+"]");
		long chaSecond=ChronoUnit.SECONDS.between(startTime, endTime);
		long chaMinu=chaSecond/60;
		long chaHour=chaMinu/60;
		chaSecond=chaSecond % 60;
		System.out.println("######### 쇼이시간..["+chaHour+"] 시...["+chaMinu+"] 분...["+chaSecond+"] 초 #############");
	}
	
	
	public static void log(String getMsg) {
		System.out.println(getMsg);
	}
	private static class nomalFixedThreadPoolService {
		
		private final ExecutorService normalExcutor=Executors.newFixedThreadPool(2);
		public nomalFixedThreadPoolService() {
		}
		public void  exector() {
			normalExcutor.submit(() -> {
				try {
					Thread.sleep(3000);
					JobInterFace int1 =new jobImple1();
					int1.method1();
	
				} catch(Exception e) {}
			});
			
			normalExcutor.submit(() -> {
				try {
					Thread.sleep(2000);
					JobInterFace int2 =new jobImple2();
					int2.method1();
	
					} catch(Exception e) {}
			});
			normalExcutor.submit(() -> {
				try {
					Thread.sleep(1000);
					JobInterFace int3 =new jobImple3();
					int3.method1();
	
					} catch(Exception e) {}
			});
			
			normalExcutor.shutdown();
			try {
				if(normalExcutor.awaitTermination(1, TimeUnit.SECONDS)) {
					System.out.println("executor Termination");
				} else {
					normalExcutor.shutdownNow();
				}
			} catch(Exception e) {
				
			}
			System.out.println("executor...completed..");
		}
	}
	
	public static String getDateTime() {
		DateTimeFormatter pattern=DateTimeFormatter.ofPattern("YYYY-MM-DD HH:mm:ss SSS");
		LocalDateTime date=LocalDateTime.now();
		String strDate=date.format(pattern);
		return strDate;
	}
	
}
