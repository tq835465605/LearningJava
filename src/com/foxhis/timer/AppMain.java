package com.foxhis.timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class AppMain {
	
	public static void main(String[] args) throws InterruptedException {
		//注册任务
		String task_name = "myfirsttask";
		int timer = 2;//2秒一次的任务
		IEventListener callback = new MyMessageRetrvice();
		FoxTimerTaskStack.getInstance().regsiterTask_second(task_name,callback, timer, 0);
		FoxTimerTaskStack.getInstance().executeTask(task_name);
		Thread.currentThread().join();
//		 Timer timer = new Timer();
//		 Task task = new Task();
//		 timer.schedule(task, new Date(), 1000);
	}
	
	static class Task extends TimerTask{
		
		     @Override
		     public void run() {
		        System.out.println("Do work...");
	     }
	 }
	static class MyMessageRetrvice implements IEventListener
	{

		@Override
		public Object trigger(Object... objects) {
			// TODO Auto-generated method stub
			System.out.println("do,it");
			return null;
		}
		
	}

}
