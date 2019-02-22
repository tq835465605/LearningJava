package com.foxhis.spring.aop;


/**横向切点 */
public class PointCut {
	
	public void beforeProform()
	{
		System.out.println("Please take sates");
	}
	
	public void afterProform()
	{
		System.out.println("鼓掌");

	}
	
	public void exeptionProform()
	{
		System.out.println("退票");
	}

}
