package com.foxhis.singleton;
import java.util.concurrent.locks.*;



/**
 * 懒汉式，线程安全
 * @author Administrator
 *
 */
public class SingletonSyn {

	private static volatile SingletonSyn singletonSyn;
	
	private static ReentrantLock lock = new ReentrantLock();
	
	private SingletonSyn(){
		
	}
	
	public static SingletonSyn getInstance() throws InterruptedException
	{
		if(singletonSyn ==null)
		{
			Thread.sleep(100);
			lock.lock();
			if(singletonSyn ==null)
			singletonSyn = new SingletonSyn();
			lock.unlock();

		}
		return singletonSyn;
	}
}
