package com.foxhis.observer;

import java.util.Enumeration;
import java.util.Vector;

public abstract class AbstractSubject implements ISubject {

	private Vector<IObserver> observers = new Vector<IObserver>();
	
	@Override
	public void addObserver(IObserver observer) {
		// TODO Auto-generated method stub
        observers.add(observer);
	}

	@Override
	public void removeObserver(IObserver observer) {
		// TODO Auto-generated method stub
       observers.remove(observer);
	}

	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		Enumeration<IObserver> observerenum =  observers.elements();
		while (observerenum.hasMoreElements()) {
			IObserver iObserver = (IObserver) observerenum.nextElement();
			iObserver.update();
		}
	}

	@Override
	public void doSelfOpertion() {
		// TODO Auto-generated method stub
		
	}

}
