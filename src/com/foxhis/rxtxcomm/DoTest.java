package com.foxhis.rxtxcomm;

public class DoTest {

	
	private static Object object = new Object();
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		Thread1 thread1 = new Thread1();
		thread1.start();
		
		Thread.sleep(5000);
		
		synchronized (object) {
			object.notify();
		}
	}

	
	
	
	
	static class Thread1 extends Thread{
		
		public void run()
		{
			while(true)
			{
				try {
					synchronized (object) {
						System.out.println("Wait...");
						object.wait();
						System.out.println("No wait...");
					}
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
	}
	
    
}
