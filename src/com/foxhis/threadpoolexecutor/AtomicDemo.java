package com.foxhis.threadpoolexecutor;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicDemo {
	
	private static AtomicInteger atomicInteger = new AtomicInteger();
	public static void main(String[] args) throws InterruptedException {
		//AtomicInteger atomicInteger = new AtomicInteger();
		atomicInteger.set(10);
		System.out.println("main"+atomicInteger.get());
		Changed changed = new Changed();
		changed.start();
		changed.join();
		System.out.println("main"+atomicInteger.get());
//		System.out.println(atomicInteger.get());
//		System.out.println(atomicInteger.incrementAndGet());
//		System.out.println(atomicInteger.getAndIncrement());
//		System.out.println(atomicInteger.get());
	}
	
	static class Changed extends Thread
	{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			for (int i = 0; i < 5; i++) {
				atomicInteger.getAndIncrement();
				System.out.println("changed:"+atomicInteger.get());
			}
		}
	}

}

