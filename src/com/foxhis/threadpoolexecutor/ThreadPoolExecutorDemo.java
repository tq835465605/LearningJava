package com.foxhis.threadpoolexecutor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorDemo {

	
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		ThreadPoolExecutor pExecutor = new ThreadPoolExecutor(5, 10, 5000, 
				TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(6) );
		
		for(int i=0;i<16;i++)
		{
			MyTask myTask = new MyTask(i);
			pExecutor.execute(myTask);
			//Thread.sleep(1000);
		    
			System.out.println("线程池中线程数："+pExecutor.getPoolSize()+
					" 线程最大值："+pExecutor.getMaximumPoolSize()+
					" 线程等待数："+pExecutor.getQueue().size()+
					" 已经执行完数："+pExecutor.getCompletedTaskCount()+
					" 活跃的个数"+	pExecutor.getActiveCount());
		}
		pExecutor.shutdown();
	}
	
	
	static class MyTask implements Runnable{
		
		int taskNum;
		
		public MyTask(int tasknum) {
			// TODO Auto-generated constructor stub
			this.taskNum = tasknum;
		}
		
		@Override
		public void run() {
			
			System.out.println("正在执行task:"+taskNum);
		   try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			 System.out.println("task "+taskNum+"执行完毕");
		}
	}

}
