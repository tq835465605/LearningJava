package com.foxhis.classloader;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * class.forname与classloader区别
 * 两者都是加载类
 * 前者会把类中的静态块与静态变量以及给静态变量赋值的方法也加载
 * 后者只是简单的类加载到jvm
 * 
 * 
 * 
 * */
public class ClassLoaderDemo {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException, IOException {
		// TODO Auto-generated method stub
		JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Jar Files(*.jar)", new String[] { "jar" });
        chooser.setFileFilter(filter);
        final int returnVal = chooser.showOpenDialog(null);
        if (returnVal != 0) {
            return;
        }
        File file = chooser.getSelectedFile();
        System.out.println(file.getAbsolutePath());
        String filepath = file.getAbsolutePath();
        List<URL> files = new ArrayList<URL>();
        if(file.isFile())
        {
        	URL url = new URL("file", null, filepath);
        	files.add(url);
        }
        URL[] urls = (URL[])files.toArray(new URL[0]);
        
        URLClassLoader loader = new URLClassLoader(urls,Thread.currentThread().getContextClassLoader());
       
        JarFile jarFile = new JarFile(file);
        Enumeration<JarEntry> entries = jarFile.entries();
        List<Class<?>> listClass = new ArrayList<Class<?>>();
        while(entries.hasMoreElements())
        {
        	JarEntry jarEntry = entries.nextElement();
        	if(jarEntry.isDirectory())
        	{
        		continue;
        	}
        	String name = jarEntry.getName();
        	if(!name.endsWith(".class"))
        	{
        		continue;
        	}
        	name = name.replace('/', '.').substring(0, name.lastIndexOf("."));
        	if (name.indexOf("$") != -1) {
        		continue;
        	}
        	 Class<?> class1 =loader.loadClass(name);
        	 listClass.add(class1);

        }
        for(Class<?> class1:listClass)
        {
        	 Object object = class1.newInstance();
             Method method = class1.getDeclaredMethod("readCardInternal",Map.class);
             method.setAccessible(true);
             Map<String, Object> input = new HashMap<String, Object>();
             input.put("roomno", "default");
             Object re = method.invoke(object, input);
             System.out.println(re);
        }
       
        
	}

}
