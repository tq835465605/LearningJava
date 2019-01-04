package com.foxhis.thread;

public class InterruptedDemo {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Demo demo = new Demo();
		Thread thread =new Thread(demo);
		thread.start();
		System.out.println(thread.getState());
		Thread.sleep(100);
		thread.interrupt();	
		//thread.join();
		System.out.println(thread.getState());
		System.out.println(thread.getState());
		System.out.println(thread.getState());
		System.out.println(thread.getState());
		System.out.println(thread.getState());
		
		System.out.println(thread.getState());
		
		/*Demo1 demo1 = new Demo1();
		demo1.setFlg(true);
		Thread thread1 =new Thread(demo1);
		thread1.start();
		System.out.println(thread1.getState());
		Thread.sleep(10);
		thread1.interrupt();
		System.out.println(thread1.getState());
		System.out.println(thread1.isInterrupted());
		if(thread1.isInterrupted())
		{
			demo1.setFlg(false);
			System.out.println("进入了");
		}
		System.out.println(thread1.getState());*/
		
	}

	
	
	
	static class Demo implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			while(!Thread.currentThread().isInterrupted())
			{
				for(int i=0;i<100;i++)
				{
					System.out.println(i);
					if(i==50)
					{
						System.out.println("i=50开始堵塞");
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							System.out.println("阻塞异常");
							Thread.currentThread().interrupt();
							break;
						}
					}
				}
			}
		      System.out.println("线程停止");
		}
		
	}
	
	static class Demo1 implements Runnable
	{

		private volatile boolean flg = false;
		
		public boolean isFlg() {
			return flg;
		}

		public void setFlg(boolean flg) {
			this.flg = flg;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(flg)
			{
				System.out.println(1234);
			}
		}
		
	}

}
