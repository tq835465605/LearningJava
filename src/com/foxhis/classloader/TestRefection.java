package com.foxhis.classloader;

public class TestRefection {

	public static void main(String[] args) throws ClassNotFoundException {
		// TODO Auto-generated method stub
        String className = "com.foxhis.classloader.MyClassLoader";
		Class pClass1=Class.forName(className);
        Class pClass2=MyClassLoader.class;
        Class pClass3=new MyClassLoader().getClass();
        System.out.println(pClass1==pClass2);
        System.out.println(pClass1==pClass3);
	}

}
