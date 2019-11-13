package com.foxhis.gc;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

/**
 * VM args:-Xmx20M -XX:MaxDirectMemorySize=10M
 * 如果不指定MaxDirectMemorySize大小。默认与Xmx一致
 * @author Administrator
 *
 */
public class DirectMemoryOOM {
	
	private static final int _1MB = 1024 * 1024;
	
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		Field unsafeField = Unsafe.class.getDeclaredFields()[0];
		unsafeField.setAccessible(true);
		Unsafe unsafe = (Unsafe)unsafeField.get(null);
		int i=0;
		while(true) {
			unsafe.allocateMemory(_1MB);
			System.out.println(++i);
		}
	}

}
