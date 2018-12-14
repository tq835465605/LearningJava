package com.foxhis.singleton;


/**
  * 静态内部类实现单例模式
 * @author Administrator
 *
 */
public class SingletonInnerClass {


	
	private SingletonInnerClass() {}
	
	private static class InnerSingleton{
		
		private static SingletonInnerClass sInnerClass = new SingletonInnerClass();
	}
	
	public static SingletonInnerClass getInstance()
	{
		return InnerSingleton.sInnerClass;
	}
	
}
