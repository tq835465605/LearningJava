package com.foxhis.generics;

public class GenericsDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Plat<? extends Fruit> pf = new Plat<Apple>(new Apple());
		pf.getT().something();
		Plat<? extends Fruit> pf1 = new Plat<Banana>(new Banana());
		pf1.getT().something();
		 String str2 = System.getProperty("java.ext.dirs");
		 System.out.println(str2);

		 A a =new A();
		 B b = new B();
		 System.out.println(a instanceof B);//false
		 System.out.println(b instanceof A);//true
	}
	
	static class A extends Object{}
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
