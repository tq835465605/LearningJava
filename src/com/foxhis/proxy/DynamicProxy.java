package com.foxhis.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


//动态代理
public class DynamicProxy implements InvocationHandler {

	Object obj ;
	public DynamicProxy(Object object) {
		// TODO Auto-generated constructor stub
		obj = object;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		Object object = method.invoke(obj, args);
		return object;
	}

}
