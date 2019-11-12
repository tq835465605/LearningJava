package com.foxhis.gc;

public class JavaVMStackSOF {
	
	private int stackLenth=1;
	public void stackLeak() {
		stackLenth++;
		stackLeak();
	}
	
	public static void main(String[] args) throws Throwable {
		JavaVMStackSOF oom = new JavaVMStackSOF();
		try {
			oom.stackLeak();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			System.out.println("stack lenth:"+ oom.stackLenth);
			throw e;
		}
	}

}
