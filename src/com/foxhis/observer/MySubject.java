package com.foxhis.observer;

public class MySubject extends AbstractSubject{

	@Override
	public void doSelfOpertion() {
		// TODO Auto-generated method stub
		 System.out.println("update self!");  
	     notifyObservers();  
	}

}
