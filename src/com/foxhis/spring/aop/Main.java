package com.foxhis.spring.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("aop.xml");
		IPerformance performance = (IPerformance)context.getBean("parkproform");
		performance.performance();
		System.out.println();
		performance.performance();
	}


}
