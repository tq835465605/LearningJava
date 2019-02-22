package com.foxhis.singleton;

public class SingletonSynchr {
	
	private  static SingletonSynchr instance;
	
	private static SingletonSynchr other1;
	
	private static SingletonSynchr other2;
	private SingletonSynchr() {}
	
	/** 重入双重判断 */
	public static SingletonSynchr getInstance()
	{
		if(instance ==null)
		{
			synchronized (SingletonSynchr.class) {
				if(instance==null)
				{
				     instance = new SingletonSynchr();
				}
			}	
		}
		return instance;
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		
		thread1 name = new thread1();
		name.start();
		thread2 name2 = new thread2();
		name2.start();
		Thread.sleep(100);
		while(true)
		{
			if(other1 != other2)
			{
				name.interrupt();
				name2.interrupt();
				break;
			}
		}
	}
	
	
     static class thread1 extends Thread{
		
		@Override
		public void run()
		{
			while(!thread1.interrupted())
			{
				try {
					other1 = SingletonSynchr.getInstance();
					System.out.println(SingletonSynchr.getInstance());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
     static class thread2 extends Thread{
		
		@Override
		public void run()
		{
			while(!thread2.interrupted())
			{
				try {
					other2 = SingletonSynchr.getInstance();
					System.out.println(SingletonSynchr.getInstance());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
