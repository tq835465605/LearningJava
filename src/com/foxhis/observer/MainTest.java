package com.foxhis.observer;

public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MySubject mySubject = new MySubject();
		mySubject.addObserver(new Observer1());
		mySubject.addObserver(new Observer2());
		mySubject.doSelfOpertion();
	}

}
