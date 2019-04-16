package com.foxhis.threadpoolexecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CacheThreadPool {

	private static ExecutorService synInterDBThreadService = Executors.newSingleThreadExecutor();
	
	public static void main(String[] args) {
		for(int i=0;i<100;i++)
		{
			synInterDBThreadService.execute(new task());
			/*try {
				if(i%10==0)Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			System.out.println(i);
		}
	}
}


class task implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(Thread.currentThread().getName());
	}
	
}
