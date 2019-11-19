package com.foxhis.gc;

import com.sun.jna.Memory;
import com.sun.jna.Pointer;

public class JNAMemoryGC {

	private static int _1MB = 1024*1024;
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		for(int i=0;i<2000;i++) {
			Pointer p = new Memory(_1MB);
			Thread.sleep(100);
			p=Pointer.NULL;
		}
		//System.gc();
		Thread.sleep(10000);
	}

}
