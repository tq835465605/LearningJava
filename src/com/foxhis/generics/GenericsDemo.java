package com.foxhis.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * 苹果 is a 水果 
    装苹果的容器 is not a 装水果的容器 
    <? super T>表示包括T在内的任何T的父类，<? extends T>表示包括T在内的任何T的子类
    
 * @author Administrator
 *
 */
public class GenericsDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Plat<? extends Fruit> pf = new Plat<Apple>(new Apple());
		pf.getT().something();
		Plat<? extends Fruit> pf1 = new Plat<Banana>(new Banana());
		pf1.getT().something();
//		 String str2 = System.getProperty("java.ext.dirs");
//		 System.out.println(str2);

		//Number is a superclass of Integer
		List<? super Integer> foo3 = new ArrayList<Integer>();
		List<? super Integer> foo4 = new ArrayList<Number>();
		
		 A a =new A();
		 B b = new B();
		 System.out.println(a instanceof B);//false
		 System.out.println(b instanceof A);//true
	}
	
	static class A {}
	static class B extends A{}
	

     interface Fruit {

		public void something();
	}

	static class Apple implements Fruit {
		public Apple() {
			// TODO Auto-generated constructor stub
			super();
		}

		public void something() {
			System.out.println("Apple do something");
		}

	}

	static class Banana implements Fruit {
		public Banana() {
			// TODO Auto-generated constructor stub
			super();
		}

		public void something() {
			System.out.println("Banana do something");
		}
	}

	static class Plat<T> {

		T t;

		public Plat(T t) {
			// TODO Auto-generated constructor stub
			this.t = t;
		}

		public T getT() {
			return t;
		}

		public void setT(T t) {
			this.t = t;
		}

	}
}
