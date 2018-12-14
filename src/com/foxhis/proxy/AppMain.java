package com.foxhis.proxy;

import java.lang.reflect.Proxy;

public class AppMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Customer customer = new Customer(12000);
		//用4s店买车
//		BuyCarProxy buyCarProxy = new BuyCarProxy(customer);
//		buyCarProxy.buyCar();
		
		DynamicProxy dynamicProxy = new DynamicProxy(customer);
		IBuyCar car = (IBuyCar)Proxy.newProxyInstance(dynamicProxy.getClass().getClassLoader(), customer.getClass().getInterfaces(), dynamicProxy);
		car.buyCar();
		
		AppMain appMain=new AppMain();
		appMain.dotask();
		
	}
	
	
	public void say() {
		System.out.println("hh");
	}
	
	public void dotask()
	{
		Customer customer = new Customer(12000);
		customer.customSay(this);
	}

}
