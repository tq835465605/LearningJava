package com.foxhis.classloader;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 
 * 
ExtClassLoader 查找java.ext.dirs
AppClassLoader 查找java.class.path
Bootstrap ClassLoader查找sun.mic.boot.class
非上面的路径的，可自定义类加载

 自定义步骤

编写一个类继承自ClassLoader抽象类。
复写它的findClass()方法。
在findClass()方法中调用defineClass()。

 * @author Administrator
 *
 */
public class MyClassLoader extends ClassLoader{

	//加载的目录
	private String mlibPath;
	
	public MyClassLoader() {
		// TODO Auto-generated constructor stub
	}
	public MyClassLoader(String mlibPath) {
		// TODO Auto-generated constructor stub
		this.mlibPath = mlibPath;
	}
	@Override
	public Class<?> findClass(String name) throws ClassNotFoundException
	{
		String filename = getFileName(name);
		File classfile = new File(mlibPath, filename);
		
		try {
			FileInputStream fStream= new FileInputStream(classfile);
			ByteArrayOutputStream bis = new ByteArrayOutputStream();
			int i=0;
			while((i = fStream.read() )!=-1)
			{
				bis.write(i);
			}
			byte[] buf=bis.toByteArray();	
			bis.close();
			fStream.close();
			return defineClass(name,buf, 0, buf.length);
				
					
		} catch (Exception e) {
			// TODO: handle exception
		}
		return super.findClass(name);
	}
	
	private String getFileName(String name)
	{
		int index = name.lastIndexOf('.');
		if(index==-1)
		{
			return name+".class";
		}
		else {
			return name.substring(index+1)+".class";
		}
	}
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
		MyClassLoader myClassLoader = new MyClassLoader("F:\\workspace\\XCSItfGenServer\\target\\classes\\com\\foxhis\\itf\\main");
		Class<?> clazz = myClassLoader.loadClass("com.foxhis.itf.main.LogFrame");
		Object object=clazz.newInstance();
		Method method = clazz.getDeclaredMethod("main", null);
		
		method.invoke(object, null);
		
	}

}
