package com.foxhis.gc;

import java.util.ArrayList;
import java.util.List;

public class JConsoleView {

	static class OOMObject{
		
		public byte[] p = new byte[64*1024];//64K
	}
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-genaddobjecterated method stub
		addobject(1000);
		Thread.sleep(1000);
		System.gc();  
	}
	
	public static void addobject(int times) throws InterruptedException{
		List<OOMObject> obj = new ArrayList<OOMObject>();
		for (int i=0;i<times;i++) {		
			Thread.sleep(50);
			obj.add(new OOMObject());
		}
  
	}

}
