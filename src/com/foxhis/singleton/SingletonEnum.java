package com.foxhis.singleton;


/**
 * 枚举方式实现单例模式
 * @author Administrator
 *
 */
public enum SingletonEnum {

	INSTANCE;
	
	public void doSomething()
	{
		System.out.println("do it");
	}
}
