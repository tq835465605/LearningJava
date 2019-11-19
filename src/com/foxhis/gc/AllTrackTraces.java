package com.foxhis.gc;

import java.util.Map;

public class AllTrackTraces {

	public static void main(String[] args) {


		for (Map.Entry<Thread, StackTraceElement[]> stackTrace : Thread.getAllStackTraces().entrySet()) {
			Thread thread = (Thread) stackTrace.getKey();
			StackTraceElement[] stack = (StackTraceElement[]) stackTrace.getValue();
			if (thread.equals(Thread.currentThread())) {
				continue;
			}
			System.out.println("线程：" + thread.getName());
			for (StackTraceElement stackTraceElement : stack) {
				System.out.println(stackTraceElement);
			}
		}
	}


}
