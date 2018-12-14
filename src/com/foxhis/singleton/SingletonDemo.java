package com.foxhis.singleton;


/**
 * 私有构造器实现单例模式，饿汉模式
 * 静态变量SINGLETON_DEMO和静态代码块在类加载的时候是线程安全，且只加载一次，但是，如果不用，则浪费。
 * 而静态方法，在调用的时候才加载
 * @author Administrator
 *
 */
public class SingletonDemo {

	private static final SingletonDemo SINGLETON_DEMO = new SingletonDemo();
	
	private SingletonDemo() {
		// TODO Auto-generated constructor stub
	}
	
	public static SingletonDemo getInstance()
	{
		return SINGLETON_DEMO;
	}
	
	
	public static void main(String[] args) {
		
		System.out.println(SingletonDemo.getInstance());
		System.out.println(SingletonDemo.getInstance());
		System.out.println(SingletonDemo.getInstance());
		System.out.println(SingletonEnum.INSTANCE.hashCode());
		System.out.println(SingletonEnum.INSTANCE.hashCode());
		System.out.println(SingletonEnum.INSTANCE.hashCode());
		
		
		thread1 tt = new thread1();
		tt.start();
		thread2 t2 = new thread2();
		t2.start();
		
		System.out.println(SingletonInnerClass.getInstance());
		System.out.println(SingletonInnerClass.getInstance());
		System.out.println(SingletonInnerClass.getInstance());
		
	}
	
	static class thread1 extends Thread{
		
		@Override
		public void run()
		{
			try {
				System.out.println(SingletonSyn.getInstance());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
     static class thread2 extends Thread{
		
		@Override
		public void run()
		{
			try {
				System.out.println(SingletonSyn.getInstance());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
